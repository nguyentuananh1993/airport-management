package model;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ComboBoxShow implements EventHandler<Event> {
	private ComboBox<String> combobox;
	
	public ComboBoxShow(ComboBox<String> c) {
		combobox = c; }

	@Override
	public void handle(Event event) {
		if(!combobox.getItems().isEmpty()) combobox.show(); }
}
