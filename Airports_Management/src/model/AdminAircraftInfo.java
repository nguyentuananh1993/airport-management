package model;

public class AdminAircraftInfo {
	private String id;
	private String name;
	private Integer numofseat;
	public AdminAircraftInfo(){
		setId("");
		setName("");
		setNumofseat(0);
	}
	public AdminAircraftInfo(String id, String name, Integer numofseat){
		setId(id);
		setName(name);
		setNumofseat(numofseat);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumofseat() {
		return numofseat;
	}

	public void setNumofseat(Integer numofseat) {
		this.numofseat = numofseat;
	};
	
}
