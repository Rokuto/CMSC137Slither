import java.awt.BorderLayout;

import javax.swing.JFrame;


public class GameFrame extends JFrame {

	private BoardPanel board;
	private SidePanel side;

	public GameFrame(String name, String ip){
		super("Slitherin");
		
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//setSize(400,400);
		setResizable(false);

		/* Initialize frame components */
		this.board = new BoardPanel(this);
		this.side = new SidePanel(this, name, ip);
		
		/* Add frame components */
		add(board, BorderLayout.CENTER);
		add(side, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void gameOver(){
		System.out.println("Its Game Over");
		this.dispose();

		MenuUI menu = new MenuUI();
		menu.cardLayout_setup();
	}

}