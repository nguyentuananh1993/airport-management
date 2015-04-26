package model;

import java.util.Date;

public class AdminRealFlightInfo {
	private String flight_id;
	private Date day;
	private Integer numpeople;
	public AdminRealFlightInfo(String flight_id, Date day, Integer numpeople){
		setDay(day);
		setFlight_id(flight_id);
		setNumpeople(numpeople);
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public Integer getNumpeople() {
		return numpeople;
	}
	public void setNumpeople(Integer numpeople) {
		this.numpeople = numpeople;
	}
	
}
