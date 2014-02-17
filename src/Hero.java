
public class Hero {
	
	private int x,y;
	
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
	
	public Hero(int posx, int posy) {
		this.setX(posx);
		this.setY(posy);
	}

}
