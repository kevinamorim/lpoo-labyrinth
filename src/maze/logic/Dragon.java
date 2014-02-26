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
	public Dragon(Maze maze) {
		
		super(maze, 'D');
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
	 * Moves the dragon randomly one position.
	 * @param maze Actual labyrinth.
	 */
	public void move(Maze maze) {
		Random r = new Random();
		
		/*
		 * Direction:
		 * 		0 - Up
		 * 		1 - Right
		 * 		2 - Down
		 * 		3 - Left
		 */
		int direction = 0; // With default values, to avoid compilation errors.
		
		this.oldX = x;
		this.oldY = y;

		direction = r.nextInt(4); 

		switch(direction) {
		case 0: 
			// UP
			this.x = x - 1;
			break;
		case 1:
			// RIGHT
			this.y = y + 1;
			break;
		case 2: 
			// DOWN
			this.x = x + 1;
			break;
		case 3:
			// LEFT
			this.y = y - 1;
			break;
		default:
			break;
		}

		if(!isValidMove(x, y, maze)) {
			this.setX(oldX);
			this.setY(oldY);
		}

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

}
