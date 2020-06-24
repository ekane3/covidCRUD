package application.controller;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jfoenix.controls.JFXButton;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import application.database.Database;
import application.modele.*;

public class AccueilController {
	private final ObservableList<Patient>patientData = FXCollections.observableArrayList();
	private final ObservableList<Patient>patientData1 = FXCollections.observableArrayList();
	Database db = new Database();
    @FXML
    private JFXButton butValider;
    
	@FXML
    private AnchorPane panelmenu;
	@FXML
	private AnchorPane panelContentMenu;

    @FXML
    private JFXButton butAccueil;

    @FXML
    private JFXButton but_List;

    @FXML
    private JFXButton butInscrire;

    @FXML
    private JFXButton butSupprimer;

    @FXML
    private JFXButton butAbout;

    @FXML
    private JFXButton button_Quit;
    
    @FXML
    private TableView<Patient> table1;

    @FXML
    private TableView<Patient> table2;
    
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
    private TableColumn<Patient, Integer> id1;
    @FXML
    private TableColumn<Patient,String> nom1;
    @FXML
    private TableColumn<Patient, String> prenom1;
    @FXML
    private TableColumn<Patient, Date> date_n1;
    @FXML
    private TableColumn<Patient, String> adr1;
    @FXML
    private TableColumn<Patient, String> tel1;
    @FXML
    private TableColumn<Patient, String> email1;
    @FXML
    private TableColumn<Patient, String> statut1;
	

    @FXML
    void initialize() throws SQLException {
    	
    	try {
    		buildTable1();
    		 buildTable2();
    		//Remplir le premier tableau
    		id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            date_n.setCellValueFactory(new PropertyValueFactory<>("date_nai"));
            adr.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            statut.setCellValueFactory(new PropertyValueFactory<>("statut"));
            tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
           
            //Remplir le deuxieme tableau
            id1.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom1.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenom1.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            date_n1.setCellValueFactory(new PropertyValueFactory<>("date_nai"));
            adr1.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            statut1.setCellValueFactory(new PropertyValueFactory<>("statut"));
            tel1.setCellValueFactory(new PropertyValueFactory<>("tel"));
            email1.setCellValueFactory(new PropertyValueFactory<>("email"));
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //Remplir le tableau 2
    public void buildTable2() throws SQLException {
        patientData.clear();//vider le tableau d'abord
      //lancer notre requete de selction des patients positifs
        ResultSet rst = db.querySelectAll("patient","statut","positif");
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
            table2.setItems(patientData);
            //table2.setItems(patientData);
        }
        rst.close();
      
    }
    
    //Remplir le tableau 1
    public void buildTable1() throws SQLException {
       //lancer notre requete de selction des patients négatifs
        ResultSet rs = db.querySelectAll("patient","statut","négatif");
        while (rs.next()) {//Construire un patient
            patientData1.add(new Patient(
            		rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"), 
                    rs.getString("adresse"), 
                    rs.getString("tel"), 
                    rs.getString("email"), 
                    rs.getString("statut"), 
                    rs.getDate("date_naissance")));
            table1.setItems(patientData1);
        }
        rs.close();
    }
    
    @FXML
    void backAccueil(MouseEvent event) throws IOException{
    	panelmenu.getChildren().setAll(panelContentMenu);
    }

    @FXML
    void doApropos(MouseEvent event) throws IOException{
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/vue/Apropos.fxml"));
        panelmenu.getChildren().setAll(pane);
    }

    @FXML
    void doInscrire(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/vue/InscrirePatient.fxml"));
        panelmenu.getChildren().setAll(pane);
    }

    @FXML
    void doList(MouseEvent event) throws IOException {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/vue/ListPatient.fxml"));
        panelmenu.getChildren().setAll(pane);
    }
    
    @FXML
    void doValider(MouseEvent event) throws IOException  {
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/vue/StatutPatient.fxml"));
        panelmenu.getChildren().setAll(pane);
    }

    @FXML
    void doQuitter(MouseEvent event) {
    	System.exit(0);
    }

    @FXML
    void doSupprimer(MouseEvent event) throws IOException{
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("/application/vue/SupprimPatient.fxml"));
        panelmenu.getChildren().setAll(pane);
    }

}
