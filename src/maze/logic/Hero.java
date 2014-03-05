package maze.logic;

import java.util.Random;

/**
 * @author Luís
 *
 */
public class Hero extends Moveable {

	private boolean armed;
	private boolean hasEagle;


	/**
	 * @param lab Actual labyrinth
	 */
	public Hero(GameLogic game) {
		super(game, 'Y');
		this.armed = false;
		this.hasEagle = true;
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
	 * @return the hasEagle
	 */
	public boolean hasEagle() {
		return hasEagle;
	}

	/**
	 * @param hasEagle the hasEagle to set
	 */
	public void setHasEagle(boolean hasEagle) {
		this.hasEagle = hasEagle;
	}
	
	/**
	 * 
	 * @param direction
	 * @param lab
	 */
	public void move(GameLogic.KEY direction, GameLogic game) {
		switch(direction) {
		case UP:
			if(isValidMove(getX() - 1, getY(), game)) moveUp();
			break;
		case RIGHT:
			if(isValidMove(getX(), getY() + 1, game)) moveRight();
			break;
		case DOWN:
			if(isValidMove(getX() + 1, getY(), game)) moveDown();
			break;
		case LEFT:
			if(isValidMove(getX(), getY() - 1, game)) moveLeft();
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
	
	public boolean foundEagle(Eagle eagle) {
		if(this.isAt(eagle)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 */
	public void arm() {
		this.armed = true;
		this.symbol = 'A';
	}

	/* (non-Javadoc)
	 * @see maze.logic.Moveable#toString()
	 */
	@Override
	public String toString() {
		String s = super.toString();
		s += "\n    armed: " + this.armed;
		return s;
	}

	/* (non-Javadoc)
	 * @see maze.logic.Element#GeneratePos(maze.logic.GameLogic)
	 */
	public void GeneratePos(GameLogic game) {
		Random r = new Random();
		
		int MAX = game.getMaze().getSize();
		int posX = 0, posY = 0;
		
		boolean done = false;
		while(!done) {
			posX = r.nextInt(MAX);
			posY = r.nextInt(MAX);
			
			// Checks if it's a valid position.
			
			done = isValidInitialPosition(game, posX, posY);
		}
		
		this.x = posX;
		this.y = posY;
		
	}

	/* (non-Javadoc)
	 * @see maze.logic.Element#isValidInitialPosition(maze.logic.GameLogic, int, int)
	 */
	protected boolean isValidInitialPosition(GameLogic game, int x, int y) {
		
		if(game.getMaze().getTiles()[x][y] == 'x') {
			return false;
		}
		
		return true;
	}
	
}
