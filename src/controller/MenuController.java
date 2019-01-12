package controller;

import application.Main;
import javafx.scene.Scene;
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
	
	public void newGame(){
		gc = new GameController(this);
		ac.playAudio();
		app.getMainWindow().setScene(gc.getScene());
	}
	
	//handle loading game
	public void loadGame() {
		gc = new GameController(this);
		boolean load = gc.getBoardController().loadGame();
		if(load) {
			ac.playAudio();
			app.getMainWindow().setScene(gc.getScene());
		}else {
			mv.showErrorLabel();
		}
	}
}
