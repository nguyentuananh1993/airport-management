package main.mod.editpanel;

import database.Query;
import fundamentals.Global;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import model.ModScheduleInfo;
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
	@FXML public TextField routeId;
	@FXML private TextField depTime;
	@FXML private TextField arrTime;
	@FXML private ComboBox<String> equipId;
	@FXML private TextField repeat;
	@FXML private TextField duration;
	@FXML public Button buttonClearAll;
	@FXML private Label labelFlightId;
	@FXML private Label labelRouteId;
	@FXML private Label labelEquipId;
	@FXML private Label labelDepTime;
	@FXML private Label labelArrTime;
	@FXML private Label labelRepeat;
	@FXML private Label labelDuration;
	private Stage dialogstage;
	private ModScheduleInfo data;
	private boolean okClicked = false;
	
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
		routeId.setText("");
		equipId.setValue("");
		repeat.setText("0");
		duration.setText("0.0");
		flightId.setText("");
		depTime.setText("");
		arrTime.setText("");
	}
	public void setData(ModScheduleInfo schedule){
		this.data=schedule;
		if(data!= null){
			flightId.setText(schedule.getFlight_id());
			routeId.setText(schedule.getRoute_id().toString());
			equipId.setValue(schedule.getEquip_id());
			repeat.setText(Integer.toString(schedule.getRepeat()));
			duration.setText(Double.toString(schedule.getDuration()));
			depTime.setText(schedule.getDeptime());
			arrTime.setText(schedule.getArrtime());
		}
	}
	@FXML public void handleOk(){
		if(isInputValid()){
			data.setDuration(Double.parseDouble(duration.getText()));
			data.setEquip_id(equipId.getValue());
			data.setArrtime(arrTime.getText());
			data.setDeptime(depTime.getText());
			data.setFlight_id(flightId.getText());
			data.setRepeat(Integer.parseInt(repeat.getText()));
			data.setRoute_id(Integer.parseInt(routeId.getText()));
			okClicked=true;
			dialogstage.close();
			
		}
	}
	private boolean isInputValid() {
		boolean tmp=true;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
		if(equipId.getValue().length()<=0 || q.CheckEquipId(equipId.getValue())==0){
			Global.MAIN_PANEL.setStatus("Equip ID is null or not available.");
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
		if(repeat.getLength()<=0){
			Global.MAIN_PANEL.setStatus("Repeat must be more than or not null.");
			tmp=false;
			labelRepeat.setText("(*)");
		}else if(Integer.parseInt(repeat.getText())<0){
			Global.MAIN_PANEL.setStatus("Repeat must be more than 0 or not null");
			tmp=false;
			labelRepeat.setText("(*)");
		}else labelRepeat.setText("");
		
		if(duration.getLength()<=0){
			Global.MAIN_PANEL.setStatus("Duration must be more than 0 or not null");
			tmp=false;
			labelDuration.setText("(*)");
		}else if(Double.parseDouble(duration.getText())<0){
			Global.MAIN_PANEL.setStatus("Duration must be more than 0 or not null");
			tmp=false;
			labelDuration.setText("(*)");
		}
		q.Close();
		return tmp;
	}
}
