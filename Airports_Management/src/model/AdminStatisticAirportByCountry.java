package model;

public class AdminStatisticAirportByCountry {
	
	private String country;
	private Integer numberofairport;
	
	public AdminStatisticAirportByCountry(String country, Integer numberofairport){
		setCountry(country);
		setNumberofairport(numberofairport);
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String c) {
		country = c;
	}
	public Integer getNumberofairport() {
		return numberofairport;
	}
	public void setNumberofairport(Integer num) {
		numberofairport = num;
	}
	
}
