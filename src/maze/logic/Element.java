package maze.logic;

import java.util.Random;

public class Element {
	
	protected int x;
	protected int y;
	protected int oldX;
	protected int oldY;
	protected char symbol;
	
	/**
	 * @param x
	 * @param y
	 * @param symbol
	 */
	public Element(int x, int y, char symbol) {
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.symbol = symbol;
	}
	
	/**
	 * @param lab
	 * @param symbol
	 */
	public Element(Maze lab, char symbol) {
		
		GeneratePos(lab);
		this.oldX = x;
		this.oldY = y;
		this.symbol = symbol;
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

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * @param symbol the symbol to set
	 */
	public void setSymbol(char symbol) {
		this.symbol = symbol;
	}

	
	/**
	 * 
	 * Generates a random valid position for the tile. 
	 * @param lab Labyrinth object 
	 */
	public void GeneratePos(Maze lab) {
		Random r = new Random();
		
		int MAX = lab.getSize();
		int posX = 0, posY = 0;
		
		boolean done = false;
		while(!done) {
			posX = r.nextInt(MAX); posY = r.nextInt(MAX);
			
			// Checks if it's a valid position.
			if(lab.getTiles()[posX][posY] == ' ') done = true;
		}
		
		this.x = posX;
		this.y = posY;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s;
		s = "[" + this.symbol + "]";
		s += " (" + this.x;
		s += ", " + this.y + ")";
		return s;
	}
	
	public boolean isAt(Element b) {
		if(this.getX() == b.getX() && this.getY() == b.getY())
			return true;
		return false;
	}
	
	public boolean isAt(int x, int y) {
		if(this.getX() == x && this.getY() == y)
			return true;
		return false;
	}

}
