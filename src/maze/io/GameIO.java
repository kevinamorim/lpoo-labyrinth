package maze.io;

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
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream(new FileInputStream(fileName)); 
			game = (GameLogic) in.readObject();
		}
		catch (Exception e) {
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
		
		return 0;
	}
}
