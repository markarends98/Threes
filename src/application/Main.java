package application;

import controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage mainWindow;
	private GameController gc;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) {
		mainWindow = window;
		mainWindow.setWidth(465);
		mainWindow.setHeight(785);
		mainWindow.setResizable(true);
		
		gc = new GameController(this);
		
		mainWindow.setScene(gc.getScene());
		mainWindow.show();
	}
	
	public Stage getMainWindow() {
		return mainWindow;
	}
	
	public void newGame() {
		gc = new GameController(this);
		mainWindow.setScene(gc.getScene());
	}
}
