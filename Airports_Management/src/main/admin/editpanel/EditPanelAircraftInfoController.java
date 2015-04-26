package main.admin.editpanel;

import database.Query;
import fundamentals.Global;
import model.AdminAircraftInfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPanelAircraftInfoController {

	@FXML public TextField labelId;
	@FXML private TextField name;
	@FXML private TextField numOfSeat;
	@FXML public Button buttonClearAll;
	@FXML private Label labelName;
	@FXML private Label labelNumOfSeat;
	@FXML private Label laid;
	private Stage dialogstage;
	private AdminAircraftInfo data;
	private boolean okClicked = false;
	public boolean exe;
	public void setDialogStage(Stage dialogstage) {
		this.dialogstage = dialogstage; }
	@FXML
	private void initialize() {
		numOfSeat.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { 
			if(newValue.intValue() > oldValue.intValue()) {
			char ch = numOfSeat.getText().charAt(oldValue.intValue());
			if(!(ch >= '0' && ch <= '9')) { 
			numOfSeat.setText(numOfSeat.getText().substring(0,numOfSeat.getText().length()-1)); } } } });
	}
	@FXML private void handleClose(){
		dialogstage.close();
	}

	@FXML private void handleClearAll(){
		labelId.setText(null);
		labelName.setText("");
		labelNumOfSeat.setText("");
		name.setText("");
		numOfSeat.setText("0");
	}
	
	public void setData(AdminAircraftInfo aircraft){
		this.data=aircraft;
		if(data!=null){
		labelId.setText(aircraft.getId());
		name.setText(aircraft.getName());
		numOfSeat.setText(Integer.toString(aircraft.getNumofseat()));
		}
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	@FXML public void handleOk(){
		if(isInputValid(exe)){
			data.setId(labelId.getText());
			data.setName(name.getText());
			data.setNumofseat(Integer.parseInt(numOfSeat.getText()));
			okClicked=true;
			
			dialogstage.close();
			
		}
	}
	
	private boolean isInputValid(boolean exe) {
		boolean tmp=true;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		if(labelId.getLength()<=0 || q.SearchAircraftId(labelId.getText(), exe)==0) {
			Global.MAIN_PANEL.setStatus("Id is incorrect.");
			laid.setText("(*)");
			tmp=false;
		}else laid.setText("");
		q.Close();
		if(name.getLength()<=0) {
			Global.MAIN_PANEL.setStatus("Username is Incorrect.");
			labelName.setText("(*)");
			tmp=false;
		}else labelName.setText("");
		if(numOfSeat.getText().length()<=0) {
			Global.MAIN_PANEL.setStatus("Number of Seat is Incorrect.(More than 0).");
			labelNumOfSeat.setText("(*)");
			tmp=false;
		}else if(Integer.parseInt(numOfSeat.getText())<0) {
			Global.MAIN_PANEL.setStatus("Number of Seat is Incorrect.(More than 0).");
			labelNumOfSeat.setText("(*)");
			tmp=false;
		}else labelNumOfSeat.setText("");
		
		return tmp;
	}

}
