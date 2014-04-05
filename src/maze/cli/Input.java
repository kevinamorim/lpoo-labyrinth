package maze.cli;

import java.util.Scanner;

public class Input {

	private Scanner scan = new Scanner(System.in);

	/**
	 * Gets user input and sets map accordingly.
	 */
	public int get() {
		
		String s = scan.nextLine();
		
		return getKeyPressed(s);
	}

	/**
	 * @return the keyPressed
	 */
	public int getKeyPressed(String s) {
		
		if(s.length() > 0) {
			
			switch(s.charAt(0)) {
			case 'w':
				return 87;
			case 'd':
				return 68;
			case 's':
				return 83;
			case 'a':
				return 65;
			case ' ':
				return 32;
			default:
				break;
			}
		}
		
		return -1;
	}
	
	/**
	 * @return
	 */
	public String getString() {
		String s = scan.nextLine();
		
		return s;
	}
}
