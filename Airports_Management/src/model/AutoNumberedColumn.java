package model;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class AutoNumberedColumn<Item> implements Callback<CellDataFeatures<Item, Item>, ObservableValue<Item>>{
	private TableView<Item> table;
	
	public AutoNumberedColumn(TableView<Item> t) {
		table = t; }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override 
	public ObservableValue<Item> call(CellDataFeatures<Item, Item> p) {
		return new ReadOnlyObjectWrapper(table.getItems().indexOf(p.getValue())+1); }

}
