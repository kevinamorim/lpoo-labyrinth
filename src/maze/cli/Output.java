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
	
	public void drawElements(Element[] elem) {
		for(int i = 0; i < elem.length; i++) {
			System.out.println(elem[i]);
		}
		System.out.println();
	}
	
	public void drawCommands() {
		System.out.println("+-------------------------+");
		System.out.println("Move............ [w,a,s,d]");
		System.out.println("Get game state.. [e]");
		System.out.println("Confirm......... [enter]");
		System.out.println("+-------------------------+");
	}
	
	public void drawGoal(Task[] tasks) {
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
	
	public void drawGameOver(boolean won) {
		if(won) {
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
			
		default:
			break;
		}
	}
	
	public void debugPrint(String msg) {
		System.out.println(msg);
	}
	
}
