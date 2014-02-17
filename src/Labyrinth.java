
public class Labyrinth {
	
	private char tiles[][];

	public Labyrinth(char[][] tiles) {
		
		this.tiles = tiles;
		
	}
	
	public void DrawBoard() {
		
		for(char[] line : tiles) {
			
			for(char tile : line) {
				
				System.out.print(" " + tile + " ");
				
			}
			
			System.out.println();
		}
		
	}
	
	

}
