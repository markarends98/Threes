package view.board;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.BoardController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
			return;
		}
		
		refreshBoard();
	}
	
	public void refreshBoard(){
		boardGrid.getChildren().clear();
		
		TileModel[][] tiles = bc.getTiles();
		for(int y = 0; y < 4;y++) {
			for(int x = 0; x < 4;x++) {
				boardGrid.add(new Tile(tiles[y][x]), x, y);
			}
		}
	}
}
