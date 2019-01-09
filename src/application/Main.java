package application;

import java.io.IOException;
import java.net.URL;

import controller.MenuController;
import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	private Stage mainWindow;
	private MenuController mc;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) {
		mainWindow = window;
		mainWindow.setWidth(465);
		mainWindow.setHeight(735);
		mainWindow.setResizable(true);
		
		mc = new MenuController(this);
		mc.toMenu();
		mainWindow.show();
	}
	
	public Stage getMainWindow() {
		return mainWindow;
	}
	

}
