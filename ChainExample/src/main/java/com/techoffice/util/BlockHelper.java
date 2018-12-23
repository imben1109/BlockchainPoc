package com.techoffice.util;

public class BlockHelper {

	public static String calcHash(String previousHash, Long timeStamp, String data){
		String hash = StringUtil.getSha256DigestMessage(previousHash + Long.toString(timeStamp) + data);
		return hash;
	}
}
