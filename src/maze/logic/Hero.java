package maze.logic;

import java.util.Random;

/**
 * @author Luís
 *
 */
public class Hero extends Moveable {

	private boolean win;
	private boolean armed;
	private boolean hasEagle;

	public Hero(GameLogic game) {
		super(game, 'Y');
		this.armed = false;
		this.hasEagle = true;
		this.setWin(false);
	}
	
	public Hero(int x, int y, char symbol) { // TEST
		super(x,y, symbol);
		this.armed = false;
		this.hasEagle = false;
		this.setWin(false);
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
	
	public void update(GameLogic game) {
		
		// Checks if hero has found the eagle.
		if(!hasEagle && foundEagle(game.getEagle())) {
			hasEagle = true;
			
			if(game.getEagle().hasSword()) {
				arm();
				game.getEagle().setHasSword(false);
				game.getEagle().setUseful(false);
			} else {
				symbol = 'Y';
			}
			
			game.getEagle().setMoving(false);
			game.getEagle().setFlying(false);
		}
		
		// Checks if hero has found the sword. 
		if(!armed && foundSword(game.getSword())) {
			arm();
		}
		
		// Checks if hero has found any dragon.
		Dragon[] dragons = game.getDragons();
		for(Dragon dragon : dragons) {

			if(dragon.isAlive() && foundDragon(dragon)) {

				if(armed) {
					dragon.die();
				}
				else {
					if(dragon.isAwake()) {
						die();
					}
				}
				
			}
		}	
		
		game.setDragons(dragons);
		
		//Checks if hero has won the game and sets a flag.
		if(isAt(game.getMaze().getExit())) {
			win = true;
			for(Dragon dragon : dragons) {
				if(dragon.isAlive()) win = false;
			}
			if(!win) moveBack();
			System.out.println("Is at exit. " + win);
		}

	}

	/**
	 * @return the win
	 */
	public boolean isWin() {
		return win;
	}

	/**
	 * @param win the win to set
	 */
	public void setWin(boolean win) {
		this.win = win;
	}
	
}
