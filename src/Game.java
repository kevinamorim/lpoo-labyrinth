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
		MenuWindow menuWindow;
		ConfigurationWindow configWindow;
		InputHandler menuHandler;
		InputHandler configHandler;
		Thread inputMenuThread;
		Thread inputConfigThread;
		
		menuHandler = new InputHandler();
		
		int mode = JOptionPane.showConfirmDialog(null, "Do you wish to play in graphical mode?");
		
		switch(mode) {
		case JOptionPane.YES_OPTION: // GRAPHICAL
			
			config = new GameConfig(GRAPHICAL, 0.06);
			menuWindow = new MenuWindow();
			configWindow = new ConfigurationWindow(config);
			
			menuHandler = new InputHandler(menuWindow);
			configHandler = new InputHandler(configWindow);
			
			inputMenuThread = new Thread(menuHandler);
			inputConfigThread = new Thread(configHandler);
			
			inputMenuThread.start();
			
			
			break;
		case JOptionPane.NO_OPTION: // CONSOLE
			config = new GameConfig(CONSOLE, 0.06);
			break;
		case JOptionPane.CANCEL_OPTION: // CANCEL
			return;
		default:
			return;
		}

		while(true) {

			if(config.getMode() == GRAPHICAL) {
				state = menuHandler.getNextCommand();
			}
			else {
				state = 0;
			}
			
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
