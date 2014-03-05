import maze.logic.*;

public class Game {

	public static void main(String[] args) {
		
		GameConfig config = new GameConfig();
		
		GameLogic game = new GameLogic(config);

		game.init();
	
		game.loop();

	}

}
