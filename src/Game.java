public class Game {
	
	private static char board[][] = {
		{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
		{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'S' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
		{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
	};
	
	public static void main(String[] args) {
		
		// Creates a new Input type object (receives keyboard inputs)
		Input in = new Input();
		
		// Creates the Labyrinth
		Labyrinth lab = new Labyrinth(board);
		
		// Creates our hero/player
		Hero player = new Hero(1, 1);
		
		// Creates our sword
		Sword sword = new Sword(8, 1);
		
		// Creates our evil Dragon!
		Dragon dragon = new Dragon(3, 1);
		
		// Sets the player's position on the labyrinth (effectively places it's reresenting char)
		lab.setPlayer(player);
		
		// Sets the dragon's position on the labyrinth (effectively places it's reresenting char)
		lab.setDragon(dragon);
		
		// Sets the sword's position on the labyrinth (effectively places it's reresenting char)
		lab.setSword(sword);
		
		// Draws the Labyrinth matrix/board
		lab.DrawBoard();
		
		// Game loop
		while(true) {
			
			// Gets user input.
			in.getInput();
			
			// Handles user input.
			for(int i = 0; i < Input.NKEYS; i++) {
				
				if(in.getKeys()[i]) {
					
					Input.KEY direction = Input.KEY.values()[i];
					switch(direction) {
					case UP:
						player.MoveUp(lab);
						break;
					case RIGHT:
						player.MoveRight(lab);
						break;
					case DOWN:
						player.MoveDown(lab);
						break;
					case LEFT:
						player.MoveLeft(lab);
						break;
					default:
						break;
					}
					
					break;
				}
				
			}
			
			// Moves dragon.
			dragon.MoveDragon(lab);
			
			
			lab.setPlayer(player);
		
			lab.setDragon(dragon);
			
		
			// Checks if the player has encountered the Dragon, that terrible beast!
			if(lab.foundDragon(player, dragon) && (dragon.isAlive())) {
				// If the player is armed, it slains the Dragon
				if(player.isArmed()) {
					lab.killDragon(dragon);
				}
				// If the player is unarmed, it gets killed
				else {
					player.setAlive(false);
					break;
				}
			}
			
			// Checks if the player has found the sword
			// If so, the player's char goes from 'H' (hero) to 'A' (armed hero)
			if(lab.foundSword(player)) {
				player.setArmed(true);
				player.setHeroChar('A');
			}
			
			// Checks if the player is at the Labyrinth exit
			if(lab.isAtExit(player)) {
				
				// If the dragon is slained our hero may sucessfully exit the Labyrinth 
				if(!dragon.isAlive())
					break;
				else
					player.MoveBack(lab);
			}

			
			// Only draws sword if dragon has not the sword and if it's not taken by the player.
			if(!dragon.hasSword() && !player.isArmed()) lab.setSword(sword);
			
			lab.DrawBoard();
			
		}
		
		lab.setPlayer(player);
		
		lab.DrawBoard();
		
		if(player.isAlive()) System.out.print(" WIN ");
		else System.out.print(" LOSE ");
		
	}


}
