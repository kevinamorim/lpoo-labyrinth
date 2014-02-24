public class Game {

	public static void main(String[] args) {

		// Creates a new Input type object (receives keyboard inputs)
		Input in = new Input();

		// Creates the Labyrinth
		Labyrinth lab = new Labyrinth(9);

		// Creates our hero/player
		Hero player = new Hero(lab);

		// Sets the player's position on the labyrinth (effectively places it's reresenting char)
		lab.setPlayer(player);

		// Creates our sword
		Tile sword = new Tile(lab, 'E');

		// Sets the sword's position on the labyrinth (effectively places it's reresenting char)
		lab.setSword(sword);

		// Creates our evil Dragon!
		Dragon dragon = new Dragon(lab);

		// Sets the dragon's position on the labyrinth (effectively places it's reresenting char)
		lab.setDragon(dragon);
		
		// Draws the Labyrinth matrix/board
		lab.DrawBoard();

		// -- BEGIN Game Loop
		while(true) {

			// Gets user input.
			in.getInput();

			// Handles user input.
			for(int i = 0; i < Input.NKEYS; i++) {

				if(in.getKeys()[i]) {

					Input.KEY direction = Input.KEY.values()[i];
					
					player.move(direction, lab);
					break;
				}
			}

			// Checks if the player is at the Labyrinth exit
			if(lab.isAtExit(player)) {
				// If the dragon is slained our hero may sucessfully exit the Labyrinth 
				if(!dragon.isAlive()) {
					System.out.println("Done");
					break;
				}
				else
					player.moveBack(lab);
			}

			// Checks if the player has found the sword
			// If so, the player's char goes from 'H' (hero) to 'A' (armed hero)
			if(lab.foundSword(player)) {
				System.out.println("Found sword!");
				player.setArmed(true);
				player.setSymbol('A');
			}

			/*
			 * Moves dragon and draws it to the maze before getting user input.
			 */
			if(dragon.isAlive()) {
				dragon.MoveDragon(lab);
				lab.setDragon(dragon);
			}

			// Checks if the player has encountered the Dragon, that terrible beast!
			if(lab.foundDragon(player, dragon) && (dragon.isAlive())) {
				// If the player is armed, it slains the Dragon
				if(player.isArmed()) {
					System.out.println("You killed that f***ing dragon!");
					lab.killDragon(dragon);
				}
				// If the player is unarmed, it gets killed
				else {
					player.setAlive(false);
					break;
				}
			}
			
			// Only draws the sword if the dragon is not upon it and if it's not taken by the player.
			if(!dragon.hasSword() && !player.isArmed()) lab.setSword(sword);
			
			lab.setPlayer(player);
			
			lab.DrawBoard();

		}

		if(player.isAlive()) {
			System.out.println(" WIN ");
			lab.setDragon(dragon);
			lab.setPlayer(player);
		}
		else {
			System.out.println(" LOSE ");
			lab.setPlayer(player);
			lab.setDragon(dragon);
		}
		
		lab.DrawBoard();

	}


}
