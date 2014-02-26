import maze.logic.*;

public class Game {

	public static void main(String[] args) {
		
		GameLogic game = new GameLogic();

		game.init();
	
		game.loop();

	}

}
