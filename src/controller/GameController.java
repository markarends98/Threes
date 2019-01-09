package controller;

import java.io.File;

import application.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.SaveFile;
import view.game.Game;
import view.tile.Tile;

public class GameController implements EventHandler<KeyEvent>{
	private Game gv;
	private BoardController bc;
	private MainController mc;
	
	public GameController(MainController mc,Tile[][] tiles) {
		this.mc = mc;
		this.bc = new BoardController(this,tiles);
		this.gv = new Game(this);
		refreshGameStats();
		enableControls();
	}
	
	public GameController(MainController mc) {
		this.mc = mc;
		this.bc = new BoardController(this);
		this.gv = new Game(this);
		refreshGameStats();
		enableControls();
	}

	public Main getApp() {
		return mc.getApp();
	}
	
	public Game getView(){
		return gv;
	}
	
	public Scene getScene(){
		return gv.getScene();
	}
	
	public MainController getMainController() {
		return mc;
	}
	
	public BoardController getBoardController() {
		return bc;
	}
	
	//listener for board movement
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
		
		refreshGameStats();
		
		if(bc.isGameOver()) {
			gv.showGameOver();
		}
	}
	
	public void refreshGameStats(){
		gv.setTurn(bc.getCurrenturn());
		gv.setDirection(bc.getDirection());
		gv.setScore(bc.getScore());
	}
	
	public void toMenu() {
		mc.toMenu();
	}

	public void newGame() {
		mc.newGame();
	}
	
	public void enableControls() {
		getScene().setOnKeyPressed(this);
	}
	
	public void disableControls() {
		getScene().setOnKeyPressed(null);
	}

	//handle saving game
	public void saveGame() {
		disableControls();
		SaveFile sf = new SaveFile();
		boolean save = sf.save(getBoardController().getTiles());
		if(save) {
			gv.showSaveOk();
		}else {
			gv.showSaveFailed();
		}
		
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis(2000),
		        ae -> hideAlertBoxes()));
		timeline.play();
	}
	
	public void hideAlertBoxes(){
		enableControls();
		gv.hideAlertBoxes();
	}
}
