import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Gui {
	
	Board b;
	JFrame frame = new JFrame();
	JPanel[][] panels;
	JPanel base;
	
	Gui(Board board){
	 this.b = board;
	}
	
	public void setUpBoard() {
	    panels = new JPanel[8][8];
	    base = new JPanel(new GridLayout(8,8));
	    base.setName("BASE");
	    DragAndDrop dnd = new DragAndDrop();
	    base.addMouseMotionListener(dnd);
	    base.addMouseListener(dnd);
	    // filling up JPanel array with JPanels that contain the names of the pieces in them
	    
	    for(int i = 0; i < 8; i++) {
		for(int j = 0; j < 8; j++) {
			
		panels[i][j] = new JPanel();
		
		panels[i][j].setName(" " +i+ " "+ j);
		panels[i][j].setSize(new Dimension(100,100));
		//panels[i][j].addMouseListener(new DragAndDrop(panels[i][j]));
		
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
		frame.add(base);
		frame.setSize(800, 800);
		frame.setVisible(true);
	}
	
	public void setState() {
		 
		
		        Cell[][] state = b.getBoard();
		        
		        
		        for(JPanel[] jPanelArray : panels) {
		        	for(JPanel jPanel: jPanelArray) {
		        		jPanel.removeAll();
		        	}
		        }
	
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

	public class DragAndDrop implements MouseMotionListener, MouseListener{
		
		private JLabel jl;
		
		private JPanel jp;
		

		@Override
		public void mouseDragged(MouseEvent e) {
			
			Component hoverOver = base.getComponentAt(e.getX(), e.getY());
			
			System.out.println(" your dragging" + " " + hoverOver.getName());
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX();
			int y = e.getY();
			//System.out.println("Mouse moved" + x + " " + y );
			
		}


		@Override
		public void mouseClicked(MouseEvent e) {
			int xSelect = e.getX();
			int ySelect = e.getY();
			System.out.println(" you have clicked " + base.getComponentAt(e.getX(), e.getY()));
		}


		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
	