package com.techoffice;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.techoffice.model.NoobChain;
import com.techoffice.model.Transaction;
import com.techoffice.model.TransactionInput;
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
		baseWallet.setNoobChain(noobChain);
		Wallet wallet2 = new Wallet();
		baseWallet.setNoobChain(noobChain);
		WalletInfoHelper.printWalletDetail("Base Wallet", baseWallet);
		WalletInfoHelper.printWalletDetail("Wallet 1", wallet1);
		WalletInfoHelper.printWalletDetail("Wallet 2", wallet2);
	
		// init
		TransactionOutput transactionOutput = new TransactionOutput(baseWallet.getPublicKey(), 100f, "0");
		noobChain.putUnspentTransactionOutput(transactionOutput.getId(), transactionOutput);
		System.out.println(baseWallet.getBalance());
		// block
//		TransactionInput 
//		Transaction transaction = new Transaction(baseWallet.getPublicKey(), baseWallet.getPublicKey(), 100f, null );
//		transaction.generateSignature(baseWallet.getPrivateKey());
//		transaction.setNoobChain(noobChain);
		
	}
}
