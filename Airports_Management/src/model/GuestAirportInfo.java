package model;

public class GuestAirportInfo {
	private int id;
	private String name;
	private String city;
	private String country;
	private String iata;
	private Double timezone;

	public GuestAirportInfo(int id, String n, String ci, String co, String i, Double t) {
		setId(id);
		setName(n); 
		setCity(ci);
		setCountry(co);
		setIata(i);
		setTimezone(t); }
	
	public void setId(int i) {
		id = i; }
	
	public void setName(String n) {
		name = n; }
	
	public void setCity(String ci) {
		city = ci; }
	
	public void setCountry(String co) {
		country = co; }
	
	public void setIata(String i) {
		iata = i; }
	
	public void setTimezone(Double t) {
		timezone = t; }
	
	public int getId() {
		return id; }
	
	public String getName() {
		return name; }
	
	public String getCity() {
		return city; }
	
	public String getCountry() {
		return country; }
	
	public String getIata() {
		return iata; }
	
	public Double getTimezone() {
		return timezone; }
	
}
