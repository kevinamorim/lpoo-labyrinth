package maze.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JSplitPane;

import java.awt.Window.Type;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JMenuItem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.Action;
import javax.swing.JMenuBar;

import maze.logic.Dragon;
import maze.logic.GameConfig;
import maze.logic.GameLogic;
import maze.logic.Maze;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;


public class GameWindow extends JFrame implements KeyListener {

	private JFrame frame;
	private JLabel label;
	private int keyCode;

	private GameLogic game;
	private GameConfig config;

	BufferedImage wall, floor, dragonPic, hero, eagle, sword, exit, heroWithEagle, eagleWithSword, eagleUponDragon, eagleUponWall, eagleUponWallWithSword;

	private int xSize,ySize;

	/**
	 * Create the application.
	 */
	public GameWindow(GameLogic game) {
		
		this.game = game;
		this.config = game.getConfig();
		
		// Inicializes the variable keyCode;
		this.setKeyCode(0);

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(new GridLayout(game.getMaze().getSize(), game.getMaze().getSize(), 0, 0));
		//frame.getContentPane().setLayout(new GridLayout(9,9, 0, 0));
		
		xSize = 1000;
		ySize = 1000;
		
		frame.setPreferredSize(new Dimension(xSize, ySize));
		
		// Adds a keyListener to the game JFrame
		frame.addKeyListener(this);
		
		initBufferedImages();
		
		drawBoard();
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFocusable(false);
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		menuBar.add(btnNewGame);
		
		JButton btnNewButton = new JButton("Restart");
		btnNewButton.setFocusable(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Configurations");
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		menuBar.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Help");
		btnNewButton_2.setFocusable(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "A tua mae", "HELP", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuBar.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.setFocusable(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menuBar.add(btnNewButton_3);
		
		frame.pack();
		frame.setVisible(true);
		
	}

	public void paint() {
		drawBoard();
	}
	
	private void drawBoard() {
		
		int i,j;

		Container maze = frame.getContentPane();
		
		maze.removeAll();

		for(i = 0; i < game.getMaze().getSize(); i++) {
			for(j = 0; j < game.getMaze().getSize(); j++) {
				
				drawCorrectImageToPanel(i, j);

				//label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

				maze.add(label, BorderLayout.CENTER);
				
			}
		}
		
		maze.revalidate();

	}

	private void drawCorrectImageToPanel(int x, int y) {

		for(Dragon dragon: game.getDragons()) {
			if(dragon.isAlive()) {
				if(dragon.isAt(x, y)) {
					if(game.getEagle().isAt(x, y)) {
						drawToPanel(eagleUponDragon);
					}
					else {
						drawToPanel(dragonPic);
					}
					return;
				}
			}
		}

		if(game.getEagle().isAlive()) {
			if(game.getEagle().isAt(x, y)) {
				if(game.getEagle().hasSword()) {
					if(game.getMaze().getTiles()[x][y] == 'x') {
						drawToPanel(eagleUponWall);
					}
					else {
						drawToPanel(eagle);
					}
				}
				else {
					if(game.getMaze().getTiles()[x][y] == 'x') {
						drawToPanel(eagleUponWallWithSword);
					}
					else {
						drawToPanel(eagle);
					}
				}
				return;
			}
		}

		if(!game.getHero().isArmed() && !game.getEagle().hasSword()) {
			if(game.getSword().isAt(x, y)) {
				drawToPanel(sword);
				return;
			}
		}

		switch(game.getMaze().getTiles()[x][y]) {
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
			//hero = ImageIO.read(new File("bin/textures/hero_on_floor.png"));
			eagle = ImageIO.read(new File("bin/textures/eagle_on_floor.png"));
			sword = ImageIO.read(new File("bin/textures/sword_on_floor.png"));
			//exit = ImageIO.read(new File("bin/textures/exit.png"));
			
			//heroWithEagle = ImageIO.read(new File("bin/textures/hero_with_eagle.png"));
			eagleWithSword = ImageIO.read(new File("bin/textures/eagle_on_floor_with_sword.png"));
			eagleUponDragon = ImageIO.read(new File("bin/textures/eagle_upon_dragon.png"));
			eagleUponWall = ImageIO.read(new File("bin/textures/eagle_upon_wall.png"));
			eagleUponWallWithSword = ImageIO.read(new File("bin/textures/eagle_upon_wall_with_sword.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception" + e);
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
}
