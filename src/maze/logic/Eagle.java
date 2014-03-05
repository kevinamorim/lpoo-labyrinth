package maze.logic;

public class Eagle extends Moveable {
	
	private boolean withHero;
	private boolean hasSword;
	private boolean movingHorizontally;
	private boolean moving;

	public Eagle(int x, int y, char symbol) {
		super(x, y, symbol);
		
		setWithHero(true);
		setHasSword(false);
		setMovingHorizontally(true);
		setMoving(false);
		
		
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
	 * @return the moving
	 */
	public boolean isMoving() {
		return moving;
	}

	/**
	 * @param moving the moving to set
	 */
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	/**
	 * @param sword
	 */
	public void moveToSword(Maze maze, Element sword) {
		
		boolean done = false;
		while(!done) {
			
			if(movingHorizontally) {
				if(x != sword.getX()) {
				
					if(x < sword.getX()) x++;
					else x--;
					
					movingHorizontally = false;
					done = true;
					
				} 
			} else {
				if(y != sword.getY()) {
				
					if(y < sword.getY()) y++;
					else y--;
					
					done = true;
					movingHorizontally = true;
					
				}
			}
			
			if(!done) movingHorizontally = !movingHorizontally;
			
			if(x == sword.getX() && y == sword.getY()) {
				hasSword = true;
				break;
			}
		
		}
		
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
	
	public void sendEagle() {
		oldX = x; 
		oldY = y;
		moving = true;
	}
	
	
}
