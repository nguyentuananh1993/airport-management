package model;

public class AdminStatisticEachAirport {
	private MyDate fromday;
	private MyDate today;
	private String airport;
	private Integer numberofguest;
	private Integer numberofroute;

	public AdminStatisticEachAirport(MyDate fromday, MyDate today, String airport, Integer numberofguest, Integer numberofroute){
		setFromday(fromday);
		setToday(today);
		setAirport(airport);
		setNumberofguest(numberofguest);
		setNumberofroute(numberofroute);
	}
	public MyDate getFromday() {
		return fromday;
	}
	public void setFromday(MyDate f) {
		fromday = f;
	}
	public MyDate getToday() {
		return today;
	}
	public void setToday(MyDate t) {
		today = t;
	}
	public String getAirport() {
		return airport;
	}
	public void setAirport(String a) {
		airport = a;
	}
	public Integer getNumberofguest() {
		return numberofguest;
	}
	public void setNumberofguest(Integer num) {
		numberofguest = num;
	}
	public Integer getNumberofroute() {
		return numberofroute;
	}
	public void setNumberofroute(Integer num) {
		numberofroute = num;
	}
	
	public String toCSV() {
		return getFromday() + "," + getToday() + "," + getAirport() + "," + getNumberofguest() + "," + getNumberofroute(); }
	
}
