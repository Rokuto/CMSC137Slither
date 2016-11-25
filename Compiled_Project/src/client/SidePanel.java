import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.io.IOException;

import java.net.Socket;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class SidePanel extends JPanel{

	private GameFrame game;
	private JTextArea text;
	private Chatbox chatbox;

	private Socket client;
	private String name;

	public SidePanel(GameFrame game, String name, String ip){
		this.game = game;
		this.name = name;

		/* TCP */
		try{
			this.client = new Socket(ip, 1024); // create a socket
			new StayAlive(client, this).start(); // new thread for listening to the server 
		}catch(IOException e){
			//e.printStackTrace();
			System.out.println("Cannot find Server");
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Usage: java GreetingClient <server ip> <port no.> '<your name>'");
		}

		/* Panel Properties */		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(300, BoardPanel.ROW_COUNT * BoardPanel.TILE_SIZE));
		setBackground(Color.BLACK);

		/* Chat history */
		text = new JTextArea(5, 20); // initialization with default dimension
		text.setEditable(false); // read-only
		text.setLineWrap(true); // Line Wrap or fixed width
		add( new JScrollPane( text ), BorderLayout.CENTER ); // add to panel

		/* Chat box */
		chatbox = new Chatbox(this); // initailization
		add( chatbox , BorderLayout.SOUTH ); // add to panel

		setFocusable(true);
	}

	public String getName(){
		return this.name;
	}

	public String getTextToSend(){
		return this.chatbox.getChatText();
	}

	public Socket getClient(){
		return this.client;
	}

	public void appendHistory(String hist){
		this.text.append("\n" + hist);
	}

	public void clearChat(){
		this.chatbox.clearText();
	}

}