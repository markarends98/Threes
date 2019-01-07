package view.game;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.GameController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class Game implements Initializable{
	private GameController gc;
	private Scene gv;

	@FXML private AnchorPane headerPane;
	@FXML private AnchorPane boardPane;
	
	@FXML private AnchorPane btnSaveGame;
	@FXML private AnchorPane btnLoadGame;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public Game(GameController gc) {
		this.gc = gc;
		 load();
	}
	
	public Scene getScene() {
		return gv;
	}

	private void setListeners() {
		btnSaveGame.setOnMouseClicked((MouseEvent event) -> {
			System.out.println("save");
			gc.getApp().newGame();
        });
		
		btnLoadGame.setOnMouseClicked((MouseEvent event) -> {
			System.out.println("load");
		});
	}

	private void load() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
	        loader.setController(this);
	        AnchorPane scene = loader.load();
	        gv = new Scene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		boardPane.getChildren().add(gc.getBoardController().getView());
		setListeners();
	}
}
