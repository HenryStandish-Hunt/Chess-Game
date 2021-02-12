
public class GameRunner {
		 
	  private static boolean gameComplete;
	  private static boolean newGameReady;
	  
	  public static void main(String[] args) {
		  
		  start();
		  
	  }
	  
	  public static void start() {
		  Gui gameInterface = new Gui();
		  boolean finished = false;
		  GameManager local = null;
	     
		  int choice = gameInterface.chooseGameType();
		  if(choice == 0) {
		   local = new singlePlayerGame();
		  }
		  if(choice == 1) {
			  local = new LocalMultiplayerGame();
		  }
		  local.setUpAndPlay(gameInterface);
		  System.out.println("hopefully im here at end");
	  
	 }

}

