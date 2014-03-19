package maze.logic;

import maze.cli.Input;
import maze.cli.Output;

public class GameConfig {
	
	private Input in;
	private Output out;
	private int mazeSize;
	private int difficulty;
	private boolean isConsole;

	// KeyCodes
	// For now: W,D,S,A -> UP,RIGHT,DOWN,LEFT
	private int gameKeyCodes[] = {87, 68, 83, 65};

	public GameConfig() {

		in = new Input();
		out = new Output();
		
		mazeSize = inputMazeSize(in, out);
		difficulty = inputGameDifficulty(in, out);
		
		this.isConsole = true;

	}
	
	public GameConfig(boolean isConsole) {

		in = new Input();
		out = new Output();
		
		mazeSize = inputMazeSize(in, out);
		difficulty = inputGameDifficulty(in, out);
		
		this.isConsole = true;

	}
	
	public GameConfig(int mazeSize, int difficulty, boolean isConsole) {
		
		this.mazeSize = mazeSize;
		this.difficulty = difficulty;
		this.isConsole = isConsole;
	}
	
	public void setGameKey(int index, int code) {
		this.gameKeyCodes[index] = code;
	}

	/**
	 * @return the gameKeyCodes
	 */
	public int[] getGameKeyCodes() {
		return gameKeyCodes;
	}

	/**
	 * @param gameKeyCodes the gameKeyCodes to set
	 */
	public void setGameKeyCodes(int[] gameKeyCodes) {
		this.gameKeyCodes = gameKeyCodes;
	}

	/**
	 * @param in
	 * @param out
	 * @return
	 */
	public int inputMazeSize(Input in, Output out) {
		
		String s;
		
		out.drawMsg(4);
		
		boolean deuMerda = false;
		
		int value;
		
		do {
			
			if(deuMerda) {
				out.drawMsg(5);
				out.drawMsg(69);
			}
			
			s = in.getString();
			
			deuMerda = true;
			
			if(isValid(s)) value = Integer.parseInt(s);
			else value = 0;
			
		} while(!(isBetween(5, 21, value) && isOdd(value)));
		
		
		return value;
		
	}
	
	public int inputGameDifficulty(Input in, Output out) {
		
		out.drawMsg(6);
		
		String s;
		
		boolean deuMerda = false;
		
		int value;
		
		do {
			
			if(deuMerda) {
				out.drawMsg(5);
				out.drawMsg(69);
			}
			
			s = in.getString();
			
			deuMerda = true;
			
			if(isValid(s)) value = Integer.parseInt(s);
			else value = 0;
			
		} while(!isBetween(1, 3, value));
		
		value = Integer.parseInt(s);
		
		return value;
		
	}
	
	/**
	 * @return the mazeSize
	 */
	public int getMazeSize() {
		return mazeSize;
	}

	/**
	 * @param mazeSize the mazeSize to set
	 */
	public void setMazeSize(int mazeSize) {
		this.mazeSize = mazeSize;
	}

	/**
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	
	/**
	 * @return the isConsole
	 */
	public boolean isConsole() {
		return isConsole;
	}
	
	/**
	 * @return the isConsole
	 */
	public boolean isGraphical() {
		return (!isConsole);
	}

	/**
	 * @param isConsole the isConsole to set
	 */
	public void setConsole(boolean isConsole) {
		this.isConsole = isConsole;
	}

	/**
	 * Helpers
	 */
	public boolean isValid(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }

	    return true;
	}
	
	public boolean isBetween(int min, int max, int i) {
		
		if( (i >= min) && (i <= max) ) return true;
		else return false;

	}
	
	public boolean isOdd(int i) {
		return ((i % 2) != 0);
	}
	

}
