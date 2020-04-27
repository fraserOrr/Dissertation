import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;


public class SpeedTester extends Frame implements  ActionListener{
	JFrame frame;
	JPanel panel1,panel2,panel3;
	JLabel label1,label2,label3;
	JButton bttn1,bttn2,bttn3;
	JComboBox comboBox, msgComboBox,topologyComboBox,msgSizeBox;
	JTextArea outputText;
	public SpeedTester(){
		setUpGui();
	}

	// main method 
	public void setUpGui(){
		frame = new JFrame("SpeedTester");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(400, 400);

      		  // AES Panel
				panel1 = new JPanel();
				//label1 = new JLabel("AES Alogorithm");
				comboBox = new JComboBox();
				comboBox.addItem("AES");
				comboBox.addItem("RSA");
				comboBox.addItem("ChaCha20");

				msgComboBox = new JComboBox();
				
				msgComboBox.addItem("GOOSE");
				msgComboBox.addItem("SMV");
				msgComboBox.addItem("MMS");

				msgSizeBox = new JComboBox();
				msgSizeBox.addItem("100");
				msgSizeBox.addItem("200");
				msgSizeBox.addItem("300");
				msgSizeBox.addItem("500");

				topologyComboBox = new JComboBox();
				topologyComboBox.addItem("Neglible");
				topologyComboBox.addItem("WLAN");
				topologyComboBox.addItem("4G");



				bttn1 = new JButton("Start");
				bttn1.addActionListener(this);
				panel1.add(comboBox);
				panel1.add(msgComboBox);
				panel1.add(msgSizeBox);
				panel1.add(topologyComboBox);
				panel1.add(bttn1);

				//ChaCha20 Panel
				panel2 = new JPanel();
				label2 = new JLabel("Average Time: ");
				outputText = new JTextArea();
				panel2.add(label2);
				panel2.add(outputText);
				


				frame.getContentPane().add(BorderLayout.CENTER, panel1);
				frame.getContentPane().add(BorderLayout.SOUTH, panel2);
				frame.setVisible(true);
			
	}
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
			SpeedTester test = new SpeedTester();
		}
		});
	}
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource() == bttn1){
			String input1 = (String)comboBox.getSelectedItem();
			String input2 = (String)msgComboBox.getSelectedItem();
			String input3 = (String)topologyComboBox.getSelectedItem();
			String input4 = (String)msgSizeBox.getSelectedItem();

			System.out.println(input1 + ", " + input2 + ", " + input3 + ".");
			NetworkSetup(input1, input2, input3, input4);
		}
	}
	public void NetworkSetup(String input1 ,String input2,String input3,String input4){
		Network netTest = new Network();
		int latency = 0;
		String msgType = "";
		int msgSize = 0;
		if(input3 == "4G"){
			latency = 37;
		}else if(input3 == "WLAN"){
			latency= 2;
		}
		if(input2 == "MMS"){
			msgType = "MMS";
		}else if(input2 == "SMV"){
			msgType = "SMV";
		}else if(input2 == "GOOSE"){
			msgType ="GOOSE";
		}
		if(input4 == "100"){
			msgSize = 100;
		}else if(input4 == "200"){
			msgSize = 200;
		}else if(input4 == "300"){msgSize = 300;}
		else if (input4 == "500"){msgSize = 500;}

		Random randomno = new Random();
		byte[] nbyte = new byte[msgSize];
		randomno.nextBytes(nbyte);
		String s = new String(nbyte);
		//System.out.println(s);


		if(input1 == "AES"){
			
			float avgTime = netTest.AesExample(nbyte,latency,msgType);
			outputText.setText(String.valueOf(avgTime) + " ms");
		}else if(input1 == "RSA"){
			float avgTime = netTest.RSAexample(nbyte,latency,msgType);
			outputText.setText(String.valueOf(avgTime) + " ms");
		}else if(input1 =="ChaCha20"){
			try{
				float avgTime = netTest.ChaChaExample(nbyte,latency,msgType);
			outputText.setText(String.valueOf(avgTime) + " ms");
			}catch(Exception e){}
		}

	}
}