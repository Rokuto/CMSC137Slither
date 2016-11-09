import java.net.*;
import java.io.*;
import java.util.*;

public class GreetingClient{
   public static void main(String [] args){
	  try{
	  	boolean connection = true;
		String serverName = args[0]; //get IP address of server from first param
		int port = Integer.parseInt(args[1]); //get port from second param
		String message = ""; // args[3] get message from the third param
		String name = args[2]; // get name of sender

		/* Message */

		/* Open a ClientSocket and connect to ServerSocket */
		System.out.println("Connecting to " + serverName + " on port " + port);
		//insert missing line here for creating a new socket for client and binding it to a port
		Socket client = new Socket(serverName, port);

		/* Receive data from the ServerSocket */
		new stayAlive(client, connection).start(); // new thread for listening to the server 
		new sendAlive(client, true, name).start(); // new thread for sending data to the server

	  }catch(IOException e){
		//e.printStackTrace();
		System.out.println("Cannot find Server");
	  }catch(ArrayIndexOutOfBoundsException e){
		 System.out.println("Usage: java GreetingClient <server ip> <port no.> '<your name>'");
	  }
   }
}