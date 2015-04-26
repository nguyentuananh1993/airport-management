package model;

import java.util.List;

public class GuestFlightInfo {
	private int depairportid;
	private int arrairportid;
	private int airlineid;
	private String depairportname;
	private String arrairportname;
	private String airlinename;
	private MyDate day;
	private MyTimestamp deptime;
	private MyTimestamp arrtime;
	private Double duration;
	
	public GuestFlightInfo(int dai, int aai, int ai, String dan, String aan, String an, MyDate d, MyTimestamp dt, MyTimestamp at, Double du) {
		setDepairportid(dai);
		setArrairportid(aai);
		setAirlineid(ai);
		setDepairportname(dan);
		setArrairportname(aan);
		setAirlinename(an);
		setDay(d);
		setDeptime(dt);
		setArrtime(at);
		setDuration(du); }
	
	public void setDepairportid(int dai) {
		depairportid = dai; }
	
	public void setArrairportid(int aai) {
		arrairportid = aai; }
	
	public void setAirlineid(int ai) {
		airlineid = ai; }
	
	public void setDepairportname(String dan) {
		depairportname = dan; }
	
	public void setArrairportname(String aan) {
		arrairportname = aan; }
	
	public void setAirlinename(String an) {
		airlinename = an; }
	
	public void setDay(MyDate d) {
		day = d; }
	
	public void setDeptime(MyTimestamp dt) {
		deptime = dt; }
	
	public void setArrtime(MyTimestamp at) {
		arrtime = at; }
	
	public void setDuration(Double du) {
		duration = du; }
	
	public int getDepairportid() {
		return depairportid; }
	
	public int getArrairportid() {
		return arrairportid; }
	
	public int getAirlineid() {
		return airlineid; }
	
	public String getDepairportname() {
		return depairportname; }
	
	public String getArrairportname() {
		return arrairportname; }
	
	public String getAirlinename() {
		return airlinename; }
	
	public MyDate getDay() {
		return day; }
	
	public MyTimestamp getDeptime() {
		return deptime; }
	
	public MyTimestamp getArrtime() {
		return arrtime; }
	
	public Double getDuration() {
		return duration; }
	
	public static boolean CheckExistedFlightInfo(GuestFlightInfo fi, List<GuestFlightInfo> l) {
		if(fi != null && l != null) {
			for(GuestFlightInfo f : l) {
				if(f.getDepairportid() == fi.getDepairportid() && f.getArrairportid() == fi.getArrairportid() 
						&& f.getAirlineid() == fi.getAirlineid()
						&& f.getDay().compareTo(fi.getDay()) == 0
						&& f.getDeptime().compareTo(fi.getDeptime()) == 0
						&& f.getArrtime().compareTo(fi.getArrtime()) == 0)
						return true; }
			return 	false; }
		else return false; }
	
	public String toCSV() {
		return getDepairportname() + "," + getArrairportname() + "," + getAirlinename() + "," + getDay() + ","
				+ getDeptime() + "," + getArrtime() + "," + getDuration(); }
}
