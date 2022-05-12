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

public class Road extends Type{
	private int countBall = 0;// counter for ball movement
	private Image roadImg, bankImg, ballImg, bdoorImg, bdoor2Img, bdoor3Img, meImg;
	private int roadx = 0;// x position of the image road
	private boolean bank = false;// when to enter bank scene
	private boolean banktype = false;// when to start typing for bank scene
	private int countDoor = 0;// counter for the door image
	private int Ix = 280;
	private int Iy = 350;
	WordRunner main;
	
	public Road(WordRunner m) {
		main = m;
	}
	
	public boolean collision() {
		if (Ix>=roadx+1560&&Ix<=roadx+1636&&Iy>=350&&Iy<=478) return true;
		else if (Ix>=roadx+1532&&Ix<=roadx+1656&&Iy>=376&&Iy<=478) return true;
		else if (Ix>=roadx+1528&&Ix<=roadx+1685&&Iy>=414&&Iy<=478) return true;
		else if (Ix>=roadx+1496&&Ix<=roadx+1688&&Iy>=446&&Iy<=478) return true;
		else if (Ix>=roadx+1464&&Ix<=roadx+1718&&Iy>=460&&Iy<=478) return true;
		return false;
	}
	
	public void initialize() {
		roadImg = Toolkit.getDefaultToolkit().getImage("Images/road.png");
		bankImg = Toolkit.getDefaultToolkit().getImage("Images/bank.png");
		ballImg = Toolkit.getDefaultToolkit().getImage("Images/ball.png");
		bdoorImg = Toolkit.getDefaultToolkit().getImage("Images/bank door.png");
		bdoor2Img = Toolkit.getDefaultToolkit().getImage("Images/bank door 2.png");
		bdoor3Img = Toolkit.getDefaultToolkit().getImage("Images/bank door 3.png");
		meImg = Toolkit.getDefaultToolkit().getImage("Images/I.png");
	}
	
	public void draw(Graphics g) {
		countBall++;
		g.drawImage(roadImg, roadx, 0, 1214, main.HEIGHT, null);
		g.drawImage(bankImg, roadx+1214, 0, 660, main.HEIGHT, null);
		if (countBall%100<25) g.drawImage(ballImg, roadx+975, 296, 56, 20, null);
		else if (countBall%100<50) g.drawImage(ballImg, roadx+915, 296, 56, 20, null);
		else if (countBall%100<75) g.drawImage(ballImg, roadx+855, 296, 56, 20, null);
		else g.drawImage(ballImg, roadx+915, 296, 56, 20, null);
		g.drawImage(meImg, Ix, Iy, 17, 31, null);
		if (collision()) countDoor++;
		if(countDoor<50) g.drawImage(bdoorImg, roadx+1454, 358, 300, 144, null);
		else if(countDoor<100) g.drawImage(bdoor2Img, roadx+1454, 358, 300, 144, null);
		else if (countDoor<150) g.drawImage(bdoor3Img, roadx+1454, 358, 300, 144, null);
		if(countDoor==200) {
			main.index++;
		}
	}
	
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		// changes paddle direction based on what button is pressed
		if (keyCode == KeyEvent.VK_DOWN) {
			if (Iy>=main.HEIGHT-92&&Iy!=main.HEIGHT-60) {
				main.previousy = Iy;
				Iy = main.HEIGHT-60;
			}
			else if (Iy == 0) Iy = main.previousy;
			else if (Iy<=main.HEIGHT-92) {
				Iy+=32;
			}
			else Iy = main.HEIGHT-60;
		}
			// fill this in
		
		if (keyCode == KeyEvent.VK_UP) {
			if (Iy<=32&&Iy!=0) {
				main.previousy = Iy;
				Iy = 0;
			}
			else if (Iy == main.HEIGHT) Iy = main.previousy;
			else if (Iy>=32) Iy-=32;
		} 
		
		if (keyCode == KeyEvent.VK_LEFT) {
			if (Ix<=32&&Ix!=0) {
				main.previousx = Ix;
				Ix = 0;
			}
			else if (Ix == main.WIDTH) Ix = main.previousx;
			else if (Ix == 0) Ix = 0;
			else if (roadx>=0&&Ix>=0) Ix-=32;
			else if (Ix>main.WIDTH/2) Ix-=32;
			else if (roadx<0) roadx+=32;
			
		}
			// fill this in
		
		if (keyCode == KeyEvent.VK_RIGHT) {
			if (Ix>=main.WIDTH-49&&Ix!=main.WIDTH-17) {
				main.previousx = Ix;
				Ix = main.WIDTH-17;
			}
			else if (Ix == 0) {
				Ix = main.previousx;
			}
			else if ((roadx<=-1274)&&(Ix<main.WIDTH-49))Ix+=32;
			else if (Ix<main.WIDTH/2) Ix+=32;
			else if (Ix<main.WIDTH-49) roadx-=32;
		} 
	}
}
