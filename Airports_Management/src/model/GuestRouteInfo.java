package model;

public class GuestRouteInfo {

	private int departedairport;
	private int arrivedairport;
	private int airline;
	private String departedairportname;
	private String arrivedairportname;
	private String airlinename;
	
	public GuestRouteInfo(int da, int aa, int a, String dan, String aan, String an) {
		setDepartedairport(da); 
		setArriveddairport(aa);
		setAirline(a);
		setDepartedairportname(dan);
		setArrivedairportname(aan);
		setAirlinename(an); }
	
	public void setDepartedairport(int da) {
		departedairport = da; }
	
	public void setArriveddairport(int aa) {
		arrivedairport = aa; }
	
	public void setAirline(int a) {
		airline = a; }
	
	public void setDepartedairportname(String da) {
		departedairportname = da; }
	
	public void setArrivedairportname(String aa) {
		arrivedairportname = aa; }
	
	public void setAirlinename(String a) {
		airlinename = a; }
	
	public int getDepartedairport() {
		return departedairport; }
	
	public int getArrivedairport() {
		return arrivedairport; }
	
	public int getAirline() {
		return airline; }
	
	public String getDepartedairportname() {
		return departedairportname; }
	
	public String getArrivedairportname() {
		return arrivedairportname; }
	
	public String getAirlinename() {
		return airlinename; }
		
}
