//import maze.gui.ConfigurationWindow;
//import maze.gui.MenuWindow;
import maze.gui.ConfigurationWindow;
import maze.gui.InputMenuHandler;
import maze.gui.MenuWindow;
import maze.logic.GameConfig;
import maze.logic.GameLogic;

public class Game {

	public static void main(String[] args) {
		
		int state = -1;
		
		gameMenu();

	}

	private static void gameMenu() {
		
		int GRAPHICAL = 1;
		
		MenuWindow menuWindow = new MenuWindow();
		
		GameConfig config = new GameConfig(GRAPHICAL, 0.06);
		
		ConfigurationWindow configWindow = new ConfigurationWindow(config);
		
		Thread inputThread = new Thread(new InputMenuHandler(menuWindow));

		//ConfigurationWindow conf = new ConfigurationWindow(config);
		
		while(state != 0) {
			
			GameLogic game = new GameLogic(config);
			
			state = game.loop();
			
			config = game.getConfig();
			
		}
		
	}

}
