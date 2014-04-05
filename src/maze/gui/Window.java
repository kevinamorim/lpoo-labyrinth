package maze.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {

	//protected JFrame frame;
	
	protected int keyCode;
	
	public Window() {	
	}
	
	public Window(String title) {
		
		this.keyCode = 0;
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @return the keyCode
	 */
	public int getKeyCode() {
		return keyCode;
	}

	/**
	 * @param keyCode the keyCode to set
	 */
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public void resetKeyCode() {
		this.keyCode = 0;
	}

}
