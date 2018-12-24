package com.techoffice;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.techoffice.model.Block;
import com.techoffice.model.NoobChain;
import com.techoffice.model.Transaction;
import com.techoffice.model.TransactionOutput;
import com.techoffice.model.Wallet;
import com.techoffice.util.WalletInfoHelper;

public class App {

	public static NoobChain noobChain = new NoobChain();
	
	public static void main(String[] args){
		Security.addProvider(new BouncyCastleProvider());
		
		// block chain
		NoobChain noobChain = new NoobChain();
		
		// wallet
		Wallet baseWallet = new Wallet();
		baseWallet.setNoobChain(noobChain);
		Wallet wallet1 = new Wallet();
		wallet1.setNoobChain(noobChain);
		Wallet wallet2 = new Wallet();
		wallet2.setNoobChain(noobChain);
		WalletInfoHelper.printWalletDetail("Base Wallet", baseWallet);
		WalletInfoHelper.printWalletDetail("Wallet 1", wallet1);
		WalletInfoHelper.printWalletDetail("Wallet 2", wallet2);
	
		// initialize base block
		Transaction transaction = new Transaction(baseWallet.getPublicKey(), wallet1.getPublicKey(), 100f, null);
		transaction.setNoobChain(noobChain);
		transaction.generateSignature(baseWallet.getPrivateKey());

		TransactionOutput transactionOutput = new TransactionOutput(wallet1.getPublicKey(), 100f, "0");
		noobChain.putUnspentTransactionOutput(transactionOutput.getId(), transactionOutput);
		
		// Base Block
		System.out.println("Base Block");
		System.out.println("==========");
		Block baseBlock = new Block("0");
		baseBlock.addTransaction(transaction);
		noobChain.add(baseBlock);		
		System.out.println("Wallet 1: " + wallet1.getBalance());
		System.out.println("Wallet 2: " + wallet2.getBalance());


		// block 1
		System.out.println("Block 1");
		System.out.println("==========");
		Block block1 = new Block(baseBlock.getHash());
		block1.addTransaction(wallet1.sendFund(wallet2.getPublicKey(), 40f));
		noobChain.add(block1);
		System.out.println("Wallet 1: " + wallet1.getBalance());
		System.out.println("Wallet 2: " + wallet2.getBalance());

	}
}
