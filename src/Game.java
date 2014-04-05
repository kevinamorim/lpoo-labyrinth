//import maze.gui.ConfigurationWindow;
//import maze.gui.MenuWindow;
import maze.logic.GameConfig;
import maze.logic.GameLogic;

public class Game {

	public static void main(String[] args) {
		
		int state = -1;
		
		//MenuWindow menu = new MenuWindow();
		
		GameConfig config = new GameConfig(1, 0.01);
		
		//ConfigurationWindow conf = new ConfigurationWindow(config);
		
		while(state != 0) {
			
			GameLogic game = new GameLogic(config);
			
			state = game.loop();
			
			config = game.getConfig();
			
		}

	}

}
