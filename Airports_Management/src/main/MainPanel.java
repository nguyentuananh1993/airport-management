package main;

import model.DialogStage;
import fundamentals.Global;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainPanel {
	@FXML
	private Button ButtonClose;
	@FXML
	private Button ButtonMin;
	@FXML
	private Button ButtonMax;
	@FXML
	private Hyperlink LogIn;
	@FXML
	private Hyperlink LogOut;
	@FXML
	private Label status = new Label();
	private boolean isGuest;
	@FXML
	private void handleLog() {
		if(isGuest) {
			final DialogStage ds = new DialogStage("view/LogInDialog.fxml", "Log In", "resources/images/LogIn.png");
			ds.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent ke) {
					if (ke.getCode() == KeyCode.ESCAPE) {
						ds.getDialogStage().close(); } } });		
			LogInDialog lid = ds.getController();
			lid.setDialogStage(ds.getDialogStage());
			ds.getDialogStage().showAndWait(); }
		else {
			Global.MAIN_STAGE.setGuestLeftPanel();
			setLogInHyperlink(); } }
	@FXML
	private void handleWinClose(){
		try{
		Global.PRIMARY_STAGE.close();}
		catch(Exception e){} }
	@FXML
	private void handleWinMin(){
		try{
		Global.PRIMARY_STAGE.setIconified(true);}
		catch(Exception e){} }
	@FXML
	private void handleWinMax(){
		final DialogStage ds = new DialogStage("view/AboutDialog.fxml", "About", "resources/images/LogIn.png");
		ds.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.ESCAPE) {
					ds.getDialogStage().close(); } } });
		AboutDialog a = ds.getController();
		a.setDialogStage(ds.getDialogStage());
		ds.getDialogStage().showAndWait(); }
	
	public void setLogOutHyperlink() {
		LogIn.setText(Global.USER_NAME); 
		LogIn.setDisable(true);
		LogOut.setText("Log Out");
		LogOut.setDisable(false);
		isGuest = false; }

	public void setLogInHyperlink() {
		LogIn.setText("Log In");
		LogIn.setDisable(false);
		LogOut.setText("");
		LogOut.setDisable(true);
		isGuest = true; }
	
	public void setStatus(String s) {
		status.setText(s); }
	@FXML
	private void handleDefault(){
		Global.PRIMARY_STAGE.getIcons().add(new Image("file:src/main/view/button/icon-flat.png"));
		if(!MainStage.scene.getStylesheets().contains(getClass().getResource("/main/view/JMetroLightTheme.css").toExternalForm()))
			MainStage.scene.getStylesheets().add(getClass().getResource("/main/view/JMetroLightTheme.css").toExternalForm()); };
	@FXML
	private void handleBlue(){
		Global.PRIMARY_STAGE.getIcons().add(new Image("file:src/main/view/button/icon-flat-blue.png"));
		if(!MainStage.scene.getStylesheets().contains(getClass().getResource("/main/view/blue/JMetroLightTheme.css").toExternalForm()))
			MainStage.scene.getStylesheets().add(getClass().getResource("/main/view/blue/JMetroLightTheme.css").toExternalForm()); };
	@FXML
	private void handleRed(){
		Global.PRIMARY_STAGE.getIcons().add(new Image("file:src/main/view/button/icon-flat-red.png"));
		if(!MainStage.scene.getStylesheets().contains(getClass().getResource("/main/view/red/JMetroLightTheme.css").toExternalForm()))
			MainStage.scene.getStylesheets().add(getClass().getResource("/main/view/red/JMetroLightTheme.css").toExternalForm()); };
	@FXML
	private void handleGreen(){
		Global.PRIMARY_STAGE.getIcons().add(new Image("file:src/main/view/button/icon-flat-green.png"));
		if(!MainStage.scene.getStylesheets().contains(getClass().getResource("/main/view/green/JMetroLightTheme.css").toExternalForm()))
		MainStage.scene.getStylesheets().add(getClass().getResource("/main/view/green/JMetroLightTheme.css").toExternalForm()); };
}
