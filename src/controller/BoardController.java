package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.BoardModel;
import view.board.Board;

public class BoardController{
	private GameController gc;
	private Board bv;
	private BoardModel bm;
	
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
		System.out.println("up");
	}

	public void moveLeft() {
		System.out.println("left");	
		
		bm.writeToConsole();
		bm.moveLeft();
		bv.refreshBoard();
		bm.writeToConsole();
	}

	public void moveRight() {
		System.out.println("right");
		
		bm.writeToConsole();
		bm.moveRight();
		bv.refreshBoard();
		bm.writeToConsole();
	}

	public void moveDown() {
		System.out.println("down");
	}
}
