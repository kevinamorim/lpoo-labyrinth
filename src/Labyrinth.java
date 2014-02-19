
public class Labyrinth {
	
	private char tiles[][];

	public Labyrinth(char[][] tiles) {
		
		this.tiles = tiles;
		
	}
	
	public void setPlayer(Hero player) {
		tiles[player.getOldX()][player.getOldY()] = ' ';
		tiles[player.getX()][player.getY()] = player.getHeroChar();
	}
	
	public void DrawBoard() {
		
		for(char[] line : tiles) {
			
			for(char tile : line) {
				
				System.out.print(" " + tile + " ");
				
			}
			
			System.out.println();
		}
		
	}
	
	public boolean isWall(int x, int y) {
		
		if(tiles[x][y] == 'x') return true;
		return false;
		
	}
	
	public boolean foundSword(Hero player) {
		if(tiles[player.getX()][player.getY()] == 'E') return true;
		return false;
	}
	
	public boolean isAtExit(Hero player) {
		
		if(tiles[player.getX()][player.getY()] == 'S') return true;
		return false;
	}

}
