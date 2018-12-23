package com.techoffice;

import com.techoffice.model.Block;
import com.techoffice.model.NoobChain;
import com.techoffice.util.StringUtil;

public class App {

	public static NoobChain noobChain = new NoobChain();
	
	public static void main(String[] args){
		Block block1 = new Block("This is a testing block 1", "0");
		noobChain.add(block1);
		
		Block block2 = new Block("This is a testing block 1", block1.getHash());
		noobChain.add(block2);
		
		Block block3 = new Block("This is a testing block 1", block2.getHash());
		noobChain.add(block3);
		
		if (noobChain.isValidChain()){
			String blockchainJson = StringUtil.getJson(noobChain.getBlockchain());
			System.out.println(blockchainJson);
		}

	}
}
