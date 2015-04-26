package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends Date {
	private static final long serialVersionUID = 1L;
	
	public MyDate(long date) {
		super(date); }
	
	@Override
	public String toString() {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		return dateformat.format(this);	}
	
}