package com.techoffice.util;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;

public class SecurityUtil {

	public static KeyPairGenerator getKeyPairGenerator(){
		try{
			return KeyPairGenerator.getInstance("ECDSA","BC");			
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static KeyPair getKeyPair(){
		try{
			KeyPairGenerator keyPairGenerator = getKeyPairGenerator();	
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
			keyPairGenerator.initialize(ecSpec, random);  
	        KeyPair keyPair = keyPairGenerator.generateKeyPair();
	        return keyPair;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static MessageDigest getMessageDigest(){
		try{
			return MessageDigest.getInstance("SHA-256");		
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	public static Signature getSignature(){
		try{
			return Signature.getInstance("ECDSA", "BC");			
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
