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
	private Image meImg, pslipImg, pslip2Img;
	
	WordRunner main;
	
	public Paper(WordRunner m) {
		main = m;
	}
	
	public void initialize() {
		meImg = Toolkit.getDefaultToolkit().getImage("Images/I.png");
		pslipImg = Toolkit.getDefaultToolkit().getImage("Images/paper slip.png");
		pslip2Img = Toolkit.getDefaultToolkit().getImage("Images/paper slip 2.png");
	}
	
	public void draw(Graphics g) {
		flicker++;
		if (flicker%90<45) g.drawImage(pslipImg, 0, 0, main.WIDTH, main.HEIGHT, null);
		else g.drawImage(pslip2Img, 0, 0, main.WIDTH, main.HEIGHT, null);
		g.drawImage(meImg, Ix, Iy, 17, 31, null);
	}
}
