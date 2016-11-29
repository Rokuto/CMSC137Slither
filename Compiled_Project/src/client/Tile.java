import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Tile extends JPanel{
	
	private ImageIcon background	= new ImageIcon("img/bg/black.png");
	private ImageIcon snakeBody		= new ImageIcon("img/snake/bodyGreen.png"); 
	private ImageIcon headSouth		= new ImageIcon("img/snake/headGreenD.png"); 
	private ImageIcon headNorth		= new ImageIcon("img/snake/headGreenN.png"); 
	private ImageIcon headLeft		= new ImageIcon("img/snake/headGreenL.png"); 
	private ImageIcon headRight		= new ImageIcon("img/snake/headGreenR.png"); 

	public Tile(){
		super();
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
      	add(new JLabel(background));	
	}

	public void changehead(int direction){
		removeAll();
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
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
		add(new JLabel(snakeBody));	
		validate();			
	}

	public void placeBlank(){
		removeAll();
		setLayout(new BorderLayout());
      	setOpaque(false);
      	setPreferredSize(new Dimension(25,25));
		add(new JLabel(background));			
		validate();
	}
}