import java.util.Random;


public class Dragon {
	
	private char dragonChar;
	private boolean alive, hasSword;


	private int x, y, oldX, oldY;


	// Constructor for a Dragon type object
	public Dragon(int x, int y) {
		this.setAlive(true);
		this.setHasSword(false);
		this.setDragonChar('D');
		this.setX(x);
		this.setY(y);
		this.setOldX(x);
		this.setOldY(y);
	}

	// Return the dragon's representing char (should not be different from 'D')
	public char getDragonChar() {
		return dragonChar;
	}

	// Sets the dragon's representing char
	public void setDragonChar(char dragonChar) {
		this.dragonChar = dragonChar;
	}
	
	// Returns the dragon's current X coordinate
	public int getX() {
		return x;
	}

	// Sets the dragon's X coordinate
	public void setX(int x) {
		this.x = x;
	}

	// Returns the dragon's current Y coordinate
	public int getY() {
		return y;
	}

	// Sets the dragon's Y coordinate
	public void setY(int y) {
		this.y = y;
	}
	
	
	/**
	 * @return the oldX
	 */
	public int getOldX() {
		return oldX;
	}

	/**
	 * @param oldX the oldX to set
	 */
	public void setOldX(int oldX) {
		this.oldX = oldX;
	}
	

	/**
	 * @return the oldY
	 */
	public int getOldY() {
		return oldY;
	}

	/**
	 * @param oldY the oldY to set
	 */
	public void setOldY(int oldY) {
		this.oldY = oldY;
	}

	// Checks whether the dragon is or not alive
	public boolean isAlive() {
		return alive;
	}

	// Sets the 'alive' parameter of the dragon (boolean, self-explanatory)
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * @return the hasSword
	 */
	public boolean hasSword() {
		return hasSword;
	}

	/**
	 * @param hasSword the hasSword to set
	 */
	public void setHasSword(boolean hasSword) {
		this.hasSword = hasSword;
		
		if(hasSword) this.dragonChar = 'F';
		else this.dragonChar = 'D';
		
	}
	
	public void MoveDragon(Labyrinth lab) {
		Random r = new Random();
		
		boolean valid = false;
		
		/*
		 * Direction:
		 * 		0 - Up
		 * 		1 - Right
		 * 		2 - Down
		 * 		3 - Left
		 */
		int direction = 0, newX = 0, newY = 0; // With default values, to avoid compilation errors.
		
		do {
			
			direction = r.nextInt(4); 

			switch(direction) {
			case 0: 
				// Move Up
				newX = x - 1;
				newY = y;
				break;
			case 1:
				// Move Right
				newX = x;
				newY = y + 1;
				break;
			case 2: 
				// Move Down
				newX = x + 1;
				newY = y;
				break;
			case 3:
				// Move Left
				newX = x;
				newY = y - 1;
				break;
			default:
				break;
			}
			
			if(lab.isValidMove(newX, newY, false)) valid = true;
			
		} while (!valid);
		
		oldX = x;
		oldY = y;
		x = newX;
		y = newY;
		
	}


}
