package model;

public class ModRealFlight {
	private String flight_id;
	private String day;
	private Integer numpeople;
	public ModRealFlight(){
		setFlight_id("");
		setDay("");
		setNumpeople(0);
	}
	public ModRealFlight(String flight_id, String day, Integer numpeople){
		setFlight_id(flight_id);
		setDay(day);
		setNumpeople(numpeople);
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public Integer getNumpeople() {
		return numpeople;
	}
	public void setNumpeople(Integer numpeople) {
		this.numpeople = numpeople;
	}
	
	
}
