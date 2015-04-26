package main.guest;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import model.GuestAirportInfo;
import database.Query;
import fundamentals.Global;

public class GuestRightPanelSearchAirport {
	@FXML
	private ComboBox<String> citycountry = new ComboBox<String>();
	@FXML
	private TableView<GuestAirportInfo> airporttable;
	@FXML
	private TableColumn<GuestAirportInfo, GuestAirportInfo> no;
	@FXML
	private TableColumn<GuestAirportInfo, String> name;
	@FXML
	private TableColumn<GuestAirportInfo, String> city;
	@FXML
	private TableColumn<GuestAirportInfo, String> country;
	@FXML
	private TableColumn<GuestAirportInfo, String> iata;
	@FXML
	private TableColumn<GuestAirportInfo, Double> timezone;
	
	public void setTextCityCountry(String c) {
		citycountry.setValue("");
		citycountry.setValue(c); }
	
	@FXML
	private void initialize() {
		citycountry.getItems().clear();
		citycountry.setOnKeyReleased(new ComboBoxShow(citycountry));
		citycountry.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(citycountry,Global.CITY_COUNTRY));
		
		no.setCellValueFactory(new Callback<CellDataFeatures<GuestAirportInfo, GuestAirportInfo>, ObservableValue<GuestAirportInfo>>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override 
			public ObservableValue<GuestAirportInfo> call(CellDataFeatures<GuestAirportInfo, GuestAirportInfo> p) {
				return new ReadOnlyObjectWrapper(airporttable.getItems().indexOf(p.getValue())+1); } });
        no.setSortable(false);
		name.setCellValueFactory(new PropertyValueFactory<GuestAirportInfo, String>("name"));
		city.setCellValueFactory(new PropertyValueFactory<GuestAirportInfo, String>("city"));
		country.setCellValueFactory(new PropertyValueFactory<GuestAirportInfo, String>("country"));
		iata.setCellValueFactory(new PropertyValueFactory<GuestAirportInfo, String>("iata"));
		timezone.setCellValueFactory(new PropertyValueFactory<GuestAirportInfo, Double>("timezone"));

		airporttable.setItems(Global.LIST_GUEST_AIRPORT); }
	
	@FXML
	public void handleSearch() {
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		String cc;
		
		Global.LIST_GUEST_AIRPORT.clear();
		Global.COUNT = 0;
		cc = citycountry.getValue();
		if(cc != null && !cc.equals("")) { 
			q.GuestSearchAirport(cc);
			Global.MAIN_PANEL.setStatus(Global.COUNT + " airport(s) had found"); }
		else {
			Global.MAIN_PANEL.setStatus("Must input correct city or country"); }
		q.Close(); }
	
	@FXML
	private void handleClearAll() {
		citycountry.setValue(""); 
		Global.LIST_GUEST_AIRPORT.clear();
		Global.MAIN_PANEL.setStatus(""); }

	@FXML
	private void handleSetDepartedAirportSearchFlight() {
		GuestAirportInfo p = airporttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchFlight();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.addDep(p.getId());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextDep(p.getName());
			Global.MAIN_PANEL.setStatus("Set Departed Airport '" + p.getName() + "'"); } 
		else Global.MAIN_PANEL.setStatus("Must select an airport"); }

	@FXML
	private void handleSetArrivedAirportSearchFlight() {
		GuestAirportInfo p = airporttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchFlight();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.addArr(p.getId());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextArr(p.getName());
			Global.MAIN_PANEL.setStatus("Set Arrived Airport '" + p.getName() + "'"); } 
		else Global.MAIN_PANEL.setStatus("Must select an airport"); }

	@FXML
	private void handleSetDepartedAirportSearchPath() {
		GuestAirportInfo p = airporttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchPath();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.addDep(p.getId());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextDep(p.getName());
			Global.MAIN_PANEL.setStatus("Set Departed Airport '" + p.getName() + "'");  } 
		else Global.MAIN_PANEL.setStatus("Must select an airport"); }

	@FXML
	private void handleSetArrivedAirportSearchPath() {
		GuestAirportInfo p = airporttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchPath();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.addArr(p.getId());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextArr(p.getName());
			Global.MAIN_PANEL.setStatus("Set Arrived Airport '" + p.getName() + "'"); } 
		else Global.MAIN_PANEL.setStatus("Must select an airport"); }

	@FXML
	private void handleSetDepartedAirportSearchRoute() {
		GuestAirportInfo p = airporttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchRoute();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.addDep(p.getId());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.setTextDep(p.getName());
			Global.MAIN_PANEL.setStatus("Set Departed Airport '" + p.getName() + "'"); } 
		else Global.MAIN_PANEL.setStatus("Must select an airport"); }

	@FXML
	private void handleSetArrivedAirportSearchRoute() {
		GuestAirportInfo p = airporttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchRoute();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.addArr(p.getId());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.setTextArr(p.getName());
			Global.MAIN_PANEL.setStatus("Set Arrived Airport '" + p.getName() + "'"); } 
		else Global.MAIN_PANEL.setStatus("Must select an airport"); }


}
