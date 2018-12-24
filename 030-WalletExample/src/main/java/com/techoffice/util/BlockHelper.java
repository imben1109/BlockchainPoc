package com.techoffice.util;

public class BlockHelper {

	public static String calcHash(String previousHash, Long timeStamp, int nonce, String merkleRoot){
		String hash = StringUtil.getDigestMessage(
						previousHash + 
						Long.toString(timeStamp) +
						Integer.toString(nonce) + 
						merkleRoot
					);
		return hash;
	}
}
