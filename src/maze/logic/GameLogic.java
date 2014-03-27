
package maze.logic;

import java.util.Random;

import maze.cli.*;
import maze.gui.GameWindow;
import maze.gui.GameWindow_old;

public class GameLogic {

	private Maze maze;
	private Hero hero;
	private Eagle eagle;
	private Dragon[] dragons;
	private Element sword;
	
	private Task[] tasks;
	private int TASKNUM = 3;
	
	private int mazeSize;
	private int difficulty;
	private int mazeDragons;

	private Input in;
	private Output out;
	private GameWindow gameWindow;
	private GameConfig config;

	private enum MSG {FOUND_SWORD, KILLED_DRAGON, GET_KEY};

	public GameLogic() { 
	}

	public GameLogic(GameConfig config, double dragonPerc) {
		
		this.config = config;
		
		this.mazeSize = config.getMazeSize();
		this.difficulty = config.getDifficulty();
		
		this.mazeDragons = (int) (mazeSize * mazeSize * dragonPerc);
	}

	/**
	 * Initializes all game variables
	 */
	public void init() {
		
		// Creates the Labyrinth
		maze = new Maze(mazeSize);

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

	}
	
	public GameConfig getConfig() {
		return this.config;
	}
	
	public void setConfig(GameConfig config) {
		this.config = config;
	}

	/**
	 * Returns the game maze
	 * @return the maze
	 */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * Sets the variable maze with the passed value
	 * @param maze the maze to set
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	/**
	 * Returns the hero
	 * @return the hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * Sets the hero with the passed value
	 * @param hero
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	/**
	 * Returns the eagle
	 * @return the eagle
	 */
	public Eagle getEagle() {
		return eagle;
	}

	/**
	 * Sets the eagle with the passed value
	 * @param eagle the eagle to set
	 */
	public void setEagle(Eagle eagle) {
		this.eagle = eagle;
	}

	/**
	 * @return the dragons
	 */
	public Dragon[] getDragons() {
		return dragons;
	}

	/**
	 * @param dragons the dragons to set
	 */
	public void setDragons(Dragon[] dragons) {
		this.dragons = dragons;
	}

	/**
	 * @return the sword
	 */
	public Element getSword() {
		return sword;
	}

	/**
	 * @param sword the sword to set
	 */
	public void setSword(Element sword) {
		this.sword = sword;
	}

	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//					HERO					//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	/**
	 * 
	 */
	public void moveHero(int command) {
		
		hero.move(this, command);
	}

	/**
	 * 
	 */
	public boolean checkIfHeroFoundSword() {

		if(!hero.isArmed() && hero.foundSword(sword)) {

			out.drawMsg(MSG.FOUND_SWORD.ordinal());

			hero.arm();
			
			if(eagle.hasSword()) {
				eagle.setHasSword(false);
			}
			
			sword.setX(0);
			sword.setY(0);
			
			return true;
		}
	
		return false;
	}
	
	public boolean checkIfHeroFoundEagle() {

		if((!hero.hasEagle()) && (hero.foundEagle(eagle))) {

			hero.setHasEagle(true);
			out.drawMsg(3);

			if(eagle.hasSword()) {

				hero.arm();

				eagle.setHasSword(false);
				eagle.setUseful(false);
			}
			else {
				hero.setSymbol('Y');
			}

			eagle.setMoving(false);
			eagle.setFlying(false);
			
			return true;
		}
		
		return false;
	}

	/**
	 * 
	 */
	public boolean checkIfHeroFoundDragon() {

		for(Dragon dragon: dragons) {

			if(dragon.isAlive() && hero.foundDragon(dragon)) {

				if(hero.isArmed()) {

					out.drawMsg(MSG.KILLED_DRAGON.ordinal());
					dragon.die();
				}
				else {
					if(dragon.isAwake()) {
						hero.die();
					}
				}
				
				return true;
			}
		}	
		
		return false;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean heroWon() {

		if(hero.isAt(maze.getExit())) {

			if(allDragonsAreDead()) {
				return true;
			}
			else {
				hero.moveBack();
			}
		}
		
		return false;
	}
	
	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//					DRAGONS					//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	
	/**
	 * 
	 */
	public void setAllDragonStates() {

		if(difficulty == 2) {
			
			for(Dragon dragon: dragons) {

				if(dragon.isAlive()) {
					dragon.setDragonState();
				}	
			}
		}
	}

	/**
	 * 
	 */
	public void moveAllDragons() {

		for(Dragon dragon: dragons) {

			if(dragon.isAwake() && dragon.isAlive()) {

				Random r = new Random();

				dragon.move(this, r.nextInt(4));

				if(dragon.isAt(maze.getExit())) {
					dragon.moveBack();
				}
			}
		}

	}

	public boolean checkIfDragonFoundSword() {

		for(Dragon dragon: dragons) {
				dragon.setHasSword(false);
		}

		for(Dragon dragon: dragons) {
			
			if(dragon.foundSword(sword)) {
				dragon.setHasSword(true);
				return true;
			}		
		}

		return false;
	}

	public void checkDragons() {

		if(difficulty > 1) {

			moveAllDragons();
			checkIfDragonFoundSword();
		}
	}
	
	/**
	 * @return
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
	 * 
	 * @return
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

	public void sendEagle() {
		if(hero.hasEagle()) {
			hero.setHasEagle(false);
			hero.setSymbol('H');
			eagle.sendEagle();
		}
	}
	
	public boolean checkIfEagleFoundDragon() {

		if(!eagle.isFlying() && !hero.hasEagle()) {

			for(Dragon dragon : dragons) {
				
				if(eagle.foundDragon(dragon)) {

					eagle.die();

					if((sword != null) && eagle.hasSword()) {

						eagle.setHasSword(false);
						eagle.setMoving(false);
						eagle.setFlying(false);

						sword.setX(eagle.getX());
						sword.setY(eagle.getY());
					}

					return true;
				}
			}
		}

		return false;
	}
	
	public boolean checkIfEagleFoundSword() {
		
		if(!eagle.hasSword() && eagle.foundSword(sword)) {
			eagle.setHasSword(true);
			eagle.setFlying(false);
			
			return true;
		}
		
		return false;
	}
	
	public void checkEagle() {

		if(eagle != null && eagle.isUseful()) {
			
			// If the hero has the eagle, he carries her
			if(hero.hasEagle()) {
				eagle.updatePosition(hero.getX(), hero.getY());
			}

			// If the eagle is on the move
			if(eagle.isAlive() && eagle.isMoving()) {
				
				if(!eagle.hasSword()) {
					eagle.moveToSword(sword);
					
					checkIfEagleFoundSword();
					
				}
				else {
					
					eagle.moveBack();
					
					sword.setX(eagle.getX());
					sword.setY(eagle.getY());
				}
				
			}
			
			// If the eagle finds a dragon, it dies.
			if(checkIfEagleFoundDragon()) {
				out.debugPrint("> Eagle found dragon");
			}
		}
	}

	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//					MISC					//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	public int getInput() {
		
		int command = -1;
		
		if(config.isGraphical()) { // Get key strokes

			do{

				if(gameWindow.getKeyCode() != 0) {
					command = getCurrentCommand(gameWindow.getKeyCode());
				}

			}while(command == -1);

			gameWindow.resetKeyCode();

		}
		else { // Get command line keys

			out.drawMsg(MSG.GET_KEY.ordinal());
			command = in.get();
		}
		
		return command;
	}
	
	public void runCommand(int command) {

		switch(command) {
		case 4: // SPACE
			sendEagle();
			break;
		default:
			moveHero(command);
			break;
		}
	}
	
	/**
	 * 
	 */
	public void createTasks() {
		
		tasks[0] = new Task("Get a weapon.");
		tasks[1] = new Task("Kill all dragons.");
		tasks[2] = new Task("Find the exit.");
	}
	
	/**
	 * 
	 */
	public void checkTasks() {
		
		if(hero.isArmed()) {
			tasks[0].setDone(true);
		}
		
		if(allDragonsAreDead()) {
			tasks[1].setDone(true);
		}
		
		if(heroWon()) {
			tasks[2].setDone(true);
		}
	}
	
	/**
	 * Draws the game board
	 * Generates a board with the complete maze and all the elements to be sent to the output class. 
	 */
	public void drawGameBoard() {
		
		char board[][] = new char[maze.getSize()][maze.getSize()];
		
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
		if(!hero.isArmed() && noDragonIsUponSword() && !eagle.hasSword()) {
			board[sword.getX()][sword.getY()] = sword.getSymbol();
		}
		
		out.drawBoard(board);
	}
	
	public void drawGameOver() {
		
		out.drawBoard(maze);
		out.drawGoal(tasks);
		out.drawGameOver(hero.isAlive());
	}

	public void drawMenu() {
		
		if(config.isConsole()) {
			out.drawCommands();
			out.drawGoal(tasks);
		}

		if(config.isGraphical()) {
			// print commands/goals
		}
	}
	
	public int getCurrentCommand(int keyCode) {
		
		for(int i = 0; i < config.getGameKeyCodes().length; i++) {
			
			if(config.getGameKeyCodes()[i] == keyCode) {
				
				return i;
			}
		}
		
		return -1;
	}

	// ++++++++++++++++++++++++++++++++++++++++	//
	//											//
	//			   MAIN GAME LOOP				//
	//											//
	// ++++++++++++++++++++++++++++++++++++++++	//
	
	public void loop() {

		int command;

		// +++++++++++++++++++++++++++++++++++++
		//				Begin Loop
		// +++++++++++++++++++++++++++++++++++++
		while(hero.isAlive()) {
			
			drawMenu();
			
			drawGameBoard();
			
			setAllDragonStates();
			
			command = getInput();
			
			runCommand(command);
			
			checkEagle();

			checkIfHeroFoundEagle();
			
			checkIfHeroFoundSword();

			checkDragons();

			checkIfHeroFoundDragon();

			checkTasks();
			
			if(heroWon()) break;
			
			gameWindow.paint();

		}	
		// +++++++++++++++++++++++++++++++++++++
		//				END OF LOOP
		// +++++++++++++++++++++++++++++++++++++
		
		drawGameOver();
	}


}
