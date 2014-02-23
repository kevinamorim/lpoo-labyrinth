import java.util.Random;


public class Dragon extends Tile {
	
	private boolean alive, hasSword;

	private int oldX, oldY;

	// Constructor for a Dragon type object
	public Dragon(Labyrinth lab) {
		super(lab, 'D');
		this.setAlive(true);
		this.setHasSword(false);
		this.setOldX(getX());
		this.setOldY(getY());
	}
	
	/**
	 * @return the oldX
	 */
	public int getOldX() {
		return oldX;
	}

	/**
	 * @param oldX the oldX to set
	 */
	public void setOldX(int oldX) {
		this.oldX = oldX;
	}
	

	/**
	 * @return the oldY
	 */
	public int getOldY() {
		return oldY;
	}

	/**
	 * @param oldY the oldY to set
	 */
	public void setOldY(int oldY) {
		this.oldY = oldY;
	}

	// Checks whether the dragon is or not alive
	public boolean isAlive() {
		return alive;
	}

	// Sets the 'alive' parameter of the dragon (boolean, self-explanatory)
	public void setAlive(boolean alive) {
		this.alive = alive;
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
	 * @param lab Actual labyrinth.
	 */
	public void MoveDragon(Labyrinth lab) {
		Random r = new Random();
		
		boolean valid = false;
		
		/*
		 * Direction:
		 * 		0 - Up
		 * 		1 - Right
		 * 		2 - Down
		 * 		3 - Left
		 */
		int direction = 0, newX = 0, newY = 0; // With default values, to avoid compilation errors.
		
		do {
			
			direction = r.nextInt(4); 

			switch(direction) {
			case 0: 
				// Move Up
				newX = getX() - 1;
				newY = getY();
				break;
			case 1:
				// Move Right
				newX = getX();
				newY = getY() + 1;
				break;
			case 2: 
				// Move Down
				newX = getX() + 1;
				newY = getY();
				break;
			case 3:
				// Move Left
				newX = getX();
				newY = getY() - 1;
				break;
			default:
				break;
			}
			
			if(lab.isValidMove(newX, newY, false)) valid = true;
			
		} while (!valid);
		
		oldX = getX();
		oldY = getY();
		setX(newX);
		setY(newY);
		
	}


}
