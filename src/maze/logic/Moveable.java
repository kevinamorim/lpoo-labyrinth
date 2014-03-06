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
	public Moveable(GameLogic game, char symbol) {
		
		super(game, symbol);
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
	
	public boolean isValidMove(int x, int y, GameLogic game) {
		
		for(Dragon dragon: game.getDragons()) {

			if(!(this == dragon)) {
				
				if(dragon.isAlive() && dragon.isAt(x, y)) {
					return false;
				}
			}
		}
		
		if(this == game.getHero()) {
			
			if(game.getMaze().getExit().isAt(x,y)) {
				return true;
			}
			
		}
		
		
		if(game.getMaze().getTiles()[x][y] == 'x') {
			return false;
		}

		if(game.getHero().isAt(x, y)) {
			return false;
		}
		
		if(game.getMaze().getTiles()[x][y] == ' ') {
			return true;
		}
		
		if(game.getSword().isAt(x, y)) {
			return true;
		}
		
		return false;
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
	
}
