
public class Labyrinth {
	
	// Char matrix representing the labyrinth 
	private char tiles[][];

	// Constructor for a Labyrinth type object
	public Labyrinth(char[][] tiles) {
		
		this.tiles = tiles;
	}
	
	// Sets the player's position tile with its representing char: 'H' or 'A' (if armed with the sword)
	// Also erases char from the player's last position
	public void setPlayer(Hero player) {
		tiles[player.getOldX()][player.getOldY()] = ' ';
		tiles[player.getX()][player.getY()] = player.getHeroChar();
	}
	
	// Sets the dragon position tile with its representing char: 'D'
	public void setDragon(Dragon dragon) {
		tiles[dragon.getX()][dragon.getY()] = dragon.getDragonChar();
	}
	
	// "Kills" the dragon, effectively setting its 'alive' parameter to false and
	// erasing its symbol from the labyrinth  
	public void killDragon(Dragon dragon) {
		dragon.setAlive(false);
		tiles[dragon.getX()][dragon.getY()] = ' ';
	}
	
	// Prints the labyrinth tiles (represented by a matrix)
	public void DrawBoard() {
		
		for(char[] line : tiles) {
			
			for(char tile : line) {
				
				System.out.print(" " + tile + " ");
				
			}
			
			System.out.println();
		}
		
	}
	
	// Checks whether the tile that the player is trying to move to is valid or not (eg: walls are not valid)
	public boolean isValidMove(int x, int y, boolean playerIsArmed) {
		
		// If the tile is a wall or a dragon the player cannot move to that location
		// (the latter is for assurity issues only, it should never occur)
		if((tiles[x][y] == 'x') || (tiles[x][y] == 'D')) {
			return false;
		}
		// If the tile is the exit, the player should be alowed to win only if armed
		// (the "dragon is dead" premise is not computed here)
		else if (tiles[x][y] == 'S') {
			if (playerIsArmed)
				return true;
			else
				return false;
		}
		return true;
		
	}
	
	// Checks if the player's current tile has the sword in it
	public boolean foundSword(Hero player) {
		
		// A sword is identified by the char 'E' in the labyrinth tile
		if(tiles[player.getX()][player.getY()] == 'E') return true;
		return false;
		
	}
	
	// Checks if any of the player's adjacent tiles has the dragon in them
	public boolean foundDragon(Hero player, Dragon dragon) {
		
		// Calculating the real distance between the dragon and the player (contiguous cells will necessarily be 1 unit apart)
		//
		// formula: sqrt(deltaX + deltaY) <--- Pitagoras' theorem
		//
		if(Math.sqrt(Math.abs(player.getX() - dragon.getX()) + Math.abs(player.getY() - dragon.getY())) == 1) return true;
		return false;
		
	}
	
	// Checks if the player's current tile is the exit tile
	public boolean isAtExit(Hero player) {
		
		// The exit tile is identifiend with the char 'S'
		if(tiles[player.getX()][player.getY()] == 'S') return true;
		return false;
	}

}
