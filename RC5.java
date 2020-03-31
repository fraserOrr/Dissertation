//package com.javainterviewpoint;

import java.util.Base64;

import javax.crypto.*;
import java.security.*;

public class RC5
{
	static String plainText = "This is a plain text which will be encrypted by ChaCha20 Algorithm";

	public static void main(String[] args) throws Exception
	{
		//long avgTime = 0;
		//for(int i =1;i<100;i++){
		KeyGenerator keyGenerator = KeyGenerator.getInstance("RC5");
		//keyGenerator.init(256);

		// Generate Key
		SecretKey key = keyGenerator.generateKey();

		System.out.println("Original Text  : " + plainText);

		long startTime = System.nanoTime();

		byte[] cipherText = encrypt(plainText.getBytes(), key);
		//System.out.println("Encrypted Text : " + Base64.getEncoder().encodeToString(cipherText));
		long endTime   = System.nanoTime();
		String decryptedText = decrypt(cipherText, key);
		long endTime2   = System.nanoTime();
		
		


		System.out.println("DeCrypted Text : " + decryptedText);




		long totalTime = (endTime - startTime); 
		long totalTime2 = (endTime2 - endTime); 
		System.out.println("Encryption takes: " + (float)totalTime/1000000);
		System.out.println("Decryption takes: " + (float)totalTime2/1000000);
		System.out.println("Total time takess " + (float)(totalTime2+totalTime2)/1000000);
		//avgTime = avgTime +totalTime2+totalTime2;
		//}

		//System.out.println("");
		//System.out.println("Avg Total Time " + (float)avgTime/100/1000000);
	}

	public static byte[] encrypt(byte[] plaintext, SecretKey key) throws Exception
	{
		
		
		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("RC5");

	

		// Initialize Cipher for ENCRYPT_MODE
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// Perform Encryption
		byte[] cipherText = cipher.doFinal(plaintext);

		return cipherText;
	}

	public static String decrypt(byte[] cipherText, SecretKey key) throws Exception
	{
		

		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("RC5");

	

		// Initialize Cipher for DECRYPT_MODE
		cipher.init(Cipher.DECRYPT_MODE, key);

		// Perform Decryption
		byte[] decryptedText = cipher.doFinal(cipherText);

		return new String(decryptedText);
	}
}