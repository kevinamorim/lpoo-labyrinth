package maze.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import maze.logic.GameLogic;

public class GameWindow implements KeyListener {
	
	private final JFrame frame;
	private JLabel label;
	
	private int keyCode;

	public GameWindow(GameLogic gameLogic) {
		frame = new JFrame("Game Status");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 600));
		
		Container maze = frame.getContentPane();
		maze.setLayout(new GridLayout(gameLogic.getMaze().getSize(), 
				gameLogic.getMaze().getSize()));
		
		for(int i = 0; i < gameLogic.getMaze().getSize(); i++) {
			for(int j = 0; j < gameLogic.getMaze().getSize(); j++) {
				JLabel tile = new JLabel("" + gameLogic.getMaze().getTiles()[i][j]);
				maze.add(tile);
			}
		}
		
		// Adds a keyListener to the game JFrame
		frame.addKeyListener(this);
		// Inicializes the variable keyCode;
		this.setKeyCode(0);
		
		frame.pack();
		frame.setVisible(true);
	
	}
	
	/**
	 * Every time a key is pressed, this method is called
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		saveKeyCode(e);
	}

	/**
	 * Every time a key is released, this method is called
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {	
	}

	/**
	 * Every time a key is typed, this method is called
	 * 
	 * @param e
	 */
	public void keyTyped(KeyEvent e) {
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
	 * @param keyCode the keyCode to set
	 */
	public void resetKeyCode() {
		this.keyCode = 0;
	}
	
	/**
	 * Saves to the variable 'keyCode' the value received by an keyboard event
	 * 
	 * @param e
	 */
	private void saveKeyCode(KeyEvent e) {
		this.keyCode = e.getKeyCode();
	}

}
