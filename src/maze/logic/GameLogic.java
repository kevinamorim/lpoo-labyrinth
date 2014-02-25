
package maze.logic;

import maze.cli.*;

public class GameLogic {
	
	private static Maze maze;
	private static Hero hero;
	private static Dragon dragon;
	private static Element sword; 
	
	public static enum KEY {NONE,UP,RIGHT,DOWN,LEFT};
	
	public static void init() {
		
		// Creates the Labyrinth
		maze = new Maze(9);

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

		// Draws the Labyrinth matrix/board
		maze.DrawBoard();
	}

	public static void loop() {

		while(true) {
			
			// Receives input
			KEY command = KEY.values()[Input.get()];

			hero.move(command, maze);

			if(hero.isAtExit(maze.getExit())) {
				
				if(!dragon.isAlive()) {
					break;
				}
				else {
					hero.moveBack();
				}
			}

			if(hero.foundSword(sword)) {
				
				System.out.println("Found sword!");
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
				
				maze.setPosition(dragon);
			}

			if(hero.foundDragon(dragon) && dragon.isAlive()) {
				
				if(hero.isArmed()) {
					
					System.out.println("You killed that f***ing dragon!");
					dragon.kill();
				}
				// If the player is unarmed, it gets killed
				else {
					hero.kill();
					break;
				}
			}

			// Only draws the sword if the dragon is not upon it and if it's not taken by the player.
			if(!dragon.hasSword() && !hero.isArmed()) {
				
				maze.setPosition(sword);
			}

			maze.setPosition(hero);

			maze.DrawBoard();

		}
		
		printGameOver();
		
	}
	
	public static void printGameOver() {
		if(hero.isAlive()) {
			System.out.println(" WIN ");
			maze.setPosition(dragon);
			maze.setPosition(hero);
		}
		else {
			System.out.println(" LOSE ");
			maze.setPosition(hero);
			maze.setPosition(dragon);
		}
		
		maze.DrawBoard();
	}
}
