package model;

public class AccountInfo {
	private Integer id;
	private String name;
	private String password;
	public AccountInfo(Integer id, String name, String password){
		setId(id);
		setName(name);
		setPassword(password);
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
