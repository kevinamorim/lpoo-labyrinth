package maze.test;

import static org.junit.Assert.assertEquals;
import maze.logic.GameLogic;
import maze.logic.Hero;
import maze.logic.Maze;

import org.junit.Test;

public class TestClass1 {

	// Char matrix representing the labyrinth 
	private char maze[][] = {
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', ' ', ' ', ' ', ' ', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', 'x', ' ', 'x', ' ', 'x' },
			{ 'x', ' ', 'x', 'x', ' ', ' ', ' ', ' ', ' ', 'x' },
			{ 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x' },
	};
	private GameLogic gameTest;
	private String commands;

	@Test
	public void TestMoveHero() {
		
		Hero comp = new Hero(1,2,'H');
		
		commands = "d";
		
		gameTest = new GameLogic();
		
		gameTest.setHero(new Hero(1,1,'H'));
		
		gameTest.setMaze(new Maze(maze));
		
		execCommands();
		
		assertEquals(comp,gameTest.getHero());
	}
	
	
	/**
	 * AUXILIARES
	 */
	private void execCommands() {

		for(int i = 0; i < commands.length(); i++) {
			switch(commands.charAt(i)) {
			case 'w':
				gameTest.getHero().move(1, gameTest);
				break;
			case 'd':
				gameTest.getHero().move(2, gameTest);
				break;
			case 's':
				gameTest.getHero().move(3, gameTest);
				break;
			case 'a':
				gameTest.getHero().move(4, gameTest);
				break;
			default:
				break;
			}
		}
	}
	
}
