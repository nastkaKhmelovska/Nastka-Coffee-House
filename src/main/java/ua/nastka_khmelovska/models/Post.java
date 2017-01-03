package ua.nastka_khmelovska.models;

public class Post {

	private Long idPost;
	private String name;
	private Double salary;
	
	
	public Long getIdPost() {
		return idPost;
	}
	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	@Override
	public String toString() {
		String returnValue = "\nidPost: "+idPost+
				"\nName: "+name+
				"\nSalary: "+salary;
		
		return returnValue;
	}
	
	
}
