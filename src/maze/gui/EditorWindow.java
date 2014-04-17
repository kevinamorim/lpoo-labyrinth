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

import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Cursor;

import maze.logic.Maze;

import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class EditorWindow extends Window {
	private Maze maze;
	private int mazeSize = 10;
	
	private boolean hasExit = false;

	private JPanel editor;
	private JPanel pics;
	private JPanel tiles;

	private BufferedImage wall, floor, dragonPic, hero, eagle, sword, exit;

	private JLabel label;
	
	/**
	 * Default Constructor.
	 */
	public EditorWindow() {
		super("Editor");
		initialize();
	}

	/**
	 * Constructor.
	 * Receives the current GameLogic instance.
	 * 
	 * @param gameLogic : current game
	 */
	public EditorWindow(int size) {
		super("Editor");

		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		this.maze = new Maze(size);
		this.mazeSize = size;
		
		initialize();
	}

	/**
	 * Initializes the entire game window.
	 *   Adds the labels, buttons, sliders, etc...
	 */
	private void initialize() {
		
		int numElements = 6;
		
		setResizable(false);
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new CardLayout(0, 0));
		setPreferredSize(new Dimension(900, 800));
		
		initBufferedImages();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(100);
		getContentPane().add(splitPane, "split");
		
		tiles = new JPanel();
		tiles.setLayout(new GridLayout(10,10,1,1));
		
		/* ____________________________________________________________
		 * 
		 * 						MAZE
		 * ____________________________________________________________
		 */
		
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				if((i == 0) || (j == 0) || (i == 9) || (j == 9)) {
					drawToPanel(wall);
					tiles.add(label);
				}
				else {
					drawToPanel(floor);
					tiles.add(label);
				}
			}
		}
		
		/* ____________________________________________________________
		 * 
		 * 						MENU BUTTONS
		 * ____________________________________________________________
		 */
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem newItem = new JMenuItem("New");
		menuBar.add(newItem);
		
		JMenuItem saveItem = new JMenuItem("Save");
		menuBar.add(saveItem);
		
		JMenuItem helpItem = new JMenuItem("Help");
		menuBar.add(helpItem);
		
		JMenuItem quitItem = new JMenuItem("Quit");
		menuBar.add(quitItem);
		
		splitPane.setRightComponent(tiles);
		
		this.getContentPane().add(splitPane);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Paint method. Used to paint the frame whenever a change is made.
	 */
	public void paint() {
	}

	/**
	 * Draws to a JLabel the given image.
	 * 
	 * @param name : name of the image
	 */
	private void drawToPanel(final Image name) {
		label = new JLabel("",JLabel.CENTER) {

			public void paintComponent(Graphics g) {

				g.drawImage(name, 0, 0, this.getWidth(), this.getHeight(), null);

				this.setOpaque(false);
				super.paintComponent(g);
			}

		};
		
		label.setBorder(BorderFactory.createLineBorder(new Color(0,0,0)));

	}

	/**
	 * Loads to memory all the pictures.
	 */
	public void initBufferedImages() {

		try {

			wall = ImageIO.read(new File("bin/textures/wall.png"));
			floor = ImageIO.read(new File("bin/textures/floor.png"));
			dragonPic = ImageIO.read(new File("bin/textures/dragon_on_floor.png"));
			hero = ImageIO.read(new File("bin/textures/hero_on_floor.png"));
			eagle = ImageIO.read(new File("bin/textures/eagle_on_floor.png"));
			sword = ImageIO.read(new File("bin/textures/sword_on_floor.png"));
			exit = ImageIO.read(new File("bin/textures/exit.png"));

		} catch (IOException e) {

			e.printStackTrace();

			System.out.println("Error loading images. Exception: " + e);

			System.exit(0);
		}

	}
}
