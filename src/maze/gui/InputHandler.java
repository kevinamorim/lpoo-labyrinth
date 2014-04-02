package maze.gui;

import java.util.concurrent.CopyOnWriteArrayList;

public class InputHandler implements Runnable {
	
	private GameWindow gameWindow;
	private int keyCode;
	private CopyOnWriteArrayList<Integer> commands;
	
	public InputHandler(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
		this.setKeyCode(0);
		this.commands = new CopyOnWriteArrayList<Integer>();
	}

	@Override
	public void run() {
		
		while(true) {
			
			if(gameWindow.getKeyCode() != 0) {
				setKeyCode(gameWindow.getKeyCode());
				commands.add(keyCode);
				gameWindow.resetKeyCode();
			}
			
			try {
				Thread.sleep(6);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
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
			commands.remove(0);
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

}
