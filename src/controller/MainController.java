package controller;

import java.io.File;

import application.Main;
import javafx.scene.Scene;
import model.SaveFile;
import view.game.Game;
import view.menu.MainMenu;

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
	
	public void newGame() {
		gc = new GameController(app);
		app.getMainWindow().setScene(gc.getScene());
	}
	
	public void loadGame() {
		System.out.println("load");
		SaveFile f = new SaveFile();

		gc = new GameController(app,f);
		app.getMainWindow().setScene(gc.getScene());
	}
}
