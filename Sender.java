import java.math.BigInteger;
public class Sender{

	private BigInteger Modulus;

	public void requestPuKey(BigInteger PublicKey){
		Modulus = PublicKey;
	}
	public BigInteger encrypt(String Message){
		BigInteger publicKey  = new BigInteger("65537"); 
		byte[] bytes = Message.getBytes();
		BigInteger message = new BigInteger(bytes);
		//System.out.println("PlainText   = " + Message);
        //System.out.println("bytes  = " + bytes);
        
       // System.out.println(Modulus);
        
		BigInteger encrypt = message.modPow(publicKey,Modulus);
		return(encrypt);
	}


}