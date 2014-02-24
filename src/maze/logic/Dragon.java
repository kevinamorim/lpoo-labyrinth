package maze.logic;

import java.util.Random;

public class Dragon extends Moveable {
	
	private boolean hasSword;

	/**
	 * @param lab
	 */
	public Dragon(Maze lab) {
		
		super(lab, 'D');
		this.hasSword = false;
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
		
		boolean valid = false;
		
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

		do {

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

			if(maze.isValidMove(x, y)) {
				valid = true;
			}
			else {
				this.setX(oldX);
				this.setY(oldY);
			}
		
		}while(!valid);
		
	}

}
