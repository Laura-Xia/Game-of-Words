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

public class LivingRoom extends Type{
	
	private String[] text = new String[8];// the texts
	private int[] count = new int[9];// counter for type writer effect 
	private boolean[] countStart = new boolean[9];// booleans to decide if the next string can be printed
	private int counter = 0;// count which string the program is on
	private int[] timer  = new int[9];// timer in between the strings
	private int[] x = new int[8];// x positions of the strings
	private int[] y = new int[8];// y positions of the strings
	private Image meImg, lrImg, doorImg, door2Img, door3Img;
	private boolean explain = false;// signify when to print the explanation for living room scene
	private boolean disappear = false;// explanation disappear when player moves
	private boolean inspect = false;// can start inspecting
	private boolean move = false;
	private boolean wall, cabinet, painting, table, flower;// items that can be inspected
	private int interm = 4;// some intermediate value for printing out two lines of text
	private boolean keep = false;// also some variable that will only be used once
	private boolean changeD = false;// signals when to change "door" into door
	private int doorx = 242;// x position of image door
	private int doory = 401;// y position of image door
	private int countDoor = 0;// counter for the door image
	
	WordRunner main;
	
	public LivingRoom(WordRunner m) {
		main = m;
	}
	
	public void initialize() {
		// setup all pictures
		meImg = Toolkit.getDefaultToolkit().getImage("Images/I.png");
		doorImg = Toolkit.getDefaultToolkit().getImage("Images/door.png");
		door2Img = Toolkit.getDefaultToolkit().getImage("Images/door 2.png");
		door3Img = Toolkit.getDefaultToolkit().getImage("Images/door 3.png");
		lrImg = Toolkit.getDefaultToolkit().getImage("Images/living room.png");
		// setup all strings
		text[0] = "I am in the living room.";
		text[1] = "Move around and hit SPACE to inspect.";
		text[2] = "Plain white wall with no stains of any kind.";
		text[3] = "My collection of liquors inside.";
		text[4] = "Painting of my boss. It's time to meet him.";
		text[5] = "He holds the key to the door of success.";
		text[6] = "Orchids. They smell nice.";
		text[7] = "Table made from aromatic fine wood.";
		// setup all positions for strings
		x[0] = 20;
		y[0] = 130;
		x[1] = 20;
		y[1] = 160;
		x[2] = 7;
		y[2] = 130;
		x[3] = 15;
		y[3] = 130;
		x[4] = 7;
		y[4] = 130;
		x[5] = 7;
		y[5] = 160;
		x[6] = 20;
		y[6] = 130;
		x[7] = 20;
		y[7] = 130;
		// reset these variables every time the scene is ran 
		counter = 0;
		explain = false;
		disappear = false;
		inspect = false;
		move = false;
		interm = 4;
		keep = false;
		changeD = false;
		doorx = 242;
		doory = 401;
		countDoor = 0;
		
		for(int i = 0; i<9; i++) {
			countStart[i] = false;
			count[i] = 0;
			timer[i] = 0;
		}
		wall = false;
		cabinet = false;
		painting = false;
		table = false;
		flower = false;
	}
	
	public void setup() {
		// setup countStart variables for typewriter effect
		if (explain == true) {
			countStart[0] = true;
		}
		if (timer[1]==20) {
			countStart[1] = true;
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
		// set font
		Font f = new Font("Chalkboard", Font.PLAIN, 24);
		g.setFont(f);
		g.setColor(Color.white);
		g.setColor(Color.white);
		// draws main character and scene
		g.drawImage(lrImg, 0, 0, main.WIDTH, main.HEIGHT, null);
		g.drawImage(meImg, main.Ix, main.Iy, 17, 31, null);
		// start giving instructions
		explain = true;
		// print out explanations for each object when applicable
		if(counter>=0&&counter<8) {
			// typewriter effect for strings
			if (countStart[counter]==true) {
				count[counter]++;
				g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
			}
			else {
				// pause between first and second string
				timer[counter]++;
			}
			// goes to print out next string
			if (getNext()==true) {
				counter++;
				setNext(false);
			}
			// if instructions are all printed out, allow player to move and inspect
			if(counter>1) {
				move = true;
				inspect = true;
			}
			// if player moves, explanation for object disappears
			for (int i = 0; i<counter; i++) {
				if (!disappear) g.drawString(text[i], x[i], y[i]);
			}
		}
		// print out explanation for the wall
		if(wall==true) {
			counter = 2;
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
		// print out explanation for the cabinet
		else if(cabinet==true) {
			counter = 3;
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
		// print out explanation for the painting
		else if(painting==true) {
			setNext(false);
			if (interm<6) {
				count[interm]++;
				g.drawString(type(text[interm], count[interm]), x[interm], y[interm]);
			}
			// the explanation has two lines, so next is needed
			if (getNext() == true) {
				interm++;
				setNext(false);
				// in order to allow player to move to next scene, the string has to stay put.
				keep = true;
				// change "door" from the string to one where the player can exit from
				if (interm == 6) changeD = true;
			}
			if (keep == true) g.drawString(text[4], x[4], y[4]);
			if (changeD == true) {
				// erase "door" from the string
				g.drawString("He holds the key to the       of success.", 7, 160);
				// draws the exit-able door
				doorx = 278;
				doory = 141;
				// after collision, increase countDoor to change "door"'s appearance
				if (collision() == true) countDoor++;
				if(countDoor<20) g.drawImage(doorImg, doorx, doory, 49, 20, null);
				else if(countDoor<40) g.drawImage(door2Img, doorx, doory, 49, 20, null);
				else if (countDoor<60) g.drawImage(door3Img, doorx, doory, 49, 20, null);
				// after a while, goes to next scene.
				if(countDoor==80) {
					main.index++;
					main.scene[main.index].initialize();
				}
			}
		}
		// print out explanation for the flower
		else if(flower==true) {
			counter = 6;
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
		// print out explanation for the table
		else if(table==true) {
			counter = 7;
			count[counter]++;
			g.drawString(type(text[counter], count[counter]), x[counter], y[counter]);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		// for all move method, if the player moves away, the explanation strings should disappear
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
			if (counter==2) disappear = true;
			if (wall==true) {
				wall = false;
				count[2]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[3]=0;
			}
			if (flower==true) {
				flower = false;
				count[6]=0;
			}
			if (table==true) {
				table = false;
				count[7]=0;
			}
		}
			// fill this in
		// move method for moving up
		if (keyCode == KeyEvent.VK_UP && move == true) {
			if (main.Iy<=32&&main.Iy!=0) {
				main.previousy = main.Iy;
				main.Iy = 0;
			}
			else if (main.Iy == main.HEIGHT) main.Iy = main.previousy;
			else if (main.Iy>=32) main.Iy-=32;
			if (counter==2) disappear = true;
			if (wall==true) {
				wall = false;
				count[2]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[3]=0;
			}
			if (flower==true) {
				flower = false;
				count[6]=0;
			}
			if (table==true) {
				table = false;
				count[7]=0;
			}
		} 
		// move method for moving left
		if (keyCode == KeyEvent.VK_LEFT && move==true) {
			if (main.Ix<=32&&main.Ix!=0) {
				main.previousx = main.Ix;
				main.Ix = 0;
			}
			else if (main.Ix == main.WIDTH) main.Ix = main.previousx;
			else if (main.Ix>32)main.Ix-=32;
			else if (main.Ix == 0) main.Ix = 0;
			if (counter==2) disappear = true;
			if (wall==true) {
				wall = false;
				count[2]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[3]=0;
			}
			if (flower==true) {
				flower = false;
				count[6]=0;
			}
			if (table==true) {
				table = false;
				count[7]=0;
			}
		}
			// fill this in
		// move method for moving right
		if (keyCode == KeyEvent.VK_RIGHT && move == true) {
			if (main.Ix>=main.WIDTH-49&&main.Ix!=main.WIDTH-17) {
				main.previousx = main.Ix;
				main.Ix = main.WIDTH-17;
			}
			else if (main.Ix == 0) {
				main.Ix = main.previousx;
			}
			else if (main.Ix<main.WIDTH-49)main.Ix+=32;
			if (counter==2) disappear = true;
			if (wall==true) {
				wall = false;
				count[2]=0;
			}
			if (cabinet==true) {
				cabinet = false;
				count[3]=0;
			}
			if (flower==true) {
				flower = false;
				count[6]=0;
			}
			if (table==true) {
				table = false;
				count[7]=0;
			}
		} 
		// hit space to inspect, explanations will pop out
		if (keyCode == KeyEvent.VK_SPACE && inspect == true) {
			if ((main.Ix<=192&&main.Ix>=0&&main.Iy>=0&&main.Iy<=84)||(main.Ix<=600&&main.Ix>=408&&main.Iy>=0&&main.Iy<=64)) {
				wall = true;
				if (painting==true) {
					count[4]=0;
					count[5]=0;
					interm = 4;
					keep = false;
					changeD = false;
					painting = false;
				}
			}
			else if (main.Ix>=192&&main.Ix<=408&&main.Iy>=52&&main.Iy<=84) {
				cabinet = true;
				if (painting==true) {
					count[4]=0;
					count[5]=0;
					interm = 4;
					keep = false;
					changeD = false;
					painting = false;
				}
			}
			else if (main.Ix>=467&&main.Ix<=531&&main.Iy>=84&&main.Iy<=180) painting = true;
			else if (main.Ix>=403&&main.Ix<=467&&main.Iy==340) {
				flower = true;
				if (painting==true) {
					count[4]=0;
					count[5]=0;
					interm = 4;
					keep = false;
					changeD = false;
					painting = false;
				}
			}
			else if (main.Ix>=403&&main.Ix<=563&&main.Iy>=372&&main.Iy<=436) {
				table = true;
				if (painting==true) {
					count[4]=0;
					count[5]=0;
					interm = 4;
					keep = false;
					changeD = false;
					painting = false;
				}
			}
		}
	}
}
