package GEditor;
import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Shape{

	int x3, y3;
	
	public Triangle(int x, int y, int w, int h, Color c) {
		super(x, y, w, h, c);
	}

	public Shape copy() {
		return null;
	}

	
	public void draw(Graphics g) {
		g.setColor(c);
		
		if(x > width && y > height) { //2nd quad
			x3 = (int) ((x + width + (Math.sqrt(3))*(height - y)) / 2);
			y3 = (int) ((y + height - (Math.sqrt(3))*(width - x)) / 2);
		} else if (x < width && y < height) { //4th quad
			x3 = (int) ((x + width - (Math.sqrt(3))*(y - height)) / 2);
			y3 = (int) ((y + height + (Math.sqrt(3))*(x - width)) / 2);
		} else if (x > width && y < height){ //3rd quad
			x3 = (int) ((x + width - (Math.sqrt(3))*(y - height)) / 2);
			y3 = (int) ((y + height + (Math.sqrt(3))*(x - width)) / 2);
		} else if (x < width && y > height) { //1st quad
			x3 = (int) ((x + width + (Math.sqrt(3))*(height - y)) / 2);
			y3 = (int) ((y + height - (Math.sqrt(3))*(width - x)) / 2);
		}
		
		//draw triangle
		int[] xpoints = {x, width, x3};
		int[] ypoints = {y, height, y3};
		g.fillPolygon(xpoints, ypoints, 3);
		
	}

	public boolean isOn(int xC, int yC) {
		
		//calculate area of whole triangle
		double A = area (x, y, width, height, x3, y3);
		
		//calculate area of smaller area
		double A1 = area (xC, yC, width, height, x3, y3);
		double A2 = area (x, y, xC, yC, x3, y3);
		double A3 = area (x, y, width, height, xC, yC);
	
		//check if smaller areas add up to big one
		if (A1 + A2 + A3 == A) {
			return true;
		} else {
			return false;
		}
	}
	
	private double area(int x1, int y1, int x2, int y2, int x3, int y3) {
		return Math.abs((x1*(y2-y3) + x2*(y3-y1) + x3*(y1-y2)) / 2);
	}

	public void resize(int newX, int newY, int initialX, int initialY) {
		x = initialX;
		y = initialY;
		width = newX;
		height = newY;
	}

}
