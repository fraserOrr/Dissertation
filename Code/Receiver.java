import java.math.BigInteger;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
public class Receiver{

	private BigInteger Modulus;
	private String Message;
	private RSA key;


	private SecretKey skey;
	private IvParameterSpec ivspec;
	private AES aes;

	private ChaCha20 chacha;

	public Receiver(){

	}
	public void ChaChaSetup(SecretKey skey)throws Exception{
		chacha = new ChaCha20(skey);
		this.skey = skey;
	}

	public String ChaChaDecode(byte[] Encoded)throws Exception{
		String plaintext = "";
		plaintext = chacha.decrypt(Encoded, skey);
		return(plaintext);
	}

	public void AesSetup(SecretKey skey, IvParameterSpec ivspec){
		this.skey = skey;
		this.ivspec = ivspec;

		AES aes = new AES(skey, ivspec);

	}
	public String AesDecode(byte[] Encoded){
		String plaintext = "";
		plaintext = aes.Decrypt(skey,ivspec,Encoded);
		return(plaintext);
	}

	public Receiver(int N){
		key = new RSA(N);
		//
		Modulus = key.ShareMod();
		
	}

	public BigInteger SharePublic(){
		//System.out.println(Modulus);
		return(Modulus);
	}
	public String Receive_Message(BigInteger Encrypted){
		BigInteger decrypt = Encrypted.modPow(key.GetPri(),Modulus);
		Message = new String(decrypt.toByteArray());
		return(Message);
	}

}