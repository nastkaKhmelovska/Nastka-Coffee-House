package ua.nastka_khmelovska.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ua.nastka_khmelovska.models.Clients;
import ua.nastka_khmelovska.models.Employees;
import ua.nastka_khmelovska.models.Filiations;
import ua.nastka_khmelovska.models.Meal;
import ua.nastka_khmelovska.models.Post;
import ua.nastka_khmelovska.models.Supplier;
import ua.nastka_khmelovska.models.User;

public class ConnectionUtil {
	
	private final String USERNAME = "root";
	private final String PASSWORD = "1234567891011";
	private final String DATABASE = "jdbc:mysql://localhost:6453/mydb?useUnicode=true&"
			+ "useJDBCCompliantTimezoneShift=true&"
			+ "useLegacyDatetimeCode=false&"
			+ "serverTimezone=UTC";
	
	
	
	private Connection connection;
	
	
	public ConnectionUtil() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Drivce config successful");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
			System.out.println("Connection successful");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	/*USERS*/
	
	public String getNameOfAdmin() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT `name` FROM `user` WHERE `name` = \"admin\"");
		ResultSet rs = stm.executeQuery();
			User admin = new User();
			while(rs.next()){
			admin.setName(rs.getString("name"));
			return admin.getName();
			}
		return DATABASE;
	}
	
	public String getPassOfAdmin() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT `password` FROM `user` WHERE `name` = \"admin\"");
		ResultSet rs = stm.executeQuery();
			User admin = new User();
			while(rs.next()){
			admin.setPassword(rs.getString("password"));
			return admin.getPassword();
			}
			return DATABASE;
	}
	
	public String getNameOfUser() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT `name` FROM `user` WHERE `name` = \"user\"");
		ResultSet rs = stm.executeQuery();
			User user = new User();
			while(rs.next()){
			user.setName(rs.getString("name"));
			return user.getName();
			}
		return DATABASE;
	}
	
	public String getPassOfUser() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT `password` FROM `user` WHERE `name` = \"user\"");
		ResultSet rs = stm.executeQuery();
			User user = new User();
			while(rs.next()){
			user.setPassword(rs.getString("password"));
			return user.getPassword();
			}
			return DATABASE;
	}
	
	/*POST*/
	
	
	public List<Post> getAllPost() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `post`");
		ResultSet rs = stm.executeQuery();
		List<Post> posts = new ArrayList<Post>();
		while(rs.next()) {
			Post tempPost = new Post();
			tempPost.setIdPost(rs.getLong("idPost"));
			tempPost.setName(rs.getString("name"));
			tempPost.setSalary(rs.getDouble("salary"));
			System.out.println(tempPost.toString());
			posts.add(tempPost);
			
		}
		return posts;
		
	}
	
	public List<Post> selectPostOfSalary(Double salary) throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `post` WHERE salary > ?");
		stm.setDouble(1, salary);
		ResultSet rs = stm.executeQuery();
		List<Post> posts = new ArrayList<Post>();
		while(rs.next()) {
			Post tempPost = new Post();
			tempPost.setIdPost(rs.getLong("idPost"));
			tempPost.setName(rs.getString("name"));
			tempPost.setSalary(rs.getDouble("salary"));
			System.out.println(tempPost.toString());
			posts.add(tempPost);
			
		}
		return posts;
	}
	
	public void insertValuesIntoPost(Long idPost,String name, Double salary) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("INSERT INTO `post` (idPost, name, salary) VALUES (?, ?, ?)");
		stm.setLong(1, idPost);
		stm.setString(2, name);
		stm.setDouble(3, salary);
		stm.execute();
	}
	
	public void updateValuesIntoPost(String name, Double salary, Long idPost) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("UPDATE `post` SET name = ?, salary = ? WHERE idPost = ?");
		stm.setString(1, name);
		stm.setDouble(2, salary);
		stm.setLong(3, idPost);
		stm.execute();
	}
	
	public void deleteValuesFromPost(Long idPost) throws SQLException{
		
		PreparedStatement stm = null;
		stm = connection.prepareStatement("DELETE FROM `post` WHERE idPost = ?");
		stm.setLong(1, idPost);
		stm.execute();
	}
	
	/*EMPLOYEES*/
	
	public List<Employees> getAllEmployees() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `employees`");
		ResultSet rs = stm.executeQuery();
		List<Employees> employees = new ArrayList<Employees>();
		while(rs.next()) {
			Employees tempEmployees = new Employees();
			tempEmployees.setIdEmployees(rs.getLong("idEmployees"));
			tempEmployees.setIdPost(rs.getLong("idPost"));
			tempEmployees.setIdFiliations(rs.getLong("idFiliations"));
			tempEmployees.setName(rs.getString("name"));
			tempEmployees.setSurname(rs.getString("surname"));
			tempEmployees.setAge(rs.getInt("age"));
			tempEmployees.setPhone(rs.getInt("phone"));
			tempEmployees.setDate_of_hiring(rs.getDate("date_of_hiring"));
			System.out.println(tempEmployees.toString());
			employees.add(tempEmployees);
			
		}
		return employees;
		
	}
	
	public List<Employees> selectEmployeesOfFiliations(Long idFiliations) throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `employees` WHERE idFiliations = ?");
		stm.setLong(1, idFiliations);
		ResultSet rs = stm.executeQuery();
		List<Employees> employees = new ArrayList<Employees>();
		while(rs.next()) {
			Employees tempEmployees = new Employees();
			tempEmployees.setIdEmployees(rs.getLong("idEmployees"));
			tempEmployees.setIdPost(rs.getLong("idPost"));
			tempEmployees.setIdFiliations(rs.getLong("idFiliations"));
			tempEmployees.setName(rs.getString("name"));
			tempEmployees.setSurname(rs.getString("surname"));
			tempEmployees.setAge(rs.getInt("age"));
			tempEmployees.setPhone(rs.getInt("phone"));
			tempEmployees.setDate_of_hiring(rs.getDate("date_of_hiring"));
			System.out.println(tempEmployees.toString());
			employees.add(tempEmployees);
			
		}
		return employees;
		
	}
	
	
	public void insertValuesIntoEmployees(Long idEmployees, Long idPost, Long idFiliations, String name, String surname, Integer age,
			Integer phone, Date date_of_hiring) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("INSERT INTO `employees` (idEmployees, idPost, idFiliations, name, "
				+ "surname, age, phone, date_of_hiring) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		stm.setLong(1, idEmployees);
		stm.setLong(2, idPost);
		stm.setLong(3, idFiliations);
		stm.setString(4, name);
		stm.setString(5, surname);
		stm.setInt(6, age);
		stm.setInt(7, phone);
		stm.setDate(8, date_of_hiring);
		stm.execute();
	}
	
	public void updateValuesIntoEmployees(Long idPost, Long idFiliations, String name, String surname, Integer age,
			Integer phone, Date date_of_hiring, Long idEmployees) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("UPDATE `employees` SET idPost = ?, idFiliations = ?, name = ?, surname = ?,"
				+ "age = ?, phone = ?, date_of_hiring = ? WHERE idEmployees = ?");
		stm.setLong(1, idPost);
		stm.setLong(2, idFiliations);
		stm.setString(3, name);
		stm.setString(4, surname);
		stm.setInt(5, age);
		stm.setInt(6, phone);
		stm.setDate(7, date_of_hiring);
		stm.setLong(8, idEmployees);
		stm.execute();
	}
	
	public void deleteValuesFromEmployees(Long idEmployees) throws SQLException{
		
		PreparedStatement stm = null;
		stm = connection.prepareStatement("DELETE FROM `employees` WHERE idEmployees = ?");
		stm.setLong(1, idEmployees);
		stm.execute();
	}
	
	
	/*MEALS*/
	
	public List<Meal> getAllMeal() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `meal`");
		ResultSet rs = stm.executeQuery();
		List<Meal> meal = new ArrayList<Meal>();
		while(rs.next()) {
			Meal tempMeal = new Meal();
			tempMeal.setIdMeal(rs.getLong("idMeal"));
			tempMeal.setName(rs.getString("name"));
			tempMeal.setPrice(rs.getDouble("price"));
			tempMeal.setAmmount(rs.getDouble("ammount"));
			System.out.println(tempMeal.toString());
			meal.add(tempMeal);
			
		}
		return meal;
		
	}
	
	public List<Meal> selectMealOfAmmount(Double ammount) throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `meal` WHERE ammount > ?");
		stm.setDouble(1, ammount);
		ResultSet rs = stm.executeQuery();
		List<Meal> meal = new ArrayList<Meal>();
		while(rs.next()) {
			Meal tempMeal = new Meal();
			tempMeal.setIdMeal(rs.getLong("idMeal"));
			tempMeal.setName(rs.getString("name"));
			tempMeal.setPrice(rs.getDouble("price"));
			tempMeal.setAmmount(rs.getDouble("ammount"));
			System.out.println(tempMeal.toString());
			meal.add(tempMeal);
			
		}
		return meal;
	}
	
	public void insertValuesIntoMeal(Long idMeal,String name, Double price, Double ammount) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("INSERT INTO `meal` (idMeal, name, price, ammount) VALUES (?, ?, ?, ?)");
		stm.setLong(1, idMeal);
		stm.setString(2, name);
		stm.setDouble(3, price);
		stm.setDouble(4, ammount);
		stm.execute();
	}
	
	public void updateValuesIntoMeal(String name, Double price, Double ammount, Long idMeal) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("UPDATE `meal` SET name = ?, price = ?, ammount = ? WHERE idMeal = ?");
		stm.setString(1, name);
		stm.setDouble(2, price);
		stm.setDouble(3, ammount);
		stm.setLong(4, idMeal);
		stm.execute();
	}
	
	public void deleteValuesFromMeal(Long idMeal) throws SQLException{
		
		PreparedStatement stm = null;
		stm = connection.prepareStatement("DELETE FROM `meal` WHERE idMeal = ?");
		stm.setLong(1, idMeal);
		stm.execute();
	}
	
	/*FILIATIONS*/
	
	public List<Filiations> getAllFiliations() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `filiations`");
		ResultSet rs = stm.executeQuery();
		List<Filiations> filiations = new ArrayList<Filiations>();
		while(rs.next()) {
			Filiations tempFiliations = new Filiations();
			tempFiliations.setIdFiliations(rs.getLong("idFiliations"));
			tempFiliations.setCountry(rs.getString("country"));
			tempFiliations.setCity(rs.getString("city"));
			tempFiliations.setAddress(rs.getString("address"));
			tempFiliations.setNumber_of_employees(rs.getInt("number_of_employees"));
			tempFiliations.setProfit(rs.getDouble("profit"));
			System.out.println(tempFiliations.toString());
			filiations.add(tempFiliations);
			
		}
		return filiations;
		
	}
	
	public List<Filiations> selectFiliationsOfCity(String city) throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `filiations` WHERE city = ?");
		stm.setString(1, city);
		ResultSet rs = stm.executeQuery();
		List<Filiations> filiations = new ArrayList<Filiations>();
		while(rs.next()) {
			Filiations tempFiliations = new Filiations();
			tempFiliations.setIdFiliations(rs.getLong("idFiliations"));
			tempFiliations.setCountry(rs.getString("country"));
			tempFiliations.setCity(rs.getString("city"));
			tempFiliations.setAddress(rs.getString("address"));
			tempFiliations.setNumber_of_employees(rs.getInt("number_of_employees"));
			tempFiliations.setProfit(rs.getDouble("profit"));
			System.out.println(tempFiliations.toString());
			filiations.add(tempFiliations);
			
		}
		return filiations;
	}
	public void insertValuesIntoFiliations(Long idFiliations, String country, String city, String address, 
			int number_of_employees, Double profit) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("INSERT INTO `filiations` (idFiliations, country, city, address, "
				+ "number_of_employees, profit) VALUES (?, ?, ?, ?, ?, ?)");
		stm.setLong(1, idFiliations);
		stm.setString(2, country);
		stm.setString(3, city);
		stm.setString(4, address);
		stm.setInt(5, number_of_employees);
		stm.setDouble(6, profit);
		stm.execute();
	}
	
	public void updateValuesIntoFiliations(String country, String city, String address, 
			int number_of_employees, Double profit, Long idFiliations) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("UPDATE `filiations` SET country = ?, city = ?, address = ?, number_of_employees = ?,"
				+ "profit = ? WHERE idFiliations = ?");
		stm.setString(1, country);
		stm.setString(2, city);
		stm.setString(3, address);
		stm.setInt(4, number_of_employees);
		stm.setDouble(5, profit);
		stm.setLong(6, idFiliations);
		stm.execute();
	}
	
	public void deleteValuesFromFiliations(Long idFiliations) throws SQLException{
		
		PreparedStatement stm = null;
		stm = connection.prepareStatement("DELETE FROM `filiations` WHERE idFiliations = ?");
		stm.setLong(1, idFiliations);
		stm.execute();
	}
	
	/*SUPPLIER*/
	
	public List<Supplier> getAllSupplier() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `supplier`");
		ResultSet rs = stm.executeQuery();
		List<Supplier> supplier = new ArrayList<Supplier>();
		while(rs.next()) {
			Supplier tempSupplier = new Supplier();
			tempSupplier.setIdSupplier(rs.getLong("idSupplier"));
			tempSupplier.setName(rs.getString("name"));
			tempSupplier.setGoods(rs.getString("goods"));
			tempSupplier.setPhone(rs.getInt("phone"));
			tempSupplier.setDate_of_purchase(rs.getDate("date_of_purchase"));
			tempSupplier.setAmmount(rs.getDouble("ammount"));
			System.out.println(tempSupplier.toString());
			supplier.add(tempSupplier);
			
		}
		return supplier;
		
	}
	
	public List<Supplier> selectSupplierOfGoods(String goods) throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `supplier` WHERE goods = ?");
		stm.setString(1, goods);
		ResultSet rs = stm.executeQuery();
		List<Supplier> supplier = new ArrayList<Supplier>();
		while(rs.next()) {
			Supplier tempSupplier = new Supplier();
			tempSupplier.setIdSupplier(rs.getLong("idSupplier"));
			tempSupplier.setName(rs.getString("name"));
			tempSupplier.setGoods(rs.getString("goods"));
			tempSupplier.setPhone(rs.getInt("phone"));
			tempSupplier.setDate_of_purchase(rs.getDate("date_of_purchase"));
			tempSupplier.setAmmount(rs.getDouble("ammount"));
			System.out.println(tempSupplier.toString());
			supplier.add(tempSupplier);
			
		}
		return supplier;
	}
	
	public void insertValuesIntoSupplier(Long idSupplier, String name, String goods, int phone, 
			Date date_of_purchase, Double ammount) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("INSERT INTO `supplier` (idSupplier, name, goods, phone, "
				+ "date_of_purchase, ammount) VALUES (?, ?, ?, ?, ?, ?)");
		stm.setLong(1, idSupplier);
		stm.setString(2, name);
		stm.setString(3, goods);
		stm.setInt(4, phone);
		stm.setDate(5, date_of_purchase);
		stm.setDouble(6, ammount);
		stm.execute();
	}
	
	public void updateValuesIntoSupplier(String name, String goods, int phone, 
			Date date_of_purchase, Double ammount, Long idSupplier) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("UPDATE `supplier` SET name = ?, goods = ?, phone = ?, date_of_purchase = ?,"
				+ "ammount = ? WHERE idSupplier = ?");
		stm.setString(1, name);
		stm.setString(2, goods);
		stm.setInt(3, phone);
		stm.setDate(4, date_of_purchase);
		stm.setDouble(5, ammount);
		stm.setLong(6, idSupplier);
		stm.execute();
	}
	
	public void deleteValuesFromSupplier(Long idSupplier) throws SQLException{
		
		PreparedStatement stm = null;
		stm = connection.prepareStatement("DELETE FROM `supplier` WHERE idSupplier = ?");
		stm.setLong(1, idSupplier);
		stm.execute();
	}
	
	/*CLIENTS*/
	
	public List<Clients> getAllClients() throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `clients`");
		ResultSet rs = stm.executeQuery();
		List<Clients> clients = new ArrayList<Clients>();
		while(rs.next()) {
			Clients tempClients = new Clients();
			tempClients.setIdClients(rs.getLong("idClients"));
			tempClients.setIdFavourite_meal(rs.getLong("idFavourite_meal"));
			tempClients.setName(rs.getString("name"));
			tempClients.setSurname(rs.getString("surname"));
			tempClients.setDate_of_birth(rs.getDate("date_of_birth"));
			tempClients.setPhone(rs.getInt("phone"));
			tempClients.setDiscount(rs.getDouble("discount"));
			System.out.println(tempClients.toString());
			clients.add(tempClients);
			
		}
		return clients;
		
	}
	
	public List<Clients> selectClientsOfFavourite_meal(Long idFavourite_meal) throws SQLException{
		PreparedStatement stm  = connection.prepareStatement("SELECT * FROM `clients` WHERE idFavourite_meal = ?");
		stm.setLong(1, idFavourite_meal);
		ResultSet rs = stm.executeQuery();
		List<Clients> clients = new ArrayList<Clients>();
		while(rs.next()) {
			Clients tempClients = new Clients();
			tempClients.setIdClients(rs.getLong("idClients"));
			tempClients.setIdFavourite_meal(rs.getLong("idFavourite_meal"));
			tempClients.setName(rs.getString("name"));
			tempClients.setSurname(rs.getString("surname"));
			tempClients.setDate_of_birth(rs.getDate("date_of_birth"));
			tempClients.setPhone(rs.getInt("phone"));
			tempClients.setDiscount(rs.getDouble("discount"));
			System.out.println(tempClients.toString());
			clients.add(tempClients);
			
		}
		return clients;
	}
	
	public void insertValuesIntoClients(Long idClients, Long idFavourite_meal, String name, String surname, Date date_of_birth,
			int phone, Double discount) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("INSERT INTO `clients` (idClients, idFavourite_meal, name, surname, date_of_birth, "
				+ "phone, discount) VALUES (?, ?, ?, ?, ?, ?, ?)");
		stm.setLong(1, idClients);
		stm.setLong(2, idFavourite_meal);
		stm.setString(3, name);
		stm.setString(4, surname);
		stm.setDate(5, date_of_birth);
		stm.setInt(6, phone);
		stm.setDouble(7, discount);
		stm.execute();
	}
	
	public void updateValuesIntoClients(Long idFavourite_meal, String name, String surname, Date date_of_birth,
			int phone, Double discount, Long idClients) throws SQLException{
		PreparedStatement stm = null;
		stm = connection.prepareStatement("UPDATE `clients` SET idFavourite_meal = ?, name = ?, surname = ?, date_of_birth = ?,"
				+ "phone = ?, discount = ? WHERE idClients = ?");
		stm.setLong(1, idFavourite_meal);
		stm.setString(2, name);
		stm.setString(3, surname);
		stm.setDate(4, date_of_birth);
		stm.setInt(5, phone);
		stm.setDouble(6, discount);
		stm.setLong(7, idClients);
		stm.execute();
	}
	
	public void deleteValuesFromClients(Long idClients) throws SQLException{
		
		PreparedStatement stm = null;
		stm = connection.prepareStatement("DELETE FROM `clients` WHERE idClients = ?");
		stm.setLong(1, idClients);
		stm.execute();
	}
	

	public void insertValues() throws SQLException {
		PreparedStatement stm = null;
		stm = connection.prepareStatement("INSERT INTO post (name, salary) VALUES (?, ?)");
		stm.setString(1, "Designer");
		stm.setFloat(2, 500.20f);
		stm.execute();
	}
	
}
