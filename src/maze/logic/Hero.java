package maze.logic;

public class Hero extends Moveable {

	private boolean armed;
	
	/**
	 * @param lab Actual labyrinth
	 */
	public Hero(Maze lab) {
		super(lab, 'H');
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
	public void move(GameLogic.KEY direction, Maze maze) {
		switch(direction) {
		case UP:
			if(maze.isValidMove(getX() - 1, getY())) moveUp(maze);
			break;
		case RIGHT:
			if(maze.isValidMove(getX(), getY() + 1)) moveRight(maze);
			break;
		case DOWN:
			if(maze.isValidMove(getX() + 1, getY())) moveDown(maze);
			break;
		case LEFT:
			if(maze.isValidMove(getX(), getY() - 1)) moveLeft(maze);
			break;
		default:
			break;
		}
	}
	
	/**
	 * @param dragon
	 * @return
	 */
	public boolean foundDragon(Dragon dragon) {
		
		// Calculating the real distance between the dragon and the player (contiguous cells will necessarily be 1 unit apart)
		//
		// formula: sqrt(deltaX + deltaY) <--- Pitagoras' theorem
		//
		if(Math.sqrt(Math.abs(x - dragon.getX()) + Math.abs(y - dragon.getY())) <= 1) return true;
		return false;
		
	}
	
	/**
	 * 
	 */
	public void arm() {
		this.armed = true;
		this.symbol = 'A';
	}
	
}
