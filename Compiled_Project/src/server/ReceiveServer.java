import java.io.IOException;

import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import java.util.HashMap;

public class ReceiveServer extends Thread {
	
	private static Tile tiles[][];
	private static int internalBoard[][];

	private MulticastSocket socket = null;
    private DatagramPacket inPacket = null;
    private byte[] inBuf = new byte[256];

    private static int max;
	private static int players;
	private static HashMap<String, Integer> pAdd;
	private static Snake snakes[];

	private UDPServer server;

	public ReceiveServer(UDPServer server, Tile tiles[][], int internalBoard[][], int max, int players, HashMap<String, Integer> pAdd, Snake snakes[]) throws IOException {
		this.max = max;
		this.players = players;
		this.pAdd = pAdd;
		this.snakes = snakes;
		this.internalBoard = internalBoard;
		this.tiles = tiles;
		this.server = server;
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
	        	String key = inPacket.getAddress()+""+inPacket.getPort();
	        	if(pAdd.get(key) == null){
	        		// System.out.println("NULL");
	        		pAdd.put(key, players);
	        	}else{
	        		// System.out.println(pAdd.get(key));
	        		if(msg.trim().equals("1")){
	        			snakes[pAdd.get(key)].changeDirection(1);

		        	}
		        	if(msg.trim().equals("2")){
	        			snakes[pAdd.get(key)].changeDirection(2);
		        	}
	        	}


	        	if(msg.trim().equals("Init")){
	        		// System.out.println("players" + players);

	        		try{
	        			Thread.sleep(1000);
	        		}catch(InterruptedException err){

	        		}

	        		server.sendData("" + players);
	        		snakes[players] = new Snake(server, tiles, internalBoard, players);
	        		snakes[players].start();
	        		
	        		players++;

	        		expandPlayerSize();
	        	}
	    	}
	    }catch(IOException ioe){
	    	// System.out.println(ioe);
	    }
	}

	private void expandPlayerSize(){
		if(players > max-1){

			max++;
			Snake temp[] = new Snake[max];

			/* Migration of previous data */
			for(int i = 0; i < max-1; i++){	
				temp[i] = snakes[i];
			}
			
			this.snakes = temp;
		}
	}
}