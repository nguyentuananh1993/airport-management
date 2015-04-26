package model;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.MainStage;
import fundamentals.Global;

public class DialogStage {
	private double xOffset = 0;
	private double yOffset = 0;
	private final Stage dialogstage = new Stage();
	private Scene scene;
	FXMLLoader loader;
	
	public void setSource(String source) {
		loader = new FXMLLoader(MainStage.class.getResource(source)); }
	
	public void setTitle(String title) {
		dialogstage.setTitle(title); }
	
	public void setIcon(String image) {
		dialogstage.getIcons().add(new Image("file:" + image)); }
	
	public DialogStage(String source, String title, String image) {
		dialogstage.setResizable(false);
		setSource(source);
		BorderPane pane;
		try {
			pane = (BorderPane) loader.load();
			setTitle(title);
			dialogstage.initModality(Modality.WINDOW_MODAL);
			if(image != null) setIcon(image);
			dialogstage.initOwner(Global.PRIMARY_STAGE);
			scene = new Scene(pane);
			scene.getStylesheets().add("/main/view/JMetroLightTheme.css");
			
			pane.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY(); } });
	        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	dialogstage.setX(event.getScreenX() - xOffset);
	            	dialogstage.setY(event.getScreenY() - yOffset); } });
			scene.setFill(Color.TRANSPARENT);
			dialogstage.initStyle(StageStyle.TRANSPARENT);
			dialogstage.setScene(scene); }
		catch(Exception e) {} }
	
	@SuppressWarnings("hiding")
	public <Object> Object getController() {
		return loader.getController(); }
	
	public Stage getDialogStage() {
		return dialogstage;	}
	
	public Scene getScene() {
		return scene; }
	
	public void showAndWait() {
		dialogstage.showAndWait(); }
}
