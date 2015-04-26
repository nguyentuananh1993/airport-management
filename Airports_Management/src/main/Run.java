package main;

import fundamentals.Global;
import javafx.application.Application;
import javafx.stage.Stage;

public class Run extends Application {

	@Override
	public void start(Stage primaryStage) {
		Global.MAIN_STAGE = new MainStage();
		Global.MAIN_STAGE.start(primaryStage); }

	public static void main(String[] args) {
		launch(args); }
}
