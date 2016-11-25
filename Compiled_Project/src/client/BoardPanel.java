import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.io.IOException;

import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

import javax.swing.JPanel;


public class BoardPanel extends JPanel{

	public static final int COL_COUNT = 9;
	public static final int ROW_COUNT = 9;
	public static final int TILE_SIZE = 25;
  private static int dim = 9;

  private final int PORT = 8888;
  
  private DatagramSocket socket = null;
  private DatagramPacket outPacket = null;
  private byte[] outBuf;

  private MulticastSocket insocket = null;
  private DatagramPacket inPacket = null;
  private byte[] inBuf = new byte[256];
	
  private String playerNo = "";
	private static Tile tiles[][];
  private static int internalBoard[][];
	
	private GameFrame game;

	public BoardPanel(GameFrame game){
		this.game = game;

		this.internalBoard 	= new int[ROW_COUNT][COL_COUNT];
		this.tiles 			= new Tile[ROW_COUNT][COL_COUNT];

		setLayout(new GridLayout(ROW_COUNT, COL_COUNT));
		setPreferredSize(new Dimension(COL_COUNT * TILE_SIZE, ROW_COUNT * TILE_SIZE));
		// setBackground(Color.BLACK);
		
    /* Create the boars */
    for(int i = 0; i < dim; i++){
      for(int j = 0; j < dim; j++){
        tiles[i][j] = new Tile();
        this.add(tiles[i][j]);
      }
    }

    /* Tell server that a new client has joined */
    try { 
      socket = new DatagramSocket();
      initClient();
    }catch(IOException ioe){
      System.out.println(ioe);
    }

    /* Snake movements */
    addKeyListener(new MoveAction(this));

    /* Recieve information */
    try{
      new ReceiveClient(tiles, internalBoard, playerNo).start();
    }catch(IOException e){

    }

    setFocusable(true);
		//requestFocusInWindow();
	}

  private void initClient(){
    String msg;
    msg = "Init ";

    sendData(msg);
    recvData();
  }

  public void recvData(){
    try {
      //Prepare to join multicast group
      insocket = new MulticastSocket(8888);
      InetAddress address = InetAddress.getByName("224.2.2.3");
      insocket.joinGroup(address);
      
      String msg = "";
      /* Waiting for the unique id number from the server */
      do{
        inPacket = new DatagramPacket(inBuf, inBuf.length);
        insocket.receive(inPacket);
        msg = new String(inBuf, 0, inPacket.getLength());
        // System.out.println(msg.length());
      }while(msg.length() != 1);

      playerNo = msg.trim();
      // System.out.println("From " + inPacket.getAddress() + ":" + inPacket.getPort() + " Msg : " + msg);

    }catch(IOException ioe){
      System.out.println(ioe);
    }
  }

  public void sendData(String msg){
    /* This function is use to broadcast a message */

    outBuf = msg.getBytes();

    try {
      //Send to multicast IP address and port
      InetAddress address = InetAddress.getByName("224.2.2.3");
      outPacket = new DatagramPacket(outBuf, outBuf.length, address, PORT);

      socket.send(outPacket);
      // System.out.println(outPacket);     
    }catch(IOException ioe){
      System.out.println(ioe);
    }
    // System.out.println("sends : " + msg);
  }
}