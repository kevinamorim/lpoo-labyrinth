import java.util.Scanner;

public class Input {
	
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * Keys array. 
	 * 
	 * 0 - Up
	 * 1 - Right
	 * 2 - Down
	 * 3 - Left
	 * 
	 * ( Starting with Up following clockwise ). 
	 */
	private Boolean[] keys = new Boolean[4];

	/**
	 * Constructor.
	 */
	public Input() {
		
		// Initializes keys array.
		ResetKeys();
		
	}
	
	/**
	 * @return Keys array.
	 */
	public Boolean[] getKeys() {
		return keys;
	}
	
	/**
	 * Sets all keys values to false. 
	 */
	private void ResetKeys() {
		
		// Sets everything to false.
		for(int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
		
	}

	/**
	 * Returns the next line of input read from the System.in stream
	 * @return User console input.
	 */
	public String getScan() {
		return scan.nextLine();
	}
	
	/**
	 * Gets user input and sets map accordingly.
	 */
	public void getInput() {
		
		ResetKeys();
		
		String s = getScan();
		
		switch(s.charAt(0)) {
		case 'w':
			keys[0] = true;
			break;
		case 'd':
			keys[1] = true;
			break;
		case 's':
			keys[2] = true;
			break;
		case 'a':
			keys[3] = true;
			break;
		default:
			break;
		}
		
	}
	
}
