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

public class Bank extends Type{
	
	private int counter = 0;
	private boolean[] countStart = new boolean[7];
	private int[] count = new int[7];// counter for type writer effect 
	private int[] timer  = new int[7];// timer in between the strings
	private String[] text = new String[6];
	private int[] x = new int[6];// x positions of the strings
	private int[] y = new int[6];// y positions of the strings
	private boolean clean = false;
	
	WordRunner main;
	
	public Bank(WordRunner m) {
		main = m;
	}
	
	public void initialize() {
		text[0] = "'This is the worst place to meet.' I remark.";
		text[1] = "'Or the best place. Remember, it's dark under";
		text[2] = "the light.' My boss says.";
		text[3] = "'Ok, I don't have much time to waste.' He";
		text[4] = "slides a piece of paper into my pocket,";
		text[5] = "'Tomorrow, be on time.'";
		
		x[0] = 50;
		y[0] = 220;
		x[1] = 50;
		y[1] = 300;
		x[2] = 50;
		y[2] = 380;
		x[3] = 50;
		y[3] = 220;
		x[4] = 50;
		y[4] = 300;
		x[5] = 50;
		y[5] = 380;
		
		counter = 0;
		clean = false;
		
		for(int i = 0; i<7; i++) {
			countStart[i] = false;
			count[i] = 0;
			timer[i] = 0;
		}
	}
	
	public void setup() {
		countStart[0] = true;
		
		if (timer[1]==60) {
			countStart[1] = true;
		}
		if (timer[2]==20) {
			countStart[2] = true;
		}
		if (timer[3]==70) {
			clean = true;
			countStart[3] = true;
		}
		if (timer[4]==20) {
			countStart[4] = true;
		}
		if (timer[5]==60) {
			countStart[5]=true;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, main.WIDTH, main.HEIGHT);
		Font f = new Font("Chalkboard", Font.PLAIN, 24);
		g.setFont(f);
		g.setColor(Color.white);
		if (counter>=0&&counter<6) {
			if (clean==true) {
				g.setColor(Color.black);
				g.fillRect(0, 0, main.WIDTH, main.HEIGHT);
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
			if (getNext()) {
				counter++;
				setNext(false);
			}
			if (clean==false) {
				for (int i = 0; i<counter; i++) {
					g.drawString(text[i], x[i], y[i]);
				}
			}
			else {
				for (int i = 3; i<counter; i++) {
					g.setFont(f);
					g.setColor(Color.white);
					g.drawString(text[i], x[i], y[i]);
				}
			}
		}
		if (counter==6) {
			main.index++;
			main.scene[main.index].initialize();
		}
	}
}
