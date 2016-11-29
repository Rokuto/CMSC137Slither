import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Snake extends JPanel implements Runnable {
	public static int position;
	public static Dimension screenSize;
	private int multiplier_x;
	private int multiplier_y;
	public static int[] position_x = new int[4];
	public static int[] position_y = new int[4];

	public Snake() {
		screenSize = Main.getScreenSize();
		setOpaque(false);

		setPreferredSize(new Dimension(3*(screenSize.width/4), screenSize.height));
		multiplier_x = ((3*(screenSize.width/4))/9);
		multiplier_y = (screenSize.height/9);

		position_x[3] = 4;
		position_x[2] = 3;
		position_x[1] = 2;
		position_x[0] = 1;
		position_y[3] = 5;
		position_y[2] = 5;
		position_y[1] = 5;
		position_y[0] = 5;

		addKeyListener(new KeyListener () {
			@Override
      public void keyTyped(KeyEvent e){}

      @Override
      public void keyReleased(KeyEvent e){}

			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyChar());
			}
		});
		setFocusable(true);
		requestFocusInWindow();

	}

	@Override
	public void run() {
		/*try {
	        Thread.sleep(500);
	    } catch (Exception e ) {}
		if(key == 'w') {
			position_x[3] = position_x[2];
			position_x[2] = position_x[1];
			position_x[1] = position_x[0];

			position_y[3] = position_y[2];
			position_y[2] = position_y[1];
			position_y[1] = position_y[0];
			position_y[0]++;
		} else if(key == 's') {
			position_x[3] = position_x[2];
			position_x[2] = position_x[1];
			position_x[1] = position_x[0];

			position_y[3] = position_y[2];
			position_y[2] = position_y[1];
			position_y[1] = position_y[0];
			position_y[0]--;
		} else if(key == 'a') {
			position_x[3] = position_x[2];
			position_x[2] = position_x[1];
			position_x[1] = position_x[0];
			position_x[0]--;

			position_y[3] = position_y[2];
			position_y[2] = position_y[1];
			position_y[1] = position_y[0];
		} else if(key == 'd') {
			position_x[3] = position_x[2];
			position_x[2] = position_x[1];
			position_x[1] = position_x[0];
			position_x[0]++;

			position_y[3] = position_y[2];
			position_y[2] = position_y[1];
			position_y[1] = position_y[0];
		}*/

		this.validate();
		this.repaint();
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		for(int i=0; i<4; i++) {
			g.fillRect(position_x[i] * multiplier_x, position_y[i] * multiplier_y, multiplier_x, multiplier_y);
		}
	}

}
