package maze.test;

import static org.junit.Assert.*;
import maze.logic.Dragon;
import maze.logic.Element;
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
		
		commands = "dw";
		
		gameTest = new GameLogic();
		
		gameTest.setHero(new Hero(1,1,'H'));
		
		gameTest.setMaze(new Maze(maze));
		
		execCommands();
		
		assertEquals(comp,gameTest.getHero());
	}
	
	@Test
	public void TestMoveHeroWall() {
		
		Hero comp = new Hero(1,1,'H');
		
		commands = "wa";
		
		gameTest = new GameLogic();
		
		gameTest.setHero(new Hero(1,1,'H'));
		
		gameTest.setMaze(new Maze(maze));
		
		execCommands();
		
		assertEquals(comp,gameTest.getHero());
	}
	
	@Test
	public void TestCatchTheSword() {
		
		commands = "ddds";
		
		gameTest = new GameLogic();
		
		gameTest.setHero(new Hero(1,1,'H'));
		
		gameTest.setMaze(new Maze(maze));
		
		gameTest.setSword(new Element(2,4,'E'));
		
		execCommands();
		
		gameTest.checkForFoundSword();
		
		assertTrue(gameTest.getHero().isArmed());
	}
	
	@Test
	public void TestLaDerrote() {
		
		commands = "ddd";
		
		gameTest = new GameLogic();
		
		gameTest.setHero(new Hero(1,1,'H'));
		
		gameTest.getHero().setArmed(true);
		
		gameTest.setMaze(new Maze(maze));
		
		gameTest.setDragons(new Dragon[1]);
		
		gameTest.getDragons()[0] = new Dragon(2,4,'D');
		
		execCommands();
		
		gameTest.checkForDragonEncounters();
		
		assertFalse(gameTest.getDragons()[0].isAlive());
	}
	
	@Test
	public void TestLeSlain() {
		
		commands = "ddd";
		
		gameTest = new GameLogic();
		
		gameTest.setHero(new Hero(1,1,'H'));
		
		gameTest.setMaze(new Maze(maze));
		
		gameTest.setDragons(new Dragon[1]);
		
		gameTest.getDragons()[0] = new Dragon(2,4,'D');
		
		execCommands();
		
		gameTest.checkForDragonEncounters();
		
		assertFalse(gameTest.getHero().isAlive());
	}
	
	@Test
	public void TestLaVitoire() {
		
		commands = "ddddw";
		
		gameTest = new GameLogic();
		
		gameTest.setHero(new Hero(1,1,'H'));
		
		gameTest.getHero().setArmed(true);
		
		gameTest.setMaze(new Maze(maze));
		
		gameTest.setDragons(new Dragon[1]);
		
		gameTest.getDragons()[0] = new Dragon(2,4,'D');
		
		gameTest.getDragons()[0].setAlive(false);
		
		gameTest.getMaze().setExit(new Element(0,5,'S'));
		
		execCommands();
		
		assertTrue(gameTest.checkIfHeroWon());
	}
	
	@Test
	public void TestCoward() {
		
		commands = "ddddw";
		
		gameTest = new GameLogic();
		
		gameTest.setHero(new Hero(1,1,'H'));
		
		gameTest.setMaze(new Maze(maze));
		
		gameTest.setDragons(new Dragon[1]);
		
		gameTest.getDragons()[0] = new Dragon(2,4,'D');
		
		gameTest.getMaze().setExit(new Element(0,5,'S'));
		
		execCommands();
		
		assertFalse(gameTest.checkIfHeroWon());
		
		gameTest.getHero().setArmed(true);
		
		assertFalse(gameTest.checkIfHeroWon());
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