package com.techoffice;

import com.techoffice.model.Block;

public class App {

	public static void main(String[] args){
		Block block1 = new Block("This is a testing block 1", "0");
		System.out.println("Block 1 Hash: " + block1.getHash());
		Block block2 = new Block("This is a testing block 1", block1.getHash());
		System.out.println("Block 2 Hash: " + block2.getHash());
		Block block3 = new Block("This is a testing block 1", block1.getHash());
		System.out.println("Block 3 Hash: " + block3.getHash());

	}
}
