import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Type {
	private boolean next = false;
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
	public boolean getNext() {
		return next;
	}
	public void setNext(boolean n) {
		next = n;
	}
	public void draw(Graphics g) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void initialize() {}
	public void setup() {}
	public void mousePressed(MouseEvent e) {}
}
