package maze.cli;

import maze.logic.*;

public class Output {
	
	public void drawBoard(Maze maze) {
		
		for(int i = 0; i < maze.getSize(); i++) {
			
			for(int j = 0; j < maze.getSize(); j++) {
				
				if(i == maze.getExit().getX() && j == maze.getExit().getY()) {
					System.out.print(" " + 'S' + " ");
				} else {
					System.out.print(" " + maze.getTiles()[i][j] + " ");
				}
			}
			
			System.out.println();
		}
	}
	
	public void drawBoard(char[][] board) {
		
		for(int i = 0; i < board.length; i++) {
			
			for(int j = 0; j < board.length; j++) {
				
					System.out.print(" " + board[i][j] + " ");
				}
			System.out.println();
		}
	}
	
	public void drawCommands() {
		System.out.println("+-------------------------+");
		System.out.println("Move............ [w,a,s,d]");
		System.out.println("Confirm......... [enter]");
		System.out.println("+-------------------------+");
	}
	
	public void drawGoals(Task[] tasks) {
		System.out.println("+--------- Goals ---------+");
		for(int i = 0; i < tasks.length; i++) {
			System.out.print((i + 1) + ". ");
			if(tasks[i].isDone()) {
				System.out.print("[X] ");
			}
			else {
				System.out.print("[ ] ");
			}
			System.out.println(tasks[i].getDescription());
		}
		System.out.println("+-------------------------+");
	}
	
	public void drawGameOver(GameLogic game) {
		
		drawBoard(game.getMaze());
		drawGoals(game.getTasks());
		
		if(game.getHero().hasWon()) {
			System.out.println("+-------------------------+");
			System.out.println("+---------- WIN ----------+");	
			System.out.println("+-------------------------+");	
		}
		else {
			System.out.println("+-------------------------+");
			System.out.println("+--------- LOSE ----------+");	
			System.out.println("+-------------------------+");	
		}
		
	}
	
	public void drawMsg(int msg) {
		switch(msg) {
		case 0:
			System.out.println("Yupii, you've found a sword! Go tell your momma...");
			break;
			
		case 1:
			System.out.println("You just slained a fucking dragon!! Go tell your father...");
			break;
			
		case 2:
			System.out.println("Key? ");
			break;
		case 3:
			System.out.println("An eagle! yey");
			break;
		case 4:
			System.out.println("Insert a maze size (the bigger the hardest (not always)) [5 - 21] <- odd number");
			break;
		case 5:
			System.out.println("I told'ya man!!!");
			break;
		case 6:
			System.out.println("Choose a dificulty: \n[1] Dumb dragons \n[2] Smart dragons (they sleep) \n[3] Dragons high on caffeine");
			break;
		case 69:
			System.out.println("8======D~~~~");
			break;
		default:
			break;
		}
	}
	
	public void debugPrint(String msg) {
		System.out.println(msg);
	}

	public void draw(GameLogic game) {
		
		// Draws menu.
		drawCommands();
		drawGoals(game.getTasks());
		
		// Draws board.
		drawBoard(game.getBoard());
		
	}
}
