
public class Hero extends Moveable {

	private boolean armed;
	
	/**
	 * @param lab Actual labyrinth
	 */
	public Hero(Maze lab) {
		super(lab, 'H');
		this.alive = true;
		this.armed = false;
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
			if(lab.isValidMove(getX() - 1, getY(), armed)) moveUp(lab);
			break;
		case RIGHT:
			if(lab.isValidMove(getX(), getY() + 1, armed)) moveRight(lab);
			break;
		case DOWN:
			if(lab.isValidMove(getX() + 1, getY(), armed)) moveDown(lab);
			break;
		case LEFT:
			if(lab.isValidMove(getX(), getY() - 1, armed)) moveLeft(lab);
			break;
		default:
			break;
		}
	}

}
