import java.util.Arrays;
import java.util.Random;
import java.util.Stack;


public class Labyrinth {
	
	// Char matrix representing the labyrinth 
	private char tiles[][];

	private int size;

	private int exit[];

	/**
	 * Labyrinth constructor.
	 * @param size Size of the generated labyrinth. The labyrinth is always square, so the final size will be (size x size).
	 */
	public Labyrinth(int size) {
		
		this.size = size;
		exit = new int[2];
		tiles = new char[size][size];
		
		SetChamber();
		DrawGrid();
		GenerateExit();
		GenerateWalls();
		
	}
	
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the tiles
	 */
	public char[][] getTiles() {
		return tiles;
	}
	
	// Prints the labyrinth tiles (represented by a matrix)
	public void DrawBoard() {
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(i == exit[0] && j == exit[1]) {
					System.out.print(" " + 'S' + " ");
				} else {
					System.out.print(" " + tiles[i][j] + " ");
				}
			}
			System.out.println();
		}
		
	}
	
	// -- BEGIN Maze generation
	
	/**
	 * Randomly generates a valid exit for the labyrinth. With the following restrictions:
	 *   The exit must be on a frontier.
	 *   The exit must not be on a corner. 
	 *   
	 * The coordinates for the generated exit are stored on the exit array. 
	 */
	public void GenerateExit() {
		
		Random r = new Random();
		
		/*
		 * 0 - Top
		 * 1 - Right
		 * 2 - Bottom
		 * 3 - Left
		 * 
		 */
		int side = r.nextInt(4);
		
		boolean done = false;
		while(!done) {
			
			switch(side) {
			case 0: 
				exit[0] = 0;
				// Can't be at (0, 0) nor (0, N).
				exit[1] = r.nextInt(size - 2) + 1;
				if(tiles[exit[0] + 1][exit[1]] != 'x') done = true;
				break;
			case 1:
				exit[0] = r.nextInt(size - 2) + 1;
				exit[1] = size - 1;
				if(tiles[exit[0]][exit[1] - 1] != 'x') done = true;
				break;
			case 2: 
				exit[0] = size - 1;
				exit[1] = r.nextInt(size - 2) + 1;
				if(tiles[exit[0] - 1][exit[1]] != 'x') done = true;
				break;
			case 3:
				exit[0] = r.nextInt(size - 2) + 1;
				exit[1] = 0;
				if(tiles[exit[0]][exit[1] + 1] != 'x') done = true;
				break;
			}
			
		}
		

		
	}
	
	/**
	 * Sets the outside wall of the labyrinth.
	 */
	public void SetChamber() {
		
		// Top wall
		for(int i = 0; i < size; i++) tiles[0][i] = 'x';
		
		// Right wall
		for(int i = 0; i < size; i++) tiles[i][size-1] = 'x';
		
		// Bottom wall
		for(int i = 0; i < size; i++) tiles[size-1][i] = 'x';
		
		// Left wall
		for(int i = 0; i < size; i++) tiles[i][0] = 'x';
		
	}
	
	/**
	 * Draws a grid inside the previously generated maze chamber.
	 * 
	 * Example (5x5): 
	 * 
	 * 		x x x x x
	 * 		x   x   x
	 * 		x x x x x
	 * 		x   x   x
	 * 		x x x x x 
	 * 
	 */
	public void DrawGrid() {
		
		// Determines whether we are drawing a wall or not.
		boolean wall = false;
		// Determines whether we are drawing a line or not.
		boolean line = false;
		// Draws grid.
		for(int i = 1; i < (size - 1); i++) {
			for(int j = 1; j < (size - 1); j++) {
				
				if(wall && (line || j < (size - 2))) tiles[i][j] = 'x';
				else tiles[i][j] = ' ';
				
				if(!line) wall = !wall;
				
			}
			
			line = !line;
			
			if(line) wall = true;
			else wall = false;
		}
		
		// Test
		/*
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(" " + tiles[i][j] + " ");
			}
			System.out.println();
		}
		*/
	}
	
	/**
	 * Generates the final labyrinth. Takes our pre-created grid and then randomly opens holes in walls.
	 * Follows the backtracking algorithm.
	 */
	public void GenerateWalls() {

		Random r = new Random();

		// Random direction.
		/*
		 * 0 - UP
		 * 1 - RIGHT
		 * 2 - DOWN
		 * 3 - LEFT
		 */
		int direction; 

		boolean visited[][] = new boolean[size][size];

		// -- BEGIN Marks all walls and the exit as visited cells, so they don't count. 
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(tiles[i][j] == 'x' || tiles[i][j] == 'S') visited[i][j] = true;
				else visited[i][j] = false;
			}
		}
		// -- END 

		// Test visited array.
		/*
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				System.out.print(" " + visited[i][j] + " ");
			}
			System.out.println();
		}
		 */

		Stack<Integer> stackX = new Stack<Integer>();
		Stack<Integer> stackY = new Stack<Integer>();
		//
		int current[] = new int[2];

		// -- BEGIN Make the initial cell the adjacent to the exit. 
		current[0] = exit[0]; current[1] = exit[1];
		if(current[0] == 0) current[0]++;
		else if(current[0] == (size - 1)) current[0]--;
		else if(current[1] == 0) current[1]++;
		else if(current[1] == (size - 1)) current[1]--;

		visited[current[0]][current[1]] = true;

		// Prints current position
		//System.out.println("Current: " + current[0] + "," + current[1]);

		// -- END 

		boolean done = false;
		while(!done) {

			// Pick valid neighbor
			boolean found = false;
			while(!found) {

				if(!hasUnvisitedNeighbors(current[0], current[1], visited)) {

					if(!stackX.isEmpty()) {
						current[0] = stackX.pop(); current[1] = stackY.pop();
					} else {
						boolean newCell = false;
						for(int i = 0; i < size; i++) {
							for(int j = 0; j < size; j++) {
								if(!visited[i][j]) {
									current[0] = i; current[1] = j;
									newCell = true;
									break;
								}
								if(newCell) break;
							}
						}
					}


				} else {

					// Push current cell to stack
					stackX.push(current[0]); stackY.push(current[1]);

					direction = r.nextInt(4);

					if(hasNeighbor(current[0], current[1], direction)) {
						switch(direction) {
						// UP
						case 0:
							if(!visited[current[0] - 2][current[1]]) {
								tiles[current[0] - 1][current[1]] = ' ';
								current[0] -= 2;
								found = true;
							}
							break;
							// RIGHT
						case 1:
							if(!visited[current[0]][current[1] + 2]) {
								tiles[current[0]][current[1] + 1] = ' ';
								current[1] += 2;
								found = true;
							}
							break;
							// DOWN
						case 2:
							if(!visited[current[0] + 2][current[1]]) {
								tiles[current[0] + 1][current[1]] = ' ';
								current[0] += 2;
								found = true;
							}
							break;
							// LEFT
						case 3:
							if(!visited[current[0]][current[1] - 2]) {
								tiles[current[0]][current[1] - 1] = ' ';
								current[1] -= 2;
								found = true;
							}
							break;
						}
						visited[current[0]][current[1]] = true;
					}
				}



			}

			// Checks if we still have unvisited cells.
			for(int i = 0; i < size; i++) {
				done = true;
				for(int j = 0; j < size; j++) {
					if(!visited[i][j]) {
						done = false;
						break;
					}
				}
				if(!done) break;
			}

		}

	}
	
	public boolean hasNeighbor(int x, int y, int direction) {
		
		switch(direction) {
		// UP
		case 0:
			if((x - 2) < 0) return false;
			else return true;
		// RIGHT
		case 1:
			if((y + 2) >= size) return false;
			else return true;
		// DOWN
		case 2:
			if((x + 2) >= size) return false;
			else return true;
		// LEFT
		case 3:
			if((y - 2) < 0) return false;
			else return true;
		}
		
		return false;
		
	}
	
	public boolean hasUnvisitedNeighbors(int x, int y, boolean visited[][]) {
		
		boolean result = false;
		for(int i = 0; i < 4; i++) {
			switch(i) {
			// UP
			case 0:
				if((x - 2) >= 0) {
					if(!visited[x - 2][y]) result = true;
				}
				break;
			// RIGHT
			case 1:
				if((y + 2) < size) {
					if(!visited[x][y + 2]) result = true;
				}
				break;
			// DOWN
			case 2:
				if((x + 2) < size) {
					if(!visited[x + 2][y]) result = true;
				}
				break;
			// LEFT
			case 3:
				if((y - 2) >= 0) {
					if(!visited[x][y - 2]) result = true;
				}
				break;
			}
			
		}
		
		return result;
	}
	
	
	// -- END Maze generation
	
	
	// Sets the player's position tile with its representing char: 'H' or 'A' (if armed with the sword)
	// Also erases char from the player's last position
	public void setPlayer(Hero player) {
		tiles[player.getOldX()][player.getOldY()] = ' ';
		tiles[player.getX()][player.getY()] = player.getSymbol();
	}
	
	// Sets the dragon position tile with its representing char: 'D'
	public void setDragon(Dragon dragon) {
		tiles[dragon.getOldX()][dragon.getOldY()] = ' ';
		
		// If new dragon position is the actual sword position.
		if(tiles[dragon.getX()][dragon.getY()] == 'E') {
			dragon.setHasSword(true);
		}
		else if(dragon.hasSword()) {
			dragon.setHasSword(false);
		}
		
		tiles[dragon.getX()][dragon.getY()] = dragon.getSymbol();
	}
	
	public void setSword(Tile sword) {
		tiles[sword.getX()][sword.getY()] = sword.getSymbol();
	}
	
	// "Kills" the dragon, effectively setting its 'alive' parameter to false and
	// erasing its symbol from the labyrinth  
	public void killDragon(Dragon dragon) {
		dragon.setAlive(false);
		dragon.setSymbol(' ');
		tiles[dragon.getX()][dragon.getY()] = ' ';
	}
	
	// Checks whether the tile that the object is trying to move to is valid or not (eg: walls are not valid)
	public boolean isValidMove(int x, int y, boolean isArmedPlayer) {
		
		// If the tile is a wall, dragon or player, it cannot move to that location
		if(isValidMoveChar(x,y,tiles[x][y])) {
			return true;
		}
		// If the tile is the exit, the player should be alowed to win only if armed
		// (the "dragon is dead" premise is not computed here)
		else if ((isArmedPlayer) && (x == exit[0]) && (y == exit[1]))
			return true;
		return false;
		
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
		if(Math.sqrt(Math.abs(player.getX() - dragon.getX()) + Math.abs(player.getY() - dragon.getY())) <= 1) return true;
//		// Security purposes. Shouldn't happen. 
//		else if(player.getX() == dragon.getX() && player.getY() == dragon.getY()) return true;
		return false;
		
	}
	
	// Checks if the player's current tile is the exit tile
	public boolean isAtExit(Hero player) {
		// The exit tile is identifiend with the char 'S'
		if(player.getX() == exit[0] && player.getY()== exit[1]) return true;
		return false;
	}

	private boolean isValidMoveChar(int x, int y, char comp) {
		if((tiles[x][y] == ' ') || (tiles[x][y] == 'A') || (tiles[x][y] == 'H') || (tiles[x][y] == 'E'))
			return true;
		return false;
	}
}
