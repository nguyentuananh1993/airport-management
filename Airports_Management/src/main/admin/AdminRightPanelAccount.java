package main.admin;

import database.Query;
import fundamentals.Global;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdminRightPanelAccount {
	@FXML private TextField textfieldChangeUserPasswordUsername;
	@FXML private TextField textfieldChangeUserPasswordPassword;
	@FXML private TextField textfieldChangeUserPasswordRePassword;
	@FXML private TextField textfieldDeleteUserUsername;
	@FXML private TextField textfieldResetPasswordUsername;
	@FXML private Label labelChangeUserPasswordUsername;
	@FXML private Label labelChangeUserPasswordPassword;
	@FXML private Label LabelChangeUserPasswordRePassword;
	@FXML private Label labelDeleteUserUsername;
	@FXML private Label labelResetPasswordUsername;
	
	
	@FXML private void handleChangeUserPasswordCreate(){
		if(isInputValidChangeUserPassword()){
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminAccountModifierAccount(textfieldChangeUserPasswordUsername.getText(),textfieldChangeUserPasswordPassword.getText());
			q.Close();
			Global.MAIN_PANEL.setStatus("Password of user "+textfieldChangeUserPasswordUsername.getText()+" had been changed!");
			handleChangeUserPasswordClearAll();
		}
	}
	@FXML private void handleChangeUserPasswordClearAll(){
		textfieldChangeUserPasswordPassword.setText("");
		textfieldChangeUserPasswordRePassword.setText("");
		textfieldChangeUserPasswordUsername.setText("");
		labelChangeUserPasswordPassword.setText("");
		LabelChangeUserPasswordRePassword.setText("");
		labelChangeUserPasswordUsername.setText("");
	}
	@FXML private void handleDeleteUserDelete(){
		if(isInputValidDeleteUser()){
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		q.AdminAccountDeleteAccount(textfieldDeleteUserUsername.getText());
		q.Close();
		Global.MAIN_PANEL.setStatus("User "+textfieldDeleteUserUsername.getText()+" had been deleted!");
		handleDeleteUserClearAll();
		}
	}
	@FXML private void handleDeleteUserClearAll(){
		textfieldDeleteUserUsername.setText("");
		labelDeleteUserUsername.setText("");
	}
	@FXML private void handleResetPasswordOk(){
		if(isInputValidResetPassword()){
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		q.AdminAccountModifierAccount(textfieldResetPasswordUsername.getText(), "123456");
		q.Close();
		Global.MAIN_PANEL.setStatus("Password of "+textfieldResetPasswordUsername.getText()+" had been reset to \"123456\"!");
		handleResetPasswordClearAll();
		}
	}
	@FXML private void handleResetPasswordClearAll(){
		textfieldResetPasswordUsername.setText("");
		labelResetPasswordUsername.setText("");
	}
	private boolean isInputValidChangeUserPassword(){
		boolean tmp=true;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		if(q.AdminAccountFindAccount(textfieldChangeUserPasswordUsername.getText())!=1){
			tmp=false;
			Global.MAIN_PANEL.setStatus("Username is Incorrect.");
			labelChangeUserPasswordUsername.setText("(*)");
		}else labelChangeUserPasswordUsername.setText("");
		if(!textfieldChangeUserPasswordPassword.getText().equals(textfieldChangeUserPasswordRePassword.getText()) || textfieldChangeUserPasswordRePassword.getLength()<6){
			tmp=false;
			labelChangeUserPasswordPassword.setText("(*)");
			LabelChangeUserPasswordRePassword.setText("(*)");
			Global.MAIN_PANEL.setStatus("Password not Match or too short(less than 6 characters).");
		}else{
			labelChangeUserPasswordPassword.setText("");
			LabelChangeUserPasswordRePassword.setText("");
		}
		q.Close();
		return tmp;
	}
	private boolean isInputValidDeleteUser(){
		boolean tmp=true;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		if(q.AdminAccountFindAccount(textfieldDeleteUserUsername.getText())!=1){
			tmp=false;
			Global.MAIN_PANEL.setStatus("Username is Incorrect.");
			labelDeleteUserUsername.setText("(*)");
		}else labelDeleteUserUsername.setText("");
		q.Close();
		return tmp;
	}
	private boolean isInputValidResetPassword(){
		boolean tmp=true;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		if(q.AdminAccountFindAccount(textfieldResetPasswordUsername.getText())!=1){
			tmp=false;
			Global.MAIN_PANEL.setStatus("Username is Incorrect.");
			labelResetPasswordUsername.setText("(*)");
		}else labelResetPasswordUsername.setText("");
		q.Close();
		return tmp;
	}
}