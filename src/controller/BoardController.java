package controller;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import model.SaveFile;
import view.board.Board;
import view.tile.Tile;

public class BoardController{
	private GameController gc;
	private Board bv;
	private BoardModel bm;
	
	public BoardController(GameController gc,Tile[][] tiles) {
		this.gc = gc;
		this.bm = new BoardModel(tiles);
		this.bv = new Board(this);
	}
	
	public BoardController(GameController gc) {
		this.gc = gc;
		this.bm = new BoardModel();
		this.bv = new Board(this);
	}

	public Board getView(){
		return bv;
	}

	
	//board movement
	public void moveUp() {
		bm.moveUp();
		bv.refreshBoard();
	}

	public void moveLeft() {
		bm.moveLeft();
		bv.refreshBoard();
	}

	public void moveRight() {
		bm.moveRight();
		bv.refreshBoard();
	}

	public void moveDown() {
		bm.moveDown();
		bv.refreshBoard();
	}

	public boolean isGameOver() {
		return bm.isGameOver();
	}

	public Tile[][] getTiles() {
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
}
