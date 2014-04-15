package maze.logic;
//import maze.gui.ConfigurationWindow;
//import maze.gui.MenuWindow;
import java.io.File;
import java.io.FileFilter;

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
				
				/* The player choses an option from the menu */
				state = menuHandler.getNextCommand();
				
				if(state > 0) {
					menuHandler.removeCommand();
				}
				
				switch(state) {
				case 1: // PLAY
					int EXIT = -1;
					int NEW_GAME = 1;
					int returnValue;

					/* Shows the configurations window */
					menuWindow.setVisible(false);

					do {
						game = new GameLogic(mode);
						if(game.valid) {
							returnValue = game.loop();
						}
						else {
							returnValue = EXIT;
						}
						
					}while(returnValue == NEW_GAME);

					menuWindow.setVisible(true);
					break;
				case 2: // LOAD GAME
					GameIO gameIO = new GameIO();
					JFileChooser fileChooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter(".sav files", new String[] {"sav"});
					fileChooser.setFileFilter(filter);
					fileChooser.setCurrentDirectory(new File( "." ));
					
					if (fileChooser.showOpenDialog(menuWindow) == JFileChooser.APPROVE_OPTION) {
						String fileName = fileChooser.getSelectedFile().getName();
						gameIO.load(game,fileName);
					}
					break;
				case 3: // OPTIONS
					break;
				case 4: // CREDITS
					state = 0;
					break;
				default:// QUIT
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
