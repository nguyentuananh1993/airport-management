package main.guest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;

import database.Query;
import eu.schudt.javafx.controls.calendar.DatePicker;
import fundamentals.FileHandle;
import fundamentals.Global;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import model.AutoNumberedColumn;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import model.GuestFlightInfo;
import model.MyDate;
import model.MyTimestamp;

public class GuestRightPanelSearchFlight {
	@FXML
	private ComboBox<String> departed = new ComboBox<String>();
	@FXML
	private ComboBox<String> arrived = new ComboBox<String>();
	@FXML
	private ComboBox<String> airline = new ComboBox<String>();
	@FXML
	private GridPane datepickGrid;

	private DatePicker departeddate;
	private DatePicker arriveddate;
	@FXML
	private TableView<GuestFlightInfo> flighttable;
	@FXML
	private TableColumn<GuestFlightInfo, GuestFlightInfo> no;
	@FXML
	private TableColumn<GuestFlightInfo, String> depairportname;
	@FXML
	private TableColumn<GuestFlightInfo, String> arrairportname;
	@FXML
	private TableColumn<GuestFlightInfo, String> airlinename;
	@FXML
	private TableColumn<GuestFlightInfo, MyDate> day;
	@FXML
	private TableColumn<GuestFlightInfo, MyTimestamp> deptime;
	@FXML
	private TableColumn<GuestFlightInfo, MyTimestamp> arrtime;
	@FXML
	private TableColumn<GuestFlightInfo, Double> duration;
	@FXML
	private CheckBox continuousflight;

	private LinkedList<Integer> dep = new LinkedList<Integer>();
	private LinkedList<Integer> arr = new LinkedList<Integer>();
	private LinkedList<GuestFlightInfo> listrawflight = new LinkedList<GuestFlightInfo>();
	private boolean isReset = true;
	
	public void addDep(int i) {
		dep.addLast(i);	}
	
	public void addArr(int i) {
		arr.addLast(i);	}
	
	public void addFlight(int dai, int aai, int ai, String dan, String aan, String an, MyDate d, MyTimestamp dt, MyTimestamp at, Double du) {
		listrawflight.add(new GuestFlightInfo(dai,aai,ai,dan,aan,an,d,dt,at,du));
		handleReset(); }
	
	public void setTextDep(String d) {
		departed.setValue("");
		departed.setValue(d); }
	
	public void setTextArr(String a) {
		arrived.setValue("");
		arrived.setValue(a); }
	
	public void setTextAirline(String al) {
		airline.setValue("");
		airline.setValue(al); }
	
	
	@FXML
	private void initialize() {
		departed.getItems().clear();
		departed.setOnKeyReleased(new ComboBoxShow(departed));
		departed.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(departed,Global.AIRPORTS));
		arrived.getItems().clear();
		arrived.setOnKeyReleased(new ComboBoxShow(arrived));
		arrived.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(arrived,Global.AIRPORTS));
		airline.getItems().clear();
		airline.setOnKeyReleased(new ComboBoxShow(airline));
		airline.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(airline,Global.AIRLINES));
		no.setCellValueFactory(new AutoNumberedColumn<GuestFlightInfo>(flighttable));
        no.setSortable(false);
        
		depairportname.setCellValueFactory(new PropertyValueFactory<GuestFlightInfo, String>("depairportname"));
		arrairportname.setCellValueFactory(new PropertyValueFactory<GuestFlightInfo, String>("arrairportname"));
		airlinename.setCellValueFactory(new PropertyValueFactory<GuestFlightInfo, String>("airlinename"));
		day.setCellValueFactory(new PropertyValueFactory<GuestFlightInfo, MyDate>("day"));
		deptime.setCellValueFactory(new PropertyValueFactory<GuestFlightInfo, MyTimestamp>("deptime"));
		arrtime.setCellValueFactory(new PropertyValueFactory<GuestFlightInfo, MyTimestamp>("arrtime"));
		duration.setCellValueFactory(new PropertyValueFactory<GuestFlightInfo, Double>("duration"));
		flighttable.setItems(Global.LIST_GUEST_FLIGHT);
		
		departeddate = new DatePicker(Locale.ENGLISH);
		departeddate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		departeddate.getCalendarView().todayButtonTextProperty().set("Today");
		departeddate.getCalendarView().setShowWeeks(false);
		departeddate.getStylesheets().add("/main/view/DatePicker.css");
		arriveddate = new DatePicker(Locale.ENGLISH);
		arriveddate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		arriveddate.getCalendarView().todayButtonTextProperty().set("Today");
		arriveddate.getCalendarView().setShowWeeks(false);
		arriveddate.getStylesheets().add("/main/view/DatePicker.css");
		  // Add DatePicker to grid
		datepickGrid.add(departeddate, 2, 0);
		datepickGrid.add(arriveddate, 6, 0); }
	
	@FXML
	private void handleAdd() {
		String dn,an,aln;
		dn = departed.getValue();
		an = arrived.getValue();
		aln = airline.getValue();
		LinkedList<Integer> airl = new LinkedList<Integer>();
		if(isReset == true) {
			Global.LIST_GUEST_FLIGHT.clear();
			Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
			airl = q.FindAirlineID(aln);
			if(dep.isEmpty()) dep = q.FindAirportID(dn);
			if(arr.isEmpty()) arr = q.FindAirportID(an);
			if(!dep.isEmpty()) {
				for(int d : dep) {
					if(!arr.isEmpty()) {
						for(int a : arr) {
							if(!airl.isEmpty()) {
								for(int al : airl) {
									listrawflight.add(new GuestFlightInfo(d,a,al,dn,an,aln,null,null,null,null)); } }
							else listrawflight.add(new GuestFlightInfo(d,a,0,dn,an,null,null,null,null,null)); } }
					else if(!airl.isEmpty()) {
						for(int al : airl) {
							listrawflight.add(new GuestFlightInfo(d,0,al,dn,null,aln,null,null,null,null)); } }
					else {
						listrawflight.add(new GuestFlightInfo(d,0,0,dn,null,null,null,null,null,null)); } } }
			else {
				if(!arr.isEmpty()) {
					for(int a : arr) {
						if(!airl.isEmpty()) {
							for(int al : airl) {
								listrawflight.add(new GuestFlightInfo(0,a,al,null,an,aln,null,null,null,null)); } }
						else listrawflight.add(new GuestFlightInfo(0,a,0,null,an,null,null,null,null,null)); } }
				else if(!airl.isEmpty()) {
					for(int al : airl) {
						listrawflight.add(new GuestFlightInfo(0,0,al,null,null,aln,null,null,null,null)); } }
				else ; }
			Global.LIST_GUEST_FLIGHT.addAll(listrawflight);
			dep.clear();
			arr.clear();
			q.Close(); } 
		else {
			Global.MAIN_PANEL.setStatus("Must reset before"); } }
	
	
	@FXML
	private void handleSearch() {
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		LinkedList<GuestFlightInfo> list = new LinkedList<GuestFlightInfo>();
		list.addAll(listrawflight);
		Global.LIST_GUEST_FLIGHT.clear();
		Global.COUNT = 0;
		if(!continuousflight.isSelected()) {
			q.GuestSearchFlight(list, departeddate.getSelectedDate(), arriveddate.getSelectedDate()); }
		else {
			q.GuestSearchContinuousFlight(list, departeddate.getSelectedDate(), arriveddate.getSelectedDate()); }
		list.clear();
		Global.MAIN_PANEL.setStatus(Global.COUNT + " flight(s) had found");
		q.Close();
		isReset = false; }
	
	@FXML
	private void handleClearAll() {
		departed.setValue("");
		arrived.setValue("");
		airline.setValue("");
		continuousflight.setSelected(false);
		Global.LIST_GUEST_FLIGHT.clear();
		listrawflight.clear();
		isReset = true;
		Global.MAIN_PANEL.setStatus(""); }
	
	@FXML
	private void handleUp() {
		GuestFlightInfo f;
		int selectedIndex = flighttable.getSelectionModel().getSelectedIndex();
		if (selectedIndex > 0) {
			f = flighttable.getItems().get(selectedIndex - 1);
			flighttable.getItems().remove(selectedIndex - 1);
			flighttable.getItems().add(selectedIndex, f);
			if(isReset) {
				listrawflight.remove(selectedIndex - 1);
				listrawflight.add(selectedIndex, f); } }
		else {
			Global.MAIN_PANEL.setStatus("Must select a flight"); } }
	
	@FXML
	private void handleDown() {
		GuestFlightInfo f;
		int selectedIndex = flighttable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0 && selectedIndex < flighttable.getItems().size() - 1) {
			f = flighttable.getItems().get(selectedIndex + 1);
			flighttable.getItems().add(selectedIndex, f);
			flighttable.getItems().remove(selectedIndex + 2);
			if(isReset) {
				listrawflight.add(selectedIndex, f);
				listrawflight.remove(selectedIndex + 2); } }
		else {
			Global.MAIN_PANEL.setStatus("Must select a flight"); } }
	
	@FXML
	private void handleDelete() {
		GuestFlightInfo f;
		int selectedIndex = flighttable.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			if(selectedIndex < flighttable.getItems().size() - 1) {
				f = flighttable.getItems().get(selectedIndex + 1);
				flighttable.getItems().add(selectedIndex, f);
				flighttable.getItems().remove(selectedIndex + 2);
				flighttable.getItems().remove(selectedIndex + 1); }
			else {
				flighttable.getItems().remove(selectedIndex); }
			if(isReset) {
				listrawflight.remove(selectedIndex); } }
		else {
			Global.MAIN_PANEL.setStatus("Must select a flight"); } }
	
	@FXML
	private void handleReset() {
		isReset = true;
		Global.LIST_GUEST_FLIGHT.clear();
		Global.LIST_GUEST_FLIGHT.addAll(listrawflight);	}
	
	@FXML
	private void handleSearchAirport() {
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		GuestFlightInfo p = flighttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchAirport();
			Global.LIST_GUEST_AIRPORT.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT.setTextCityCountry(null);
			q.GuestSearchAirport(p.getDepairportid());
			q.GuestSearchAirport(p.getArrairportid());
			Global.MAIN_PANEL.setStatus(Global.COUNT + " airport(s) had found");
			q.Close();
			Global.MAIN_PANEL.setStatus("Set Airport"); }
		else Global.MAIN_PANEL.setStatus("Must select a flight"); }
	
	@FXML
	private void handleSearchPath() {
		String d,a;
		d = departed.getValue();
		a = arrived.getValue();
		GuestFlightInfo p = flighttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchPath();
			Global.LIST_GUEST_PATH.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.addDep(p.getDepairportid());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.addArr(p.getArrairportid());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextDep(p.getDepairportname());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextArr(p.getArrairportname()); }
		else if(d != null && !d.equals("")	&& a != null && !a.equals("")) {
			Global.MAIN_STAGE.setGuestRightPanelSearchPath();
			Global.LIST_GUEST_PATH.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextDep(d);
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_PATH.setTextArr(a);	}
		else Global.MAIN_PANEL.setStatus("Must select a flight"); }
	
	@FXML
	private void handleSearchRoute() {
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		GuestFlightInfo p = flighttable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchRoute();
			Global.LIST_GUEST_ROUTE.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.setTextDep(p.getDepairportname());
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.setTextArr(p.getArrairportname());
			q.GuestSearchRoute(p.getDepairportid(),p.getArrairportid());
			Global.MAIN_PANEL.setStatus(Global.COUNT + " route(s) had found");
			q.Close(); }
		else Global.MAIN_PANEL.setStatus("Must select a flight"); }
	
	@FXML
	private void handleSave() {
        FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (Comma-delimited)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(Global.PRIMARY_STAGE);
        
        if (file != null) {
        	if (!file.getPath().endsWith(".csv")) {
        		file = new File(file.getPath() + ".csv"); }
        	FileHandle fh = new FileHandle(file);
        	fh.appendText("Departed airport,Arrived Airport,Airline,Date,Departed Time,Arrived Time,Duration");
        	for(GuestFlightInfo fi : Global.LIST_GUEST_FLIGHT) {
        		fh.appendTextline(fi.toCSV()); } 
        	fh.Close(); } }

}
