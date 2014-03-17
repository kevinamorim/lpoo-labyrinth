package maze.gui;

import java.awt.*;
import javax.swing.*;

import maze.logic.GameLogic;

public class GameWindow {
	
	private final JFrame frame;
	private JLabel label;
	
	public GameWindow(GameLogic gameLogic) {
		frame = new JFrame("Game Status");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(600, 600));
		
		Container maze = frame.getContentPane();
		maze.setLayout(new GridLayout(gameLogic.getMaze().getSize(), 
				gameLogic.getMaze().getSize()));
		
		for(int i = 0; i < gameLogic.getMaze().getSize(); i++) {
			for(int j = 0; j < gameLogic.getMaze().getSize(); j++) {
				JLabel tile = new JLabel("" + gameLogic.getMaze().getTiles()[i][j]);
				maze.add(tile);
			}
		}
		
		frame.pack();
		frame.setVisible(true);
	
	}

}
