package maze.gui;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JFrame;

public class InputHandler implements Runnable {
	
	protected JFrame window;
	protected int keyCode;
	protected boolean terminate;

	protected CopyOnWriteArrayList<Integer> commands;
	
	public InputHandler(JFrame w) {
		this.window = w;
		this.keyCode = 0;
		this.terminate = false;
		this.commands = new CopyOnWriteArrayList<Integer>();
	}

	public InputHandler() {
	}

	@Override
	public void run() {

		while(!terminate) {
			
			if(window instanceof GameWindow) {
				
				if(((GameWindow) window).getKeyCode() != 0) {
					this.keyCode = ((GameWindow) window).getKeyCode();
					commands.add(keyCode);
					((GameWindow) window).resetKeyCode();
				}
			}

			

			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

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

	/**
	 * @return the commands
	 */
	public CopyOnWriteArrayList<Integer> getCommands() {
		return commands;
	}

	/**
	 * @param commands the commands to set
	 */
	public void setCommands(CopyOnWriteArrayList<Integer> commands) {
		this.commands = commands;
	}
	
	/** 
	 * @return First element of the queue. If the queue is empty, returns -1.
	 */
	public Integer getNextCommand() {
		if(!commands.isEmpty()) {
			int i = commands.get(0);
			return i;
		} else {
			return -1;
		}
	}
	
	/**
	 * Removes the first command of the queue.
	 */
	public void removeCommand() {
		commands.remove(0);
	}
	
	/**
	 * @return the terminate
	 */
	public boolean isTerminate() {
		return terminate;
	}

	/**
	 * @param terminate the terminate to set
	 */
	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}


}
