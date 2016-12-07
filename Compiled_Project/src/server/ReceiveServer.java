import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

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
	public static int players;
	private static HashMap<String, Integer> pAdd;
	public static Snake snakes[];

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
	        	
	        	if(pAdd.get(key) == null){ // newly connected person
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


	        		try{
		        		BufferedReader reader = new BufferedReader(new FileReader("img/menu/highscores.txt"));
						String line = reader.readLine();
						String[][] highscores = new String[3][2];
						
						while (line != null) {
							for(int i = 0; i < 3; i++){
								String[] word = line.split("\\s+");
								for(int j = 0; j < 2; j++){
									System.out.println(word[j]);
									highscores[i][j] = word[j];
								}
								//highscores[i] = word;
								// lbl_name[i].setText(word[0]);
								// lbl_score[i].setText(word[1]);
								line = reader.readLine();
							}
						}
					}catch(Exception e){
						e.printStackTrace();
					}


	        		server.sendData("" + players); // define the specific player number
	        		snakes[players] = new Snake(server, tiles, internalBoard, players); // create the snake of the player
	        		snakes[players].start(); // start the snake

	        		players++; // increase number of players

	        		expandPlayerSize(); // increase player array
	        	}
	    	}
	    }catch(IOException ioe){
	    	// System.out.println(ioe);
	    }
	}

	private void expandPlayerSize(){
		if(players > max-1){

			max++; // increase max allowable snakes
			Snake temp[] = new Snake[max]; // create a new array

			/* Migration of previous data */
			for(int i = 0; i < max-1; i++){
				temp[i] = snakes[i];
			}

			this.snakes = temp; // use the new expanded version 
		}
	}
}
