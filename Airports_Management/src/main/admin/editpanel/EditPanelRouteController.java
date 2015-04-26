package main.admin.editpanel;

import database.Query;
import fundamentals.Global;
import model.AdminRouteInfo;
import model.ComboBoxShow;
import model.ComboBoxUpdateListItem;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditPanelRouteController {
	
	@FXML public TextField id;
	@FXML private TextField airlineId;
	@FXML private ComboBox<String> airlineIata;
	@FXML private TextField depAirportId;
	@FXML private ComboBox<String> depAirportIata;
	@FXML private TextField ArrAirportId;
	@FXML private ComboBox<String> ArrAirportIata;
	@FXML public Button buttonClearAll;
	@FXML private Label labelAirlineId;
	@FXML private Label labelAirlineIata;
	@FXML private Label labelDepAirportId;
	@FXML private Label labelDepAirportIata;
	@FXML private Label labelArrAirportId;
	@FXML private Label labelArrAirportIata;
	private Stage dialogstage;
	private AdminRouteInfo data;
	private boolean okClicked=false;
	
	@FXML
	private void initialize() {
		airlineIata.getItems().clear();
		airlineIata.setOnKeyReleased(new ComboBoxShow(airlineIata));
		airlineIata.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(airlineIata,Global.IATA_AIRLINES));
		depAirportIata.getItems().clear();
		depAirportIata.setOnKeyReleased(new ComboBoxShow(depAirportIata));
		depAirportIata.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(depAirportIata,Global.IATA_AIRPORTS));
		ArrAirportIata.getItems().clear();
		ArrAirportIata.setOnKeyReleased(new ComboBoxShow(ArrAirportIata));
		ArrAirportIata.getEditor().textProperty().addListener(new ComboBoxUpdateListItem(ArrAirportIata,Global.IATA_AIRPORTS));
		
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
		airlineIata.setValue("");
		airlineId.setText("0");
		depAirportIata.setValue("");
		depAirportId.setText("0");
		ArrAirportIata.setValue("");
		ArrAirportId.setText("0");
		labelAirlineIata.setText("");
		labelAirlineId.setText("");
		labelArrAirportIata.setText("");
		labelArrAirportId.setText("");
		labelDepAirportIata.setText("");
		labelDepAirportId.setText("");
	}
	public void setData(AdminRouteInfo route){
		this.data = route;
		if(data!= null){
		this.id.setText(Integer.toString(route.getId()));
		this.airlineId.setText(Integer.toString(route.getAirline_id()));
		this.airlineIata.setValue(route.getAirline_iata());
		this.depAirportId.setText(Integer.toString(route.getDep_airport_id()));
		this.depAirportIata.setValue(route.getDep_airport_iata());
		this.ArrAirportId.setText(Integer.toString(route.getArr_airport_id()));
		this.ArrAirportIata.setValue(route.getArr_airport_iata());}
	}
	@FXML public void handleOk(){
		if(isInputValid()){
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		data.setAirline_iata(airlineIata.getValue());
		data.setAirline_id(q.SearchAirlineId(airlineIata.getValue()));
		data.setArr_airport_iata(ArrAirportIata.getValue());
		data.setArr_airport_id(q.SearchAirportId(ArrAirportIata.getValue()));
		data.setDep_airport_iata(depAirportIata.getValue());
		data.setDep_airport_id(q.SearchAirportId(depAirportIata.getValue()));
		data.setId(Integer.parseInt(id.getText()));
		okClicked=true;
		q.Close();
		dialogstage.close();
		
		}
	}
	private boolean isInputValid() {
		boolean tmp=true;
//		if(Integer.parseInt(airlineId.getText())<1){
//			Global.MAIN_PANEL.setStatus("Airline ID is incorrect or null.");
//			tmp=false;
//			labelAirlineId.setText("(*)");
//		}else labelAirlineId.setText("");
//		
//		if(Integer.parseInt(depAirportId.getText())<1){
//			tmp=false;
//			Global.MAIN_PANEL.setStatus("Departed Airport ID is incorrect or null.");
//			labelDepAirportId.setText("(*)");
//		}else labelDepAirportId.setText("");
//		
//		if(Integer.parseInt(ArrAirportId.getText())<1){
//			tmp=false;
//			Global.MAIN_PANEL.setStatus("Arrived Airport ID is incorrect or null.");
//			labelArrAirportId.setText("(*)");
//		}else labelArrAirportId.setText("");
		Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
		
		if(airlineIata.getValue().length()!=2){
			Global.MAIN_PANEL.setStatus("Airline IATA is including 2 characters.");
			labelAirlineIata.setText("(*)");
			tmp=false;
		}else labelAirlineIata.setText("");
		
		if(depAirportIata.getValue().length()!=3){
			Global.MAIN_PANEL.setStatus("Departed Airport IATA is including 3 characters.");
			labelDepAirportIata.setText("(*)");
			tmp=false;
		}else labelDepAirportIata.setText("");
		
		if(ArrAirportIata.getValue().length()!=3){
			Global.MAIN_PANEL.setStatus("Arrived Airport IATA is including 3 characters.");
			labelArrAirportIata.setText("(*)");
			tmp=false;
		}else labelArrAirportIata.setText("");
		if(q.CheckAirlineIata(airlineIata.getValue())==0){
			Global.MAIN_PANEL.setStatus("Airline IATA is not avaiable.");
			labelAirlineIata.setText("(*)");
			tmp=false;
		}else labelAirlineIata.setText("(*)");
		if(depAirportIata.getValue().equals(ArrAirportIata.getValue())){
			Global.MAIN_PANEL.setStatus("DepAirportIata must be different to  ArrAirportIata.");
			labelDepAirportIata.setText("(*)");
			labelArrAirportIata.setText("(*)");
			tmp=false;
		}else{
			labelDepAirportIata.setText("");
			labelArrAirportIata.setText("");
		}
		q.Close();
		return tmp;
	}
}
