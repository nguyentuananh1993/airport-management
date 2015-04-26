package main.admin;

import java.io.IOException;
import java.util.Date;

import database.Query;
import fundamentals.Global;
import fundamentals.Util;
import main.MainPanel;
import main.admin.editpanel.EditPanelAircraftInfoController;
import main.admin.editpanel.EditPanelAirlineController;
import main.admin.editpanel.EditPanelAirportController;
import main.admin.editpanel.EditPanelRouteController;
import main.admin.editpanel.EditPanelScheduleController;
import model.AdminAircraftInfo;
import model.AdminAirlineInfo;
import model.AdminAirportInfo;
import model.AdminRouteInfo;
import model.AdminScheduleInfo;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminRightPanelEdit {
	/*************
	 * button
	 * ***********/
	@FXML public static Button buttonAirlineAdd;
	@FXML public static Button buttonAirlineDelete;
	@FXML public static Button buttonAirlineEdit;
	@FXML public static Button buttonAirportAdd;
	@FXML public static Button buttonAirportDelete;
	@FXML public static Button buttonAirportEdit;
	@FXML public static Button buttonRouteAdd;
	@FXML public static Button buttonRouteDelete;
	@FXML public static Button buttonRouteEdit;
	@FXML public static Button buttonScheduleAdd;
	@FXML public static Button buttonScheduleDelete;
	@FXML public static Button buttonScheduleEdit;
	@FXML public static Button buttonAircraftInfoAdd;
	@FXML public static Button buttonAircraftInfoDelete;
	@FXML public static Button buttonAircraftInfoEdit;
	/*************
	 * TableView
	 * ***********/
	@FXML
	private TableView<AdminAirlineInfo> AirlineTable;
	@FXML
	private TableColumn<AdminAirlineInfo, Integer> tableColAirlineId;
	@FXML
	private TableColumn<AdminAirlineInfo, String> tableColAirlineName;
	@FXML
	private TableColumn<AdminAirlineInfo, String> tableColAirlineIata;
	@FXML
	private TableColumn<AdminAirlineInfo, String> tableColAirlineCountry;
	@FXML
	private TableColumn<AdminAirlineInfo, Boolean> tableColAirlineActive;

	@FXML
	private TableView<AdminAirportInfo> AirportTable;
	@FXML
	private TableColumn<AdminAirportInfo, Integer> tableColAirportId;
	@FXML
	private TableColumn<AdminAirportInfo, String> tableColAirportName;
	@FXML
	private TableColumn<AdminAirportInfo, String> tableColAirportCity;
	@FXML
	private TableColumn<AdminAirportInfo, String> tableColAirportCountry;
	@FXML
	private TableColumn<AdminAirportInfo, String> tableColAirportIata;
	@FXML
	private TableColumn<AdminAirportInfo, Double> tableColAirportLatitude;
	@FXML
	private TableColumn<AdminAirportInfo, Double> tableColAirportLongitude;
	@FXML
	private TableColumn<AdminAirportInfo, Double> tableColAirportTimezone;
	@FXML
	private TableColumn<AdminAirportInfo, String> tableColAirportDestination;

	@FXML
	private TableView<AdminRouteInfo> RouteTable;
	@FXML
	private TableColumn<AdminRouteInfo, Integer> tableColRouteId;
	@FXML
	private TableColumn<AdminRouteInfo, Integer> tableColRouteAirlineId;
	@FXML
	private TableColumn<AdminRouteInfo, String> tableColRouteAirlineIata;
	@FXML
	private TableColumn<AdminRouteInfo, Integer> tableColRouteDepAirportId;
	@FXML
	private TableColumn<AdminRouteInfo, String> tableColRouteDepAirportIata;
	@FXML
	private TableColumn<AdminRouteInfo, Integer> tableColRouteArrAirportId;
	@FXML
	private TableColumn<AdminRouteInfo, String> tableColRouteArrAirportIata;

	@FXML
	private TableView<AdminScheduleInfo> ScheduleTable;
	@FXML
	private TableColumn<AdminScheduleInfo, String> tableColScheduleFlightId;
	@FXML
	private TableColumn<AdminScheduleInfo, String> tableColScheduleDepAirportIata;
	@FXML
	private TableColumn<AdminScheduleInfo, String> tableColScheduleArrAirportIata;
	@FXML
	private TableColumn<AdminScheduleInfo, Date> tableColScheduleDepTime;
	@FXML
	private TableColumn<AdminScheduleInfo, Date> tableColScheduleArrTime;
	@FXML
	private TableColumn<AdminScheduleInfo, String> tableColScheduleEquipId;
	@FXML
	private TableColumn<AdminScheduleInfo, Integer> tableColScheduleRepeat;
	@FXML
	private TableColumn<AdminScheduleInfo, Double> tableColScheduleDuration;

	@FXML
	private TableView<AdminAircraftInfo> AircraftInfoTable;
	@FXML
	private TableColumn<AdminAircraftInfo, String> tableColAircraftInfoId;
	@FXML
	private TableColumn<AdminAircraftInfo, String> tableColAircraftInfoName;
	@FXML
	private TableColumn<AdminAircraftInfo, Integer> tableColAircraftInfoNumberOfSeat;


	/********************
	 * Khoi tao editable
	 *******************/
	@FXML
	private void initialize() {
		Util.setDisableButtonAdminEdit();
	}

	/********************************************
	 * 
	 * button to add delete and undo the action
	 * 
	 * ******************************************/
	@FXML
	private void handleAirlineDelete() {
		int selectedIndex = AirlineTable.getSelectionModel().getSelectedIndex();
		AdminAirlineInfo selectedAirline = AirlineTable.getSelectionModel().getSelectedItem();
		try{
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminDeleteAirline(selectedAirline.getId());
			q.Close();
			Global.EDITING_AIRLINES=true;
			Global.EDITING_IATA_AIRLINES=true;
			AirlineTable.getItems().remove(selectedIndex);
			AirlineTable.getSelectionModel().selectedIndexProperty();
			Global.MAIN_PANEL.setStatus("Airline had been deleted.");
		}catch(Exception e){Global.MAIN_PANEL.setStatus("Airline can't delete.");}
	};
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML
	private void handleAirlineAdd() {
		int selectedIndex = AirlineTable.getSelectionModel().getSelectedIndex();
		AdminAirlineInfo airline = new AdminAirlineInfo();
		Query j = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		airline.setId(j.AdminAirlineCount()+1);
		j.Close();
		boolean okClicked = showAirlineEditPanel(false, airline);
		if (okClicked) {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminInsertAirline(q.AdminAirlineCount()+1,airline);
			q.InsertAccount(airline.getId(),airline.getName());
			q.Close();
			Global.EDITING_AIRLINES=true;
			Global.EDITING_IATA_AIRLINES=true;
			Global.LIST_ADMIN_AIRLINE.add(airline);
			refreshAirlineTable(selectedIndex);
			Global.MAIN_PANEL.setStatus("Airline had been added.");
		}
	};


	@FXML
	private void handleAirlineEdit() {
		int selectedIndex = AirlineTable.getSelectionModel().getSelectedIndex();
		AdminAirlineInfo selectedAirline = AirlineTable.getSelectionModel().getSelectedItem();
		if(selectedAirline != null){
			boolean isOkclick = showAirlineEditPanel(true,selectedAirline);
			if(isOkclick){
				Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
				q.AdminUpdateAirline(selectedAirline, selectedAirline.getId());
				q.Close();
				Global.EDITING_AIRLINES=true;
				Global.EDITING_IATA_AIRLINES=true;
				
				refreshAirlineTable(selectedIndex);
				Global.MAIN_PANEL.setStatus("Airline had been modified.");
			}
		}
	};

	@FXML
	private void handleAirportDelete() {
		int selectedIndex = AirportTable.getSelectionModel().getSelectedIndex();
		AdminAirportInfo selectedAirport = AirportTable.getSelectionModel().getSelectedItem();
		try{
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminDeleteAirport(selectedAirport.getId());
			q.Close();
			Global.EDITING_AIRPORTS=true;
			Global.EDITING_IATA_AIRPORTS=true;
			Global.EDITING_CITY_COUNTRY=true;
			Global.EDITING_ROUTES=true;
			AirportTable.getItems().remove(selectedIndex);
			AirportTable.getSelectionModel().selectedIndexProperty();
			Global.MAIN_PANEL.setStatus("Airport had been deleted.");
		}catch(Exception e){Global.MAIN_PANEL.setStatus("Airport can't delete.");}
	};

	@FXML
	private void handleAirportAdd() {
		AdminAirportInfo airport = new AdminAirportInfo();
		int selectedIndex = AirportTable.getSelectionModel().getSelectedIndex();
		Query j = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		airport.setId(j.AdminAirportCount()+1);
		j.Close();
		boolean okClicked = showAirportEditPanel(false, airport);
		if (okClicked) {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminInsertAirport(airport);
			q.Close();
			Global.EDITING_AIRPORTS=true;
			Global.EDITING_IATA_AIRPORTS=true;
			Global.EDITING_CITY_COUNTRY=true;
			Global.EDITING_ROUTES=true;
			Global.LIST_ADMIN_AIRPORT.add(airport);
			refreshAirportTable(selectedIndex);
			Global.MAIN_PANEL.setStatus("Airport had been added.");
		}
	};

	@FXML
	private void handleAirportEdit() {
		AdminAirportInfo selectedAirport = AirportTable.getSelectionModel().getSelectedItem();
		int selectedIndex = AirportTable.getSelectionModel().getSelectedIndex();
		if(selectedAirport != null){
			boolean isOkclick = showAirportEditPanel(true,selectedAirport);
			if(isOkclick){
				Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
				q.AdminUpdateAirport(selectedAirport, selectedAirport.getId());
				q.Close();
				Global.EDITING_AIRPORTS=true;
				Global.EDITING_IATA_AIRPORTS=true;
				Global.EDITING_CITY_COUNTRY=true;
				Global.EDITING_ROUTES=true;
				refreshAirportTable(selectedIndex);
				Global.MAIN_PANEL.setStatus("Airport had been edited.");
			}
		}
	};

	@FXML
	private void handleRouteDelete() {
		int selectedIndex = RouteTable.getSelectionModel().getSelectedIndex();
		AdminRouteInfo selectedRoute = RouteTable.getSelectionModel().getSelectedItem();
		try{
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminDeleteRoute(selectedRoute.getId());
			q.Close();
			Global.EDITING_ROUTES=true;
			RouteTable.getItems().remove(selectedIndex);
			RouteTable.getSelectionModel().selectedIndexProperty();
			Global.MAIN_PANEL.setStatus("Airport had been deleted.");
		}catch(Exception e){Global.MAIN_PANEL.setStatus("Airport can't delete.");}
	};

	@FXML
	private void handleRouteAdd() {
		AdminRouteInfo route = new AdminRouteInfo();
		int selectedIndex = RouteTable.getSelectionModel().getSelectedIndex();
		Query j = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		route.setId(j.AdminRouteCount()+1);
		j.Close();
		boolean okClicked = showRouteEditPanel(false, route);
		if (okClicked) {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminInsertRoute(route);
			q.Close();
			Global.EDITING_ROUTES=true;
			Global.LIST_ADMIN_ROUTE.add(route);
			refreshRouteTable(selectedIndex);
			Global.MAIN_PANEL.setStatus("Route had been added.");
		}
	};

	@FXML
	private void handleRouteEdit() {
		AdminRouteInfo selectedRoute =RouteTable.getSelectionModel().getSelectedItem();
		int selectedIndex = RouteTable.getSelectionModel().getSelectedIndex();
		if(selectedRoute != null){
			boolean isOkclick = showRouteEditPanel(true,selectedRoute);
			if(isOkclick){
				Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
				q.AdminUpdateRoute(selectedRoute, selectedRoute.getId());
				q.Close();
				Global.EDITING_ROUTES=true;
				refreshRouteTable(selectedIndex);
				Global.MAIN_PANEL.setStatus("Route had been edited.");
			}
		}
	};

	@FXML
	private void handleScheduleDelete() {
		int selectedIndex = ScheduleTable.getSelectionModel().getSelectedIndex();
		AdminScheduleInfo selectedSchedule = ScheduleTable.getSelectionModel().getSelectedItem();
		try{
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminDeleteSchedule(selectedSchedule.getFlight_id());
			q.Close();
			Global.EDITING_FLIGHT_ID=true;
			ScheduleTable.getItems().remove(selectedIndex);
			ScheduleTable.getSelectionModel().selectedIndexProperty();
			Global.MAIN_PANEL.setStatus("Schedule had been deleted.");
		}catch(Exception e){Global.MAIN_PANEL.setStatus("Schedule can't delete.");}
	};

	@FXML
	private void handleScheduleAdd() {
		AdminScheduleInfo schedule = new AdminScheduleInfo();
		int selectedIndex = ScheduleTable.getSelectionModel().getSelectedIndex();
		boolean okClicked = showScheduleEditPanel(false, schedule);
		if (okClicked) {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminInsertSchedule(schedule);
			q.Close();
			Global.EDITING_FLIGHT_ID=true;
			Global.LIST_ADMIN_SCHEDULE.add(schedule);
			refreshScheduleTable(selectedIndex);
			
			Global.MAIN_PANEL.setStatus("Schedule had been addded.");
		}
	};

	@FXML
	private void handleScheduleEdit() {
		AdminScheduleInfo selectedSchedule = ScheduleTable.getSelectionModel().getSelectedItem();
		int selectedIndex = ScheduleTable.getSelectionModel().getSelectedIndex();
		if(selectedSchedule != null){
			boolean isOkclick = showScheduleEditPanel(true,selectedSchedule);
			if(isOkclick){
				Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
				q.AdminUpdateSchedule(selectedSchedule, selectedSchedule.getDep_airport_iata(),selectedSchedule.getArr_airport_iata());
				q.Close();
				Global.EDITING_FLIGHT_ID=true;
				refreshScheduleTable(selectedIndex);
				Global.MAIN_PANEL.setStatus("Schedule had been edited.");
			}
		}
	};

	@FXML
	private void handleAircraftInfoDelete() {
		int selectedIndex = AircraftInfoTable.getSelectionModel().getSelectedIndex();
		AdminAircraftInfo selectedAircraft = AircraftInfoTable.getSelectionModel().getSelectedItem();
		try{
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminDeleteAircraftInfo(selectedAircraft.getId());
			q.Close();
			Global.EDITING_AIRCRAFT_ID=true;
			AircraftInfoTable.getItems().remove(selectedIndex);
			AircraftInfoTable.getSelectionModel().selectedIndexProperty();
			Global.MAIN_PANEL.setStatus("Aircraft Info had been deleted.");
		}catch(Exception e){}
	};

	@FXML
	private void handleAircraftInfoAdd() {
		AdminAircraftInfo aircraft = new AdminAircraftInfo();
		int selectedIndex = AircraftInfoTable.getSelectionModel().getSelectedIndex();
		boolean okClicked = showAircraftEditPanel(false, aircraft);
		if (okClicked) {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminInsertAircraft(aircraft);
			q.Close();
			Global.EDITING_AIRCRAFT_ID=true;
			Global.LIST_ADMIN_AIRCRAFT.add(aircraft);
			refreshAircraftTable(selectedIndex);
			Global.MAIN_PANEL.setStatus("Aircraft Info had been added.");
		}
	};

	@FXML
	private void handleAircraftInfoEdit() {
		AdminAircraftInfo selectedAircraft = AircraftInfoTable.getSelectionModel().getSelectedItem();
		int selectedIndex = AircraftInfoTable.getSelectionModel().getSelectedIndex();
		if(selectedAircraft != null){
			boolean isOkclick = showAircraftEditPanel(true,selectedAircraft);
			if(isOkclick){
				Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
				q.AdminUpdateAircraftInfo(selectedAircraft, selectedAircraft.getId());
				q.Close();
				Global.EDITING_AIRCRAFT_ID=true;
				refreshAircraftTable(selectedIndex);
				Global.MAIN_PANEL.setStatus("Aircraft Info had been edited.");
			}
		}
	};
	@FXML
	private void handleAirlineLoadDatabase() {
		AirlineTable.setEditable(true);
		tableColAirlineId.setCellValueFactory(new PropertyValueFactory<AdminAirlineInfo, Integer>("id"));
		tableColAirlineName.setCellValueFactory(new PropertyValueFactory<AdminAirlineInfo, String>("name"));
		tableColAirlineIata.setCellValueFactory(new PropertyValueFactory<AdminAirlineInfo, String>("iata"));
		tableColAirlineCountry.setCellValueFactory(new PropertyValueFactory<AdminAirlineInfo, String>("country"));
		tableColAirlineActive.setCellValueFactory(new PropertyValueFactory<AdminAirlineInfo, Boolean>("active"));

		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		Global.LIST_ADMIN_AIRLINE.clear();
		Global.COUNT = 0;
		q.AdminShowAirline();
		AirlineTable.setItems(Global.LIST_ADMIN_AIRLINE);
		AirlineTable.getSelectionModel().selectFirst();
		q.Close(); 
		Util.setDisableButtonAdminEdit();
		buttonAirlineAdd.setDisable(false);
		buttonAirlineDelete.setDisable(false);
		buttonAirlineEdit.setDisable(false);
		Global.MAIN_PANEL.setStatus(Global.COUNT + " airline(s) had been found");

	};
	@FXML
	private void handleAirportLoadDatabase() {
		AirportTable.setEditable(true);
		tableColAirportId.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, Integer>("id"));
		tableColAirportName.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, String>("name"));
		tableColAirportCity.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, String>("city"));
		tableColAirportCountry.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, String>("country"));
		tableColAirportIata.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, String>("iata"));
		tableColAirportLatitude.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, Double>("latitude"));
		tableColAirportLongitude.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, Double> ("longitude"));
		tableColAirportTimezone.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, Double>("timezone"));
		tableColAirportDestination.setCellValueFactory(new PropertyValueFactory<AdminAirportInfo, String>("dst"));
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		Util.adminClearData();
		q.AdminShowAirport();
		AirportTable.setItems(Global.LIST_ADMIN_AIRPORT);
		AirportTable.getSelectionModel().selectFirst();
		q.Close(); 
		Util.setDisableButtonAdminEdit();
		buttonAirportAdd.setDisable(false);
		buttonAirportDelete.setDisable(false);
		buttonAirportEdit.setDisable(false);
		Global.MAIN_PANEL.setStatus(Global.COUNT + " airport(s) had been found");

	};
	@FXML
	private void handleRouteLoadDatabase() {
		RouteTable.setEditable(true);
		tableColRouteId.setCellValueFactory(new PropertyValueFactory<AdminRouteInfo, Integer>("id"));
		tableColRouteAirlineId.setCellValueFactory(new PropertyValueFactory<AdminRouteInfo, Integer>("airline_id"));
		tableColRouteAirlineIata.setCellValueFactory(new PropertyValueFactory<AdminRouteInfo, String>("airline_iata"));
		tableColRouteDepAirportId.setCellValueFactory(new PropertyValueFactory<AdminRouteInfo, Integer>("dep_airport_id"));
		tableColRouteDepAirportIata.setCellValueFactory(new PropertyValueFactory<AdminRouteInfo, String>("dep_airport_iata"));
		tableColRouteArrAirportId.setCellValueFactory(new PropertyValueFactory<AdminRouteInfo, Integer>("arr_airport_id"));
		tableColRouteArrAirportIata.setCellValueFactory(new PropertyValueFactory<AdminRouteInfo, String>("arr_airport_iata"));
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		Util.adminClearData();
		q.AdminShowRoute();
		RouteTable.setItems(Global.LIST_ADMIN_ROUTE);
		RouteTable.getSelectionModel().selectFirst();
		q.Close();
		Util.setDisableButtonAdminEdit();
		buttonRouteAdd.setDisable(false);
		buttonRouteDelete.setDisable(false);
		buttonRouteEdit.setDisable(true);
		Global.MAIN_PANEL.setStatus(Global.COUNT + " route(s) had been found");

	};
	@FXML
	private void handleScheduleLoadDatabase() {
		ScheduleTable.setEditable(true);
		tableColScheduleFlightId.setCellValueFactory(new PropertyValueFactory<AdminScheduleInfo, String>("flight_id"));
		tableColScheduleDepAirportIata.setCellValueFactory(new PropertyValueFactory<AdminScheduleInfo, String>("dep_airport_iata"));
		tableColScheduleArrAirportIata.setCellValueFactory(new PropertyValueFactory<AdminScheduleInfo, String>("arr_airport_iata"));
		tableColScheduleDepTime.setCellValueFactory(new PropertyValueFactory<AdminScheduleInfo, Date>("deptime"));
		tableColScheduleArrTime.setCellValueFactory(new PropertyValueFactory<AdminScheduleInfo, Date>("arrtime"));
		tableColScheduleEquipId.setCellValueFactory(new PropertyValueFactory<AdminScheduleInfo, String>("equip_id"));
		tableColScheduleRepeat.setCellValueFactory(new PropertyValueFactory<AdminScheduleInfo, Integer>("repeat"));
		tableColScheduleDuration.setCellValueFactory(new PropertyValueFactory<AdminScheduleInfo, Double>("duration"));
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		Util.adminClearData();
		q.AdminShowSchedule();
		ScheduleTable.setItems(Global.LIST_ADMIN_SCHEDULE);
		ScheduleTable.getSelectionModel().selectFirst();
		q.Close();
		Util.setDisableButtonAdminEdit();
		buttonScheduleAdd.setDisable(false);
		buttonScheduleDelete.setDisable(false);
		buttonScheduleEdit.setDisable(true);
		Global.MAIN_PANEL.setStatus(Global.COUNT + " schedule(s) had been found");

	};

	@FXML
	private void handleAircraftInfoLoadDatabase() {
		AircraftInfoTable.setEditable(true);
		tableColAircraftInfoId.setCellValueFactory(new PropertyValueFactory<AdminAircraftInfo, String>("id"));
		tableColAircraftInfoName.setCellValueFactory(new PropertyValueFactory<AdminAircraftInfo, String>("name"));
		tableColAircraftInfoNumberOfSeat.setCellValueFactory(new PropertyValueFactory<AdminAircraftInfo, Integer>("numofseat"));
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		Util.adminClearData();
		q.AdminShowAircraft();
		AircraftInfoTable.setItems(Global.LIST_ADMIN_AIRCRAFT); 
		AircraftInfoTable.getSelectionModel().selectFirst();
		q.Close();
		Util.setDisableButtonAdminEdit();
		buttonAircraftInfoAdd.setDisable(false);
		buttonAircraftInfoDelete.setDisable(false);
		buttonAircraftInfoEdit.setDisable(false);
		Global.MAIN_PANEL.setStatus(Global.COUNT + " aircraft(s) had been found");

	};
	public boolean showAirlineEditPanel(Boolean xemXet,AdminAirlineInfo airline){
		try {
			FXMLLoader loader = new FXMLLoader(MainPanel.class.getResource("view/EditPanelAirline.fxml"));
			BorderPane bp = (BorderPane) loader.load();
			final Stage dialogstage = new Stage();
			dialogstage.setTitle("Log In");
			dialogstage.initModality(Modality.WINDOW_MODAL);
			dialogstage.initOwner(Global.PRIMARY_STAGE);
			Scene scene = new Scene(bp);
			scene.getStylesheets().add("/main/view/JMetroLightTheme.css");
			bp.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY(); } });
			bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					dialogstage.setX(event.getScreenX() - xOffset);
					dialogstage.setY(event.getScreenY() - yOffset); } });
			//set stage to transparent
			scene.setFill(Color.TRANSPARENT);
			dialogstage.initStyle(StageStyle.TRANSPARENT);
			dialogstage.setScene(scene);
			final EditPanelAirlineController lid = loader.getController();
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent ke) {
		            if (ke.getCode() == KeyCode.ESCAPE) {
		                dialogstage.close();
		            }
		            if (ke.getCode() == KeyCode.ENTER) {
		                lid.handleOk();
		            }
		        }
		    });	
			lid.id.setDisable(true);
			lid.buttonClearAll.setDisable(xemXet);
			lid.setData(airline);
			lid.exe=xemXet;
			lid.setDialogStage(dialogstage);
			dialogstage.showAndWait(); 
			return lid.isOkClicked();//chu y
			}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	public boolean showAirportEditPanel(Boolean xemXet,AdminAirportInfo airport){
		try {
			FXMLLoader loader = new FXMLLoader(MainPanel.class.getResource("view/EditPanelAirport.fxml"));
			BorderPane bp = (BorderPane) loader.load();
			final Stage dialogstage = new Stage();
			dialogstage.setTitle("Log In");
			dialogstage.initModality(Modality.WINDOW_MODAL);
			dialogstage.initOwner(Global.PRIMARY_STAGE);
			Scene scene = new Scene(bp);
			scene.getStylesheets().add("/main/view/JMetroLightTheme.css");
			bp.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY(); } });
			bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					dialogstage.setX(event.getScreenX() - xOffset);
					dialogstage.setY(event.getScreenY() - yOffset); } });
			//set stage to transparent
			scene.setFill(Color.TRANSPARENT);
			dialogstage.initStyle(StageStyle.TRANSPARENT);
			dialogstage.setScene(scene);
			final EditPanelAirportController lid = loader.getController();
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent ke) {
		            if (ke.getCode() == KeyCode.ESCAPE) {
		                dialogstage.close();
		            }
		            if (ke.getCode() == KeyCode.ENTER) {
		                lid.handleOk();
		            }
		        }
		    });	
			lid.id.setDisable(true);
			lid.buttonClearAll.setDisable(xemXet);
			lid.setData(airport);
			
			lid.setDialogStage(dialogstage);
			dialogstage.showAndWait(); 
			return lid.isOkClicked();//chu y
			}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	public boolean showAircraftEditPanel(Boolean xemXet,AdminAircraftInfo aircraft){
		try {
			FXMLLoader loader = new FXMLLoader(MainPanel.class.getResource("view/EditPanelAircraftInfo.fxml"));
			BorderPane bp = (BorderPane) loader.load();
			final Stage dialogstage = new Stage();
			dialogstage.setTitle("Log In");
			dialogstage.initModality(Modality.WINDOW_MODAL);
			dialogstage.initOwner(Global.PRIMARY_STAGE);
			Scene scene = new Scene(bp);
			scene.getStylesheets().add("/main/view/JMetroLightTheme.css");
			bp.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY(); } });
			bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					dialogstage.setX(event.getScreenX() - xOffset);
					dialogstage.setY(event.getScreenY() - yOffset); } });
			//set stage to transparent
			scene.setFill(Color.TRANSPARENT);
			dialogstage.initStyle(StageStyle.TRANSPARENT);
			dialogstage.setScene(scene);
			final EditPanelAircraftInfoController lid = loader.getController();
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent ke) {
		            if (ke.getCode() == KeyCode.ESCAPE) {
		                dialogstage.close();
		            }
		            if (ke.getCode() == KeyCode.ENTER) {
		                lid.handleOk();
		            }
		        }
		    });	
			lid.labelId.setDisable(xemXet);
			lid.buttonClearAll.setDisable(xemXet);
			lid.setData(aircraft);
			lid.exe=xemXet;
			lid.setDialogStage(dialogstage);
			dialogstage.showAndWait(); 
			return lid.isOkClicked();//chu y
			}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	public boolean showScheduleEditPanel(Boolean xemXet,AdminScheduleInfo schedule){
		try {
			FXMLLoader loader = new FXMLLoader(MainPanel.class.getResource("view/EditPanelSchedule.fxml"));
			BorderPane bp = (BorderPane) loader.load();
			final Stage dialogstage = new Stage();
			dialogstage.setTitle("Log In");
			dialogstage.initModality(Modality.WINDOW_MODAL);
			dialogstage.initOwner(Global.PRIMARY_STAGE);
			Scene scene = new Scene(bp);
			scene.getStylesheets().add("/main/view/JMetroLightTheme.css");
			bp.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY(); } });
			bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					dialogstage.setX(event.getScreenX() - xOffset);
					dialogstage.setY(event.getScreenY() - yOffset); } });
			//set stage to transparent
			scene.setFill(Color.TRANSPARENT);
			dialogstage.initStyle(StageStyle.TRANSPARENT);
			dialogstage.setScene(scene);
			final EditPanelScheduleController lid = loader.getController();
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent ke) {
		            if (ke.getCode() == KeyCode.ESCAPE) {
		                dialogstage.close();
		            }
		            if (ke.getCode() == KeyCode.ENTER) {
		                lid.handleOk();
		            }
		        }
		    });			
			lid.flightId.setDisable(xemXet);
			lid.buttonClearAll.setDisable(xemXet);
			lid.exe=xemXet;//phan biet giua edit va add
			lid.setData(schedule);
			
			lid.setDialogStage(dialogstage);
			dialogstage.showAndWait(); 
			return lid.isOkClicked();//chu y
			}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	public boolean showRouteEditPanel(Boolean xemXet,AdminRouteInfo route){
		try {
			FXMLLoader loader = new FXMLLoader(MainPanel.class.getResource("view/EditPanelRoute.fxml"));
			BorderPane bp = (BorderPane) loader.load();
			final Stage dialogstage = new Stage();
			dialogstage.setTitle("Log In");
			dialogstage.initModality(Modality.WINDOW_MODAL);
			dialogstage.initOwner(Global.PRIMARY_STAGE);
			Scene scene = new Scene(bp);
			scene.getStylesheets().add("/main/view/JMetroLightTheme.css");
			bp.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY(); } });
			bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					dialogstage.setX(event.getScreenX() - xOffset);
					dialogstage.setY(event.getScreenY() - yOffset); } });
			//set stage to transparent
			scene.setFill(Color.TRANSPARENT);
			dialogstage.initStyle(StageStyle.TRANSPARENT);
			dialogstage.setScene(scene);
			final EditPanelRouteController lid = loader.getController();
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent ke) {
		            if (ke.getCode() == KeyCode.ESCAPE) {
		                dialogstage.close();
		            }
		            if (ke.getCode() == KeyCode.ENTER) {
		                lid.handleOk();
		            }
		        }
		    });			
			lid.id.setDisable(true);
			lid.buttonClearAll.setDisable(xemXet);
			lid.setData(route);
			
			lid.setDialogStage(dialogstage);
			dialogstage.showAndWait(); 
			return lid.isOkClicked();//chu y
			}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	
	private void refreshAirlineTable(int selectedIndex) {
		AirlineTable.setItems(null);
		AirlineTable.layout();
		AirlineTable.setItems(Global.LIST_ADMIN_AIRLINE);
		AirlineTable.getSelectionModel().select(selectedIndex);
	}
	private void refreshAirportTable(int selectedIndex) {
		AirportTable.setItems(null);
		AirportTable.layout();
		AirportTable.setItems(Global.LIST_ADMIN_AIRPORT);
		AirportTable.getSelectionModel().select(selectedIndex);
	}
	private void refreshAircraftTable(int selectedIndex) {
		AircraftInfoTable.setItems(null);
		AircraftInfoTable.layout();
		AircraftInfoTable.setItems(Global.LIST_ADMIN_AIRCRAFT);
		AircraftInfoTable.getSelectionModel().select(selectedIndex);
	}
	private void refreshScheduleTable(int selectedIndex) {
		ScheduleTable.setItems(null);
		ScheduleTable.layout();
		ScheduleTable.setItems(Global.LIST_ADMIN_SCHEDULE);
		ScheduleTable.getSelectionModel().select(selectedIndex);
	}
	private void refreshRouteTable(int selectedIndex) {
		RouteTable.setItems(null);
		RouteTable.layout();
		RouteTable.setItems(Global.LIST_ADMIN_ROUTE);
		RouteTable.getSelectionModel().select(selectedIndex);
	}
		
}
