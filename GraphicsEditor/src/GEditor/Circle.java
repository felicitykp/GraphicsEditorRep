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

	public boolean isOn(int xCurr, int yCurr) {
		boolean xCheck = false;
		boolean yCheck = false;
		
		//check x
		if(xCurr > x && xCurr < x+width) {
			xCheck = true;
		}
		
		//check y
		if(yCurr > y && yCurr < y+height) {
			yCheck = true;
		}
		
		//confirm if both true
		if(xCheck == true && yCheck == true) {
			return true;
		} else {
			return false;
		}
		
	}

	public void resize(int newX, int newY, int initialX, int initialY) {
		
		//check X
		if(newX > x) {
			width = (int) Math.sqrt((newX - initialX)*(newX - initialX) + (newY - initialY)*(newY - initialY));
			height = width;
		} else {
			width = (int) Math.sqrt((initialX - newX)*(initialX - newX) + (initialY - newY)*(initialY - newY));
			height = width;
			x = newX;
		}
		
		//check Y
		if(newY > y) {
			height = (int) Math.sqrt((newX - initialX)*(newX - initialX) + (newY - initialY)*(newY - initialY));
			width = height;
		} else {
			height = (int) Math.sqrt((initialX - newX)*(initialX - newX) + (initialY - newY)*(initialY - newY));
			width = height;
			y = newY;
		}
		
		
		
		
		
	}

}
