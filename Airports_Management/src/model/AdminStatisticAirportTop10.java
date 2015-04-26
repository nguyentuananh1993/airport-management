package model;

public class AdminStatisticAirportTop10 {
	private MyDate fromdate;
	private MyDate todate;
	private String airport;
	private int numberofguest;
	
	public AdminStatisticAirportTop10(MyDate fromdate, MyDate todate, String airport, int num) {
		setTodate(todate);
		setFromdate(fromdate);
		setAirport(airport);
		setNumberofguest(num); }

	public void setFromdate(MyDate f) {
		fromdate = f;	}
	
	public void setTodate(MyDate t) {
		todate = t;	}

	public void setAirport(String m) {
		airport = m;
	}
	public void setNumberofguest(int num) {
		numberofguest = num;
	}

	public MyDate getFromdate() {
		return fromdate;	}
	
	public MyDate getTodate() {
		return todate;	}

	public String getAirport() {
		return airport;
	}

	public int getNumberofguest() {
		return numberofguest;
	}
}