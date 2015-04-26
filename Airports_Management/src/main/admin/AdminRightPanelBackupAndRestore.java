package main.admin;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.GuestFlightInfo;
import fundamentals.FileHandle;
import fundamentals.Global;

public class AdminRightPanelBackupAndRestore {
	
	@FXML private TextField FileBackup;
	@FXML private TextField FileRestore;
	
	@FXML private void handleClearFileBackup() {
		FileBackup.setText("");
		Global.MAIN_PANEL.setStatus(""); }
	
	@FXML private void handleClearFileRestore() {
		FileBackup.setText("");
		Global.MAIN_PANEL.setStatus(""); }
	
	@FXML private void handleChooseFile() {
        FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Backup file", "*.backup");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(Global.PRIMARY_STAGE);
        
        if (file != null) {
        	if (!file.getPath().endsWith(".csv")) {
        		file = new File(file.getPath() + ".csv"); }
        	FileHandle fh = new FileHandle(file);
        	fh.appendText("Departed airport,Arrived Airport,Airline,Date,Departed Time,Arrived Time,Duration");
        	for(GuestFlightInfo fi : Global.LIST_GUEST_FLIGHT) {
        		fh.appendTextline(fi.toCSV()); } 
        	fh.Close(); } }

}
