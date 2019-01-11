package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class SaveFile {
	private File file = new File("resources/input.txt");
	private String delimiter = " ";
	
	/**
	 * save a game
	 * @param tiles
	 * @return boolean
	 */
	public boolean save(TileModel[][] tiles) {
		String sb = "";
		try (PrintStream saveFile = new PrintStream(file)) {
			for(int y = 0; y < BoardModel.SIZE;y++) {
				for(int x = 0; x < BoardModel.SIZE;x++) {
					
					sb = y + delimiter + x + delimiter + tiles[y][x].getValue();
					if(x+1 == BoardModel.SIZE && y+1 == BoardModel.SIZE) {
						saveFile.print(sb);
					}else {
						saveFile.println(sb);
					}
				}
			}
			saveFile.close();
		} catch (IOException e) {
			System.out.print("Error while saving: ");
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * load a saved game
	 * @return tiles or null on read error
	 */
	public TileModel[][] loadSave(){
		TileModel[][] tiles = new TileModel[BoardModel.SIZE][BoardModel.SIZE];
		
		if(file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			    	String[] parts = line.split(delimiter);
					int y = Integer.parseInt(parts[0]);
					int x = Integer.parseInt(parts[1]);
					int val = Integer.parseInt(parts[2]);
					tiles[y][x] = new TileModel(val);
			    }
			} catch (IOException e) {
				System.out.print("Error while loading savefile: ");
				System.out.println(e.getMessage());
				return null;
			}
		}else {
			for(int y = 0; y < 4;y++) {
				for(int x = 0; x < 4;x++) {
					tiles[y][x] = new TileModel(0);
				}
			}
			
			Random r = new Random();
			int x = r.nextInt((3 - 0) + 1) + 0;
			int y = r.nextInt((3 - 0) + 1) + 0;
			int val = r.nextInt((3 - 1) + 1) + 1;
			
			for(int i = 0; i < 7; i++) {
				while(tiles[y][x].getValue() > 0) {
					x = r.nextInt((3 - 0) + 1) + 0;
					y = r.nextInt((3 - 0) + 1) + 0;
				}
				val = r.nextInt((3 - 1) + 1) + 1;
				tiles[y][x] = new TileModel(val);
			}
		}
		return tiles;
	}
}
