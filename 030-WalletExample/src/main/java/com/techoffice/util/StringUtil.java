package com.techoffice.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import com.google.gson.Gson;

public class StringUtil {

	/**
	 * Get Digest Message String
	 * @param input
	 * @return Digest Message
	 */
	public static String getDigestMessage(String input){		
		try {
			MessageDigest digest = SecurityUtil.getMessageDigest();   
			byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
			StringBuffer hexString = new StringBuffer(); 
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
	
	/**
	 * Get JSON String 
	 * @param obj
	 * @return JSON String
	 */
	public static String getJson(Object obj){
		Gson gson = new Gson();
		String json = gson.toJson(obj);
		return json;
	}
	
	/**
	 * Get Key String
	 * @param key
	 * @return Key String
	 */
	public static String getStringFromKey(Key key) {
		return Base64.getEncoder().encodeToString(key.getEncoded());
	}
	
	/**
	 * Get Signature 
	 * 
	 * @param privateKey
	 * @param input
	 * @return 
	 */
	public static byte[] getSignature(PrivateKey privateKey, String input){
		try{
			Signature signature = SecurityUtil.getSignature();
			signature.initSign(privateKey);
			byte[] inputByteArr = input.getBytes();
			signature.update(inputByteArr);
			byte[] signatureByteArr = signature.sign();
			return signatureByteArr;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param publicKey
	 * @param data
	 * @param signature
	 * @return true if verified. false if otherwise.
	 */
	public static boolean verifySignatureArr(PublicKey publicKey, String data, byte[] signatureByteArr){
		try{
			Signature signature = SecurityUtil.getSignature();
			signature.initVerify(publicKey);
			signature.update(data.getBytes());
			return signature.verify(signatureByteArr);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

}
