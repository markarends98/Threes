package controller;

import model.BoardModel;
import model.Direction;
import model.TileModel;
import view.board.Board;

public class BoardController{
	private Board bv;
	private BoardModel bm;
	
	public BoardController() {
		this(null);
	}
	
	public BoardController(TileModel[][] tiles) {
		this.bm = new BoardModel(tiles);
		this.bv = new Board(this);
	}

	public Board getView(){
		return bv;
	}
	
	public void move(Direction direction) {
		bm.move(direction);
		bv.refreshBoard();
	}

	public boolean isGameOver() {
		return bm.isGameOver();
	}

	public TileModel[][] getTiles() {
		return bm.getTiles();
	}

	public int getScore() {
		return bm.getScore();
	}

	public int getCurrenturn() {
		return bm.getCurrenturn();
	}

	public String getDirection() {
		return bm.getDirection();
	}

	public TileModel getNextTile() {
		return bm.getNextTile();
	}
}
