import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;


public class BoardPanel extends JPanel{

	public static final int COL_COUNT = 25;
	public static final int ROW_COUNT = 25;
	public static final int TILE_SIZE = 20;
	
	
	private GameFrame game;

	public BoardPanel(GameFrame game){
		this.game = game;

		setPreferredSize(new Dimension(COL_COUNT * TILE_SIZE, ROW_COUNT * TILE_SIZE));
		setBackground(Color.BLACK);
	}
}