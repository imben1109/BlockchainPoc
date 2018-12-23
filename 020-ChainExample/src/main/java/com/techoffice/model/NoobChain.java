package com.techoffice.model;

import java.util.ArrayList;
import java.util.List;

public class NoobChain {
	
	private ArrayList<Block> blockchain = null; 

	public NoobChain(){
		blockchain = new ArrayList<Block>(); 
	}
	
	public void add(Block block){
		this.blockchain.add(block);
	}
	
	public boolean isValidChain(){
		for (int i=1; i<blockchain.size(); i++){
			Block currentBlock = blockchain.get(i);
			Block previousBlock = blockchain.get(i-1);
			if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())){
				return false;
			}
		}
		return true;
	}
	
	public List<Block> getBlockchain(){
		return this.blockchain;
	}
}
