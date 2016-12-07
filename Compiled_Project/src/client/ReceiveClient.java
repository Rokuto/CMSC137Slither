import java.io.IOException;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.MulticastSocket;


public class ReceiveClient extends Thread {

	private GameFrame game;

	private String playerNo;
	private static Tile tiles[][];
	private static int internalBoard[][];

	private MulticastSocket socket = null;
    private DatagramPacket inPacket = null;
    private byte[] inBuf = new byte[256];

    private static int dim = 9;

	public ReceiveClient(Tile tiles[][], int internalBoard[][], String playerNo, GameFrame game) throws IOException {
		this.game = game;
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


	  	    int magic = 0;
	      	while (true) {
	        	inPacket = new DatagramPacket(inBuf, inBuf.length);
	        	socket.receive(inPacket);
	        	String msg = new String(inBuf, 0, inPacket.getLength());

	  			String  token[] = msg.trim().split(" ");
	  			if(token.length != 82){
	  				/* Wrong message length */
	  				// System.out.println(token.length);
	  				continue;
	  			}
	  			int k = 0;
	  			/* correct board for the correct player */
	  			if(token[k].trim().equals(playerNo)){
	  				k++;
	  				/* Build the board */
		  			for(int i = 0; i < dim; i++){
		  				for(int j = 0; j < dim; j++){
		  					if(internalBoard[i][j] == Integer.parseInt(token[k])){
		  			 			k++;
		  						continue;
		  					}
		  					/* Build the internal logic */
		  			 		internalBoard[i][j] = Integer.parseInt(token[k]);
		  			 		k++;

		  					/* Build GUI */
		  			 		if(internalBoard[i][j] == 8){
								tiles[i][j].placeBody();
		  			 		}else if(internalBoard[i][j] == 0){
								tiles[i][j].placeBlank();
							} else if(internalBoard[i][j] == 5){
								tiles[i][j].placeSpeed();
							} else if(internalBoard[i][j] == 6){
								tiles[i][j].placeSlow();
							} else if(internalBoard[i][j] == 7){
								tiles[i][j].placePoison();
							} else{
								tiles[i][j].changehead(internalBoard[i][j]);
		  			 		}
		  				}
		  			}

		  			if(magic == 0){
		  				magic++;
		  				continue;
		  			}
		  			/* Check if Game Over */
		  			if( (internalBoard[4][4] < 1 || internalBoard[4][4] > 4) && magic > 0 && internalBoard[4][4] != 8 ){
		  				// System.out.println();
		  				// for(int i = 0; i < 9; i++){
		  				// 	for(int j = 0; j < 9; j++){
		  				// 		System.out.print(internalBoard[i][j] + " ");
		  				// 	}
		  				// 	System.out.println();
		  				// }
		  				// System.out.println();
		  				if(magic == 1){
		  					game.gameOver();
		  					magic++;
		  				}
		  			}
	  			}
	    	}
	    }catch(IOException ioe){
	    	System.out.println(ioe);
	    }
	}
}
