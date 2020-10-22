import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import GEditor.Circle;
import GEditor.Rect;
import GEditor.Shape;

public class Editor{
	
	//VARIABLES
	public final int WIDTH = 800, HEIGHT = 600;
	public final int BUTTON_HEIGHT = 75;
	public int mouseX, mouseY;
	public Color currColor = Color.BLUE;
	public LinkedList<Shape> shapes = new LinkedList<Shape>();
	public HashMap<String, Boolean> myMap = new HashMap<String, Boolean>();
	public JColorChooser jc = new JColorChooser();
	

	//CONSTRUCTOR
	public Editor() {
		
		//set up JPanel
		JPanel panel = new JPanel();
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 5), 
				"Graphics Editor", TitledBorder.CENTER, TitledBorder.TOP, new Font("Ariel",Font.BOLD,16), Color.BLACK));
		
		//create buttons
		JButton circleButton = new JButton("Circle");
		circleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("circle", true);
			}});		
		JButton rectButton = new JButton("Rectangle");
		rectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("rect", true);
			}});	
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("delete", true);
			}});	
		JButton colorButton = new JButton("Current Color");
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("color", true);
				currColor = jc.showDialog(null, "Select New Color", null);
			}});
		JButton forwardButton = new JButton("Front");
		forwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("front", true);
			}});
		
		
		//set up Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setPreferredSize(new Dimension(WIDTH, BUTTON_HEIGHT));
		buttonPanel.add(circleButton);
		buttonPanel.add(rectButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(colorButton);
		buttonPanel.add(forwardButton);
		panel.add(buttonPanel);
		
		//set up Drawing Panel
		JPanel drawPanel = new JPanel(){ //override how the drawPanel paints
			public void paint(Graphics g) {
				for(int i = 0; i < shapes.size(); i++) {
					shapes.get(i).draw(g);
				}
			}
		};
		drawPanel.setBackground(Color.WHITE);
		drawPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT-BUTTON_HEIGHT));
		panel.add(drawPanel);
		
		//main container
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		panel.setFocusable(true);
		
		//add mouse listener
		drawPanel.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
		
				//paints a circle
				if(myMap.get("circle") == true) {
					mouseX = e.getX();
					mouseY = e.getY();
					shapes.add(new Circle(mouseX, mouseY, 0, 0, currColor));
				}
					
				//paints a rect
				else if (myMap.get("rect") == true) { 
					mouseX = e.getX();
					mouseY = e.getY();
					shapes.add(new Rect(mouseX, mouseY, 0, 0, currColor));
				}
				
				//deletes a shape
				else if(myMap.get("delete") == true) {
					for(int i = 0; i < shapes.size(); i++) {
						if(shapes.get(i).isOn(e.getX(), e.getY()) == true) {
							shapes.remove(i);
							break;
						}
					}
				}
				
				//moves a shape forward
				else if (myMap.get("front") == true) {
					for(int i = 0; i < shapes.size(); i++) {
						if(shapes.get(i).isOn(e.getX(), e.getY()) == true) {
							if(shapes.get(i+1) != null) {
								Shape temp = shapes.get(i);
								shapes.remove(i);
								shapes.add(temp);
							}
						}
					}
				}
				
				//repaint
				frame.getContentPane().repaint();
			}
			public void mouseReleased(MouseEvent e) {}
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		
		//add mouse motion listener
		drawPanel.addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
			     //resize a rect
				if(myMap.get("rect") == true) {
					shapes.get(shapes.size() - 1).resize(e.getX(), e.getY(), mouseX, mouseY);
				}
				
				//resize a circle
				if(myMap.get("circle") == true) {
					shapes.get(shapes.size() - 1).resize(e.getX(), e.getY(), mouseX, mouseY);
				}
				
				//repaint
				frame.getContentPane().repaint();
		    }
			
			public void mouseMoved(MouseEvent e) {}   
		});
		
			
			
	}
	
	//METHODS
	public void setAllFalse() {
		myMap.put("circle", false);
		myMap.put("rect", false);
		myMap.put("delete", false);
		myMap.put("color", false);
		myMap.put("front", false);
	}
	
	//MAIN
	public static void main(String[] args) {
		new Editor();
	}
	
	
}
