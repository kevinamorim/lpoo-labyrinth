package maze.logic;

import maze.cli.*;
import maze.gui.ConfigurationWindow;
import maze.gui.GameWindow;
import maze.gui.InputHandler;

public class GameLogic {
	
	private char board[][];

	private Maze maze;
	private Hero hero;
	private Eagle eagle;
	private Dragon[] dragons;
	private Element sword;
	
	private Task[] tasks;

	private int TASKNUM = 3;
	
	private int mazeDragons;

	private Input in;
	private InputHandler inputHandler;
	private Output out;
	
	private GameWindow gameWindow;
	private ConfigurationWindow configWindow;
	
	private GameConfig config;

	private enum MSG {FOUND_SWORD, KILLED_DRAGON, GET_KEY};
	
	private boolean done;
	
	private final int FRAMES_PER_SECOND = 30;
	private final int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
	
	private double next_game_tick = System.currentTimeMillis();
	int sleep_time = 0;

	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//			   INITIALIZATION			    //
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	
	/**
	 * GameLogic constructor.
	 * Calls methot init() for parameters initialization.
	 * 
	 * @param config : current game configuration
	 */
	public GameLogic(GameConfig config) {
		
		this.config = config;
		
		init();
		
	}

	/** 
	 * Default GameLogic constructor.
	 */
	public GameLogic() {}

	/**
	 * Initializes all game parameters.
	 */
	public void init() {
		
		done = false;
		
		board = new char[config.getMazeSize()][config.getMazeSize()];
		
		mazeDragons = (int) (config.getMazeSize() * config.getMazeSize()  * config.getDragonPerc());
		
		// Creates the Labyrinth
		maze = new Maze(config.getMazeSize());

		// Creates our hero/player
		hero = new Hero(this);
		
		// Creates our eagle
		eagle = new Eagle(hero.getX(), hero.getY(), 'V');

		// Creates our sword
		sword = new Element(this, 'E');
		
		// Creates our evil Dragons!
		dragons = new Dragon[mazeDragons];

		for(int i = 0; i < dragons.length; i++) {
			dragons[i] = new Dragon(this);
		}
		
		// Creates our Input
		in = new Input();
		
		// Creates our Output
		out = new Output();
		
		tasks = new Task[TASKNUM];

		createTasks();
		
		gameWindow = new GameWindow(this);
		
		inputHandler = new InputHandler(gameWindow);
		
		// Starts game loop.
		loop();

	}

	
	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//					DRAGONS					//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	
	/**
	 * Checks if all dragons are dead.
	 * 
	 * @return true if all dragons are dead
	 */
	public boolean allDragonsAreDead() {

		for(Dragon dragon: dragons) {
			if(dragon.isAlive()) {
				return false;
			}	
		}

		return true;
	}

	/**
	 * Checks if there is a dragon with a sword. 
	 * 
	 * @return true if any dragon has a sword
	 */
	public boolean noDragonIsUponSword() {

		for(Dragon dragon: dragons) {
			if(dragon.hasSword()) {
				return false;
			}
		}

		return true;
	}

	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//					EAGLE					//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//

	/**
	 * Sends the eagle towards the sword. 
	 * The hero looses the eagle and the eagle starts moving.
	 */
	public void sendEagle() {
		if(hero.hasEagle()) {
			hero.setHasEagle(false);
			hero.setSymbol('H');
			eagle.sendEagle();
		}
	}

	
	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//					TASKS					//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	
	/**
	 * Creates the list of user tasks.
	 * This tasks are objectives to be completed by the hero/player.
	 */
	public void createTasks() {
		
		tasks[0] = new Task("Get a weapon.");
		tasks[1] = new Task("Kill all dragons.");
		tasks[2] = new Task("Find the exit.");
	}
	
	/**
	 * Checks the state of all the tasks.
	 */
	public void checkTasks() {
		
		if(hero.hasSword()) {
			tasks[0].setDone(true);
		}
		
		if(allDragonsAreDead()) {
			tasks[1].setDone(true);
		}
		
		if(hero.hasWon()) {
			tasks[2].setDone(true);
		}
	}
	
	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//					INPUT					//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	
	/** 
	 * Gets user input.
	 * Depending whether the game is in graphic mode or console mode the input method is different.
	 * 
	 * @return keycode : input keycode
	 */
	public int getInput() {

		if(config.getMode() == 1) { // Get key strokes
			return gameWindow.getKeyCode();
		}
		else { // Get command line keys
			out.drawMsg(MSG.GET_KEY.ordinal());
			return in.get();
		}
	}
	
	/** 
	 * Executes a hero command (receives a command - after it has been converted from keycode - and executes the order).
	 * 
	 * @param command : order to execute
	 */
	public void runCommand(int command) {

		switch(command) {
		case 4: // SPACE
			sendEagle();
			break;
		default: // 0,1,2,3
			hero.move(this, command);
			break;
		}
	}

	/**
	 * Converts a keycode into a game command.
	 * 
	 * @param keyCode : key pressed by the user
	 * @return game command corresponding to the keycode
	 */
	public int getCurrentCommand(int keyCode) {
		
		for(int i = 0; i < config.getGameKeyCodes().length; i++) {
			
			if(config.getGameKeyCodes()[i] == keyCode) {
				
				return i;
			}
		}
		
		return -1;
	}

	
	/**
	 * Draws the game board.
	 * Generates a board with the complete maze and all the elements to be sent to the output class. 
	 */
	public void setGameBoard() {
		
		// Get board without elements.
		for(int i = 0; i < maze.getSize(); i++) {
			for(int j = 0; j < maze.getSize(); j++) {
				board[i][j] = maze.getTiles()[i][j];
			}
		}
		
		// Includes exit.
		board[maze.getExit().getX()][maze.getExit().getY()] = maze.getExit().getSymbol();
		
		// Includes hero.
		board[hero.getX()][hero.getY()] = hero.getSymbol();
		
		// Includes eagle.
		if((eagle != null) && !hero.hasEagle() && eagle.isAlive()) {
			board[eagle.getX()][eagle.getY()] = eagle.getSymbol();
		}
		
		// Includes all dragons.
		for(int i = 0; i < dragons.length; i++) {
			if(dragons[i].isAlive()) board[dragons[i].getX()][dragons[i].getY()] = dragons[i].getSymbol();
		}
		// Includes sword.
		if(!hero.hasSword() && noDragonIsUponSword() && !eagle.hasSword()) {
			board[sword.getX()][sword.getY()] = sword.getSymbol();
		}
	}



	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//			   MAIN GAME LOOP				//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	
	/**
	 * Main game loop.
	 * 
	 * @return Stopping state. 
	 * 			0 - End of game.
	 * 			1 - New game.
	 */
	public int loop() {
		
		Thread inputThread = new Thread(inputHandler);
		inputThread.start();

		int command = -1;

		// First draw.
		if(config.getMode() == 0) {
			out.draw(this);
		} else if(config.getMode() == 1) {
			gameWindow.paint();
		}

		// +++++++++++++++++++++++++++++++++++++
		//				Begin Loop
		// +++++++++++++++++++++++++++++++++++++
		while(hero.isAlive() && !done) {
			
			command = inputHandler.getNextCommand();
			
			if(getCurrentCommand(command) >= 0) {
				
				inputHandler.removeCommand();

				setGameBoard();
				
				updateAllDragons();
				
				runCommand(getCurrentCommand(command));
				
				eagle.update(this);

				hero.update(this);

				checkTasks();
				
				if(hero.hasWon()) {
					done = true;
					break;
				}
				
				if(config.getMode() == 0) {
					out.draw(this);
				} else if(config.getMode() == 1) {
					gameWindow.paint();
				}

			}
			
		}	
		// +++++++++++++++++++++++++++++++++++++
		//				END OF LOOP
		// +++++++++++++++++++++++++++++++++++++
		
		if(gameWindow.getState() == 1) {
			gameWindow.getFrame().dispose();
			return 1;
		} else {
			out.drawGameOver(this);
			return 0;
		}

		
	}
	
	/**
	 * Calls method update(GameLogic game) for all dragons.
	 * For more info consult the methods.
	 */
	private void updateAllDragons() {
		for(Dragon dragon: dragons) {
			dragon.update(this);
		}
	}

	/**
	 * Sets [done] flag parameter to true.
	 */
	public void stop() {
		done = true;
	}


	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//			   GETTERS/SETTERS				//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	

	/**
	 * Gets the game instance of [maze].
	 * 
	 * @return the current instance of Maze
	 */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * Sets the game instance of [sword].
	 * 
	 * @param maze : parameter to be set
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;
	}
	
	/**
	 * Gets the game instance of [hero].
	 * 
	 * @return the hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * Sets the game instance of [hero].
	 * 
	 * @param hero : parameter to be set
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}
	

	/**
	 * Gets the game instance of [sword].
	 * 
	 * @return the sword
	 */
	public Element getSword() {
		return sword;
	}

	/**
	 * Sets the game instance of [sword].
	 *  
	 * @param sword : parameter to be set
	 */
	public void setSword(Element sword) {
		this.sword = sword;
	}

	
	/**
	 * Gets the game instance of [eagle].
	 *  
	 * @return the eagle
	 */
	public Eagle getEagle() {
		return eagle;
	}

	/**
	 * Sets the game instance of [eagle].
	 * 
	 * @param eagle : parameter to be set
	 */
	public void setEagle(Eagle eagle) {
		this.eagle = eagle;
	}
	
	/**
	 * Gets the game instances of [dragon] from the array [dragons].
	 * 
	 * @return the dragons
	 */
	public Dragon[] getDragons() {
		return dragons;
	}

	/**
	 * Sets the array [dragons].
	 * 
	 * @param dragons : parameter to be set
	 */
	public void setDragons(Dragon[] dragons) {
		this.dragons = dragons;
	}
	
	/**
	 * Gets the current game configuration.
	 * 
	 * @return GameConfig configuration
	 */
	public GameConfig getConfig() {
		return this.config;
	}
	
	/**
	 * Sets the current game configuration.
	 * 
	 * @param config : configuration to set
	 */
	public void setConfig(GameConfig config) {
		this.config = config;
	}

	/**
	 * Gets the array containing the tasks.
	 * 
	 * @return the tasks
	 */
	public Task[] getTasks() {
		return tasks;
	}

	/**
	 * Sets the array containing the tasks.
	 * 
	 * @param tasks : array of tasks to be set
	 */
	public void setTasks(Task[] tasks) {
		this.tasks = tasks;
	}

	/**
	 * Gets the array containing the game board chars.
	 * 
	 * @return the board
	 */
	public char[][] getBoard() {
		return board;
	}

	/**
	 * Sets the array containing the game board chars.
	 * 
	 * @param board : array of game board chars to be set
	 */
	public void setBoard(char board[][]) {
		this.board = board;
	}
	
}
