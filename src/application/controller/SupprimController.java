package application.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import application.database.Database;
import application.modele.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class SupprimController {

	private final ObservableList<Patient>patientData = FXCollections.observableArrayList();
	@FXML
	private TextField nom, prenom,adr,statut, tel, email;
	@FXML
	private DatePicker date_n;
	@FXML
	private Button button_inscrire;
	@FXML
	private Button button_supprimer;

	Database db = new Database();
	@FXML
	private ListView<Patient>  listSupprim;
	
	 @FXML
	private JFXButton button_suppri;
	 
	 private Patient courant;
	@FXML
	void initialize() throws SQLException {
		
		buildTable();
		listSupprim.setItems(patientData);
	
	}

    //Remplir le tableau 
    public void buildTable() throws SQLException {
    	   patientData.clear();//vider la liste
    	      //lancer notre requete de selction des patients positifs
    	        ResultSet rst = db.querySelectAll("patient");
    	        while (rst.next()) {//Construire un patient
    	        	patientData.add(new Patient(
    	            		rst.getInt("id"),
    	                    rst.getString("nom"),
    	                    rst.getString("prenom"), 
    	                    rst.getString("adresse"), 
    	                    rst.getString("tel"), 
    	                    rst.getString("email"), 
    	                    rst.getString("statut"), 
    	                    rst.getDate("date_naissance")));
    	        }
        rst.close();
    }
    
    @FXML
    void doSupprimer(MouseEvent event) throws ClassNotFoundException, SQLException {
    	System.out.println("Supprimer");
		
	     Class.forName("com.mysql.jdbc.Driver");    
		Connection		cn		=  DriverManager.getConnection("jdbc:mysql://localhost:3306/bd", "root", "");
		 PreparedStatement preparedStatement=null;
	     
		 ResultSet resultSet=null;
	         String sql = "DELETE FROM patient WHERE nom = ?";
	         try {
	       preparedStatement=cn.prepareStatement(sql);
	       preparedStatement.setString(1, nom.getText());
	        preparedStatement.executeUpdate();
	        Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("Suppresion réussie");
	        alert.setContentText("Suppresion du patient : "+ nom.getText() +" résussie !");
	        alert.show();
	         }catch (Exception e) {
				// TODO: handle exception
	             System.out.println(""+e.getMessage());
			}
    }
    
    
    @FXML
    public void onFillForm() {
    	if (listSupprim.getSelectionModel().getSelectedIndex() > -1) { // si on a selectionné un element dans la liste
    		System.out.println("select :"+listSupprim.getSelectionModel().getSelectedItem());
    		courant = listSupprim.getSelectionModel().getSelectedItem();
    		nom.setText(courant.getNom());
    		prenom.setText(courant.getPrenom());
    		adr.setText(courant.getAdresse());
    		email.setText(courant.getEmail());
    		tel.setText(courant.getTel());
    		statut.setText(courant.getStatut());
    		//date_n.setDate(courant.getDate_nai());
    	}
    	else System.out.println("Nothing selected");
    }
    
}
