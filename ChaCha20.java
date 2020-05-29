//package com.javainterviewpoint;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ChaCha20
{
	static String plainText = "This is a plain text which will be encrypted by ChaCha20 Algorithm";
	private KeyGenerator keygen;
	private SecretKey key;

	public ChaCha20() throws Exception{

		keygen = KeyGenerator.getInstance("ChaCha20");
		keygen.init(256);

		// Generate Key
		key = keygen.generateKey();
	}
	public ChaCha20(SecretKey skey){
		this.key = skey;
	}

	public SecretKey GetSKey(){
		return(key);
	}

	public static void main(String[] args) throws Exception
	{
		
	}

	public static byte[] encrypt(byte[] plaintext, SecretKey key) throws Exception
	{
		byte[] nonceBytes = new byte[12];
		int counter = 5;
		
		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("ChaCha20");

		// Create ChaCha20ParameterSpec
		ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, counter);
				
		// Create SecretKeySpec
		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "ChaCha20");

		// Initialize Cipher for ENCRYPT_MODE
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);

		// Perform Encryption
		byte[] cipherText = cipher.doFinal(plaintext);

		return cipherText;
	}

	public static String decrypt(byte[] cipherText,SecretKey key) throws Exception
	{
		byte[] nonceBytes = new byte[12];
		int counter = 5;

		// Get Cipher Instance
		Cipher cipher = Cipher.getInstance("ChaCha20");

		// Create ChaCha20ParameterSpec
		ChaCha20ParameterSpec paramSpec = new ChaCha20ParameterSpec(nonceBytes, counter);
				
		// Create SecretKeySpec
		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "ChaCha20");

		// Initialize Cipher for DECRYPT_MODE
		cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);

		// Perform Decryption
		byte[] decryptedText = cipher.doFinal(cipherText);
		//System.out.println(decryptedText);

		return new String(decryptedText);
	}
}