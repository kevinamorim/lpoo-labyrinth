package maze.logic;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import maze.gui.InputHandler;
import maze.gui.MenuWindow;
import maze.io.GameIO;

public class Game {

	public static void main(String[] args) {

		gameMenu();

		System.exit(0);
	}

	private static void gameMenu() {
		
		int state = -1;
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

		while(state != 0) {

			if(mode == GRAPHICAL) {
				
				int EXIT = -1;
				int NEW_GAME = -2;
				int returnValue = EXIT;
				
				/* The player choses an option from the menu */
				state = menuHandler.getNextCommand();
				
				if(state > 0) {
					menuHandler.removeCommand();
				}
				
				switch(state) {
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
				game = new GameLogic(new GameConfig(mode));
				state = game.loop();
			}
		}

	}
	
}
