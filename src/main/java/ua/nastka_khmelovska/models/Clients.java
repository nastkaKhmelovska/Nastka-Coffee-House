package ua.nastka_khmelovska.models;

import java.sql.Date;

public class Clients {
	
	private Long idClients;
	private Long idFavourite_meal;
	private String name;
	private String surname;
	private Date date_of_birth;
	private int phone;
	private double discount;
	public Long getIdClients() {
		return idClients;
	}
	public void setIdClients(Long idClients) {
		this.idClients = idClients;
	}
	public Long getIdFavourite_meal() {
		return idFavourite_meal;
	}
	public void setIdFavourite_meal(Long idFavourite_meal) {
		this.idFavourite_meal = idFavourite_meal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		String returnValue = "\nIdClients" + idClients +
				"\nIdFavourite_Meal" + idFavourite_meal + 
				"\nName" + name +
				"\nSurname" + surname + 
				"\nDate_of_birth" + date_of_birth + 
				"\nPhone" + phone + 
				"\nDiscount" + discount;
		return returnValue;
	}
	
	
	

}
