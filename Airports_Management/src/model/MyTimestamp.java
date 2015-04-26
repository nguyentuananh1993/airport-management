package model;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MyTimestamp extends Timestamp {

	private static final long serialVersionUID = 1L;
	
	public MyTimestamp(long timestamp) {
		super(timestamp); }

	@Override
	public String toString() {
		DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
		return dateformat.format(this);	}
}
