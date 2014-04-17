package maze.logic;

import javax.swing.JOptionPane;
import maze.gui.InputHandler;
import maze.gui.MenuWindow;

public class Game {

	public static void main(String[] args) {

		gameMenu();

		System.exit(0);
	}

	/**
	 * Main menu. Deals with all of the options in the menu (in the graphical interface).
	 * Allows the player to choose the console.
	 */
	private static void gameMenu() {
		
		int state = 0;
		int GRAPHICAL = 1;
		int CONSOLE = 0;
		int mode = CONSOLE;
		
		GameLogic game;
		MenuWindow menuWindow;
		InputHandler menuHandler;
		Thread inputMenuThread;
		
		game = null;
		menuHandler = null;
		menuWindow = null;
		
		/*
		 * 
		 */
		switch(JOptionPane.showConfirmDialog(null, "Do you wish to play in graphical mode?")) {
		case JOptionPane.YES_OPTION: // GRAPHICAL

			menuWindow = new MenuWindow("Menu");
			menuHandler = new InputHandler(menuWindow);
			
			inputMenuThread = new Thread(menuHandler);
			inputMenuThread.start();
			
			mode = GRAPHICAL;
			
			break;
		case JOptionPane.NO_OPTION: // CONSOLE
			mode = CONSOLE;
			break;
		case JOptionPane.CANCEL_OPTION: // CANCEL
			return;
		default:
			return;
		}

		while(state != -1) {
			
			int EXIT = -1;
			int NEW_GAME = -2;
			
			if(mode == GRAPHICAL) {
				
				int returnValue = EXIT;
				int innerState = 0;
				
				/* The player choses an option from the menu */
				innerState = menuHandler.getNextCommand();
				
				if(innerState > 0) {
					menuHandler.removeCommand();
				}
				
				switch(innerState) {
				/*	___________________________________________
				 * 
				 * 					PLAY
				 *  ___________________________________________
				 */
				case 1:
					menuWindow.setVisible(false);
					
					game = new GameLogic(mode);

					do {
						if(game.isValid()) {
							game.init();
							game.initNonSerializable();
							returnValue = game.loop();
						}
						
					}while(returnValue == NEW_GAME);

					menuWindow.setVisible(true);
					break;
				case 2: // EDITOR
					menuWindow.setVisible(false);
					
					menuWindow.setVisible(true);
					break;
				case 3: // CREDITS
					break;
				case 4: // QUIT
					return;
				default:// DO NOTHING
					break;
				}
				
			}
			else {
				do {
					game = new GameLogic(new GameConfig(mode));
					game.init();
					state = game.loop();
				}while(state == NEW_GAME);
			}
		}

	}
	
}
