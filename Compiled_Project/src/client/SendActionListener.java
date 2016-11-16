import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.net.SocketTimeoutException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;

public class SendActionListener implements ActionListener {
	
	private SidePanel chat;

	public SendActionListener(SidePanel chat){
		this.chat = chat;
	}

	public void actionPerformed(ActionEvent e) {
		try{
			/* Send data to the ServerSocket */
			OutputStream outToServer = chat.getClient().getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);
			out.writeUTF( "Client " + chat.getName() + " says: " + chat.getTextToSend() ); // sends data to the server
    		
    		chat.clearChat();
    	}catch(SocketTimeoutException s){
            System.out.println("Socket timed out!");
        }catch(IOException err){
            //e.printStackTrace();
            System.out.println("Usage: java GreetingServer <port no.>");
        }
    }
}