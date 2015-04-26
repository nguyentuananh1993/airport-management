package main.guest;

import model.AutoNumberedColumn;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import model.GuestPathInfo;
import graphs.SearchGraph.Node;
import graphs.Vertex;
import database.Query;
import fundamentals.Global;

import java.util.LinkedList;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class GuestRightPanelSearchPath {	
	@FXML
	private ComboBox<String> departed = new ComboBox<String>();
	@FXML
	private ComboBox<String> arrived = new ComboBox<String>();
	@FXML
	private TextField intermediate = new TextField();
	@FXML
	private TableView<GuestPathInfo> pathtable;
	@FXML
	private TableColumn<GuestPathInfo, GuestPathInfo> no;
	@FXML
	private TableColumn<GuestPathInfo, LinkedList<String>> path;
	@FXML
	private TableColumn<GuestPathInfo, Double> length;
	private LinkedList<Integer> dep = new LinkedList<Integer>();
	private LinkedList<Integer> arr = new LinkedList<Integer>();
	
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
		no.setCellValueFactory(new AutoNumberedColumn<GuestPathInfo>(pathtable));
        no.setSortable(false);
		intermediate.lengthProperty().addListener(new ChangeListener<Number>() {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {              
			if(newValue.intValue() > oldValue.intValue()) {
				char ch = intermediate.getText().charAt(oldValue.intValue());
				if(!(ch >= '0' && ch <= '3')) {
					intermediate.setText(intermediate.getText().substring(0,intermediate.getText().length()-1)); } } } });
        
		path.setCellValueFactory(new PropertyValueFactory<GuestPathInfo, LinkedList<String>>("pathstring"));
		length.setCellValueFactory(new PropertyValueFactory<GuestPathInfo, Double>("length"));
		pathtable.setItems(Global.LIST_GUEST_PATH); }
	
	@FXML
	private void handleSearch() {
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		int im;
		try {
			im = Integer.parseInt(intermediate.getText()) + 2; }
		catch(NumberFormatException e) {im = 2; }
		if(dep.isEmpty()) dep = q.FindAirportID(departed.getValue());
		if(arr.isEmpty()) arr = q.FindAirportID(arrived.getValue());
		if(dep.isEmpty() || arr.isEmpty()) Global.MAIN_PANEL.setStatus("Must input correct airport name");
		Global.LIST_GUEST_PATH.clear();
		Global.COUNT = 0;
		for(int d : dep) {
			for(int a : arr) { 
				try {q.GuestSearchPath(d,a,im);} catch (CloneNotSupportedException e) {} } }
		Global.MAIN_PANEL.setStatus(Global.COUNT + " path(s) had found");
		dep.clear();
		arr.clear();
		q.Close(); }
	
	@FXML
	private void handleClearAll() {
		departed.setValue("");
		arrived.setValue("");
		intermediate.setText(""); 
		Global.LIST_GUEST_PATH.clear();
		Global.MAIN_PANEL.setStatus(""); }
	
	@FXML
	private void handleSearchFlight() {
		LinkedList<Node<Vertex>> list;
		GuestPathInfo p = pathtable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchFlight();
			list = p.getPath();
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextDep(null);
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.setTextArr(null);
			for(int i = 0; i <= list.size() - 2; i++) {
				Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_FLIGHT.addFlight(list.get(i).getVertex(), list.get(i+1).getVertex(), 0, 
						list.get(i).getVertexLabel().getName(), list.get(i+1).getVertexLabel().getName(), null, null, null, null, null); } }
		else Global.MAIN_PANEL.setStatus("Must select a path"); }
	
	@FXML
	private void handleSearchRoute() {
		LinkedList<Node<Vertex>> list;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		GuestPathInfo p = pathtable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchRoute();
			list = p.getPath();
			Global.LIST_GUEST_ROUTE.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.setTextDep(null);
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_ROUTE.setTextArr(null);
			for(int i = 0; i <= list.size() - 2; i++) {
				q.GuestSearchRoute(list.get(i).getVertex(),list.get(i + 1).getVertex()); }
			Global.MAIN_PANEL.setStatus(Global.COUNT + " route(s) had found");
			q.Close(); }
		else Global.MAIN_PANEL.setStatus("Must select a path"); }
	
	@FXML
	private void handleSearchAirport() {
		LinkedList<Node<Vertex>> list;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_GUEST,Global.POSTGRES_GUEST_PASSWORD);
		GuestPathInfo p = pathtable.getSelectionModel().getSelectedItem();
		if(p != null) {
			Global.MAIN_STAGE.setGuestRightPanelSearchAirport();
			list = p.getPath();
			Global.LIST_GUEST_AIRPORT.clear();
			Global.COUNT = 0;
			Global.CONTROLLER_GUEST_RIGHT_PANEL_SEARCH_AIRPORT.setTextCityCountry(null);
			for(int i = 0; i <= list.size() - 1; i++) {
				q.GuestSearchAirport(list.get(i).getVertex()); }
			Global.MAIN_PANEL.setStatus(Global.COUNT + " airport(s) had found");
			q.Close(); } 
		else Global.MAIN_PANEL.setStatus("Must select a path");	}
}
