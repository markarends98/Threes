package controller;

import application.Main;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.game.Game;

public class GameController implements EventHandler<KeyEvent>{
	private Main app;
	private Game gv;
	private BoardController bc;
	
	public GameController(Main main) {
		this.app = main;
		this.bc = new BoardController(this);
		this.gv = new Game(this);
		getScene().setOnKeyPressed(this);
	}

	public Main getApp() {
		return app;
	}
	
	public Game getView(){
		return gv;
	}
	
	public Scene getScene(){
		return gv.getScene();
	}
	
	public BoardController getBoardController() {
		return bc;
	}
	
	@Override
	public void handle(KeyEvent event) {
		KeyCode key = event.getCode();
		
		if(key.equals(KeyCode.UP)) {
			bc.moveUp();
		}else if(key.equals(KeyCode.LEFT)) {
			bc.moveLeft();
		}
		else if(key.equals(KeyCode.RIGHT)) {
			bc.moveRight();
		}
		else if(key.equals(KeyCode.DOWN)) {
			bc.moveDown();
		}
	}
}
