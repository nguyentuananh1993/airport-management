package main.admin.editpanel;


import database.Query;
import fundamentals.Global;
import model.AdminAirlineInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPanelAirlineController {
	
	@FXML public TextField id;
	@FXML private TextField name;
	@FXML private TextField iata;
	@FXML private TextField	country;
	@FXML private ComboBox<String> active;
	@FXML public Button buttonClearAll;
	@FXML private Label labelName;
	@FXML private Label labelIata;
	@FXML private Label labelCountry;
	@FXML private Label labelActive;
	private Stage dialogstage;
	private AdminAirlineInfo data;
	private boolean okClicked = false;
	public boolean exe;
	@FXML
	private void initialize() {
		ObservableList<String> status = FXCollections.observableArrayList( 
				"true","false");
		active.setItems(status);
		}
	
	public void setDialogStage(Stage dialogstage) {
		this.dialogstage = dialogstage; 
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}

	@FXML private void handleClose(){
		dialogstage.close();
	}

	@FXML private void handleClearAll(){
		name.setText("");
		iata.setText("");
		country.setText("");
		labelCountry.setText("");
		labelIata.setText("");
		labelName.setText("");
	}
	public void setData(AdminAirlineInfo Airline){
		this.data=Airline;
		if(data!=null){
			id.setText(Integer.toString(Airline.getId()));
			name.setText(Airline.getName());
			iata.setText(Airline.getIata());
			country.setText(Airline.getCountry());
			active.setValue(Boolean.toString(Airline.getActive()));
			}
	}
	
	@FXML
	public void handleOk(){
		if(isInputValid()){
			data.setId(Integer.parseInt(id.getText()));
			data.setName(name.getText());
			data.setActive(Boolean.valueOf(active.getValue()));
			data.setIata(iata.getText());
			data.setCountry(country.getText());
			okClicked = true;
			dialogstage.close();
		}
	}
	
	private boolean isInputValid() {
		boolean tmp=true;
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		
		if(iata.getLength()!=2 || (q.checkAirlineIataAvailable(iata.getText())&&!exe)){
			Global.MAIN_PANEL.setStatus("Airline IATA is including 2 characters or not available.");
			labelIata.setText("(*)");
			tmp=false;
		}else{labelIata.setText("");}
		if(country.getText().length()<=0){
			Global.MAIN_PANEL.setStatus("Country is incorrect or null.");
			labelCountry.setText("(*)");
			tmp=false;
		}else{labelCountry.setText("");}
		if(name.getText().length()<=0){
			Global.MAIN_PANEL.setStatus("Airline's name is Incorrect or null.");
			labelName.setText("(*)");
			tmp=false;
		}else{labelName.setText("");}
		if(active.getValue().equals("true") && exe && q.checkAirlineIataAvailable(iata.getText())){
			Global.MAIN_PANEL.setStatus("Have a Iata active status change to true.");
			labelActive.setText("(*)");
			tmp=false;
		}else labelActive.setText("");
		return tmp;
	}

}
