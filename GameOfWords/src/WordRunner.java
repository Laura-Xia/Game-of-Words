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

public class WordRunner extends JPanel implements KeyListener{
	public final int WIDTH = 600, HEIGHT = 600, WINDOW_HEIGHT = 600;

	public int Ix = 51;
	public int Iy = 116;
	public int previousx, previousy;// stores the previous position of "I"
	public int index = 0;
	
	public Type[] scene = new Type[5];
	
	public void initialize() {
		scene[0] = new Intro(this);
		scene[1] = new LivingRoom(this);
		scene[2] = new Road(this);
		scene[3] = new Bank(this);
		scene[4] = new Paper(this);
		for(int i = 0; i<scene.length; i++) {
			scene[i].initialize();
		}
	}
	
	public void setup() {
		scene[index].setup();
	}

	public void paint(Graphics g) {
		scene[index].draw(g);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		scene[index].keyPressed(e);
	}
	
	public void mouseP(MouseEvent e) {
		scene[index].mousePressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	
	public void run() {

		// while(true) should rarely be used to avoid infinite loops, but here it is ok because
		// closing the graphics window will end the program
		initialize();
		while (true) {
	
			// draws the game
			setup();
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
		new WordRunner();
	}
 
	// does complicated stuff to initialize the graphics and key listeners
	// DO NOT CHANGE THIS CODE UNLESS YOU ARE EXPERIENCED WITH JAVA
	// GRAPHICS!
	public WordRunner() {
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, WINDOW_HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
				mouseP(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		this.addKeyListener(this);
		this.setFocusable(true);
		
		run();
	}

}
