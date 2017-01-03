package ua.nastka_khmelovska.models;

public class Meal {

	private Long idMeal;
	private String name;
	private double price;
	private double ammount;
	
	public Long getIdMeal() {
		return idMeal;
	}
	public void setIdMeal(Long idMeal) {
		this.idMeal = idMeal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	@Override
	public String toString() {
		String returnValue = "\nIdMeal" + idMeal +
				"\nName" + name +
				"\nPrice" + price + 
				"\nAmmount" + ammount;
		return returnValue;
	}
	
	
	
}
