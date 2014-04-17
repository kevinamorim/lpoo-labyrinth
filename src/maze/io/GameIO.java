package maze.io;

import maze.logic.Dragon;
import maze.logic.GameLogic;

import java.io.*;

public class GameIO {

	public GameIO() {

	}

	public int save(GameLogic game, String fileName) {
		ObjectOutputStream os = null;
		try {
			
			if(!fileName.contains(".sav")) {
				fileName += ".sav";
			}
			os = new ObjectOutputStream(new FileOutputStream(fileName));
			
			os.writeObject(game);
		}
		catch(IOException e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
					return -2;
				}
			}
		}
		
		return 0;
	}
	
	public int load(GameLogic game, String fileName) {
		ObjectInputStream in = null;
		
		GameLogic temp = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(fileName)); 
			temp = (GameLogic) in.readObject();
		}
		catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		catch(ClassNotFoundException e ) {
			e.printStackTrace();
			return -1;
		}
		finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					return -2;
				}
			}
		}
		
		game.setBoard(temp.getBoard());
		game.setConfig(temp.getConfig());
		game.setDragons(temp.getDragons());
		game.setEagle(temp.getEagle());
		game.setHero(temp.getHero());
		game.setMaze(temp.getMaze());
		game.setSword(temp.getSword());
		game.setTasks(temp.getTasks());
		game.setValid(temp.isValid());
		
		game.getConfigWindow().setConfig(game.getConfig());
		
		return 0;
	}
}
