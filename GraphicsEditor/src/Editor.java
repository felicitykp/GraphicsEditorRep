import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import GEditor.Circle;
import GEditor.Rect;
import GEditor.Shape;

public class Editor{
	
	//VARIABLES
	public final int WIDTH = 600, HEIGHT = 600;
	public final int BUTTON_HEIGHT = 75;
	public int mouseX1, mouseY1;
	public Color currColor = Color.BLUE;
	public LinkedList<Shape> shapes = new LinkedList<Shape>();
	public boolean circleClick = false, rectClick = false;

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
				circleClick = true;
				rectClick = false;
				}});		
		JButton rectButton = new JButton("Rectangle");
		rectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rectClick = true;
				circleClick = false;
				}});	
		
		//set up Button Panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.LIGHT_GRAY);
		buttonPanel.setPreferredSize(new Dimension(WIDTH, BUTTON_HEIGHT));
		buttonPanel.add(circleButton);
		buttonPanel.add(rectButton);
		panel.add(buttonPanel);
		
		//set up Drawing Panel
		JPanel drawPanel = new JPanel(){
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
						mouseX1 = e.getX();
						mouseY1 = e.getY();
						if(circleClick == true) {
							shapes.add(new Circle(mouseX1, mouseY1, 100, 100, currColor));
							frame.getContentPane().repaint();
						} else if (rectClick == true) {
							shapes.add(new Rect(mouseX1, mouseY1, 100, 100, currColor));
							frame.getContentPane().repaint();
						}
						
					}
					
					public void mouseReleased(MouseEvent e) {}
					public void mouseClicked(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
				});
	}
	
	//MAIN
	public static void main(String[] args) {
		new Editor();
	}
	
	
}
