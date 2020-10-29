package GEditor;
import java.awt.Color;
import java.awt.Graphics;

public class Text extends Shape {

	String input;
	
	//CONSTRUCTOR
	public Text(int x, int y, int w, int h, Color c, String i) {
		super(x, y, w, h, c);
		input = i;
	}

	public Shape copy() {
		return null;
	}

	public void draw(Graphics g) {
		g.setColor(c);
		g.drawString(input, x, y);
		
	}

	public boolean isOn(int x, int y) {
		return false;
	}

	public void resize(int newX, int newY, int initialX, int initialY) {
		//no thank u
	}

}
