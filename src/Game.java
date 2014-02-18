import java.util.Scanner;


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
		
		Scanner scan = new Scanner(System.in);
			
		Labyrinth lab = new Labyrinth(board);
		
		Hero player = new Hero(1, 1);
		
		lab.setPlayer(player);
		
		lab.DrawBoard();
		
		while(!lab.isExit(player.getX(), player.getY())) {
			
			String c = scan.nextLine();
			
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
			
			lab.setPlayer(player);
			
			lab.DrawBoard();
			
		}
		
		scan.close();
		
	}


}
