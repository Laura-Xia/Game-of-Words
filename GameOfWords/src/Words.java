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
	private boolean collide = false; 		// booleans to keep track of collision
	private int[] count = new int[15];// counter for type writer effect 
	private boolean[] countStart = new boolean[15];// booleans to decide if the next string can be printed
	private String[] text = new String[15];// the texts
	private boolean next = false;// the boolean to decide if the program should progress to the next string
	private int counter = 0;// count which string the program is on
	private int[] timer  = new int[15];// timer in between the strings
	private int[] x = new int[15];// x positions of the strings
	private int[] y = new int[15];// y positions of the strings
	private boolean move = false;
	private int countDoor = 0;// counter for the door image
	private boolean explain = false;// signify when to print the explanation for living room scene
	private boolean disappear = false;// explanation disappear when player moves
	private boolean inspect = false;// can start inspecting
	private boolean wall, cabinet, painting, table, flower;// items that can be inspected
	private int interm = 8;// some intermediate value for printing out two lines of text
	private boolean keep = false;// also some variable that will only be used once
	// image variables
	private Image meImg, doorImg, door2Img, door3Img, lrImg;
	private int Ix = 51;
	private int Iy = 116;
	
	// this method returns the "inter" for the type writer effect
	public String type(String str, int num) {
		int inter = num/10;
		if (inter>str.length()) {
			next = true;
			return str;
		}
		else {
			next = false;
			return str.substring(0, inter);
		}
	}
	// upload my images and give values to arrays
	public void setup() {
		// upload my images
		meImg = Toolkit.getDefaultToolkit().getImage("I.png");
		doorImg = Toolkit.getDefaultToolkit().getImage("door.png");
		door2Img = Toolkit.getDefaultToolkit().getImage("door 2.png");
		door3Img = Toolkit.getDefaultToolkit().getImage("door 3.png");
		lrImg = Toolkit.getDefaultToolkit().getImage("living room.png");
		
		
		// text array
		text[0] = " wake up to complete darkness in my room.";
		text[1] = "The eeriness is making me uncomfortable, I";
		text[2] = "decide to leave the room.";
		text[3] = "But where is the door?";
		text[4] = "I am in the living room.";
		text[5] = "Move around and hit SPACE to inspect.";
		text[6] = "Plain white wall with no stains of any kind.";
		text[7] = "My collection of liquors inside.";
		text[8] = "Painting of my boss. It's time to meet him.";
		text[9] = "He holds the key to the door of success.";
		text[10] = "Orchids. They smell nice.";
		text[11] = "Table made from aromatic fine wood.";
		
		// set first countStart boolean in order to initiate the typing
		countStart[0] = true;
		
		// time between each string
		if (timer[1]==60) {
			countStart[1] = true;
		}
		if (timer[2]==20) {
			countStart[2] = true;
		}
		if (timer[3]==70) {
			countStart[3] = true;
		}
		if (explain == true) {
			countStart[4] = true;
		}
		if (timer[5]==20) {
			countStart[5] = true;
		}
		
		// setting up x and y positions for the strings
		x[0] = 68;
		y[0] = 140;
		x[1] = 50;
		y[1] = 220;
		x[2] = 50;
		y[2] = 300;
		x[3] = 50;
		y[3] = 420;
		x[4] = 20;
		y[4] = 130;
		x[5] = 20;
		y[5] = 160;
		x[6] = 7;
		y[6] = 130;
		x[7] = 15;
		y[7] = 130;
		x[8] = 7;
		y[8] = 130;
		x[9] = 7;
		y[9] = 160;
		x[10] = 20;
		y[10] = 130;
		x[11] = 20;
		y[11] = 130;
	}
	
	// check if I and something else collides
	public void collision() {
		if (Ix>=242&&Ix<=291&&Iy>=401&&Iy<=421) {
			collide = true;
		}
	}
	// defines what we want to happen anytime we draw the game
	// you simply need to fill in a few parameters here
	public void paint(Graphics g) {
		
		// background color is black
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g.drawImage(meImg, Ix, Iy, 17, 31, null);
		
		// draw your rectangles and circles here
		// .......
		Font f = new Font("Chalkboard", Font.PLAIN, 24);
		g.setFont(f);
		g.setColor(Color.white);
		if (countStart[counter]==true) {
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
		else {
			timer[counter]++;
		}
		if (next==true) {
			counter++;
			next = false;
		}
		if (counter<5) {
			for (int i = 0; i<counter; i++) {
				if (i==3) {
					text[3] = "But where is the      ?";
					if(countDoor<20) g.drawImage(doorImg, 242, 401, 49, 20, null);
					else if(countDoor<40) g.drawImage(door2Img, 242, 401, 49, 20, null);
					else if (countDoor<60) g.drawImage(door3Img, 242, 401, 49, 20, null);
				}
				g.drawString(text[i], x[i], y[i]);
			}
		}
		if (counter==4) move = true;
		// if collide, clear screen
		if(collide == true) {
			countDoor++;
			// background color is black
			if (countDoor >= 80) {
				g.drawImage(lrImg, 0, 0, WIDTH, HEIGHT, null);
				g.drawImage(meImg, Ix, Iy, 17, 31, null);
				explain = true;
				move = false;
			}
		}
		if(counter>=4) {
			if (countStart[counter]==true) {
				count[counter]++;
				g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
			}
			else {
				timer[counter]++;
			}
			if (next==true) {
				counter++;
				next = false;
			}
			if(counter>5) {
				move = true;
				inspect = true;
			}
			for (int i = 4; i<counter; i++) {
				if (!disappear) g.drawString(text[i], x[i], y[i]);
			}
		}
		if(wall==true) {
			counter = 6;
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
		else if(cabinet==true) {
			counter = 7;
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
		else if(painting==true) {
			next = false;
			count[interm]++;
			g.drawString(type(text[interm], count[interm]), x[interm], y[interm]);
			if (next == true) {
				interm = 9;
				next = false;
				keep = true;
			}
			if (keep == true) g.drawString(text[8], x[8], y[8]);
		}
		else if(flower==true) {
			counter = 10;
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
		else if(table==true) {
			counter = 11;
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
	}

	// defines what we want to happen if a keyboard button has 
	// been pressed - you need to complete this
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		// changes paddle direction based on what button is pressed
		if (keyCode == KeyEvent.VK_DOWN && move==true) {
			Iy+=32;
			if (counter==6) disappear = true;
			if (wall==true) {
				wall = false;
				count[6]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[7]=0;
			}
			if (painting==true) {
				painting = false;
				count[8]=0;
				count[9]=0;
			}
			if (flower==true) {
				flower = false;
				count[10]=0;
			}
			if (table==true) {
				table = false;
				count[11]=0;
			}
		}
			// fill this in
		
		if (keyCode == KeyEvent.VK_UP && move == true) {
			Iy-=32;
			if (counter==6) disappear = true;
			if (wall==true) {
				wall = false;
				count[6]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[7]=0;
			}
			if (painting==true) {
				painting = false;
				count[8]=0;
				count[9]=0;
			}
			if (flower==true) {
				flower = false;
				count[10]=0;
			}
			if (table==true) {
				table = false;
				count[11]=0;
			}
		} 
		
		if (keyCode == KeyEvent.VK_LEFT && move==true) {
			Ix-=32;
			if (counter==6) disappear = true;
			if (wall==true) {
				wall = false;
				count[6]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[7]=0;
			}
			if (painting==true) {
				painting = false;
				count[8]=0;
				count[9]=0;
			}
			if (flower==true) {
				flower = false;
				count[10]=0;
			}
			if (table==true) {
				table = false;
				count[11]=0;
			}
		}
			// fill this in
		
		if (keyCode == KeyEvent.VK_RIGHT && move == true) {
			Ix+=32;
			if (counter==6) disappear = true;
			if (wall==true) {
				wall = false;
				count[6]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[7]=0;
			}
			if (painting==true) {
				painting = false;
				count[8]=0;
				count[9]=0;
			}
			if (flower==true) {
				flower = false;
				count[10]=0;
			}
			if (table==true) {
				table = false;
				count[11]=0;
			}
		} 
		
		if (keyCode == KeyEvent.VK_SPACE && inspect == true) {
			if ((Ix<=192&&Ix>=0&&Iy>=0&&Iy<=84)||(Ix<=600&&Ix>=408&&Iy>=0&&Iy<=64)) wall = true;
			else if (Ix>=192&&Ix<=408&&Iy>=52&&Iy<=84) cabinet = true;
			else if (Ix>=467&&Ix<=531&&Iy>=84&&Iy<=180) painting = true;
			else if (Ix>=403&&Ix<=467&&Iy==340) flower = true;
			else if (Ix>=403&&Ix<=563&&Iy>=372&&Iy<=436) table = true;
			System.out.println(Ix);
			System.out.println(Iy);
		}
			// fill this in

	}

	// defines what we want to happen if a keyboard button
	// has been released - you need to complete this
	public void keyReleased(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
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
			setup();
			collision();
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