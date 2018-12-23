package com.techoffice.model;

import java.security.PublicKey;

import com.techoffice.util.StringUtil;

public class TransactionOutput {

	public String id;
	public PublicKey reciepient;
	public float value;
	public String parentTransactionId; 
	
	public TransactionOutput(PublicKey reciepient, float value, String parentTransactionId) {
		this.reciepient = reciepient;
		this.value = value;
		this.parentTransactionId = parentTransactionId;
		this.id = StringUtil.getDigestMessage(StringUtil.getStringFromKey(reciepient)+Float.toString(value)+parentTransactionId);
	}
	
	public boolean isMine(PublicKey publicKey) {
		return (publicKey == reciepient);
	}

	public String getId() {
		return id;
	}

	public PublicKey getReciepient() {
		return reciepient;
	}

	public float getValue() {
		return value;
	}

	public String getParentTransactionId() {
		return parentTransactionId;
	}

	
	
}
