//import maze.gui.ConfigurationWindow;
//import maze.gui.MenuWindow;
import javax.swing.JOptionPane;

import maze.gui.ConfigurationWindow;
import maze.gui.InputHandler;
import maze.gui.MenuWindow;
import maze.logic.GameConfig;
import maze.logic.GameLogic;

public class Game {

	public static void main(String[] args) {

		gameMenu();

	}

	private static void gameMenu() {
		
		int state = 0;
		int GRAPHICAL = 1;
		int CONSOLE = 0;
		
		GameConfig config;
		
		int mode = JOptionPane.showConfirmDialog(null, "Do you wish to play in graphical mode?");
		
		switch(mode) {
		case JOptionPane.YES_OPTION:
			config = new GameConfig(GRAPHICAL, 0.06);
			break;
		case JOptionPane.NO_OPTION:
			config = new GameConfig(CONSOLE, 0.06);
			break;
		case JOptionPane.CANCEL_OPTION:
			return;
		default:
			return;
		}
		
		
		MenuWindow menuWindow = new MenuWindow();
		ConfigurationWindow configWindow = new ConfigurationWindow(config);
		
		InputHandler menuHandler = new InputHandler(menuWindow);
		InputHandler configHandler = new InputHandler(configWindow);
		
		Thread inputMenuThread = new Thread(menuHandler);
		Thread inputConfigThread = new Thread(configHandler);

		//ConfigurationWindow conf = new ConfigurationWindow(config);

		while(true) {

			inputMenuThread.start();

			state = menuHandler.getNextCommand();

			switch(state) {
			case -1:
				return;
			case 0:
				GameLogic game = new GameLogic(config);
				game.loop();
				config = game.getConfig();
				break;
			case 1:
				break;
			case 2:
				break;
			default:
				return;
			}

		}

	}

}
