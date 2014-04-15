package maze.io;

import maze.logic.GameLogic;

import java.io.*;

public class GameIO {

	public GameIO() {

	}

	public int save(GameLogic game, String fileName) {
		ObjectOutputStream os = null;
		try {
			os = new ObjectOutputStream(new FileOutputStream(fileName));
			
			os.writeObject(game);
		}
		catch (Exception e) {
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
		ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(new FileInputStream(fileName)); 
			game = (GameLogic) is.readObject();
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
					return -2;
				}
			}
		}
		
		return 0;
	}
}
