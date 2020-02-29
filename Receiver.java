import java.math.BigInteger;
public class Receiver{
	private BigInteger Modulus;
	private String Message;
	private RSA key;
	public Receiver(int N){
		key = new RSA(N);
		//
		Modulus = key.ShareMod();
		
	}

	public BigInteger SharePublic(){
		//System.out.println(Modulus);
		return(Modulus);
	}
	public void Receive_Message(BigInteger Encrypted){
		BigInteger decrypt = Encrypted.modPow(key.GetPri(),Modulus);
		Message = new String(decrypt.toByteArray());
		System.out.println(Message);
	}

}