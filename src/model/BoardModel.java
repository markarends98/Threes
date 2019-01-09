package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

import view.tile.Tile;

public class BoardModel {
	public static final int SIZE = 4;
	private TileModel[][] tiles;
	private int currentTurn = 0;
	private int score = 0;
	private String lastDirection = "";
	private TileModel nextTile;
	
	public BoardModel(){
		this(null);
	}
	
	/**
	 * load an existing situation
	 * @param tiles
	 */
	public BoardModel(TileModel[][] tiles){
		resetValues();
		if(tiles != null) {
			this.tiles = tiles;
		}else {
			loadNewBoard();
		}
		generateNewTile();
	}
	
	/**
	 * create a new board
	 */
	public void loadNewBoard() {		
		tiles = new TileModel[SIZE][4];
		
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				tiles[y][x] = new TileModel(0);
			}
		}
		
		spawnBeginTiles();
	}
	
	public TileModel[][] getTiles(){
		return this.tiles;
	}
	
	/**
	 * reset values to start new game
	 */
	public void resetValues() {
		currentTurn = 0;
		lastDirection = "Make your first move!";
		score = 0;
	}

	/**
	 * spawn first 2 tiles to begin
	 */
	private void spawnBeginTiles() {
		Random r = new Random();
		int x1 = r.nextInt((3 - 0) + 1) + 0;
		int y1 = r.nextInt((3 - 0) + 1) + 0;
		
		tiles[y1][x1] = new TileModel(1);
		 
		int x2 = r.nextInt((3 - 0) + 1) + 0;
		int y2 = r.nextInt((3 - 0) + 1) + 0;
		
		while(tiles[y2][x2].getValue() > 0) {
			x2 = r.nextInt((3 - 0) + 1) + 0;
			y2 = r.nextInt((3 - 0) + 1) + 0;
		}
		
		tiles[y2][x2] = new TileModel(2);
	}
	
	/**
	 * create new tile
	 * @param direction
	 */
	private void spawnNewTile(Direction direction){

		//check for an empty space from the pushed direction
		boolean hasEmptySpace = false;
        int x = 0;
        int y = 0;
        
        for(int i = 0; i < SIZE; i++) {
        	if(direction == Direction.LEFT) {
        		x = SIZE - 1;
        		if(tiles[y][x].getValue() == 0) {
        			hasEmptySpace = true;
        		}
        		y++;
        	}else if(direction == Direction.RIGHT) {
        		x = 0;
        		if(tiles[y][x].getValue() == 0) {
        			hasEmptySpace = true;
        		}
        		y++;
        	}else if(direction == Direction.UP) {
        		y = SIZE - 1;
        		if(tiles[y][x].getValue() == 0) {
        			hasEmptySpace = true;
        		}
        		x++;
        	}else if(direction == Direction.DOWN) {
        		y = 0;
        		if(tiles[y][x].getValue() == 0) {
        			hasEmptySpace = true;
        		}
        		x++;
        	}
        }
        
        if(!hasEmptySpace) {
        	return;
        }
        
        Random random = new Random();

        //iterate until an empty tile if found
        while (true){
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
            switch(direction){
	            case LEFT: x = SIZE - 1; break;
	            case RIGHT: x = 0; break;
	            case UP: y = SIZE - 1; break;
	            case DOWN: y = 0; break;
	            default: break;
            }
           
            if (tiles[y][x].getValue() == 0){
                tiles[y][x] = nextTile;
                return;
            }

        }
    }

    /**
     * move and merge tiles in the direction given
     * @param direction
     */
	public void move(Direction direction) {
		for (int y = 0; y < SIZE; y++){

            //create row or column of tiles depending on the direction
			List<TileModel> tileSet = new ArrayList<TileModel>();
			
            for (int x = 0; x < SIZE; x++){          	
                switch(direction){
	                case LEFT: 
	                	tileSet.add(tiles[y][x]);
	                break;
	                case RIGHT: 
	                	tileSet.add(tiles[y][(SIZE - x - 1)]);
	                	break;
	                case UP: 
	                	tileSet.add(tiles[x][y]); 
	                	break;
	                case DOWN: 
	                	tileSet.add(tiles[(SIZE - x - 1)][y]); 
	                	break;
	                default: 
	                	break;
                }
            }
            
            if (!(isEmptyTile(tileSet))){
            	moveBoard(tileSet);
            }
        }
		
		currentTurn++;
		lastDirection = direction.toString();
		spawnNewTile(direction);
		generateNewTile();
	}
	
	private void generateNewTile() {
		nextTile = new TileModel(getNewTileValue());
	}

	public TileModel getNextTile() {
		return nextTile;
	}

    //get tile value between 1 and 3 
    private int getNewTileValue(){
        return new Random().nextInt((3 - 1) + 1) + 1;
    }
	
	
	/**
	 * check if all remaining tiles in a row or column are empty
	 * @param tileSet
	 * @param startAt, start from this index/tile
	 * @return true if remaining tiles are empty else false
	 */
    private boolean remainingIsZero(List<TileModel> tileSet, int startAt) {
        List<TileModel> remainingTile = new ArrayList<TileModel>();

        for (int i = startAt; i < tileSet.size(); i++){
            remainingTile.add(tileSet.get(i));
        }

        return (isEmptyTile(remainingTile));

    }

    /**
     * move and merge tiles
     * @param tileSet
     * cTile = currentTile
     * nTile = nextTile
     */
    private void moveBoard(List<TileModel> tileSet){
        for (int i = 0; i < tileSet.size() - 1; i++){
            if (remainingIsZero(tileSet, i)){
                return;
            }

        	TileModel cTile = tileSet.get(i);
        	TileModel nTile = tileSet.get(i + 1);

        	if(cTile.getValue() == 0) {
        		cTile.setValue(nTile.getValue());
        		nTile.clear();
        	}else {
        		if(cTile.canMerge(nTile)) {
        			cTile.merge(nTile);
        			score += cTile.getValue();
        			nTile.clear();
        		}
        	}
        }
    }
    
    /**
     * check if row or column has empty values
     * @param tileSet
     * @return true on empty else false
     */
	private boolean isEmptyTile(List<TileModel> tileSet) {
		for (TileModel tile: tileSet){
		    if (tile.getValue() != 0){
		        return false;
		    }
		}
		return true;
	}
	
	/**
	 * check if no more moves can be made
	 * @return boolean
	 */
	public boolean isGameOver() {
		if(hasEmptyTile()) {
			return false;
		}
		return !(hasMergeableTilesLeft());
	}
	
	/**
	 * check if board has empty tiles
	 * @return boolean
	 */
	private boolean hasEmptyTile() {
		for(int y = 0; y < SIZE;y++) {
			for(int x = 0; x < SIZE;x++) {
			   if (tiles[y][x].getValue() == 0){
                    return true;
                }
			}
		}
		return false;
	}
	
	/**
	 * check if board has tiles left that can be merged
	 * @return true if there are tiles that can be merged else false
	 */
	private boolean hasMergeableTilesLeft() {
		for(int y = 0; y < SIZE;y++) {
			for(int x = 0; x < SIZE;x++) {
				if (x < SIZE - 1){
					if(tiles[y][x].canMerge(tiles[y][(x + 1)])) {
						return true;
					}
                }

                if (y < SIZE - 1){
					if(tiles[y][x].canMerge(tiles[(y + 1)][x])) {
						return true;
					}
                }
			}
		}
		return false;
	}

	public int getCurrenturn() {
		return currentTurn;
	}

	public int getScore() {
		return score;
	}
	
	public String getDirection() {
		return lastDirection;
	}
}
