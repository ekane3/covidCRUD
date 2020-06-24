package application.controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.database.Database;
import application.modele.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListController {

	private final ObservableList<Patient>patientData = FXCollections.observableArrayList();
	Database db = new Database();
	  @FXML
	    private TableView<Patient> table1;
	    
	    @FXML
	    private TableColumn<Patient, Integer> id;
	    @FXML
	    private TableColumn<Patient,String> nom;
	    @FXML
	    private TableColumn<Patient, String> prenom;
	    @FXML
	    private TableColumn<Patient, Date> date_n;
	    @FXML
	    private TableColumn<Patient, String> adr;
	    @FXML
	    private TableColumn<Patient, String> tel;
	    @FXML
	    private TableColumn<Patient, String> email;
	    @FXML
	    private TableColumn<Patient, String> statut;

    @FXML
    void initialize() throws SQLException {
    	
    	try {
    		id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            date_n.setCellValueFactory(new PropertyValueFactory<>("date_nai"));
            adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            buildTable();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Remplir le tableau 
    public void buildTable() throws SQLException {
    	   patientData.clear();//vider le tableau d'abord
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
    	            table1.setItems(patientData);
    	        }
        rst.close();
    }
}
