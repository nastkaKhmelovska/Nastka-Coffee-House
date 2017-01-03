package ua.nastka_khmelovska.models;

public class Filiations {
	
	private Long idFiliations;
	private String country;
	private String city;
	private String address;
	private int number_of_employees;
	private double profit;
	public Long getIdFiliations() {
		return idFiliations;
	}
	public void setIdFiliations(Long idFiliations) {
		this.idFiliations = idFiliations;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getNumber_of_employees() {
		return number_of_employees;
	}
	public void setNumber_of_employees(int number_of_employees) {
		this.number_of_employees = number_of_employees;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	@Override
	public String toString() {
		String returnValue = "\nIdFiliations" + idFiliations + 
				"\nCountry" + country + 
				"\nCity" + city + 
				"\nAddress" + address +
				"\nNumber_of_employees" + number_of_employees + 
				"\nProfit" + profit;
		return returnValue;
	}
	
	

}
