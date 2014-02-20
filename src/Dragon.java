
public class Dragon {
	
	private char dragonChar;
	private boolean alive;
	private int x,y;
	
	// Constructor for a Dragon type object
	public Dragon(int x, int y) {
		this.setAlive(true);
		this.setDragonChar('D');
		this.setX(x);
		this.setY(y);
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

	// Checks whether the dragon is or not alive
	public boolean isAlive() {
		return alive;
	}

	// Sets the 'alive' parameter of the dragon (boolean, self-explanatory)
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void MoveDragon() {
		
	}

}
