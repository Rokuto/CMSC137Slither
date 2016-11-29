import java.io.IOException;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.MulticastSocket;


public class ReceiveClient extends Thread {
	
	private String playerNo;
	private static Tile tiles[][];
	private static int internalBoard[][];

	private MulticastSocket socket = null;
    private DatagramPacket inPacket = null;
    private byte[] inBuf = new byte[256];

    private static int dim = 9;

	public ReceiveClient(Tile tiles[][], int internalBoard[][], String playerNo) throws IOException {
		this.tiles = tiles;
		this.playerNo = playerNo;
		this.internalBoard = internalBoard;
	}

	public void run(){
		try {
	      	//Prepare to join multicast group
	      	socket = new MulticastSocket(8888);
	      	InetAddress address = InetAddress.getByName("224.2.2.3");
	  	    socket.joinGroup(address);
	 
	      	while (true) {
	        	inPacket = new DatagramPacket(inBuf, inBuf.length);
	        	socket.receive(inPacket);
	        	String msg = new String(inBuf, 0, inPacket.getLength());
	        	// System.out.println("From " + inPacket.getAddress() + ":" + inPacket.getPort() + " Msg : " + msg);

	  			// System.out.println(msg);  
	  			// msg.split(' ');
	  			// System.out.println(msg.trim().split(" ").length);
	  			
	  			String  token[] = msg.trim().split(" ");
	  			if(token.length != 82){
	  				System.out.println(token.length);
	  				continue;
	  			}
	  			int k = 0;
	  			if(token[k].trim().equals(playerNo)){
	  				k++;
		  			for(int i = 0; i < dim; i++){
		  				for(int j = 0; j < dim; j++){
		  					if(internalBoard[i][j] == Integer.parseInt(token[k])){
		  			 			k++;
		  						continue;
		  					}
		  			 		internalBoard[i][j] = Integer.parseInt(token[k]);
		  			 		k++;
		  			 		// System.out.print(internalBoard[i][j] + " ");
		  			 		
		  			 		if(internalBoard[i][j] == 8){
								tiles[i][j].placeBody();
		  			 		}else if(internalBoard[i][j] == 0){
								tiles[i][j].placeBlank();
		  			 		}else{
								tiles[i][j].changehead(internalBoard[i][j]);
		  			 		}
		  				}
		  				// System.out.println("");
		  			} 	
	  			}
	    	}
	    }catch(IOException ioe){
	    	System.out.println(ioe);
	    }
	}
}