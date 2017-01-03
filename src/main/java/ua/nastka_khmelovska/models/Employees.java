package ua.nastka_khmelovska.models;

import java.sql.Date;

public class Employees {
	
	private Long idEmployees;
	private Long idPost;
	private Long idFiliations;
	private String name;
	private String surname;
	private int age;
	private int phone;
	private Date date_of_hiring;
	public Employees(Long idEmployees, Long idPost, Long idFiliations, String name, String surname, Integer age,
			Integer phone, Date date_of_hiring) {
		// TODO Auto-generated constructor stub
	}
	public Employees() {
		// TODO Auto-generated constructor stub
	}
	public Long getIdEmployees() {
		return idEmployees;
	}
	public void setIdEmployees(Long idEmployees) {
		this.idEmployees = idEmployees;
	}
	public Long getIdPost() {
		return idPost;
	}
	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}
	public Long getIdFiliations() {
		return idFiliations;
	}
	public void setIdFiliations(Long idFiliations) {
		this.idFiliations = idFiliations;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public Date getDate_of_hiring() {
		return date_of_hiring;
	}
	public void setDate_of_hiring(Date date_of_hiring) {
		this.date_of_hiring = date_of_hiring;
	}
	@Override
	public String toString() {
		String returnValue = "\nIdEmployees" + idEmployees +
				"\nIdPost" + idPost +
				"\nIdFiliations" + idFiliations + 
				"\nName" + name + 
				"\nSurname" + surname + 
				"\nAge" + age + 
				"\nPhone" + phone + 
				"\nDate_of_hiring" + date_of_hiring;
		return returnValue;
	}
	
	

}
