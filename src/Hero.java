
public class Hero extends Element {

	private boolean armed;
	private boolean alive;
	
	/**
	 * @param lab Actual labyrinth
	 */
	public Hero(Maze lab) {
		super(lab, 'H');
		this.setOldX(getX());
		this.setOldY(getY());
		this.setArmed(false);
		this.setAlive(true);
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
	
	/**
	 * 
	 * @param direction
	 * @param lab
	 */
	public void move(Input.KEY direction, Maze lab) {
		switch(direction) {
		case UP:
			moveUp(lab);
			break;
		case RIGHT:
			moveRight(lab);
			break;
		case DOWN:
			moveDown(lab);
			break;
		case LEFT:
			moveLeft(lab);
			break;
		default:
			break;
		}
	}
	
	// Increments the hero's X coordinate (if such move is valid)
	//
	// Note: X coordinates represent the line number in the labyrinth matrix 
	// 		 (instead of the column, as it would be in a cartesian axis - vice-versa for Y coordinates)
	//
	public void moveDown(Maze lab) {
		
		// Checks if the desired move is valid
		if(lab.isValidMove(getX() + 1, getY(), this.armed)) {
			setOldX(getX());
			setOldY(getY());
			setX(getX() + 1);
		}
	}

	// Decrements the hero's X coordinate (if such move is valid)
	public void moveUp(Maze lab) {

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
	public void moveLeft(Maze lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(getX(), getY() - 1, this.armed)) {
			setOldX(getX());
			setOldY(getY());
			setY(getY() - 1);
		}
	}

	// Increments the hero's Y coordinate (if such move is valid)
	public void moveRight(Maze lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(getX(), getY() + 1, this.armed)) {
			setOldX(getX());
			setOldY(getY());
			setY(getY() + 1);
		}
	}

	// Returns the player to it's former position before any move
	public void moveBack(Maze lab) {

		// Checks if the desired move is valid
		if(lab.isValidMove(oldX, oldY, this.armed)) {
			setX(oldX);
			setY(oldY);
		}
	}

}
