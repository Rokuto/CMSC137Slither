   // File Name GreetingServer.java

import java.net.*;
import java.io.*;

public class stayAlive extends Thread{
   	private boolean connected = true;
   	private Socket client;

   	public stayAlive(Socket client, boolean connected) throws IOException{
   		this.connected = connected;
   		this.client = client;
   	}

   	public void run(){
    	while(connected){
	        try{
	        	/* Receives data */
	        	InputStream inFromServer = client.getInputStream();
				DataInputStream in = new DataInputStream(inFromServer);

				/* Show the message from the server */
				System.out.println(in.readUTF());	
				System.out.print("Enter Message: ");
	        }catch(SocketTimeoutException s){
	            System.out.println("Socket timed out!");
	            break;
	        }catch(IOException e){
	            //e.printStackTrace();
	            System.out.println("Usage: java GreetingServer <port no.>");
	            break;
	        }
    	} 
	}
   
}
/**
a) Socket server = serverSocket.accept();
b) serverSocket = new ServerSocket(port);
**/