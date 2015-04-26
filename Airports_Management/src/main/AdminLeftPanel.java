package main;

import fundamentals.Global;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminLeftPanel {
	
	@FXML
	private Button Edit;
	@FXML
	private Button Statistics;
	@FXML
	private Button Account;

	@FXML
	private void handleAccount() {
		Global.MAIN_STAGE.setAdminRightPanelAccount();
		setDefaultButton(Account); }
	
	@FXML
	private void handleEdit() {
		Global.MAIN_STAGE.setAdminRightPanelEdit(); 
		setDefaultButton(Edit);}
	
	@FXML
	private void handleStatistics() {
		Global.MAIN_STAGE.setAdminRightPanelStatistics();
		setDefaultButton(Statistics); }
	
	private void unsetDefaultButton(){
		Edit.setDefaultButton(false);
		Statistics.setDefaultButton(false);
		Account.setDefaultButton(false); }
	
	private void setDefaultButton(Button newbutton){
		unsetDefaultButton();
		newbutton.setDefaultButton(true); }
	
	public void setDefaultButton(String s) {
		if(s.equals("Account")) setDefaultButton(Account);
		else if(s.equals("Edit")) setDefaultButton(Edit);
		else if(s.equals("Statistics")) setDefaultButton(Statistics); }
}
