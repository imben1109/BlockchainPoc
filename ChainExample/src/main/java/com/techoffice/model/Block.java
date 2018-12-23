package com.techoffice.model;

import java.util.Date;

import com.techoffice.util.BlockHelper;

public class Block {
	
	public String hash;
	private String previousHash;
	private String data; 
	private long timeStamp; 
	
	public Block(String data,String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		this.hash = BlockHelper.calcHash(this.previousHash, this.timeStamp, this.data);
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
