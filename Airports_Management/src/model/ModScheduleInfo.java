package model;


public class ModScheduleInfo {
	private String flight_id;
	private Integer route_id;
	private String deptime;
	private String arrtime;
	private String equip_id;
	private Integer repeat;
	private Double duration;
	public ModScheduleInfo(){
		setArrtime(null);
		setDeptime(null);
		setDuration(0.0);
		setEquip_id("");
		setFlight_id("");
		setRepeat(0);
		setRoute_id(0);
	}
	public ModScheduleInfo(String flight_id, Integer route_id, String deptime,
			String arrtime, String equip_id, Integer repeat, Double duration) {
		setArrtime(arrtime);
		setDeptime(deptime);
		setDuration(duration);
		setFlight_id(flight_id);
		setEquip_id(equip_id);
		setRepeat(repeat);
		setRoute_id(route_id);
	}
	public String getFlight_id() {
		return flight_id;
	}
	public void setFlight_id(String flight_id) {
		this.flight_id = flight_id;
	}
	public Integer getRoute_id() {
		return route_id;
	}
	public void setRoute_id(Integer route_id) {
		this.route_id = route_id;
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