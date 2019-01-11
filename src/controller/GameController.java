package controller;

import application.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.Direction;
import model.SaveFile;
import model.TileModel;
import view.game.Game;

public class GameController implements EventHandler<KeyEvent>{
	private Game gv;
	private MenuController mc;
	private BoardController bc;
	
	public GameController(MenuController mc,TileModel[][] tiles) {
		this.mc = mc;
		this.bc = new BoardController(tiles);
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
	
	public MenuController getMainController() {
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
			bc.move(Direction.UP);
		}else if(key.equals(KeyCode.LEFT)) {
			bc.move(Direction.LEFT);
		}
		else if(key.equals(KeyCode.RIGHT)) {
			bc.move(Direction.RIGHT);
		}
		else if(key.equals(KeyCode.DOWN)) {
			bc.move(Direction.DOWN);
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
		gv.setNextTile(bc.getNextTile());
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
		        execute -> hideAlertBoxes()));
		timeline.play();
	}
	
	public void hideAlertBoxes(){
		enableControls();
		gv.hideAlertBoxes();
	}
}
