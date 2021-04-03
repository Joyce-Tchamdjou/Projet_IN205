package com.ensta.librarymanager.modele;

public class Livre{
	/**identifiant*/
	private int id;
	/**titre du livre*/
	private String titre;
	/**auteur du livre*/
	private String auteur;
	/**isbn du livre*/
	private String isbn;

	public Livre(int id, String titre, String auteur, String isbn){
		this.id = id;
		this.titre = titre;
		this.auteur = auteur;
		this.isbn = isbn;
	}

	public Livre(){}
	
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getTitre(){
		return titre;
	}

	public void setTitre(String titre){
		this.titre = titre;
	}

	public String getAuteur(){
		return auteur;
	}

	public void setAuteur(String auteur){
		this.auteur = auteur;
	}

	public String getIsbn(){
		return isbn;
	}

	public void setIsbn(String isbn){
		this.isbn = isbn;
	}

	@Override
	public String toString(){
		return id + ", " + titre + ", " + auteur + ", " + isbn;
	}
}