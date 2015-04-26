package model;

public class ModStatisticsTopAircraftInfo {
	private MyDate fromdate;
	private MyDate todate;
	private String name;
	private int sum;
	
	public ModStatisticsTopAircraftInfo(MyDate fromdate, MyDate todate, String n, int s) {
		setFromdate(fromdate);
		setTodate(todate);
		setName(n);
		setSum(s); }
	
	public void setFromdate(MyDate fromdate) {
		this.fromdate = fromdate;	}
	
	public void setTodate(MyDate todate) {
		this.todate = todate;	}

	
	public void setName(String n) {
		name = n; }
	
	public void setSum(int s) {
		sum = s; }
	
	public MyDate getFromdate() {
		return fromdate;	}
	
	public MyDate getTodate() {
		return todate;	}
	
	
	public String getName() {
		return name; }
	
	public int getSum() {
		return sum;	}
	
	public String toCSV() {
		return getFromdate() + "," + getTodate() + "," + getName() + "," + getSum(); }
}
