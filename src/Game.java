import maze.gui.GameWindow;
import maze.logic.GameConfig;
import maze.logic.GameLogic;

public class Game {

	public static void main(String[] args) {
		
		GameConfig config = new GameConfig(true);
		
		GameLogic game = new GameLogic(config, 0.05);

		game.init();
	
		game.loop();
		
	}

}
