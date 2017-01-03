package ua.nastka_khmelovska.models;

public class User {
	
	private String name;
	private String password;
	
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
	
	@Override
	public String toString() {
		String returnValue = "\nName: "+name+
				"\nPassword: "+password;
		return returnValue;
	}
	

}
