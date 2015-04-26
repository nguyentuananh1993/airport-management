package main.mod;


import java.io.IOException;

import database.Query;
import fundamentals.Global;
import fundamentals.Util;
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
import main.MainPanel;
import main.mod.editpanel.EditPanelScheduleController;
import model.ModScheduleInfo;

public class ModRightPanelEditSchedule {
	//Khai bao table view
	@FXML private TableView<ModScheduleInfo> ModScheduleInfo;
	@FXML private TableColumn<ModScheduleInfo, String> tableColScheduleFlightId;
	@FXML private TableColumn<ModScheduleInfo, String> tableColScheduleRouteId;
	@FXML private TableColumn<ModScheduleInfo, String> tableColScheduleDepTime;
	@FXML private TableColumn<ModScheduleInfo, String> tableColScheduleArrTime;
	@FXML private TableColumn<ModScheduleInfo, String> tableColScheduleEquipId;
	@FXML private TableColumn<ModScheduleInfo, Integer> tableColScheduleRepeat;
	@FXML private TableColumn<ModScheduleInfo, Double> tableColScheduleDuration;
	@FXML public Button buttonAdd;
	@FXML public Button buttonEdit;
	@FXML public Button buttonDelete;
	@FXML private void handleLoadDatabase(){
		ModScheduleInfo.setEditable(true);
		tableColScheduleFlightId.setCellValueFactory(new PropertyValueFactory<ModScheduleInfo, String>("flight_id"));
		tableColScheduleRouteId.setCellValueFactory(new PropertyValueFactory<ModScheduleInfo, String>("route_id"));
		tableColScheduleDepTime.setCellValueFactory(new PropertyValueFactory<ModScheduleInfo, String>("deptime"));
		tableColScheduleArrTime.setCellValueFactory(new PropertyValueFactory<ModScheduleInfo, String>("arrtime"));
		tableColScheduleEquipId.setCellValueFactory(new PropertyValueFactory<ModScheduleInfo, String>("equip_id"));
		tableColScheduleRepeat.setCellValueFactory(new PropertyValueFactory<ModScheduleInfo, Integer>("repeat"));
		tableColScheduleDuration.setCellValueFactory(new PropertyValueFactory<ModScheduleInfo, Double>("duration"));
		buttonEdit.setDisable(false);
		Query q = new Query(Global.DATABASE,Global.POSTGRES_MOD,Global.POSTGRES_MOD_PASSWORD);
		Util.modClearData();
		q.ModShowScheduleData(Global.USER_NAME);
		ModScheduleInfo.setItems(Global.LIST_MOD_SCHEDULE); 
		ModScheduleInfo.getSelectionModel().selectFirst();
		q.Close();
		Global.MAIN_PANEL.setStatus(Global.COUNT + " Schedule(s) had been found");
	}

	public void setButtonDisableAll(){
		buttonAdd.setDisable(true);
		buttonDelete.setDisable(true);
		buttonEdit.setDisable(true);
	}
	@FXML private void handleAdd(){
		
	}
	
	@FXML private void handleEdit(){
		int selectedIndex = ModScheduleInfo.getSelectionModel().getSelectedIndex();
		ModScheduleInfo selectedAirline = ModScheduleInfo.getSelectionModel().getSelectedItem();
		if(selectedAirline != null){
			boolean isOkclick = showScheduleEditPanel(true,selectedAirline);
			if(isOkclick){
				Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
				q.ModUpdateSchedule(selectedAirline, selectedAirline.getFlight_id());
				q.Close();
				refreshScheduleTable(selectedIndex);
				Global.MAIN_PANEL.setStatus("Schedule of "+Global.USER_NAME+" had been modified.");
			}
		}
	}
	
	@FXML private void handleDelete(){
		int selectedIndex = ModScheduleInfo.getSelectionModel().getSelectedIndex();
		ModScheduleInfo selectedAirport = ModScheduleInfo.getSelectionModel().getSelectedItem();
		try{
			Query q = new Query(Global.DATABASE,Global.POSTGRES_ADMIN,Global.POSTGRES_ADMIN_PASSWORD);
			q.ModDeleteSchedule(selectedAirport.getFlight_id());
			q.Close();
			ModScheduleInfo.getItems().remove(selectedIndex);
			ModScheduleInfo.getSelectionModel().selectedIndexProperty();
			Global.MAIN_PANEL.setStatus("Schedule of "+Global.USER_NAME+" had been deleted.");
		}catch(Exception e){Global.MAIN_PANEL.setStatus("Schedule of "+Global.USER_NAME+" can't delete.");}
	}

	@FXML private void initialize() {}
	private double xOffset = 0;
	private double yOffset = 0;
	public boolean showScheduleEditPanel(Boolean xemXet,ModScheduleInfo schedule){
		try {
			FXMLLoader loader = new FXMLLoader(MainPanel.class.getResource("view/ModEditPanelSchedule.fxml"));
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
			final EditPanelScheduleController lid = loader.getController();
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
			lid.flightId.setDisable(xemXet);
			lid.buttonClearAll.setDisable(xemXet);
			lid.routeId.setDisable(xemXet);
			lid.setData(schedule);
			
			lid.setDialogStage(dialogstage);
			dialogstage.showAndWait(); 
			return lid.isOkClicked();//chu y=
			}
		catch (IOException e) {
			e.printStackTrace();
			return false;
		} 
		
	}
	private void refreshScheduleTable(int selectedIndex) {
		ModScheduleInfo.setItems(null);
		ModScheduleInfo.layout();
		ModScheduleInfo.setItems(Global.LIST_MOD_SCHEDULE);
		ModScheduleInfo.getSelectionModel().select(selectedIndex);
	}
}
