package model;

import java.util.ArrayList;
import java.util.Random;

import view.tile.Tile;

public class BoardModel {
	private Tile[][] tiles;
	private int currentTurn = 0;
	private int score = 0;
	private String direction = "";
	private ArrayList<Tile> spawnableTiles;
	
	public BoardModel(){
		loadNewBoard();
	}
	
	public BoardModel(SaveFile input){
		loadNewBoard();
		tiles = input.loadSave(tiles);
	}
	
	//create a new board
	public void loadNewBoard() {
		currentTurn = 0;
		direction = "";
		score = 0;
		
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

	//spawn first 2 tiles to begin
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
		boolean hasMoved = false;
		
		//move and/or merge tiles to the left
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				if(x > 0) {
					Tile tile = tiles[y][x];
					Tile tileToLeft = tiles[y][(x - 1)];
					
					if(tile.getTile().getValue() > 0) {					
						if(tileToLeft.getTile().getValue() > 0) {
							//tile to left is not empty
							if(tileToLeft.getTile().getX() == 0) {
								//tile to left is on the edge
								if(canMerge(tile.getTile().getValue(), tileToLeft.getTile().getValue())) {
									//move and merge tiles
									int val = tileToLeft.getTile().getValue() + tile.getTile().getValue();
									score += val;
									tiles[y][(x - 1)] = new Tile(new TileModel(val,tileToLeft.getTile().getX(),tileToLeft.getTile().getY()));
									tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
									hasMoved = true;
								}
							}
						}else {
							//move tile to empty tile to left
							tiles[y][(x - 1)] = new Tile(new TileModel(tile.getTile().getValue(),tileToLeft.getTile().getX(),tileToLeft.getTile().getY()));
							tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
							hasMoved = true;
						}
					}
				}
			}
		}
		
		//get valid spawnable tiles
		spawnableTiles = new ArrayList<>();
		for(int y = 0; y < 4;y++) {
			Tile t = tiles[y][3];
			if(t.getTile().getValue() == 0) {
				spawnableTiles.add(t);
			}
		}
		
		spawnNewTile();
		
		if(hasMoved) {
			currentTurn++;
			direction = "Left";
		}
		System.out.println(isGameOver());
	}
	
	public void moveRight() {
		boolean hasMoved = false;
		
		//move and/or merge tiles to the right
		for(int y = 0; y < 4;y++) {
			for(int x = 3; x >= 0;x--) {
				if(x < 3) {
					Tile tile = tiles[y][x];
					Tile tileToRight = tiles[y][(x + 1)];
					
					if(tile.getTile().getValue() > 0) {					
						if(tileToRight.getTile().getValue() > 0) {
							//tile to right is not empty
							if(tileToRight.getTile().getX() == 3) {
								//tile to right is on the edge
								if(canMerge(tile.getTile().getValue(), tileToRight.getTile().getValue())) {
									//move and merge tiles
									int val = tileToRight.getTile().getValue() + tile.getTile().getValue();
									score += val;
									tiles[y][(x + 1)] = new Tile(new TileModel(val,tileToRight.getTile().getX(),tileToRight.getTile().getY()));
									tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
									hasMoved = true;
								}
							}
						}else {
							//move tile to empty tile to right
							tiles[y][(x + 1)] = new Tile(new TileModel(tile.getTile().getValue(),tileToRight.getTile().getX(),tileToRight.getTile().getY()));
							tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
							hasMoved = true;
						}
					}
				}
			}
		}
		
		//get valid spawnable tiles
		spawnableTiles = new ArrayList<>();
		for(int y = 0; y < 4;y++) {
			Tile t = tiles[y][0];
			if(t.getTile().getValue() == 0) {
				spawnableTiles.add(t);
			}
		}
		
		spawnNewTile();
		
		if(hasMoved) {
			currentTurn++;
			direction = "Right";
		}
		System.out.println(isGameOver());
	}
		
	public void moveUp() {
		boolean hasMoved = false;
		
		//move and/or merge tiles up
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				if(y > 0) {
					Tile tile = tiles[y][x];
					Tile tileAbove = tiles[(y - 1)][x];
					
					if(tile.getTile().getValue() > 0) {		
						if(tileAbove.getTile().getValue() > 0) {
							//tile above is not empty
							if(tileAbove.getTile().getY() == 0) {
								//tile above is on the edge
								if(canMerge(tile.getTile().getValue(), tileAbove.getTile().getValue())) {
									//move and merge tiles
									int val = tileAbove.getTile().getValue() + tile.getTile().getValue();
									score += val;
									tiles[(y - 1)][x] = new Tile(new TileModel(val,tileAbove.getTile().getX(),tileAbove.getTile().getY()));
									tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
									hasMoved = true;
								}
							}
						}else {
							//move tile to empty tile above
							tiles[(y - 1)][x] = new Tile(new TileModel(tile.getTile().getValue(),tileAbove.getTile().getX(),tileAbove.getTile().getY()));
							tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
							hasMoved = true;
						}
					}
				}
			}
		}
		
		//get valid spawnable tiles
		spawnableTiles = new ArrayList<>();
		for(int x = 0; x < 4;x++) {
			Tile t = tiles[3][x];
			if(t.getTile().getValue() == 0) {
				spawnableTiles.add(t);
			}
		}
		
		spawnNewTile();
		
		if(hasMoved) {
			currentTurn++;
			direction = "Up";
		}
		System.out.println(isGameOver());
	}
	
	public void moveDown() {
		boolean hasMoved = false;
		
		//move and/or merge tiles down
		for(int y = 3; y >= 0;y--) {
			for(int x = 0; x < 4;x++) {
				if(y < 3) {
					Tile tile = tiles[y][x];
					Tile tileBelow = tiles[(y + 1)][x];
					
					if(tile.getTile().getValue() > 0) {					
						if(tileBelow.getTile().getValue() > 0) {
							//tile below is not empty
							if(tileBelow.getTile().getY() == 3) {
								//tile below is on the edge
								if(canMerge(tile.getTile().getValue(), tileBelow.getTile().getValue())) {
									//move and merge tiles
									int val = tileBelow.getTile().getValue() + tile.getTile().getValue();
									score += val;
									tiles[(y + 1)][x] = new Tile(new TileModel(val,tileBelow.getTile().getX(),tileBelow.getTile().getY()));
									tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
									hasMoved = true;
								}
							}
						}else {
							//move tile to empty tile below
							tiles[(y + 1)][x] = new Tile(new TileModel(tile.getTile().getValue(),tileBelow.getTile().getX(),tileBelow.getTile().getY()));
							tiles[y][x] = new Tile(new TileModel(0,tile.getTile().getX(),tile.getTile().getY()));
							hasMoved = true;
						}
					}
				}
			}
		}
		
		//get valid spawnable tiles
		spawnableTiles = new ArrayList<>();
		for(int x = 0; x < 4;x++) {
			Tile t = tiles[0][x];
			if(t.getTile().getValue() == 0) {
				spawnableTiles.add(t);
			}
		}
		
		spawnNewTile();
		
		if(hasMoved) {
			currentTurn++;
			direction = "Up";
		}
		
		System.out.println(isGameOver());
	}
	
	public boolean isGameOver() {
		if(hasEmptyTile()) {
			return false;
		}
		
		return !(hasMergeableTiles());
	}
	
	//check if board has empty tiles
	private boolean hasEmptyTile() {
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				   if (tiles[y][x].getTile().getValue() == 0){
	                    return true;
	                }
			}
		}
		return false;
	}
	
	//check if board has tiles left that can be merged
	private boolean hasMergeableTiles() {
		System.out.println("no empty tiles");
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				if (x < 4 - 1){
					if(tiles[y][x].getTile().getX() == 0 || tiles[y][x].getTile().getX() == 2) {
						if(canMerge(tiles[y][x].getTile().getValue(),tiles[y][(x + 1)].getTile().getValue())) {
							return true;
						}
					}
                }

                if (y < 4 - 1){
                	if(tiles[y][x].getTile().getY() == 0 || tiles[y][x].getTile().getY() == 2) {
						if(canMerge(tiles[y][x].getTile().getValue(),tiles[(y + 1)][x].getTile().getValue())) {
							return true;
						}
                	}
                }
			}
		}
		return false;
	}
	
	//check if tile values can merge
	private boolean canMerge(int value1, int value2) {
		if((value1 == 1 && value2 == 2) || (value1 == 2 && value2 == 1)) {
			return true;
		}
		else if((value1 > 2 && value2 > 2) && (value1 == value2)){
			return true;
		}
		return false;
	}
		
	//spawn new tile with random value
	private void spawnNewTile() {
		if(spawnableTiles.size() > 0) {
			Tile newSpawnedTile = spawnableTiles.get(new Random().nextInt(spawnableTiles.size()));
			int x = newSpawnedTile.getTile().getX();
			int y = newSpawnedTile.getTile().getY();
			Random r = new Random();
			int val =  r.nextInt((3 - 1) + 1) + 1;
			tiles[y][x] = new Tile(new TileModel(val,x,y));
		}
		spawnableTiles.clear();
	}

	public int getCurrenturn() {
		return currentTurn;
	}

	public int getScore() {
		return score;
	}
	
	public String getDirection() {
		return direction;
	}
}
