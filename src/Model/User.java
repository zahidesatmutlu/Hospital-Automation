package Model;

import Helper.databaseConnection;

public class User {
	private int id;
	String tcNo, name, password, type;

	databaseConnection connection = new databaseConnection();

	public User(int id, String name, String tcNo, String password, String type) {
		this.id = id;
		this.tcNo = tcNo;
		this.name = name;
		this.password = password;
		this.type = type;
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTcNo() {
		return tcNo;
	}

	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
