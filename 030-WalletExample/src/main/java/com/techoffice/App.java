package com.techoffice;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.techoffice.model.NoobChain;
import com.techoffice.model.Transaction;
import com.techoffice.model.Wallet;
import com.techoffice.util.StringUtil;

public class App {

	public static NoobChain noobChain = new NoobChain();
	
	public static void main(String[] args){
		Security.addProvider(new BouncyCastleProvider());
		Wallet wallet1 = new Wallet();
		Wallet wallet2 = new Wallet();
		
		System.out.println("Wallet 1");
		System.out.println("==========");
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(wallet1.getPrivateKey()));
		System.out.println(StringUtil.getStringFromKey(wallet1.getPublicKey()));
		System.out.println("==========");

		System.out.println("Wallet 2");
		System.out.println("==========");
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(wallet2.getPrivateKey()));
		System.out.println(StringUtil.getStringFromKey(wallet2.getPublicKey()));
		System.out.println("==========");
		
		Transaction transaction = new Transaction(wallet1.getPublicKey(), wallet2.getPublicKey(), 5, null);
		transaction.generateSignature(wallet1.getPrivateKey());
		System.out.println("Transaction Verified: " + transaction.verifySignature());

	}
}
