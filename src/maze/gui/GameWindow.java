package maze.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import maze.logic.GameLogic;

@SuppressWarnings("serial")
public class GameWindow extends JPanel implements KeyListener {
	
	private final JFrame frame;
	private JLabel label;
	private int xSize,ySize;
	
	BufferedImage wall, floor, dragon, hero, eagle, sword;
	
	private int keyCode;

	public GameWindow(GameLogic gameLogic) {
		frame = new JFrame("Game Status");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		xSize = 600;
		ySize = 600;
		int resizeValue = (int) (xSize/gameLogic.getMaze().getSize()*(0.8));
		
		frame.setPreferredSize(new Dimension(xSize, ySize));
		
		initBufferedImages();
		
		Container maze = frame.getContentPane();
		
		maze.setLayout(new GridLayout(gameLogic.getMaze().getSize(), 
				gameLogic.getMaze().getSize()));
		
		for(int i = 0; i < gameLogic.getMaze().getSize(); i++) {
			for(int j = 0; j < gameLogic.getMaze().getSize(); j++) {

				label = new JLabel() {
					public void paintComponent(Graphics g) {
						g.drawImage(wall, 0, 0, this.getWidth(), this.getHeight(), null);
						super.paintComponent(g);
					}
				};

				//label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				maze.add(label, BorderLayout.CENTER);
			}
		}
		
		// Adds a keyListener to the game JFrame
		frame.addKeyListener(this);
		// Inicializes the variable keyCode;
		this.setKeyCode(0);
		
		frame.pack();
		frame.setVisible(true);
	
	}
	
	public void initBufferedImages() {
		
		try {
			
			wall = ImageIO.read(new File("bin/textures/wall.png"));
//			floor = ImageIO.read(new File("textures/floor.png"));
//			dragon = ImageIO.read(new File("textures/dragon.png"));
//			hero = ImageIO.read(new File("textures/hero.png"));
//			eagle = ImageIO.read(new File("textures/eagle.png"));
//			sword = ImageIO.read(new File("textures/sword.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
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
