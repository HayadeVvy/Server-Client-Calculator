// works for two digit numbers
// requires spacing between sign and number
// Only positive numbers and 0 as inputs are acceptable
import java.io.*; 
import java.net.*; 
public class Client3 { // attach IP address and port number

	public static void main(String[] args) throws Exception 
	{
		// regex pattern
		 String regex = "[0-9][0-9]+(\\s*[\\/*+\\-]\\s*[0-9][0-9])";
		
		// User input
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 

		// Client Socket
		DatagramSocket clientSocket = new DatagramSocket(); 
		
		//Finds IP address
	    InetAddress IPAddress = InetAddress.getByName("127.0.0.1"); 
		  
	     // Used for DatagramPacket
		 byte[] sendData;
		 byte[] receiveData = new byte[3];
		 byte[] receiveData2 = new byte[32];

		 //Get User's name
		 System.out.print("Please enter your name: ");
		 String clientName = inFromUser.readLine();

		 //attach client key to name
		clientName = "c" + clientName;

		 sendData = clientName.getBytes();
		// Details of packet being sent from client to server.
		DatagramPacket sendPacketCName = new DatagramPacket(sendData, sendData.length, IPAddress, 9787);
		// Sending packet from client to server.
		clientSocket.send(sendPacketCName);

		DatagramPacket receivePacket2 = new DatagramPacket(receiveData2, receiveData2.length);

		 clientSocket.receive(receivePacket2); // receive packet

		 String modifiedSentence2 = new String(receivePacket2.getData());
		 
		 System.out.println(modifiedSentence2);
		
		
		// Asking for prompt from user.
		 System.out.print("Please enter your equation: ");
		 
		 // Read input
		 String sentence = inFromUser.readLine(); 
		 while (!(sentence.contains("Quit")))
		 {
			 if (sentence.matches(regex))
			 {

				 //Attach Letter to beginning of string to identify client 1
				 sentence = "c" + sentence; //Client 1 = a

				 int length_send = sentence.length();
				 sendData = new byte[length_send];
			 sendData = sentence.getBytes();         
			      
			 // Details of packet being sent from client to server.
			 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9787);

				 // Sending packet from client to server.
				 clientSocket.send(sendPacket);

				 //after sending equation, remove identifier
				 sentence = sentence.substring(1);

				 // Receive data from server
				 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

				 clientSocket.receive(receivePacket); // receive packet

				 String modifiedSentence = new String(receivePacket.getData());

				 String check = "";
				 int k;
				 int j;
				 int a,b,c,d;

				 if (sentence.matches("[0][0-9]+(\\s*[-]\\s*[0-9][0-9])"))
				 {
					 for (int i = 0; i < sentence.length(); i++)
					 {
						 char ch = sentence.charAt(i);

						 // if current character is a digit
						 if (Character.isDigit(ch))
							 check = check + ch;

					 }

					 a = Character.getNumericValue(check.charAt(0))*10;
					 b = Character.getNumericValue(check.charAt(1));
					 c = Character.getNumericValue(check.charAt(2))*10;
					 d = Character.getNumericValue(check.charAt(3));
					 k = a + b;
					 j = c + d;
					 if (k > j)
					 {

						 System.out.println("FROM SERVER_SUB: " + modifiedSentence.charAt(0));
						 // Asking for prompt from user.
						 System.out.print("Please enter next equation: ");

						 // Read input
						 sentence = inFromUser.readLine();
					 }
					 else
					 {

						 System.out.println("FROM SERVER: " + modifiedSentence);
						 // Asking for prompt from user.
						 System.out.print("Please enter next equation: ");

						 // Read input
						 sentence = inFromUser.readLine();

					 }

				 }
				 else if (sentence.matches("[0][0-9] +(\\s*[+]\\s*[0][0-9])"))
				 {
					 for (int i = 0; i < sentence.length(); i++)
					 {
						 char ch = sentence.charAt(i);

						 // if current character is a digit
						 if (Character.isDigit(ch))
							 check = check + ch;

					 }

					 a = Character.getNumericValue(check.charAt(0))*10;
					 b = Character.getNumericValue(check.charAt(1));
					 c = Character.getNumericValue(check.charAt(2))*10;
					 d = Character.getNumericValue(check.charAt(3));
					 k = a + b;
					 j = c + d;
					 if (k + j >= 10)
					 {

						 System.out.println("FROM SERVER: " + modifiedSentence.charAt(0)+modifiedSentence.charAt(1));
						 // Asking for prompt from user.
						 System.out.print("Please enter next equation: ");

						 // Read input
						 sentence = inFromUser.readLine();
					 }
					 else if (k+j < 10)
					 {

						 System.out.println("FROM SERVER: " + modifiedSentence.charAt(0));
						 // Asking for prompt from user.
						 System.out.print("Please enter next equation: ");

						 // Read input
						 sentence = inFromUser.readLine();
					 }
					 else
					 {

						 System.out.println("FROM SERVER: " + modifiedSentence);
						 // Asking for prompt from user.
						 System.out.print("Please enter next equation: ");

						 // Read input
						 sentence = inFromUser.readLine();

					 }
				 }
				 else if (sentence.matches("[0][0-9] +(\\s*[*]\\s*[0][0-9])"))
				 {
					 for (int i = 0; i < sentence.length(); i++)
					 {
						 char ch = sentence.charAt(i);

						 // if current character is a digit
						 if (Character.isDigit(ch))
							 check = check + ch;

					 }

					 a = Character.getNumericValue(check.charAt(0))*10;
					 b = Character.getNumericValue(check.charAt(1));
					 c = Character.getNumericValue(check.charAt(2))*10;
					 d = Character.getNumericValue(check.charAt(3));
					 k = a + b;
					 j = c + d;
					 if (k * j >= 10)
					 {

						 System.out.println("FROM SERVER: " + modifiedSentence.charAt(0)+modifiedSentence.charAt(1));
						 // Asking for prompt from user.
						 System.out.print("Please enter next equation: ");

						 // Read input
						 sentence = inFromUser.readLine();
					 }
					 else if (k*j < 10)
					 {

						 System.out.println("FROM SERVER: " + modifiedSentence.charAt(0));
						 // Asking for prompt from user.
						 System.out.print("Please enter next equation: ");

						 // Read input
						 sentence = inFromUser.readLine();
					 }
					 else
					 {

						 System.out.println("FROM SERVER: " + modifiedSentence);
						 // Asking for prompt from user.
						 System.out.print("Please enter next equation: ");

						 // Read input
						 sentence = inFromUser.readLine();

					 }
				 }
				 else
				 {
					 // Display data
					 System.out.println("FROM SERVER: " + modifiedSentence);

					 // Asking for prompt from user.
					 System.out.print("Please enter next equation: ");

					 // Read input
					 sentence = inFromUser.readLine();

				 }
			 }
			 else
			 {
				 while(!(sentence.matches(regex)))
				 {
					 System.out.print ("Please reenter the equation: ");

					 sentence = inFromUser.readLine();

					 if ((sentence.contains("Quit")))
						 System.exit(1);

				 }
				 System.out.println("Now send packet below...");
				 sendData = sentence.getBytes();

				 // Details of packet being sent from client to server.
				 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9776);

				 // Sending packet from client to server.
				 clientSocket.send(sendPacket);


				 // Receive data from server
				 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

				 clientSocket.receive(receivePacket); // receive packet
			 }
		 }

		 //if user enters quit, send message to server
		 if(sentence.contains("Quit")) {
			 //connection terminated client name to server
			 sentence = "Connection with " + clientName + " has been terminated.";
			 System.out.println("Connection terminated.");

			 sentence = "a" + sentence;
			 sendData = sentence.getBytes();
			 // Details of packet being sent from client to server.
			 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9776);
			 // Sending packet from client to server.
			 clientSocket.send(sendPacket);
		 }

		 clientSocket.close();  // closing socket
	
		
	}

}
