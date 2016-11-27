import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Tile extends JPanel{

	// private ImageIcon background	= new ImageIcon(getClass().getResource("img/bg/black.png"));
	private ImageIcon background 	= new ImageIcon("img/bg/black.png");
	private ImageIcon snakeBody		= new ImageIcon("img/snake/bodyGreen.png");
	private ImageIcon headSouth		= new ImageIcon("img/snake/headGreenD.png");
	private ImageIcon headNorth		= new ImageIcon("img/snake/headGreenN.png");
	private ImageIcon headLeft		= new ImageIcon("img/snake/headGreenL.png");
	private ImageIcon headRight		= new ImageIcon("img/snake/headGreenR.png");
	private ImageIcon speed 			= new ImageIcon("img/powerup/speed.png");
	private ImageIcon slow				= new ImageIcon("img/powerup/slow.png");
	private ImageIcon poison 			= new ImageIcon("img/powerup/poison.png");

	private int powerType;

	public Tile(){
		super();
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
      	add(new JLabel(background));
				powerType=0;
	}

	public void changehead(int direction){
		removeAll();
		powerType=0;
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));

		switch(direction){
			case 1:
				add(new JLabel(headNorth));
				break;
			case 2:
				add(new JLabel(headRight));
				break;
			case 3:
				add(new JLabel(headSouth));
				break;
			case 4:
				add(new JLabel(headLeft));
				break;
			default:
			    break;
		}

		validate();
	}

	public void placeBody(){
		removeAll();
		powerType=0;
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
		add(new JLabel(snakeBody));
		validate();
	}

	public void placeBlank(){
		removeAll();
		powerType=0;
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
		add(new JLabel(background));
		validate();
	}

	public void placeSpeed() {
		removeAll();
		powerType=1;
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
		add(new JLabel(speed));
		validate();
	}

	public void placeSlow() {
		removeAll();
		powerType=2;
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
		add(new JLabel(slow));
		validate();
	}

	public void placePoison() {
		removeAll();
		powerType=3;
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
		add(new JLabel(poison));
		validate();
	}
}
