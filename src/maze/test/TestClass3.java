package maze.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import maze.logic.Dragon;
import maze.logic.Eagle;
import maze.logic.Element;
import maze.logic.GameLogic;
import maze.logic.Maze;

import org.junit.Test;

public class TestClass3 {
	
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
	public void TestEagleCatchSword() {
		
		gameTest = new GameLogic();
		
		gameTest.setMaze(new Maze(maze));
		
		Eagle eagle = new Eagle(1, 1, 'V');
		gameTest.setEagle(eagle);
		
		Element sword = new Element(1, 3, 'E');
		gameTest.setSword(sword);
		
		commands = "dd";
		
		execCommands();
		
		gameTest.checkForEagleFoundSword();
		
		assertTrue(gameTest.getEagle().hasSword());
		
	}
	
	@Test
	public void EagleFindItsWayToSword() {
		
		gameTest = new GameLogic();
		
		gameTest.setMaze(new Maze(maze));
		
		Eagle eagle = new Eagle(1, 1, 'V');
		gameTest.setEagle(eagle);
		
		Element sword = new Element(4, 8, 'E');
		gameTest.setSword(sword);
		
		// Limit to 100 movements.
		for(int i = 0; i < 100; i++) {
			gameTest.getEagle().moveToSword(gameTest.getMaze(), gameTest.getSword());
			gameTest.checkForEagleFoundSword();
			if(gameTest.getEagle().hasSword()) break;
		}
		
		assertTrue(gameTest.getEagle().hasSword());
		
	}
	
	@Test
	public void EagleFindItsWayBack() {
		
		gameTest = new GameLogic();
		
		gameTest.setMaze(new Maze(maze));
		
		Eagle eagle = new Eagle(1, 1, 'V');
		Eagle initialEagle = new Eagle(1, 1, 'V');
		
		gameTest.setEagle(eagle);
		
		gameTest.getEagle().sendEagle();
		gameTest.getEagle().setX(4);
		gameTest.getEagle().setY(8);
		
		// Limit to 100 movements.
		for(int i = 0; i < 100; i++) {
			gameTest.getEagle().moveBack();
			if(!gameTest.getEagle().isFlying()) break;
		}
		
		assertEquals(initialEagle, gameTest.getEagle());
		
	}
	
	@Test
	public void EagleDies() {
		
		gameTest = new GameLogic();

		gameTest.setMaze(new Maze(maze));

		Eagle eagle = new Eagle(4, 4, 'V');
		
		gameTest.setEagle(eagle);
		
		gameTest.setDragons(new Dragon[1]);
		
		gameTest.getDragons()[0] = new Dragon(4, 4, 'D');
		
		gameTest.checkKillEagle();
		
		assertFalse(gameTest.getEagle().isAlive());
		
	}
	
	
	
	/**
	 * AUXILIARES
	 */
	private void execCommands() {

		for(int i = 0; i < commands.length(); i++) {
			switch(commands.charAt(i)) {
			case 'w':
				gameTest.getEagle().move(gameTest, 0);
				break;
			case 'd':
				gameTest.getEagle().move(gameTest, 1);
				break;
			case 's':
				gameTest.getEagle().move(gameTest, 2);
				break;
			case 'a':
				gameTest.getEagle().move(gameTest, 3);
				break;
			default:
				break;
			}
		}
	}

}
