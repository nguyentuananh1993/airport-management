package main.mod;

import java.io.IOException;

import database.Query;
import fundamentals.Global;
import fundamentals.Util;
import main.MainPanel;
import main.mod.editpanel.EditPanelRealFlightController;
import model.ModRealFlight;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ModRightPanelRealFlight {
	@FXML private TableView<ModRealFlight> tableviewRealFlight;
	@FXML private TableColumn<ModRealFlight, String> tablecolFlightId;
	@FXML private TableColumn<ModRealFlight, String> tablecolDay;
	@FXML private TableColumn<ModRealFlight, Integer> tablecolNumberOfPeople;
	
	@FXML private Button buttonAdd;
	@FXML private Button buttonEdit;
	@FXML private Button buttonDelete;
	private double xOffset = 0;
	private double yOffset = 0;
	@FXML private void handleLoadData(){
		tableviewRealFlight.setEditable(true);
		tablecolFlightId.setCellValueFactory(new PropertyValueFactory<ModRealFlight, String>("flight_id"));
		tablecolDay.setCellValueFactory(new PropertyValueFactory<ModRealFlight, String>("day"));
		tablecolNumberOfPeople.setCellValueFactory(new PropertyValueFactory<ModRealFlight, Integer>("numpeople"));
		Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
		Util.modClearData();
		q.ModShowRealFlightData(Global.USER_NAME);
		tableviewRealFlight.setItems(Global.LIST_MOD_REAL_FLIGHT); 
		tableviewRealFlight.getSelectionModel().selectFirst();
		q.Close();
		Global.MAIN_PANEL.setStatus(Global.COUNT + " Flight(s) had found");
		buttonAdd.setDisable(false);
		buttonDelete.setDisable(false);
		buttonEdit.setDisable(false);
	}
	
	public void setButtonDisableAll(){
		buttonAdd.setDisable(true);
		buttonDelete.setDisable(true);
		buttonEdit.setDisable(true);
	}
	@FXML private void handleAdd(){
		int selectedIndex = tableviewRealFlight.getSelectionModel().getSelectedIndex();
		ModRealFlight flight = new ModRealFlight();
		boolean okClicked = showRealFlightEditPanel(false, flight);
		if (okClicked) {
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.ModInsertRealFlight(flight);
			q.Close();
			Global.LIST_MOD_REAL_FLIGHT.add(flight);
			refreshRealFlightTable(selectedIndex);
			Global.MAIN_PANEL.setStatus("RealFlight of "+Global.USER_NAME+" had been added.");
		}
	}
	@FXML private void handleEdit(){
		int selectedIndex = tableviewRealFlight.getSelectionModel().getSelectedIndex();
		ModRealFlight selectedFlight = tableviewRealFlight.getSelectionModel().getSelectedItem();
		if(selectedFlight != null){
			boolean isOkclick = showRealFlightEditPanel(true,selectedFlight);
			if(isOkclick){
				Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
				q.ModUpdateRealFlight(selectedFlight, selectedFlight.getFlight_id());
				q.Close();
				refreshRealFlightTable(selectedIndex);
				Global.MAIN_PANEL.setStatus("RealFlight of "+Global.USER_NAME+" had been edited.");
			}
		}
	}
	@FXML private void handleDelete(){
		int selectedIndex = tableviewRealFlight.getSelectionModel().getSelectedIndex();
		ModRealFlight realflight = tableviewRealFlight.getSelectionModel().getSelectedItem();
		try{
			Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
			q.ModRealFlightDelete(realflight.getFlight_id(),realflight.getDay());
			q.Close();
			tableviewRealFlight.getItems().remove(selectedIndex);
			tableviewRealFlight.getSelectionModel().selectedIndexProperty();
			Global.MAIN_PANEL.setStatus("RealFlight of "+Global.USER_NAME+" had been deleted.");
		}catch(Exception e){Global.MAIN_PANEL.setStatus("RealFlight of "+Global.USER_NAME+" can't delete.");}
	}
	
	public boolean showRealFlightEditPanel(Boolean xemXet,ModRealFlight flight){
		try {
			FXMLLoader loader = new FXMLLoader(MainPanel.class.getResource("view/ModRealFlightEditPanel.fxml"));
			BorderPane bp = (BorderPane) loader.load();
			final Stage dialogstage = new Stage();
			dialogstage.setTitle("Log In");
			dialogstage.initModality(Modality.WINDOW_MODAL);
			dialogstage.initOwner(Global.PRIMARY_STAGE);
			Scene scene = new Scene(bp);
			scene.getStylesheets().add("/main/view/JMetroLightTheme.css");
			bp.setOnMousePressed(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					xOffset = event.getSceneX();
					yOffset = event.getSceneY(); } });
			bp.setOnMouseDragged(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					dialogstage.setX(event.getScreenX() - xOffset);
					dialogstage.setY(event.getScreenY() - yOffset); } });
			//set stage to transparent
			scene.setFill(Color.TRANSPARENT);
			dialogstage.initStyle(StageStyle.TRANSPARENT);
			dialogstage.setScene(scene);
			final EditPanelRealFlightController lid = loader.getController();
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		        public void handle(KeyEvent ke) {
		            if (ke.getCode() == KeyCode.ESCAPE) {
		                dialogstage.close();
		            }
		            if (ke.getCode() == KeyCode.ENTER) {
		                lid.handleOk();
		            }
		        }
		    });	
			lid.exe=xemXet;
			lid.FlightId.setDisable(xemXet);
			lid.datepickDate.setDisable(xemXet);
			lid.textfieldDate.setDisable(xemXet);
			lid.setData(flight);
			lid.setDialogStage(dialogstage);
			dialogstage.showAndWait(); 
			return lid.isOkClicked();//chu y
			}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	private void refreshRealFlightTable(int selectedIndex) {
		tableviewRealFlight.setItems(null);
		tableviewRealFlight.layout();
		tableviewRealFlight.setItems(Global.LIST_MOD_REAL_FLIGHT);
		tableviewRealFlight.getSelectionModel().select(selectedIndex);
	}
}
