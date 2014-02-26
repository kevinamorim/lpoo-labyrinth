
package maze.logic;

import maze.cli.*;

public class GameLogic {
	
	private Maze maze;
	private Hero hero;
	private Dragon dragon;
	private Element sword;
	
	private Task[] tasks;
	private int TASKNUM = 3;

	private Input in;
	private Output out;
	
	public enum KEY {NONE, UP, RIGHT, DOWN, LEFT};
	private enum MSG {FOUND_SWORD, KILLED_DRAGON, GET_KEY};
	
	public void init() {
		
		// Creates the Labyrinth
		maze = new Maze(17);

		// Creates our hero/player
		hero = new Hero(maze);

		// Sets the player's position on the labyrinth (effectively places it's reresenting char)
		maze.setPosition(hero);

		// Creates our sword
		sword = new Element(maze, 'E');

		// Sets the sword's position on the labyrinth (effectively places it's reresenting char)
		maze.setPosition(sword);

		// Creates our evil Dragon!
		dragon = new Dragon(maze);

		// Sets the dragon's position on the labyrinth (effectively places it's reresenting char)
		maze.setPosition(dragon);
		
		// Creates our Input
		in = new Input();
		
		// Creates our Output
		out = new Output();
		
		tasks = new Task[TASKNUM];
		
		createTasks();
		
	}

	private void createTasks() {
		
		tasks[0] = new Task("Get a weapon.");
		tasks[1] = new Task("Kill all dragons.");
		tasks[2] = new Task("Find the exit.");
	}
	

	private void checkTasks() {
		if(hero.isArmed()) {
			tasks[0].setDone(true);
		}
		
		if(!dragon.isAlive()) {
			tasks[1].setDone(true);
		}
		
//		if(hero.isAtExit(maze.getExit()) && !dragon.isAlive()) {
//			tasks[2].setDone(true);
//		}
	}

	public void loop() {
	
		out.drawGoal(tasks);
		out.drawBoard(maze);
		out.drawCommands();

		while(hero.isAlive()) {
			
			out.drawMsg(MSG.GET_KEY.ordinal());
			
			// Receives input
			KEY command = KEY.values()[in.get()];

			hero.move(command, maze);

			if(hero.isAtExit(maze.getExit())) {
				
				if(!dragon.isAlive()) {
					break;
				}
				else {
					hero.moveBack();
				}
			}

			if(!hero.isArmed() && hero.foundSword(sword)) {
				
				out.drawMsg(MSG.FOUND_SWORD.ordinal());
				hero.arm();
			}
			
			if(dragon.isAlive()) {
				
				dragon.move(maze);
				
				if(dragon.foundSword(sword)) {
					dragon.setHasSword(true);
				}
				else {
					dragon.setHasSword(false);
				}
				
				if(dragon.isAtExit(maze.getExit())) {
					dragon.moveBack();
				}
			}

			if(hero.foundDragon(dragon) && dragon.isAlive()) {
				
				if(hero.isArmed()) {
					
					out.drawMsg(MSG.KILLED_DRAGON.ordinal());
					dragon.kill();
				}
				// If the player is unarmed, it gets killed
				else {
					hero.kill();
				}
			}

			// Only draws the sword if the dragon is not upon it and if it's not taken by the player.
			if(!dragon.hasSword() && !hero.isArmed()) {
				
				maze.setPosition(sword);
			}

			maze.setPosition(dragon);
			maze.setPosition(hero);

			if(!hero.isAlive()) {
				maze.setPosition(dragon);
			}
			
			checkTasks();

			out.drawGoal(tasks);
			out.drawBoard(maze);
			out.drawCommands();
			
		}
		
		boolean won = hero.isAlive();
		if(won) {
			maze.setPosition(dragon);
			maze.setPosition(hero);
		}
		else {
			maze.setPosition(hero);
			maze.setPosition(dragon);
		}
		
		out.drawBoard(maze);
		out.drawGameOver(won);
		
	}
	
}
