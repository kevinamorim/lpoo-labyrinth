import maze.logic.GameConfig;
import maze.logic.GameLogic;

public class Game {

	public static void main(String[] args) {
		
		int state = -1;
		
		boolean isConsole = false;
		
		GameConfig config = new GameConfig(isConsole);
		
		while(state != 0) {
			
			GameLogic game = new GameLogic(config, 0.01);
			
			state = game.loop();
			
			config = game.getConfig();
			
		}

	}

}
