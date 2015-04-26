package main;

import java.io.IOException;

import database.Query;
import fundamentals.Global;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class LogInDialog {
	private Stage dialogstage;
	
	public LogInDialog() {}
	@FXML
	private TextField UserName;
	@FXML
	private TextField Password;
	@FXML
	private CheckBox RememberMe;
	@FXML Label labelUser;
	@FXML Label labelPass;
	public void setDialogStage(Stage dialogstage) {
		this.dialogstage = dialogstage; }
	
	
	@FXML
	private void handleLogin() {
		labelPass.setText("");
		labelUser.setText("");
		if(UserName.getText().equals(Global.ADMIN) && Password.getText().equals(Global.ADMIN_PASSWORD)) {
			Global.USER_NAME = UserName.getText(); 
			Global.MAIN_STAGE.setAdminLeftPanel();
			Global.MAIN_PANEL.setLogOutHyperlink();
			dialogstage.close();}
		else {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			if(q.CheckLogin(UserName.getText(),Password.getText())) {
				Global.USER_NAME = UserName.getText(); 
				Global.MAIN_STAGE.setModLeftPanel();
				Global.MAIN_PANEL.setLogOutHyperlink();
				dialogstage.close(); }
			else { 
				labelPass.setText("(*)");
				labelUser.setText("(*)"); } 
			q.Close(); } }
	
	@FXML
	private void handleCancel() {
		dialogstage.close(); }

	@FXML
	private void handleForgotPassword() {
		FXMLLoader loader = new FXMLLoader(MainPanel.class.getResource("view/ForgotPassword.fxml"));
		BorderPane bp;
		try {
			bp = (BorderPane) loader.load();
			Stage errordialogstage = new Stage();
			errordialogstage.setTitle("Forgot Password???");
			errordialogstage.initModality(Modality.WINDOW_MODAL);
			errordialogstage.getIcons().add(new Image("file:resources/images/ForgotPassword.png"));
			errordialogstage.initOwner(dialogstage);
			Scene scene = new Scene(bp);
			errordialogstage.setScene(scene);
			errordialogstage.showAndWait(); }
		catch (IOException e) {} 
		dialogstage.close(); }
}