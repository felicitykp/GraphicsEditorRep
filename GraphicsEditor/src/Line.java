import java.awt.Color;
import java.awt.Graphics;

import GEditor.Shape;

public class Line extends Shape{

	//CONSTRUCTOR
	public Line(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public Shape copy() {
		return null;
	}

	
	public void draw(Graphics g) {
		g.setColor(c);
		g.drawLine(x, y, width, height);
	}

	public boolean isOn(int x, int y) {
		return false;
	}

	public void resize(int newX, int newY, int initialX, int initialY) {
		
		width = newX;
		height = newY;
		x = initialX;
		y = initialY;
		
	}

}
