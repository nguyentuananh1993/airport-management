package main.mod;

import database.Query;
import fundamentals.Global;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ModRightPanelAccount {
	
	@FXML
	private Label Account;
	@FXML
	private TextField oldPassword;
	@FXML
	private TextField newPassword;
	@FXML
	private TextField renewPassword;
	@FXML private Label OldPass;
	@FXML private Label newpass;
	@FXML private Label renewpass;
	@FXML
	private void initialize() {
		Account.setText(Global.USER_NAME); }
	
	@FXML
	private void handleOk() {
		String oldpass;
		String newpass;
		String renewpass;
		oldpass = oldPassword.getText();
		newpass = newPassword.getText();
		renewpass = renewPassword.getText();
		if (oldpass == null || newpass == null || renewpass == null || oldpass.equals("") || newpass.equals("") || renewpass.equals("")) {
			Global.MAIN_PANEL.setStatus("No field is empty"); 
			OldPass.setText("(*)");
			this.newpass.setText("(*)");
			this.renewpass.setText("(*)");
		}
		else {
			if(!newpass.equals(renewpass)) {
				Global.MAIN_PANEL.setStatus("New password not match");
				this.newpass.setText("(*)");
				this.renewpass.setText("(*)");	
			}
			else {
				Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
				if ((q.CheckLogin(Global.USER_NAME, oldpass)) == false ) {
					Global.MAIN_PANEL.setStatus("password is not correcty");
					OldPass.setText("(*)");	
				}
				else {
					q.ModChangePass(Global.USER_NAME, newpass);
				    Global.MAIN_PANEL.setStatus("password is change success");
					OldPass.setText("");
					this.newpass.setText("");
					this.renewpass.setText("");    
				}
				q.Close(); } } 
		handleClearAll();	
	}
	@FXML
	private void handleClearAll() {
		oldPassword.setText("");
		newPassword.setText("");
		renewPassword.setText("");

	}

}
