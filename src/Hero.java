
public class Hero {
	
	private int x,y;
	private int oldX, oldY;
	
	private char heroChar;
	private boolean armed;
	private boolean alive;
	
	// Constructor for a Hero type object
	public Hero(int x, int y) {
		this.setHeroChar('H');
		this.setX(x);
		this.setY(y);;
		this.setOldX(x);
		this.setOldY(y);
		this.setArmed(false);
		this.setAlive(true);
	}
	
	// Returns the hero's current X coordinate
	public int getX() {
		return x;
	}

	// Sets the hero's X coordinate
	public void setX(int x) {
		this.x = x;
	}
	
	// Returns the hero's current Y coordinate
	public int getY() {
		return y;
	}
	
	// Sets the hero's Y coordinate
	public void setY(int y) {
		this.y = y;
	}
	
	// Returns the hero's old X coordinate
	public int getOldX() {
		return oldX;
	}

	// Sets the hero's old X coordinate
	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	// Returns the hero's old Y coordinate
	public int getOldY() {
		return oldY;
	}

	// Sets the hero's old Y coordinate
	public void setOldY(int oldY) {
		this.oldY = oldY;
	}
	
	// Returns the hero's representing char: 'H' or 'A' (if armed with the sword)
	public char getHeroChar() {
		return heroChar;
	}

	// Sets the hero's representing char
	public void setHeroChar(char heroChar) {
		this.heroChar = heroChar;
	}

	// Checks if the hero is alive or not
	public boolean isAlive() {
		return alive;
	}

	// Sets the hero's 'alive' parameter (boolean, self-explanatory)
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	// Checks if the hero is armed or not
	public boolean isArmed() {
		return armed;
	}

	// Sets the hero's 'armed' parameter (boolean, self-explanatory)
	public void setArmed(boolean armed) {
		this.armed = armed;
	}
	
	// Increments the hero's X coordinate (if such move is valid)
	//
	// Note: X coordinates represent the line number in the labyrinth matrix 
	// 		 (instead of the column, as it would be in a cartesian axis - vice-versa for Y coordinates)
	//
	public void MoveDown(Labyrinth lab) {
		
		// Checks if the desired move is valid
		if(lab.isValidMove(x + 1, y, this.armed)) {
			setOldX(x);
			setOldY(y);
			x++;
		}
	}

	// Decrements the hero's X coordinate (if such move is valid)
	public void MoveUp(Labyrinth lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(x - 1, y, this.armed)) {
			setOldX(x);
			setOldY(y);
			x--;
		}
	}

	// Decrements the hero's Y coordinate (if such move is valid)
	//
	// Note: Y coordinates represent the column number in the labyrinth matrix 
	// 		 (instead of the line, as it would be in a cartesian axis - vice-versa for X coordinates)
	//
	public void MoveLeft(Labyrinth lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(x, y - 1, this.armed)) {
			setOldX(x);
			setOldY(y);
			y--;
		}
	}

	// Increments the hero's Y coordinate (if such move is valid)
	public void MoveRight(Labyrinth lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(x, y + 1, this.armed)) {
			setOldX(x);
			setOldY(y);
			y++;
		}
	}

	// Returns the player to it's former position before any move
	public void MoveBack(Labyrinth lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(oldX, oldY, this.armed)) {
			setX(oldX);
			setY(oldY);
		}
	}

}
