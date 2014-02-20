
public class Sword {
	
	private int x;
	private int y;
	private char swordChar;
	
	public Sword(int x, int y) {
		this.x = x;
		this.y = y;
		
		swordChar = 'E';
	}

	/**
	 * @return the swordChar
	 */
	public char getSwordChar() {
		return swordChar;
	}

	/**
	 * @param swordChar the swordChar to set
	 */
	public void setSwordChar(char swordChar) {
		this.swordChar = swordChar;
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
	
	

}
