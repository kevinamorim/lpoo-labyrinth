
public class Hero extends Tile {
	
	private int oldX, oldY;

	private boolean armed;
	private boolean alive;
	
	/**
	 * @param lab Actual labyrinth
	 */
	public Hero(Labyrinth lab) {
		super(lab, 'H');
		this.setOldX(getX());
		this.setOldY(getY());
		this.setArmed(false);
		this.setAlive(true);
	}

	/**
	 * @return The last x position.
	 */
	public int getOldX() {
		return oldX;
	}

	/**
	 * @param oldX The last x position.
	 */
	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	/**
	 * @return The last y position.
	 */
	public int getOldY() {
		return oldY;
	}

	/**
	 * @param oldY The last y position.
	 */
	public void setOldY(int oldY) {
		this.oldY = oldY;
	}

	/**
	 * 
	 * @return True if the hero stills alive, false otherwise.
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * 
	 * @param alive True if the hero stills alive, false otherwise.
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	/**
	 * 
	 * @return True if the player is armed, false otherwise.
	 */
	public boolean isArmed() {
		return armed;
	}

	/**
	 * 
	 * @param armed True if the player is armed, false otherwise.
	 */
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
		if(lab.isValidMove(getX() + 1, getY(), this.armed)) {
			setOldX(getX());
			setOldY(getY());
			setX(getX() + 1);
		}
	}

	// Decrements the hero's X coordinate (if such move is valid)
	public void MoveUp(Labyrinth lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(getX() - 1, getY(), this.armed)) {
			setOldX(getX());
			setOldY(getY());
			setX(getX() - 1);
		}
	}

	// Decrements the hero's Y coordinate (if such move is valid)
	//
	// Note: Y coordinates represent the column number in the labyrinth matrix 
	// 		 (instead of the line, as it would be in a cartesian axis - vice-versa for X coordinates)
	//
	public void MoveLeft(Labyrinth lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(getX(), getY() - 1, this.armed)) {
			setOldX(getX());
			setOldY(getY());
			setY(getY() - 1);
		}
	}

	// Increments the hero's Y coordinate (if such move is valid)
	public void MoveRight(Labyrinth lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(getX(), getY() + 1, this.armed)) {
			setOldX(getX());
			setOldY(getY());
			setY(getY() + 1);
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
