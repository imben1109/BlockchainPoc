package com.techoffice.model;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

import com.techoffice.util.SecurityUtil;

public class Wallet {
	
	private PrivateKey privateKey;
	private PublicKey publicKey;

	private Map<String, TransactionOutput> TransactionOutputMap;
	
	public Wallet(){
		TransactionOutputMap = new HashMap<String, TransactionOutput>();
		try{
			KeyPair keyPair = SecurityUtil.getKeyPair();
        	this.privateKey = keyPair.getPrivate();
        	this.publicKey = keyPair.getPublic();
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	

	
//	public Transaction sendFunds(PublicKey recipient float value){
//		
//	}
}
