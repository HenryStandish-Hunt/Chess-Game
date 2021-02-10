import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

public class Gui {
	
	Board b;
	JFrame frame = new JFrame();
	JPanel[][] panels;
	JPanel base;
	JPanel sidePanel;
	DragAndDrop dnd;
	Dimension boardSize = new Dimension(800,800);
	Dimension tileSize = new Dimension(100,100);
	JMenuBar menuBar;
	ActionManager actionMan;
	JTextArea sideTextArea;
	
	Gui(Board board){
	 this.b = board;
	}
	
	public void setUpBoard() {
		
		// mouse listeners placed on base to provide the drag and drop functionality
		
	    panels = new JPanel[8][8];
	    actionMan = new ActionManager();
	    setUpSidePanel();
	    setUpMenuBar();
	   // sidePanel.setBackground(Color.YELLOW);

	    
	    base = new JPanel(new GridLayout(8,8));
	    base.setName("BASE");
	    dnd = new DragAndDrop();
	    base.addMouseMotionListener(dnd);
	    base.addMouseListener(dnd);
	    
	    // filling up JPanel array with JPanels that are the named after their coordinates on the board
	    // the name is used later to detect position of mouse after dragging 
	    
	    for(int i = 0; i < 8; i++) {
		for(int j = 0; j < 8; j++) {
			
		panels[i][j] = new JPanel();
		panels[i][j].setName(i+ " "+ j);
		panels[i][j].setSize(tileSize);
		
		
		//Set up colours of the tiles on the back board
		
		if(j == 0 || j == 2 || j == 4 || j == 6) {
			if(i % 2 == 0) {
			panels[i][j].setBackground(Color.gray);
			} else {
		    panels[i][j].setBackground(Color.white);
		    }
		}
		if(j == 1 || j == 3 || j == 5 || j == 7) {
			if(i % 2 == 0) {
			panels[i][j].setBackground(Color.white);
			} else {
			panels[i][j].setBackground(Color.gray);	
			}
		} 

		}
		}
		
		//Placing the panels into their correct place on the board;
		for(int y = 7; y >= 0; y--)	{
		for(int x = 0; x < 8; x++)	{
		
			
		     base.add(panels[x][y]);
		}
		}
		base.setSize(boardSize);
		frame.add(base);
		frame.add(sidePanel);
		frame.setJMenuBar(menuBar);
		frame.setSize(1200, 860);
		frame.setVisible(true);
	}
	
	public void setUpSidePanel() {
		Color sideColor = new Color(8, 199, 107);
		Color sideHighLight = new Color(11, 214, 116);
		
		sidePanel = new JPanel();
	    sidePanel.setBackground(sideColor);
	    sidePanel.setLayout(null);
	    JLabel sideHead = new JLabel();
	    sideHead.setBounds(810, 0, 400, 75);
	    //side.setText("Hello and Welcome to Chess By Henry Standish-Hunt");
	    sideHead.setText("<html><h1>Hello and Welcome to Chess</h1></html>");
	    
	    sideTextArea = new JTextArea();
	    sideTextArea.setBounds(810, 80, 365, 300);
	    sideTextArea.setText("To start Game player 1 drag and drop a white piece");
	    sideTextArea.setEditable(false);
	    sideTextArea.setBackground(sideHighLight);
	    sideTextArea.setLineWrap(true);
	    sideTextArea.setWrapStyleWord(true);
	    Font testFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
	    sideTextArea.setFont(testFont);
	    sidePanel.add(sideHead);
		sidePanel.add(sideTextArea);
	}
	public void setSideText(String s) {
		sideTextArea.setText(s);
	}
	public void setUpMenuBar() {
		menuBar = new JMenuBar();
		
		JMenu m1 = new JMenu("testing testing mic one");
		
		JMenuItem newGame = new JMenuItem("New Game"); 
		newGame.addActionListener(actionMan);
		
		m1.add(newGame);
		menuBar.add(m1);
		
	}
	
	
	//This is used to update the Board placing the piece labels in their corrisponding positions on the board
	public void setState() {
		 
		
		        Cell[][] state = b.getBoard();
		        
		        // this removes all the labels currently on the board
		        for(JPanel[] jPanelArray : panels) {
		        	for(JPanel jPanel: jPanelArray) {
		        		jPanel.removeAll();
		        	}
		        }
		        
		        //Then go through checking the board state and producing/adding labels where the pieces are
		        for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
				String text = "";
				
				if(state[i][j].getOccupied()) {
					text = state[i][j].getPiece().getName() + " " + state[i][j].getPiece().getColour().substring(0, 1);
					
				}
				JLabel jl = new JLabel();
				jl.setText(text);
				panels[i][j].add(jl);
				panels[i][j].revalidate();;
				}
		        }
		        
		        frame.repaint();
	
	}
	//not sure how to get the program to wait for a response using an empty while loop
	//doesn't work so printing an empty string was my solution
	public int[] getSelectGetMov() {
		dnd.setPrimed(true);
		String s = "";
		while(dnd.getPrimed()) {
			//System.out.println(dnd.getPrimed());
			System.out.print(s);
		}
		
		return dnd.getCoord();
	}
	public class ActionManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(e.getModifiers() + " light camara action");
			
			
		}
		
	}
	
	//This class defines the drag and drop functions and takes input from the user through the GUI
	public class DragAndDrop implements MouseMotionListener, MouseListener{
		
		//This is used to co-ordinate input so that only one sample of the input data is written into the coord field 
		boolean primed;
		//Holds the co ordinates to be passed to movementPath class for processing
		int[] coord = new int [4];
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
			Component hoverOver = base.getComponentAt(e.getX(), e.getY());
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			//System.out.println("Mouse moved" + x + " " + y );
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
		
		// finds the start co ordinate to be used as the Selected piece x pos and the Selected piece y pos
		// writes it to coord if primed
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println("start");
			Component hoverOver = base.getComponentAt(e.getX(), e.getY());
			String pos = hoverOver.getName();
			
			System.out.println(hoverOver.getX());
			
			int xSelect = Integer.parseInt(pos, 0, 1, 10);
			int ySelect = Integer.parseInt(pos, 2, 3, 10);
			if(primed) {
			coord[0] = xSelect;
			coord[1] = ySelect;
			}
			System.out.println("done");
		}
		
		// this collects the movement co ord for the selected piece then sets primed to false;
		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("end");
			
			Component hoverOver = base.getComponentAt(e.getX(), e.getY());
			String pos = hoverOver.getName();
			
			System.out.println(hoverOver.getX());
			
			int xMov = Integer.parseInt(pos, 0, 1, 10);
			int yMov = Integer.parseInt(pos, 2, 3, 10);
			if(primed) {
			coord[2] = xMov;
			coord[3] = yMov;
			}
			setPrimed(false);
			System.out.println("done");
			System.out.println(Arrays.toString(coord));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		public void setPrimed(boolean primed) {
			this.primed = primed;
		}
		public boolean getPrimed() {
			return primed;
		}
		
		public int[] getCoord() {
			return coord;
			
		}


	}
	

// testing platform 
	/*
public static void main(String[] args) {
	 boolean finished = false;	
     System.out.println("welcome to chess by Henry Standish-Hunt");
     System.out.println();
     Board b = new Board();
     Player one = new Player("White");
     Player two = new Player("Black");
     one.setName("One");
     two.setName("Two");
     b.startBoard("Black", b);
   
  //  System.out.println(b.getCell(0, 1).getOccupied());
	 //System.out.println( b.getCell(4,0).getPiece().getName());
  
     
	Graphics g = new Graphics(b);
	g.printState();
	System.out.println();
   System.out.println();
	System.out.println();

	
	
	Gui gg = new Gui(b);
	//gg.printBoard();
	gg.setUpBoard();
	gg.setState();
	
}*/
}
	