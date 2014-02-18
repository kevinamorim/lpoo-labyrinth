
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
		
		Input in = new Input();
			
		Labyrinth lab = new Labyrinth(board);
		
		Hero player = new Hero(1, 1);
		
		lab.setPlayer(player);
		
		lab.DrawBoard();
		
		while(true) {
			
			String c = in.getScan();
			
			switch(c.charAt(0)) {
			case 'w':
				player.MoveUp(lab);
				break;
				
			case 'd':
				player.MoveRight(lab);
				break;
				
			case 's':
				player.MoveDown(lab);
				break;
				
			case 'a':
				player.MoveLeft(lab);
				break;
				
			default:
				break;
				
			}
			
			if(!lab.isAtExit(player)) {
				
				lab.setPlayer(player);
				lab.DrawBoard();
			}
			else break;
			
		}
		
		System.out.print(" END ");
		
	}


}
