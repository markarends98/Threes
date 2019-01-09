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
import model.SaveFile;

public class Game implements Initializable{
	private GameController gc;
	private Scene gv;

	@FXML private AnchorPane headerPane;
	@FXML private AnchorPane boardPane;
	@FXML private AnchorPane btnSaveGame;
	@FXML private AnchorPane btnBack;
	@FXML private Label lblTurns;
	@FXML private Label lblDirection;
	
	@FXML private AnchorPane alertGameOver;
	@FXML private Label lblEndScore;
	@FXML private AnchorPane btnPlayAgain;
	@FXML private AnchorPane btnQuit;
	
	@FXML private AnchorPane alertSave;
	@FXML private Label lblSaveTitle;
	@FXML private Label lblSaveMessage;
	
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
		hideAlertBoxes();
		setListeners();
	}
	
	private void setListeners() {
		btnSaveGame.setOnMouseClicked((MouseEvent event) -> {
			gc.saveGame();
        });
		
		btnBack.setOnMouseClicked((MouseEvent event) -> {
			gc.toMenu();
		});
		
		btnPlayAgain.setOnMouseClicked((MouseEvent event) -> {
			gc.newGame();
		});
		
		btnQuit.setOnMouseClicked((MouseEvent event) -> {
			gc.toMenu();
		});
	}
	
	public void setTurn(int currenturn) {
		lblTurns.setText(currenturn +"");
	}
	
	public void setDirection(String direction) {
		lblDirection.setText(direction);
	}

	public void setScore(int score) {
		gc.getApp().getMainWindow().setTitle("Currentscore: " + score);		
	}
	
	public void showGameOver() {
		int score = gc.getBoardController().getScore();
		lblEndScore.setText(score + "");
		gc.disableControls();
		alertGameOver.setVisible(true);
	}
	
	public void showSaveOk() {
		lblSaveTitle.getStyleClass().removeAll();
		lblSaveTitle.getStyleClass().add("lblSaveTitleOk");
		lblSaveTitle.setText("GAME SAVED");
		lblSaveMessage.setText("Game saved successfully");
		alertSave.setVisible(true);
	}
	
	public void showSaveFailed() {
		lblSaveTitle.getStyleClass().removeAll();
		lblSaveTitle.getStyleClass().add("lblSaveTitleFail");
		lblSaveTitle.setText("OOPS!");
		lblSaveMessage.setText("Error while saving game, try again later!");
		alertSave.setVisible(true);
	}

	public void hideAlertBoxes() {
		alertGameOver.setVisible(false);
		alertSave.setVisible(false);
	}
}
