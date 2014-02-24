import java.util.Random;


public class Dragon extends Element {
	
	private boolean alive, hasSword;

	// Constructor for a Dragon type object
	public Dragon(Maze lab) {
		super(lab, 'D');
		this.setAlive(true);
		this.setHasSword(false);
		this.setOldX(getX());
		this.setOldY(getY());
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
	public void MoveDragon(Maze lab) {
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
		
		this.setOldX(x);
		this.setOldY(y);
		
		do {
			
			direction = r.nextInt(4); 

			switch(direction) {
			case 0: 
				// Move Up
					this.setX(x - 1);
				break;
			case 1:
				// Move Right
					this.setY(y + 1);
				break;
			case 2: 
				// Move Down
					this.setX(x + 1);
				break;
			case 3:
				// Move Left
					this.setY(y - 1);
				break;
			default:
				break;
			}
			
			if(lab.isValidMove(x, y, false)) {
				valid = true;
			}
			else {
				this.setX(oldX);
				this.setY(oldY);
			}
		
		}while(!valid);
		
	}
	
}
