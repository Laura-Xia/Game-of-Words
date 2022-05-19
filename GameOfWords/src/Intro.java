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

public class Intro extends Type{
	
	private String[] text = new String[4];// the texts
	private int[] count = new int[5];// counter for type writer effect 
	private boolean[] countStart = new boolean[5];// booleans to decide if the next string can be printed
	private int counter = 0;// count which string the program is on
	private int[] timer  = new int[5];// timer in between the strings
	private int[] x = new int[4];// x positions of the strings
	private int[] y = new int[4];// y positions of the strings
	private int countDoor = 0;// counter for the door image
	private Image meImg, doorImg, door2Img, door3Img;
	private int doorx = 242;// x position of image door
	private int doory = 401;// y position of image door
	private boolean move = false;
	
	
	WordRunner main;
	
	public Intro(WordRunner m) {
		main = m;
	}

	
	public void initialize() {
		// setup all the pictures
		meImg = Toolkit.getDefaultToolkit().getImage("Images/I.png");
		doorImg = Toolkit.getDefaultToolkit().getImage("Images/door.png");
		door2Img = Toolkit.getDefaultToolkit().getImage("Images/door 2.png");
		door3Img = Toolkit.getDefaultToolkit().getImage("Images/door 3.png");
		// setup all the sentences
		text[0] = " wake up to complete darkness in my room.";
		text[1] = "The eeriness is making me uncomfortable, I";
		text[2] = "decide to leave the room.";
		text[3] = "But where is the door?";
		// setup all the positions of the sentences
		x[0] = 68;
		y[0] = 140;
		x[1] = 50;
		y[1] = 220;
		x[2] = 50;
		y[2] = 300;
		x[3] = 50;
		y[3] = 420;
		// reset these variables every time the scene is ran 
		countDoor = 0;
		counter = 0;
		
		main.Ix = 51;
		main.Iy = 116;
		doorx = 242;// x position of image door
		doory = 401;// y position of image door
		move = false;
		
		for(int i = 0; i<5; i++) {
			countStart[i] = false;
			count[i] = 0;
			timer[i] = 0;
		}
	}
	
	public void setup() {
		// setup countStart variables for typewriter effect
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
	}
	
	public boolean collision() {
		// collision method with "door"
		if (main.Ix>=doorx-3&&main.Ix<=doorx+49&&main.Iy>=doory&&main.Iy<=doory+20) {
			return true;
		}
		return false;
	}

	public void draw(Graphics g) {
		// set background
		g.setColor(Color.black);
		g.fillRect(0, 0, main.WIDTH, main.HEIGHT);
		// draws main character
		g.drawImage(meImg, main.Ix, main.Iy, 17, 31, null);
		
		// set font
		Font f = new Font("Chalkboard", Font.PLAIN, 24);
		g.setFont(f);
		g.setColor(Color.white);
		if (counter<=4) {
			// typewriter effect for first 5 strings
			if (countStart[counter]==true) {
				count[counter]++;
				g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
			}
			else {
				// pause between strings
				timer[counter]++;
			}
			// goes to print out next string
			if (getNext()==true) {
				counter++;
				setNext(false);
			}
			// if all strings are printed
			if (counter<5) {
				for (int i = 0; i<counter; i++) {
					// keeps all strings on screen
					if (i==3) {
						text[3] = "But where is the      ?";
						// change appearance of "door" when collided" 
						if(countDoor<20) g.drawImage(doorImg, doorx, doory, 49, 20, null);
						else if(countDoor<40) g.drawImage(door2Img, doorx, doory, 49, 20, null);
						else if (countDoor<60) g.drawImage(door3Img, doorx, doory, 49, 20, null);
					}
					g.drawString(text[i], x[i], y[i]);
				}
			}
			// allow main character to move
			if (counter==4) move = true;
			// if collide, clear screen
			if(collision() == true) {
				countDoor++;
				if (countDoor >= 80) {
					// changes to next scene + initialize that scene
					main.index++;
					main.scene[main.index].initialize();
				}
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		// main character cannot move out of the screen in all directions.
		int keyCode = e.getKeyCode();
		// move method for moving down
		if (keyCode == KeyEvent.VK_DOWN && move==true) {
			if (main.Iy>=main.HEIGHT-92&&main.Iy!=main.HEIGHT-60) {
				main.previousy = main.Iy;
				main.Iy = main.HEIGHT-60;
			}
			else if (main.Iy == 0) main.Iy = main.previousy;
			else if (main.Iy<=main.HEIGHT-92) {
				main.Iy+=32;
			}
			else main.Iy = main.HEIGHT-60;
		}
		// move method for moving up
		if (keyCode == KeyEvent.VK_UP && move == true) {
			if (main.Iy<=32&&main.Iy!=0) {
				main.previousy = main.Iy;
				main.Iy = 0;
			}
			else if (main.Iy == main.HEIGHT) main.Iy = main.previousy;
			else if (main.Iy>=32) main.Iy-=32;
		}
		// move method for moving left
		if (keyCode == KeyEvent.VK_LEFT && move==true) {
			if (main.Ix<=32&&main.Ix!=0) {
				main.previousx = main.Ix;
				main.Ix = 0;
			}
			else if (main.Ix == main.WIDTH) main.Ix = main.previousx;
			else if (main.Ix>=32) main.Ix-=32;
		}
		// move method for moving right
		if (keyCode == KeyEvent.VK_RIGHT && move == true) {
			if (main.Ix>=main.WIDTH-49&&main.Ix!=main.WIDTH-17) {
				main.previousx = main.Ix;
				main.Ix = main.WIDTH-17;
			}
			else if (main.Ix == 0) {
				main.Ix = main.previousx;
			}
			else if (main.Ix<=main.WIDTH-32) main.Ix+=32;
		}
	}
}
