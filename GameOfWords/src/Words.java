// filler code for pong provided by Mr. David
// I got the idea of this game from "Word Game" on Steam featured by Team9. This game is similar to "Word Game", but in English
// instead of Chinese, and the plot is also different.

// this is the initial and unfinished version of the code, and it is no longer in use due to its length and inefficiency.
// However, I still decided to keep it because it has a smoother transition than the current version since all scenes are in one class
// not all code in this class are commented since I abandoned this class. 

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
	private boolean collide = false, collideB = false; 		// booleans to keep track of collision
	private int[] count = new int[30];// counter for type writer effect 
	private boolean[] countStart = new boolean[30];// booleans to decide if the next string can be printed
	private String[] text = new String[30];// the texts
	private boolean next = false;// the boolean to decide if the program should progress to the next string
	private int counter = 0;// count which string the program is on
	private int[] timer  = new int[30];// timer in between the strings
	private int[] x = new int[30];// x positions of the strings
	private int[] y = new int[30];// y positions of the strings
	private boolean move = false;
	private int[] countDoor = new int[10];// counter for the door image
	private boolean explain = false;// signify when to print the explanation for living room scene
	private boolean disappear = false;// explanation disappear when player moves
	private boolean inspect = false;// can start inspecting
	private boolean wall, cabinet, painting, table, flower;// items that can be inspected
	private int interm = 8;// some intermediate value for printing out two lines of text
	private boolean keep = false;// also some variable that will only be used once
	private boolean changeD = false;// signals when to change "door" into door
	private int doorx = 242;// x position of image door
	private int doory = 401;// y position of image door
	private boolean lr = false;// when to paint the living room
	private boolean road = false;// when to paint road and bank
	private int roadx = 0;// x position of the image road
	private int countBall = 0;// counter for ball movement
	private int previousx, previousy;// stores the previous position of "I"
	private boolean bank = false;// when to enter bank scene
	private boolean banktype = false;// when to start typing for bank scene
	private boolean clean = false;// cleans the board after certain conversation
	private int flicker = 0;// counter for the flickering effect in the paper slip scene
	// image variables
	private Image meImg, doorImg, door2Img, door3Img, lrImg, roadImg, bankImg, ballImg, bdoorImg, bdoor2Img, bdoor3Img, pslipImg, pslip2Img;
	private int Ix = 51;
	private int Iy = 116;
	
	// this method returns the strings for the type writer effect
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
		meImg = Toolkit.getDefaultToolkit().getImage("Images/I.png");
		doorImg = Toolkit.getDefaultToolkit().getImage("Images/door.png");
		door2Img = Toolkit.getDefaultToolkit().getImage("Images/door 2.png");
		door3Img = Toolkit.getDefaultToolkit().getImage("Images/door 3.png");
		lrImg = Toolkit.getDefaultToolkit().getImage("Images/living room.png");
		roadImg = Toolkit.getDefaultToolkit().getImage("Images/road.png");
		bankImg = Toolkit.getDefaultToolkit().getImage("Images/bank.png");
		ballImg = Toolkit.getDefaultToolkit().getImage("Images/ball.png");
		bdoorImg = Toolkit.getDefaultToolkit().getImage("Images/bank door.png");
		bdoor2Img = Toolkit.getDefaultToolkit().getImage("Images/bank door 2.png");
		bdoor3Img = Toolkit.getDefaultToolkit().getImage("Images/bank door 3.png");
		pslipImg = Toolkit.getDefaultToolkit().getImage("Images/paper slip.png");
		pslip2Img = Toolkit.getDefaultToolkit().getImage("Images/paper slip 2.png");
		
		
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
		text[12] = "'This is the worst place to meet.' I remark.";
		text[13] = "'Or the best place. Remember, it's dark under";
		text[14] = "the light.' My boss says.";
		text[15] = "'Ok, I don't have much time to waste.' He";
		text[16] = "slides a piece of paper into my pocket,";
		text[17] = "'Tomorrow, be on time.'";
		
		
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
		
		if (timer[5]==20) {
			countStart[5] = true;
		}
		if (timer[13]==60) {
			countStart[13] = true;
		}
		if (timer[14]==20) {
			countStart[14] = true;
		}
		if (timer[15]==70) {
			clean = true;
			countStart[15] = true;
		}
		if (timer[16]==20) {
			countStart[16] = true;
		}
		if (timer[17]==60) {
			countStart[17]=true;
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
		x[12] = 50;
		y[12] = 220;
		x[13] = 50;
		y[13] = 300;
		x[14] = 50;
		y[14] = 380;
		x[15] = 50;
		y[15] = 220;
		x[16] = 50;
		y[16] = 300;
		x[17] = 50;
		y[17] = 380;
	}
	
	// check if I and something else collides
	public void collision() {
		if (Ix>=doorx-3&&Ix<=doorx+49&&Iy>=doory&&Iy<=doory+20) {
			collide = true;
		}
		if (Ix>=roadx+1560&&Ix<=roadx+1636&&Iy>=350&&Iy<=478) collideB = true;
		else if (Ix>=roadx+1532&&Ix<=roadx+1656&&Iy>=376&&Iy<=478) collideB = true;
		else if (Ix>=roadx+1528&&Ix<=roadx+1685&&Iy>=414&&Iy<=478) collideB = true;
		else if (Ix>=roadx+1496&&Ix<=roadx+1688&&Iy>=446&&Iy<=478) collideB = true;
		else if (Ix>=roadx+1464&&Ix<=roadx+1718&&Iy>=460&&Iy<=478) collideB = true;
		
//		if (collideB == true) System.out.println("great");
	}
	// defines what we want to happen any time we draw the game
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
		if (counter<=4) {
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
						if(countDoor[0]<20) g.drawImage(doorImg, doorx, doory, 49, 20, null);
						else if(countDoor[0]<40) g.drawImage(door2Img, doorx, doory, 49, 20, null);
						else if (countDoor[0]<60) g.drawImage(door3Img, doorx, doory, 49, 20, null);
					}
					g.drawString(text[i], x[i], y[i]);
				}
			}
			if (counter==4) move = true;
			// if collide, clear screen
			if(collide == true&&painting == false) {
				countDoor[0]++;
				// background color is black
				if (countDoor[0] >= 80) {
					lr = true;
					collide = false;
					doorx = 1200;
					doory = 1200;
				}
			}
		}
		if (lr == true) {
			g.drawImage(lrImg, 0, 0, WIDTH, HEIGHT, null);
			g.drawImage(meImg, Ix, Iy, 17, 31, null);
			explain = true;
			move = false;
		}
		if(counter>=4&&counter<12) {
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
			if (interm<10) {
				count[interm]++;
				g.drawString(type(text[interm], count[interm]), x[interm], y[interm]);
			}
			if (next == true) {
				interm++;
				next = false;
				keep = true;
				if (interm == 10) changeD = true;
			}
			if (keep == true) g.drawString(text[8], x[8], y[8]);
			if (changeD == true) {
				g.drawString("He holds the key to the       of success.", 7, 160);
				doorx = 278;
				doory = 141;
				if (collide == true) countDoor[1]++;
				if(countDoor[1]<20) g.drawImage(doorImg, doorx, doory, 49, 20, null);
				else if(countDoor[1]<40) g.drawImage(door2Img, doorx, doory, 49, 20, null);
				else if (countDoor[1]<60) g.drawImage(door3Img, doorx, doory, 49, 20, null);
				if(countDoor[1]==80) {
					road = true;
					Ix = 280;
					Iy = 350;
				}
			}
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
		if (road == true) {
			countBall++;
			g.drawImage(roadImg, roadx, 0, 1214, HEIGHT, null);
			g.drawImage(bankImg, roadx+1214, 0, 660, HEIGHT, null);
			if (countBall%100<25) g.drawImage(ballImg, roadx+975, 296, 56, 20, null);
			else if (countBall%100<50) g.drawImage(ballImg, roadx+915, 296, 56, 20, null);
			else if (countBall%100<75) g.drawImage(ballImg, roadx+855, 296, 56, 20, null);
			else g.drawImage(ballImg, roadx+915, 296, 56, 20, null);
			g.drawImage(meImg, Ix, Iy, 17, 31, null);
			if (collideB == true) countDoor[2]++;
			if(countDoor[2]<50) g.drawImage(bdoorImg, roadx+1454, 358, 300, 144, null);
			else if(countDoor[2]<100) g.drawImage(bdoor2Img, roadx+1454, 358, 300, 144, null);
			else if (countDoor[2]<150) g.drawImage(bdoor3Img, roadx+1454, 358, 300, 144, null);
			if(countDoor[2]==200) {
				bank = true;
				banktype = true;
			}
		}
		if (bank == true) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
		}
		if (banktype==true) {
			counter = 12;
			countStart[counter] = true;
			banktype = false;
		}
		g.setFont(f);
		g.setColor(Color.white);
		if (counter>=12&&counter<18) {
			if (clean==true) {
				g.setColor(Color.black);
				g.fillRect(0, 0, WIDTH, HEIGHT);
			}
			if (countStart[counter]==true) {
				g.setFont(f);
				g.setColor(Color.white);
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
			if (clean==false) {
				for (int i = 12; i<counter; i++) {
					g.drawString(text[i], x[i], y[i]);
				}
			}
			else {
				for (int i = 15; i<counter; i++) {
					g.setFont(f);
					g.setColor(Color.white);
					g.drawString(text[i], x[i], y[i]);
				}
			}
		}
		if (counter==18) {
			flicker++;
			if (flicker%90<45) g.drawImage(pslipImg, 0, 0, WIDTH, HEIGHT, null);
			else g.drawImage(pslip2Img, 0, 0, WIDTH, HEIGHT, null);
			Ix = 233;
			Iy = 285;
			g.drawImage(meImg, Ix, Iy, 17, 31, null);
			move = true;
		}
	}

	// defines what we want to happen if a keyboard button has 
	// been pressed - you need to complete this
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		// changes paddle direction based on what button is pressed
		if (keyCode == KeyEvent.VK_DOWN && move==true) {
			if (Iy>=HEIGHT-92&&Iy!=HEIGHT-60) {
				previousy = Iy;
				Iy = HEIGHT-60;
			}
			else if (Iy == 0) Iy = previousy;
			else if (Iy<=HEIGHT-92) {
				Iy+=32; 
				if(flicker>0) System.out.println("down");
			}
			else Iy = HEIGHT-60;
			if (counter==6) disappear = true;
			if (wall==true) {
				wall = false;
				count[6]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[7]=0;
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
			if (Iy<=32&&Iy!=0) {
				previousy = Iy;
				Iy = 0;
			}
			else if (Iy == HEIGHT) Iy = previousy;
			else if (Iy>=32) Iy-=32;
			if (counter==6) disappear = true;
			if (wall==true) {
				wall = false;
				count[6]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[7]=0;
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
			if (Ix<=32&&Ix!=0) {
				previousx = Ix;
				Ix = 0;
			}
			else if (Ix == WIDTH) Ix = previousx;
			else if (road == false&&Ix>32)Ix-=32;
			else if (Ix == 0) Ix = 0;
			else if (roadx>=0&&Ix>=0) Ix-=32;
			else if (Ix>WIDTH/2) Ix-=32;
			else if (road == true&&roadx<0) roadx+=32;
			if (counter==6) disappear = true;
			if (wall==true) {
				wall = false;
				count[6]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[7]=0;
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
			if (Ix>=WIDTH-49&&Ix!=WIDTH-17) {
				previousx = Ix;
				Ix = WIDTH-17;
			}
			else if (Ix == 0) {
				Ix = previousx;
			}
			else if ((road == false||roadx<=-1274)&&(Ix<WIDTH-49))Ix+=32;
			else if (Ix<WIDTH/2) Ix+=32;
			else if (road == true&&Ix<WIDTH-49) roadx-=32;
			if (counter==6) disappear = true;
			if (wall==true) {
				wall = false;
				count[6]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[7]=0;
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
			if ((Ix<=192&&Ix>=0&&Iy>=0&&Iy<=84)||(Ix<=600&&Ix>=408&&Iy>=0&&Iy<=64)) {
				wall = true;
				if (painting==true) {
					count[8]=0;
					count[9]=0;
					interm = 8;
					keep = false;
					changeD = false;
					painting = false;
				}
			}
			else if (Ix>=192&&Ix<=408&&Iy>=52&&Iy<=84) {
				cabinet = true;
				if (painting==true) {
					count[8]=0;
					count[9]=0;
					interm = 8;
					keep = false;
					changeD = false;
					painting = false;
				}
			}
			else if (Ix>=467&&Ix<=531&&Iy>=84&&Iy<=180) painting = true;
			else if (Ix>=403&&Ix<=467&&Iy==340) {
				flower = true;
				if (painting==true) {
					count[8]=0;
					count[9]=0;
					interm = 8;
					keep = false;
					changeD = false;
					painting = false;
				}
			}
			else if (Ix>=403&&Ix<=563&&Iy>=372&&Iy<=436) {
				table = true;
				if (painting==true) {
					count[8]=0;
					count[9]=0;
					interm = 8;
					keep = false;
					changeD = false;
					painting = false;
				}
			}
		}
		
		if (keyCode == KeyEvent.VK_1) {
			System.out.println(Ix);
			System.out.println(Iy);
			System.out.println(move);
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
			repaint();
			
			setup();
			collision();
			
			
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