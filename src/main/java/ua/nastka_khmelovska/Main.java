package ua.nastka_khmelovska;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ua.nastka_khmelovska.database.ConnectionUtil;
import ua.nastka_khmelovska.models.Clients;
import ua.nastka_khmelovska.models.Employees;
import ua.nastka_khmelovska.models.Filiations;
import ua.nastka_khmelovska.models.Meal;
import ua.nastka_khmelovska.models.Post;
import ua.nastka_khmelovska.models.Supplier;

public class Main extends Application {
	
	//
	public static ConnectionUtil util = new ConnectionUtil();
	//
	
	
	GridPane root = new GridPane();
	Scene scene = new Scene(root, 250, 200);
	Stage primaryStage = new Stage();

	public static void main(String[] args) throws SQLException {
	
		launch(args);
		

	
		//util.getAllPosts();
		
	}
	

	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primarySrage) throws Exception {
		
		
		
		primaryStage.setTitle("Nastka-Coffee-House");
		primaryStage.setScene(scene);
		root.setStyle("-fx-background-color: lightpink;");
		primaryStage.show();
		
		//настройки корня
		root.setAlignment(Pos.TOP_LEFT);
		root.setHgap(1);
		root.setVgap(1);
		root.setPadding(new Insets (10,10,10,10));
		
		Text title = new Text("Hello in Nastka-Coffee-House!");
		title.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 16));
		root.add(title, 1, 1);
		
		Text tLogin = new Text("Please enter your login:");
		title.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 16));
		root.add(tLogin, 1, 2);
		
		final TextField login = new TextField();
		login.setPromptText("Login");
		root.add(login, 1, 3);
		
		Text tPass = new Text("Please enter your password:");
		tPass.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 16));
		root.add(tPass, 1, 4);
		
		final PasswordField pass = new PasswordField();
		pass.setPromptText("Password");
		root.add(pass, 1, 5);
		
		Button submit = new Button();
		submit.setText("Sign In");
		root.add(submit, 1, 7);
		
		submit.setOnAction(new EventHandler<ActionEvent>(){
			// слушатель на кнопку Submit
			public void handle(ActionEvent arg0) {
				String iLogin = new String();
				iLogin = login.getText();  
				System.out.println(iLogin);
				
				String iPass = new String();
				iPass = pass.getText();
				System.out.println(iPass);
				
				try {
					if(((iLogin.equals(util.getNameOfUser()))&& (iPass.equals(util.getPassOfUser()))) || 
							(((iLogin.equals(util.getNameOfAdmin()))&& (iPass.equals(util.getPassOfAdmin()))))){
						final GridPane root = new GridPane();
						root.setPadding(new Insets (10,10,10,10));
						root.setStyle("-fx-background-color: lightpink;");
						Scene scene = new Scene(root, 700, 400);
						Stage primaryStage = new Stage();
						primaryStage.setTitle("Database");
						primaryStage.setScene(scene);
						primaryStage.show();
						
						Text allowsFunctions = new Text("Available functions:");
						allowsFunctions.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 16));
						root.add(allowsFunctions, 1, 1);
						
						Button view = new Button();
						view.setText("View tables");
						root.add(view, 1, 2);
						
						
						
						/*VIEW*/
						
						view.setOnAction(new EventHandler<ActionEvent>(){

							public void handle(ActionEvent arg0) {
								
								

								final ChoiceBox tables = new ChoiceBox(FXCollections.observableArrayList(
										"Employees", "Post", "Meal", "Filiations", "Supplier", "Clients"));
								tables.setTooltip(new Tooltip("Choose the table"));
								root.add(tables, 2, 1);
								
								tables.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){

									public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
										System.out.println(newValue);
										if (newValue.intValue() == 0){
											//employees
											TableView table = new TableView();
											table.setEditable(true);
											TableColumn idEmployees = new TableColumn("idEmployees");
											TableColumn idPost = new TableColumn("idPost");
											TableColumn idFiliations = new TableColumn("idFiliations");
											TableColumn name = new TableColumn("name");
											TableColumn surname = new TableColumn("surname");
											TableColumn age = new TableColumn("age");
											TableColumn phone = new TableColumn("phone");
											TableColumn date_of_hiring = new TableColumn("date_of_hiring");
											
											table.getColumns().addAll(idEmployees, idPost, idFiliations,  name, surname, age, phone, date_of_hiring);
											root.add(table, 2, 2);
											
											ObservableList<Employees> employees = FXCollections.observableArrayList();
											
											idEmployees.setCellValueFactory(
													new PropertyValueFactory<Employees, Long>("idEmployees")
													);
											idPost.setCellValueFactory(
													new PropertyValueFactory<Employees, Long>("idPost")
													);
											idFiliations.setCellValueFactory(
													new PropertyValueFactory<Employees, Long>("idFiliations")
													);
											name.setCellValueFactory(
													new PropertyValueFactory<Employees, String>("name")
													);
											surname.setCellValueFactory(
													new PropertyValueFactory<Employees, String>("surname")
													);
											age.setCellValueFactory(
													new PropertyValueFactory<Employees, Integer>("age")
													);
											phone.setCellValueFactory(
													new PropertyValueFactory<Employees, Integer>("phone")
													);
											date_of_hiring.setCellValueFactory(
													new PropertyValueFactory<Employees, Date>("date_of_hiring")
													);
											table.setItems(employees);
											try {
												employees.addAll(util.getAllEmployees());
											} catch (SQLException e) {
												e.printStackTrace();
											}
											
											Button selectIdFiliations = new Button();
											selectIdFiliations.setText("Select everybody, who work in:");
											TextField sIdFiliations = new TextField();
											sIdFiliations.setPromptText("idFiliations");
											Button select = new Button();
											select.setText("Select");
											HBox hb = new HBox();
											hb.getChildren().addAll(selectIdFiliations, sIdFiliations, select);
											root.add(hb, 2, 3);
											
											select.setOnAction(new EventHandler<ActionEvent>(){

												public void handle(ActionEvent event) {
												
													try {
														employees.removeAll(employees);
														employees.addAll(util.selectEmployeesOfFiliations(
																Long.valueOf(sIdFiliations.getText())
																));
														sIdFiliations.clear();
													} catch (NumberFormatException e) {
														
														e.printStackTrace();
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
											
												}
									        	
									        });
								
											}
									
									  
										if (newValue.intValue() == 1){
											// post								
											TableView table = new TableView();
											table.setEditable(true);
											TableColumn idPost = new TableColumn("idPost");
											TableColumn name = new TableColumn("name");
											TableColumn salary = new TableColumn("salary");
											
											table.getColumns().addAll(idPost, name, salary);
											root.add(table, 2, 2);
											
											ObservableList<Post> post = FXCollections.observableArrayList();
											
											idPost.setCellValueFactory(
													new PropertyValueFactory<Post, Long>("idPost")
													);
											name.setCellValueFactory(
													new PropertyValueFactory<Post, String>("name")
													);
											salary.setCellValueFactory(
													new PropertyValueFactory<Post, Double>("salary")
													);
											table.setItems(post);
											try {
												post.addAll(util.getAllPost());
											} catch (SQLException e) {
												e.printStackTrace();
											}
											
											Button selectSalary = new Button();
											selectSalary.setText("Select everybody, whose salary is more, than:");
											TextField sSalary = new TextField();
											sSalary.setPromptText("Salary");
											Button select = new Button();
											select.setText("Select");
											HBox hb = new HBox();
											hb.getChildren().addAll(selectSalary, sSalary, select);
											root.add(hb, 2, 3);
											
											select.setOnAction(new EventHandler<ActionEvent>(){

												public void handle(ActionEvent event) {
												
													try {
														post.removeAll(post);
														post.addAll(util.selectPostOfSalary(
																Double.valueOf(sSalary.getText())
																));
														sSalary.clear();
													} catch (NumberFormatException e) {
														
														e.printStackTrace();
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
											
												}
									        	
									        });
										
										}
										if (newValue.intValue() == 2){
											//meal
											TableView table = new TableView();
											table.setEditable(true);
											TableColumn idMeal = new TableColumn("idMeal");
											TableColumn name = new TableColumn("name");
											TableColumn price = new TableColumn("price");
											TableColumn ammount = new TableColumn("ammount");
											
											table.getColumns().addAll(idMeal, name, price, ammount);
											root.add(table, 2, 2);
											
											ObservableList<Meal> meal = FXCollections.observableArrayList();
											
											idMeal.setCellValueFactory(
													new PropertyValueFactory<Meal, Long>("idMeal")
													);
											name.setCellValueFactory(
													new PropertyValueFactory<Meal, String>("name")
													);
											price.setCellValueFactory(
													new PropertyValueFactory<Meal, Double>("price")
													);
											ammount.setCellValueFactory(
													new PropertyValueFactory<Meal, Double>("ammount")
													);
											table.setItems(meal);
											try {
												meal.addAll(util.getAllMeal());
											} catch (SQLException e) {
												e.printStackTrace();
											}
											
											Button selectAmmount = new Button();
											selectAmmount.setText("Select everything, whose ammount is more, than:");
											TextField sAmmount = new TextField();
											sAmmount.setPromptText("Ammount");
											Button select = new Button();
											select.setText("Select");
											HBox hb = new HBox();
											hb.getChildren().addAll(selectAmmount, sAmmount, select);
											root.add(hb, 2, 3);
											
											select.setOnAction(new EventHandler<ActionEvent>(){

												public void handle(ActionEvent event) {
												
													try {
														meal.removeAll(meal);
														meal.addAll(util.selectMealOfAmmount(
																Double.valueOf(sAmmount.getText())
																));
														sAmmount.clear();
													} catch (NumberFormatException e) {
														
														e.printStackTrace();
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
											
												}
									        	
									        });

											
										}
										if(newValue.intValue() == 3){
											// filiations
											TableView table = new TableView();
											table.setEditable(true);
											TableColumn idFiliations = new TableColumn("idFiliations");
											TableColumn country = new TableColumn("country");
											TableColumn city = new TableColumn("city");
											TableColumn address = new TableColumn("address");
											TableColumn number_of_employees = new TableColumn("number_of_employees");
											TableColumn profit = new TableColumn("profit");
											
											table.getColumns().addAll(idFiliations, country, city, address, number_of_employees, profit);
											root.add(table, 2, 2);
											
											ObservableList<Filiations> filiations = FXCollections.observableArrayList();
											
											idFiliations.setCellValueFactory(
													new PropertyValueFactory<Filiations, Long>("idFiliations")
													);
											country.setCellValueFactory(
													new PropertyValueFactory<Filiations, String>("country")
													);
											city.setCellValueFactory(
													new PropertyValueFactory<Filiations, String>("city")
													);
											address.setCellValueFactory(
													new PropertyValueFactory<Filiations, String>("address")
													);
											number_of_employees.setCellValueFactory(
													new PropertyValueFactory<Filiations, Integer>("number_of_employees")
													);
											profit.setCellValueFactory(
													new PropertyValueFactory<Filiations, Double>("profit")
													);
											table.setItems(filiations);
											try {
												filiations.addAll(util.getAllFiliations());
											} catch (SQLException e) {
												e.printStackTrace();
											}
											
											Button selectCity = new Button();
											selectCity.setText("Select filiations, which are located in:");
											TextField sCity = new TextField();
											sCity.setPromptText("City");
											Button select = new Button();
											select.setText("Select");
											HBox hb = new HBox();
											hb.getChildren().addAll(selectCity, sCity, select);
											root.add(hb, 2, 3);
											
											select.setOnAction(new EventHandler<ActionEvent>(){

												public void handle(ActionEvent event) {
												
													try {
														filiations.removeAll(filiations);
														filiations.addAll(util.selectFiliationsOfCity(
																sCity.getText()
																));
														sCity.clear();
													} catch (NumberFormatException e) {
														
														e.printStackTrace();
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
											
												}
									        	
									        });
										}
										if(newValue.intValue() == 4){
											// supplier
											TableView table = new TableView();
											table.setEditable(true);
											TableColumn idSupplier = new TableColumn("idSupplier");
											TableColumn name = new TableColumn("name");
											TableColumn goods = new TableColumn("goods");
											TableColumn phone = new TableColumn("phone");
											TableColumn date_of_purchase = new TableColumn("date_of_purchase");
											TableColumn ammount = new TableColumn("ammount");
											
											table.getColumns().addAll(idSupplier, name, goods, phone, date_of_purchase, ammount);
											root.add(table, 2, 2);
											
											ObservableList<Supplier> supplier = FXCollections.observableArrayList();
											
											idSupplier.setCellValueFactory(
													new PropertyValueFactory<Supplier, Long>("idSupplier")
													);
											name.setCellValueFactory(
													new PropertyValueFactory<Supplier, String>("name")
													);
											goods.setCellValueFactory(
													new PropertyValueFactory<Supplier, String>("goods")
													);
											phone.setCellValueFactory(
													new PropertyValueFactory<Supplier, Integer>("phone")
													);
											date_of_purchase.setCellValueFactory(
													new PropertyValueFactory<Supplier, Date>("date_of_purchase")
													);
											ammount.setCellValueFactory(
													new PropertyValueFactory<Supplier, Double>("ammount")
													);
											table.setItems(supplier);
											try {
												supplier.addAll(util.getAllSupplier());
											} catch (SQLException e) {
												e.printStackTrace();
											}
											Button selectGoods = new Button();
											selectGoods.setText("Select suppliers, which supply:");
											TextField sGoods = new TextField();
											sGoods.setPromptText("Good");
											Button select = new Button();
											select.setText("Select");
											HBox hb = new HBox();
											hb.getChildren().addAll(selectGoods, sGoods, select);
											root.add(hb, 2, 3);
											
											select.setOnAction(new EventHandler<ActionEvent>(){

												public void handle(ActionEvent event) {
												
													try {
														supplier.removeAll(supplier);
														supplier.addAll(util.selectSupplierOfGoods(
																sGoods.getText()
																));
														sGoods.clear();
													} catch (NumberFormatException e) {
														
														e.printStackTrace();
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
											
												}
									        	
									        });

										}
										if(newValue.intValue() == 5){
											// clients
											TableView table = new TableView();
											table.setEditable(true);
											TableColumn idClients = new TableColumn("idClients");
											TableColumn idFavourite_meal = new TableColumn("idFavourite_Meal");
											TableColumn name = new TableColumn("name");
											TableColumn surname = new TableColumn("surname");
											TableColumn date_of_birth = new TableColumn("date_of_birth");
											TableColumn phone = new TableColumn("phone");
											TableColumn discount = new TableColumn("discount");
											
											table.getColumns().addAll(idClients, idFavourite_meal, name, surname, date_of_birth, phone, discount);
											root.add(table, 2, 2);
											
											ObservableList<Clients> clients = FXCollections.observableArrayList();
											
											idClients.setCellValueFactory(
													new PropertyValueFactory<Clients, Long>("idClients")
													);
											idFavourite_meal.setCellValueFactory(
													new PropertyValueFactory<Clients, Long>("idFavourite_meal")
													);
											name.setCellValueFactory(
													new PropertyValueFactory<Clients, String>("name")
													);
											surname.setCellValueFactory(
													new PropertyValueFactory<Clients, String>("surname")
													);
											date_of_birth.setCellValueFactory(
													new PropertyValueFactory<Clients, Date>("date_of_birth")
													);
											phone.setCellValueFactory(
													new PropertyValueFactory<Clients, Integer>("phone")
													);
											discount.setCellValueFactory(
													new PropertyValueFactory<Clients, Double>("discount")
													);
											table.setItems(clients);
											try {
												clients.addAll(util.getAllClients());
											} catch (SQLException e) {
												e.printStackTrace();
											}
											
											Button selectFavourite_meal = new Button();
											selectFavourite_meal.setText("Select clients, which like:");
											TextField sFavourite_meal = new TextField();
											sFavourite_meal.setPromptText("Favourite_meal");
											Button select = new Button();
											select.setText("Select");
											HBox hb = new HBox();
											hb.getChildren().addAll(selectFavourite_meal, sFavourite_meal, select);
											root.add(hb, 2, 3);
											
											select.setOnAction(new EventHandler<ActionEvent>(){

												public void handle(ActionEvent event) {
												
													try {
														clients.removeAll(clients);
														clients.addAll(util.selectClientsOfFavourite_meal(
																Long.valueOf(sFavourite_meal.getText())
																));
														sFavourite_meal.clear();
													} catch (NumberFormatException e) {
														
														e.printStackTrace();
													} catch (SQLException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
											
												}
									        	
									        });
										}
									}
									
								});
							}
							
						});
						
						
						
						
						try {
							String aPass = new String(util.getPassOfAdmin());
							if (iPass.equals(aPass)) {
								Button update = new Button();
								update.setText("Update tables");
								root.add(update, 1, 3);
								
								/*UPDATE*/
								
								update.setOnAction(new EventHandler<ActionEvent>(){

									public void handle(ActionEvent arg0) {
										
										

										final ChoiceBox tables = new ChoiceBox(FXCollections.observableArrayList(
												"Employees", "Post", "Meal", "Filiations", "Supplier", "Clients"));
										tables.setTooltip(new Tooltip("Choose the table"));
										root.add(tables, 2, 1);
										
										tables.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){

											public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
												System.out.println(newValue);
												if (newValue.intValue() == 0){
													//employees
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idEmployees = new TableColumn("idEmployees");
													TableColumn idPost = new TableColumn("idPost");
													TableColumn idFiliations = new TableColumn("idFiliations");
													TableColumn name = new TableColumn("name");
													TableColumn surname = new TableColumn("surname");
													TableColumn age = new TableColumn("age");
													TableColumn phone = new TableColumn("phone");
													TableColumn date_of_hiring = new TableColumn("date_of_hiring");
													
													table.getColumns().addAll(idEmployees, idPost, idFiliations,  name, surname, age, phone, date_of_hiring);
													root.add(table, 2, 2);
													
													ObservableList<Employees> employees = FXCollections.observableArrayList();
													
													idEmployees.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idEmployees")
															);
													idPost.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idPost")
															);
													idFiliations.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idFiliations")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Employees, String>("name")
															);
													surname.setCellValueFactory(
															new PropertyValueFactory<Employees, String>("surname")
															);
													age.setCellValueFactory(
															new PropertyValueFactory<Employees, Integer>("age")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Employees, Integer>("phone")
															);
													date_of_hiring.setCellValueFactory(
															new PropertyValueFactory<Employees, Date>("date_of_hiring")
															);
													table.setItems(employees);
													try {
														employees.addAll(util.getAllEmployees());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectIdEmployees = new TextField();
													selectIdEmployees.setPromptText("Select id");
													selectIdEmployees.setMaxWidth(idEmployees.getPrefWidth());
											        
											        TextField updateIdPost = new TextField();
											        updateIdPost.setPromptText("idPost");
											        updateIdPost.setMaxWidth(idPost.getPrefWidth());
											        
											        TextField updateIdFiliations = new TextField();
											        updateIdFiliations.setPromptText("idFiliations");
											        updateIdFiliations.setMaxWidth(idFiliations.getPrefWidth());
											        
											        TextField updateName = new TextField();
											        updateName.setPromptText("Name");
											        updateName.setMaxWidth(name.getPrefWidth());
											        
											        TextField updateSurname = new TextField();
											        updateSurname.setPromptText("Surname");
											        updateSurname.setMaxWidth(surname.getPrefWidth());
											        
											        TextField updateAge = new TextField();
											        updateAge.setPromptText("Age");
											        updateAge.setMaxWidth(age.getPrefWidth());
											        
											        TextField updatePhone = new TextField();
											        updatePhone.setPromptText("Phone");
											        updatePhone.setMaxWidth(phone.getPrefWidth());
											        
											        TextField updateDate_of_hiring = new TextField();
											        updateDate_of_hiring.setPromptText("Date_of_hiring");
											        updateDate_of_hiring.setMaxWidth(date_of_hiring.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(selectIdEmployees, updateIdPost, updateIdFiliations,
											        		updateName, updateSurname, 
											        		updateAge, updatePhone, updateDate_of_hiring);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button update = new Button();
											        update.setText("Update");
											        root.add(update, 2, 4);
											        
											        update.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.updateValuesIntoEmployees(
																		Long.valueOf(updateIdPost.getText()), 
																		Long.valueOf(updateIdFiliations.getText()), 
																		updateName.getText(), 
																		updateSurname.getText(), 
																		Integer.valueOf(updateAge.getText()), 
																		Integer.valueOf(updatePhone.getText()), 
																		Date.valueOf(updateDate_of_hiring.getText()),
																		Long.valueOf(selectIdEmployees.getText())
																		);
																selectIdEmployees.clear();
																updateIdPost.clear();
																updateIdFiliations.clear();
																updateName.clear();
																updateSurname.clear();
																updateAge.clear();
																updatePhone.clear();
																updateDate_of_hiring.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
													
														}
											        	
											        });
										
													}
												
											  
												if (newValue.intValue() == 1){
													// post								
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idPost = new TableColumn("idPost");
													TableColumn name = new TableColumn("name");
													TableColumn salary = new TableColumn("salary");
													
													table.getColumns().addAll(idPost, name, salary);
													root.add(table, 2, 2);
													
													ObservableList<Post> post = FXCollections.observableArrayList();
													
													idPost.setCellValueFactory(
															new PropertyValueFactory<Post, Long>("idPost")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Post, String>("name")
															);
													salary.setCellValueFactory(
															new PropertyValueFactory<Post, Double>("salary")
															);
													table.setItems(post);
													try {
														post.addAll(util.getAllPost());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectIdPost = new TextField();
											        selectIdPost.setPromptText("Select id");
											        selectIdPost.setMaxWidth(idPost.getPrefWidth());
											        
											        TextField updateName = new TextField();
											        updateName.setPromptText("Name");
											        updateName.setMaxWidth(name.getPrefWidth());
											        
											        TextField updateSalary = new TextField();
											        updateSalary.setPromptText("Salary");
											        updateSalary.setMaxWidth(salary.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(selectIdPost, updateName, updateSalary);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button update = new Button();
											        update.setText("Update");
											        root.add(update, 2, 4);
											        
											        update.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.updateValuesIntoPost(
																		updateName.getText(),
																		Double.valueOf(updateSalary.getText()),
																		Long.valueOf(selectIdPost.getText())
																		);
																selectIdPost.clear();
																updateName.clear();
																updateSalary.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
														
														}
											        	
											        });
												

												
												}
												if (newValue.intValue() == 2){
													//meal
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idMeal = new TableColumn("idMeal");
													TableColumn name = new TableColumn("name");
													TableColumn price = new TableColumn("price");
													TableColumn ammount = new TableColumn("ammount");
													
													table.getColumns().addAll(idMeal, name, price, ammount);
													root.add(table, 2, 2);
													
													ObservableList<Meal> meal = FXCollections.observableArrayList();
													
													idMeal.setCellValueFactory(
															new PropertyValueFactory<Meal, Long>("idMeal")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Meal, String>("name")
															);
													price.setCellValueFactory(
															new PropertyValueFactory<Meal, Double>("price")
															);
													ammount.setCellValueFactory(
															new PropertyValueFactory<Meal, Double>("ammount")
															);
													table.setItems(meal);
													try {
														meal.addAll(util.getAllMeal());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectIdMeal = new TextField();
											        selectIdMeal.setPromptText("Select id");
											        selectIdMeal.setMaxWidth(idMeal.getPrefWidth());
											        
											        TextField updateName = new TextField();
											        updateName.setPromptText("Name");
											        updateName.setMaxWidth(name.getPrefWidth());
											        
											        TextField updatePrice = new TextField();
											        updatePrice.setPromptText("Price");
											        updatePrice.setMaxWidth(price.getPrefWidth());
											        
											        TextField updateAmmount = new TextField();
											        updateAmmount.setPromptText("Ammount");
											        updateAmmount.setMaxWidth(ammount.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(selectIdMeal, updateName, updatePrice, updateAmmount);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button update = new Button();
											        update.setText("Update");
											        root.add(update, 2, 4);
											        
											        update.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.updateValuesIntoMeal(
																		updateName.getText(),
																		Double.valueOf(updatePrice.getText()),
																		Double.valueOf(updateAmmount.getText()),
																		Long.valueOf(selectIdMeal.getText())
																		);
																selectIdMeal.clear();
																updateName.clear();
																updatePrice.clear();
																updateAmmount.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
															
														}
											        	
											        });
													
												}
												if(newValue.intValue() == 3){
													// filiations
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idFiliations = new TableColumn("idFiliations");
													TableColumn country = new TableColumn("country");
													TableColumn city = new TableColumn("city");
													TableColumn address = new TableColumn("address");
													TableColumn number_of_employees = new TableColumn("number_of_employees");
													TableColumn profit = new TableColumn("profit");
													
													table.getColumns().addAll(idFiliations, country, city, address, number_of_employees, profit);
													root.add(table, 2, 2);
													
													ObservableList<Filiations> filiations = FXCollections.observableArrayList();
													
													idFiliations.setCellValueFactory(
															new PropertyValueFactory<Filiations, Long>("idFiliations")
															);
													country.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("country")
															);
													city.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("city")
															);
													address.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("address")
															);
													number_of_employees.setCellValueFactory(
															new PropertyValueFactory<Filiations, Integer>("number_of_employees")
															);
													profit.setCellValueFactory(
															new PropertyValueFactory<Filiations, Double>("profit")
															);
													table.setItems(filiations);
													try {
														filiations.addAll(util.getAllFiliations());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectIdFiliations = new TextField();
											        selectIdFiliations.setPromptText("Select id");
											        selectIdFiliations.setMaxWidth(idFiliations.getPrefWidth());
											        
											        TextField updateCountry = new TextField();
											        updateCountry.setPromptText("Country");
											        updateCountry.setMaxWidth(country.getPrefWidth());
											        
											        TextField updateCity = new TextField();
											        updateCity.setPromptText("City");
											        updateCity.setMaxWidth(city.getPrefWidth());
											        
											        TextField updateAddress = new TextField();
											        updateAddress.setPromptText("Address");
											        updateAddress.setMaxWidth(address.getPrefWidth());
											        
											        TextField updateNumber_of_employees = new TextField();
											        updateNumber_of_employees.setPromptText("Number_of_employees");
											        updateNumber_of_employees.setMaxWidth(number_of_employees.getPrefWidth());
											        
											        TextField updateProfit = new TextField();
											        updateProfit.setPromptText("Profit");
											        updateProfit.setMaxWidth(profit.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(selectIdFiliations, updateCountry, updateCity, updateAddress,
											        		updateNumber_of_employees, updateProfit);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button update = new Button();
											        update.setText("Update");
											        root.add(update, 2, 4);
											        
											        update.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.updateValuesIntoFiliations(
																		updateCountry.getText(),
																		updateCity.getText(),
																		updateAddress.getText(),
																		Integer.valueOf(updateNumber_of_employees.getText()),
																		Double.valueOf(updateProfit.getText()),
																		Long.valueOf(selectIdFiliations.getText())
																		);
																selectIdFiliations.clear();
																updateCountry.clear();
																updateCity.clear();
																updateAddress.clear();
																updateNumber_of_employees.clear();
																updateProfit.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
															
														}
											        	
											        });

												}
												if(newValue.intValue() == 4){
													// supplier
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idSupplier = new TableColumn("idSupplier");
													TableColumn name = new TableColumn("name");
													TableColumn goods = new TableColumn("goods");
													TableColumn phone = new TableColumn("phone");
													TableColumn date_of_purchase = new TableColumn("date_of_purchase");
													TableColumn ammount = new TableColumn("ammount");
													
													table.getColumns().addAll(idSupplier, name, goods, phone, date_of_purchase, ammount);
													root.add(table, 2, 2);
													
													ObservableList<Supplier> supplier = FXCollections.observableArrayList();
													
													idSupplier.setCellValueFactory(
															new PropertyValueFactory<Supplier, Long>("idSupplier")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Supplier, String>("name")
															);
													goods.setCellValueFactory(
															new PropertyValueFactory<Supplier, String>("goods")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Supplier, Integer>("phone")
															);
													date_of_purchase.setCellValueFactory(
															new PropertyValueFactory<Supplier, Date>("date_of_purchase")
															);
													ammount.setCellValueFactory(
															new PropertyValueFactory<Supplier, Double>("ammount")
															);
													table.setItems(supplier);
													try {
														supplier.addAll(util.getAllSupplier());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectIdSupplier = new TextField();
											        selectIdSupplier.setPromptText("Select id");
											        selectIdSupplier.setMaxWidth(idSupplier.getPrefWidth());
											        
											        TextField updateName = new TextField();
											        updateName.setPromptText("Name");
											        updateName.setMaxWidth(name.getPrefWidth());
											        
											        TextField updateGoods = new TextField();
											        updateGoods.setPromptText("Goods");
											        updateGoods.setMaxWidth(goods.getPrefWidth());
											        
											        TextField updatePhone = new TextField();
											        updatePhone.setPromptText("Phone");
											        updatePhone.setMaxWidth(phone.getPrefWidth());
											        
											        TextField updateDate_of_purchase = new TextField();
											        updateDate_of_purchase.setPromptText("Date_of_purchase");
											        updateDate_of_purchase.setMaxWidth(date_of_purchase.getPrefWidth());
											        
											        TextField updateAmmount = new TextField();
											        updateAmmount.setPromptText("Ammount");
											        updateAmmount.setMaxWidth(ammount.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(selectIdSupplier, updateName, updateGoods, updatePhone,
											        		updateDate_of_purchase, updateAmmount);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button update = new Button();
											        update.setText("Update");
											        root.add(update, 2, 4);
											        
											        update.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.updateValuesIntoSupplier(
																		updateName.getText(),
																		updateGoods.getText(),
																		Integer.valueOf(updatePhone.getText()),
																		Date.valueOf(updateDate_of_purchase.getText()),
																		Double.valueOf(updateAmmount.getText()),
																		Long.valueOf(selectIdSupplier.getText())
																		);
																selectIdSupplier.clear();
																updateName.clear();
																updateGoods.clear();
																updatePhone.clear();
																updateDate_of_purchase.clear();
																updateAmmount.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
															
														}
											        	
											        });

												}
												if(newValue.intValue() == 5){
													// clients
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idClients = new TableColumn("idClients");
													TableColumn idFavourite_meal = new TableColumn("idFavourite_Meal");
													TableColumn name = new TableColumn("name");
													TableColumn surname = new TableColumn("surname");
													TableColumn date_of_birth = new TableColumn("date_of_birth");
													TableColumn phone = new TableColumn("phone");
													TableColumn discount = new TableColumn("discount");
													
													table.getColumns().addAll(idClients, idFavourite_meal, name, surname, date_of_birth, phone, discount);
													root.add(table, 2, 2);
													
													ObservableList<Clients> clients = FXCollections.observableArrayList();
													
													idClients.setCellValueFactory(
															new PropertyValueFactory<Clients, Long>("idClients")
															);
													idFavourite_meal.setCellValueFactory(
															new PropertyValueFactory<Clients, Long>("idFavourite_meal")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Clients, String>("name")
															);
													surname.setCellValueFactory(
															new PropertyValueFactory<Clients, String>("surname")
															);
													date_of_birth.setCellValueFactory(
															new PropertyValueFactory<Clients, Date>("date_of_birth")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Clients, Integer>("phone")
															);
													discount.setCellValueFactory(
															new PropertyValueFactory<Clients, Double>("discount")
															);
													table.setItems(clients);
													try {
														clients.addAll(util.getAllClients());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectIdClients = new TextField();
													selectIdClients.setPromptText("Select id");
													selectIdClients.setMaxWidth(idClients.getPrefWidth());
											        
											        TextField updateIdFavourite_meal = new TextField();
											        updateIdFavourite_meal.setPromptText("idFavourite_meal");
											        updateIdFavourite_meal.setMaxWidth(idFavourite_meal.getPrefWidth());
											        
											        TextField updateName = new TextField();
											        updateName.setPromptText("Name");
											        updateName.setMaxWidth(name.getPrefWidth());
											        
											        TextField updateSurname = new TextField();
											        updateSurname.setPromptText("Surname");
											        updateSurname.setMaxWidth(surname.getPrefWidth());
											        
											        TextField updateDate_of_birth = new TextField();
											        updateDate_of_birth.setPromptText("Date_of_birth");
											        updateDate_of_birth.setMaxWidth(date_of_birth.getPrefWidth());
											        
											        TextField updatePhone = new TextField();
											        updatePhone.setPromptText("Phone");
											        updatePhone.setMaxWidth(phone.getPrefWidth());
											        
											        TextField updateDiscount = new TextField();
											        updateDiscount.setPromptText("Discount");
											        updateDiscount.setMaxWidth(discount.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(selectIdClients, updateIdFavourite_meal,
											        		updateName, updateSurname, updateDate_of_birth, updatePhone,
											        		updateDiscount);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button update = new Button();
											        update.setText("Update");
											        root.add(update, 2, 4);
											        
											        update.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.updateValuesIntoClients(
																		Long.valueOf(updateIdFavourite_meal.getText()),
																		updateName.getText(),
																		updateSurname.getText(),
																		Date.valueOf(updateDate_of_birth.getText()),
																		Integer.valueOf(updatePhone.getText()),
																		Double.valueOf(updateDiscount.getText()),
																		Long.valueOf(selectIdClients.getText())
																		);
																selectIdClients.clear();
																updateIdFavourite_meal.clear();
																updateName.clear();
																updateSurname.clear();
																updateDate_of_birth.clear();
																updatePhone.clear();
																updateDiscount.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
															
														}
											        	
											        });

												}
											}
											
										});
									}
									
								});

								
								
								Button delete = new Button();
								delete.setText("Delete tables");
								root.add(delete, 1, 4);
								
								/*DELETE*/
								
								delete.setOnAction(new EventHandler<ActionEvent>(){

									public void handle(ActionEvent arg0) {
										
										

										final ChoiceBox tables = new ChoiceBox(FXCollections.observableArrayList(
												"Employees", "Post", "Meal", "Filiations", "Supplier", "Clients"));
										tables.setTooltip(new Tooltip("Choose the table"));
										root.add(tables, 2, 1);
										
										tables.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){

											public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
												System.out.println(newValue);
												if (newValue.intValue() == 0){
													//employees
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idEmployees = new TableColumn("idEmployees");
													TableColumn idPost = new TableColumn("idPost");
													TableColumn idFiliations = new TableColumn("idFiliations");
													TableColumn name = new TableColumn("name");
													TableColumn surname = new TableColumn("surname");
													TableColumn age = new TableColumn("age");
													TableColumn phone = new TableColumn("phone");
													TableColumn date_of_hiring = new TableColumn("date_of_hiring");
													
													table.getColumns().addAll(idEmployees, idPost, idFiliations,  name, surname, age, phone, date_of_hiring);
													root.add(table, 2, 2);
													
													ObservableList<Employees> employees = FXCollections.observableArrayList();
													
													idEmployees.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idEmployees")
															);
													idPost.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idPost")
															);
													idFiliations.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idFiliations")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Employees, String>("name")
															);
													surname.setCellValueFactory(
															new PropertyValueFactory<Employees, String>("surname")
															);
													age.setCellValueFactory(
															new PropertyValueFactory<Employees, Integer>("age")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Employees, Integer>("phone")
															);
													date_of_hiring.setCellValueFactory(
															new PropertyValueFactory<Employees, Date>("date_of_hiring")
															);
													table.setItems(employees);
													try {
														employees.addAll(util.getAllEmployees());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectId = new TextField();
													selectId.setPromptText("Select id");
													root.add(selectId, 2, 3);
													
													Button delete = new Button();
													delete.setText("Delete");
													root.add(delete, 2, 4);
													
													delete.setOnAction(new EventHandler<ActionEvent>(){

															public void handle(ActionEvent event) {
																
																
																try {
																	util.deleteValuesFromEmployees(
																			Long.valueOf(selectId.getText())
																			);
																	selectId.clear();
																} catch (NumberFormatException e) {
																	
																	e.printStackTrace();
																} catch (SQLException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																
															}
												        	
												        });
										
													}
											
											  
												if (newValue.intValue() == 1){
													// post								
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idPost = new TableColumn("idPost");
													TableColumn name = new TableColumn("name");
													TableColumn salary = new TableColumn("salary");
													
													table.getColumns().addAll(idPost, name, salary);
													root.add(table, 2, 2);
													
													ObservableList<Post> post = FXCollections.observableArrayList();
													
													idPost.setCellValueFactory(
															new PropertyValueFactory<Post, Long>("idPost")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Post, String>("name")
															);
													salary.setCellValueFactory(
															new PropertyValueFactory<Post, Double>("salary")
															);
													table.setItems(post);
													try {
														post.addAll(util.getAllPost());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectId = new TextField();
													selectId.setPromptText("Select id");
													root.add(selectId, 2, 3);
													
													Button delete = new Button();
													delete.setText("Delete");
													root.add(delete, 2, 4);
													
													delete.setOnAction(new EventHandler<ActionEvent>(){

															public void handle(ActionEvent event) {
																
																
																try {
																	util.deleteValuesFromPost(
																			Long.valueOf(selectId.getText())
																			);
																	selectId.clear();
																} catch (NumberFormatException e) {
																	
																	e.printStackTrace();
																} catch (SQLException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																
															}
												        	
												        });
												
												}
												if (newValue.intValue() == 2){
													//meal
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idMeal = new TableColumn("idMeal");
													TableColumn name = new TableColumn("name");
													TableColumn price = new TableColumn("price");
													TableColumn ammount = new TableColumn("ammount");
													
													table.getColumns().addAll(idMeal, name, price, ammount);
													root.add(table, 2, 2);
													
													ObservableList<Meal> meal = FXCollections.observableArrayList();
													
													idMeal.setCellValueFactory(
															new PropertyValueFactory<Meal, Long>("idMeal")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Meal, String>("name")
															);
													price.setCellValueFactory(
															new PropertyValueFactory<Meal, Double>("price")
															);
													ammount.setCellValueFactory(
															new PropertyValueFactory<Meal, Double>("ammount")
															);
													table.setItems(meal);
													try {
														meal.addAll(util.getAllMeal());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectId = new TextField();
													selectId.setPromptText("Select id");
													root.add(selectId, 2, 3);
													
													Button delete = new Button();
													delete.setText("Delete");
													root.add(delete, 2, 4);
													
													delete.setOnAction(new EventHandler<ActionEvent>(){

															public void handle(ActionEvent event) {
																
																
																try {
																	util.deleteValuesFromMeal(
																			Long.valueOf(selectId.getText())
																			);
																	selectId.clear();
																} catch (NumberFormatException e) {
																	
																	e.printStackTrace();
																} catch (SQLException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																
															}
												        	
												        });
													
												}
												if(newValue.intValue() == 3){
													// filiations
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idFiliations = new TableColumn("idFiliations");
													TableColumn country = new TableColumn("country");
													TableColumn city = new TableColumn("city");
													TableColumn address = new TableColumn("address");
													TableColumn number_of_employees = new TableColumn("number_of_employees");
													TableColumn profit = new TableColumn("profit");
													
													table.getColumns().addAll(idFiliations, country, city, address, number_of_employees, profit);
													root.add(table, 2, 2);
													
													ObservableList<Filiations> filiations = FXCollections.observableArrayList();
													
													idFiliations.setCellValueFactory(
															new PropertyValueFactory<Filiations, Long>("idFiliations")
															);
													country.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("country")
															);
													city.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("city")
															);
													address.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("address")
															);
													number_of_employees.setCellValueFactory(
															new PropertyValueFactory<Filiations, Integer>("number_of_employees")
															);
													profit.setCellValueFactory(
															new PropertyValueFactory<Filiations, Double>("profit")
															);
													table.setItems(filiations);
													try {
														filiations.addAll(util.getAllFiliations());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField selectId = new TextField();
													selectId.setPromptText("Select id");
													root.add(selectId, 2, 3);
													
													Button delete = new Button();
													delete.setText("Delete");
													root.add(delete, 2, 4);
													
													delete.setOnAction(new EventHandler<ActionEvent>(){

															public void handle(ActionEvent event) {
																
																
																try {
																	util.deleteValuesFromFiliations(
																			Long.valueOf(selectId.getText())
																			);
																	selectId.clear();
																} catch (NumberFormatException e) {
																	
																	e.printStackTrace();
																} catch (SQLException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																
															}
												        	
												        });
												}
												if(newValue.intValue() == 4){
													// supplier
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idSupplier = new TableColumn("idSupplier");
													TableColumn name = new TableColumn("name");
													TableColumn goods = new TableColumn("goods");
													TableColumn phone = new TableColumn("phone");
													TableColumn date_of_purchase = new TableColumn("date_of_purchase");
													TableColumn ammount = new TableColumn("ammount");
													
													table.getColumns().addAll(idSupplier, name, goods, phone, date_of_purchase, ammount);
													root.add(table, 2, 2);
													
													ObservableList<Supplier> supplier = FXCollections.observableArrayList();
													
													idSupplier.setCellValueFactory(
															new PropertyValueFactory<Supplier, Long>("idSupplier")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Supplier, String>("name")
															);
													goods.setCellValueFactory(
															new PropertyValueFactory<Supplier, String>("goods")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Supplier, Integer>("phone")
															);
													date_of_purchase.setCellValueFactory(
															new PropertyValueFactory<Supplier, Date>("date_of_purchase")
															);
													ammount.setCellValueFactory(
															new PropertyValueFactory<Supplier, Double>("ammount")
															);
													table.setItems(supplier);
													try {
														supplier.addAll(util.getAllSupplier());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													TextField selectId = new TextField();
													selectId.setPromptText("Select id");
													root.add(selectId, 2, 3);
													
													Button delete = new Button();
													delete.setText("Delete");
													root.add(delete, 2, 4);
													
													delete.setOnAction(new EventHandler<ActionEvent>(){

															public void handle(ActionEvent event) {
																
																
																try {
																	util.deleteValuesFromSupplier(
																			Long.valueOf(selectId.getText())
																			);
																	selectId.clear();
																} catch (NumberFormatException e) {
																	
																	e.printStackTrace();
																} catch (SQLException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																
															}
												        	
												        });

												}
												if(newValue.intValue() == 5){
													// clients
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idClients = new TableColumn("idClients");
													TableColumn idFavourite_meal = new TableColumn("idFavourite_Meal");
													TableColumn name = new TableColumn("name");
													TableColumn surname = new TableColumn("surname");
													TableColumn date_of_birth = new TableColumn("date_of_birth");
													TableColumn phone = new TableColumn("phone");
													TableColumn discount = new TableColumn("discount");
													
													table.getColumns().addAll(idClients, idFavourite_meal, name, surname, date_of_birth, phone, discount);
													root.add(table, 2, 2);
													
													ObservableList<Clients> clients = FXCollections.observableArrayList();
													
													idClients.setCellValueFactory(
															new PropertyValueFactory<Clients, Long>("idClients")
															);
													idFavourite_meal.setCellValueFactory(
															new PropertyValueFactory<Clients, Long>("idFavourite_meal")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Clients, String>("name")
															);
													surname.setCellValueFactory(
															new PropertyValueFactory<Clients, String>("surname")
															);
													date_of_birth.setCellValueFactory(
															new PropertyValueFactory<Clients, Date>("date_of_birth")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Clients, Integer>("phone")
															);
													discount.setCellValueFactory(
															new PropertyValueFactory<Clients, Double>("discount")
															);
													table.setItems(clients);
													try {
														clients.addAll(util.getAllClients());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													TextField selectId = new TextField();
													selectId.setPromptText("Select id");
													root.add(selectId, 2, 3);
													
													Button delete = new Button();
													delete.setText("Delete");
													root.add(delete, 2, 4);
													
													delete.setOnAction(new EventHandler<ActionEvent>(){

															public void handle(ActionEvent event) {
																
																
																try {
																	util.deleteValuesFromClients(
																			Long.valueOf(selectId.getText())
																			);
																	selectId.clear();
																} catch (NumberFormatException e) {
																	
																	e.printStackTrace();
																} catch (SQLException e) {
																	// TODO Auto-generated catch block
																	e.printStackTrace();
																}
																
															}
												        	
												        });

												}
											}
											
										});
									}
									
								});

								
								Button insert = new Button();
								insert.setText("Insert into tables");
								root.add(insert, 1, 6);
								
								/*INSERT*/
								
								insert.setOnAction(new EventHandler<ActionEvent>(){

									public void handle(ActionEvent arg0) {
										
										

										final ChoiceBox tables = new ChoiceBox(FXCollections.observableArrayList(
												"Employees", "Post", "Meal", "Filiations", "Supplier", "Clients"));
										tables.setTooltip(new Tooltip("Choose the table"));
										root.add(tables, 2, 1);
										
										tables.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>(){

											public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
												System.out.println(newValue);
												if (newValue.intValue() == 0){
													//employees
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idEmployees = new TableColumn("idEmployees");
													TableColumn idPost = new TableColumn("idPost");
													TableColumn idFiliations = new TableColumn("idFiliations");
													TableColumn name = new TableColumn("name");
													TableColumn surname = new TableColumn("surname");
													TableColumn age = new TableColumn("age");
													TableColumn phone = new TableColumn("phone");
													TableColumn date_of_hiring = new TableColumn("date_of_hiring");
													
													table.getColumns().addAll(idEmployees, idPost, idFiliations,  name, surname, age, phone, date_of_hiring);
													root.add(table, 2, 2);
													
													ObservableList<Employees> employees = FXCollections.observableArrayList();
													
													idEmployees.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idEmployees")
															);
													idPost.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idPost")
															);
													idFiliations.setCellValueFactory(
															new PropertyValueFactory<Employees, Long>("idFiliations")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Employees, String>("name")
															);
													surname.setCellValueFactory(
															new PropertyValueFactory<Employees, String>("surname")
															);
													age.setCellValueFactory(
															new PropertyValueFactory<Employees, Integer>("age")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Employees, Integer>("phone")
															);
													date_of_hiring.setCellValueFactory(
															new PropertyValueFactory<Employees, Date>("date_of_hiring")
															);
													table.setItems(employees);
													try {
														employees.addAll(util.getAllEmployees());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													

													TextField addIdEmployees = new TextField();
											        addIdEmployees.setPromptText("idEmployees");
											        addIdEmployees.setMaxWidth(idEmployees.getPrefWidth());
											        
											        TextField addIdPost = new TextField();
											        addIdPost.setPromptText("idPost");
											        addIdPost.setMaxWidth(idPost.getPrefWidth());
											        
											        TextField addIdFiliations = new TextField();
											        addIdFiliations.setPromptText("idFiliations");
											        addIdFiliations.setMaxWidth(idFiliations.getPrefWidth());
											        
											        TextField addName = new TextField();
											        addName.setPromptText("Name");
											        addName.setMaxWidth(name.getPrefWidth());
											        
											        TextField addSurname = new TextField();
											        addSurname.setPromptText("Surname");
											        addSurname.setMaxWidth(surname.getPrefWidth());
											        
											        TextField addAge = new TextField();
											        addAge.setPromptText("Age");
											        addAge.setMaxWidth(age.getPrefWidth());
											        
											        TextField addPhone = new TextField();
											        addPhone.setPromptText("Phone");
											        addPhone.setMaxWidth(phone.getPrefWidth());
											        
											        TextField addDate_of_hiring = new TextField();
											        addDate_of_hiring.setPromptText("Date_of_hiring");
											        addDate_of_hiring.setMaxWidth(date_of_hiring.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(addIdEmployees, addIdPost, addIdFiliations, addName, addSurname, 
											        		addAge, addPhone, addDate_of_hiring);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button add = new Button();
											        add.setText("Add");
											        root.add(add, 2, 4);
											        
											        add.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.insertValuesIntoEmployees(
																		Long.valueOf(addIdEmployees.getText()), 
																		Long.valueOf(addIdPost.getText()), 
																		Long.valueOf(addIdFiliations.getText()), 
																		addName.getText(), 
																		addSurname.getText(), 
																		Integer.valueOf(addAge.getText()), 
																		Integer.valueOf(addPhone.getText()), 
																		Date.valueOf(addDate_of_hiring.getText()) 
																		);
																addIdEmployees.clear();
																addIdPost.clear();
																addIdFiliations.clear();
																addName.clear();
																addSurname.clear();
																addAge.clear();
																addPhone.clear();
																addDate_of_hiring.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
														
														}
											        	
											        });
											        
													}
											   
											        
											        
													
												
												if (newValue.intValue() == 1){
													// post								
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idPost = new TableColumn("idPost");
													TableColumn name = new TableColumn("name");
													TableColumn salary = new TableColumn("salary");
													
													table.getColumns().addAll(idPost, name, salary);
													root.add(table, 2, 2);
													
													ObservableList<Post> post = FXCollections.observableArrayList();
													
													idPost.setCellValueFactory(
															new PropertyValueFactory<Post, Long>("idPost")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Post, String>("name")
															);
													salary.setCellValueFactory(
															new PropertyValueFactory<Post, Double>("salary")
															);
													table.setItems(post);
													try {
														post.addAll(util.getAllPost());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													

													TextField addIdPost = new TextField();
											        addIdPost.setPromptText("idPost");
											        addIdPost.setMaxWidth(idPost.getPrefWidth());
											        
											        TextField addName = new TextField();
											        addName.setPromptText("Name");
											        addName.setMaxWidth(name.getPrefWidth());
											        
											        TextField addSalary = new TextField();
											        addSalary.setPromptText("Salary");
											        addSalary.setMaxWidth(salary.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(addIdPost, addName, addSalary);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button add = new Button();
											        add.setText("Add");
											        root.add(add, 2, 4);
											        
											        add.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.insertValuesIntoPost(
																		Long.valueOf(addIdPost.getText()),
																		addName.getText(),
																		Double.valueOf(addSalary.getText())
																		);
																addIdPost.clear();
																addName.clear();
																addSalary.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
												
														}
											        	
											        });
												
												}
												if (newValue.intValue() == 2){
													//meal
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idMeal = new TableColumn("idMeal");
													TableColumn name = new TableColumn("name");
													TableColumn price = new TableColumn("price");
													TableColumn ammount = new TableColumn("ammount");
													
													table.getColumns().addAll(idMeal, name, price, ammount);
													root.add(table, 2, 2);
													
													ObservableList<Meal> meal = FXCollections.observableArrayList();
													
													idMeal.setCellValueFactory(
															new PropertyValueFactory<Meal, Long>("idMeal")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Meal, String>("name")
															);
													price.setCellValueFactory(
															new PropertyValueFactory<Meal, Double>("price")
															);
													ammount.setCellValueFactory(
															new PropertyValueFactory<Meal, Double>("ammount")
															);
													table.setItems(meal);
													try {
														meal.addAll(util.getAllMeal());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField addIdMeal = new TextField();
											        addIdMeal.setPromptText("idMeal");
											        addIdMeal.setMaxWidth(idMeal.getPrefWidth());
											        
											        TextField addName = new TextField();
											        addName.setPromptText("Name");
											        addName.setMaxWidth(name.getPrefWidth());
											        
											        TextField addPrice = new TextField();
											        addPrice.setPromptText("Price");
											        addPrice.setMaxWidth(price.getPrefWidth());
											        
											        TextField addAmmount = new TextField();
											        addAmmount.setPromptText("Ammount");
											        addAmmount.setMaxWidth(ammount.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(addIdMeal, addName, addPrice, addAmmount);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button add = new Button();
											        add.setText("Add");
											        root.add(add, 2, 4);
											        
											        add.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.insertValuesIntoMeal(
																		Long.valueOf(addIdMeal.getText()),
																		addName.getText(),
																		Double.valueOf(addPrice.getText()),
																		Double.valueOf(addAmmount.getText())
																		);
																addIdMeal.clear();
																addName.clear();
																addPrice.clear();
																addAmmount.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
															
														}
											        	
											        });

													
												}
												if(newValue.intValue() == 3){
													// filiations
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idFiliations = new TableColumn("idFiliations");
													TableColumn country = new TableColumn("country");
													TableColumn city = new TableColumn("city");
													TableColumn address = new TableColumn("address");
													TableColumn number_of_employees = new TableColumn("number_of_employees");
													TableColumn profit = new TableColumn("profit");
													
													table.getColumns().addAll(idFiliations, country, city, address, number_of_employees, profit);
													root.add(table, 2, 2);
													
													ObservableList<Filiations> filiations = FXCollections.observableArrayList();
													
													idFiliations.setCellValueFactory(
															new PropertyValueFactory<Filiations, Long>("idFiliations")
															);
													country.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("country")
															);
													city.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("city")
															);
													address.setCellValueFactory(
															new PropertyValueFactory<Filiations, String>("address")
															);
													number_of_employees.setCellValueFactory(
															new PropertyValueFactory<Filiations, Integer>("number_of_employees")
															);
													profit.setCellValueFactory(
															new PropertyValueFactory<Filiations, Double>("profit")
															);
													table.setItems(filiations);
													try {
														filiations.addAll(util.getAllFiliations());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField addIdFiliations = new TextField();
											        addIdFiliations.setPromptText("idFiliations");
											        addIdFiliations.setMaxWidth(idFiliations.getPrefWidth());
											        
											        TextField addCountry = new TextField();
											        addCountry.setPromptText("Country");
											        addCountry.setMaxWidth(country.getPrefWidth());
											        
											        TextField addCity = new TextField();
											        addCity.setPromptText("City");
											        addCity.setMaxWidth(city.getPrefWidth());
											        
											        TextField addAddress = new TextField();
											        addAddress.setPromptText("Address");
											        addAddress.setMaxWidth(address.getPrefWidth());
											        
											        TextField addNumber_of_employees = new TextField();
											        addNumber_of_employees.setPromptText("Number_of_employees");
											        addNumber_of_employees.setMaxWidth(number_of_employees.getPrefWidth());
											        
											        TextField addProfit = new TextField();
											        addProfit.setPromptText("Profit");
											        addProfit.setMaxWidth(profit.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(addIdFiliations, addCountry, addCity, addAddress,
											        		addNumber_of_employees, addProfit);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button add = new Button();
											        add.setText("Add");
											        root.add(add, 2, 4);
											        
											        add.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.insertValuesIntoFiliations(
																		Long.valueOf(addIdFiliations.getText()),
																		addCountry.getText(),
																		addCity.getText(),
																		addAddress.getText(),
																		Integer.valueOf(addNumber_of_employees.getText()),
																		Double.valueOf(addProfit.getText())
																		);
																addIdFiliations.clear();
																addCountry.clear();
																addCity.clear();
																addAddress.clear();
																addNumber_of_employees.clear();
																addProfit.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
															
														}
											        	
											        });

													
													
												}
												if(newValue.intValue() == 4){
													// supplier
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idSupplier = new TableColumn("idSupplier");
													TableColumn name = new TableColumn("name");
													TableColumn goods = new TableColumn("goods");
													TableColumn phone = new TableColumn("phone");
													TableColumn date_of_purchase = new TableColumn("date_of_purchase");
													TableColumn ammount = new TableColumn("ammount");
													
													table.getColumns().addAll(idSupplier, name, goods, phone, date_of_purchase, ammount);
													root.add(table, 2, 2);
													
													ObservableList<Supplier> supplier = FXCollections.observableArrayList();
													
													idSupplier.setCellValueFactory(
															new PropertyValueFactory<Supplier, Long>("idSupplier")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Supplier, String>("name")
															);
													goods.setCellValueFactory(
															new PropertyValueFactory<Supplier, String>("goods")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Supplier, Integer>("phone")
															);
													date_of_purchase.setCellValueFactory(
															new PropertyValueFactory<Supplier, Date>("date_of_purchase")
															);
													ammount.setCellValueFactory(
															new PropertyValueFactory<Supplier, Double>("ammount")
															);
													table.setItems(supplier);
													try {
														supplier.addAll(util.getAllSupplier());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													TextField addIdSupplier = new TextField();
											        addIdSupplier.setPromptText("idSupplier");
											        addIdSupplier.setMaxWidth(idSupplier.getPrefWidth());
											        
											        TextField addName = new TextField();
											        addName.setPromptText("Name");
											        addName.setMaxWidth(name.getPrefWidth());
											        
											        TextField addGoods = new TextField();
											        addGoods.setPromptText("Goods");
											        addGoods.setMaxWidth(goods.getPrefWidth());
											        
											        TextField addPhone = new TextField();
											        addPhone.setPromptText("Phone");
											        addPhone.setMaxWidth(phone.getPrefWidth());
											        
											        TextField addDate_of_purchase = new TextField();
											        addDate_of_purchase.setPromptText("Date_of_purchase");
											        addDate_of_purchase.setMaxWidth(date_of_purchase.getPrefWidth());
											        
											        TextField addAmmount = new TextField();
											        addAmmount.setPromptText("Ammount");
											        addAmmount.setMaxWidth(ammount.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(addIdSupplier, addName, addGoods, addPhone,
											        		addDate_of_purchase, addAmmount);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button add = new Button();
											        add.setText("Add");
											        root.add(add, 2, 4);
											        
											        add.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.insertValuesIntoSupplier(
																		Long.valueOf(addIdSupplier.getText()),
																		addName.getText(),
																		addGoods.getText(),
																		Integer.valueOf(addPhone.getText()),
																		Date.valueOf(addDate_of_purchase.getText()),
																		Double.valueOf(addAmmount.getText())
																		);
																addIdSupplier.clear();
																addName.clear();
																addGoods.clear();
																addPhone.clear();
																addDate_of_purchase.clear();
																addAmmount.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
															
														}
											        	
											        });

													
													

												}
												if(newValue.intValue() == 5){
													// clients
													TableView table = new TableView();
													table.setEditable(true);
													TableColumn idClients = new TableColumn("idClients");
													TableColumn idFavourite_meal = new TableColumn("idFavourite_Meal");
													TableColumn name = new TableColumn("name");
													TableColumn surname = new TableColumn("surname");
													TableColumn date_of_birth = new TableColumn("date_of_birth");
													TableColumn phone = new TableColumn("phone");
													TableColumn discount = new TableColumn("discount");
													
													table.getColumns().addAll(idClients, idFavourite_meal, name, surname, date_of_birth, phone, discount);
													root.add(table, 2, 2);
													
													ObservableList<Clients> clients = FXCollections.observableArrayList();
													
													idClients.setCellValueFactory(
															new PropertyValueFactory<Clients, Long>("idClients")
															);
													idFavourite_meal.setCellValueFactory(
															new PropertyValueFactory<Clients, Long>("idFavourite_meal")
															);
													name.setCellValueFactory(
															new PropertyValueFactory<Clients, String>("name")
															);
													surname.setCellValueFactory(
															new PropertyValueFactory<Clients, String>("surname")
															);
													date_of_birth.setCellValueFactory(
															new PropertyValueFactory<Clients, Date>("date_of_birth")
															);
													phone.setCellValueFactory(
															new PropertyValueFactory<Clients, Integer>("phone")
															);
													discount.setCellValueFactory(
															new PropertyValueFactory<Clients, Double>("discount")
															);
													table.setItems(clients);
													try {
														clients.addAll(util.getAllClients());
													} catch (SQLException e) {
														e.printStackTrace();
													}
													
													TextField addIdClients = new TextField();
											        addIdClients.setPromptText("idClients");
											        addIdClients.setMaxWidth(idClients.getPrefWidth());
											        
											        TextField addIdFavourite_meal = new TextField();
											        addIdFavourite_meal.setPromptText("idFavourite_meal");
											        addIdFavourite_meal.setMaxWidth(idFavourite_meal.getPrefWidth());
											        
											        TextField addName = new TextField();
											        addName.setPromptText("Name");
											        addName.setMaxWidth(name.getPrefWidth());
											        
											        TextField addSurname = new TextField();
											        addSurname.setPromptText("Surname");
											        addSurname.setMaxWidth(surname.getPrefWidth());
											        
											        TextField addDate_of_birth = new TextField();
											        addDate_of_birth.setPromptText("Date_of_birth");
											        addDate_of_birth.setMaxWidth(date_of_birth.getPrefWidth());
											        
											        TextField addPhone = new TextField();
											        addPhone.setPromptText("Phone");
											        addPhone.setMaxWidth(phone.getPrefWidth());
											        
											        
											        TextField addDiscount = new TextField();
											        addDiscount.setPromptText("Discount");
											        addDiscount.setMaxWidth(discount.getPrefWidth());
											        
											        HBox hb = new HBox();
											        hb.getChildren().addAll(addIdClients, addIdFavourite_meal,
											        		addName, addSurname, addDate_of_birth, addPhone,
											        		 addDiscount);
											        hb.setSpacing(3);
											        root.add(hb, 2, 3);
											        
											        Button add = new Button();
											        add.setText("Add");
											        root.add(add, 2, 4);
											        
											        add.setOnAction(new EventHandler<ActionEvent>(){

														public void handle(ActionEvent event) {
															
															
															try {
																util.insertValuesIntoClients(
																		Long.valueOf(addIdClients.getText()),
																		Long.valueOf(addIdFavourite_meal.getText()),
																		addName.getText(),
																		addSurname.getText(),
																		Date.valueOf(addDate_of_birth.getText()),
																		Integer.valueOf(addPhone.getText()),
																		Double.valueOf(addDiscount.getText())
																		);
																addIdClients.clear();
																addIdFavourite_meal.clear();
																addName.clear();
																addSurname.clear();
																addDate_of_birth.clear();
																addPhone.clear();
																addDiscount.clear();
															} catch (NumberFormatException e) {
																
																e.printStackTrace();
															} catch (SQLException e) {
																
																e.printStackTrace();
															}
															
														}
											        	
											        });
												}
											}
											
										});
									}
									
								});
								
								
							}
						}

										
											catch (SQLException e) { 
							e.printStackTrace();
						}
					}
					else {
						
						
						GridPane root = new GridPane();
						root.setPadding(new Insets (10,10,10,10));
						root.setStyle("-fx-background-color: red;");
						Scene scene = new Scene(root, 250, 100);
						Stage primaryStage = new Stage();
						primaryStage.setTitle("Error");
						primaryStage.setScene(scene);
						primaryStage.show();
						
						Text error = new Text("Incorrect login or password");
						error.setFont(Font.font("Century Gothic", FontWeight.NORMAL, 16));
						root.add(error, 1, 1);
						
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	
			}
		});
		
		
	}

}
