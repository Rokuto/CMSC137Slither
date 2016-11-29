import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;

import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;

import javax.swing.JFrame;

import java.util.HashMap;


public class UDPServer extends JFrame {

	private static Tile tiles[][];
	private static int internalBoard[][];

	private final int PORT = 8888;

	private DatagramSocket socket = null;
    private DatagramPacket outPacket = null;
    private byte[] outBuf;

	private static int max = 2;
	private static int players = 0;
	private static HashMap<String, Integer> playerAdd;
	private static Snake snakes[] = new Snake[max];

	private static int dim = 25;
	public UDPServer(){
		super("Slitherin");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new GridLayout(dim, dim));

		this.internalBoard = new int[dim][dim];
		this.tiles = new Tile[dim][dim];
		this.playerAdd = new HashMap<String, Integer>();

		for(int i = 0; i < dim; i++){
			for(int j = 0; j < dim; j++){
				tiles[i][j] = new Tile();
				this.add(tiles[i][j]);
			}
		}

		new SpawnPower(tiles).start();

		try {
    		socket = new DatagramSocket();
 		}catch(IOException ioe){
	    	// System.out.println(ioe);
	    }

		try{
			new ReceiveServer(this, tiles, internalBoard, max, players, playerAdd, snakes).start();
		}catch(IOException e){

		}

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void sendData(String msg){
		outBuf = msg.getBytes();

    	try {
		    //Send to multicast IP address and port
	  	  	InetAddress address = InetAddress.getByName("224.2.2.3");
	    	outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);

    		socket.send(outPacket);

	    }catch(IOException ioe){
	    	// System.out.println(ioe);
	    }
	}
}
