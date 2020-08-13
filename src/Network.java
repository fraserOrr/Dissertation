import java.math.BigInteger;
public class Network{

	public static void main(String[] args) {
		//String Message = "Test1 and test2";

		//RSAexample(Message);
		//System.out.println(bytes.length);


	}	

	public static float ChaChaExample(byte[] msg,int latency, String msgType)throws Exception{

	
		long totalTime = 0;
		float totaltime2 = 0;
		long totalsetup = 0;
		float totalsetup2 = 0;
		
		
		//byte[] msg2 = message.getBytes();
		for(int i =1 ; i<10000; i++){
			long setupTime =  System.nanoTime();
				Sender IeDSender = new Sender();
		Receiver IeDReceiver = new Receiver();
			IeDSender.ChaChaSetup();
		IeDReceiver.ChaChaSetup(IeDSender.GetSKey());

			long startTime = System.nanoTime();
		

		
			
			String plaintext = IeDReceiver.ChaChaDecode(IeDSender.ChaChaEncrypt(msg));
			long endTime   = System.nanoTime();
			totalTime = (endTime - startTime);
			totaltime2 = totaltime2 + (float)totalTime/1000000+latency;
			totalsetup = (startTime - setupTime);
			totalsetup2 = totalsetup2 + (float)totalsetup/1000000;
			//System.out.println(plaintext);
		}

		

		System.out.print("Average encryption time:  ");
		System.out.println( totaltime2/10000);
		System.out.print("Average setup time:  ");
		System.out.println( totalsetup2/10000);
		return(totaltime2/10000);
	}
		
	public static float AesExample(byte[] msg,int latency, String msgType){
		
		
		
		
		long totalTime = 0;
		float totaltime2 = 0;
		long totalsetup = 0;
		float totalsetup2 = 0;

		for(int i =1 ; i<10000; i++){
			long setupTime =  System.nanoTime();
			Sender IeDSender = new Sender();
		Receiver IEDReceiver = new Receiver();
			IeDSender.AesSetup();
			IEDReceiver.AesSetup(IeDSender.GetSKey(),IeDSender.getIV());


			long startTime = System.nanoTime();
			byte[] encoded = IeDSender.AesEncrypt(msg);
			String plaintext = IEDReceiver.AesDecode(encoded);

			long endTime   = System.nanoTime();
			totalTime = (endTime - startTime);

			totaltime2 = totaltime2 + (float)totalTime/1000000+latency;

			totalsetup = (startTime - setupTime);
			totalsetup2 = totalsetup2 + (float)totalsetup/1000000;
			//System.out.println(plaintext);
		}
		

		
		System.out.print("Average encryption time:  ");
		System.out.println( totaltime2/10000);
		System.out.print("Average setup time:  ");
		System.out.println( totalsetup2/10000);

		return(totaltime2/10000);
	}
		

	
	public static float RSAexample(byte[] msg,int latency, String msgType){
		

		long totalTime = 0;
		float totaltime2 = 0;
		long totalsetup = 0;
		float totalsetup2 = 0;

		for(int i =1;i<1000;i++){
			long setupTime =  System.nanoTime();
			Sender IoTSender = new Sender();
			Receiver IoTReciever = new Receiver(2048);
			
			
			long startTime = System.nanoTime();
			//request public key cmpnenets 
			IoTSender.requestPuKey(IoTReciever.SharePublic());

			//encrypt message and send
			BigInteger Encrypted = IoTSender.encrypt(msg);
			// receieve and decrypt
			String plaintext = IoTReciever.Receive_Message(Encrypted);


			long endTime   = System.nanoTime();
			totalTime = (endTime - startTime);
			totaltime2 = totaltime2 + (float)totalTime/1000000+latency;
			//System.out.println(totaltime2);
			//System.out.println(plaintext);
			totalsetup = (startTime - setupTime);
			totalsetup2 = totalsetup2 + (float)totalsetup/1000000;
		}
		System.out.print("Average encryption time:  ");
		System.out.println( totaltime2/10000);
		System.out.print("Average setup time:  ");
		System.out.println( totalsetup2/10000);
		return(totaltime2/1000);
	}
}