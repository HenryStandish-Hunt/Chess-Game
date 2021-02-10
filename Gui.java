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
import java.util.Arrays;

import javax.swing.*;

public class Gui {
	
	Board b;
	JFrame frame = new JFrame();
	Tile[][] panels;
	JPanel base;
	DragAndDrop dnd;
	Gui(Board board){
	 this.b = board;
	}
	
	public void setUpBoard() {
	    panels = new Tile[8][8];
	    base = new JPanel(new GridLayout(8,8));
	    base.setName("BASE");
	    dnd = new DragAndDrop();
	    base.addMouseMotionListener(dnd);
	    base.addMouseListener(dnd);
	    // filling up JPanel array with JPanels that contain the names of the pieces in them
	    
	    for(int i = 0; i < 8; i++) {
		for(int j = 0; j < 8; j++) {
			
		panels[i][j] = new Tile();
		
		panels[i][j].setName(i+ " "+ j);
		panels[i][j].setXpos(i);
		panels[i][j].setYpos(j);
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
	public void setPrimedClick() {
		dnd.setPrimed(true);
	//	System.out.println("Set prime");
	}
	//not sure how to get the program to wait for a response and an empty while loop doesnt work
	public int[] getSelectGetMov() {
		dnd.setPrimed(true);
		String s = "";
		while(dnd.getPrimed()) {
			//System.out.println(dnd.getPrimed());
			System.out.print(s);
		}
		
		return dnd.getCoord();
	}
	
	public class Tile extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private int x;
		private int y;
		
		public void setXpos(int x) {
			this.x = x;
		}
		public void setYpos(int y) {
			this.y = y;
		}
		//@Override
		public int getXpos() {
			return x;
		}
		//@Override
		public int getYpos() {
			return y;
		}
		
	}

	public class DragAndDrop implements MouseMotionListener, MouseListener{
		boolean primed;
		
		int[] coord = new int [4];
		
		@Override
		public void mouseDragged(MouseEvent e) {
			//click = true;
			
			Component hoverOver = base.getComponentAt(e.getX(), e.getY());
			
			//System.out.println(" your dragging" + " " + hoverOver.getName());
			
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
			// TODO Auto-generated method stub
			
			
		}

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
	