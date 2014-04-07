package maze.logic;
//import maze.gui.ConfigurationWindow;
//import maze.gui.MenuWindow;
import javax.swing.JOptionPane;

import maze.gui.ConfigurationWindow;
import maze.gui.InputHandler;
import maze.gui.MenuWindow;

public class Game {

	public static void main(String[] args) {

		gameMenu();

		System.exit(0);
	}

	private static void gameMenu() {
		
		int state = -1;
		int GRAPHICAL = 1;
		int CONSOLE = 0;
		
		GameLogic game;
		GameConfig config;
		MenuWindow menuWindow;
		ConfigurationWindow configWindow;
		InputHandler menuHandler;
		InputHandler configHandler;
		Thread inputMenuThread;
		Thread inputConfigThread;
		
		game = null;
		menuHandler = null;
		configHandler = null;
		configWindow = null;
		menuWindow = null;
		
		switch(JOptionPane.showConfirmDialog(null, "Do you wish to play in graphical mode?")) {
		case JOptionPane.YES_OPTION: // GRAPHICAL
			
			config = new GameConfig(GRAPHICAL, 0.06);
			
			configWindow = new ConfigurationWindow("Options",config);
			configWindow.setVisible(false);
			
			menuWindow = new MenuWindow("Menu");
			
			menuHandler = new InputHandler(menuWindow);
			configHandler = new InputHandler(configWindow);
			
			inputMenuThread = new Thread(menuHandler);
			inputConfigThread = new Thread(configHandler);
			
			inputMenuThread.start();
			inputConfigThread.start();
			
			break;
		case JOptionPane.NO_OPTION: // CONSOLE
			config = new GameConfig(CONSOLE, 0.06);
			break;
		case JOptionPane.CANCEL_OPTION: // CANCEL
			return;
		default:
			return;
		}

		while(state != 0) {

			if(config.getMode() == GRAPHICAL) {
				
				/* The player choses an option from the menu */
				state = menuHandler.getNextCommand();
				
				if(state > 0) {
					menuHandler.removeCommand();
				}
				
				switch(state) {
				case 1: // PLAY
					int innerState = -1;
					
					/* Shows the configurations window */
					menuWindow.setVisible(false);
					configWindow.setVisible(true);
					
					/* Waits for the player to chose confirm/cancel */
					do {
						innerState = configHandler.getNextCommand();
					}while(innerState == -1);
					
					configHandler.removeCommand();
					
					configWindow.setVisible(false);
					
					if(innerState == 1) { // Confirm - Start game
						
						do {
							
							if(innerState == 1) { // First Time Game
								game = new GameLogic(config, configWindow);
							}
							else if(innerState == -2) { // New Game (with the new configuration)
								config = game.getConfig();
								game = new GameLogic(config, configWindow);
							}
							
							innerState = game.loop();
						
							
						}while(innerState == -2 || innerState == -3);
						
					}

					menuWindow.setVisible(true);
					break;
				case 2: // OPTIONS
					break;
				case 3: // CREDITS
					break;
				case 4: // CREDITS
					state = 0;
					break;
				default:// QUIT
					break;
				}
				
			}
			else {
				game = new GameLogic(config);
				state = game.loop();
				config = game.getConfig();
			}
		}

	}
	
}
