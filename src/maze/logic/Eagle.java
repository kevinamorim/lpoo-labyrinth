package maze.logic;

public class Eagle extends Moveable {
	
	private boolean withHero;
	private boolean hasSword;
	private boolean movingHorizontally;
	private boolean aboveWall;
	
	public Eagle(int x, int y, char symbol) {
		super(x, y, symbol);
		
		setWithHero(true);
		setHasSword(false);
		setMovingHorizontally(true);
		aboveWall = false;
		
	}

	/**
	 * @return the withHero
	 */
	public boolean isWithHero() {
		return withHero;
	}

	/**
	 * @param withHero the withHero to set
	 */
	public void setWithHero(boolean withHero) {
		this.withHero = withHero;
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
	}
	
	/**
	 * @return the movingHorizontally
	 */
	public boolean isMovingHorizontally() {
		return movingHorizontally;
	}

	/**
	 * @param movingHorizontally the movingHorizontally to set
	 */
	public void setMovingHorizontally(boolean movingHorizontally) {
		this.movingHorizontally = movingHorizontally;
	}

	
	/**
	 * @return the aboveWall
	 */
	public boolean isAboveWall() {
		return aboveWall;
	}

	/**
	 * @param aboveWall the aboveWall to set
	 */
	public void setAboveWall(boolean aboveWall) {
		this.aboveWall = aboveWall;
	}
	
	/**
	 * @param sword
	 */
	public void moveToSword(Maze maze, Element sword) {
		
		boolean done = false;
		while(!done) {
			
			if(movingHorizontally) {
				if(x != sword.getX()) {
					
					oldX = x;
					if(x < sword.getX()) x++;
					else x--;
					
					movingHorizontally = false;
					done = true;
					
				} 
			} else {
				if(y != sword.getY()) {
					
					oldY = y;
					if(y < sword.getY()) y++;
					else y--;
					
					done = true;
					movingHorizontally = true;
					
				}
			}
			
			if(!done) movingHorizontally = !movingHorizontally;
			
			if(x == sword.getX() && y == sword.getY()) break;
		
		}
		
		
		// Sets the maze actual symbol.
		if(maze.getTiles()[x][y] == 'x') aboveWall = true;
		else aboveWall = false;
		
	}

	/**
	 * 
	 */
	public void moveBack() {
		
		// Moves in x. 
		if(x != oldX) {
			if(x < oldX) x++;
			else x--;
		} 
		// Moves in y.
		else if(y != oldY) {
			if(y < oldY) y++;
			else y--;
		}
		
	}
	
}
