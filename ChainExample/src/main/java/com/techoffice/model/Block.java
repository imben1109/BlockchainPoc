package com.techoffice.model;

import java.util.Date;

import com.techoffice.util.BlockHelper;

public class Block {
	
	public String hash;
	private String previousHash;
	private String data; 
	private long timeStamp; 
	private int nonce = 0;
	public static int difficulty = 5;
	
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.mineBlock();
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
