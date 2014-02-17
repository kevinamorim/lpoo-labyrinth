
public class Labyrinth {
	
	private char tiles[][];

	public Labyrinth(char[][] tiles) {
		
		this.tiles = tiles;
		
	}
	
	public void setPlayer(Hero player) {
		tiles[player.getX()][player.getY()] = 'H';
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
	
	public boolean isExit(int x, int y) {
		
		if(tiles[x][y] == 'S') return true;
		return false;
	}

}
