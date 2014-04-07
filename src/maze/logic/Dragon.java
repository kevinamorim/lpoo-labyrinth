package maze.logic;

import java.util.Random;

public class Dragon extends Moveable {
	
	private boolean awake;
	private int roundsToWake;
	
	private int maxRounds = 2;
	
	private int RANDOM_SLEEP = 6;

	/**
	 * Constructor for Dragon.
	 * Receives the current GameLogic in order to call super(game, symbol).
	 * 
	 * @param game current GameLogic
	 */
	public Dragon(GameLogic game) {
		
		super(game, 'D');
		
		this.hasSword = false;
		this.awake = true;
		this.roundsToWake = 0;
	}

	/**
	 * Constructor for Dragon.
	 * Receives the x and y coordinates in order to call super(x, y, symbol).
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param symbol
	 */
	public Dragon(int x, int y, char symbol) {

		super(x,y,symbol);
		
		this.hasSword = false;
		this.awake = true;
		this.roundsToWake = 0;
	}

	/**
	 * Gets the value of the parameter [awake].
	 * 
	 * @return true if the dragon is awake
	 */
	public boolean isAwake() {
		return awake;
	}

	/**
	 * Sets the value of the parameter [awake].
	 * 
	 * @param awake : value to be set
	 */
	public void setAwake(boolean awake) {
		this.awake = awake;
	}

	/**
	 * Gets the value of the parameter [roundstoWake].
	 * 
	 * @return the number of rounds before the dragon awakes
	 */
	public int getRoundsToWake() {
		return roundsToWake;
	}

	/**
	 * Sets the value of the parameter [roundstoWake].
	 * 
	 * @param roundsToWake : the number of rounds to pass before the dragon awakes
	 */
	public void setRoundsToWake(int roundsToWake) {
		this.roundsToWake = roundsToWake;
	}
	
	/**
	 * Checks and sets if a dragon is asleep or not.
	 * 
	 * If the dragon is asleep, decrements its parameter [roundsToWake]
	 * and verifies if the dragon has awoken (roundsToWake <= 0).
	 * 
	 * If the dragon is awake, the function randomly decides whether the dragon goes to sleep.
	 */
	public void setDragonState() {
		Random r = new Random();
		
		if(this.isAwake()) {
			boolean awake = (r.nextInt(RANDOM_SLEEP) > 0);
			if(!awake) {
				this.setAwake(awake);
				if(this.hasSword) {
					this.setSymbol('f');
				}
				else {
					this.setSymbol('d');
				}
				this.setRoundsToWake(r.nextInt(maxRounds) + 4);
			}
		}
		else {
			this.roundsToWake--;
			if(roundsToWake <= 0) {
				this.setAwake(true);
				if(this.hasSword) {
					this.setSymbol('F');
				}
				else {
					this.setSymbol('D');
				}
			}
		}
	}
	
	/**
	 * Updates a dragon:
	 * 	- calls setDragonState();
	 *  - randomly moves the dragon;
	 *  - checks for found swords by the dragon.
	 *  
	 * @param game : current GameLogic instance
	 */
	public void update(GameLogic game) {
		
		// Sets dragon state.
		
		if(game.getConfig() == null) return;
		
		if(game.getConfig().getDifficulty() == 2) {
			if(alive) {
				setDragonState();
			}
		}
		
		
		// Moves dragon. 
		if(game.getConfig().getDifficulty() > 1) {
			if(awake && alive) {
				Random r = new Random();
				move(game, r.nextInt(4));
				
				if(game.getMaze().getExit() != null) {
					if(isAt(game.getMaze().getExit())) {
						moveBack();
					}
				}

			}
		}

		// Checks if dragon has found the sword.
		hasSword = false;
		if(checkIfFound(game.getSword(), 0)) {
			hasSword = true;
		}

	}
}
