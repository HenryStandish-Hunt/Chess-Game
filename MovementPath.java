import java.util.Scanner;
public class MovementPath {
	
	private static Scanner scan = new Scanner(System.in);
	private static boolean successful = false;
	private static boolean test = false;
	private static int lastMoveStartxPos;
	private static int lastMoveStartyPos;
	private static int lastMoveEndxPos;
	private static int lastMoveEndyPos;
	private static Piece lastPieceInCell;

	public static void playerTurn(Board b, Player player) {
		int xSelect;
		int ySelect;
		int xMov;
		int yMov;
		boolean correct = false;
		
		do {
		setTest(false);	
		System.out.println("Hello " + player.getName() + " please type the coordinate of the chess piece you are selecting ");
		System.out.println("Please use formate X Y with the coordinates filled with integers between 0-7");
		xSelect = scan.nextInt();
		ySelect = scan.nextInt();
		
		if(b.getCell(xSelect, ySelect).getOccupied()) {
			
		System.out.println(b.getCell(xSelect, ySelect).getColour() + " " + b.getCell(xSelect, ySelect).getPiece().getName());
		
			//Checking if selecting opponants piece
			if(b.getCell(xSelect, ySelect).getPiece().getColour().equals(player.getColour())) {
			System.out.println();
			correct = true;
		    }else {
		    	System.out.println("That isnt your piece please Re select");
				System.out.println();
		    }
		
		} else {
			System.out.println("That cell is unoccupied please Re select");
			System.out.println();
			
		}
		
		} while(!correct);
		
		 correct = false;
		 
		do {
			System.out.println("Hello again player please type the coordinate cell the piece is moving to ");
			System.out.println("Please use formate X Y with the coordinates filled with integers between 0-7");
			xMov = scan.nextInt();
			yMov = scan.nextInt();
			System.out.println(xMov + " X Coordinant " + yMov + " Y Coordinant");
			System.out.println();
			correct = true;
			/*System.out.println("Is this correct type y or n");
			if(	scan.next().equals("y")){
				correct = true;
			}*/
			} while(!correct);
		 

		movementPath(b, player, xSelect, ySelect, xMov, yMov);
		
	}
	
	
	public static void movementPath(Board b, Player player, int xSelect, int ySelect, int xMov, int yMov) {
		
		setSuccessful(false);
		
		//Checking to see if selection is on the board
		if(xSelect < 0 || xSelect > 7 ||ySelect < 0 || ySelect > 7 ) {
			System.out.println("Please make a valid selection");
			return;
		}
		
		Piece p = b.getCell(xSelect, ySelect).getPiece();
		
		// Checking if Movement is off the board
		if(xMov < 0 || xMov > 7 ||yMov < 0 || yMov > 7 ) {
			System.out.println("movement path invalid please select another");
			return;
		}
		// Checking if selection and movement are the same
				if(xMov == xSelect && yMov == ySelect) {
					System.out.println("movement path invalid cant stay in the same place please select another");
					return;
				}
		//Checking if landing on a cell occupied by the players own piece
		if(b.getCell(xMov, yMov).getOccupied() && b.getCell(xMov, yMov).getColour().equals(player.getColour())) {
			System.out.println("movement path invalid cant take own piece");
			return;
		}
		
		
		
		switch(p.getName()) {
		
		case "King":
			king(p, b, xMov, yMov, player);
			break;
		case "Queen":
			 queen(p, b, xMov, yMov, player);
			break;
		case "Rook":
			 rook(p, b, xMov, yMov, player);
			break;
		case "Bishop":
			 bishop(p, b, xMov, yMov, player);
			break;
		case "Knight":
			 Knight(p, b, xMov, yMov, player);
			break;
		case "Prawn":	
		      prawn(p, b, xMov, yMov,player);
			break;
		default:
			System.out.println("Unknown piece selection ");
		
		}
		
	}
	public static void Knight(Piece p, Board b, int xMov, int yMov, Player player) {
		//check if its up left/right
		if(xMov == p.getxPos() - 1 && yMov == p.getyPos() + 2) {
			finishMove(p, b, xMov, yMov, player);
		}
		else if(xMov == p.getxPos() + 1 && yMov == p.getyPos() + 2) {
			finishMove(p, b, xMov, yMov, player);
		}
		else if(xMov == p.getxPos() - 2 && yMov == p.getyPos() + 1) {
			finishMove(p, b, xMov, yMov, player);
		}
		else if(xMov == p.getxPos() - 2 && yMov == p.getyPos() - 1) {
			finishMove(p, b, xMov, yMov, player);
		}
		else if(xMov == p.getxPos() - 1 && yMov == p.getyPos() - 2) {
			finishMove(p, b, xMov, yMov, player);
		}
		else if(xMov == p.getxPos() + 1 && yMov == p.getyPos() - 2) {
			finishMove(p, b, xMov, yMov, player);
		}
		else if(xMov == p.getxPos() + 2 && yMov == p.getyPos() + 1) {
			finishMove(p, b, xMov, yMov, player);
		}
		else if(xMov == p.getxPos() + 2 && yMov == p.getyPos() - 1) {
			finishMove(p, b, xMov, yMov, player);
		} 
		else {
		//	System.out.println("invalid movement path");
		}
		
		
	}
	public static void bishop(Piece p, Board b, int xMov, int yMov, Player player) {
		if(Math.abs(p.getxPos() - xMov) == Math.abs(p.getyPos() - yMov)) {
			if(xMov > p.getxPos() && yMov > p.getyPos()) {
				checkDiagUpRight(p, b, xMov, yMov, player);
			} 
			else if(xMov > p.getxPos() && yMov <  p.getyPos()){
				checkDiagDownRight(p, b, xMov, yMov, player);
			}
			else if(xMov < p.getxPos() && yMov < p.getyPos()) {
				checkDiagDownLeft(p, b, xMov, yMov,player);
			}
			else if(xMov < p.getxPos() && yMov > p.getyPos()) {
				checkDiagUpLeft(p, b, xMov, yMov,player);
    	    }
		}
		else {
		//	System.out.println("invalid movement path");
    	}
		
	}
		
	
	public static void rook(Piece p, Board b, int xMov, int yMov , Player player) {
		
		if(xMov == p.getxPos() && yMov > p.getyPos()) {
			checkUp(p,  b,  xMov,  yMov, player);
		}
		else if(xMov == p.getxPos() && yMov < p.getyPos()) {
			checkDown(p,  b,  xMov,  yMov, player);
		}
		else if(yMov == p.getyPos() && xMov < p.getyPos()) {
			checkLeft(p,  b,  xMov,  yMov, player);
		}
		
		else if(yMov == p.getyPos() && xMov > p.getyPos()) {
			checkRight(p,  b,  xMov,  yMov, player);
		}
		else {
		//	System.out.println("invalid movement path");
		}
		
	}
	// methods for the pieces
	private static void king(Piece p, Board b, int xMov, int yMov, Player player) {
		
		if(Math.abs(xMov - p.getxPos()) > 1) {
			//System.out.println("invalid movement path");
			return;
		}
		if(Math.abs(yMov - p.getyPos()) > 1) {
			//System.out.println("invalid movement path");
			return;
		}
		finishMove(p, b, xMov, yMov, player);
		
	}
	private static void prawn(Piece p, Board b, int xMov, int yMov, Player player) {
		// these two checks prawns are moving the correct way
		if(player.getName().equals("One")){
		   if(yMov <= p.getyPos()) {
			  // System.out.println("Prawns Cant move backwards");
			   return;
		   }	
		} 
			
		if(player.getName().equals("Two")){
			   if(yMov >= p.getyPos()) {
				//   System.out.println("Prawns Cant move backwards");
				   return;
			   }
		}
		// these try to check all possible invalid movement pathways 
		
	    if(p.getNumMoves() > 0 && Math.abs(yMov - p.getyPos()) > 1 ) {
	    	//  System.out.println("Prawns Cant move more than one space after their first move");
	    	  return;
	    }
	    if(p.getNumMoves() == 0 && Math.abs(yMov - p.getyPos()) > 2 ) {
	    	//  System.out.println("Prawns Cant move more than two spaces on the first move");
	    	  return;
	    }
				
		if(!b.getCell(xMov, yMov).getOccupied() && xMov != p.getxPos()) {
			System.out.println("Prawns Cant move laterally unless taking");
			 return;
		}
		if(b.getCell(xMov, yMov).getOccupied() && Math.abs(xMov - p.getxPos()) > 1) {
			// System.out.println("Prawns Cant move laterally more than one while taking");
			 return;
		}
		
		finishMove(p, b, xMov, yMov, player);
		
		
	}
	
    private static void queen(Piece p, Board b, int xMov, int yMov, Player player) {
		 // If queen and the movement are on the same x coord check up if the y pos is greater than the selected pieces y position
		// else check down 
    	
	    if (p.getxPos() == xMov) {
	    	if(yMov > p.getyPos()) {
	        checkUp(p, b, xMov, yMov, player);
	    	} else {
	    	checkDown(p, b, xMov, yMov, player);
	    	}
	    }
	  
	    // If queen and the opponent  
	    // are in the same column 
	    else if (p.getyPos() == yMov) {
	    	 if(xMov > p.getxPos()) {
		        checkRight(p, b, xMov, yMov, player);
		    	} else {
		    	checkLeft(p, b, xMov, yMov, player);
		    	}
	    }
	       
	  
	    // If queen can attack diagonally 
	    else if(Math.abs(p.getxPos() - xMov) == Math.abs(p.getyPos() - yMov)) {
	    
	    	if(xMov > p.getxPos() && yMov > p.getyPos()) {
	    		checkDiagUpRight(p, b, xMov, yMov, player);
	    	}
	    	if(xMov > p.getxPos() && yMov <  p.getyPos()){
	    		checkDiagDownRight(p, b, xMov, yMov, player);
	    	}
	    	if(xMov < p.getxPos() && yMov < p.getyPos()) {
	    		checkDiagDownLeft(p, b, xMov, yMov, player);
	    	}
	    	if(xMov < p.getxPos() && yMov > p.getyPos()) {
	    		checkDiagUpLeft(p, b, xMov, yMov, player);
	    	}
	    }
	  
	    else {
	    	//System.out.println("invalid movement path");
	    }
	} 
    
    //common moves and the finish move method below
	
    private static void finishMove(Piece p, Board b, int xMov, int yMov, Player player){
    	//System.out.println("Your " + p.getName() + " has been moved successfully");
       
    	
        // This branches if the call to move is not from a check test
    	if(!isTest()) {
    		
        if(b.getCell(xMov, yMov).getOccupied()) {
        	lastPieceInCell = b.getCell(xMov, yMov).getPiece();
        } else {
        	lastPieceInCell = null;
        }
    	lastMoveStartxPos = p.getxPos();
    	lastMoveStartyPos = p.getyPos();
    	lastMoveEndxPos = xMov;
    	lastMoveEndyPos = yMov;
    	b.setCellEmpty(p.getxPos(), p.getyPos());
    	b.getCell(xMov, yMov).setPiece(p);
    	p.setX(xMov);
    	p.setY(yMov);
    	p.addNumMoves();
    	}
    	setSuccessful(true);
    	setTest(false);
    	
    	
    	
		
    	  
    	
    	
    }
    private static void checkDiagUpRight(Piece p, Board b, int xMov, int yMov, Player player){
    	//starts one step towards target so own cell isnt checked
    	int x = p.getxPos() + 1;
    	int y = p.getyPos() + 1;
    	
    	//this checks that all the diagonal spaces to one before the target location are clear 
    	while(x != xMov  && y != yMov ) {
    		if(b.getCell(x, y).getOccupied()){
    		//System.out.println("Cant jump over piece invalid move");
    		return;
    	    }
    		x++;
    		y++;
        }
    	finishMove(p, b, xMov, yMov, player);
    }
    private static void checkDiagDownRight(Piece p, Board b, int xMov, int yMov, Player player){
    	//starts one step towards target so own cell isnt checked
    	int x = p.getxPos() + 1;
    	int y = p.getyPos() - 1;
    	
    	//this checks that all the diagonal spaces to one before the target location are clear 
    	while(x != xMov && y != yMov ) {
    		if(b.getCell(x, y).getOccupied()){
    		//System.out.println("Cant jump over piece invalid move");
    		return;
    	    }
    		x++;
    		y--;
        }
    	finishMove(p, b, xMov, yMov, player);
    }
    private static void checkDiagDownLeft(Piece p, Board b, int xMov, int yMov,  Player player){
    	//starts one step towards target so own cell isnt checked
    	int x = p.getxPos() - 1;
    	int y = p.getyPos() - 1;
    	
    	//this checks that all the diagonal spaces to one before the target location are clear 
    	while(x != xMov && y != yMov) {
    		if(b.getCell(x, y).getOccupied()){
    		//System.out.println("Cant jump over piece invalid move");
    		return;
    	    }
    		x--;
    		y--;
        }
    	finishMove(p, b, xMov, yMov, player);
    }
    private static void checkDiagUpLeft(Piece p, Board b, int xMov, int yMov, Player player){
    	int x = p.getxPos() - 1;
    	int y = p.getyPos() + 1;
    	
    	//this checks that all the diagonal spaces to one before the target location are clear 
    	while(x != xMov && y != yMov ) {
    		if(b.getCell(x, y).getOccupied()){
    		//System.out.println("Cant jump over piece invalid move");
    		return;
    	    }
    		x--;
    		y++;
        }
    	finishMove(p, b, xMov, yMov, player);
    }
    
    private static void checkLeft(Piece p, Board b, int xMov, int yMov, Player player){
   
    	for(int i = p.getxPos() - 1; i > xMov; i--) {
    		if(b.getCell(i, p.getyPos()).getOccupied()){
    		//System.out.println("Cant jump over piece invalid move");
    		return;
    	    }
        }
    	finishMove(p, b, xMov, yMov, player);
    }
    
    private static void checkRight(Piece p, Board b, int xMov, int yMov, Player player){
    	
    	for(int i = p.getxPos() + 1; i < xMov; i++) {
    		
    		if(b.getCell(i, p.getyPos()).getOccupied()){
    		//System.out.println("Cant jump over piece invalid move");
    		return;
    	    }
        }
    	finishMove(p, b, xMov, yMov, player);
    }
    
    private static void checkUp(Piece p, Board b, int xMov, int yMov, Player player){
    	
    	for(int i = p.getyPos() + 1; i < yMov; i++) {
    		if(b.getCell(p.getxPos(),i).getOccupied()) {
    			//System.out.println("Cant jump over piece invalid move");
    			return;
    		}
    	}
    	
    	//if it doesnt return that means the way to the target cell is clear 
    	// finish move moves the piece then clears the old cell
    	finishMove(p, b, xMov, yMov, player);
    
    }
    private static void checkDown(Piece p, Board b, int xMov, int yMov, Player player){
    	
    	for(int i = p.getyPos() - 1; i > yMov; i--) {
    		if(b.getCell(p.getxPos(),i).getOccupied()) {
    			//System.out.println("Cant jump over piece invalid move");
    			return;
    		}
    	}
    	
    	//if it doesnt return that means the way to the target cell is clear 
    	// finish move moves the piece then clears the old cell
    	finishMove(p, b, xMov, yMov, player);
    
    }
    
    public static boolean isSuccessful() {
		return successful;
	}


	public static void setSuccessful(boolean successful) {
		MovementPath.successful = successful;
	}


	public static boolean isTest() {
		return test;
	}


	public static void setTest(boolean test) {
		MovementPath.test = test;
	}
	
	public static void manualMove(Board b, int xSelect, int ySelect, int xMov, int yMov){
		    	
		        Piece p = b.getCell(xSelect, ySelect).getPiece();
		        p.setX(xMov);
		    	p.setY(yMov);
		    	b.setCellEmpty(xSelect, ySelect);
		    	b.getCell(xMov, yMov).setPiece(p);
		    	
		    	if(lastPieceInCell != null) {
		    		b.getCell(xSelect, ySelect).setPiece(lastPieceInCell);
		    	}
		   
		    }
	
	public static void undoLastMove(Board b) {
		
		Piece p = b.getCell(lastMoveEndxPos, lastMoveEndyPos).getPiece();	
    	b.setCellEmpty(p.getxPos(), p.getyPos());
    	b.getCell(lastMoveStartxPos, lastMoveStartyPos).setPiece(p);
    	p.setX(lastMoveStartxPos);
    	p.setY(lastMoveStartyPos);
    	p.minusNumMoves();
    	
    	if(lastPieceInCell != null) {
    		b.getCell(lastMoveEndxPos, lastMoveEndyPos).setPiece(lastPieceInCell);
    	}
    	
		MovementPath.setSuccessful(false);
	}
	}
	
	


