package com.ensta.librarymanager.modele;

import java.time.LocalDate;

public class Emprunt{
	/**identifiant*/
	int id;
	/**membre concerne*/
	Membre idMembre;
	/**Livre concerné*/
	Livre idLivre;
	/**date d'emprunt*/
	static LocalDate dateEmprunt;
	/**date de retour*/
	static LocalDate dateRetour;

	public Emprunt(int id, Membre idMembre, Livre idLivre, String dateEmprunt1, String dateRetour1){
		this.id = id;
		this.idMembre = idMembre;
		this.idLivre = idLivre;
		this.dateEmprunt = LocalDate.parse(dateEmprunt1);
		this.dateRetour = LocalDate.parse(dateRetour1);
	}

	public Emprunt(){}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public Membre getIdMembre(){
		return idMembre;
	}

	public void setIdMembre(Membre idMembre){
		this.idMembre = idMembre;
	}

	public Livre getIdLivre(){
		return idLivre;
	}

	public void setIdLivre(Livre idLivre){
		this.idLivre = idLivre;
	}

	public LocalDate getDateEmprunt(){
		return dateEmprunt;
	}

	public void setDateEmprunt(String dateEmprunt1){
		this.dateEmprunt = LocalDate.parse(dateEmprunt1);
	}

	public LocalDate getDateRetour(){
		return dateRetour;
	}

	public void setDateRetour(String dateRetour1){
		this.dateRetour = LocalDate.parse(dateRetour1);
	}

	@Override
	public String toString(){
		return id + ", " + idMembre.toString() + ", " + idLivre.toString() + ", " + dateEmprunt.toString() + ", " + dateRetour.toString();
	}
}

