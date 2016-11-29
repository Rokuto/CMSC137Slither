
import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

public class CanvasArena extends JPanel implements Runnable {
	private static Random random = new Random();
	private int n;
	private int[] x = new int[5];
	private int[] y = new int[5];
	public static Dimension screenSize;
	private int multiplier_x;
	private int multiplier_y;
	private int offset_x;
	private int offset_y;

	public CanvasArena() {
		screenSize = Main.getScreenSize();
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(3*(screenSize.width/4), screenSize.height));
		multiplier_x = ((3*(screenSize.width/4))/9);
		multiplier_y = (screenSize.height/9);
		offset_x = (((3*(screenSize.width/4))/9)-50)/2;
		offset_y = ((screenSize.height/9)-50)/2;
		setOpaque(true);
	}

	@Override
	public void run() {
		try {
	        Thread.sleep(10000);
	    } catch (Exception e ) {}
		this.validate();
		this.repaint();
		return;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0;i<5;i++){
		n = random.nextInt(4);
		if(n==0) {
			g.setColor(Color.YELLOW);
		} else if(n==1) {
			g.setColor(Color.BLUE);
		} else if(n==2) {
			g.setColor(Color.GREEN);
		} else if(n==3) {
			g.setColor(Color.RED);
		}
		x[i] = random.nextInt(9);
		y[i] = random.nextInt(9);
	    g.fillOval(x[i] * multiplier_x + offset_x, y[i] * multiplier_y  + offset_y, 50, 50);
		}
	}


}
