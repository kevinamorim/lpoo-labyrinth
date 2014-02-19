import java.math.*;

public class Labyrinth {
	
	private char tiles[][];

	public Labyrinth(char[][] tiles) {
		
		this.tiles = tiles;
		
	}
	
	public void setPlayer(Hero player) {
		tiles[player.getOldX()][player.getOldY()] = ' ';
		tiles[player.getX()][player.getY()] = player.getHeroChar();
	}
	
	public void setDragon(Dragon dragon) {
		tiles[dragon.getX()][dragon.getY()] = dragon.getDragonChar();
	}
	
	public void killDragon(Dragon dragon) {
		dragon.setAlive(false);
		tiles[dragon.getX()][dragon.getY()] = ' ';
	}
	public void DrawBoard() {
		
		for(char[] line : tiles) {
			
			for(char tile : line) {
				
				System.out.print(" " + tile + " ");
				
			}
			
			System.out.println();
		}
		
	}
	
	public boolean isValidMove(int x, int y, boolean armed) {
		
		if((tiles[x][y] == 'x') || (tiles[x][y] == 'D')) return false;
		else if (tiles[x][y] == 'S') {
			if (armed) return true;
			else return false;
		}
		return true;
		
	}
	
	public boolean foundSword(Hero player) {
		
		if(tiles[player.getX()][player.getY()] == 'E') return true;
		return false;
		
	}
	
	public boolean foundDragon(Hero player, Dragon dragon) {
		
		if((Math.abs(player.getX() - dragon.getX()) <= 1) &&
				(Math.abs(player.getY() - dragon.getY()) <= 1)) return true;
		return false;
		
	}
	
	public boolean isAtExit(Hero player) {
		
		if(tiles[player.getX()][player.getY()] == 'S') return true;
		return false;
	}

}
