package main.admin.editpanel;

import database.Query;
import fundamentals.Global;
import model.AdminScheduleInfo;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPanelScheduleController {
	
	@FXML public TextField flightId;
	@FXML public ComboBox<String> depairportiata;
	@FXML public ComboBox<String> arrairportiata;
	@FXML private TextField depTime;
	@FXML private TextField arrTime;
	@FXML private ComboBox<String> equipId;
	@FXML private TextField repeat;
	@FXML private TextField duration;
	@FXML public Button buttonClearAll;
	@FXML private Label labelFlightId;
	@FXML private Label labelEquipId;
	@FXML private Label labelDepTime;
	@FXML private Label labelArrTime;
	@FXML private Label labelDepIata;
	@FXML private Label labelArrIata;
	@FXML private Label labelRepeat;
	@FXML private Label labelDuration;
	
	private Stage dialogstage;
	private AdminScheduleInfo data;
	private boolean okClicked = false;
	public boolean exe=false;
	@FXML
	private void initialize() {
		repeat.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { 
			if(newValue.intValue() > oldValue.intValue()) {
			char ch = repeat.getText().charAt(oldValue.intValue());
			if(!(ch >= '0' && ch <= '9')) { 
			repeat.setText(repeat.getText().substring(0,repeat.getText().length()-1)); } } } });
		duration.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { 
			if(newValue.intValue() > oldValue.intValue()) {
			char ch = duration.getText().charAt(oldValue.intValue());
			if(!(ch >= '0' && ch <= '9' || ch=='.')) { 
			duration.setText(duration.getText().substring(0,duration.getText().length()-1)); } } } });
		equipId.getItems().clear();
		equipId.setOnKeyReleased(new ComboBoxShow(equipId));
		equipId.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(equipId,Global.AIRCRAFT_ID));
		depairportiata.getItems().clear();
		depairportiata.setOnKeyReleased(new ComboBoxShow(depairportiata));
		depairportiata.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(depairportiata,Global.IATA_AIRPORTS));
		arrairportiata.getItems().clear();
		arrairportiata.setOnKeyReleased(new ComboBoxShow(arrairportiata));
		arrairportiata.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(arrairportiata,Global.IATA_AIRPORTS));
	}
	
	public void setDialogStage(Stage dialogstage) {
		this.dialogstage = dialogstage; }
	
	public boolean isOkClicked() {
		return okClicked;
	}
	@FXML private void handleClose(){
		dialogstage.close();
	}

	@FXML private void handleClearAll(){
		depairportiata.setValue("");
		arrairportiata.setValue("");
		equipId.setValue("");
		repeat.setText("0");
		duration.setText("0.0");
		flightId.setText("");
		depTime.setText("");
		arrTime.setText("");
	}
	public void setData(AdminScheduleInfo schedule){
		this.data=schedule;
		if(data!= null){
			depairportiata.setValue(schedule.getDep_airport_iata());
			arrairportiata.setValue(schedule.getArr_airport_iata());
			flightId.setText(schedule.getFlight_id());
			equipId.setValue(schedule.getEquip_id());
			repeat.setText(Integer.toString(schedule.getRepeat()));
			duration.setText(Double.toString(schedule.getDuration()));
			depTime.setText(schedule.getDeptime());
			arrTime.setText(schedule.getArrtime());
		}
	}
	@FXML public void handleOk(){
		if(isInputValid(exe)){
			data.setDuration(Double.parseDouble(duration.getText()));
			data.setEquip_id(equipId.getValue());
			data.setArrtime(arrTime.getText());
			data.setDeptime(depTime.getText());
			data.setFlight_id(flightId.getText());
			data.setRepeat(Integer.parseInt(repeat.getText()));
			data.setDep_airport_iata(depairportiata.getValue());
			data.setArr_airport_iata(arrairportiata.getValue());
			
			okClicked=true;
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.AdminUpdateSchedule(data, data.getDep_airport_iata(),data.getArr_airport_iata());
			q.Close();
			dialogstage.close();
			
		}
	}
	//q.findSchedule(flightId.getText(),exe)==0 ||
	private boolean isInputValid(boolean exe) {
		boolean tmp=true;
		
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		if(q.findSchedule(flightId.getText(),exe)==0 || flightId.getLength()<=0 || flightId.getLength()>6){
			tmp=false;
			Global.MAIN_PANEL.setStatus("Flight ID is not available or null.");
			labelFlightId.setText("(*)");
		}else labelFlightId.setText("");
		if(equipId.getValue().length()<=0 ){
			Global.MAIN_PANEL.setStatus("Equip ID is null.");
			tmp=false;
			labelEquipId.setText("(*)");
		}else labelEquipId.setText("");
		
		if(depTime.getLength()!=8){
			Global.MAIN_PANEL.setStatus("Departed Time is incorrect.");
			tmp=false;
			labelDepTime.setText("(*)");
		}else labelDepTime.setText("");
		if(arrTime.getLength()!=8){
			Global.MAIN_PANEL.setStatus("Arrived Time is incorrect.");
			tmp=false;
			labelArrTime.setText("(*)");
		}else labelArrTime.setText("");
		if(depairportiata.getValue().length()!=3 || q.CheckAirportIata(depairportiata.getValue())==0){
			Global.MAIN_PANEL.setStatus("Dep Iata is incorrect.");
			tmp=false;
			labelDepIata.setText("(*)");
		}else labelDepIata.setText("");
		
		if(arrairportiata.getValue().length()!=3 || q.CheckAirportIata(arrairportiata.getValue())==0){
			Global.MAIN_PANEL.setStatus("Dep Iata is incorrect.");
			tmp=false;
			labelArrIata.setText("(*)");
		}else labelArrIata.setText("");
		if(Integer.parseInt(repeat.getText())<=0){
			Global.MAIN_PANEL.setStatus("Repeat must be more than 0.");
			tmp=false;
			labelRepeat.setText("(*)");
		}else labelRepeat.setText("");
		if(Double.parseDouble(duration.getText())<=0){
			Global.MAIN_PANEL.setStatus("Duration must be more than 0.");
			tmp=false;
			labelDuration.setText("(*)");
		}else labelDuration.setText("");
		if(q.SearchRouteId(depairportiata.getValue(),arrairportiata.getValue())==0){
			Global.MAIN_PANEL.setStatus("departed airport not connect to arrived airport.");
			tmp=false;
			labelArrIata.setText("(*)");
			labelDepIata.setText("(*)");
		}else {
			labelArrIata.setText("");
			labelDepIata.setText("");
		}
		q.Close();
		return tmp;
	}
}
