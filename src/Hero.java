
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
	
	public void MoveDown(Labyrinth l) {
		
		if(l.isWall(x, y + 1))
			y++;
	}

	public void MoveUp(Labyrinth l) {

		if(l.isWall(x, y - 1))
			y--;
	}

	public void MoveLeft(Labyrinth l) {

		if(l.isWall(x - 1, y))
			x--;
	}

	public void MoveRight(Labyrinth l) {

		if(l.isWall(x + 1, y))
			x++;
	}

}
