import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;


public class AES{
	private final static SecureRandom srandom = new SecureRandom();

	public static void main(String[] args){
		float totaltime2 = 0;
		
		byte[] iv = IntialiseVector();
		IvParameterSpec ivspec = new IvParameterSpec(iv);

		//SaveIv(iv);

		long startTime = System.nanoTime();
		SecretKey skey = keyGen();
		byte[] keyb = skey.getEncoded();
		SaveKey(keyb);

		byte[] encoded = encrypt(skey,ivspec);
		String plainText = Decrypt(skey,ivspec,encoded);
		System.out.println(plainText);
		long endTime   = System.nanoTime();
		long totalTime = (endTime - startTime); 
		totaltime2 = totaltime2 + (float)totalTime/1000000;
		System.out.println(totaltime2);
	}

	private static byte[]  IntialiseVector(){
		byte[] iv = new byte[128/8];
		srandom.nextBytes(iv);
		return(iv);
	}
	private static void SaveIv(byte[] iv){
		String ivFile = "ivfile";               // saving parameters
		try{
			FileOutputStream out = new FileOutputStream(ivFile);
			out.write(iv);
		}catch(Exception e){
			System.out.println("Filestream error");
		}
	}
	private static SecretKey keyGen(){
		KeyGenerator kgen= null;
		try{
			 kgen = KeyGenerator.getInstance("AES");
			
		}catch(Exception e){
			System.out.println("Keygen error");	

		}finally{
			SecretKey skey = kgen.generateKey();
			return(skey);
		}
	}

	private static void SaveKey(byte[] keyb){
		String keyFile = "keyFile";
		try{
			FileOutputStream out = new FileOutputStream(keyFile);
			out.write(keyb);
		}catch(Exception e){System.out.println("Filestream error");}
	}

	private static SecretKey LoadKey(){
		String keyFile = "keyFile";
		SecretKeySpec skey = null;
		try{
			byte[] keyb =  Files.readAllBytes(Paths.get(keyFile));
			skey = new SecretKeySpec(keyb, "AES");
			
		}catch(Exception e){System.out.println("Filestream error");
		}finally{
			return(skey);
		}
		
	}


	public static byte[] encrypt(SecretKey skey,IvParameterSpec ivspec){
		
		String plainText = "Test 1 and Test 2";
		String outFile = "OutFile";
		byte[] encoded  = null;
		try{
			Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);
			//FileOutputStream out = new FileOutputStream(outFile);
    		byte[] input = plainText.getBytes("UTF-8");
    		encoded = ci.doFinal(input);
   			//out.write(encoded);
		}catch(Exception e){System.out.println("Encrypt error");
		}finally{
			return(encoded);
		}
	}
	public static String Decrypt(SecretKey skey,IvParameterSpec ivspec,byte[] encoded2){
		String inFile = "OutFile";
		String plainText2 = "";
		try{
			Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ci.init(Cipher.DECRYPT_MODE, skey, ivspec);
			//byte[] encoded2 = Files.readAllBytes(Paths.get(inFile));
			plainText2 = new String(ci.doFinal(encoded2), "UTF-8");
		}catch(Exception e){System.out.println("Decrypt error");
		}finally{
			return(plainText2);
		}

	}


}