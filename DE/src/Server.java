//for stopwatch
//for stopwatch
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;

//for removing trailing zeros
import java.math.*;

//for printing dateTime
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Basic Math server
import java.net.*; 
public class Server { // extracts sender IP address and port number from received packet

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception 
	{
		// TODO Auto-generated method stub
		 DatagramSocket serverSocket = new DatagramSocket(9787); // server socket
		  
	     byte[] receiveData = new byte[1024];
	     byte[] sendData;
	     byte[] sendData2;
		//initialize stopwatches for clients
		Instant instantStarted1 = Instant.now();
		Instant instantStarted2 = Instant.now();
		Instant instantStarted3 = Instant.now();

		//Storing name of clients
		String client1 = ""; //storing the name of 1st client
		String client2 = ""; //storing the name of 2nd client
		String client3 = ""; //storing the name of 3rd client

		//stores the client we are currently on
		String currentClient = "";

		//key to identify the client
		char clientKey = ' ';
	  
	      while(true) {
			  // packet which holds info regarding received data
			  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			  serverSocket.receive(receivePacket);

			  String sentence = new String(receivePacket.getData());

			  InetAddress IPAddress = receivePacket.getAddress();

			  int port = receivePacket.getPort();


			  //if the sentence contains a letter, it's a name
			  if (Character.isLetter(sentence.charAt(1))) {

				  //Register the new client
				  if(!sentence.contains("terminated.")) {

					  //extract the identifier
					  clientKey = sentence.charAt(0);
					  //remove identifier from client name
					  sentence = sentence.substring(1);

					  //use loop to see when we reach non-zero
					  for(int i = sentence.length() - 1; i >= 0; i--) {
						  //if we encounter a letter, remove trailing zeros
						  if(Character.isLetter(sentence.charAt(i))) {
							  sentence = sentence.substring(0, i + 1); //trim zeros
							  break; //break loop after trimming zeros
						  }
					  }

					  //register first client
					  if(clientKey == 'a') {
						  client1 = sentence; //set the first client
						  currentClient = client1;
						  instantStarted1 = Instant.now(); //start the stopwatch for client 1
					  }
					  //register second client
					  if(clientKey == 'b') {
						  client2 = sentence; //set the second client
						  currentClient = client2;
						  instantStarted2 = Instant.now(); //start the stopwatch for client 2
					  }
					  //register third client
					  if(clientKey == 'c') {
						  client3 = sentence; //set the third client
						  currentClient = client3;
						  instantStarted3 = Instant.now(); //start the stopwatch for client 3
					  }

					  System.out.println("");

					  //confirm connection established with new user
					  System.out.println("A new connection has been established.");
					  System.out.println("Connection Details...");
					  System.out.println("Client Name: " + currentClient); //client name

					  // creating a new object of the class Date
					  java.util.Date date = new java.util.Date();
					  System.out.println("Connected on: " + date); //when connection is established
					  System.out.println("");
					  
					  String Connection = "You are connected to the server.";
					  int len_C = Connection.length();
					  sendData2 = new byte[len_C]; 
					  sendData2= Connection.getBytes();
					  DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, IPAddress, port);

					  serverSocket.send(sendPacket2); // send data back to client
				  }
				  //if the connection with client has been closed.
				  else if(sentence.contains("terminated.")) {

					  System.out.println("");

					  //extract the identifier
					  clientKey = sentence.charAt(0);
					  //remove identifier from client name
					  sentence = sentence.substring(1);

					  // creating a new object of the class Date
					  java.util.Date date1 = new java.util.Date();

					  //if client1 connection is closed
					  if(clientKey == 'a') {
						  //stop the stopwatch
						  Instant instantStopped = Instant.now();
						  //calculate elapsed time
						  Duration durationBetween = Duration.between(instantStarted1, instantStopped);

						  System.out.println("Connection with " + client1 + " has been terminated.");
						  System.out.println("Disconnected on: " + date1);
						  System.out.println("Elapsed time in milliseconds: " + durationBetween.toMillis());
						  System.out.println("Elapsed time in seconds: " + durationBetween.toSeconds());
						  System.out.println("Elapsed time in minutes: " + durationBetween.toMinutes());


					  }
					  //if client2 connection is closed
					  else if(clientKey == 'b') {
						  //stop the stopwatch
						  Instant instantStopped = Instant.now();
						  //calculate elapsed time
						  Duration durationBetween = Duration.between(instantStarted2, instantStopped);

						  System.out.println("Connection with " + client2 + " has been terminated.");
						  System.out.println("Disconnected on: " + date1);
						  System.out.println("Elapsed time in milliseconds: " + durationBetween.toMillis());
						  System.out.println("Elapsed time in seconds: " + durationBetween.toSeconds());
						  System.out.println("Elapsed time in minutes: " + durationBetween.toMinutes());
					  }
					  //if client1 connection is closed
					  else if(clientKey == 'c') {
						  //stop the stopwatch
						  Instant instantStopped = Instant.now();
						  //calculate elapsed time
						  Duration durationBetween = Duration.between(instantStarted3, instantStopped);

						  System.out.println("Connection with " + client3 + " has been terminated.");
						  System.out.println("Disconnected on: " + date1);
						  System.out.println("Elapsed time in milliseconds: " + durationBetween.toMillis());
						  System.out.println("Elapsed time in seconds: " + durationBetween.toSeconds());
						  System.out.println("Elapsed time in minutes: " + durationBetween.toMinutes());

					  }
				  }
			  }
			  else {

				  //extract the identifier
				  clientKey = sentence.charAt(0);
				  //System.out.println(clientKey);

				  //check which client sent the request
				  if(clientKey == 'a')
					  currentClient = client1;
				  else if(clientKey == 'b')
					  currentClient = client2;
				  else if(clientKey == 'c')
					  currentClient = client3;

				  //remove identifier from string
				  sentence = sentence.substring(1);

				  //trim zeros
				  sentence = sentence.substring(0, 7);

				  //Display who sent what request
				  System.out.println(currentClient + " has requested equation: " + sentence);

				  //String capitalizedSentence = sentence.toUpperCase();

				  // String capitalizedSentence = add(sentence);

				  String capitalizedSentence;

				  // checking pattern and selecting function:
				  if (sentence.contains("+")) // sum pattern and directed to sum function
				  {
					  capitalizedSentence = add(sentence);
					  //System.out.println("hello");
				  } else if (sentence.contains("-")) // subtraction pattern and directed to sub function
				  {
					  capitalizedSentence = sub(sentence);
					  // System.out.println("hello sub");
				  } else if (sentence.contains("*")) // Multiplication pattern and directed to mul function
				  {
					  capitalizedSentence = mul(sentence);
					  //System.out.println("hello mul");
				  } else  // Division pattern is left and directed to div function
				  {
					  capitalizedSentence = div(sentence);
					  // System.out.println("hello last");
				  }

				  int length_send = capitalizedSentence.length();
				  sendData = new byte[length_send];
				  sendData = capitalizedSentence.getBytes();
				  DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

				  serverSocket.send(sendPacket); // send data back to client
			  }
		  }
		
		
		
	}
	

	// Addition function
	public static String add (String exp)
	 {
		 String check = "";
		 int sum = 0;
		 String temp;
		 int a,b,c,d; // if number is 01 + 10 so a = 0*10,b =1,c=1*10,d=0
		 for (int i = 0; i < exp.length(); i++) 
		 {
	       char ch = exp.charAt(i);
	 
	       // if current character is a digit
	       if (Character.isDigit(ch))
	           check = check + ch;
	       
		 }
		 
		 // to sum the values in string
		   // for(int j = 0; j < check.length() ; j++)
		    //{
		    	
	           // if( Character.isDigit(check.charAt(j)) )
	            ////{
	            	a = Character.getNumericValue(check.charAt(0))*10;
	            	b = Character.getNumericValue(check.charAt(1));
	            	c = Character.getNumericValue(check.charAt(2))*10;
	            	d = Character.getNumericValue(check.charAt(3));
	            	sum = sum + a + b + c + d;
	              //  sum = sum + Character.getNumericValue(check.charAt(j));
	         //   }
	       // }
		    
		    
		// sum = Integer.parseInt(check);
		 //temp = Integer.toString(sum);
		 temp = Integer.toString(sum);
		 return temp;
	 }

	// Subtraction function
	 public static String sub (String exp)
	 {
		 String check = "";
		 int sub = 0;
		 String temp;
		 int a,b,c,d; // if number is 01 + 10 so a = 0*10,b =1,c=1*10,d=0
		 for (int i = 0; i < exp.length(); i++) 
		 {
	       char ch = exp.charAt(i);
	 
	       // if current character is a digit
	       if (Character.isDigit(ch))
	           check = check + ch;
	       
		 }
		 
		a = Character.getNumericValue(check.charAt(0))*10;
     	b = Character.getNumericValue(check.charAt(1));
     	c = Character.getNumericValue(check.charAt(2))*10;
     	d = Character.getNumericValue(check.charAt(3));
     	int k = a + b ; // holds first two digits
     	int j = c + d; // holds last two digits
     	sub = k - j; // getting the subtraction result
		 
	
		 temp = Integer.toString(sub);
		 return temp;
	 }
	 
	 // Multiply function
	public static String mul(String exp) 
	 {
		 String check = "";
		 int multiply = 0;
		 String temp;
		 int a,b,c,d; // if number is 01 + 10 so a = 0*10,b =1,c=1*10,d=0
		 for (int i = 0; i < exp.length(); i++) 
		 {
	       char ch = exp.charAt(i);
	 
	       // if current character is a digit
	       if (Character.isDigit(ch))
	           check = check + ch;
	       
		 }
		 
		a = Character.getNumericValue(check.charAt(0))*10;
    	b = Character.getNumericValue(check.charAt(1));
    	c = Character.getNumericValue(check.charAt(2))*10;
    	d = Character.getNumericValue(check.charAt(3));
     	int k = a + b ; // holds first two digits
     	int j = c + d; // holds last two digits
     	multiply = k * j ; // getting the subtraction result
    	
	    temp = Integer.toString(multiply);
		return temp;
	  }
	 
	 
	 
	 // Divide function
	 public static String div(String exp) 
	 {
		 String check = "";
		 double divide = 0;
		 String temp;
		 int a,b,c,d; // if number is 01 + 10 so a = 0*10,b =1,c=1*10,d=0
		 for (int i = 0; i < exp.length(); i++) 
		 {
	       char ch = exp.charAt(i);
	 
	       // if current character is a digit
	       if (Character.isDigit(ch))
	           check = check + ch;
	       
		 }
		 
		a = Character.getNumericValue(check.charAt(0))*10;
    	b = Character.getNumericValue(check.charAt(1));
    	c = Character.getNumericValue(check.charAt(2))*10;
    	d = Character.getNumericValue(check.charAt(3));
     	double k = a + b ; // holds first two digits
     	double j = c + d; // holds last two digits
     	divide = k / j ; // getting the subtraction result
    	
     	temp = String.valueOf(divide); // for double to string conversion
	    //temp = Integer.toString(divide);
		return temp;
	}
}


