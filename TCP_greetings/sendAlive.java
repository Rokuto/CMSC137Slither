   // File Name GreetingServer.java

import java.net.*;
import java.io.*;
import java.util.*;

public class sendAlive extends Thread{
   	private boolean connected = true;
   	private Socket client;
   	private String message = "";
   	private String name;

   	public sendAlive(Socket client, boolean connected, String name) throws IOException{
   		this.connected = connected; 
   		this.client = client; // status of the connection
   		this.name = name; // name of client
   	}

   	public void run(){
   		boolean index = true; // flag to remove redundant prints
    	while(connected){
	        try{
	        	Scanner scan = new Scanner(System.in); // instance of scanner

				if(index){
					System.out.println("Just connected to " + client.getRemoteSocketAddress());
					System.out.print("Enter Message:");
					index = !index; // switch flag off
				}
				message = scan.nextLine(); // user's message input
				
				/* Send data to the ServerSocket */
				OutputStream outToServer = client.getOutputStream();
				DataOutputStream out = new DataOutputStream(outToServer);
				out.writeUTF("Client " + name + " says: " + message); // sends data to the server

				/* Close connection String */
				if(message.trim().equals("RoKuTo")){
					client.close();
					connected = false;
				}

	        }catch(SocketTimeoutException s){
	            //System.out.println("Socket timed out!");
	            break;
	        }catch(IOException e){
	            //e.printStackTrace();
	            //System.out.println("Usage: java GreetingServer <port no.>");
	            break;
	        }
    	} 
	}
   
}
