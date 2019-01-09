package application;

import controller.GameController;
import controller.MainController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage mainWindow;
	private MainController mc;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage window) {
		mainWindow = window;
		mainWindow.setWidth(465);
		mainWindow.setHeight(735);
		mainWindow.setResizable(true);
		
		mc = new MainController(this);
		mc.toMenu();
		mainWindow.show();
	}
	
	public Stage getMainWindow() {
		return mainWindow;
	}
}
