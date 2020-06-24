package application.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

import application.database.Database;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InscrireController {

	@FXML 
	private TextField nom,prenom,adresse,tel,email;
	@FXML
	private DatePicker date_n;
	@FXML
	private Button button_inscrire;
	@FXML
	private Button button_supprimer;
	
	Database db = new Database();
	
	@FXML
	void doAjouter() throws ClassNotFoundException, SQLException {
		System.out.println("Enregistrer");
		System.out.println(""+date_n.getValue());
		String statut = "négatif";
		
	     Class.forName("com.mysql.jdbc.Driver");    
		Connection		cn		=  DriverManager.getConnection("jdbc:mysql://localhost:3306/bd", "root", "");
		 PreparedStatement preparedStatement=null;
	     
		 ResultSet resultSet=null;
	         String sql = "INSERT INTO patient (nom, prenom, adresse,date_naissance, email,tel,statut) VALUES ( ?,?, ?,?, ?,?, ?)";
	         try {
	       preparedStatement=cn.prepareStatement(sql);
	       preparedStatement.setString(1, nom.getText());
	       preparedStatement.setString(2, prenom.getText());
	       preparedStatement.setString(3, adresse.getText());
	       java.util.Date date = java.util.Date.from(date_n.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
	       java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	       preparedStatement.setDate(4, sqlDate);
	       preparedStatement.setString(5, email.getText());
	       preparedStatement.setString(6, tel.getText());
	       preparedStatement.setString(7, statut);
	        preparedStatement.executeUpdate();
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Inscription patient");
	        alert.setContentText("Inscription du patient : "+ nom.getText() +" résussie !");
	        alert.show();
	        doEffacer();
	       
	         }catch (Exception e) {
				// TODO: handle exception
	             System.out.println(""+e.getMessage());
			}
	                
	         
	}
	
	@FXML
	void doEffacer() {
		nom.setText(null);
		prenom.setText(null);
		tel.setText(null);
		email.setText(null);
		adresse.setText(null);
		date_n.setValue(null);
	}
	
}
