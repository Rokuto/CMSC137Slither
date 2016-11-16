import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class sendActionListener implements ActionListener {
	
	private SidePanel chat;

	public sendActionListener(SidePanel chat){
		this.chat = chat;
	}

	public void actionPerformed(ActionEvent e) {
		/* Add chat to chat history */
		chat.appendHistory("Ha");
    }
}