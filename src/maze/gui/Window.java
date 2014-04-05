package maze.gui;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame {

	protected JFrame frame;
	
	protected int keyCode;
	
	public Window() {
		this.keyCode = 0;
		this.frame = new JFrame();
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

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
