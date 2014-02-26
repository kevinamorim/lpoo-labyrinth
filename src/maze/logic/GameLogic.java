
package maze.logic;

import maze.cli.*;

public class GameLogic {
	
	private Maze maze;
	private Hero hero;
	private Dragon[] dragons;
	private Element sword;
	
	private Task[] tasks;
	private int TASKNUM = 3;
	
	private int mazeSize = 9;
	private int mazeDragons = 3;

	private Input in;
	private Output out;
	
	public enum KEY {NONE, UP, RIGHT, DOWN, LEFT};
	private enum MSG {FOUND_SWORD, KILLED_DRAGON, GET_KEY};
	
	/**
	 * 
	 */
	public void init() {
		
		// Creates the Labyrinth
		maze = new Maze(mazeSize);

		// Creates our hero/player
		hero = new Hero(maze);

		// Sets the player's position on the labyrinth (effectively places it's reresenting char)
		maze.drawElement(hero);

		// Creates our sword
		sword = new Element(maze, 'E');

		// Sets the sword's position on the labyrinth (effectively places it's reresenting char)
		maze.drawElement(sword);

		// Creates our evil Dragon!
		dragons = new Dragon[mazeDragons];

		for(int i = 0; i < dragons.length; i++) {

			dragons[i] = new Dragon(maze);
		}

		// Sets the dragon's position on the labyrinth (effectively places it's reresenting char)
		maze.drawElement(dragons);
		
		// Creates our Input
		in = new Input();
		
		// Creates our Output
		out = new Output();
		
		tasks = new Task[TASKNUM];
		
		createTasks();
		
	}

	/**
	 * 
	 */
	private void createTasks() {
		
		tasks[0] = new Task("Get a weapon.");
		tasks[1] = new Task("Kill all dragons.");
		tasks[2] = new Task("Find the exit.");
	}
	
	/**
	 * 
	 */
	private void checkTasks() {
		
		if(hero.isArmed()) {
			tasks[0].setDone(true);
		}
		
		if(allDragonsAreDead()) {
			tasks[1].setDone(true);
		}
	}
		
	/**
	 * @return
	 */
	private boolean allDragonsAreDead() {
		
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
	private void moveHero() {
		
		KEY command = KEY.values()[in.get()];
		
		hero.move(command, maze);
	}
	
	/**
	 * @return
	 */
	private boolean checkIfHeroWon() {

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
	private void checkForFoundSword() {

		if(!hero.isArmed() && hero.foundSword(sword)) {

			out.drawMsg(MSG.FOUND_SWORD.ordinal());

			hero.arm();
		}
	}

	/**
	 * 
	 */
	private void setAllDragonStates() {
		
		//out.debugPrint("> setAllDragonStates() [ ]");

		for(Dragon dragon: dragons) {

			if(dragon.isAlive()) {
				dragon.setDragonState();
			}	
		}
		
		//out.debugPrint("> setAllDragonStates() [X]");
	}

	/**
	 * 
	 */
	private void moveAllDragons() {
		
		//out.debugPrint("> moveAllDragons() [ ]");
		
		for(Dragon dragon: dragons) {

			if(dragon.isAwake() && dragon.isAlive()) {

				dragon.move(maze);

				if(dragon.foundSword(sword)) {
					dragon.setHasSword(true);
				}
				else {
					dragon.setHasSword(false);
				}

				if(dragon.isAt(maze.getExit())) {
					dragon.moveBack();
				}
			}
		}
		
		//out.debugPrint("> moveAllDragons() [X]");
	}
	
	/**
	 * 
	 */
	private void checkForDragonEncounters() {
		
		//out.debugPrint("> checkForDragonEncounters() [ ]");

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
		
		//out.debugPrint("> checkForDragonEncounters() [X]");
	}

	public void loop() {

		out.drawCommands();
		out.drawBoard(maze);
		out.drawGoal(tasks);

		while(hero.isAlive()) {

			out.drawMsg(MSG.GET_KEY.ordinal());

			setAllDragonStates();
			
			moveHero();
			
			if(checkIfHeroWon() == true) {
				break;
			}
			
			checkForFoundSword();
			
			moveAllDragons();
			
			checkForDragonEncounters();

			drawAllElements();
			
			checkTasks();

			out.drawCommands();
			out.drawBoard(maze);
			out.drawGoal(tasks);
		}
		
		// END OF LOOP
		
		boolean won = hero.isAlive();
		
		if(won) {
			maze.drawElement(dragons);
			maze.drawElement(hero);
		}
		else {
			maze.drawElement(hero);
			maze.drawElement(dragons);
		}
		
		out.drawBoard(maze);
		out.drawGameOver(won);
		
	}

	private void drawAllElements() {
		
		// Dragons
		maze.drawElement(dragons);
		
		// Sword - only draws if it is not currently guarded
		for(Dragon dragon: dragons) {

			if(!dragon.hasSword() && !hero.isArmed()) {

				maze.drawElement(sword);
			}
		}
		
		// Hero
		maze.drawElement(hero);
		
	}

}
