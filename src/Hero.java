
public class Hero {
	
	private char heroChar;
	
	private int x,y;
	private int oldX, oldY;
	private boolean armed;
	private boolean alive;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getOldX() {
		return oldX;
	}

	public void setOldX(int oldX) {
		this.oldX = oldX;
	}

	public int getOldY() {
		return oldY;
	}

	public void setOldY(int oldY) {
		this.oldY = oldY;
	}
	
	public Hero(int x, int y) {
		this.setHeroChar('H');
		this.setX(x);
		this.setY(y);;
		this.setOldX(x);
		this.setOldY(y);
		this.setArmed(false);
		this.setAlive(true);
	}
	
	public void MoveDown(Labyrinth l) {
		
		if(!l.isWall(x + 1, y)) {
			setOldX(x);
			setOldY(y);
			x++;
		}
	}

	public void MoveUp(Labyrinth l) {

		if(!l.isWall(x - 1, y)) {
			setOldX(x);
			setOldY(y);
			x--;
		}
	}

	public void MoveLeft(Labyrinth l) {

		if(!l.isWall(x, y - 1)) {
			setOldX(x);
			setOldY(y);
			y--;
		}
	}

	public void MoveRight(Labyrinth l) {

		if(!l.isWall(x, y + 1)) {
			setOldX(x);
			setOldY(y);
			y++;
		}
	}

	public boolean isArmed() {
		return armed;
	}

	public void setArmed(boolean armed) {
		this.armed = armed;
	}

	public char getHeroChar() {
		return heroChar;
	}

	public void setHeroChar(char heroChar) {
		this.heroChar = heroChar;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

}
