package com.techoffice.model;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.techoffice.util.SecurityUtil;

public class Wallet {
	
	private PrivateKey privateKey;
	private PublicKey publicKey;
	private NoobChain noobChain = null;

	private Map<String, TransactionOutput> unspentTransactionOutputMap = null;
	
	public Wallet(){
		unspentTransactionOutputMap = new HashMap<String, TransactionOutput>();
		try{
			KeyPair keyPair = SecurityUtil.getKeyPair();
        	this.privateKey = keyPair.getPrivate();
        	this.publicKey = keyPair.getPublic();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public void setNoobChain(NoobChain noobChain){
		this.noobChain = noobChain;
	}
	
	public PrivateKey getPrivateKey(){
		return this.privateKey;
	}
	
	public PublicKey getPublicKey(){
		return this.publicKey;
	}
	
	public float getBalance(){
		float total = 0;
		
		for (Map.Entry<String, TransactionOutput> item: this.noobChain.getUnspentTransactionOutputMap().entrySet()){
			TransactionOutput unspentTransactionOutput = item.getValue();
			if (unspentTransactionOutput.isMine(publicKey)){
				unspentTransactionOutputMap.put(unspentTransactionOutput.getId(), unspentTransactionOutput);
				total += unspentTransactionOutput.getValue();
			}
		}
		return total;
	}
	
	public Transaction sendFund(PublicKey recipient, float value){
		List<TransactionInput> inputs = new ArrayList<TransactionInput>();
//		float total = 0;
		Transaction newTransaction = new Transaction(recipient, publicKey , value, inputs);
		return newTransaction;
	}
}
