import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Chatbox extends JPanel{
	
	private SidePanel chat;
	private JTextArea text;
	private JButton send;

	public Chatbox(SidePanel chat){
		this.chat = chat;

		/* Panel Properties */
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(70, 70));
		setBackground(Color.BLACK);
		
		/* Chatbox */
		text = new JTextArea(); // initialization
		text.setLineWrap(true); // Line Wrap or fixed width
		add( new JScrollPane( text ), BorderLayout.CENTER ); // add chatbox to panel
		
		/* Send button */
		send = new JButton("Send"); // initialization
		send.addActionListener( new SendActionListener(chat) ); // action of the button
		add( send, BorderLayout.EAST ); // add to panel
	}

	public String getChatText(){
		return text.getText().trim();
	}

	public void clearText(){
		text.setText("");
	}
}