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
public class ConfigurationWindow extends JFrame {

	private JPanel contentPane;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConfigurationWindow(final GameConfig config) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(4, 2, 0, 0));
		setContentPane(contentPane);

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
				//config.setDragonPerc(dragonPerc);
				System.out.println(mazeSize.getValue());
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
			}
		});
		btnCancel.setFont(new Font("Sakkal Majalla", Font.BOLD, 40));
		
		contentPane.add(mazeSizeLabel);
		contentPane.add(mazeSize);
		contentPane.add(difficultyLabel);
		contentPane.add(difficulty);
		contentPane.add(dragonPercLabel);
		contentPane.add(dragonPerc);
		contentPane.add(btnConfirm);
		contentPane.add(btnCancel);
		
		this.pack();
		this.setVisible(true);
	}
}
