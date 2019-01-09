package view.tile;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.BoardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import model.TileModel;

public class Tile extends AnchorPane implements Initializable{
	@FXML private AnchorPane tile;
	@FXML private Label lblValue;
	
	private TileModel mTile;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	public Tile(TileModel mTile) {
		this.mTile = mTile;
		load();
		setColor();
	}

	private void load() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Tile.fxml"));
	        loader.setController(this);
	        this.getChildren().add(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		lblValue.setText(String.valueOf(mTile.getValue()));
	}

	private void setColor() {
		int value = mTile.getValue();
		if(value <= 0) {
			tile.getStyleClass().add("tile0");
		}else if(value == 1) {
			tile.getStyleClass().add("tile1");
		}
		else if(value == 2) {
			tile.getStyleClass().add("tile2");
		}else if(value >= 3) {
			tile.getStyleClass().add("tile3");
		}
	}
}
