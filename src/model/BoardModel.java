package model;

import java.util.ArrayList;
import java.util.Random;

import view.tile.Tile;

public class BoardModel {
	private Tile[][] tiles;
	
	public BoardModel(){
		loadNewBoard();
	}
	public BoardModel(String input){}
	
	public void loadNewBoard() {
		tiles = new Tile[4][4];
		
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				tiles[y][x] = new Tile(new TileModel(0,x,y));
			}
		}
		
		spawnBeginTiles();
	}

	
	public Tile[][] getTiles(){
		return this.tiles;
	}

	public void spawnBeginTiles() {
		Random r = new Random();
		int x1 = r.nextInt((3 - 0) + 1) + 0;
		int y1 = r.nextInt((3 - 0) + 1) + 0;
		
		tiles[y1][x1] = new Tile(new TileModel(1,x1,y1));
		
		int x2 = r.nextInt((3 - 0) + 1) + 0;
		int y2 = r.nextInt((3 - 0) + 1) + 0;
		
		while(tiles[y2][x2].getTile().getValue() > 0) {
			x2 = r.nextInt((3 - 0) + 1) + 0;
			y2 = r.nextInt((3 - 0) + 1) + 0;
		}
		
		tiles[y2][x2] = new Tile(new TileModel(2,x2,y2));
	}
	
	public void moveLeft() {
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				if(x > 0) {
					Tile tile = tiles[y][x];
					Tile tileToLeft = tiles[y][(x - 1)];
					
					if(tile.getTile().getValue() > 0) {					
						if(tileToLeft.getTile().getValue() > 0) {
							if(tileToLeft.getTile().getX() == 0) {
								if(canMerge(tile.getTile().getValue(), tileToLeft.getTile().getValue())) {
									int val = tileToLeft.getTile().getValue() + tile.getTile().getValue();
									tiles[y][(x - 1)] = new Tile(new TileModel(val,tileToLeft.getTile().getX(),tileToLeft.getTile().getY()));
									tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
								}
							}
						}else {
							tiles[y][(x - 1)] = new Tile(new TileModel(tile.getTile().getValue(),tileToLeft.getTile().getX(),tileToLeft.getTile().getY()));
							tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
						}
					}
				}
			}
		}
		
		//spawn new tile
	}
	
	public void moveRight() {
		for(int y = 0; y < 4;y++) {
			for(int x = 3; x >= 0;x--) {
				if(x < 3) {
					Tile tile = tiles[y][x];
					Tile tileToLeft = tiles[y][(x + 1)];
					
					if(tile.getTile().getValue() > 0) {					
						if(tileToLeft.getTile().getValue() > 0) {
							if(tileToLeft.getTile().getX() == 3) {
								if(canMerge(tile.getTile().getValue(), tileToLeft.getTile().getValue())) {
									int val = tileToLeft.getTile().getValue() + tile.getTile().getValue();
									tiles[y][(x - 1)] = new Tile(new TileModel(val,tileToLeft.getTile().getX(),tileToLeft.getTile().getY()));
									tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
								}
							}
						}else {
							tiles[y][(x + 1)] = new Tile(new TileModel(tile.getTile().getValue(),tileToLeft.getTile().getX(),tileToLeft.getTile().getY()));
							tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
						}
					}
				}
			}
		}
		
		//spawn new tile
	}
		
	private boolean canMerge(int currValue, int moveToValue) {
		if((currValue == 1 && moveToValue == 2) || (currValue == 2 && moveToValue == 1)) {
			return true;
		}
		else if((currValue > 2 && moveToValue > 2) && (currValue == moveToValue)){
			return true;
		}
		return false;
	}
	
	public void writeToConsole() {
		for(int y = 0; y < 4;y++) {
			System.out.print(y + ":");
			for(int x = 0; x < 4;x++) {
				int val = tiles[y][x].getTile().getValue();
				System.out.print(val + " ");
			}
			System.out.println();
		}
	}
	
	private int getRandomTileValue() {
		Random r = new Random();
		return r.nextInt((3 - 0) + 1) + 0;
	}
}
