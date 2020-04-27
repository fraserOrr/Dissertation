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
	private SecretKey skey;
	private IvParameterSpec ivspec;

	public AES(){
		byte[] iv = IntialiseVector();
		ivspec = new IvParameterSpec(iv);
		skey = keyGen();
	}
	public AES(SecretKey skey, IvParameterSpec ivspec ){
		this.skey = skey;
		this.ivspec = ivspec;
	}
	public SecretKey GetSKey(){
		return(skey);
	}
	public IvParameterSpec getIV(){
		return(ivspec);
	}

	public static void main(String[] args){
		/*long avgTime = 0;
		for(int i =1;i<1000;i++){
		float totaltime2 = 0;
		long setupTime1 = System.nanoTime();
		//byte[] iv = IntialiseVector();
		//IvParameterSpec ivspec = new IvParameterSpec(iv);
		long setupTime2 = System.nanoTime();
		System.out.println("Initalise vector setup time: " + (float)(setupTime2 - setupTime1)/1000000);
		//SaveIv(iv);

		long keyGenTime1 = System.nanoTime();	
		//SecretKey skey = keyGen();
		//byte[] keyb = skey.getEncoded();
		long keyGenTime2 = System.nanoTime();	
		System.out.println("Key Generation time: " + (float)(keyGenTime2 - keyGenTime1)/1000000);  
		SaveKey(keyb);

		long startTime = System.nanoTime();
		byte[] encoded = encrypt(skey,ivspec);
		long endTime   = System.nanoTime();
		String plainText = Decrypt(skey,ivspec,encoded);
		long endTime2   = System.nanoTime();
		//System.out.println(plainText);
		
		
		long totalTime = (endTime - startTime); 

		long totalTime2 = (endTime2 - endTime); 
		long totalTime3 = (endTime2 - startTime); 
		//totaltime2 = totaltime2 + (float)totalTime/1000000;
		//System.out.println(totaltime2);
		System.out.println("Encryption takes: " + (float)totalTime/1000000);
		System.out.println("Decryption takes: " + (float)totalTime2/1000000);
		System.out.println("Total Time takes: " + (float)totalTime3/1000000);
		avgTime = avgTime +totalTime2+totalTime2;
		}

		System.out.println("");
		System.out.println("Avg Total Time " + (float)avgTime/1000/1000000);

	*/
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


	public static byte[] encrypt(byte[] msg , SecretKey skey,IvParameterSpec ivspec){
		
		
		String outFile = "OutFile";
		byte[] encoded  = null;
		try{
			Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
			ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);
			
    		encoded = ci.doFinal(msg);
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