package com.ensta.librarymanager.modele;

public class Membre{
	/**identifiant*/
	private int id;
	/**nom du membre*/
	private String nom;
	/**prenom du membre*/
	private String prenom;
	/**adresse du membre*/
	private String adresse;
	/**adresse mail du membre*/
	private String email;
	/**telephone*/
	private String telephone;
	/**type d'abonnement*/
	private Abonnement abonnement;

	/**
	 *Constructeur valué de la classe
	 *@param id
	 *@param nom
	 *@param prenom
	 *@param adresse
	 *@param email
	 *@param telephone
	 *@param abonnement
	 */
	public Membre(int id, String nom, String prenom, String adresse, String email, String telephone, Abonnement abonnement){
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this. abonnement = abonnement;
	}

	public Membre(){}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getNom(){
		return nom;
	}

	public void setNom(String nom){
		this.nom = nom;
	}

	public String getPrenom(){
		return prenom;
	}

	public void setPrenom(String prenom){
		this.prenom = prenom;
	}

	public String getAdresse(){
		return adresse;
	}

	public void setAdresse(String adresse){
		this.adresse = adresse;
	}

	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getTelephone(){
		return telephone;
	}

	public void setTelephone(String telephone){
		this.telephone = telephone;
	}

	public Abonnement getAbonnement(){
		return abonnement;
	}

	public void setAbonnement(Abonnement abonnement){
		this.abonnement = abonnement;
	}

	@Override
	public String toString(){
		return id + ", " + nom + ", " + prenom + ", " + adresse + ", " + email + ", " + telephone + ", " + abonnement;
	}
}

