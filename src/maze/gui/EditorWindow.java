package maze.gui;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;

import maze.io.GameIO;
import maze.logic.Dragon;
import maze.logic.Element;
import maze.logic.GameLogic;
import maze.logic.Hero;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Cursor;

import maze.logic.Maze;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;

import javax.swing.JSplitPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class EditorWindow extends Window {
	private GameLogic game;
	private int mazeSize = 10;
	
	private final int numElements = 5;
	private int numberOfDragons = 0;
	private int selected = 0;
	private int picInfo[];

	private JPanel pics;
	private JPanel tiles;

	private BufferedImage wall, floor, dragonPic, hero, sword, exit;

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
		
		this.game = new GameLogic();
		this.game.setMaze(new Maze(size));
		
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
		pics.setLayout(new GridLayout(numElements, 1, 10, 10));
		
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
		
		tiles = new JPanel();
		tiles.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				int i = e.getX() / ((tiles.getWidth() + 5) / mazeSize);
				int j = e.getY() / ((tiles.getHeight() + 5) / mazeSize);
				
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(picInfo[selected] > -1) {
						
						if (isBetween(i, 0, mazeSize-1) && isBetween(j, 0, mazeSize-1)) {
							if(drawMazeImage(i,j) == true) {
								tiles.remove(i+j*mazeSize);
								tiles.add(label, i+j*mazeSize);
								tiles.revalidate();
							}
						}
					}
				}
				else {
					if (isBetween(i, 0, mazeSize-1) && isBetween(j, 0, mazeSize-1)) {
						if(takeMazeImage(i,j) == true) {
							tiles.remove(i+j*mazeSize);
							tiles.add(label, i+j*mazeSize);
							tiles.revalidate();
						}
					}
				}

			}
			
			private boolean takeMazeImage(int i, int j) {
				if(game.getMaze().getExit().isAt(i, j)) {
					drawToPanel(wall);
					picInfo[0] = 0;
					return true;
				}
				
				if(game.getMaze().getTiles()[i][j] == 'Y'){
					drawToPanel(floor);
					game.getMaze().getTiles()[i][j] = ' ';
					picInfo[1] = 0;
					return true;
				}
				
				if(game.getMaze().getTiles()[i][j] == 'E'){
					drawToPanel(floor);
					game.getMaze().getTiles()[i][j] = ' ';
					picInfo[2] = 0;
					return true;
				}
				
				if(game.getMaze().getTiles()[i][j] == 'D'){
					drawToPanel(floor);
					game.getMaze().getTiles()[i][j] = ' ';
					picInfo[3] = 1;
					return true;
				}
				
				if(game.getMaze().getTiles()[i][j] == 'x'){
					drawToPanel(floor);
					game.getMaze().getTiles()[i][j] = ' ';
					picInfo[4] = 1;
					return true;
				}
				
				return false;
			}

			private boolean drawMazeImage(int i, int j) {
				switch(selected) {
				case 0:
					// EXIT
					if(isProperExitPlace(i,j)) {
						drawToPanel(exit);
						game.getMaze().setExit(new Element(i,j,'S'));
						picInfo[selected] = -1;
						return true;
					}
					break;
				case 1:
					// HERO
					if(isBetween(i, 1, mazeSize-2) && isBetween(j, 1, mazeSize-2)) {
						drawToPanel(hero);
						game.getMaze().getTiles()[i][j] = 'Y';
						picInfo[selected] = -1;
						return true;
					}
					break;
				case 2:
					// SWORD
					if(isBetween(i, 1, mazeSize-2) && isBetween(j, 1, mazeSize-2)) {
						drawToPanel(sword);
						game.getMaze().getTiles()[i][j] = 'E';
						picInfo[selected] = -1;
						return true;
					}
					break;
				case 3:
					// DRAGONS
					if(isBetween(i, 1, mazeSize-2) && isBetween(j, 1, mazeSize-2)) {
						game.getMaze().getTiles()[i][j] = 'D';
						drawToPanel(dragonPic);
						return true;
					}
					break;
				case 4:
					// WALL
					if(isBetween(i, 1, mazeSize-2) && isBetween(j, 1, mazeSize-2)) {
						game.getMaze().getTiles()[i][j] = 'x';
						drawToPanel(wall);
						return true;
					}
					break;
				default:
					break;
				}
				
				return false;
			}

			private boolean isProperExitPlace(int i, int j) {
				if(i == 0) {
					if(isBetween(j,1,mazeSize-1)) {
						return true;
					}
				}
				else if(i == (mazeSize - 1)) {
					if(isBetween(j,1,mazeSize-1)) {
						return true;
					}
				}
				else if(j == 0) {
					if(isBetween(i,1,mazeSize-1)) {
						return true;
					}
				}
				else if(j == (mazeSize - 1)) {
					if(isBetween(i,1,mazeSize-1)) {
						return true;
					}
				}
				
				return false;
			}
		});
		tiles.setLayout(new GridLayout(mazeSize,mazeSize,1,1));
		
		for(int i = 0; i < mazeSize; i++) {
			for(int j = 0; j < mazeSize; j++) {
				if((i == 0) || (j == 0) || (i == (mazeSize - 1)) || (j == (mazeSize - 1))) {
					drawToPanel(wall);
					game.getMaze().getTiles()[i][j] = 'x';
					tiles.add(label);
				}
				else {
					drawToPanel(floor);
					game.getMaze().getTiles()[i][j] = ' ';
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
				if(isVerified()) {
					saveElements();
					saveMaze();
					keyCode = 1;
				}
			}
		});
		menuBar.add(saveItem);
		
		JMenuItem helpItem = new JMenuItem("Help");
		helpItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog helpDialog = new JDialog(null,"Help",Dialog.ModalityType.APPLICATION_MODAL);
				
				helpDialog.setFont(new Font("Sakkal Majalla", Font.BOLD, 22));
				
				helpDialog.setLayout(new GridLayout(3,3,10,10));
				helpDialog.setLocationRelativeTo(null);
				helpDialog.setFocusable(true);
				helpDialog.setResizable(false);
				
				helpDialog.add(new JLabel("Mouse Button", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				helpDialog.add(new JLabel("Left Panel", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				helpDialog.add(new JLabel("Right Panel", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				
				helpDialog.add(new JLabel("Left Button", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				helpDialog.add(new JLabel("Select", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				helpDialog.add(new JLabel("Add", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				
				helpDialog.add(new JLabel("Right Button", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				helpDialog.add(new JLabel("Select", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				helpDialog.add(new JLabel("Remove", JLabel.CENTER), BorderFactory.createLineBorder(new Color(0,0,0),2));
				
				helpDialog.pack();
				helpDialog.setVisible(true);
				keyCode = 2;
			}
		});
		menuBar.add(helpItem);
		
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				keyCode = 3;
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

	protected void saveElements() {
		for(int i = 0; i < game.getMaze().getTiles().length; i++) {
			for(int j = 0; j < game.getMaze().getTiles().length; j++) {
				if(game.getMaze().getTiles()[i][j] == 'Y') {
					game.setHero(new Hero(i,j,'Y'));
				}
				else if(game.getMaze().getTiles()[i][j] == 'E') {
					game.setSword(new Element(i,j,'Y'));
				}
				else if(game.getMaze().getTiles()[i][j] == 'D') {
					numberOfDragons++;
				}
			}
		}
		
		game.setDragons(new Dragon[numberOfDragons]);
		
		int index = 0;
		
		for(int i = 0; i < game.getMaze().getTiles().length; i++) {
			for(int j = 0; j < game.getMaze().getTiles().length; j++) {
				if(game.getMaze().getTiles()[i][j] == 'D') {
					game.getDragons()[index] = new Dragon(i,j,'D');
					index++;
				}
			}
		}
	}
	
	/**
	 * Creates an instance of GameIO to save the game.
	 * For more information on GameIO consult the class.
	 * 
	 * @return 0 if the user has pressed the 'save' button
	 */
	public int saveMaze() {
		GameIO gameIO = new GameIO();
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".maze files", new String[] {"maze"});
		fileChooser.setFileFilter(filter);
		fileChooser.setCurrentDirectory(new File( "." ));
		
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			
			String fileName = fileChooser.getSelectedFile().getName();
			gameIO.save(game, fileName, ".maze");
		}
		else {
			return -1;
		}
		
		return 0;
	}

	private boolean isVerified() {
		if(picInfo[0] >= 0) {
			JOptionPane.showMessageDialog(this, "You must place an exit!");
			return false;
		}
		
		if(picInfo[1] >= 0) {
			JOptionPane.showMessageDialog(this, "You must place a hero!");
			return false;
		}
		
		if(picInfo[2] >= 0) {
			JOptionPane.showMessageDialog(this, "You must place a sword!");
			return false;
		}

		return true;
	}
	
	private boolean isBetween(int num, int min, int max) {
		
		if(num >= min && num <= max) {
			return true;
		}
		return false;
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
			sword = ImageIO.read(new File("bin/textures/sword_on_floor.png"));
			exit = ImageIO.read(new File("bin/textures/exit.png"));

		} catch (IOException e) {

			e.printStackTrace();

			System.out.println("Error loading images. Exception: " + e);

			System.exit(0);
		}

	}
}
