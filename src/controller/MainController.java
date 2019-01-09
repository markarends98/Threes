package controller;

import java.io.File;

import application.Main;
import javafx.scene.Scene;
import model.SaveFile;
import view.game.Game;
import view.menu.MainMenu;
import view.tile.Tile;

public class MainController {
	private Main app;
	private MainMenu mv;
	private GameController gc;
	
	public MainController(Main main) {
		this.app = main;
		this.mv = new MainMenu(this);
	}

	public Main getApp() {
		return app;
	}
	
	public MainMenu getView(){
		return mv;
	}
	
	public Scene getScene(){
		return mv.getScene();
	}
	
	public void toMenu() {
		mv.hideErrorLabel();
		app.getMainWindow().setScene(getScene());
	}
	
	public void newGame() {
		gc = new GameController(this);
		app.getMainWindow().setScene(gc.getScene());
	}
	
	//handle loading game
	public void loadGame() {
		SaveFile f = new SaveFile();
		
		Tile[][] tiles = f.loadSave();
		if(tiles != null) {
			gc = new GameController(this,tiles);
			app.getMainWindow().setScene(gc.getScene());
		}else {
			mv.showErrorLabel();
		}
	}
}
