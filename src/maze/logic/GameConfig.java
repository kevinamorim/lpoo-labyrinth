package maze.logic;

import java.io.Serializable;

import maze.cli.Input;
import maze.cli.Output;

public class GameConfig implements Serializable {
	
	private static final long serialVersionUID = 1;

	private Input in;
	private Output out;
	private int mazeSize;
	private int difficulty;
	private int mazeDragons;
	
	private double dragonPerc;
	
	private int CONSOLE = 0;

	private int mode;
	
	// KeyCodes
	// For now: W,D,S,A -> UP,RIGHT,DOWN,LEFT
	private int gameKeyCodes[] = {87, 68, 83, 65, 32};
	
	public GameConfig() {}

	/**
	 * Constructor for a game configuration.
	 * Sets the dragon number percentage.
	 * Reads the difficulty and the maze size from the user.
	 * Sets the game to NON-graphical mode.
	 * 
	 * @param dragonPerc : percentage of dragons in the maze
	 */
	public GameConfig(double dragonPerc) {

		in = new Input();
		out = new Output();
		
		mazeSize = inputMazeSize(in, out);
		difficulty = inputGameDifficulty(in, out);
		
		this.dragonPerc = dragonPerc;
		
		// Console mode is the default mode.
		mode = CONSOLE;

	}
	
	/**
	 * Constructor for a game configuration.
	 * Sets the dragon number percentage.
	 * Reads the difficulty and the maze size from the user.
	 * Sets the game to graphical or NON-graphical mode according to the parameter [isConsole].
	 * 
	 * @param isConsole : true if the game is in NON-graphical mode
	 * @param dragonPerc : percentage of dragons in the maze
	 */
	public GameConfig(int mode, double dragonPerc) {

		if(mode == CONSOLE) {
			in = new Input();
			out = new Output();
			
			mazeSize = inputMazeSize(in, out);
			difficulty = inputGameDifficulty(in, out);
		}
		
		this.dragonPerc = dragonPerc;
		this.mazeDragons = (int) (this.mazeSize * this.mazeSize * this.dragonPerc);
		
		this.mode = mode;

	}
	
	/**
	 * Constructor for a game configuration.
	 * Sets the dragon number percentage.
	 * Sets the maze size and difficulty.
	 * Sets the game to graphical or NON-graphical mode according to the parameter [isConsole].
	 * 
	 * @param mazeSize : the size of the maze
	 * @param difficulty : the difficulty of the game [3 available]
	 * @param isConsole : true if the game is in NON-graphical mode
	 * @param dragonPerc : percentage of dragons in the maze
	 */
	public GameConfig(int mazeSize, int difficulty, boolean isConsole, double dragonPerc) {
		
		this.mazeSize = mazeSize;
		this.difficulty = difficulty;
		
		this.dragonPerc = dragonPerc;
	}

	/**
	 * Sets the game key code for the array of key codes at the given index.
	 * 
	 * @param index : position to be replaced
	 * @param code : key code to set
	 */
	public void setGameKey(int index, int code) {
		this.gameKeyCodes[index] = code;
	}

	/**
	 * Returns the array containing the key codes.
	 * 
	 * @return the gameKeyCodes
	 */
	public int[] getGameKeyCodes() {
		return gameKeyCodes;
	}

	/**
	 * Sets the array of key codes
	 * 
	 * @param gameKeyCodes : the gameKeyCodes to set
	 */
	public void setGameKeyCodes(int[] gameKeyCodes) {
		this.gameKeyCodes = gameKeyCodes;
	}

	/**
	 * Gets the maze size from the user.
	 * 
	 * @param in : input stream
	 * @param out : output stream
	 * @return the maze size
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
	
	/**
	 * Gets the game difficulty from the user.
	 * 
	 * @param in : input stream
	 * @param out : output stream
	 * @return the difficulty
	 */
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
	 * Returns the maze size.
	 * 
	 * @return the mazeSize
	 */
	public int getMazeSize() {
		return mazeSize;
	}

	/**
	 * Sets the maze size variable.
	 * 
	 * @param mazeSize : the mazeSize to set
	 */
	public void setMazeSize(int mazeSize) {
		this.mazeSize = mazeSize;
	}

	/**
	 * Returns the game difficulty.
	 * 
	 * @return the difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * Sets the game difficulty.
	 * 
	 * @param difficulty : the difficulty to set
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Checks if a string is a number.
	 * 
	 * @param s : String to evaluate
	 * @return true if is a number
	 */
	public boolean isValid(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }

	    return true;
	}
	
	/**
	 * Checks if a number is in the interval [min, max].
	 * 
	 * @param min : minimal value of the interval
	 * @param max : maximal value of the interval
	 * @param i : number to evaluate
	 * @return true if in interval
	 */
	public boolean isBetween(int min, int max, int i) {
		
		if( (i >= min) && (i <= max) ) return true;
		else return false;

	}
	
	/**
	 * Checks if a number is odd or even
	 * 
	 * @param i : number to evaluate
	 * @return true if odd
	 */
	public boolean isOdd(int i) {
		return ((i % 2) != 0);
	}

	/**
	 * Returns the dragon percentage.
	 * 
	 * @return the dragonPerc
	 */
	public double getDragonPerc() {
		return dragonPerc;
	}

	/**
	 * Sets the dragon percentage of the game.
	 * 
	 * @param dragonPerc : the dragonPerc to set
	 */
	public void setDragonPerc(double dragonPerc) {
		this.dragonPerc = dragonPerc;
	}

	/**
	 * @return the mode
	 */
	public int getMode() {
		return mode;
	}

	/**
	 * @param mode : the mode to set
	 */
	public void setMode(int mode) {
		this.mode = mode;
	}

	/**
	 * @return the mazeDragons
	 */
	public int getMazeDragons() {
		return mazeDragons;
	}

	/**
	 * @param mazeDragons the mazeDragons to set
	 */
	public void setMazeDragons(int mazeDragons) {
		this.mazeDragons = mazeDragons;
	}

}
