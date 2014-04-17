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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class EditorWindow extends Window {
	private Maze maze;
	private int mazeSize = 10;
	
	private final int numElements = 5;
	private int selected = 0;
	private int picInfo[];
	
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
		
		picInfo = new int[numElements];
		
		
		initialize();
	}

	/**
	 * Initializes the entire game window.
	 *   Adds the labels, buttons, sliders, etc...
	 */
	private void initialize() {
		
		picInfo[0] = 0; // Exit
		picInfo[1] = 0; // Hero
		picInfo[2] = 0; // Sword
		picInfo[3] = 1; // Dragons
		picInfo[4] = 1; // Walls
		
		setResizable(false);
		setBounds(100, 100, 500, 500);
		getContentPane().setLayout(new CardLayout(0, 0));
		setPreferredSize(new Dimension(900, 800));
		
		initBufferedImages();
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(100);
		getContentPane().add(splitPane, "split");
		
		pics = new JPanel();
		pics.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				selected = (e.getY() / (pics.getHeight() / numElements));
				drawSamples();
			}
		});
		pics.setLayout(new GridLayout(5,1,10,10));
		
		/* ____________________________________________________________
		 * 
		 * 						PICS
		 * ____________________________________________________________
		 */
		
		drawSamples();
		
		/* ____________________________________________________________
		 * 
		 * 						MAZE
		 * ____________________________________________________________
		 */
		
		int test = 20;
		
		tiles = new JPanel();
		tiles.setLayout(new GridLayout(test,test,1,1));
		
		for(int i = 0; i < test; i++) {
			for(int j = 0; j < test; j++) {
				if((i == 0) || (j == 0) || (i == (test - 1)) || (j == (test - 1))) {
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
		
		JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyCode = 2;
			}
		});
		menuBar.add(saveItem);
		
		JMenuItem helpItem = new JMenuItem("Help");
		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyCode = 3;
			}
		});
		menuBar.add(helpItem);
		
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				keyCode = 4;
			}
		});
		menuBar.add(quitItem);
		
		splitPane.setEnabled(false);
		splitPane.setBorder(BorderFactory.createLineBorder(new Color(128,128,128),5));
		splitPane.setLeftComponent(pics);
		splitPane.setRightComponent(tiles);
		
		this.getContentPane().add(splitPane);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void drawSamples() {
		pics.removeAll();
		
		drawSampleToPanel(exit, 0);
		pics.add(label);
		
		drawSampleToPanel(hero, 1);
		pics.add(label);
		
		drawSampleToPanel(sword, 2);
		pics.add(label);
		
		drawSampleToPanel(dragonPic, 3);
		pics.add(label);
		
		drawSampleToPanel(wall, 4);
		pics.add(label);
		
		pics.revalidate();
	}

	/**
	 * Paint method. Used to paint the frame whenever a change is made.
	 */
	public void paint() {
	}
	
	private void drawSampleToPanel(final Image name, int index) {
		
		final int border = 4;
		
		label = new JLabel("",JLabel.CENTER) {
			
			public void paintComponent(Graphics g) {

				g.drawImage(name, border, border, this.getWidth() - border, this.getHeight() - border, null);

				this.setOpaque(false);
				super.paintComponent(g);
			}

		};
		
		if(index == selected) {
			label.setBorder(BorderFactory.createLineBorder(new Color(0,0,0), border));
		}
		else if(picInfo[index] == 1) {
			label.setBorder(BorderFactory.createLineBorder(new Color(0,128,0), border));
		}
		else if(picInfo[index] == 0) {
			label.setBorder(BorderFactory.createLineBorder(new Color(0,0,255), border));
		}
		else {
			label.setBorder(BorderFactory.createLineBorder(new Color(255,0,0), border));
		}
		
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
