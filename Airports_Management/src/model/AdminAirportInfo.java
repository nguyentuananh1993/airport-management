package model;

public class AdminAirportInfo {
	private Integer id;
	private String name;
	private String city;
	private String country;
	private String iata;
	private Double latitude;
	private Double longitude;
	private Integer timezone;
	private String dst;
	public AdminAirportInfo(){
		setCity("");
		setCountry("");
		setDst("A");
		setIata("");
		setId(0);
		setLatitude(0.0);
		setLongitude(0.0);
		setName("");
		setTimezone(0);
	}
	public AdminAirportInfo(Integer id, String name, String city,
			String country, String iata, Double latitude, Double longitude,
			Integer timezone, String dst) {
		setCity(city);
		setCountry(country);
		setDst(dst);
		setIata(iata);
		setId(id);
		setLatitude(latitude);
		setLongitude(longitude);
		setName(name);
		setTimezone(timezone);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getTimezone() {
		return timezone;
	}

	public void setTimezone(Integer timezone) {
		this.timezone = timezone;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}
	
}
