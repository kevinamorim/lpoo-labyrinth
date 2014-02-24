
public class GameLogic {
	
	private static Input in;
	private static Maze maze;
	private static Hero hero;
	private static Dragon dragon;
	private static Element sword; 
	
	public static void init() {
		// Creates a new Input type object (receives keyboard inputs)
		in = new Input();

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
		// -- BEGIN Game Loop
		while(true) {

			// Gets user input.
			in.getInput();

			// Handles user input.
			for(int i = 0; i < Input.NKEYS; i++) {

				if(in.getKeys()[i]) {

					Input.KEY direction = Input.KEY.values()[i];

					hero.move(direction, maze);
					break;
				}
			}

			// Checks if the player is at the Labyrinth exit
			if(maze.isAtExit(hero)) {
				// If the dragon is slained our hero may sucessfully exit the Labyrinth 
				if(!dragon.isAlive()) break;
			}

			// Checks if the player has found the sword
			// If so, the player's char goes from 'H' (hero) to 'A' (armed hero)
			if(maze.foundSword(hero)) {
				System.out.println("Found sword!");
				hero.setArmed(true);
				hero.setSymbol('A');
			}

			/*
			 * Moves dragon and draws it to the maze before getting user input.
			 */
			if(dragon.isAlive()) {
				dragon.MoveDragon(maze);
				maze.setPosition(dragon);
			}

			// Checks if the player has encountered the Dragon, that terrible beast!
			if(maze.foundDragon(hero, dragon) && (dragon.isAlive())) {
				// If the player is armed, it slains the Dragon
				if(hero.isArmed()) {
					System.out.println("You killed that f***ing dragon!");
					maze.killDragon(dragon);
				}
				// If the player is unarmed, it gets killed
				else {
					hero.setAlive(false);
					break;
				}
			}

			// Only draws the sword if the dragon is not upon it and if it's not taken by the player.
			if(!dragon.hasSword() && !hero.isArmed()) maze.setPosition(sword);

			maze.setPosition(hero);

			maze.DrawBoard();

		}

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
