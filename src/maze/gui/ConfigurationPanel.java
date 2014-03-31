package maze.gui;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import maze.logic.GameConfig;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SpinnerListModel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSlider;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ConfigurationPanel extends JFrame {

	private JPanel contentPane;
	private GameConfig config;

	/**
	 * Create the frame.
	 */
	public ConfigurationPanel(final GameConfig config) {
		setResizable(false);
		
		this.config = config;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 693, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(4, 2, 0, 0));
		setContentPane(contentPane);
		
		JLabel lblMazeSize = new JLabel("Maze size");
		lblMazeSize.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(lblMazeSize);
		
		
		
		List<Integer> sizes = Arrays.asList(5,7,9,11,13,15,17,19,21);
		SpinnerListModel sizesSpin = new SpinnerListModel(sizes);
		
		final JSlider slider = new JSlider();
		slider.setPaintLabels(true);
		slider.setFocusable(false);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setMajorTickSpacing(2);
		slider.setMaximum(21);
		slider.setMinimum(5);
		slider.setMinorTickSpacing(2);
		contentPane.add(slider);
		
		JLabel lblDifficulty = new JLabel("Difficulty");
		lblDifficulty.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(lblDifficulty);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Dumb dragons ", "Smart dragons (they sleep)", "Dragons high on caffeine"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Dragons");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(lblNewLabel);
		
		JSlider slider_1 = new JSlider();
		slider_1.setMaximum((int)(0.05*config.getMazeSize()*config.getMazeSize()));
		slider_1.setMinimum(1);
		slider_1.setMajorTickSpacing(1);
		slider_1.setMinorTickSpacing(1);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		contentPane.add(slider_1);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				config.setMazeSize(slider.getValue());
				System.out.println(slider.getValue());
			}
		});
		btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(btnConfirm);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 40));
		contentPane.add(btnCancel);
		
		this.pack();
		this.setVisible(true);
	}
}
