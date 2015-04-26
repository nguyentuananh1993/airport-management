package main.admin.editpanel;

import database.Query;
import fundamentals.Global;
import model.AdminAirportInfo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPanelAirportController {
	
	@FXML public TextField id;
	@FXML private TextField name;
	@FXML private TextField city;
	@FXML private TextField country;
	@FXML private TextField iata;
	@FXML private TextField latitude;
	@FXML private TextField longitude;
	@FXML private TextField timezone;
	@FXML private ComboBox<String> destination;
	@FXML public Button buttonClearAll;
	@FXML private Label labelName;
	@FXML private Label labelCity;
	@FXML private Label labelCountry;
	@FXML private Label labelIata;
	@FXML private Label labelDestination;
	private Stage dialogstage;
	private AdminAirportInfo data;
	private boolean okClicked = false;
	
	@FXML
	private void initialize() {
		latitude.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { 
				if(newValue.intValue() > oldValue.intValue()) {
					char ch = latitude.getText().charAt(oldValue.intValue());
					if(!(ch >= '0' && ch <= '9' || ch=='.')) { 
						latitude.setText(latitude.getText().substring(0,latitude.getText().length()-1)); } } } });
		longitude.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { 
			if(newValue.intValue() > oldValue.intValue()) {
			char ch = longitude.getText().charAt(oldValue.intValue());
			if(!(ch >= '0' && ch <= '9' || ch=='.')) { 
			longitude.setText(longitude.getText().substring(0,longitude.getText().length()-1)); } } } });
		timezone.lengthProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { 
			if(newValue.intValue() > oldValue.intValue()) {
			char ch = timezone.getText().charAt(oldValue.intValue());
			if(!(ch >= '0' && ch <= '9')) { 
			timezone.setText(timezone.getText().substring(0,timezone.getText().length()-1)); } } } });
		ObservableList<String> status = FXCollections.observableArrayList( 
				"A","E","N","O","S","U","Z");
		destination.setItems(status);
	}
	
	public void setDialogStage(Stage dialogstage) {
		this.dialogstage = dialogstage; }
	
	public boolean isOkClicked(){
		return okClicked;
	}
	
	@FXML private void handleClose(){
		dialogstage.close();
	}

	@FXML private void handleClearAll(){
		name.setText("");
		city.setText("");
		country.setText("");
		latitude.setText("0.0");
		longitude.setText("0.0");
		timezone.setText("0");
		iata.setText("");
		destination.setValue("A");
		labelCity.setText("");
		labelCountry.setText("");
		labelDestination.setText("");
		labelIata.setText("");
		labelName.setText("");
		latitude.setText("");
	}
	
	public void setData(AdminAirportInfo airport){
		this.data=airport;
		if(data!=null){
			id.setText(Integer.toString(airport.getId()));
			name.setText(airport.getName());
			city.setText(airport.getCity());
			country.setText(airport.getCountry());
			iata.setText(airport.getIata());
			latitude.setText(Double.toString(airport.getLatitude()));
			longitude.setText(Double.toString(airport.getLongitude()));
			timezone.setText(Integer.toString(airport.getTimezone()));
			destination.setValue(airport.getDst());
			}
	}
	@FXML public void handleOk(){
		if(isInputValid()){
			data.setCity(city.getText());
			data.setCountry(country.getText());
			data.setDst(destination.getValue());
			data.setIata(iata.getText());
			data.setId(Integer.parseInt(id.getText()));
			data.setLatitude(Double.parseDouble(latitude.getText()));
			data.setLongitude(Double.parseDouble(longitude.getText()));
			data.setName(name.getText());
			data.setTimezone(Integer.parseInt(timezone.getText()));
			okClicked=true;

			dialogstage.close();

		}
	}
	private boolean isInputValid() {
		boolean tmp=true;
		if(name.getLength()<=0){
			Global.MAIN_PANEL.setStatus("Airport's name is incorrect or null.");
			labelName.setText("(*)");
			tmp=false;
		}else labelName.setText("");
		if(city.getLength()<=0){
			Global.MAIN_PANEL.setStatus("City is incorrect or null.");
			labelCity.setText("(*)");
			tmp=false;
		}else labelCity.setText("");
		if(country.getLength()<=0){
			Global.MAIN_PANEL.setStatus("Country is incorrect or null.");
			labelCountry.setText("(*)");
			tmp=false;
		}else labelCountry.setText("");
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		if(iata.getLength()!=3){
			Global.MAIN_PANEL.setStatus("Airport IATA is including 3 characters.");
			labelIata.setText("(*)");
			tmp=false;
		}else if(q.checkAirportIata(iata.getText())){
			Global.MAIN_PANEL.setStatus("Airport IATA is not available.");
			labelIata.setText("(*)");
			tmp=false;
		}else labelIata.setText("");
		q.Close();
		if(destination.getValue().length()!=1){
			Global.MAIN_PANEL.setStatus("Destination is incorrect or null.");
			labelDestination.setText("(*)");
			tmp=false;
		}else labelDestination.setText("");
		return tmp;
	}

}
