package jmp.ekans;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Font;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JSlider;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.SwingConstants;

/**
 * @author JMP085
 *
 */
@SuppressWarnings("serial")
public class ekans extends JPanel implements KeyListener, ItemListener, ChangeListener, ActionListener{

	static boolean restart = false;
	private static boolean pause = true;
	double w = 770*Math.random();
	double h = 700*Math.random()+40;
	boolean dd = false;
	int u = 0;
	int v = 0;
	private Ball ball2;
	int n = 0;
	private static int s= 5;
	private boolean bound;
	static boolean kill= false;

	Ball ball = new Ball(this, 0, 41, 20);
	Ball point = new Ball(this ,(int) (w),(int) (h), 20);
	ArrayList <Integer> posicionesX = new ArrayList<Integer>();
	ArrayList <Integer> posicionesY = new ArrayList<Integer>();
	ArrayList <Ball> bubbles = new ArrayList<Ball>();

	JTextPane txtrRecord = new JTextPane();
	static JFrame frame = new JFrame("SNAKE (by JMP)");
	private JCheckBox chckbxProMode;
	private static JSlider slider;
	private JButton btnPlayPause;
	private JLabel lblMinSpeed;
	private JLabel lblMaxSpeed;
	private static JLabel lbl;
	private static JLabel lblNewLabel;

	public ekans() {
		setBackground(Color.DARK_GRAY);
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				repaint();
				/*
				if (pause == true){
					switch (c){
					case KeyEvent.VK_1: slider.setValue(1);
					case KeyEvent.VK_2: slider.setValue(2);
					case KeyEvent.VK_3: slider.setValue(3);
					case KeyEvent.VK_4: slider.setValue(4);
					case KeyEvent.VK_5: slider.setValue(5);
					case KeyEvent.VK_6: slider.setValue(6);
					case KeyEvent.VK_7: slider.setValue(7);
					case KeyEvent.VK_8: slider.setValue(8);
					default: slider.setValue(s);
					}
				}
				*/
				if (c == KeyEvent.VK_ENTER){
					pause = false;
					chckbxProMode.setEnabled(false);
					slider.setEnabled(false);
					lblMinSpeed.setEnabled(false);
					lblMaxSpeed.setEnabled(false);
				}
				if (c == KeyEvent.VK_P){
					pause = true;
				}
				if (c == KeyEvent.VK_UP) {
					if (ball.getYa() != 1){   
						ball.setXa(0);
						ball.setYa(-1);
					}
				}
				if (c == KeyEvent.VK_DOWN) {
					if (ball.getYa() != -1){
						ball.setXa(0);
						ball.setYa(1);
					}
				}
				if (c == KeyEvent.VK_RIGHT) {
					if (ball.getXa() != -1){
						ball.setYa(0);
						ball.setXa(1);
					}
				}
				if (c == KeyEvent.VK_LEFT) {
					if (ball.getXa() != 1){
						ball.setYa(0);
						ball.setXa(-1);
					}
				}

			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		setFocusable(true);
		setLayout(null);

		lblNewLabel = new JLabel("PAUSED");
		lblNewLabel.setBounds(270, 290, 233, 38);
		add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 51));
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setBackground(null);
		lblNewLabel.setVisible(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 40, 800, 760);
		add(panel);
		panel.setLayout(null);

		lbl = new JLabel("Press ENTER to continue");
		lbl.setBounds(230, 286, 312, 31);
		panel.add(lbl);
		lbl.setHorizontalAlignment(SwingConstants.CENTER);
		lbl.setVerticalAlignment(SwingConstants.CENTER);
		lbl.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 20));
		lbl.setBackground(new Color(240, 240, 240));
		lbl.setBackground(null);
		lbl.setVisible(false);

		if (!restart) slider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
		if(restart) slider = new JSlider(JSlider.HORIZONTAL, 1, 10, s);
		slider.setBounds(237, 9, 200, 26);
		slider.setInverted(true);
		//slider.setMinorTickSpacing(3);
		slider.addChangeListener(this);
		slider.setOpaque(false);
		//slider.setPaintTrack(false);
		add(slider);
		
		lblMinSpeed = new JLabel("Min. Speed");
		lblMinSpeed.setForeground(Color.WHITE);
		lblMinSpeed.setBounds(161, 13, 72, 19);
		add(lblMinSpeed);

		lblMaxSpeed = new JLabel("Max. Speed");
		lblMaxSpeed.setForeground(Color.WHITE);
		lblMaxSpeed.setBounds(442, 13, 72, 19);
		add(lblMaxSpeed);

		btnPlayPause = new JButton("PLAY / PAUSE");
		btnPlayPause.setBounds(655, 9, 123, 28);
		btnPlayPause.addActionListener(this);
		add(btnPlayPause);

		chckbxProMode = new JCheckBox("Pro Mode", true);
		chckbxProMode.setBackground(Color.DARK_GRAY);
		chckbxProMode.setForeground(Color.WHITE);
		chckbxProMode.setBounds(536, 7, 103, 28);
		chckbxProMode.setSelected(true);
		add(chckbxProMode);

		chckbxProMode.addItemListener(this);
		slider.setFocusable(false);
		chckbxProMode.setFocusable(false);
		btnPlayPause.setFocusable(false);
		txtrRecord.setToolTipText("");
		txtrRecord.setBounds(12, 9, 124, 26);
		txtrRecord.setForeground(Color.WHITE);
		txtrRecord.setBackground(Color.BLACK);
		txtrRecord.setFont(new Font("GulimChe", Font.BOLD, 14));
		txtrRecord.setText(" Record");
		txtrRecord.setEditable(false);
		txtrRecord.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		add(txtrRecord);
	}
	public void stateChanged(ChangeEvent e) {
		s = slider.getValue();
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			bound= true;
		}
		else { 
			bound = false;
		}
	}

	public void keyReleased(KeyEvent e) {
	}    

	public static int getS() {
		return s;
	}

	public void setBX(int j, int value) {
		bubbles.get(j).setX(value);
	}

	public void setBY(int j, int value) {
		bubbles.get(j).setY(value);
	}

	private void moveBall() {
		txtrRecord.setText(" Record: "+Integer.toString(n));
		ball.move();
		posicionesX.add(ball.getX());
		posicionesY.add(ball.getY());	

		if (bound == false){
			if (ball.getX() <= 0 || ball.getX() >= 773 || ball.getY() <= 40|| ball.getY()>= 744)
				kill = true;
		}
		//Down
		for ( int j = 0; j < n; j++){
			if (ball.getXa() == 0 && ball.getYa() == 1 ){
				setBX (j, posicionesX.get(posicionesX.size()-20*(j+1)));
				setBY( j, posicionesY.get(posicionesY.size()-20*(j+1)));
			}
			//Up 
			if (ball.getXa() == 0 && ball.getYa() == -1 ){
				setBX(j, posicionesX.get(posicionesX.size()-20*(j+1)));
				setBY(j, posicionesY.get(posicionesY.size()-20*(j+1)));
			}
			//Right
			if (ball.getXa() == 1 && ball.getYa() == 0 ){
				setBX(j, posicionesX.get(posicionesX.size()-20*(j+1)));
				setBY(j, posicionesY.get(posicionesY.size()-20*(j+1)));
			}
			//Left
			if (ball.getXa() == -1 && ball.getYa() == 0 ){
				setBX(j, posicionesX.get(posicionesX.size()-20*(j+1)));
				setBY(j, posicionesY.get(posicionesY.size()-20*(j+1)));
			}

			if (Math.abs(ball.getX() - bubbles.get(j).getX()) <= 5 && Math.abs(ball.getY() - bubbles.get(j).getY()) <= 5){
				kill = true;
			}
			ball2.move();
		}
	}
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		ball.paint(g2d);

		for(int i = 0; i < bubbles.size(); i++){
			bubbles.get(i).paint(g2d);
		}
		g.setColor(Color.GRAY);
		point.paint(g2d);

		if (Math.abs(ball.getX() - point.getX()) <= 15 && Math.abs(ball.getY() - point.getY()) <= 15){
			point = new Ball(this ,(int) ( 770*Math.random()),(int) (700*Math.random()+40), 20);
			ball2 = new Ball(this, 0, 0, 20);
			bubbles.add(ball2);
			n++;
		}
	}

	public static void paused() throws InterruptedException, IOException{
		while(pause){
			kill = false;
			Thread.sleep(1);
			lblNewLabel.setVisible(true);
			lbl.setVisible(true);
			if (pause == false){ 
				lblNewLabel.setVisible(false);
				lbl.setVisible(false);
				return;}
		}
	}

	public static void main(String[] args) throws InterruptedException, URISyntaxException, IOException {
	
		do {
			ekans game = new ekans();
			frame.getContentPane().add(game);
			frame.setSize(800, 800);
			game.setBounds(0, 40, 800, 760);
			frame.setVisible(true);
			frame.setResizable(false);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			 frame.setLocationRelativeTo(null);
			while (kill == false) {
				game.moveBall();
				game.repaint();
				Thread.sleep(game.getS());
				paused();
			}

			if (kill == true){
				if(JOptionPane.showConfirmDialog(frame, "Play again?","GAME OVER!",JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
					/*
			final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
			  final File currentJar = new File(ekans.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			  if(!currentJar.getName().endsWith(".jar"))
			    return;
			  final ArrayList<String> command = new ArrayList<String>();
			  command.add(javaBin);
			  command.add("-jar");
			  command.add(currentJar.getPath());

			  final ProcessBuilder builder = new ProcessBuilder(command);
			  builder.start();
			  System.exit(0);
					 */
					s = ekans.getS();
					restart = true;
					kill = false;
					pause = true;
					frame.dispose();
				    frame = new JFrame("SNAKE (by JMP)");
				    game = new ekans();
					game.repaint();
					;}
				else{restart = false;
				System.exit(0);}
			}
		}
		while (restart);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		pause  = !pause;
		chckbxProMode.setEnabled(false);
		slider.setEnabled(false);
		lblMinSpeed.setEnabled(false);
		lblMaxSpeed.setEnabled(false);
		// TODO Auto-generated method stub
	}
	
}



