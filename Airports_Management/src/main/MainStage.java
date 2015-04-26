package main;

import java.io.IOException;

import fundamentals.Global;
import fundamentals.Util;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;

public class MainStage {
	
	private BorderPane mainpane;
	
	public MainStage() {}
	private double xOffset = 0;
	private double yOffset = 0;
	public static Scene scene;
	public void start(final Stage primarystage) {
		Global.PRIMARY_STAGE = primarystage;
		Global.PRIMARY_STAGE.setResizable(false);
		Global.PRIMARY_STAGE.setTitle("Airport Admin");
		Global.PRIMARY_STAGE.getIcons().add(new Image("file:resources/images/Main.png"));
		
		try {
			FXMLLoader loader = new FXMLLoader(MainStage.class.getResource("view/MainPanel.fxml"));
			mainpane = (BorderPane) loader.load();
			scene = new Scene(mainpane);
			scene.getStylesheets().add("/main/view/JMetroLightTheme.css");
			
			//set scene can drag drop
			mainpane.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY(); } });
	        mainpane.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	primarystage.setX(event.getScreenX() - xOffset);
	            	primarystage.setY(event.getScreenY() - yOffset); } });
			//set stage to transparent
			scene.setFill(Color.TRANSPARENT);
			primarystage.initStyle(StageStyle.TRANSPARENT);
			primarystage.setScene(scene);
			Global.MAIN_PANEL = loader.getController();
			Global.MAIN_PANEL.setLogInHyperlink();
			setGuestLeftPanel();
			

			primarystage.show(); }
		catch (IOException e) {} }
	
	
/*----------------------------------------------------------------------------------------------------------------------*/	
// Dieu chinh cho cum cua Admin 
	
	public void setAdminLeftPanel() {
		try {
			if(Global.LOADER_ADMIN_LEFT_PANEL == null) {
				Global.LOADER_ADMIN_LEFT_PANEL = new FXMLLoader(MainStage.class.getResource("view/AdminLeftPanel.fxml"));
				Global.PANE_ADMIN_LEFT_PANEL = (BorderPane) Global.LOADER_ADMIN_LEFT_PANEL.load(); }
			mainpane.setLeft(Global.PANE_ADMIN_LEFT_PANEL);
			Global.CONTROLLER_ADMIN_LEFT_PANEL = Global.LOADER_ADMIN_LEFT_PANEL.getController(); 
			setAdminRightPanelAccount();
			Global.MAIN_PANEL.setStatus("Administrator"); }
		catch (IOException e) {} }

	public void setAdminRightPanelAccount() {
		try {
			if(Global.LOADER_ADMIN_RIGHT_PANEL_ACCOUNT == null) {
				Global.LOADER_ADMIN_RIGHT_PANEL_ACCOUNT
				= new FXMLLoader(MainStage.class.getResource("view/AdminRightPanelAccount.fxml"));
				Global.PANE_ADMIN_RIGHT_PANEL_ACCOUNT = (BorderPane) Global.LOADER_ADMIN_RIGHT_PANEL_ACCOUNT.load(); }
			mainpane.setRight(Global.PANE_ADMIN_RIGHT_PANEL_ACCOUNT); 
			Global.CONTROLLER_ADMIN_RIGHT_PANEL_ACCOUNT = Global.LOADER_ADMIN_RIGHT_PANEL_ACCOUNT.getController();
			Global.CONTROLLER_ADMIN_LEFT_PANEL.setDefaultButton("Account");
			Global.MAIN_PANEL.setStatus("Admin Account"); }
		catch (IOException e) {} }
	
	public void setAdminRightPanelEdit() {
		Util.ScanInformationFromDatabase("AircraftID","IataAirlines","IataAirports");
		try {
			if(Global.LOADER_ADMIN_RIGHT_PANEL_EDIT == null) {
				Global.LOADER_ADMIN_RIGHT_PANEL_EDIT = new FXMLLoader(MainStage.class.getResource("view/AdminRightPanelEdit.fxml"));
				Global.PANE_ADMIN_RIGHT_PANEL_EDIT = (BorderPane) Global.LOADER_ADMIN_RIGHT_PANEL_EDIT.load(); }
			mainpane.setRight(Global.PANE_ADMIN_RIGHT_PANEL_EDIT); 
			Global.CONTROLLER_ADMIN_RIGHT_PANEL_EDIT = Global.LOADER_ADMIN_RIGHT_PANEL_EDIT.getController();
			Global.CONTROLLER_ADMIN_LEFT_PANEL.setDefaultButton("Edit");
			Global.MAIN_PANEL.setStatus("Admin Edit");  }
		catch (IOException e) {} }

	public void setAdminRightPanelStatistics() {
		Util.ScanInformationFromDatabase("Airports");
		try {
			if(Global.LOADER_ADMIN_RIGHT_PANEL_STATISTICS == null) {
				Global.LOADER_ADMIN_RIGHT_PANEL_STATISTICS
				= new FXMLLoader(MainStage.class.getResource("view/AdminRightPanelStatistics.fxml"));
				Global.PANE_ADMIN_RIGHT_PANEL_STATISTICS
				= (BorderPane) Global.LOADER_ADMIN_RIGHT_PANEL_STATISTICS.load(); } 
				mainpane.setRight(Global.PANE_ADMIN_RIGHT_PANEL_STATISTICS); 
				Global.CONTROLLER_ADMIN_RIGHT_PANEL_STATISTICS = Global.LOADER_ADMIN_RIGHT_PANEL_STATISTICS.getController();
				Global.CONTROLLER_ADMIN_LEFT_PANEL.setDefaultButton("Statistics");
				Global.MAIN_PANEL.setStatus("Admin Statistics"); }
		catch (IOException e) {} }

/*---------------------------------------------------------------------------------------------------------------------------*/
// Dieu chinh cho cum Mod
	
	public void setModLeftPanel() {
		try {
			if(Global.LOADER_MOD_LEFT_PANEL == null) {
				Global.LOADER_MOD_LEFT_PANEL = new FXMLLoader(MainStage.class.getResource("view/ModLeftPanel.fxml"));
				Global.PANE_MOD_LEFT_PANEL = (BorderPane) Global.LOADER_MOD_LEFT_PANEL.load(); }
			mainpane.setLeft(Global.PANE_MOD_LEFT_PANEL);
			Global.CONTROLLER_MOD_LEFT_PANEL = Global.LOADER_MOD_LEFT_PANEL.getController();
			setModRightPanelEditSchedule();
			Global.MAIN_PANEL.setStatus("Moderator"); }
		catch (IOException e) {} }

	public void setModRightPanelEditSchedule() {
		Util.ScanInformationFromDatabase("AircraftID");
		try {
			if(Global.LOADER_MOD_RIGHT_PANEL_EDIT_SCHEDULE == null) {
				Global.LOADER_MOD_RIGHT_PANEL_EDIT_SCHEDULE
				= new FXMLLoader(MainStage.class.getResource("view/ModRightPanelEditSchedule.fxml"));
				Global.PANE_MOD_RIGHT_PANEL_EDIT_SCHEDULE = (BorderPane) Global.LOADER_MOD_RIGHT_PANEL_EDIT_SCHEDULE.load(); }
			mainpane.setRight(Global.PANE_MOD_RIGHT_PANEL_EDIT_SCHEDULE); 
			Global.CONTROLLER_MOD_RIGHT_PANEL_EDIT_SCHEDULE = Global.LOADER_MOD_RIGHT_PANEL_EDIT_SCHEDULE.getController();
			Global.CONTROLLER_MOD_LEFT_PANEL.setDefaultButton("EditSchedule");
			Global.CONTROLLER_MOD_RIGHT_PANEL_EDIT_SCHEDULE.setButtonDisableAll();//note
			Util.modClearData();
			Global.MAIN_PANEL.setStatus("Mod Edit Schedule"); }
		catch (IOException e) {} }
		
	public void setModRightPanelRealFlight() {
		Util.ScanInformationFromDatabase("FlightID");
		try {
			if(Global.LOADER_MOD_RIGHT_PANEL_REAL_FLIGHT == null) {
				Global.LOADER_MOD_RIGHT_PANEL_REAL_FLIGHT
				= new FXMLLoader(MainStage.class.getResource("view/ModRightPanelRealFlight.fxml"));
				Global.PANE_MOD_RIGHT_PANEL_REAL_FLIGHT = (BorderPane) Global.LOADER_MOD_RIGHT_PANEL_REAL_FLIGHT.load(); }
			mainpane.setRight(Global.PANE_MOD_RIGHT_PANEL_REAL_FLIGHT); 
			Global.CONTROLLER_MOD_RIGHT_PANEL_REAL_FLIGHT = Global.LOADER_MOD_RIGHT_PANEL_REAL_FLIGHT.getController();
			Global.CONTROLLER_MOD_RIGHT_PANEL_REAL_FLIGHT.setButtonDisableAll();//note
			Util.modClearData();
			Global.CONTROLLER_MOD_LEFT_PANEL.setDefaultButton("RealFlight");
			Global.MAIN_PANEL.setStatus("Mod Real Flight"); }
		catch (IOException e) {} }
		
	public void setModRightPanelStatistics() {
		try {
			if(Global.LOADER_MOD_RIGHT_PANEL_STATISTICS == null) {
				Global.LOADER_MOD_RIGHT_PANEL_STATISTICS
				= new FXMLLoader(MainStage.class.getResource("view/ModRightPanelStatistics.fxml"));
				Global.PANE_MOD_RIGHT_PANEL_STATISTICS = (BorderPane) Global.LOADER_MOD_RIGHT_PANEL_STATISTICS.load(); }
			mainpane.setRight(Global.PANE_MOD_RIGHT_PANEL_STATISTICS); 
			Global.CONTROLLER_MOD_RIGHT_PANEL_STATISTICS = Global.LOADER_MOD_RIGHT_PANEL_STATISTICS.getController();
			Global.CONTROLLER_MOD_LEFT_PANEL.setDefaultButton("Statistics");
			Global.MAIN_PANEL.setStatus("Mod Statistics"); }
		catch (IOException e) {} }
	
	public void setModRightPanelAccount() {
		try {
			if(Global.LOADER_MOD_RIGHT_PANEL_ACCOUNT == null) {
				Global.LOADER_MOD_RIGHT_PANEL_ACCOUNT
				= new FXMLLoader(MainStage.class.getResource("view/ModRightPanelAccount.fxml"));
				Global.PANE_MOD_RIGHT_PANEL_ACCOUNT = (BorderPane) Global.LOADER_MOD_RIGHT_PANEL_ACCOUNT.load(); }
			mainpane.setRight(Global.PANE_MOD_RIGHT_PANEL_ACCOUNT); 
			Global.CONTROLLER_MOD_RIGHT_PANEL_ACCOUNT = Global.LOADER_MOD_RIGHT_PANEL_ACCOUNT.getController();
			Global.CONTROLLER_MOD_LEFT_PANEL.setDefaultButton("Account");
			Global.MAIN_PANEL.setStatus("Mod Account"); }
		catch (IOException e) {} }
	


/*--------------------------------------------------------------------------*/
// Cum dieu khien cua Guest
	public void setGuestLeftPanel() {
		try {
			if(Global.LOADER_GUEST_LEFT_PANEL == null) {
				Global.LOADER_GUEST_LEFT_PANEL = new FXMLLoader(MainStage.class.getResource("view/GuestLeftPanel.fxml"));
				Global.PANE_GUEST_LEFT_PANEL = (BorderPane) Global.LOADER_GUEST_LEFT_PANEL.load(); }
			mainpane.setLeft(Global.PANE_GUEST_LEFT_PANEL); 
			Global.CONTROLLER_GUEST_LEFT_PANEL = Global.LOADER_GUEST_LEFT_PANEL.getController(); 
			setGuestRightPanelSearchAirport();
			Global.MAIN_PANEL.setStatus("Guest"); }
		catch (IOException e) {} }
	
	public void setGuestRightPanelSearchFlight() {
		Util.ScanInformationFromDatabase("Airlines","Airports");
		try {
			if(Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT == null) {
				Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT
				= new FXMLLoader(MainStage.class.getResource("view/GuestRightPanelSearchFlight.fxml"));
				Global.PANE_GUEST_RIGHT_PANEL_SEARCH_FLIGHT = (BorderPane) Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.load(); }
			mainpane.setRight(Global.PANE_GUEST_RIGHT_PANEL_SEARCH_FLIGHT);
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT = Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.getController();
			Global.CONTROLLER_GUEST_LEFT_PANEL.setDefaultButton("SearchFlight");
			Global.MAIN_PANEL.setStatus("Guest Search Flight"); }
		catch (IOException e) {} }

	public void setGuestRightPanelSearchAirport() {
		Util.ScanInformationFromDatabase("CityCountry");
		try {
			if(Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT == null) {
				Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT
				= new FXMLLoader(MainStage.class.getResource("view/GuestRightPanelSearchAirport.fxml"));
				Global.PANE_GUEST_RIGHT_PANEL_SEARCH_AIRPORT = (BorderPane) Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT.load(); }
			mainpane.setRight(Global.PANE_GUEST_RIGHT_PANEL_SEARCH_AIRPORT);
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT = Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT.getController();
			Global.CONTROLLER_GUEST_LEFT_PANEL.setDefaultButton("SearchAirport");
			Global.MAIN_PANEL.setStatus("Guest Search Airport"); }
		catch (IOException e) {} }

	public void setGuestRightPanelSearchPath() {
		Util.ScanInformationFromDatabase("Airports","Routes");
		try {
			if(Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_PATH == null) {
				Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_PATH
				= new FXMLLoader(MainStage.class.getResource("view/GuestRightPanelSearchPath.fxml"));
				Global.PANE_GUEST_RIGHT_PANEL_SEARCH_PATH = (BorderPane) Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_PATH.load(); }
			mainpane.setRight(Global.PANE_GUEST_RIGHT_PANEL_SEARCH_PATH); 
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH = Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_PATH.getController();
			Global.CONTROLLER_GUEST_LEFT_PANEL.setDefaultButton("SearchPath");
			Global.MAIN_PANEL.setStatus("Guest Search Path"); }
		catch (IOException e) {} }

	public void setGuestRightPanelSearchRoute() {
		Util.ScanInformationFromDatabase("Airports","Routes");
		try {
			if(Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_ROUTE == null) {
				Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_ROUTE
				= new FXMLLoader(MainStage.class.getResource("view/GuestRightPanelSearchRoute.fxml"));
				Global.PANE_GUEST_RIGHT_PANEL_SEARCH_ROUTE = (BorderPane) Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.load(); }
			mainpane.setRight(Global.PANE_GUEST_RIGHT_PANEL_SEARCH_ROUTE);
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE = Global.LOADER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.getController();
			Global.CONTROLLER_GUEST_LEFT_PANEL.setDefaultButton("SearchRoute");
			Global.MAIN_PANEL.setStatus("Guest Search Route"); }
		catch (IOException e) {} }

}
