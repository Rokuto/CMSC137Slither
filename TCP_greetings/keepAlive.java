import java.net.*;
import java.io.*;

public class keepAlive extends Thread{
   private static Socket[] socket = new Socket[10];
   private static String[] clients = new String[10];
   private static int total = 0;
   private int local;
   private boolean connected = true;
   
	public keepAlive(Socket socket, boolean connected) throws IOException{
		this.socket[total] = socket; // binding a port to a socket
		this.local = total; // keep track of the current position in the array
		this.connected = connected;	// connection status
		total++; // increment "total" number of players
	}

	public void sendAll(String message){
		/* This function broadcast all the messages that it receives from a client to all the clients */
		for (int i = 0; i < total; i++) {
			try{
   			DataOutputStream out = new DataOutputStream(socket[i].getOutputStream());
            //System.out.println("Server:" + server.getOutputStream());
            out.writeUTF(message);	
			}catch (IOException e) {
				//e.printStackTrace();	
			}
		}
	}

	public void run(){
    	while(connected){
	        try{
	            
	        	System.out.println("Just connected to " + socket[local].getRemoteSocketAddress());
	            
	            /* Read data from the ClientSocket */
	            DataInputStream in = new DataInputStream(socket[local].getInputStream());
	            
	            String message = in.readUTF(); // store the message
	         
	            sendAll(message); // Broadcast message 

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
