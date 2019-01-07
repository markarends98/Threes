package view.board;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.BoardController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import model.TileModel;
import view.tile.Tile;

public class Board extends AnchorPane implements Initializable{
	private BoardController bc;
	@FXML private AnchorPane mainPane;
	@FXML private GridPane boardGrid;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public Board(BoardController bc) {
		this.bc = bc;
		load();
	}
	
	private void load() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Board.fxml"));
	        loader.setController(this);
	        this.getChildren().add( loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Tile[][] tiles = bc.getModel().getTiles();
		
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				boardGrid.add(tiles[y][x], x, y);
			}
		}
	}
	
	public void refreshBoard(){
		boardGrid.getChildren().clear();
		
		Tile[][] tiles = bc.getModel().getTiles();
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				boardGrid.add(tiles[y][x], x, y);
			}
		}
	}
}