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
import java.awt.GraphicsEnvironment;

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

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;


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
	Image img_arrowkeys		= new ImageIcon("img/menu/arrowkeys.png").getImage();
	Image img_wasd			= new ImageIcon("img/menu/wasd.png").getImage();
	
	//JPanel instantiation
	JPanel panel;
	JPanel pnl_main;
	JPanel pnl_instructions;
	JPanel pnl_highscores;
	JPanel pnl_menu;
	
	//JLabel instantiation
	JLabel lbl_highscores 	= new JLabel(new ImageIcon(img_lbl_hs));
	JLabel lbl_instructions = new JLabel(new ImageIcon(img_lbl_ins));
	JLabel lbl_arrowkeys	= new JLabel(new ImageIcon(img_arrowkeys));
	JLabel lbl_wasd			= new JLabel(new ImageIcon(img_wasd));
	JLabel lbl_instxt		= new JLabel("Use the keys below to control your snake");
	
	//JLabel for highscores
	JLabel lbl_top1 = new JLabel("1st");
	JLabel lbl_top2 = new JLabel("2nd");
	JLabel lbl_top3 = new JLabel("3rd");
	
	JLabel[] lbl_name = new JLabel[3];
	JLabel[] lbl_score = new JLabel[3];
	
	//JButton instantiation
	JButton btn_enter 			= new JButton(new ImageIcon(img_enter));
	JButton btn_highscores 		= new JButton(new ImageIcon(img_highscores));
	JButton btn_instructions 	= new JButton(new ImageIcon(img_instructions));
	JButton btn_back1 			= new JButton(new ImageIcon(img_back));
	JButton btn_back2 			= new JButton(new ImageIcon(img_back));

	//JTextField instantiation
	JTextField txtfld_usrname 	= new JTextField(20);
	JTextField txtfld_ipaddress = new JTextField(20);
	JTextField txtfld_idno		= new JTextField(20);
		
	//Other instantiation
	Border border 		= BorderFactory.createLineBorder(Color.YELLOW, 5);
	Border btn_border 	= BorderFactory.createLineBorder(Color.GREEN, 2);
	Font font1 			= txtfld_usrname.getFont().deriveFont(Font.PLAIN, 20f);
	Font font;
	String [][] highscores;
	int x1, y1, width1, height1;
	int x2, y2, width2, height2;
	

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
		
		//Font import
		try{
			File fontFile = new File("img/menu/8bit_wonder.TTF");
		    font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        	font = font.deriveFont(Font.PLAIN,15);
		    GraphicsEnvironment ge =
		        GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(font);
		}catch(Exception e){
			e.printStackTrace();		
		}
		
		//frame component
		frame_main = new JFrame("Slitherin");
		
			
		//set textfield properties
		txtfld_usrname.setBorder(border);
		txtfld_usrname.setBounds(100, 300, 200, 40);
		txtfld_usrname.setFont(font1);
		txtfld_usrname.setHorizontalAlignment(JTextField.CENTER);
		txtfld_usrname.setText("Username");
		
		
		txtfld_ipaddress.setBorder(border);
		txtfld_ipaddress.setBounds(315, 300, 200, 40);
		txtfld_ipaddress.setFont(font1);
		txtfld_ipaddress.setHorizontalAlignment(JTextField.CENTER);
		txtfld_ipaddress.setText("127.0.0.1");
		
		txtfld_idno.setBorder(border);
		txtfld_idno.setBounds(530, 300, 200, 40);
		txtfld_idno.setFont(font1);
		txtfld_idno.setHorizontalAlignment(JTextField.CENTER);
		txtfld_idno.setText("ID No.");
		
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
		
		//menu components
		pnl_main.add(txtfld_usrname);
		pnl_main.add(txtfld_ipaddress);
		pnl_main.add(txtfld_idno);
		pnl_main.add(btn_enter);
		pnl_main.add(btn_highscores);
		pnl_main.add(btn_instructions);
		
		//highscores and instructions
		lbl_highscores.setBounds(270, 50, 300, 50);
		lbl_top1.setBounds(100, 150, 300, 50);
		lbl_top2.setBounds(100, 250, 300, 50);
		lbl_top3.setBounds(100, 350, 300, 50);
		
		
		lbl_top1.setFont(font);
		lbl_top2.setFont(font);
		lbl_top3.setFont(font);
		
		lbl_top1.setForeground(Color.GREEN);
		lbl_top2.setForeground(Color.GREEN);
		lbl_top3.setForeground(Color.GREEN);
		
		lbl_instructions.setBounds(270,50,300,50);
		lbl_arrowkeys.setBounds(125, 200, 300, 300);
		lbl_wasd.setBounds(400, 200, 300, 300);
		lbl_instxt.setBounds(140, 20, 650, 320);
		lbl_instxt.setFont(font);
		lbl_instxt.setForeground(Color.GREEN);
		
		readFile();
		
		pnl_highscores.add(btn_back1);
		pnl_highscores.add(lbl_highscores);
		pnl_highscores.add(lbl_top1);
		pnl_highscores.add(lbl_top2);
		pnl_highscores.add(lbl_top3);
		
		x1 = 300; x2 = 550; y1 = 150; y2 = 150;
		for(int i = 0; i < 3; i++){
			pnl_highscores.add(lbl_name[i]);
			pnl_highscores.add(lbl_score[i]);
			
			lbl_name[i].setBounds(x1,y1,400,50);
			lbl_score[i].setBounds(x2,y2,400,50);
			
			lbl_name[i].setForeground(Color.WHITE);
			lbl_score[i].setForeground(Color.WHITE);
			
			y1+=100;
			y2+=100;		
		}		
		
		pnl_instructions.add(btn_back2);
		pnl_instructions.add(lbl_instructions);
		pnl_instructions.add(lbl_arrowkeys);
		pnl_instructions.add(lbl_wasd);
		pnl_instructions.add(lbl_instxt);
				
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
	
	private void readFile(){
		BufferedReader reader;
		
		try{
			
			for(int i = 0; i < 3; i++){
				lbl_name[i]		= new JLabel();
				lbl_score[i]	= new JLabel();	
				
				lbl_name[i].setFont(font);
				lbl_score[i].setFont(font);		
			}
				
			reader = new BufferedReader(new FileReader("img/menu/highscores.txt"));
			String line = reader.readLine();
			highscores = new String[3][2];
			
			while (line != null) {
				for(int i = 0; i < 3; i++){
					String[] word = line.split("\\s+");
					//highscores[i] = word;
					lbl_name[i].setText(word[0]);
					lbl_score[i].setText(word[1]);
					line = reader.readLine();
				}
			}
			
			for(int i = 0; i < 3; i++){
				pnl_highscores.add(lbl_name[i]);
				pnl_highscores.add(lbl_score[i]);
			}
			
			reader.close();	
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		CardLayout cardLayout = (CardLayout)(panel.getLayout());
		
		if(e.getSource() == btn_enter){
			
			frame_main.dispose();

			//String ip = "127.0.0.1";
			
			new GameFrame(txtfld_usrname.getText(), txtfld_ipaddress.getText());

			//new window properties
			// frame_game = new JFrame("Slitherin");
			// frame_game.setSize(1000, 700);
			// frame_game.setResizable(false);

		}else if(e.getSource() == btn_highscores){
			cardLayout.show(panel, "Highscores"); 
		}else if(e.getSource() == btn_instructions){
			cardLayout.show(panel, "Instructions"); 
		}else if(e.getSource() == btn_back1 || e.getSource() == btn_back2){
			cardLayout.show(panel, "Main"); 
		}

	}
}
