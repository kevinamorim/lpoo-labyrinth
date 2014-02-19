
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
		{ 'x', 'E', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
		{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
	};
	
	public static void main(String[] args) {
		
		Input in = new Input();
			
		Labyrinth lab = new Labyrinth(board);
		
		Hero player = new Hero(1, 1);
		
		Dragon dragon = new Dragon(1, 4);
		
		lab.setPlayer(player);
		
		lab.setDragon(dragon);
		
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
			
			if(lab.foundDragon(player, dragon) && dragon.isAlive()) {
				if(player.isArmed()) {
					lab.killDragon(dragon);
				}
				else {
					player.setAlive(false);
					break;
				}
			}
			
			if(lab.foundSword(player)) {
				player.setArmed(true);
				player.setHeroChar('A');
			}
			
			if(lab.isAtExit(player)) {
				
				if(player.isArmed()) break;
				else player.MoveLeft(lab);
			}
			
			lab.setPlayer(player);
			
			lab.DrawBoard();
			
		}
		
		lab.setPlayer(player);
		
		lab.DrawBoard();
		
		if(player.isAlive()) System.out.print(" WIN ");
		else System.out.print(" LOSE ");
		
	}


}
