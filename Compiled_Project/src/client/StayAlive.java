import java.net.Socket;
import java.net.SocketTimeoutException;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class StayAlive extends Thread{
   	private boolean connected;
   	private Socket client;
   	private SidePanel sidePanel;

   	public StayAlive(Socket client, SidePanel sidePanel) throws IOException{
   		this.sidePanel = sidePanel;
   		this.client = client;

   		connected = true;
   	}

   	public void run(){
    	while(connected){
	        try{
	        	/* Receives data */
	        	InputStream inFromServer = client.getInputStream();
				DataInputStream in = new DataInputStream(inFromServer);

				/* Show the message from the server */
				this.sidePanel.appendHistory(in.readUTF());
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
