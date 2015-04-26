package model;


public class AdminScheduleInfo {
	private String flight_id;
	private String dep_airport_iata;
	private String arr_airport_iata;
	private String deptime;
	private String arrtime;
	private String equip_id;
	private Integer repeat;
	private Double duration;
	public AdminScheduleInfo(){
		setArrtime(null);
		setDeptime(null);
		setDuration(0.0);
		setEquip_id("");
		setFlight_id("");
		setRepeat(0);
		setDep_airport_iata("");
		setArr_airport_iata("");
	}
	public AdminScheduleInfo(String flight_id, String dep_airport_iata,String arr_airport_iata, String deptime,
			String arrtime, String equip_id, Integer repeat, Double duration) {
		setArrtime(arrtime);
		setDeptime(deptime);
		setDuration(duration);
		setFlight_id(flight_id);
		setEquip_id(equip_id);
		setRepeat(repeat);
		setDep_airport_iata(dep_airport_iata);
		setArr_airport_iata(arr_airport_iata);
	}
	
	public String getDep_airport_iata() {
		return dep_airport_iata;
	}
	public void setDep_airport_iata(String dep_airport_iata) {
		this.dep_airport_iata = dep_airport_iata;
	}
	public String getArr_airport_iata() {
		return arr_airport_iata;
	}
	public void setArr_airport_iata(String arr_airport_iata) {
		this.arr_airport_iata = arr_airport_iata;
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}

	public String getDeptime() {
		return deptime;
	}
	public void setDeptime(String deptime) {
		this.deptime = deptime;
	}
	public String getArrtime() {
		return arrtime;
	}
	public void setArrtime(String arrtime) {
		this.arrtime = arrtime;
	}
	public String getEquip_id() {
		return equip_id;
	}
	public void setEquip_id(String equip_id) {
		this.equip_id = equip_id;
	}
	public Integer getRepeat() {
		return repeat;
	}
	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	
}