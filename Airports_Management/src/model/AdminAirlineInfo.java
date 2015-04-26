package model;

public class AdminAirlineInfo {
	private Integer id;
	private String name;
	private String iata;
	private String country;
	private Boolean active;
	public AdminAirlineInfo(){
		setId(0);
		setActive(true);
		setCountry("");
		setIata("");
		setName("");
	}
	public AdminAirlineInfo(int id, String na, String ia, String co,Boolean ac) {
		setId(id);
		setActive(ac);
		setCountry(co);
		setIata(ia);
		setName(na);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
