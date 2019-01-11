package controller;

import application.Main;
import javafx.scene.Scene;
import model.SaveFile;
import model.TileModel;
import view.menu.MainMenu;

public class MenuController {
	private Main app;
	private MainMenu mv;
	private GameController gc;
	private AudioController ac;
	
	public MenuController(Main app) {
		this.app = app;
		this.mv = new MainMenu(this);
		this.ac = new AudioController();
	}

	public Main getApp() {
		return app;
	}
	
	public Scene getScene(){
		return mv.getScene();
	}
	
	public void toMenu() {
		ac.stopAudio();
		mv.hideErrorLabel();
		app.getMainWindow().setTitle("");
		app.getMainWindow().setScene(getScene());
	}
	
	public void newGame() {
		newGame(null);
	}
	
	private void newGame(TileModel[][] tiles){
		ac.playAudio();
		gc = new GameController(this,tiles);
		app.getMainWindow().setScene(gc.getScene());
	}
	
	//handle loading game
	public void loadGame() {
		SaveFile f = new SaveFile();
		
		TileModel[][] tiles = f.loadSave();
		if(tiles != null) {
			newGame(tiles);
		}else {
			mv.showErrorLabel();
		}
	}
}
