import java.net.Socket;
import java.net.SocketTimeoutException;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;


public class keepAlive extends Thread{
	private static int max = 2;
	private static Socket[] sockets = new Socket[max];
	private static int total = 0;
	private int local;
	private boolean connected = true;
   
	public keepAlive(Socket socket, boolean connected) throws IOException{

		this.sockets[total] = socket; // binding a port to a socket
		this.local = total; // keep track of the current position in the array
		this.connected = connected;	// connection status
		
		total++; // increment "total" number of players
		
		if(total > max-1){
			System.out.println(max);
			max++;
			Socket[] newSocket = new Socket[max];
			for(int i = 0; i < max-1; i++){	
				newSocket[i] = sockets[i];
			}

			this.sockets = newSocket;
		}
	}

	public void sendAll(String message){
		/* This function broadcast all the messages that it receives from a client to all the clients */
		for (int i = 0; i < total; i++) {
			try{
   			DataOutputStream out = new DataOutputStream(sockets[i].getOutputStream());
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
	            
	        	System.out.println("Just connected to " + sockets[local].getRemoteSocketAddress());
	            
	            /* Read data from the ClientSocket */
	            DataInputStream in = new DataInputStream(sockets[local].getInputStream());
	            
	            String message = in.readUTF(); // store the message
	         
	            sendAll(message); // Broadcast message 

	        }catch(SocketTimeoutException s){
	            System.out.println("Socket timed out!");
	            break;
	        }catch(IOException e){
	            //e.printStackTrace();
	            System.out.println("Usage: java Server <port no.>");
	            break;
	        }
    	} 
	}
   
}
