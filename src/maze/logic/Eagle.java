package maze.logic;

public class Eagle extends Moveable {
	
	private boolean hasSword;
	private boolean movingHorizontally;
	private boolean moving;
	private boolean flying;


	public Eagle(int x, int y, char symbol) {
		super(x, y, symbol);
		
		setHasSword(false);
		setMovingHorizontally(true);
		setMoving(false);
		flying = false;
			
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
	 * @return the flying
	 */
	public boolean isFlying() {
		return flying;
	}

	/**
	 * @param flying the flying to set
	 */
	public void setFlying(boolean flying) {
		this.flying = flying;
	}
	
	/**
	 * @param sword
	 */
	public void moveToSword(Maze maze, Element sword) {
		
		flying = true;
		
		boolean done = false;
		while(!done) {
			
			if(movingHorizontally) {
				if(x != sword.getX()) {
				
					if(x < sword.getX()) moveDown();
					else moveUp();
					
					movingHorizontally = false;
					done = true;
					
				} 
			} else {
				if(y != sword.getY()) {
				
					if(y < sword.getY()) moveRight();
					else moveLeft();
					
					done = true;
					movingHorizontally = true;
					
				}
			}
			
			if(!done) movingHorizontally = !movingHorizontally;
			
		}
		
	}

	/**
	 * 
	 */
	public void moveBack() {
		
		flying = true;
		
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
		else {
			flying = false;
		}
		
	}
	
	public void updatePosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void sendEagle() {
		oldX = x; 
		oldY = y;
		moving = true;
	}
	
	
}
