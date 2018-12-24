package com.techoffice.util;

import com.techoffice.model.Wallet;

public class WalletInfoHelper {

	public static void printWalletDetail(String walletHeader, Wallet wallet){
		System.out.println(walletHeader);
		System.out.println("==========");
		System.out.println("Private and public keys:");
		System.out.println(StringUtil.getStringFromKey(wallet.getPrivateKey()));
		System.out.println(StringUtil.getStringFromKey(wallet.getPublicKey()));
		System.out.println("==========");
	} 
}
