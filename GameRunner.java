
public class GameRunner {
		 
	  private static boolean gameComplete;
	  private static boolean newGameReady;
	  
	  public static void main(String[] args) {
		  
		  start();
		  
	  }
	  
	  public static void start() {
		  Gui gameInterface = new Gui();
		  //GameManager local = new singlePlayerGame();
		  GameManager local = new LocalMultiplayerGame();
		  local.setUpAndPlay(gameInterface);
		  System.out.println("hopefully im here at end");
	  }

}

