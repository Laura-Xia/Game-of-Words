// filler code for pong provided by Mr. David
// I got the idea of this game from "Word Game" on Steam featured by Team9. This game is similar to "Word Game", but in 
// instead of Chinese, and the plot is also different.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;

public class Words extends JPanel implements KeyListener {
	
	// constants that are predefined and won't change as the program runs
	private final int WIDTH = 600, HEIGHT = 600, WINDOW_HEIGHT = 600;

	
	// your instance variables here, I've given you a few 
	private boolean up1, down1, up2, down2; 		// booleans to keep track of paddle movement
	private boolean solo = false;
	private int[] count = new int[10];
	// image variables
	private Image I;
	
	// this method returns the "inter" for the type writer effect
	public String type(String str, int num) {
		int inter = num/10;
		if (inter>str.length()) {
			return str;
		}
		else {
			return str.substring(0, inter);
		}
	}
	// upload my images
	public void setup() {
		I = Toolkit.getDefaultToolkit().getImage("I.png");
	}
	// defines what we want to happen anytime we draw the game
	// you simply need to fill in a few parameters here
	public void paint(Graphics g) {
		
		// background color is black
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// draw your rectangles and circles here
		// .......
		Font f = new Font("Chalkboard", Font.PLAIN, 24);
		g.setFont(f);
		g.setColor(Color.white);
		g.drawImage(I, 50, 160, 32, 32, this);
//		count[0]++;
//		g.drawString(type(" woke up to complete darkness in my room", count[0]), 68, 160);
		// writes the score of the game - you just need to fill the scores in
	}

	// defines what we want to happen if a keyboard button has 
	// been pressed - you need to complete this
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		// changes paddle direction based on what button is pressed
		if (keyCode == KeyEvent.VK_DOWN) 
			// fill this in
		
		if (keyCode == KeyEvent.VK_UP) 
			// fill this in

		if (e.getKeyChar() == 'w')
			// move paddle down
		
		if (e.getKeyChar() =='s')
			// fill this in
			
		// turn 1-player mode on
		if (e.getKeyChar() == '1')
			// fill this in
			
		// turn 2-player mode on
		if (e.getKeyChar() == '2')
			// fill this in
	}

	// defines what we want to happen if a keyboard button
	// has been released - you need to complete this
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		// stops paddle motion based on which button is released
		if (keyCode == KeyEvent.VK_UP) 
			// fill this in

		if (keyCode == KeyEvent.VK_DOWN) 
			// fill this in
  
		if(e.getKeyChar() == 'w')
			// fill this in
		
		if (e.getKeyChar() == 's')
			// fill this in
	}
	
	// restarts the game, including scores
//	public void restart() {
//
//		// your code here
//	}

	//////////////////////////////////////
	//////////////////////////////////////
	
	// DON'T TOUCH THE BELOW CODE
	
	
	// this method runs the actual game.
	public void run() {

		// while(true) should rarely be used to avoid infinite loops, but here it is ok because
		// closing the graphics window will end the program
		while (true) {
	
			// draws the game
			repaint();
			
			
			// every hundredth of a second
			//rests for a hundredth of a second
			try {
				Thread.sleep(10);
			} catch (Exception ex) {}
		}
	}
	
	// very simple main method to get the game going
	public static void main(String[] args) {
		new Words();
	}
 
	// does complicated stuff to initialize the graphics and key listeners
	// DO NOT CHANGE THIS CODE UNLESS YOU ARE EXPERIENCED WITH JAVA
	// GRAPHICS!
	public Words() {
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		this.addKeyListener(this);
		this.setFocusable(true);
		
		run();
	}
	
	// method does nothing
	public void keyTyped(KeyEvent e) {}
}