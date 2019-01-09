package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import view.tile.Tile;

public class SaveFile {
	private File file = new File("resources/input.txt");
	private String delimiter = " ";
	
	public boolean save(Tile[][] tiles) {
		Tile cTile;
		String sb = "";
		try (PrintStream saveFile = new PrintStream(file)) {
			for(int y = 0; y < 4;y++) {
				for(int x = 0; x < 4;x++) {
					cTile = tiles[y][x];
					sb = cTile.getTile().getY() + delimiter + cTile.getTile().getX() + delimiter + cTile.getTile().getValue();
					if(x+1 == 4 && y+1 == 4) {
						saveFile.print(sb);
					}else {
						saveFile.println(sb);
					}
				}
			}
			saveFile.close();
		} catch (IOException e) {
			System.out.println("Error while saving");
			return false;
		}
		return true;
	}
	
	public Tile[][] loadSave(){
		Tile[][] tiles = new Tile[4][4];
		if(file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    String line;
			    while ((line = br.readLine()) != null) {
			    	String[] parts = line.split(delimiter);
					int y = Integer.parseInt(parts[0]);
					int x = Integer.parseInt(parts[1]);
					int val = Integer.parseInt(parts[2]);
					tiles[y][x] = new Tile(new TileModel(val,x,y));
			    }
			} catch (IOException e) {
				System.out.println("Error while loading savefile");
				return null;
			}
		}else {
			Random r = new Random();
			int x = r.nextInt((3 - 0) + 1) + 0;
			int y = r.nextInt((3 - 0) + 1) + 0;
			int val = r.nextInt((3 - 1) + 1) + 1;
			
			for(int i = 0; i < 7; i++) {
				while(tiles[y][x].getTile().getValue() > 0) {
					x = r.nextInt((3 - 0) + 1) + 0;
					y = r.nextInt((3 - 0) + 1) + 0;
				}
				val = r.nextInt((3 - 1) + 1) + 1;
				tiles[y][x] = new Tile(new TileModel(val,x,y));
			}
		}
		return tiles;
	}
}
