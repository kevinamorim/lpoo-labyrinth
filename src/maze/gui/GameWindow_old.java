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
public class GameWindow_old extends JPanel implements KeyListener {
	
	private final JFrame frame;
	private JLabel label;
	private int xSize,ySize;
	private GameLogic gameLogic;
	
	BufferedImage wall, floor, dragon, hero, eagle, sword;
	
	private int keyCode;

	public GameWindow_old(GameLogic gameLogic) {
		
		this.gameLogic = gameLogic;
		
		frame = new JFrame("Game Status");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		xSize = 1000;
		ySize = 1000;
		
		frame.setPreferredSize(new Dimension(xSize, ySize));
		
		initBufferedImages();
		
		drawBoard();
		
		// Adds a keyListener to the game JFrame
		frame.addKeyListener(this);
		// Inicializes the variable keyCode;
		this.setKeyCode(0);
		
		frame.pack();
		frame.setVisible(true);
	
	}

	private void drawBoard() {
		
		int i,j;

		Container maze = frame.getContentPane();

		maze.setLayout(new GridLayout(gameLogic.getMaze().getSize(), 
				gameLogic.getMaze().getSize()));

		for(i = 0; i < gameLogic.getMaze().getSize(); i++) {
			for(j = 0; j < gameLogic.getMaze().getSize(); j++) {
				
				drawCorrectImageToPanel(i, j);

				//label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				maze.add(label, BorderLayout.CENTER);
			}
		}

	}

	private void drawCorrectImageToPanel(int i, int j) {
		
		switch(gameLogic.getMaze().getTiles()[i][j]) {
		case 'x':
			drawToPanel(wall);
			break;
		case ' ':
			drawToPanel(floor);
			break;
		default:
			drawToPanel(floor);
			break;
		}
		
		
	}

	private void drawToPanel(final Image name) {
		label = new JLabel() {
			
			public void paintComponent(Graphics g) {
				
				g.drawImage(name, 0, 0, this.getWidth(), this.getHeight(), null);
				
				this.setOpaque(false);
				super.paintComponent(g);
			}
			
		};
		
	}

	public void initBufferedImages() {
		
		try {
			
			wall = ImageIO.read(new File("bin/textures/wall.png"));
			floor = ImageIO.read(new File("bin/textures/floor.png"));
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
