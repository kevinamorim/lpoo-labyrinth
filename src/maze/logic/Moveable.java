package maze.logic;

public class Moveable extends Element {
	
	protected boolean alive;

	/**
	 * @param x
	 * @param y
	 * @param symbol
	 */
	public Moveable(int x, int y, char symbol) {
		
		super(x, y, symbol);
		this.alive = true;
	}

	/**
	 * @param lab
	 * @param symbol
	 */
	public Moveable(Maze lab, char symbol) {
		
		super(lab, symbol);
		this.alive = true;
	}

	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	/**
	 * @param lab
	 */
	public void moveDown(Maze lab) {
		
		setOldX(x);
		setOldY(y);
		setX(x + 1);
	}

	/**
	 * @param lab
	 */
	public void moveUp(Maze lab) {

		setOldX(x);
		setOldY(y);
		setX(x - 1);
	}

	/**
	 * @param lab
	 */
	public void moveLeft(Maze lab) {

		setOldX(x);
		setOldY(y);
		setY(y - 1);
	}

	/**
	 * @param lab
	 */
	public void moveRight(Maze lab) {

		setOldX(x);
		setOldY(y);
		setY(y + 1);
	}
	
	/**
	 * 
	 */
	public void moveBack() {
		this.x = oldX;
		this.y = oldY;
	}
	
	/**
	 * 
	 */
	public void kill() {
		this.alive = false;
		this.symbol = ' ';
	}
	
	public boolean isAtExit(Element exit) {
		
		if((exit.getX() == x) && (exit.getY() == y)) {
			return true;
		}
		return false;
	}

}
