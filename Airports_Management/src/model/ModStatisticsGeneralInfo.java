package model;

public class ModStatisticsGeneralInfo {
	private MyDate fromdate;
	private MyDate todate;
	private int numofpassenger;
	private int numofflight;
	private int numofroute;
	
	public ModStatisticsGeneralInfo(MyDate fromdate, MyDate todate, int passenger, int flight, int route) {
		setFromdate(fromdate);
		setTodate(todate);
		setNumofpassenger(passenger);
		setNumofflight(flight);
		setNumofroute(route); }
	
	public void setFromdate(MyDate fromdate) {
		this.fromdate = fromdate;	}
	
	public void setTodate(MyDate todate) {
		this.todate = todate;	}

	public void setNumofpassenger(int passenger) {
		numofpassenger = passenger; }
	
	public void setNumofflight(int flight) {
		numofflight = flight; }
	
	public void setNumofroute(int route) {
		numofroute = route; }
	
	public MyDate getFromdate() {
		return fromdate;	}
	
	public MyDate getTodate() {
		return todate;	}
	
	public int getNumofpassenger() {
		return numofpassenger; }
	
	public int getNumofflight() {
		return numofflight; }
	
	public int getNumofroute() {
		return numofroute; }
	
	public String toCSV() {
		String s = new String();
		s = getFromdate() + "," + getTodate() + "," + getNumofflight() + "," + getNumofroute() + "," + getNumofpassenger();
		return s; }
}