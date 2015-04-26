package main.admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import database.Query;
import eu.schudt.javafx.controls.calendar.DatePicker;
import fundamentals.FileHandle;
import fundamentals.Global;
import model.AdminStatisticAirlineByCountry;
import model.AdminStatisticAirlineTop10;
import model.AdminStatisticAirportByCountry;
import model.AdminStatisticAirportTop10;
import model.AdminStatisticEachAirport;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import model.MyDate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class AdminRightPanelStatistics {
	@FXML private TableView<AdminStatisticAirportByCountry> tableviewAirportByCountry;
	@FXML private TableColumn<AdminStatisticAirportByCountry, AdminStatisticAirportByCountry> tablecolAirportByCountryId;
	@FXML private TableColumn<AdminStatisticAirportByCountry, String> tablecolAirportByCountryCountry;
	@FXML private TableColumn<AdminStatisticAirportByCountry, Integer> tablecolAirportByCountryNumberOfAirport;

	@FXML private TableView<AdminStatisticAirportTop10> tableviewAirportTop10;
	@FXML private TableColumn<AdminStatisticAirportTop10, AdminStatisticAirportTop10> tabcolAirportTop10Id;
	@FXML private TableColumn<AdminStatisticAirportTop10, MyDate> tabcolAirportTop10FromDay;
	@FXML private TableColumn<AdminStatisticAirportTop10, MyDate> tabcolAirportTop10ToDay;
	@FXML private TableColumn<AdminStatisticAirportTop10, String> tabcolAirportTop10Airport;
	@FXML private TableColumn<AdminStatisticAirportTop10, Integer> tabcolAirportTop10NumberOfGuest;

	@FXML private TableView<AdminStatisticAirlineByCountry> tableviewAirlineByCountry;
	@FXML private TableColumn<AdminStatisticAirlineByCountry, AdminStatisticAirlineByCountry> tablecolAirlineByCountryId;
	@FXML private TableColumn<AdminStatisticAirlineByCountry, String> tablecolAirlineByCountryCountry;
	@FXML private TableColumn<AdminStatisticAirlineByCountry, Integer> tablecolAirlineByCountryNumberOfAirline;

	@FXML private TableView<AdminStatisticAirlineTop10> tableviewAirlineTop10;
	@FXML private TableColumn<AdminStatisticAirlineTop10, AdminStatisticAirlineTop10> tabcolAirlineTop10Id;
	@FXML private TableColumn<AdminStatisticAirlineTop10, MyDate> tabcolAirlineTop10FromDay;
	@FXML private TableColumn<AdminStatisticAirlineTop10, MyDate> tabcolAirlineTop10ToDay;
	@FXML private TableColumn<AdminStatisticAirlineTop10, String> tabcolAirlineTop10Airline;
	@FXML private TableColumn<AdminStatisticAirlineTop10, Integer> tabcolAirlineTop10NumberOfGuest;

	@FXML private TableView<AdminStatisticEachAirport> tableviewEachAirport;
	@FXML private TableColumn<AdminStatisticEachAirport, AdminStatisticEachAirport> tablecolEachAirportId;
	@FXML private TableColumn<AdminStatisticEachAirport, MyDate> tablecolEachAirportFromDay;
	@FXML private TableColumn<AdminStatisticEachAirport, MyDate> tablecolEachAirportToDay;
	@FXML private TableColumn<AdminStatisticEachAirport, String> tablecolEachAirportAirport;
	@FXML private TableColumn<AdminStatisticEachAirport, Integer> tablecolEachAirportNumberOfGuest;
	@FXML private TableColumn<AdminStatisticEachAirport, Integer> tablecolEachAirportNumberOfRoute;

	@FXML private ComboBox<String> EachAirportAirport = new ComboBox<String>();
	@FXML
	private GridPane gridAirportTop10;
	private DatePicker datepickerAirportTop10FromDay;
	private DatePicker datepickerAirportTop10ToDay;
	@FXML 
	private GridPane gridAirlineTop10;
	private DatePicker datepickerAirlineTop10FromDay;
	private DatePicker datepickerAirlineTop10ToDay;
	@FXML 
	private GridPane gridEachAirport;
	private DatePicker datepickerEachAirportFromDay;
	private DatePicker datepickerEachAirportToDay;
	
	@FXML
	private void initialize() {
		
		//Man hinh AirportByCountry
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		q.AdminStatisticsAirport();
		q.Close();
		
		tablecolAirportByCountryId.setCellValueFactory(
				new Callback<CellDataFeatures<AdminStatisticAirportByCountry, AdminStatisticAirportByCountry>, 
				ObservableValue<AdminStatisticAirportByCountry>>() {
		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override 
		public ObservableValue<AdminStatisticAirportByCountry> 
		call(CellDataFeatures<AdminStatisticAirportByCountry, AdminStatisticAirportByCountry> p) {
		return new ReadOnlyObjectWrapper(tableviewAirportByCountry.getItems().indexOf(p.getValue())+1); } });
		tablecolAirportByCountryId.setSortable(false);
		
		tablecolAirportByCountryCountry.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirportByCountry, String>("country"));
		tablecolAirportByCountryNumberOfAirport.setCellValueFactory(
				new PropertyValueFactory<AdminStatisticAirportByCountry, Integer>("numberofairport"));
		tableviewAirportByCountry.setItems(Global.LIST_ADMIN_STATISTICS_AIRPORT_BY_COUNTRY);
		
		//Man hinh Top 10 Airport

		datepickerAirportTop10FromDay = new DatePicker(Locale.ENGLISH);
		datepickerAirportTop10FromDay.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		datepickerAirportTop10FromDay.getCalendarView().todayButtonTextProperty().set("Today");
		datepickerAirportTop10FromDay.getCalendarView().setShowWeeks(false);
		datepickerAirportTop10FromDay.getStylesheets().add("/main/view/DatePicker.css");
		datepickerAirportTop10ToDay = new DatePicker(Locale.ENGLISH);
		datepickerAirportTop10ToDay.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		datepickerAirportTop10ToDay.getCalendarView().todayButtonTextProperty().set("Today");
		datepickerAirportTop10ToDay.getCalendarView().setShowWeeks(false);
		datepickerAirportTop10ToDay.getStylesheets().add("/main/view/DatePicker.css");
		gridAirportTop10.add(datepickerAirportTop10FromDay, 0, 1);
		gridAirportTop10.add(datepickerAirportTop10ToDay, 1, 1); 
		
		tabcolAirportTop10Id.setCellValueFactory(
				new Callback<CellDataFeatures<AdminStatisticAirportTop10, AdminStatisticAirportTop10>,
				ObservableValue<AdminStatisticAirportTop10>>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override 
			public ObservableValue<AdminStatisticAirportTop10> call(CellDataFeatures<AdminStatisticAirportTop10, AdminStatisticAirportTop10> p) {
				return new ReadOnlyObjectWrapper(tableviewAirportTop10.getItems().indexOf(p.getValue())+1); } });
		tabcolAirportTop10Id.setSortable(false);		
		
		tabcolAirportTop10FromDay.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirportTop10, MyDate>("fromdate"));
		tabcolAirportTop10ToDay.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirportTop10, MyDate>("todate"));
		tabcolAirportTop10Airport.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirportTop10, String>("airport"));
		tabcolAirportTop10NumberOfGuest.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirportTop10, Integer>("numberofguest"));
		tableviewAirportTop10.setItems(Global.LIST_ADMIN_STATISTICS_AIRPORT_TOP_10);
		
		//Man hinh airline by coutry
		Query p = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		p.AdminStatisticsAirline();
		p.Close();
		
		tablecolAirlineByCountryId.setCellValueFactory(
				new Callback<CellDataFeatures<AdminStatisticAirlineByCountry, AdminStatisticAirlineByCountry>, 
				ObservableValue<AdminStatisticAirlineByCountry>>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override 
			public ObservableValue<AdminStatisticAirlineByCountry> 
			call(CellDataFeatures<AdminStatisticAirlineByCountry, AdminStatisticAirlineByCountry> p) {
				return new ReadOnlyObjectWrapper(tableviewAirlineByCountry.getItems().indexOf(p.getValue())+1); } });
		tablecolAirlineByCountryId.setSortable(false);
		
		tablecolAirlineByCountryCountry.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirlineByCountry, String>("country"));
		tablecolAirlineByCountryNumberOfAirline.setCellValueFactory(
				new PropertyValueFactory<AdminStatisticAirlineByCountry, Integer>("numberofairline"));
		tableviewAirlineByCountry.setItems(Global.LIST_ADMIN_STATISTICS_AIRLINE_BY_COUNTRY);
		
		//man hinh top 10 airline
		
		datepickerAirlineTop10FromDay = new DatePicker(Locale.ENGLISH);
		datepickerAirlineTop10FromDay.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		datepickerAirlineTop10FromDay.getCalendarView().todayButtonTextProperty().set("Today");
		datepickerAirlineTop10FromDay.getCalendarView().setShowWeeks(false);
		datepickerAirlineTop10FromDay.getStylesheets().add("/main/view/DatePicker.css");
		datepickerAirlineTop10ToDay = new DatePicker(Locale.ENGLISH);
		datepickerAirlineTop10ToDay.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		datepickerAirlineTop10ToDay.getCalendarView().todayButtonTextProperty().set("Today");
		datepickerAirlineTop10ToDay.getCalendarView().setShowWeeks(false);
		datepickerAirlineTop10ToDay.getStylesheets().add("/main/view/DatePicker.css");
		gridAirlineTop10.add(datepickerAirlineTop10FromDay, 0, 1);
		gridAirlineTop10.add(datepickerAirlineTop10ToDay, 1, 1);
		
		tabcolAirlineTop10Id.setCellValueFactory(
				new Callback<CellDataFeatures<AdminStatisticAirlineTop10, AdminStatisticAirlineTop10>, 
				ObservableValue<AdminStatisticAirlineTop10>>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override 
			public ObservableValue<AdminStatisticAirlineTop10> call(CellDataFeatures<AdminStatisticAirlineTop10, AdminStatisticAirlineTop10> p) {
				return new ReadOnlyObjectWrapper(tableviewAirlineTop10.getItems().indexOf(p.getValue())+1); } });
		tabcolAirlineTop10Id.setSortable(false);
		
		tabcolAirlineTop10FromDay.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirlineTop10, MyDate>("fromdate"));
		tabcolAirlineTop10ToDay.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirlineTop10, MyDate>("todate"));
		tabcolAirlineTop10Airline.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirlineTop10, String>("airline"));
		tabcolAirlineTop10NumberOfGuest.setCellValueFactory(new PropertyValueFactory<AdminStatisticAirlineTop10, Integer>("numberofguest"));
		tableviewAirlineTop10.setItems(Global.LIST_ADMIN_STATISTICS_AIRLINE_TOP_10);
		
		//man hinh Each Airport
		
		datepickerEachAirportFromDay = new DatePicker(Locale.ENGLISH);
		datepickerEachAirportFromDay.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		datepickerEachAirportFromDay.getCalendarView().todayButtonTextProperty().set("Today");
		datepickerEachAirportFromDay.getCalendarView().setShowWeeks(false);
		datepickerEachAirportFromDay.getStylesheets().add("/main/view/DatePicker.css");
		datepickerEachAirportToDay = new DatePicker(Locale.ENGLISH);
		datepickerEachAirportToDay.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		datepickerEachAirportToDay.getCalendarView().todayButtonTextProperty().set("Today");
		datepickerEachAirportToDay.getCalendarView().setShowWeeks(false);
		datepickerEachAirportToDay.getStylesheets().add("/main/view/DatePicker.css");
		gridEachAirport.add(datepickerEachAirportFromDay, 1, 0);
		gridEachAirport.add(datepickerEachAirportToDay, 1, 1); 
		
		EachAirportAirport.getItems().clear();
		EachAirportAirport.setOnKeyReleased(new ComboBoxShow(EachAirportAirport));
		EachAirportAirport.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(EachAirportAirport,Global.AIRPORTS));
		
		tablecolEachAirportId.setCellValueFactory(
				new Callback<CellDataFeatures<AdminStatisticEachAirport, AdminStatisticEachAirport>, 
				ObservableValue<AdminStatisticEachAirport>>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override 
			public ObservableValue<AdminStatisticEachAirport> call(CellDataFeatures<AdminStatisticEachAirport, AdminStatisticEachAirport> p) {
				return new ReadOnlyObjectWrapper(tableviewEachAirport.getItems().indexOf(p.getValue())+1); } });
		tablecolEachAirportId.setSortable(false);
		
		tablecolEachAirportFromDay.setCellValueFactory(new PropertyValueFactory<AdminStatisticEachAirport, MyDate>("fromday"));
		tablecolEachAirportToDay.setCellValueFactory(new PropertyValueFactory<AdminStatisticEachAirport, MyDate>("today"));
		tablecolEachAirportAirport.setCellValueFactory(new PropertyValueFactory<AdminStatisticEachAirport, String>("airport"));
		tablecolEachAirportNumberOfGuest.setCellValueFactory(new PropertyValueFactory<AdminStatisticEachAirport, Integer>("numberofguest"));
		tablecolEachAirportNumberOfRoute.setCellValueFactory(new PropertyValueFactory<AdminStatisticEachAirport, Integer>("numberofroute"));
		tableviewEachAirport.setItems(Global.LIST_ADMIN_STATISTICS_EACH_AIRPORT);
	}
	
	@FXML private void handleAirportTop10Search(){
		if(datepickerAirportTop10FromDay.getSelectedDate() == null || datepickerAirportTop10ToDay.getSelectedDate() == null) {
			Global.MAIN_PANEL.setStatus("No input From Date or To Date");
		}
		else {
			Global.LIST_ADMIN_STATISTICS_AIRPORT_TOP_10.clear();
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminStatisticsAirportTop10Search(datepickerAirportTop10FromDay.getSelectedDate(),datepickerAirportTop10ToDay.getSelectedDate());
			q.Close();
		}
		
	}
	
	@FXML private void handleAirlineTop10Search(){
		if(datepickerAirlineTop10FromDay.getSelectedDate() == null || datepickerAirlineTop10ToDay.getSelectedDate() == null) {
			Global.MAIN_PANEL.setStatus("No input From Date or To Date");
		}
		else {
			Global.LIST_ADMIN_STATISTICS_AIRLINE_TOP_10.clear();
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminStatisticsAirlineTop10Search(datepickerAirlineTop10FromDay.getSelectedDate(),datepickerAirlineTop10ToDay.getSelectedDate());
			q.Close();
		}	
	};
	
	
	
	@FXML private void handleEachAirportSearch(){
		if(datepickerEachAirportFromDay.getSelectedDate() == null || datepickerEachAirportToDay.getSelectedDate() == null) {
			Global.MAIN_PANEL.setStatus("No input From Date or To Date");
		}
		else {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminStatisticsEachAirport(EachAirportAirport.getValue(),datepickerEachAirportFromDay.getSelectedDate(),
					datepickerEachAirportToDay.getSelectedDate());
			q.Close();
		}
	}
	
	@FXML private void handleEachAirportClearAll(){
		Global.LIST_ADMIN_STATISTICS_EACH_AIRPORT.clear();
		Global.MAIN_PANEL.setStatus("");
	}
	
	
	@FXML private void handleEachAirportSave(){
	    FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (Comma-delimited)", "*.csv");
	        fileChooser.getExtensionFilters().add(extFilter);
	        File file = fileChooser.showSaveDialog(Global.PRIMARY_STAGE);
	        
	        if (file != null) {
	        	if (!file.getPath().endsWith(".csv")) {
	        		file = new File(file.getPath() + ".csv"); }
	        	FileHandle fh = new FileHandle(file);
	        	fh.appendText("From Date,To Date,Airport,Passengers,Num of Route");
	        	for(AdminStatisticEachAirport stai : Global.LIST_ADMIN_STATISTICS_EACH_AIRPORT) {
	        		fh.appendTextline(stai.toCSV()); } 
	        	fh.Close(); } }
}