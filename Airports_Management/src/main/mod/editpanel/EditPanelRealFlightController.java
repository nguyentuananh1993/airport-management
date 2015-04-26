package main.mod.editpanel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import database.Query;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import model.MyDate;
import model.ModRealFlight;
import eu.schudt.javafx.controls.calendar.DatePicker;
import fundamentals.Global;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



public class EditPanelRealFlightController {
	@FXML public ComboBox<String> FlightId;
	@FXML public TextField textfieldDate;
	@FXML public TextField textfieldNumberOfGuest;
	@FXML private GridPane gridRealFlight;
	public DatePicker datepickDate;
	@FXML private Label labelFlightId;
	@FXML private Label labelNumofGuest;
	@FXML private Label labelDate;
	
	private Stage dialogstage;
	private ModRealFlight data;
	private boolean okClicked = false;
	public boolean exe=true;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
	
	@FXML
	public void handleOk(){
		if(isInputValid(exe)){
			data.setFlight_id(FlightId.getValue());
			data.setNumpeople(Integer.parseInt(textfieldNumberOfGuest.getText()));
			data.setDay( new MyDate(datepickDate.getSelectedDate().getTime()).toString());
			okClicked=true;
			dialogstage.close();
			
		}
	}
	
	@FXML private void handleClearAll(){

	}
	@FXML private void handleClose(){
		dialogstage.close();
	}

	
	
	public void setDialogStage(Stage dialogstage) {
		this.dialogstage = dialogstage; }
	
	public void setData(ModRealFlight realflight){
		this.data=realflight;
		if(data!=null){
			FlightId.setValue(realflight.getFlight_id());
			if(exe)
				try {
					date = sdf.parse(realflight.getDay());
				} catch (Exception e) {
				}
			datepickDate.setSelectedDate(date);
			textfieldNumberOfGuest.setText(realflight.getNumpeople().toString());
		}
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	private boolean isInputValid(boolean exe) {
		boolean tmp=true;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		if(FlightId.getValue().length()>6 || FlightId.getValue().length()<=0 || q.CheckFlightId(FlightId.getValue())==0 || !q.CheckFlightAirlineAvailable(Global.USER_NAME,FlightId.getValue())) {
			tmp=false;
			Global.MAIN_PANEL.setStatus("Flight ID is not available or null.");
			labelFlightId.setText("(*)"); }
		else if(textfieldNumberOfGuest.getText().length()<=0 || Integer.parseInt(textfieldNumberOfGuest.getText())<0 
				|| q.findNumberOfSeat(FlightId.getValue())<Integer.parseInt(textfieldNumberOfGuest.getText())) {
			tmp=false;
			Global.MAIN_PANEL.setStatus("Number of Passenger must be less than number of seat and more than 0.");
			labelNumofGuest.setText("(*)"); }
		else if(datepickDate.getSelectedDate() == null) {
			tmp=false;
			Global.MAIN_PANEL.setStatus("Date must be set");
			labelDate.setText("(*)"); }
		else labelNumofGuest.setText("");
		MyDate date = new MyDate(datepickDate.getSelectedDate().getTime());
		if(!q.checkScheduleAvailable(FlightId.getValue(),date.toString()) && !exe) {
			tmp=false;
			Global.MAIN_PANEL.setStatus("Flight ID with date not available.");
			labelDate.setText("(*)");
			labelFlightId.setText("(*)");
			}
		else{
			labelNumofGuest.setText("");
			labelFlightId.setText("");
		}
		q.Close();
		return tmp; }

	@FXML
	private void initialize() {
		FlightId.getItems().clear();
		FlightId.setOnKeyReleased(new ComboBoxShow(FlightId));
		FlightId.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(FlightId,Global.FLIGHT_ID));
		textfieldNumberOfGuest.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { 
			if(newValue.intValue() > oldValue.intValue()) {
			char ch = textfieldNumberOfGuest.getText().charAt(oldValue.intValue());
			if(!(ch >= '0' && ch <= '9' || ch=='.')) { 
			textfieldNumberOfGuest.setText(textfieldNumberOfGuest.getText().substring(0,textfieldNumberOfGuest.getText().length()-1)); } } } });
		datepickDate = new DatePicker(Locale.ENGLISH);
		datepickDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		datepickDate.getCalendarView().todayButtonTextProperty().set("Today");
		datepickDate.getCalendarView().setShowWeeks(false);
		datepickDate.getStylesheets().add("/main/view/DatePicker.css");
		gridRealFlight.add(datepickDate, 1, 1);
	}
}
