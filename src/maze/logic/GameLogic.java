
package maze.logic;

import java.util.Random;

import maze.cli.*;
import maze.gui.GameWindow;

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
	
	// KeyCodes
	// For now: W,D,S,A -> UP,RIGHT,DOWN,LEFT
	private int gameKeyCodes[] = {87, 68, 83, 65};
	
	private boolean inputMethodKeyboard = true;

	private Input in;
	private Output out;
	private GameWindow gameWindow;

	private enum MSG {FOUND_SWORD, KILLED_DRAGON, GET_KEY};

	public GameLogic() { 
		out = new Output();
		gameWindow = new GameWindow(this);
	}

	public GameLogic(GameConfig config, double dragonPerc) {
		
		this.mazeSize = config.getMazeSize();
		this.difficulty = config.getDifficulty();
		
		this.mazeDragons = (int) (mazeSize * mazeSize * dragonPerc);
	}

	/**
	 * 
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

	/**
	 * @return the maze
	 */
	public Maze getMaze() {
		return maze;
	}

	/**
	 * @param maze the maze to set
	 */
	public void setMaze(Maze maze) {
		this.maze = maze;
	}

	/**
	 * @return the hero
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * @param hero the hero to set
	 */
	public void setHero(Hero hero) {
		this.hero = hero;
	}

	/**
	 * @return the eagle
	 */
	public Eagle getEagle() {
		return eagle;
	}

	/**
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
		
		if(checkIfHeroWon()) {
			tasks[2].setDone(true);
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
	 */
	public void moveHero(int command) {
		
		hero.move(this, command);
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean checkIfHeroWon() {

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

	/**
	 * 
	 */
	public void checkForFoundSword() {

		if(!hero.isArmed() && hero.foundSword(sword)) {

			out.drawMsg(MSG.FOUND_SWORD.ordinal());

			hero.arm();
		}
	}
	
	public void checkForEagleFoundSword() {
		if(!eagle.hasSword() && eagle.foundSword(sword)) {
			eagle.setHasSword(true);
			eagle.setFlying(false);
		}
	}

	
	public void checkForFoundEagle() {
		
		if(!hero.hasEagle() && hero.foundEagle(eagle)) {
			
			hero.setHasEagle(true);
			hero.setSymbol('Y');
			out.drawMsg(3);
			
			if(eagle.hasSword()) {
				hero.arm();
				eagle.setHasSword(false);
			}
		}
		
	}
	
	/**
	 * 
	 */
	public void setAllDragonStates() {

		for(Dragon dragon: dragons) {

			if(dragon.isAlive()) {
				dragon.setDragonState();
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
	
	public void checkIfDragonsFoundSword() {
		
		for(Dragon dragon: dragons) {
			
			if(dragon.foundSword(sword)) {
				dragon.setHasSword(true);
			}
			else {
				dragon.setHasSword(false);
			}
		}

	}
	
	/**
	 * 
	 */
	public void checkForDragonEncounters() {

		for(Dragon dragon: dragons) {

			if(hero.foundDragon(dragon) && dragon.isAlive()) {

				if(hero.isArmed()) {

					out.drawMsg(MSG.KILLED_DRAGON.ordinal());
					dragon.die();
				}
				else {
					if(dragon.isAwake())
						hero.die();
				}
			}
		}
		
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

	
	public void sendEagle() {
		if(!eagle.isMoving()) {
			hero.setHasEagle(false);
			hero.setSymbol('H');
			eagle.sendEagle();
		}
	}
	
	public void checkKillEagle() {
		
		
		for(Dragon dragon : dragons) {
			if(eagle.foundDragon(dragon)) {
				if(!eagle.isFlying()) {
					eagle.setAlive(false);
					eagle.setHasSword(false);
					
					if(sword != null) {
						sword.setX(eagle.getX());
						sword.setY(eagle.getY());
					}

				}
			}
		}
		
	}
	
	/**
	 * Generates a board with the complete maze and all the elements to be sent to the output class. 
	 */
	public void draw() {
		
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
		
		if((eagle != null) && !hero.hasEagle() && eagle.isAlive()) board[eagle.getX()][eagle.getY()] = eagle.getSymbol();
		
		// Includes all dragons.
		for(int i = 0; i < dragons.length; i++) {
			if(dragons[i].isAlive()) board[dragons[i].getX()][dragons[i].getY()] = dragons[i].getSymbol();
		}
		// Includes sword.
		if(!hero.isArmed() && noDragonIsUponSword() && !eagle.hasSword()) board[sword.getX()][sword.getY()] = sword.getSymbol();
		
		out.drawBoard(board);
		

	}


	/** 
	 *  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 *    MAIN GAME LOOP
	 *  +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	public void loop() {
		
		int command;
		
		out.drawCommands();
		out.drawGoal(tasks);
		draw();

		while(hero.isAlive()) {

			out.drawMsg(MSG.GET_KEY.ordinal());

			if(difficulty == 2) setAllDragonStates();
			
			// Handles input.
//			if(inputMethodKeyboard)
//				command = waitForKey();
//			else
				command = in.get();
			switch(command) {
			case 4: // SPACE
				sendEagle();
				break;
			default:
				System.out.println("Command: " + command);
				moveHero(command);
				if(hero.hasEagle()) eagle.updatePosition(hero.getX(), hero.getY());
				break;
			}
			// --
			
			if(eagle.isMoving() && eagle.isAlive()) {
				if(!eagle.hasSword()) eagle.moveToSword(maze, sword);
				else {
					eagle.moveBack();
					sword.setX(eagle.getX());
					sword.setY(eagle.getY());
				}
				
				checkForEagleFoundSword();
			}
			
			checkKillEagle();
			
			if(checkIfHeroWon() == true) {
				break;
			}
			
			checkForFoundSword();
			checkForFoundEagle();
			
			if(difficulty > 1) {
				moveAllDragons();
				checkIfDragonsFoundSword();
			}
			
			checkForDragonEncounters();
			
			checkTasks();

			out.drawCommands();
			out.drawGoal(tasks);
			
			draw();
			
		}
		
		// END OF LOOP
		
		boolean won = hero.isAlive();
		
		checkTasks();
		
		out.drawBoard(maze);
		out.drawGoal(tasks);
		out.drawGameOver(won);
		
	}

	private int waitForKey() {
		while(validKeyCode(this.gameWindow.getKeyCode()) == -1) {
			
		}
		return 0;
	}

	private int validKeyCode(int keyCode) {
		for(int i = 0; i < gameKeyCodes.length; i++) {
			if(gameKeyCodes[i] == keyCode) {
				return i;
			}
		}
		
		return -1;
	}
}
