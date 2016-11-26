import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MoveAction implements KeyListener{

  private BoardPanel client; 
	
	public MoveAction(BoardPanel client){
	 this.client = client;
  }

	@Override
  public void keyTyped(KeyEvent e){}

  @Override
  public void keyReleased(KeyEvent e){}

  @Override
  public void keyPressed(KeyEvent e){
    switch(e.getKeyCode()){
    	case KeyEvent.VK_LEFT:
        client.sendData("1");
        System.out.println("1");
        break;
      case KeyEvent.VK_RIGHT:
        client.sendData("2");
        System.out.println("2");
        break;
      case KeyEvent.VK_UP:
        break;
      case KeyEvent.VK_DOWN:
        break;
      
      case KeyEvent.VK_A:
        client.sendData("1");
        System.out.println("1");
        break;
      case KeyEvent.VK_D:
        client.sendData("2");
        System.out.println("2");
        break;
      case KeyEvent.VK_W:
        break;
      case KeyEvent.VK_S:
        break;
      default:
   		     break;
    }
  }
}