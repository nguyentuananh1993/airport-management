package model;

public class AdminStatisticAirlineByCountry {
	private String country;
	private Integer numberofairline;
	
	public AdminStatisticAirlineByCountry(){
		setCountry("");
		setNumberofairline(0);
	}
	public AdminStatisticAirlineByCountry(String country, Integer numberofairline){
		setCountry(country);
		setNumberofairline(numberofairline);
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String c) {
		country = c;
	}
	public Integer getNumberofairline() {
		return numberofairline;
	}
	public void setNumberofairline(Integer num) {
		numberofairline = num;
	}
	
}
