package model;

import ternarysearchtree.TernarySearchTree;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;

public class ComboBoxUpdateListItem implements ChangeListener<String> {
	private ComboBox<String> combobox;
	private TernarySearchTree originallistitem;
	
	public ComboBoxUpdateListItem(ComboBox<String> c, TernarySearchTree oli) {
		combobox = c;
		originallistitem = oli; }
	
	@Override
	public void changed(ObservableValue<? extends String> o,
			String oldString, String newString) {
		if(!combobox.getItems().contains(newString)) {
			combobox.getItems().clear();
			combobox.getItems().addAll(originallistitem.matchPrefix(newString)); } }
}
