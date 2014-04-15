package maze.gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;

import maze.logic.Dragon;
import maze.logic.GameLogic;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Cursor;

@SuppressWarnings("serial")
public class GameWindow extends Window implements KeyListener {

	private GameLogic gameLogic;

	private JPanel game;
	
	private BufferedImage wall, floor, dragonPic, hero, eagle, sword, exit;
	private BufferedImage heroWithEagle, heroWithSword, heroWithSwordAndEagle;
	private BufferedImage eagleWithSword, eagleUponDragon, eagleUponDragonAsleep, eagleUponDragonWithSwordAsleep, eagleUponDragonWithSword, eagleUponWall, eagleUponWallWithSword;
	private BufferedImage dragonAsleep, dragonWithSword, dragonWithSwordAsleep;
	
	private JLabel label;

	private int state; 
	
	/**
	 * Create the application.
	 */
	public GameWindow(GameLogic gameLogic) {
		super("Game");
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.gameLogic = gameLogic;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		state = 0;
		
		game = new JPanel();
		
		//frame.setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new CardLayout(0, 0));
		setPreferredSize(new Dimension(800, 800));
		addKeyListener(this);

		// Default grid layout, just for the sake of design. 
		game.setLayout(new GridLayout(9, 9, 0, 0));
		
		getContentPane().add(game, "game");

		initBufferedImages();
		paint();

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem newGameMenuItem = new JMenuItem("New Game");
		newGameMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(JOptionPane.showConfirmDialog(null, "Are you sure? \nNote: all unsaved data will be lost.")) {
				case JOptionPane.YES_OPTION: // GRAPHICAL
					
					keyCode = -2;
					
					break;
				default:
					return;
				}
				
			}
		});
		menuBar.add(newGameMenuItem);
		
		JMenuItem restartGameMenuItem = new JMenuItem("Save Game");
		restartGameMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyCode = -3;
			}
		});
		menuBar.add(restartGameMenuItem);
		
		JMenuItem configurationMenuItem = new JMenuItem("Configuration");
		configurationMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyCode = -4;
			}
		});
		menuBar.add(configurationMenuItem);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(JOptionPane.showConfirmDialog(null, "Are you sure? \nNote: all unsaved data will be lost.")) {
				case JOptionPane.YES_OPTION: // GRAPHICAL
					
					keyCode = -5;
					
					break;
				default:
					return;
				}
			}
		});
		menuBar.add(exitMenuItem);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void paint() {
		drawBoard();
	}
	
	private void drawBoard() {
		
		int i,j;
		
		game.removeAll();
		game.setLayout(new GridLayout(gameLogic.getMaze().getSize(), gameLogic.getMaze().getSize()));
		//game.setLayout(new GridLayout(9, 9, 0, 0));

		for(i = 0; i < gameLogic.getMaze().getSize(); i++) {
			for(j = 0; j < gameLogic.getMaze().getSize(); j++) {
				
				drawCorrectImageToPanel(i, j);

				game.add(label, BorderLayout.CENTER);
				
			}
		}
		
		game.revalidate();

	}

	private void drawCorrectImageToPanel(int x, int y) {

		for(Dragon dragon: gameLogic.getDragons()) {
			
			if(dragon.isAlive()) {
				
				if(dragon.isAt(x, y)) {
					
					if(gameLogic.getEagle().isAt(x, y)) {
						
						if(dragon.hasSword()) {
							if(dragon.isAwake()) {
								drawToPanel(eagleUponDragonWithSword);
							}
							else {
								drawToPanel(eagleUponDragonWithSwordAsleep);
							}			
						}
						else {
							if(dragon.isAwake()) {
								drawToPanel(eagleUponDragon);
							}
							else {
								drawToPanel(eagleUponDragonAsleep);
							}
						}
					}
					else {
						if(dragon.hasSword()) {
							if(dragon.isAwake()) {
								drawToPanel(dragonWithSword);
							}
							else {
								drawToPanel(dragonWithSwordAsleep);
							}
						}
						else {
							if(dragon.isAwake()) {
								drawToPanel(dragonPic);
							}
							else {
								drawToPanel(dragonAsleep);
							}
						}
					}
					
					return;
				}
			}
		}
		
		if(gameLogic.getHero().isAlive()) {
			
			if(gameLogic.getHero().isAt(x, y)) {
				
				if(gameLogic.getHero().hasEagle()) {
					
					if(gameLogic.getHero().hasSword()) {		
						drawToPanel(heroWithSwordAndEagle);
					}
					else {
						drawToPanel(heroWithEagle);
					}
					
				}
				else {
					
					if(gameLogic.getHero().hasSword()) {
						drawToPanel(heroWithSword);
					}
					else {
						drawToPanel(hero);
					}
				}
				
				return;
			}
		}
		
		if (gameLogic.getEagle().isAlive() && !gameLogic.getHero().hasEagle()) {
			
			if(gameLogic.getEagle().isAt(x, y)) {
				
				if(gameLogic.getEagle().hasSword()) {
					
					if(gameLogic.getMaze().getTiles()[x][y] == 'x') {
						drawToPanel(eagleUponWallWithSword);
					}
					else {
						drawToPanel(eagleWithSword);
					}
				}
				else {
					if(gameLogic.getMaze().getTiles()[x][y] == 'x') {
						drawToPanel(eagleUponWall);
					}
					else {
						drawToPanel(eagle);
					}
				}
				return;
			}
		}
		
		if(!gameLogic.getHero().hasSword() && !gameLogic.getEagle().hasSword()) {
			if(gameLogic.getSword().isAt(x, y)) {
				drawToPanel(sword);
				return;
			}
		}
		
		if(gameLogic.getMaze().getExit().isAt(x, y)) {
			drawToPanel(exit);
			return; 
		}

		switch(gameLogic.getMaze().getTiles()[x][y]) {
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
			dragonPic = ImageIO.read(new File("bin/textures/dragon_on_floor.png"));
			hero = ImageIO.read(new File("bin/textures/hero_on_floor.png"));
			eagle = ImageIO.read(new File("bin/textures/eagle_on_floor.png"));
			sword = ImageIO.read(new File("bin/textures/sword_on_floor.png"));
			exit = ImageIO.read(new File("bin/textures/exit.png"));
			
			heroWithEagle = ImageIO.read(new File("bin/textures/hero_with_eagle.png"));
			heroWithSword = ImageIO.read(new File("bin/textures/hero_with_sword.png"));
			heroWithSwordAndEagle = ImageIO.read(new File("bin/textures/hero_with_sword_and_eagle.png"));
			
			eagleWithSword = ImageIO.read(new File("bin/textures/eagle_on_floor_with_sword.png"));
			eagleUponDragon = ImageIO.read(new File("bin/textures/eagle_upon_dragon.png"));
			eagleUponWall = ImageIO.read(new File("bin/textures/eagle_upon_wall.png"));
			eagleUponWallWithSword = ImageIO.read(new File("bin/textures/eagle_upon_wall_with_sword.png"));
			eagleUponDragonWithSword = ImageIO.read(new File("bin/textures/eagle_upon_dragon_with_sword.png"));
			eagleUponDragonWithSwordAsleep = ImageIO.read(new File("bin/textures/eagle_upon_dragon_with_sword_asleep.png"));
			
			dragonAsleep = ImageIO.read(new File("bin/textures/dragon_asleep.png"));
			dragonWithSword = ImageIO.read(new File("bin/textures/dragon_with_sword.png"));
			dragonWithSwordAsleep = ImageIO.read(new File("bin/textures/dragon_with_sword_asleep.png"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			System.out.println("Error loading images. Exception: " + e);
			
			System.exit(0);
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
	 * @param keyCode the keyCode to set
	 */
	public void resetKeyCode() {
		this.keyCode = 0;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.keyCode = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public JPanel getGamePanel() {
		return this.game;
	}
	

	public void setState(int state) {
		this.state = state;
	}
	
	public int getState() {
		return state;
	}
}
