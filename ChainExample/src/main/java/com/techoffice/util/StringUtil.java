package com.techoffice.util;

import java.security.MessageDigest;

import com.google.gson.Gson;

public class StringUtil {

	/**
	 * Get SHA-256 Digest Message
	 * @param input
	 * @return Digest Message
	 */
	public static String getSha256DigestMessage(String input){		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
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

}
