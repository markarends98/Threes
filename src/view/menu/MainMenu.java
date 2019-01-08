package view.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.TileModel;

public class MainMenu implements Initializable{
	private MainController mc;
	private Scene mv;
	
	@FXML private AnchorPane btnNewGame;
	@FXML private AnchorPane btnLoadGame;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public MainMenu(MainController mc) {
		this.mc= mc;
		load();
	}

	private void load() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
	        loader.setController(this);
	        AnchorPane scene = loader.load();
	        mv = new Scene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		setListeners();
	}

	private void setListeners() {
		btnNewGame.setOnMouseClicked((MouseEvent event) -> {
			mc.newGame();
        });
		
		btnLoadGame.setOnMouseClicked((MouseEvent event) -> {
			mc.loadGame();
		});		
	}

	public Scene getScene() {
		return mv;
	}
}
