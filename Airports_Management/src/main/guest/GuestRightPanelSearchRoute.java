package main.guest;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AutoNumberedColumn;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import model.GuestRouteInfo;
import database.Query;
import fundamentals.Global;
import fundamentals.MyLinkedList;

public class GuestRightPanelSearchRoute {
	@FXML
	private ComboBox<String> departed = new ComboBox<String>();
	@FXML
	private ComboBox<String> arrived = new ComboBox<String>();
	@FXML
	private TableView<GuestRouteInfo> routetable;
	@FXML
	private TableColumn<GuestRouteInfo, GuestRouteInfo> no;
	@FXML
	private TableColumn<GuestRouteInfo, String> departedairportname;
	@FXML
	private TableColumn<GuestRouteInfo, String> arrivedairportname;
	@FXML
	private TableColumn<GuestRouteInfo, String> airlinename;
	@FXML
	private CheckBox withoutairline = new CheckBox();
	private MyLinkedList<Integer> dep = new MyLinkedList<Integer>();
	private MyLinkedList<Integer> arr = new MyLinkedList<Integer>();
	
	public void addDep(int i) {
		dep.addLast(i);	}
	
	public void addArr(int i) {
		arr.addLast(i);	}
	
	public void setTextDep(String d) {
		departed.setValue("");
		departed.setValue(d); }
	
	public void setTextArr(String a) {
		arrived.setValue("");
		arrived.setValue(a); }
	
	@FXML
	private void initialize() {
		departed.getItems().clear();
		departed.setOnKeyReleased(new ComboBoxShow(departed));
		departed.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(departed,Global.AIRPORTS));
		arrived.getItems().clear();
		arrived.setOnKeyReleased(new ComboBoxShow(arrived));
		arrived.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(arrived,Global.AIRPORTS));
		no.setCellValueFactory(new AutoNumberedColumn<GuestRouteInfo>(routetable));
        no.setSortable(false);
        
		departedairportname.setCellValueFactory(new PropertyValueFactory<GuestRouteInfo, String>("departedairportname"));
		arrivedairportname.setCellValueFactory(new PropertyValueFactory<GuestRouteInfo, String>("arrivedairportname"));
		airlinename.setCellValueFactory(new PropertyValueFactory<GuestRouteInfo, String>("airlinename")); 
		routetable.setItems(Global.LIST_GUEST_ROUTE); }
	
	@FXML
	private void handleSearch() {
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		if(dep.isEmpty()) dep = q.FindAirportID(departed.getValue());
		if(arr.isEmpty()) arr = q.FindAirportID(arrived.getValue());
		GuestRouteInfo r = routetable.getSelectionModel().getSelectedItem();
		
		Global.LIST_GUEST_ROUTE.clear();
		Global.COUNT = 0;
		if(!dep.isEmpty() && !arr.isEmpty()) {
			for(int d : dep) {
				for(int a : arr) {
					q.GuestSearchRoute(d,a); } } }
		else if(r != null) {
			q.GuestSearchRoute(r.getDepartedairport(), r.getArrivedairport());	}
		else {
			Global.MAIN_PANEL.setStatus("Must input correct airport name");	}
		Global.MAIN_PANEL.setStatus(Global.COUNT + " route(s) had found");
		dep.clear();
		arr.clear();
		q.Close(); }
	
	@FXML
	private void handleClearAll() {
		departed.setValue("");
		withoutairline.setSelected(false);
		arrived.setValue(""); 
		Global.LIST_GUEST_ROUTE.clear();
		Global.MAIN_PANEL.setStatus(""); }
	
	@FXML
	private void handleSearchFlight() {
		String d,a;
		d = departed.getValue();
		a = arrived.getValue();
		GuestRouteInfo p = routetable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchFlight();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextDep(p.getDepartedairportname()); 
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextArr(p.getArrivedairportname()); 
			if(!withoutairline.isSelected()) Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextAirline(p.getAirlinename()); 
			else Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextAirline(null); 
			Global.MAIN_PANEL.setStatus("Set Route"); }
		else if(d != null && !d.equals("")	&& a != null && !a.equals("")) {
			Global.MAIN_STAGE.setGuestRightPanelSearchFlight();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextDep(d);
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextArr(a); 
			Global.MAIN_PANEL.setStatus("Set Route"); }
		else Global.MAIN_PANEL.setStatus("Must select a route"); }

	@FXML
	private void handleSearchAirport() {
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		GuestRouteInfo p = routetable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchAirport();
			Global.LIST_GUEST_AIRPORT.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT.setTextCityCountry(null);
			q.GuestSearchAirport(p.getDepartedairport());
			q.GuestSearchAirport(p.getArrivedairport());
			Global.MAIN_PANEL.setStatus(Global.COUNT + " airport(s) had found");
			q.Close();
			Global.MAIN_PANEL.setStatus("Set Airport"); }
		else Global.MAIN_PANEL.setStatus("Must select a route"); }
	
	@FXML
	private void handleSearchPath() {
		String d,a;
		d = departed.getValue();
		a = arrived.getValue();
		GuestRouteInfo p = routetable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchPath();
			Global.LIST_GUEST_PATH.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.addDep(p.getDepartedairport());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.addArr(p.getArrivedairport());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextDep(p.getDepartedairportname());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextArr(p.getArrivedairportname()); }
		else if(d != null && !d.equals("")	&& a != null && !a.equals("")) {
			Global.MAIN_STAGE.setGuestRightPanelSearchPath();
			Global.LIST_GUEST_PATH.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextDep(d);
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextArr(a);	}
		else Global.MAIN_PANEL.setStatus("Must select a route"); }
	
	 

}
