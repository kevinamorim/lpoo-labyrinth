
public class Game {
	
	private static char board[][] = {
		{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
		{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'S' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
		{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
		{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
	};
	
	public static void main(String[] args) {
			
		Labyrinth l = new Labyrinth(board);
		
		l.DrawBoard();
		
	}


}
