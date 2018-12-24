package com.techoffice.model;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.techoffice.util.StringUtil;

public class Transaction {

	private String transactionId;
	private PublicKey sender; 
	private PublicKey reciepient; 
	private float value;
	private byte[] signature; 
	private long timeStamp; 
	private NoobChain noobChain = null;
	
	private List<TransactionInput> inputs = new ArrayList<TransactionInput>();
	private List<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	public Transaction(PublicKey from, PublicKey to, float value,  List<TransactionInput> inputs){
		this.sender = from;
		this.reciepient = to;
		this.value = value;
		this.inputs = inputs;
		this.timeStamp = new Date().getTime();
		this.transactionId = this.calcHash();
	}
	
	public void setNoobChain(NoobChain noobChain){
		this.noobChain = noobChain;
	}
	
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(this.sender) + StringUtil.getStringFromKey(this.reciepient) + Float.toString(this.value);
		this.signature = StringUtil.getSignature(privateKey, data);
	}

	public boolean verifySignature(){
		String data = StringUtil.getStringFromKey(this.sender) + StringUtil.getStringFromKey(this.reciepient) + Float.toString(this.value)	;
		return StringUtil.verifySignatureArr(this.sender, data, this.signature);
	}
	
	// for generating id 
	private String calcHash(){
		return StringUtil.getDigestMessage(
				StringUtil.getStringFromKey(sender)+
				StringUtil.getStringFromKey(reciepient)+
				Float.toString(value)+
				Long.toString(timeStamp)
			);
	} 

	public boolean process(){
		if (!verifySignature()){
			System.out.println("Transaction is not verified");
			return false;
		}
		for (TransactionInput input: inputs){
			String transactionOutputId = input.getTransactionOutputId();
			TransactionOutput unspentTransactionOutput = this.noobChain.getUnspentTransactionOutput(transactionOutputId);
			input.setTransactionOutput(unspentTransactionOutput);
		}
		float leftOver = this.getInputsValue() - value;
		outputs.add(new TransactionOutput( this.reciepient, value, this.transactionId)); 
		outputs.add(new TransactionOutput( this.sender, leftOver, this.transactionId)); 
		for (TransactionOutput output: outputs){
			this.noobChain.putUnspentTransactionOutput(output.getId(), output);
		}
		for (TransactionInput input: inputs){
			if (input.getTransactionOutput() == null ){
				continue;
			}
			this.noobChain.removeUnspentTransactionOutput(input.getTransactionOutput().getId());
		}
		return true;
	}
	
	public float getInputsValue(){
		float total = 0;
		for(TransactionInput input: this.inputs){
			if (input.getTransactionOutput() == null){
				continue;
			}
			total += input.getTransactionOutput().getValue();
		}
		return total;
	}
	
	public float getOutputValue(){
		float total = 0;
		for (TransactionOutput output: this.outputs){
			total += output.getValue();
		}
		return total;
	}
	
	public String getTransactionId(){
		return this.transactionId;
	}
}
