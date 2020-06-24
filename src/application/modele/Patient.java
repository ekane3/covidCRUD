package application.modele;

import java.sql.Date;

public class Patient {

	private int id;
	private String nom;
	private String prenom;
	private String adresse;
	private String tel;
	private String email;
	private String statut;
	private Date date_nai;
	
	
	
	public Patient(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}
	
	public Patient(int id, String nom, String prenom, String adresse, String tel, String email, String statut,
			Date date_nai) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.tel = tel;
		this.email = email;
		this.statut = statut;
		this.date_nai = date_nai;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public Date getDate_nai() {
		return date_nai;
	}
	public void setDate_nai(Date date_nai) {
		this.date_nai = date_nai;
	}


	@Override
	public String toString() {
		return  nom  ;
	}
	
}
