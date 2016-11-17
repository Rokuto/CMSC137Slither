import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JPanel;


public class BoardPanel extends JPanel{

	public static final int COL_COUNT = 25;
	public static final int ROW_COUNT = 25;
	public static final int TILE_SIZE = 20;

	private static int internalBoard[][];
	private static Tile tiles[][];
	
	private GameFrame game;

	public BoardPanel(GameFrame game){
		this.game = game;

		this.internalBoard 	= new int[ROW_COUNT][COL_COUNT];
		this.tiles 			= new Tile[ROW_COUNT][COL_COUNT];

		setLayout(new GridLayout(25, 25));
		setPreferredSize(new Dimension(COL_COUNT * TILE_SIZE, ROW_COUNT * TILE_SIZE));
		setBackground(Color.BLACK);
		
		addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e){}

            @Override
            public void keyReleased(KeyEvent e){}

            @Override
            public void keyPressed(KeyEvent e){

                switch(e.getKeyCode()){
                	case KeyEvent.VK_LEFT:
               			System.out.println("Left");
               			break;
               		case KeyEvent.VK_RIGHT:
               			System.out.println("Right");
               			break;
               		case KeyEvent.VK_UP:
               			System.out.println("Up");
               			break;
               		case KeyEvent.VK_DOWN:
               			System.out.println("Down");
               			break;
               		default:
               		     break;
                }
            }
        });

		setFocusable(true);
		requestFocusInWindow();


	}
}