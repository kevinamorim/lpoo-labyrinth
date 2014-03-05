package maze.logic;

import maze.cli.Input;
import maze.cli.Output;

public class GameConfig {
	
	private Input in;
	private Output out;
	private int mazeSize;

	public GameConfig() {
		
		in = new Input();
		out = new Output();
		
		String s;
		
		out.drawMsg(4);
		
		boolean deuMerda = false;
		
		do {
			
			if(deuMerda) {
				out.drawMsg(5);
				out.drawMsg(69);
			}
			
			s = in.getString();
			
			deuMerda = true;
			
		} while(!isValid(s));
		
		mazeSize = Integer.parseInt(s);

	}
	
	public boolean isValid(String s) {
		int i;
	    try { 
	        i = Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    
        if(i > 21) return false;
        if(i < 5) return false;
        if(i % 2 == 0) return false;
        // If control+z = $Q#%$Q

	    return true;
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


}
