
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
		{ 'x', 'E', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
		{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
	};
	
	public static void main(String[] args) {
		
		// Creates a new Input type object (receives keyboard inputs)
		Input in = new Input();
		
		// Creates the Labyrinth
		Labyrinth lab = new Labyrinth(board);
		
		// Creates our hero/player
		Hero player = new Hero(1, 1);
		
		// Creates our evil Dragon!
		Dragon dragon = new Dragon(3, 1);
		
		// Sets the player's position on the labyrinth (effectively places it's reresenting char)
		lab.setPlayer(player);
		
		// Sets the dragon's position on the labyrinth (effectively places it's reresenting char)
		lab.setDragon(dragon);
		
		// Draws the Labyrinth matrix/board
		lab.DrawBoard();
		
		// Game loop
		while(true) {
			
			// Gets user input.
			in.getInput();
			
			// Handles user input.
			for(int i = 0; i < in.getKeys().length; i++) {
				
				if(in.getKeys()[i]) {
					
					switch(i) {
					case 0:
						player.MoveUp(lab);
						break;
					case 1:
						player.MoveRight(lab);
						break;
					case 2:
						player.MoveDown(lab);
						break;
					case 3:
						player.MoveLeft(lab);
						break;
					default:
						break;
					}
					
					break;
				}
				
			}
		
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
			
			lab.setPlayer(player);
			
			lab.DrawBoard();
			
		}
		
		lab.setPlayer(player);
		
		lab.DrawBoard();
		
		if(player.isAlive()) System.out.print(" WIN ");
		else System.out.print(" LOSE ");
		
	}


}
