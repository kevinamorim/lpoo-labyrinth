package maze.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.GameConfig;

import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.CardLayout;

import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.JFormattedTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ConfigurationWindow extends Window {

	private JPanel panel;
	private JPanel layer1, layer2;
	private GameConfig config;

	private int localKeyCode = 0;
	
	/**
	 * Create the frame.
	 */

	public ConfigurationWindow() {
		initialize();	
	}
	
	public ConfigurationWindow(String title, final GameConfig c) {
		super(title);
		this.config = c;
		
		initialize();		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {

		setBounds(100, 100, 711, 512);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		/* _______________________________________________________________________________________
		 * 
		 * 										LAYER 1
		 * _______________________________________________________________________________________
		 */
		
		layer1 = new JPanel();
		panel.add(layer1, "maze_config");
		layer1.setLayout(new GridLayout(3, 3, 0, 0));
		
		JLabel mazeSizeLabel = new JLabel("Maze size");
		layer1.add(mazeSizeLabel);
		
		mazeSizeLabel.setFocusable(false);
		mazeSizeLabel.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		final JSlider mazeSize = new JSlider();
		layer1.add(mazeSize);

		mazeSize.setFocusable(false);
		mazeSize.setPaintLabels(true);
		mazeSize.setPaintTicks(true);
		mazeSize.setSnapToTicks(true);
		mazeSize.setMajorTickSpacing(2);
		mazeSize.setMaximum(21);
		mazeSize.setMinimum(5);
		mazeSize.setMinorTickSpacing(2);
		
		JLabel difficultyLabel = new JLabel("Difficulty");
		difficultyLabel.setFocusable(false);
		difficultyLabel.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		final JComboBox difficulty = new JComboBox();
		

		difficulty.setFocusable(false);
		difficulty.setModel(new DefaultComboBoxModel(new String[] {"Dumb dragons ", "Smart dragons (they sleep)", "Dragons high on caffeine"}));
		difficulty.setFont(new Font("Sakkal Majalla", Font.PLAIN, 28));
		
		/*
		 * Dragons percentage
		 */
		final JSlider dragonPerc = new JSlider();
		dragonPerc.setValue(2);
		dragonPerc.setToolTipText("");
		dragonPerc.setSnapToTicks(true);
		dragonPerc.setMinorTickSpacing(2);
		dragonPerc.setMinimum(2);
		dragonPerc.setMaximum(10);
		dragonPerc.setMajorTickSpacing(2);
		dragonPerc.setFocusable(false);
		
		final JLabel dragonPercLabel = new JLabel("Dragons   " + dragonPerc.getValue() + "%");
		
		dragonPercLabel.setFocusable(false);
		dragonPercLabel.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));

		dragonPerc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				dragonPercLabel.setText("Dragons   " + dragonPerc.getValue() + "%");
			}
		});
		
		layer1.add(difficultyLabel);
		layer1.add(difficulty);
		layer1.add(dragonPercLabel);
		layer1.add(dragonPerc);
		
		/* _______________________________________________________________________________________
		 * 
		 * 										LAYER 2
		 * _______________________________________________________________________________________
		 */
		
		layer2 = new JPanel();
		panel.add(layer2, "keys_config");
		layer2.setLayout(new GridLayout(5, 2, 0, 0));
		
		JLabel upKey = new JLabel("UP");
		upKey.setHorizontalAlignment(SwingConstants.CENTER);
		upKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		JLabel downKey = new JLabel("DOWN");
		downKey.setHorizontalAlignment(SwingConstants.CENTER);
		downKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		upKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		JLabel rightKey = new JLabel("RIGHT");
		rightKey.setHorizontalAlignment(SwingConstants.CENTER);
		rightKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		upKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		JLabel leftKey = new JLabel("LEFT");
		leftKey.setHorizontalAlignment(SwingConstants.CENTER);
		leftKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		upKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		JLabel eagleKey = new JLabel("EAGLE AWAY!");
		eagleKey.setHorizontalAlignment(SwingConstants.CENTER);
		eagleKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		eagleKey.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		final JFormattedTextField upKeyField = new JFormattedTextField();
		upKeyField.setEditable(false);
		upKeyField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				receiveNewKey(0);
				upKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[0]));
			}
		});
		upKeyField.setHorizontalAlignment(SwingConstants.CENTER);
		upKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[0]));

		final JFormattedTextField downKeyField = new JFormattedTextField();
		downKeyField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				receiveNewKey(1);
				downKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[1]));
			}
		});
		downKeyField.setEditable(false);
		downKeyField.setHorizontalAlignment(SwingConstants.CENTER);
		downKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[1]));

		final JFormattedTextField rightKeyField = new JFormattedTextField();
		rightKeyField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				receiveNewKey(2);
				rightKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[2]));
			}
		});
		rightKeyField.setEditable(false);
		rightKeyField.setHorizontalAlignment(SwingConstants.CENTER);
		rightKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[2]));

		final JFormattedTextField leftKeyField = new JFormattedTextField();
		leftKeyField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				receiveNewKey(3);
				leftKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[3]));
			}
		});
		leftKeyField.setEditable(false);
		leftKeyField.setHorizontalAlignment(SwingConstants.CENTER);
		leftKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[3]));
		
		final JFormattedTextField eagleKeyField = new JFormattedTextField();
		eagleKeyField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				receiveNewKey(4);
				eagleKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[4]));
			}
		});
		eagleKeyField.setEditable(false);
		eagleKeyField.setHorizontalAlignment(SwingConstants.CENTER);
		eagleKeyField.setText(KeyEvent.getKeyText(config.getGameKeyCodes()[4]));

		layer2.add(upKey);
		layer2.add(upKeyField);
		
		layer2.add(downKey);
		layer2.add(downKeyField);
		
		layer2.add(rightKey);
		layer2.add(rightKeyField);
		
		layer2.add(leftKey);
		layer2.add(leftKeyField);
		
		layer2.add(eagleKey);
		layer2.add(eagleKeyField);
		
		/* _______________________________________________________________________________________
		 * 
		 * 										END OF LAYERS
		 * _______________________________________________________________________________________
		 */
		
		JMenu mnChanges = new JMenu("Changes");
		mnChanges.setFocusable(false);
		menuBar.add(mnChanges);

		JMenuItem btnConfirm = new JMenuItem("Save");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				config.setDifficulty(difficulty.getSelectedIndex());
				config.setDragonPerc(dragonPerc.getValue());
				config.setMazeSize(mazeSize.getValue());
				
				keyCode = 1;
			}
		});
		mnChanges.add(btnConfirm);
		btnConfirm.setFocusable(false);
		btnConfirm.setFont(new Font("Sakkal Majalla", Font.BOLD, 24));

		JMenuItem btnCancel = new JMenuItem("Discard");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyCode = 2;
			}
		});
		mnChanges.add(btnCancel);


		btnCancel.setFocusable(false);
		btnCancel.setFont(new Font("Sakkal Majalla", Font.BOLD, 24));

		JMenu mnPage = new JMenu("Page 1");
		mnPage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				layer1.setVisible(true);
				layer2.setVisible(false);
			}
		});
		mnPage.setFocusable(false);
		menuBar.add(mnPage);

		JMenu mnPage_1 = new JMenu("Page 2");
		mnPage_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				layer1.setVisible(false);
				layer2.setVisible(true);
			}
		});
		mnPage_1.setFocusable(false);
		menuBar.add(mnPage_1);

		this.setFocusable(true);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
	
	public void receiveNewKey(int index) {
		String key = JOptionPane.showInputDialog("Enter the the new key");
		if(key != null) {
			key.toUpperCase();
			char keyCode = key.charAt(0);
			
			localKeyCode = KeyEvent.getExtendedKeyCodeForChar((int) keyCode);
		
//			System.out.println("localKeyCode: " + localKeyCode);
			
			if(keyNotExistant()) {
				config.setGameKey(index, localKeyCode);
			}
		}
	}

	/**
	 * @return the localKeyCode
	 */
	public int getLocalKeyCode() {
		return localKeyCode;
	}

	/**
	 * @param localKeyCode the localKeyCode to set
	 */
	public void setLocalKeyCode(int localKeyCode) {
		this.localKeyCode = localKeyCode;
	}
	
	public boolean keyNotExistant() {
		for(int i = 0; i < config.getGameKeyCodes().length ; i++) {
			if(localKeyCode == config.getGameKeyCodes()[i]) {
				return false;
			}
		}
		return true;
	}
	
}
