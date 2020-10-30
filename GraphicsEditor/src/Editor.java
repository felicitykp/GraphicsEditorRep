import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import GEditor.Circle;
import GEditor.Line;
import GEditor.Oval;
import GEditor.Rect;
import GEditor.Shape;
import GEditor.Text;
import GEditor.Triangle;

public class Editor{
	
	//PUBLIC VARIABLES
	public final int WIDTH = 750, HEIGHT = 700;
	public final int BUTTON_HEIGHT = 100;
	public final int JTEXT_WIDTH = 60, JTEXT_HEIGHT = 25;
	public int mouseX, mouseY;
	public Color currColor = Color.BLUE;
	public ArrayList<Shape> shapes = new ArrayList<Shape>();
	public HashMap<String, Boolean> myMap = new HashMap<String, Boolean>();
	public JColorChooser jc = new JColorChooser();
	

	//CONSTRUCTOR
	public Editor() {
		
		//set up JPanel
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
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
		JButton triButton = new JButton("Triangle");
		triButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("tri", true);
			}});
		JButton ovalButton = new JButton("Oval");
		ovalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("oval", true);
			}});
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("delete", true);
			}});	
		JButton undoButton = new JButton("Undo");
			//action listener later bc i have to make frame first
		JButton colorButton = new JButton("Current Color");
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currColor = jc.showDialog(null, "Select New Color", null); //this calls the color dialog
			}});
		JButton forwardButton = new JButton("Front");
		forwardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("front", true);
			}});
		JButton lineButton = new JButton("Line");
		lineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("line", true);
			}});
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("back", true);
			}});
		JButton textButton = new JButton("Text");
		textButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				myMap.put("text", true);
			}});
		
		//create text fields
		JLabel stringPrompt = new JLabel();
		stringPrompt.setText("Text: ");
		stringPrompt.setPreferredSize(new Dimension(JTEXT_WIDTH, JTEXT_HEIGHT));
		
		JTextField stringInput = new JTextField();
		stringInput.setPreferredSize(new Dimension(JTEXT_WIDTH, JTEXT_HEIGHT));

		//set up Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setPreferredSize(new Dimension(WIDTH, BUTTON_HEIGHT));
			//first set of items
			buttonPanel.add(circleButton);
			buttonPanel.add(rectButton);
			buttonPanel.add(lineButton);
			buttonPanel.add(triButton);
			buttonPanel.add(ovalButton);
			//second set of items
			buttonPanel.add(deleteButton);
			buttonPanel.add(undoButton);
			buttonPanel.add(forwardButton);
			buttonPanel.add(backButton);
			//third set of items
			buttonPanel.add(textButton);
			buttonPanel.add(stringPrompt);
			buttonPanel.add(stringInput);
			buttonPanel.add(colorButton);
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
		
		//make undo button functional
		undoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllFalse();
				shapes.remove(shapes.size()-1); //deletes last shape in the list
				frame.getContentPane().repaint();
			}});
		
		//add mouse listener
		drawPanel.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent e) {
		
				//sets up circle shape
				if (myMap.get("circle") == true) {
					mouseX = e.getX();
					mouseY = e.getY();
					shapes.add(new Circle(mouseX, mouseY, 0, 0, currColor));
				}
					
				//set up rect shape
				else if (myMap.get("rect") == true) { 
					mouseX = e.getX();
					mouseY = e.getY();
					shapes.add(new Rect(mouseX, mouseY, 0, 0, currColor));
				}
				
				//set up triangle :)
				else if (myMap.get("tri") == true) {
					mouseX = e.getX();
					mouseY = e.getY();
					shapes.add(new Triangle(mouseX, mouseY, 0, 0, currColor));
				}
				
				//set up oval
				else if (myMap.get("oval") == true) {
					mouseX = e.getX();
					mouseY = e.getY();
					shapes.add(new Oval(mouseX, mouseY, 0, 0, currColor));
				}
				
				//deletes a shape
				else if (myMap.get("delete") == true) {
					for(int i = 0; i < shapes.size(); i++) {
						if(shapes.get(i).isOn(e.getX(), e.getY()) == true) {
							shapes.remove(i);
							break;
						} 
					}
				}
				
				//moves shape to front
				else if (myMap.get("front") == true) {
					for(int i = 0; i < shapes.size(); i++) {
						if(shapes.get(i).isOn(e.getX(), e.getY()) == true) {
							if(shapes.get(i+1) != null) {
								Shape temp = shapes.get(i);
								shapes.remove(i);
								shapes.add(shapes.size(), temp);
								break;
							}
						}
					}
				}
				
				//moves shape back by one
				else if (myMap.get("back") == true) {
					for(int i = 0; i < shapes.size(); i++) {
						if(shapes.get(i).isOn(e.getX(), e.getY()) == true) {
							if(i != 0) {
								Shape temp = shapes.get(i);
								shapes.remove(i);
								shapes.add(i-1, temp);
								break;
							}
						}
					}
				}
				
				//line
				else if(myMap.get("line") == true) {
					mouseX = e.getX();
					mouseY = e.getY();
					shapes.add(new Line(mouseX, mouseY, 0, 0, currColor));
				}
				
				//go into text mode
				else if (myMap.get("text") == true) {
					String text = stringInput.getText();
					shapes.add(new Text(e.getX(), e.getY(), 0, 0, currColor, text));
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
				
				//resize a triangle
				if(myMap.get("tri") == true) {
					shapes.get(shapes.size() - 1).resize(e.getX(), e.getY(), mouseX, mouseY);
				}
				
				//resize an oval
				if(myMap.get("oval") == true) {
					shapes.get(shapes.size() - 1).resize(e.getX(), e.getY(), mouseX, mouseY);
				}
				
				//line
				else if(myMap.get("line") == true) {
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
		myMap.put("front", false);
		myMap.put("line", false);
		myMap.put("text", false);
		myMap.put("back", false);
		myMap.put("tri", false);
		myMap.put("oval", false);
	}
	
	//MAIN
	public static void main(String[] args) {
		new Editor();
	}
	
	
}
