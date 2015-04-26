package model;

public class ModStatisticsTopRouteInfo{
	private MyDate fromdate;
	private MyDate todate;
	private String departedairport;
	private String arrivedairport;
	private int numofflight;
	private int numofpeople;
	
	public ModStatisticsTopRouteInfo(MyDate fromdate, MyDate todate, String d, String a, int nf, int np) {
		setFromdate(fromdate);
		setTodate(todate);
		setDepartedairport(d);
		setArrivedairport(a);
		setNumofFlight(nf);
		setNumofPeople(np);	}
	
	public void setFromdate(MyDate fromdate) {
		this.fromdate = fromdate;	}
	
	public void setTodate(MyDate todate) {
		this.todate = todate;	}

	public void setDepartedairport(String d) {
		departedairport = d; }

	public void setArrivedairport(String a) {
		arrivedairport = a; }
	
	public void setNumofFlight(int nf) {
		numofflight = nf; }
	
	public void setNumofPeople(int np) {
		numofpeople = np; }
	
	public MyDate getFromdate() {
		return fromdate;	}
	
	public MyDate getTodate() {
		return todate;	}
	
	
	public String getDepartedairport() {
		return departedairport;	}
	
	public String getArrivedairport() {
		return arrivedairport;	}
	
	public int getNumofflight() {
		return numofflight;	}
	
	public int getNumofpeople() {
		return numofpeople;	}
	
	public String toCSV() {
		return getFromdate() + "," + getTodate() + "," 
				+ getDepartedairport() + "," + getArrivedairport() + "," + getNumofflight() + "," + getNumofpeople(); }

}