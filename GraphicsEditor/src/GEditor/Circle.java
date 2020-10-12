package GEditor;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape{

	//CONSTRUCTOR
	public Circle(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}


	public Shape copy() {
		return null;
	}

	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x, y, width, height);
	}

	public boolean isOn(int x, int y) {
		return false;
	}

	public void resize(int x1, int y1, int x2, int y2) {
		
	}

}
