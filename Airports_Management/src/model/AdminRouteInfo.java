package model;

public class AdminRouteInfo {
	private Integer id;
	private Integer airline_id;
	private String airline_iata;
	private Integer dep_airport_id;
	private String dep_airport_iata;
	private Integer arr_airport_id;
	private String arr_airport_iata;
	public AdminRouteInfo(){
		setAirline_iata("");
		setAirline_id(0);
		setArr_airport_iata("");
		setArr_airport_id(0);
		setDep_airport_iata("");
		setDep_airport_id(0);
		setId(0);
	}
	public AdminRouteInfo(Integer id, Integer airlineid,
			String airlineiata, Integer depairportid, String depairportiata,
			Integer arrairportid, String arrairportiata) {
		setAirline_iata(airlineiata);
		setAirline_id(airlineid);
		setArr_airport_iata(arrairportiata);
		setArr_airport_id(arrairportid);
		setDep_airport_iata(depairportiata);
		setDep_airport_id(depairportid);
		setId(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAirline_id() {
		return airline_id;
	}

	public void setAirline_id(Integer airline_id) {
		this.airline_id = airline_id;
	}

	public String getAirline_iata() {
		return airline_iata;
	}

	public void setAirline_iata(String airline_iata) {
		this.airline_iata = airline_iata;
	}

	public Integer getDep_airport_id() {
		return dep_airport_id;
	}

	public void setDep_airport_id(Integer dep_airport_id) {
		this.dep_airport_id = dep_airport_id;
	}

	public String getDep_airport_iata() {
		return dep_airport_iata;
	}

	public void setDep_airport_iata(String dep_airport_iata) {
		this.dep_airport_iata = dep_airport_iata;
	}

	public Integer getArr_airport_id() {
		return arr_airport_id;
	}

	public void setArr_airport_id(Integer arr_airport_id) {
		this.arr_airport_id = arr_airport_id;
	}

	public String getArr_airport_iata() {
		return arr_airport_iata;
	}

	public void setArr_airport_iata(String arr_airport_iata) {
		this.arr_airport_iata = arr_airport_iata;
	}

	
}
	