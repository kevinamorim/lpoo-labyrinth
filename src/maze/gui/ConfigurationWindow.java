package maze.gui;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.GameConfig;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class ConfigurationWindow extends Window {

	private JPanel jlabel;
	private GameConfig config;
	
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
		
		jlabel = new JPanel();
		jlabel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jlabel);
		jlabel.setLayout(new GridLayout(4, 2, 0, 0));
		

		final JSlider mazeSize = new JSlider();
		final JComboBox difficulty = new JComboBox();
		final JSlider dragonPerc = new JSlider();
		
		JLabel mazeSizeLabel = new JLabel("Maze size");
		JLabel difficultyLabel = new JLabel("Difficulty");
		final JLabel dragonPercLabel = new JLabel("Dragons   " + dragonPerc.getValue() + "%");
		
		JButton btnCancel = new JButton("Cancel");
		JButton btnConfirm = new JButton("Confirm");
		
		mazeSizeLabel.setFocusable(false);
		mazeSizeLabel.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));

		mazeSize.setFocusable(false);
		mazeSize.setPaintLabels(true);
		mazeSize.setPaintTicks(true);
		mazeSize.setSnapToTicks(true);
		mazeSize.setMajorTickSpacing(2);
		mazeSize.setMaximum(21);
		mazeSize.setMinimum(5);
		mazeSize.setMinorTickSpacing(2);	

		difficultyLabel.setFocusable(false);
		difficultyLabel.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));

		difficulty.setFocusable(false);
		difficulty.setModel(new DefaultComboBoxModel(new String[] {"Dumb dragons ", "Smart dragons (they sleep)", "Dragons high on caffeine"}));
		difficulty.setFont(new Font("Sakkal Majalla", Font.PLAIN, 28));
		
		dragonPerc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				dragonPercLabel.setText("Dragons   " + dragonPerc.getValue() + "%");
			}
		});
		dragonPercLabel.setFocusable(false);
		dragonPercLabel.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));

		btnConfirm.setFocusable(false);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				config.setMazeSize(mazeSize.getValue());
				config.setDifficulty(difficulty.getSelectedIndex());
				config.setDragonPerc(dragonPerc.getValue()/100.0);
				
				keyCode = 1;
				
			}
		});
		
		dragonPerc.setValue(2);
		dragonPerc.setToolTipText("");
		dragonPerc.setSnapToTicks(true);
		dragonPerc.setMinorTickSpacing(2);
		dragonPerc.setMinimum(2);
		dragonPerc.setMaximum(50);
		dragonPerc.setMajorTickSpacing(2);
		dragonPerc.setFocusable(false);
		btnConfirm.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		btnCancel.setFocusable(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyCode = 0;
			}
		});
		btnCancel.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		jlabel.add(mazeSizeLabel);
		jlabel.add(mazeSize);
		jlabel.add(difficultyLabel);
		jlabel.add(difficulty);
		jlabel.add(dragonPercLabel);
		jlabel.add(dragonPerc);
		jlabel.add(btnConfirm);
		jlabel.add(btnCancel);

		pack();
		setVisible(true);
		
	}
}
