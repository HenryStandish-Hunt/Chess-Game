import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ComputerPlayer {
	
	private static int [] nextMove;
	 
	private static final int DEPTH = 4;
	
	public static void computerRandomTurn(Board b, Player computer, Player opponent) {
		
		 ArrayList<int[]> legalMoves = getLegalMoves(b,computer,opponent);
		
		 for(int i = 0; i < 5000000; i++) {
	        	System.out.print("");
	        	
	        }
	        System.out.println();
	        
	        System.out.println(legalMoves.size());
	        int rand = (int) (Math.random()*legalMoves.size());
	        int[] move = legalMoves.get(rand);
	        MovementPath.setTest(false);
	        System.out.println("computer movement "+ move[0] + " " + move[1] + " " + move[2] + " " + move[3]);
	        MovementPath.movementPath(b, computer,  move[0], move[1], move[2], move[3]);
				
		
		        
	}
	public static void computerMiniMaxAlphaBetaTurn(Board b, Player computer, Player opponent) {
		
		maximizer(b, DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE,  computer,  opponent);
		int[]coord = nextMove;
		MovementPath.setTest(false);
	    MovementPath.movementPath(b, computer,  coord[0], coord[1], coord[2], coord[3]);
		
	
	
	}
	private static int maximizer(Board b, int depth, int alpha, int beta, Player computer, Player opponent) {
		
		    if(depth == 0) {
			return evaluate(b,computer, opponent);
		    }
		    
		    List<int[]> legalMoves = getLegalMoves(b, computer, opponent);
		    
		    for(int[] move : legalMoves) {
				
				Board childBoard = b.duplicateBoard(); 
				MovementPath.setTest(false);
		        MovementPath.movementPath(childBoard, computer,  move[0], move[1], move[2], move[3]);
		        
				int score = minimizer(childBoard, depth -1,alpha, beta, computer, opponent);
				
				if(score > alpha) {
					alpha = score;
					if(depth == DEPTH ) {
						nextMove = move;
				    }
		        }
				
				if(alpha >= beta) {
				   return alpha;
				}
		    }
		    return alpha; 
	}
    private static int minimizer(Board b, int depth, int alpha, int beta, Player computer, Player opponent) {

	    if(depth == 0) {
		return evaluate(b,computer, opponent);
	    }
	    List<int[]> legalMoves = getLegalMoves(b, opponent, computer);
	    
	    for(int[] move : legalMoves) {
			
			Board childBoard = b.duplicateBoard(); 
			MovementPath.setTest(false);
	        MovementPath.movementPath(childBoard, opponent,  move[0], move[1], move[2], move[3]);
	       
			int score = maximizer(childBoard, depth -1,alpha, beta, computer, opponent);
			if(score < beta) {
				beta = score;
				
	        }
			
			if(alpha >= beta) {
			   return beta;
			}
	    }
	    return beta; 
	}
	public static ArrayList<int[]> getLegalMoves(Board b, Player player, Player opponent) {
		 
			
			ArrayList<int[]>legalMoves = new ArrayList<>();
			Cell[][] currentState = b.getBoard();
	
	        for(int xSelect = 0; xSelect < 8; xSelect++) {
			for(int ySelect = 0; ySelect < 8; ySelect++) {	
		  
				 if(currentState[xSelect][ySelect].getOccupied() && currentState[xSelect][ySelect].getColour().contentEquals(player.getColour())) {
					 
		    	 for(int xMov = 0; xMov < 8; xMov++) {
		    	 for(int yMov = 0; yMov < 8; yMov++) {
		    		 
		    	 Board bDup = b.duplicateBoard();
		    	 MovementPath.setTest(false);
		    	 MovementPath.setSuccessful(false);
		    	 MovementPath.movementPath(bDup, player,  xSelect, ySelect, xMov, yMov);
		    	// System.out.println("in get legal move is this before error");
		    	 Boolean success = MovementPath.isSuccessful();
		    	 if(success) {
		    	 if(GameLogic.checkCheck(opponent, bDup)) {
		    		 success = false;
		    	 }
		    	 }
		 
		    	 	if(success) {
		    	 	Piece p = b.getCell(xSelect, ySelect).getPiece();
		    	 	if(p == null) {
		    	 		System.out.println("null selected +" + xSelect + " "+ ySelect);
		    	 	}
		    	 	
		    	 		int[] coord = new int[4];
		    	 		coord[0] = xSelect;
		    	 		coord[1] = ySelect;
		    	 		coord[2] = xMov;
		    	 		coord[3] = yMov;
		    	 		legalMoves.add(coord);
		    	     }
		    	 
		    		
		    	 }
		    	 }
		    	 }
		     }
		     }
	        
	        return legalMoves;
	       
	}
	
	private static int evaluate(Board b, Player computer, Player opponent) {
		int eval = 0;
		
		if(GameLogic.checkCheck(computer, b)) {
			if(GameLogic.checkMate(computer, b)) {
			eval += 10000;
			}
		}
		if(GameLogic.checkCheck(opponent, b)) {
			if(GameLogic.checkMate(opponent, b)) {
			eval -= 10000;
			}
		}
		
		    for(Cell[] cellArray : b.getBoard()) {
			for(Cell cell : cellArray) {
				if(cell.getOccupied()) {
					Piece p = cell.getPiece();
					if(computer.getColour().equals(p.getColour())){
						eval += pieceRating(p);
						//System.out.println("mid loop eval max " + eval);
					}else {
						eval -= pieceRating(p);
						//System.out.println("mid loop eval min " + eval);
					}
				}
			}
		}
		//System.out.println(eval);
		return eval;
	}
	private static int pieceRating(Piece p) {
		int score = 0;
		
		switch(p.getName()) {	
		case "King":
			score = 900;
			break;
		case "Queen":
			score = 100;
			break;
		case "Rook":
			score = 75;
			break;
		case "Bishop":
			score = 30;
			break;
		case "Knight":
			score = 30;
			break;
		case "Prawn":	
			score = 10;
			break;
		default:
			System.out.println("Unknown piece evaluation ");
		
		}
		//System.out.println(" after de switch case score " + score);
		return score;
	}

}


