package model;

public class AdminStatisticAirlineTop10 {
	private MyDate fromdate;
	private MyDate todate;
	private String airline;
	private Integer numberofguest;
	
	public AdminStatisticAirlineTop10(MyDate fromday, MyDate today, String airline, Integer numberofguest){
		setFromdate(fromday);
		setTodate(today);
		setAirline(airline);
		setNumberofguest(numberofguest);
	}

	public void setFromdate(MyDate f) {
		fromdate = f;	}
	
	public void setTodate(MyDate t) {
		todate = t;	}

	public void setAirline(String n) {
		airline = n;}

	public void setNumberofguest(Integer num) {
		numberofguest = num;
	}

	public MyDate getFromdate() {
		return fromdate;	}
	
	public MyDate getTodate() {
		return todate;	}

	public String getAirline() {
		return airline;
	}

	public Integer getNumberofguest() {
		return numberofguest;
	}

}
