package maze.logic;

public class Eagle extends Moveable {
	
	private boolean hasSword;
	private boolean movingHorizontally;
	private boolean moving;
	private boolean flying;
	private boolean useful;


	public Eagle(int x, int y, char symbol) {
		super(x, y, symbol);
		
		setHasSword(false);
		setMovingHorizontally(true);
		setMoving(false);
		setFlying(false);
		setUseful(true);
			
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
	 * @return the useful
	 */
	public boolean isUseful() {
		return useful;
	}

	/**
	 * @param useful the useful to set
	 */
	public void setUseful(boolean useful) {
		this.useful = useful;
	}

	/**
	 * @param sword
	 */
	public void moveToSword(Element sword) {
		
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
		else if(this.isAt(oldX, oldY)){
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
	
	public void update(GameLogic game) {
		
		if(useful) {
			
			if(game.getHero().hasEagle()) {
				updatePosition(game.getHero().getX(), game.getHero().getY());
			}
			
			if(alive && moving) {
				if(!hasSword) {
					moveToSword(game.getSword());
					if(!hasSword() && foundSword(game.getSword())) {
						hasSword = true;
						flying = false;
					}
				} else {
					moveBack();
					game.getSword().setX(x);
					game.getSword().setY(y);
				}
			}
			
			if(!flying && !game.getHero().hasEagle()) {

				for(Dragon dragon : game.getDragons()) {
					
					if(foundDragon(dragon)) {

						die();

						if((game.getSword() != null) && hasSword) {

							hasSword = false;
							moving = false;
							flying = false;

							game.getSword().setX(x);
							game.getSword().setY(y);
						}
					}
				}
			}
		}
	}
	
}
