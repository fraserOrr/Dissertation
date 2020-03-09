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


public class StreamCipher{
	private final static SecureRandom srandom = new SecureRandom();
	public static void main(String[] args){
		byte[] iv = new byte[128/8];
		srandom.nextBytes(iv);
		IvParameterSpec ivspec = new IvParameterSpec(iv);  // used for creating the cipher

		String ivFile = "ivfile";               // saving parameters
		try(FileOutputStream out = new FileOutputStream(ivFile)){
			out.write(iv);
		}catch(Exception e){
			System.out.println("Error");
		}




		try{KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecretKey skey = kgen.generateKey();
		
		


		/*String keyFile = "keyFile";
		byte[] keyb =  Files.readAllBytes(Paths.get(keyFile));   //Load Key 
		SecretKeySpec skey = new SecretKeySpec(keyb, "AES");
		*/

		String keyFile = "keyFile";
		try(FileOutputStream out = new FileOutputStream(keyFile)){
			byte[] keyb = skey.getEncoded();
    		out.write(keyb);
		}catch(Exception e){
			System.out.println("Error");
		}
		
		} catch(Exception e){System.out.println("Error"); }

		2010/* decryptinh
		String ivFile = ...;
		byte[] iv = Files.readAllBytes(Paths.get(ivFile));
		IvParameterSpec ivspec = new IvParameterSpec(iv);

		*/

		Cipher ci = Cipher.getInstance("AES/CBC/PKCS5Padding");
		ci.init(Cipher.ENCRYPT_MODE, skey, ivspec);

		

	}

} 