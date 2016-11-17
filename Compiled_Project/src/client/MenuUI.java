import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.OverlayLayout;
import javax.swing.Timer;
import javax.swing.border.Border;


public class MenuUI extends JFrame implements ActionListener{
	private JFrame frame_main;
	private JFrame frame_game;
	
	//image file instantiation
	Image img_background 	= new ImageIcon("img/menu/backgroundv2.jpg").getImage();
	Image img_backgroundv2 	= new ImageIcon("img/menu/backgroundv3.jpg").getImage();
	Image img_enter 		= new ImageIcon("img/menu/enter.png").getImage();
	Image img_highscores 	= new ImageIcon("img/menu/highscores.png").getImage();
	Image img_instructions 	= new ImageIcon("img/menu/instructions.png").getImage();
	Image img_back 			= new ImageIcon("img/menu/back.png").getImage();
	Image img_lbl_hs 		= new ImageIcon("img/menu/highscores_clk.png").getImage();
	Image img_lbl_ins 		= new ImageIcon("img/menu/instructions_clk.png").getImage();
	
	//JPanel instantiation
	JPanel panel;
	JPanel pnl_main;
	JPanel pnl_instructions;
	JPanel pnl_highscores;
	JPanel pnl_menu;
	
	//JLabel instantiation
	JLabel lbl_highscores 	= new JLabel(new ImageIcon(img_lbl_hs));
	JLabel lbl_instructions = new JLabel(new ImageIcon(img_lbl_ins));
	
	//JButton instantiation
	JButton btn_enter 			= new JButton(new ImageIcon(img_enter));
	JButton btn_highscores 		= new JButton(new ImageIcon(img_highscores));
	JButton btn_instructions 	= new JButton(new ImageIcon(img_instructions));
	JButton btn_back1 			= new JButton(new ImageIcon(img_back));
	JButton btn_back2 			= new JButton(new ImageIcon(img_back));
	
	//JTextField instantiation
	JTextField txtfld_usrname 	= new JTextField(20);
	JTextField txtfld_ipaddress = new JTextField(20);
	JTextArea instructions 		= new JTextArea();
	
	//Other instantiation
	Border border 		= BorderFactory.createLineBorder(Color.YELLOW, 5);
	Border btn_border 	= BorderFactory.createLineBorder(Color.GREEN, 2);
	Font font1 			= txtfld_usrname.getFont().deriveFont(Font.PLAIN, 20f);
	

	public MenuUI(){ // Constructor
		createMenu();
	}
	
	private void createMenu(){
		
		//pnl_main properties
		pnl_main = new JPanel(){
			 public void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            g.drawImage(img_background, 0, 0, 825, 645, this);
		        }
		};
		
		pnl_main.setLayout(null);
		
		//highscores panel
		pnl_highscores = new JPanel(){
			 public void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            g.drawImage(img_backgroundv2, 0, 0, 825, 645, this);
		        }
		};
		
		pnl_highscores.setLayout(null);
		
		//instructions panel
		pnl_instructions = new JPanel(){
			 public void paintComponent(Graphics g) {
		            super.paintComponent(g);
		            g.drawImage(img_backgroundv2, 0, 0, 825, 645, this);
		        }
		};
		
		pnl_instructions.setLayout(null);
		
		//frame component
		frame_main = new JFrame("Slitherin");
		
			
		//set textfield properties
		txtfld_usrname.setBorder(border);
		txtfld_usrname.setBounds(220, 300, 200, 40);
		txtfld_usrname.setFont(font1);
		txtfld_usrname.setHorizontalAlignment(JTextField.CENTER);
		txtfld_usrname.setText("Username");
		
		
		txtfld_ipaddress.setBorder(border);
		txtfld_ipaddress.setBounds(430, 300, 200, 40);
		txtfld_ipaddress.setFont(font1);
		txtfld_ipaddress.setHorizontalAlignment(JTextField.CENTER);
		txtfld_ipaddress.setText("IP Address");
		
		//button properties and position
		btn_enter.setBounds(325, 360, 200, 50);
		btn_enter.setContentAreaFilled(false);
		btn_enter.setBorder(btn_border);
		btn_enter.addActionListener(this);
		
		btn_highscores.setBounds(300, 420, 250, 50);
		btn_highscores.setContentAreaFilled(false);
		btn_highscores.setBorder(btn_border);
		
		btn_instructions.setBounds(280, 480, 290, 50);
		btn_instructions.setContentAreaFilled(false);
		btn_instructions.setBorder(btn_border);
		
		btn_back1.setBounds(20, 550, 50, 50);
		btn_back1.setContentAreaFilled(false);
		btn_back1.setBorder(null);
		btn_back1.addActionListener(this);
		
		btn_back2.setBounds(20, 550, 50, 50);
		btn_back2.setContentAreaFilled(false);
		btn_back2.setBorder(null);
		btn_back2.addActionListener(this);
		
		//JTextArea property
		instructions = new JTextArea(
			    "↑/W - Move Up " + "\n" +
			    "←/A - Move Left" + "\n" +
			    "↓/S - Move Down" + "\n" +
			    "→/D - Move Right" + "\n"
		);
		instructions.setFont(new Font("Serif", Font.ITALIC, 16));
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setEditable(false);
		instructions.setBounds(200, 150, 430, 300);
		
		//menu components
		pnl_main.add(txtfld_usrname);
		pnl_main.add(txtfld_ipaddress);
		pnl_main.add(btn_enter);
		pnl_main.add(btn_highscores);
		pnl_main.add(btn_instructions);
		
		//highscores and instructions
		lbl_highscores.setBounds(270, 50, 300, 50);
		lbl_instructions.setBounds(270,50,300,50);
		
		pnl_highscores.add(btn_back1);
		pnl_highscores.add(lbl_highscores);
		
		pnl_instructions.add(btn_back2);
		pnl_instructions.add(lbl_instructions);
		pnl_instructions.add(instructions);
				
		frame_main.setResizable(false);
		frame_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame_main.setSize(825, 645);
		frame_main.setVisible(true);
		
	}

	public void cardLayout_setup(){
		panel = new JPanel(new CardLayout());
	
		panel.add("Main", pnl_main);
		panel.add("Highscores", pnl_highscores);
		panel.add("Instructions", pnl_instructions);
		
		btn_highscores.addActionListener(this);
		btn_instructions.addActionListener(this);
		
		frame_main.setContentPane(panel);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout)(panel.getLayout());
		
		if(e.getSource() == btn_enter){
			
			frame_main.dispose();

			String ip = "127.0.0.1";
			
			new GameFrame(txtfld_usrname.getText(), ip);

			//new window properties
			// frame_game = new JFrame("Slitherin");
			// frame_game.setSize(1000, 700);
			// frame_game.setResizable(false);
			// frame_game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}else if(e.getSource() == btn_highscores){
			cardLayout.show(panel, "Highscores"); 
		}else if(e.getSource() == btn_instructions){
			cardLayout.show(panel, "Instructions"); 
		}else if(e.getSource() == btn_back1 || e.getSource() == btn_back2){
			cardLayout.show(panel, "Main"); 
		}
		
	}
}
