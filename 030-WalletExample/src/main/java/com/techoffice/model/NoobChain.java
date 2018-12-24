package com.techoffice.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NoobChain {
	
	private ArrayList<Block> blockchain = null; 
	private Map<String, TransactionOutput> unspentTransactionOutputMap = null;
	
	public NoobChain(){
		blockchain = new ArrayList<Block>(); 
		unspentTransactionOutputMap = new HashMap<String, TransactionOutput>();
	}
	
	public void add(Block block){
		block.mineBlock();
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
	
	public TransactionOutput getUnspentTransactionOutput(String transactionOutputId){
		TransactionOutput output = this.unspentTransactionOutputMap.get(transactionOutputId);
		return output;
	}
	
	public void putUnspentTransactionOutput(String transactionOutputId, TransactionOutput output){
		this.unspentTransactionOutputMap.put(transactionOutputId, output);
	}
	
	public void removeUnspentTransactionOutput(String transactionOutputId){
		this.unspentTransactionOutputMap.remove(transactionOutputId);
	}
	
	public Map<String, TransactionOutput> getUnspentTransactionOutputMap(){
		return this.unspentTransactionOutputMap;
	}
	

	
}
