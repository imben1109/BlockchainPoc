package com.techoffice.model;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

import com.techoffice.util.StringUtil;

public class Transaction {

	private String transactionId; 
	private PublicKey sender; 
	private PublicKey reciepient; 
	private float value;
	private byte[] signature; 
	
	private ArrayList<TransactionInput> inputs = new ArrayList<TransactionInput>();
	private ArrayList<TransactionOutput> outputs = new ArrayList<TransactionOutput>();
	
	public void generateSignature(PrivateKey privateKey) {
		String data = StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value)	;
	}


}
