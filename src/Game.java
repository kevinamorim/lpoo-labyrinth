import maze.logic.*;

public class Game {

	public static void main(String[] args) {
		
		GameConfig config = new GameConfig();
		
		GameLogic game = new GameLogic(config, 0.05);

		game.init();
	
		game.loop();

	}

}
