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

public class Paper extends Type{
	
	private int flicker = 0;// counter for the flickering effect in the paper slip scene
	private int Ix = 233;
	private int Iy = 285;
	private boolean move = false;
	private Image meImg, pslipImg, pslip2Img, chImg, plImg;
	
	WordRunner main;
	
	public Paper(WordRunner m) {
		main = m;
	}
	
	public void initialize() {
		meImg = Toolkit.getDefaultToolkit().getImage("Images/I.png");
		pslipImg = Toolkit.getDefaultToolkit().getImage("Images/paper slip.png");
		pslip2Img = Toolkit.getDefaultToolkit().getImage("Images/paper slip 2.png");
		chImg = Toolkit.getDefaultToolkit().getImage("Images/chapter.png");
		plImg = Toolkit.getDefaultToolkit().getImage("Images/play.png");
		
		flicker = 0;// counter for the flickering effect in the paper slip scene
		Ix = 233;
		Iy = 285;
		move = false;
	}
	
	public boolean collision() {
		if(Ix<550&&Ix>75&&Iy<505&&Iy>58) return false;
		return true;
	}
	
	public void draw(Graphics g) {
		flicker++;
		if (flicker%90<45) g.drawImage(pslipImg, 0, 0, main.WIDTH, main.HEIGHT, null);
		else g.drawImage(pslip2Img, 0, 0, main.WIDTH, main.HEIGHT, null);
		g.drawImage(meImg, Ix, Iy, 17, 31, null);
		move = true;
		if(collision()) {
			g.drawImage(chImg, 0, 0, main.WIDTH, main.HEIGHT, null);
			Font f = new Font("Chalkboard", Font.PLAIN, 56);
			g.setFont(f);
			g.setColor(Color.white);
			g.drawString("1", 400, 268);
			g.drawImage(plImg, 215, 350, 170, 58, null);
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_DOWN && move==true) {
			if (Iy<=main.HEIGHT-92) {
				Iy+=32;
			}
		}
		if (keyCode == KeyEvent.VK_UP && move == true) {
			if (Iy>=32) Iy-=32;
		}
		if (keyCode == KeyEvent.VK_LEFT && move==true) {
			if (Ix>=32) Ix-=32;
		}
		if (keyCode == KeyEvent.VK_RIGHT && move == true) {
			if (Ix<=main.WIDTH-32) Ix+=32;
		}
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		if (x>215&&x<385&&y>350&&y<408) {
			main.index = 0;
			main.scene[main.index].initialize();
		}
	}
}
