import java.math.BigInteger;
public class Network{

	public static void main(String[] args) {
		String Message = "Test1 and test2";

		RSAexample(Message);
		//System.out.println(bytes.length);

	}	
		
	public static void StreamCipher(){
		
	}
		

	
	public static void RSAexample(String Message){
		byte[] bytes = Message.getBytes();
		float totaltime2 = 0;
		///for(int i =1;i<1000;i++){

			long startTime = System.nanoTime();
			Sender IoTSender = new Sender();
			Receiver IoTReciever = new Receiver(bytes.length*8);
			
		
			//request public key cmpnenets 
			IoTSender.requestPuKey(IoTReciever.SharePublic());

			//encrypt message and send
			BigInteger Encrypted = IoTSender.encrypt(Message);
			// receieve and decrypt
			IoTReciever.Receive_Message(Encrypted);


			long endTime   = System.nanoTime();
			long totalTime = (endTime - startTime);
			totaltime2 = totaltime2 + (float)totalTime/1000000;
			System.out.println(totaltime2);
		//}
	}
}