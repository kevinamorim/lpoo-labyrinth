package maze.logic;

import java.util.Random;

public class Hero extends Moveable {

	private boolean won;
	private boolean hasEagle;

	/**
	 * Constructor for Hero.
	 * Receives the current GameLogic in order to call super(game, symbol).
	 * 
	 * @param game current GameLogic
	 */
	public Hero(GameLogic game) {
		
		super(game, 'Y');
		
		this.hasSword = false;
		this.hasEagle = true;
		this.won = false;
	}
	
	/**
	 * Constructor for Hero.
	 * Receives the x and y coordinates in order to call super(x, y, symbol).
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param symbol
	 */
	public Hero(int x, int y, char symbol) {
		
		super(x,y, symbol);
		
		this.hasSword = false;
		this.hasEagle = false;
		this.won = false;
	}

	/**
	 * Gets the value of the parameter [hasEagle].
	 * 
	 * @return true if the hero has the eagle
	 */
	public boolean hasEagle() {
		return hasEagle;
	}

	/**
	 * Sets the value of the parameter [hasEagle].
	 * 
	 * @param hasEagle : value to be set
	 */
	public void setHasEagle(boolean hasEagle) {
		this.hasEagle = hasEagle;
	}
	
	/**
	 * Gets the value of the parameter [won].
	 * 
	 * @return true if the hero has won the game
	 */
	public boolean hasWon() {
		return won;
	}

	/**
	 * Sets the value of the parameter [won].
	 * This parameter declares if the hero has won the game or not.
	 * 
	 * @param win : value to be set
	 */
	public void setWon(boolean win) {
		this.won = win;
	}

	/**
	 * Checks if the hero found the eagle.
	 * 
	 * @param eagle : the eagle to be found
	 * @return true if hero/eagle at the same position (x, y)
	 */
	public boolean foundEagle(Eagle eagle) {
		if(this.isAt(eagle)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Arms the player by changing its representing symbol and by setting the parameter [hasSword] to true.
	 */
	public void arm() {
		this.hasSword = true;
		this.symbol = 'A';
	}

	/* (non-Javadoc)
	 * @see maze.logic.Moveable#toString()
	 */
	@Override
	public String toString() {
		String s = super.toString();
		s += "\n    armed: " + this.hasSword;
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
	
	/**
	 * Updates a hero:
	 * 	- checks for found eagle;
	 *  - checks for found sword;
	 *  - checks for dragon encounters;
	 *  - check for found exit.
	 *  
	 * @param game : current GameLogic instance
	 */
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
		if(!hasSword && foundSword(game.getSword())) {
			arm();
		}
		
		// Checks if hero has found any dragon.
		for(Dragon dragon : game.getDragons()) {

			if(dragon.isAlive() && foundDragon(dragon)) {

				if(hasSword) {
					dragon.die();
				}
				else {
					if(dragon.isAwake()) {
						die();
					}
				}
				
			}
		}	
		
		//Checks if hero has won the game and sets a flag.
		if(isAt(game.getMaze().getExit())) {
			
			for(Dragon dragon : game.getDragons()) {
				if(dragon.isAlive()) {
					won = false;
				}
			}
			
			if(!won) {
				moveBack();
			}
			else {
				won = true;
			}
		}

	}
	
}
