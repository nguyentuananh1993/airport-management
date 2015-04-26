package main.mod;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

import database.Query;
import eu.schudt.javafx.controls.calendar.DatePicker;
import fundamentals.FileHandle;
import fundamentals.Global;
import model.AutoNumberedColumn;
import model.ModStatisticsGeneralInfo;
import model.ModStatisticsTopAircraftInfo;
import model.ModStatisticsTopRouteInfo;
import model.MyDate;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
public class ModRightPanelStatistics {
	
    // Khai bao bien Bang general
	@FXML private TableView<ModStatisticsGeneralInfo> GeneralTable;
	@FXML private TableColumn<ModStatisticsGeneralInfo, MyDate> GeneralFromDate;
	@FXML private TableColumn<ModStatisticsGeneralInfo, MyDate> GeneralToDate;
	@FXML private TableColumn<ModStatisticsGeneralInfo, Integer> GeneralNumOfPassenger;
	@FXML private TableColumn<ModStatisticsGeneralInfo, Integer> GeneralNumOfFlight;
	@FXML private TableColumn<ModStatisticsGeneralInfo, Integer> GeneralNumOfRoute;
	@FXML private GridPane GeneralDatepickGrid;
	private DatePicker GeneralFromday;
	private DatePicker GeneralToday;
	
	//Khai bao bien bang Top Route
	@FXML private TableView<ModStatisticsTopRouteInfo> TopRouteTable;
	@FXML private TableColumn<ModStatisticsTopRouteInfo, ModStatisticsTopRouteInfo> TopRouteNo;
	@FXML private TableColumn<ModStatisticsTopRouteInfo, MyDate> TopRouteFromDate;
	@FXML private TableColumn<ModStatisticsTopRouteInfo, MyDate> TopRouteToDate;
	@FXML private TableColumn<ModStatisticsTopRouteInfo, String> TopRouteDepartedAirport;
	@FXML private TableColumn<ModStatisticsTopRouteInfo, String> TopRouteArrivedAirport;
	@FXML private TableColumn<ModStatisticsTopRouteInfo, Integer> TopRouteNumOfFlight;
	@FXML private TableColumn<ModStatisticsTopRouteInfo, Integer> TopRouteNumOfPeople;
	@FXML private GridPane TopRouteDatepickGrid;
	private DatePicker TopRouteFromday;
	private DatePicker TopRouteToday;

	// Khai bao bien bang Top Aircraft
	@FXML private TableView<ModStatisticsTopAircraftInfo> TopAircraftTable;
	@FXML private TableColumn<ModStatisticsTopAircraftInfo, ModStatisticsTopAircraftInfo> TopAircraftNo;
	@FXML private TableColumn<ModStatisticsTopAircraftInfo, MyDate> TopAircraftFromDate;
	@FXML private TableColumn<ModStatisticsTopAircraftInfo, MyDate> TopAircraftToDate;
	@FXML private TableColumn<ModStatisticsTopAircraftInfo, String> TopAircraftNameOfAircraft;
	@FXML private TableColumn<ModStatisticsTopAircraftInfo, Integer> TopAircraftSum;
	@FXML private GridPane TopAircraftDatepickGrid;
	private DatePicker TopAircraftFromday;
	private DatePicker TopAircraftToday;

	@FXML
	private void initialize() {

		//Man hinh General
		GeneralFromDate.setCellValueFactory(new PropertyValueFactory<ModStatisticsGeneralInfo, MyDate>("fromdate"));
		GeneralToDate.setCellValueFactory(new PropertyValueFactory<ModStatisticsGeneralInfo, MyDate>("todate"));
		GeneralNumOfPassenger.setCellValueFactory(new PropertyValueFactory<ModStatisticsGeneralInfo, Integer>("numofpassenger"));
		GeneralNumOfFlight.setCellValueFactory(new PropertyValueFactory<ModStatisticsGeneralInfo, Integer>("numofflight"));
		GeneralNumOfRoute.setCellValueFactory(new PropertyValueFactory<ModStatisticsGeneralInfo, Integer>("numofroute"));
		GeneralTable.setItems(Global.LIST_MOD_STATISTICS_GENERAL);

		GeneralFromday = new DatePicker(Locale.ENGLISH);
		GeneralFromday.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		GeneralFromday.getCalendarView().todayButtonTextProperty().set("Today");
		GeneralFromday.getCalendarView().setShowWeeks(false);
		GeneralFromday.getStylesheets().add("/main/view/DatePicker.css");
		GeneralToday = new DatePicker(Locale.ENGLISH);
		GeneralToday.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		GeneralToday.getCalendarView().todayButtonTextProperty().set("Today");
		GeneralToday.getCalendarView().setShowWeeks(false);
		GeneralToday.getStylesheets().add("/main/view/DatePicker.css");
		GeneralDatepickGrid.add(GeneralFromday, 1, 0);
		GeneralDatepickGrid.add(GeneralToday, 1, 1);

		//Man hinh Route
		TopRouteNo.setCellValueFactory(new AutoNumberedColumn<ModStatisticsTopRouteInfo>(TopRouteTable));
        TopRouteNo.setSortable(false);
		TopRouteFromDate.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopRouteInfo, MyDate>("fromdate"));
		TopRouteToDate.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopRouteInfo, MyDate>("todate"));
		TopRouteDepartedAirport.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopRouteInfo, String>("departedairport"));
		TopRouteArrivedAirport.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopRouteInfo, String>("arrivedairport"));
		TopRouteNumOfFlight.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopRouteInfo, Integer>("numofflight"));
		TopRouteNumOfPeople.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopRouteInfo, Integer>("numofpeople"));
		TopRouteTable.setItems(Global.LIST_MOD_STATISTICS_TOP_ROUTE);
		
		TopRouteFromday = new DatePicker(Locale.ENGLISH);
		TopRouteFromday.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		TopRouteFromday.getCalendarView().todayButtonTextProperty().set("Today");
		TopRouteFromday.getCalendarView().setShowWeeks(false);
		TopRouteFromday.getStylesheets().add("/main/view/DatePicker.css");
		TopRouteToday = new DatePicker(Locale.ENGLISH);
		TopRouteToday.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		TopRouteToday.getCalendarView().todayButtonTextProperty().set("Today");
		TopRouteToday.getCalendarView().setShowWeeks(false);
		TopRouteToday.getStylesheets().add("/main/view/DatePicker.css");
		TopRouteDatepickGrid.add(TopRouteFromday, 1, 0);
		TopRouteDatepickGrid.add(TopRouteToday, 1, 1);

		//Man hinh Aircraft
		TopAircraftNo.setCellValueFactory(new AutoNumberedColumn<ModStatisticsTopAircraftInfo>(TopAircraftTable));
        TopAircraftNo.setSortable(false);
		TopAircraftFromDate.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopAircraftInfo, MyDate>("fromdate"));
		TopAircraftToDate.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopAircraftInfo, MyDate>("todate"));
		TopAircraftNameOfAircraft.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopAircraftInfo, String>("name"));
		TopAircraftSum.setCellValueFactory(new PropertyValueFactory<ModStatisticsTopAircraftInfo, Integer>("sum"));
		TopAircraftTable.setItems(Global.LIST_MOD_STATISTICS_TOP_AIRCRAFT);
		
		TopAircraftFromday = new DatePicker(Locale.ENGLISH);
		TopAircraftFromday.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		TopAircraftFromday.getCalendarView().todayButtonTextProperty().set("Today");
		TopAircraftFromday.getCalendarView().setShowWeeks(false);
		TopAircraftFromday.getStylesheets().add("/main/view/DatePicker.css");
		TopAircraftToday = new DatePicker(Locale.ENGLISH);
		TopAircraftToday.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		TopAircraftToday.getCalendarView().todayButtonTextProperty().set("Today");
		TopAircraftToday.getCalendarView().setShowWeeks(false);
		TopAircraftToday.getStylesheets().add("/main/view/DatePicker.css");
		TopAircraftDatepickGrid.add(TopAircraftFromday, 1, 0);
		TopAircraftDatepickGrid.add(TopAircraftToday, 1, 1);
		
	}
	
//Cac ham cho cum General
	@FXML 
	private void handleGeneralOk(){
		if(GeneralFromday.getSelectedDate() == null || GeneralToday.getSelectedDate() == null) {
			Global.MAIN_PANEL.setStatus("No input From Date or To Date");
		}
		else {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
			Global.COUNT = 0;
			q.ModStatisticsGeneral(Global.USER_NAME, GeneralFromday.getSelectedDate(), GeneralToday.getSelectedDate());
			q.Close();
			Global.MAIN_PANEL.setStatus("Have " + Global.COUNT + " results"); } }
	
	@FXML 
	private void handleGeneralClear(){
		Global.LIST_MOD_STATISTICS_GENERAL.clear();
		Global.MAIN_PANEL.setStatus(""); }
	
	@FXML
	private void handleGeneralSave(){
        FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (Comma-delimited)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(Global.PRIMARY_STAGE);
        
        if (file != null) {
        	if (!file.getPath().endsWith(".csv")) {
        		file = new File(file.getPath() + ".csv"); }
        	FileHandle fh = new FileHandle(file);
        	fh.appendText("\n");
        	fh.appendText("From Date,To Date,Num of Flight,Num of Route,Num of Passenger");
        	for(ModStatisticsGeneralInfo sgi : Global.LIST_MOD_STATISTICS_GENERAL) {
        		fh.appendTextline(sgi.toCSV()); } 
        	fh.Close(); } }
	
	
//Cac ham cho cum Top Route
	@FXML 
	private void handleTopRouteOk(){
		if(TopRouteFromday.getSelectedDate() == null || TopRouteToday.getSelectedDate() == null) {
			Global.MAIN_PANEL.setStatus("No input From Date or To Date");
		}
		else {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
			Global.COUNT = 0;
			q.ModStatisticsTopRoute(Global.USER_NAME, TopRouteFromday.getSelectedDate(), TopRouteToday.getSelectedDate());
			q.Close();
			Global.MAIN_PANEL.setStatus("Have " + Global.COUNT + " results");} }
	
	@FXML 
	private void handleTopRouteClear(){
		Global.LIST_MOD_STATISTICS_TOP_ROUTE.clear();
		Global.MAIN_PANEL.setStatus(""); }
	
	@FXML
	private void handleTopRouteSave(){
        FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (Comma-delimited)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(Global.PRIMARY_STAGE);
        
        if (file != null) {
        	if (!file.getPath().endsWith(".csv")) {
        		file = new File(file.getPath() + ".csv"); }
        	FileHandle fh = new FileHandle(file);
        	fh.appendText("\n");
        	fh.appendText("From Date,To Date,Departed Airport,Arrived Airport,Num of Flight,Num of People");
        	for(ModStatisticsTopRouteInfo stri : Global.LIST_MOD_STATISTICS_TOP_ROUTE) {
        		fh.appendTextline(stri.toCSV()); } 
        	fh.Close(); } }

	
//Cac ham cho cum Top Aircraft
	@FXML 
	private void handleTopAircraftOk(){
		if(TopAircraftFromday.getSelectedDate() == null || TopAircraftToday.getSelectedDate() == null) {
			Global.MAIN_PANEL.setStatus("No input From Date or To Date");
		}
		else {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
			Global.COUNT = 0;
			q.ModStatisticsTopAircraft(Global.USER_NAME, TopAircraftFromday.getSelectedDate(), TopAircraftToday.getSelectedDate());
			q.Close();
			Global.MAIN_PANEL.setStatus("Have " + Global.COUNT + " results");} }
	
	@FXML 
	private void handleTopAircraftClear(){
		Global.LIST_MOD_STATISTICS_TOP_AIRCRAFT.clear();
		Global.MAIN_PANEL.setStatus(""); }
	
	@FXML 
	private void handleTopAircraftSave(){
        FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (Comma-delimited)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(Global.PRIMARY_STAGE);
        
        if (file != null) {
        	if (!file.getPath().endsWith(".csv")) {
        		file = new File(file.getPath() + ".csv"); }
        	FileHandle fh = new FileHandle(file);
        	fh.appendText("\n");
        	fh.appendText("From Date,To Date,Name of Aircraft,Sum");
        	for(ModStatisticsTopAircraftInfo stai : Global.LIST_MOD_STATISTICS_TOP_AIRCRAFT) {
        		fh.appendTextline(stai.toCSV()); } 
        	fh.Close(); } }	
}