import java.math.BigInteger;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Sender{
	private SecretKey skey;
	private IvParameterSpec ivspec;
	private AES aes;
	private BigInteger Modulus;
	private ChaCha20 chacha;

	public void ChaChaSetup() throws Exception{
		chacha = new ChaCha20();
		skey = chacha.GetSKey();
		
	}
	public byte[] ChaChaEncrypt(byte[] msg) throws Exception{
		
		byte[] encoded = chacha.encrypt(msg,skey);
		return(encoded);
	}

	public void AesSetup(){
		aes = new AES();
		skey = aes.GetSKey();
		ivspec = aes.getIV();
	}
	public SecretKey GetSKey(){
		return(skey);
	}
	public IvParameterSpec getIV(){
		return(ivspec);
	}
	public byte[] AesEncrypt(byte[] msg){
	
		
		byte[] encoded = aes.encrypt(msg,skey,ivspec);


		return(encoded);
	}

	public void requestPuKey(BigInteger PublicKey){
		Modulus = PublicKey;
	}
	public BigInteger encrypt(byte[] msg){
		BigInteger publicKey  = new BigInteger("65537"); 
		
		BigInteger message = new BigInteger(msg);
		//System.out.println("PlainText   = " + Message);
        //System.out.println("bytes  = " + bytes);
        
       // System.out.println(Modulus);
        
		BigInteger encrypt = message.modPow(publicKey,Modulus);
		return(encrypt);
	}


}