import java.util.Scanner;

public class Input {
	
	private Scanner scan = new Scanner(System.in);

	// Returns the next line of input read from the System.in stream
	public String getScan() {
		return scan.nextLine();
	}
	
}
