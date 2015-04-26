package main;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class AboutDialog {
	private Stage dialogstage;
	
	public void setDialogStage(Stage dialogstage) {
		this.dialogstage = dialogstage; }
	@FXML
	private void handleClose() {
		dialogstage.close(); }

}
