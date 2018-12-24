package com.techoffice.model;

public class TransactionInput {

	private String transactionOutputId;
	private TransactionOutput transactionOutput;
	
	public TransactionInput(String transactionOutputId){
		this.transactionOutputId = transactionOutputId;
	}
	
	public String getTransactionOutputId(){
		return this.transactionOutputId;
	}
	
	public void setTransactionOutput(TransactionOutput transactionOutput){
		this.transactionOutput = transactionOutput;
	}
	
	public TransactionOutput getTransactionOutput(){
		return this.transactionOutput;
	}
	
}
