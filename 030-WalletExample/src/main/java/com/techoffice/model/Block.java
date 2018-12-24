package com.techoffice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.techoffice.util.BlockHelper;

public class Block {
	
	private String merkleRoot;
	private String hash;
	private String previousHash;
	private long timeStamp; 
	private int nonce = 0;
	private List<Transaction> transactionList = new ArrayList<Transaction>();
	public static int difficulty = 0;
	
	public Block(String previousHash ) {
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = BlockHelper.calcHash(this.previousHash, this.timeStamp, nonce, merkleRoot);
	}
	
	public void mineBlock(){
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		String hash = BlockHelper.calcHash(this.previousHash, this.timeStamp, nonce, merkleRoot);
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = BlockHelper.calcHash(this.previousHash, this.timeStamp, nonce, merkleRoot );
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

	public long getTimeStamp() {
		return timeStamp;
	}

}
