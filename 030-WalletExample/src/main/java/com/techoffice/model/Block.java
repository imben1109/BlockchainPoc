package com.techoffice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.techoffice.util.BlockHelper;

public class Block {
	
	private String hash;
	private String previousHash;
	private String data; 
	private long timeStamp; 
	private int nonce = 0;
	private List<Transaction> transactionList = new ArrayList<Transaction>();
	public static int difficulty = 5;
	
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
	}
	
	public void mineBlock(){
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		String hash = BlockHelper.calcHash(this.previousHash, this.timeStamp, Integer.toString(nonce) + this.data);
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = BlockHelper.calcHash(this.previousHash, this.timeStamp, Integer.toString(nonce) + this.data);
		}
		this.hash = hash;
		System.out.println("Block Mined!!! : " + hash);
	}
	
	public boolean addTransaction(Transaction transaction){
		this.transactionList.add(transaction);
		if (!"0".equals(this.previousHash)){
			boolean processResult = transaction.process();
			if (!processResult){
				return false;
			}
		}
		return true;
	}

	public String getHash() {
		return hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public String getData() {
		return data;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

}
