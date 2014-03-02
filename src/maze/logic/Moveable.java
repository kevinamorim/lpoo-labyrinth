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
	public void moveDown() {
		
		setOldX(x);
		setOldY(y);
		setX(x + 1);
	}

	/**
	 * @param lab
	 */
	public void moveUp() {

		setOldX(x);
		setOldY(y);
		setX(x - 1);
	}

	/**
	 * @param lab
	 */
	public void moveLeft() {

		setOldX(x);
		setOldY(y);
		setY(y - 1);
	}

	/**
	 * @param lab
	 */
	public void moveRight() {

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
	public void die() {
		this.alive = false;
		this.symbol = ' ';
	}

	/**
	 * @param sword
	 * @return
	 */
	public boolean foundSword(Element sword) {
		if(this.isAt(sword)) {
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see maze.logic.Element#toString()
	 */
	@Override
	public String toString() {
		String s = super.toString();
		s += "\n    alive: " + this.alive;
		return s;
	}
	
	public boolean isValidMove(int x, int y, Maze maze) {
		
		if(maze.getExit().isAt(x,y))
			return true;
		
		switch(maze.getTiles()[x][y]) {
		case ' ':
			return true;
		case 'E':
			return true;
		case 'D':
			return false;
		case 'd':
			return false;
		case 'F':
			return false;
		case 'f':
			return false;
		default:
			return false;
		}
	}
	
}
