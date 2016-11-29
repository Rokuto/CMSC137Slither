
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public class Main extends Window {
  private Button exitButton;
  private JPanel rightPanel;
  private JPanel promptPanel;
  private JPanel scorePanel;
  private JPanel gamePanel;
  Thread t1 = null;
  CanvasArena canvas = null;
  Snake snake = null;

  public Main() {
    super(new Frame());
    setLayout(new GridBagLayout());
    rightPanel = new JPanel();
    rightPanel.setVisible(false);
    scorePanel = new JPanel();
    scorePanel.setVisible(false);

    Dimension screenSize = getScreenSize();

    //sets the location of the window to top left of screen
    setBounds(0,0,screenSize.width, screenSize.height);

    //set background
    setBackground(Color.BLACK);


    //create prompt panel
    promptPanel = new JPanel();
    promptPanel.setBorder(new LineBorder(Color.BLACK));
    promptPanel.setPreferredSize(new Dimension((screenSize.width)/3, (screenSize.height)/10));
    promptPanel.setLayout(new GridLayout());

    JTextField getName = new JTextField("Name", 10);
    getName.setPreferredSize(new Dimension(100, 20));
    getName.requestFocus();
    getName.setEditable(true);
    getName.setFocusable(true);

    JButton playButton = new JButton("PLAY");

    playButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          promptPanel.setVisible(false);
          rightPanel.setVisible(true);
          scorePanel.setVisible(true);

          setLayout(new BorderLayout());
          setRightPanel();
          setGamePanel();

          spawnPower();
        }});
    promptPanel.add(getName);
    promptPanel.add(playButton);
    add(promptPanel, new GridBagConstraints());

  }

  public void setRightPanel() {
	  Dimension screenSize = getScreenSize();
	  exitButton = new Button("I am a quitter");

	    exitButton.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        System.exit(0);
	      }});

	    //create the rightmost panel

	    rightPanel.setLayout(new BorderLayout());
	    rightPanel.setPreferredSize(new Dimension((screenSize.width)/4, screenSize.height));
	    rightPanel.setOpaque(true);

	    scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
	    scorePanel.add(exitButton);

	    JLabel scoreLabel = new JLabel("Score: ");
	    scorePanel.add(scoreLabel);
	    rightPanel.add(scorePanel, BorderLayout.NORTH);
	    exitButton.setVisible(true);
      exitButton.setBackground(Color.RED);

	    JTextField chat = new JTextField(20);
	    rightPanel.add(chat, BorderLayout.SOUTH);
	    this.add(rightPanel, BorderLayout.EAST);
  }

  public void setGamePanel() {
      gamePanel = new JPanel();
      gamePanel.setPreferredSize(new Dimension(3*(getScreenSize().width/4), getScreenSize().height));
      gamePanel.setLayout(new OverlayLayout(gamePanel));
      snake = new Snake();
      canvas = new CanvasArena();
      gamePanel.add(snake);
      gamePanel.add(canvas);

      this.add(gamePanel, BorderLayout.WEST);

  }


  public static Dimension getScreenSize() {
	  return Toolkit.getDefaultToolkit().getScreenSize();
  }

  public void spawnPower() {
	  System.out.println("repaint canvas");
          t1 = new Thread(canvas);
          t1.start();

          Timer t = new Timer(10000, new ActionListener() {
        	    public void actionPerformed(ActionEvent e) {
        	    	if(!t1.isAlive()) {
        	    	spawnPower();
        	    	}
        	    }
        	});
          t.start();


  }



  public static void main(String[] args) {
    new Main().setVisible(true);
  }

}
