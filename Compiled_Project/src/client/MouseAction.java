import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;

public class MouseAction extends MouseAdapter {
	
	private BoardPanel client; 

	public MouseAction(BoardPanel client){
		this.client = client;
	}

	@Override
	public void mousePressed(MouseEvent me) { 
    	// System.out.println(me); 
    	client.setFocusable(true);
		client.requestFocusInWindow();
  	} 
}