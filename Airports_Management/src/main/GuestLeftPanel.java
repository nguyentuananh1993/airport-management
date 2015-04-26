package main;

import fundamentals.Global;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GuestLeftPanel {
	
	@FXML
	private Button SearchFlight;
	@FXML
	private Button SearchAirport;
	@FXML
	private Button SearchRoute;
	@FXML
	private Button SearchPath;
	
	@FXML
	private void handleSearchFlight() {
		Global.MAIN_STAGE.setGuestRightPanelSearchFlight(); 
		setDefaultButton("SearchFlight"); }
	@FXML
	private void handleSearchAirport() {
		Global.MAIN_STAGE.setGuestRightPanelSearchAirport(); 
		setDefaultButton("SearchAirport"); }
	@FXML
	private void handleSearchRoute() {
		Global.MAIN_STAGE.setGuestRightPanelSearchRoute();
		setDefaultButton("SearchRoute"); }
	@FXML
	private void handleSearchPath() {
		Global.MAIN_STAGE.setGuestRightPanelSearchPath();
		setDefaultButton("SearchPath"); }
	
	private void unsetDefaultButton(){
		SearchFlight.setDefaultButton(false);
		SearchAirport.setDefaultButton(false);
		SearchRoute.setDefaultButton(false);
		SearchPath.setDefaultButton(false); }
	
	private void setDefaultButton(Button newbutton){
		unsetDefaultButton();
		newbutton.setDefaultButton(true); }
	
	public void setDefaultButton(String s) {
		if(s.equals("SearchAirport")) setDefaultButton(SearchAirport);
		else if(s.equals("SearchFlight")) setDefaultButton(SearchFlight);
		else if(s.equals("SearchPath")) setDefaultButton(SearchPath);
		else if(s.equals("SearchRoute")) setDefaultButton(SearchRoute);	}
}
