package controller;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import model.SaveFile;
import view.board.Board;

public class BoardController{
	private GameController gc;
	private Board bv;
	private BoardModel bm;
	
	public BoardController(GameController gc,SaveFile input) {
		this.gc = gc;
		this.bm = new BoardModel(input);
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
	
	public BoardModel getModel(){
		return bm;
	}

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
}
