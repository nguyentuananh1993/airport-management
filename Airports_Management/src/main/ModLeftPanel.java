package main;

import fundamentals.Global;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModLeftPanel {
	
	@FXML private Button EditSchedule;
	@FXML private Button RealFlight;
	@FXML private Button Statistics;
	@FXML private Button Account;
	
	@FXML
	private void handleEditSchedule() {
		Global.MAIN_STAGE.setModRightPanelEditSchedule(); 
		setDefaultButton(EditSchedule);}
	
	@FXML
	private void handleRealFlight() {
		Global.MAIN_STAGE.setModRightPanelRealFlight(); 
		setDefaultButton(RealFlight);}
	
	@FXML
	private void handleStatistics(){
		Global.MAIN_STAGE.setModRightPanelStatistics();
		setDefaultButton(Statistics);}

	@FXML
	private void handleAccount(){
		Global.MAIN_STAGE.setModRightPanelAccount();
		setDefaultButton(Account);}	

	
	private void unsetDefaultButton(){
		EditSchedule.setDefaultButton(false);
		RealFlight.setDefaultButton(false);
		Statistics.setDefaultButton(false); 
		Account.setDefaultButton(false);}
	
	private void setDefaultButton(Button newbutton){
		unsetDefaultButton();
		newbutton.setDefaultButton(true); }

	public void setDefaultButton(String s) {
		if(s.equals("Account")) setDefaultButton(Account);
		else if(s.equals("EditSchedule")) setDefaultButton(EditSchedule);
		else if(s.equals("RealFlight")) setDefaultButton(RealFlight);
		else if(s.equals("Statistics")) setDefaultButton(Statistics); }
}
