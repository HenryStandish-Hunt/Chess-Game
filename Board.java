
public class Board {
	
	int y = 8;
	int x = 8;
	
	private Cell [][] board;
	
	// board constructor creates Cell list of lists then populates with cells with null pieceses assigned and sets there occupied to false
	
	Board() {
		board = new Cell[8][8];
		for(int i = 0; i < 8; i++) {
		for(int j = 0; j < 8; j++)	{
		board[i][j] = new Cell(null,false);
		}
		}
	}

	public Cell[][] getBoard() {
		return board;
	}
	
	public void setBoard(Cell[][] board) {
	 this.board = board;
		
	}
	public Cell getCell(int x, int y) {
		return board[x][y];
	}
	
	//sets the cell with new Piece
	public void setCell(int x, int y, Piece p) {
		board[x][y].setPiece(p);
	
	}
	//clears a cells piece and makes occupation false
	public void setCellEmpty(int x, int y) {
		board[x][y].setPiece(null);
	
	}
	 
	//Fills start board to regulation chess board
	public void startBoard(Board b) {
		 
	    //SetUp line of white prawns	
		for(int i = 0; i < 8; i++) {
		Piece prawn = new Piece("Prawn","White",i,1);
		b.setCell(i, 1, prawn);
		}
		
		//SetUp line of black prawns
		for(int i = 0; i < 8; i++) {
			Piece prawn = new Piece("Prawn","Black",i,6);
			b.setCell(i, 6, prawn);
		}
		
		//Repeat twice filling in back lines of both exluding king and queen
		int k = 0;
		for(int i = 0; i < 2; i++) {
			String colour;
			
			if(k == 0) {
				colour = "White";
		    } else {
		    	colour = "Black";
		    }
			
			Piece rook = new Piece("Rook", colour,0,k);
			b.setCell(0, k, rook);
			Piece knight = new Piece("Knight", colour,1,k);
			b.setCell(1, k, knight);
			Piece bishop = new Piece("Bishop", colour,2,k);
			b.setCell(2, k, bishop);
			Piece bishop2 = new Piece("Bishop", colour,5,k);
			b.setCell(5, k, bishop2);
			Piece knight2 = new Piece("Knight", colour,6,k);
			b.setCell(6, k, knight2);
			Piece rook2 = new Piece("Rook", colour,7,k);
			b.setCell(7, k, rook2);
			k = 7;
		}
		
		    k = 0;
				for(int i = 0; i < 2; i++) {
					String colour;
					int kPos;
					int qPos;
					
					if(k == 0) {
						colour = "White";
						kPos = 4;
						qPos = 3;
				    } else {
				    	colour = "Black";
				    	kPos = 4;
						qPos = 3;
				    }
					
					Piece king = new Piece("King", colour,kPos,k);
					b.setCell(kPos, k, king);
					
					Piece queen = new Piece("Queen", colour,qPos,k);
					b.setCell(qPos, k, queen);
					k = 7;
				}
		
	}
	
	public Board duplicateBoard() {
		Board dup = new Board();
		Cell[][] dupState = dup.getBoard();
		
		 for(int x = 0; x < 8; x++) {
		 for(int y = 0; y < 8; y++)	{
			if(!board[x][y].getOccupied()) {
				dupState[x][y] =  new Cell(null,false);
			} else {
				Piece p = board[x][y].getPiece();
	            dupState[x][y].setPiece(new Piece(p.getName(),p.getColour(),x,y));
	           
			}
			    		
			    			
		 }
		 }
		 return dup;
   }
}
