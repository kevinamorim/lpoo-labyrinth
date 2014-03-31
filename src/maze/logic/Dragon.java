package maze.logic;

import java.util.Random;

public class Dragon extends Moveable {
	
	private boolean hasSword;
	private boolean awake;
	private int roundsToWake;
	
	private int maxRounds = 2;

	/**
	 * @param maze
	 */
	public Dragon(GameLogic game) {
		
		super(game, 'D');
		this.hasSword = false;
		this.awake = true;
		this.setRoundsToWake(0);
	}

	public Dragon(int x, int y, char symbol) { // TEST

		super(x,y,symbol);
		
		this.hasSword = false;
		this.awake = true;
		this.setRoundsToWake(0);
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
		
		if(hasSword) setSymbol('F');
		else setSymbol('D');
		
	}

	/**
	 * @return the awake
	 */
	public boolean isAwake() {
		return awake;
	}

	/**
	 * @param awake the awake to set
	 */
	public void setAwake(boolean awake) {
		this.awake = awake;
	}

	/**
	 * @return the roundsToWake
	 */
	public int getRoundsToWake() {
		return roundsToWake;
	}

	/**
	 * @param roundsToWake the roundsToWake to set
	 */
	public void setRoundsToWake(int roundsToWake) {
		this.roundsToWake = roundsToWake;
	}
	
	/**
	 * 
	 */
	public void setDragonState() {
		Random r = new Random();
		
		if(this.isAwake()) {
			boolean awake = (r.nextInt(6) > 0);
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
	
	public void update(GameLogic game) {
		
		// Sets dragon state.
		if(game.getConfig().getDifficulty() == 2) {
			if(alive) {
				setDragonState();
			}
		}
		
		// Moves dragon. 
		if(game.getConfig().getDifficulty() > 1) {
			if(isAwake() && isAlive()) {
				Random r = new Random();
				move(game, r.nextInt(4));
				
				if(isAt(game.getMaze().getExit())) {
					moveBack();
				}
			}
		}
		
		// Checks if dragon has found the sword.
		hasSword = false;
		if(foundSword(game.getSword())) {
			hasSword = true;
		}
		
	}
}
