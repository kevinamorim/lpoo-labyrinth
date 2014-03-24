import maze.logic.GameConfig;
import maze.logic.GameLogic;

public class Game {

	public static void main(String[] args) {
		
		boolean isConsole = false;
		
		GameConfig config = new GameConfig(isConsole);
		
		GameLogic game = new GameLogic(config, 0.05);

		game.init();
	
		game.loop();
		
	}

}
