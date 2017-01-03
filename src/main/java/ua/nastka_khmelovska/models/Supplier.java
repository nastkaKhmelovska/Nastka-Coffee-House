package ua.nastka_khmelovska.models;

import java.sql.Date;

public class Supplier {
	
	private Long idSupplier;
	private String name;
	private String goods;
	private int phone;
	private Date date_of_purchase;
	private double ammount;
	public Long getIdSupplier() {
		return idSupplier;
	}
	public void setIdSupplier(Long idSupplier) {
		this.idSupplier = idSupplier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public Date getDate_of_purchase() {
		return date_of_purchase;
	}
	public void setDate_of_purchase(Date date_of_purchase) {
		this.date_of_purchase = date_of_purchase;
	}
	public double getAmmount() {
		return ammount;
	}
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	@Override
	public String toString() {
		String returnValue = "\nIdSupplier" + idSupplier +
				"\nName" + name +
				"\nGoods" + goods +
				"\nPhone" + phone +
				"\nDate_of_purchase" + date_of_purchase + 
				"\nAmmount" + ammount;
		return returnValue;
	}
	
	

}
