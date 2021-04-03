package com.ensta.librarymanager.dao.impl;

import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.utils.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.*; 
import java.time.*;

public class EmpruntImpl implements EmpruntDao{
	private static EmpruntImpl instance;

	private EmpruntImpl(){}

	public static EmpruntImpl getInstance(){
		if(instance == null){
			instance = new EmpruntImpl();
		}
		return instance;
	}

	String SELECT_ALL = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre ORDER BY dateRetour DESC;";
	String GET_ALL = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL;";
	String GET_BYMEMBRE = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email,telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND membre.id = ?;";
	String GET_BYLIVRE = "SELECT e.id AS id, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE dateRetour IS NULL AND livre.id = ?;";
	String GET_BYID = "SELECT e.id AS idEmprunt, idMembre, nom, prenom, adresse, email, telephone, abonnement, idLivre, titre, auteur, isbn, dateEmprunt, dateRetour FROM emprunt AS e INNER JOIN membre ON membre.id = e.idMembre INNER JOIN livre ON livre.id = e.idLivre WHERE e.id = ?;";
	String CREATE = "INSERT INTO emprunt(idMembre, idLivre, dateEmprunt, dateRetour) VALUES (?, ?, ?, ?);";
	String UPDATE = "UPDATE emprunt SET idMembre = ?, idLivre = ?, dateEmprunt = ?, dateRetour = ? WHERE id = ?;";
	String COUNT = "SELECT COUNT(id) AS count FROM emprunt;"; 

	public List<Emprunt> getList() throws DaoException{
		List<Emprunt> result = new ArrayList<Emprunt>();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(SELECT_ALL)){
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Emprunt emprunt = new Emprunt();

				Membre membre = new Membre();
				membre.setId(rs.getInt("id"));
				membre.setNom(rs.getString("nom"));
				membre.setPrenom(rs.getString("prenom"));
				membre.setAdresse(rs.getString("adresse"));
				membre.setEmail(rs.getString("email"));
				membre.setTelephone(rs.getString("telephone"));
				String ab = rs.getString("abonnement");
				membre.setAbonnement(Abonnement.valueOf(ab));

				Livre livre = new Livre();
				livre.setId(rs.getInt("id"));
				livre.setTitre(rs.getString("titre"));
				livre.setAuteur(rs.getString("auteur"));
				livre.setIsbn(rs.getString("isbn"));

				emprunt.setId(rs.getInt("id"));
				emprunt.setIdMembre(membre);
				emprunt.setIdLivre(livre);
				emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toString());
				emprunt.setDateRetour(rs.getDate("dateRetour").toString());
				result.add(emprunt);
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public List<Emprunt> getListCurrent() throws DaoException{
		List<Emprunt> result = new ArrayList<Emprunt>();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(GET_ALL)){
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				if(rs.getDate("dateRetour") == null){
					Emprunt emprunt = new Emprunt();

					Membre membre = new Membre();
					membre.setId(rs.getInt("id"));
					membre.setNom(rs.getString("nom"));
					membre.setPrenom(rs.getString("prenom"));
					membre.setAdresse(rs.getString("adresse"));
					membre.setEmail(rs.getString("email"));
					membre.setTelephone(rs.getString("telephone"));
					String ab = rs.getString("abonnement");
					membre.setAbonnement(Abonnement.valueOf(ab));

					Livre livre = new Livre();
					livre.setId(rs.getInt("id"));
					livre.setTitre(rs.getString("titre"));
					livre.setAuteur(rs.getString("auteur"));
					livre.setIsbn(rs.getString("isbn"));

					emprunt.setId(rs.getInt("id"));
					emprunt.setIdMembre(membre);
					emprunt.setIdLivre(livre);
					emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toString());
					emprunt.setDateRetour(null);
					result.add(emprunt);
				}
				
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public List<Emprunt> getListCurrentByMembre(int idMembre) throws DaoException{
		List<Emprunt> result = new ArrayList<Emprunt>();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(GET_BYMEMBRE)){
			st.setInt(1, idMembre);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				if(rs.getDate("dateRetour") == null){
					Emprunt emprunt = new Emprunt();

					Membre membre = new Membre();
					membre.setId(rs.getInt("id"));
					membre.setNom(rs.getString("nom"));
					membre.setPrenom(rs.getString("prenom"));
					membre.setAdresse(rs.getString("adresse"));
					membre.setEmail(rs.getString("email"));
					membre.setTelephone(rs.getString("telephone"));
					String ab = rs.getString("abonnement");
					membre.setAbonnement(Abonnement.valueOf(ab));

					Livre livre = new Livre();
					livre.setId(rs.getInt("id"));
					livre.setTitre(rs.getString("titre"));
					livre.setAuteur(rs.getString("auteur"));
					livre.setIsbn(rs.getString("isbn"));

					emprunt.setId(rs.getInt("id"));
					emprunt.setIdMembre(membre);
					emprunt.setIdLivre(livre);
					emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toString());
					emprunt.setDateRetour(null);
					result.add(emprunt);
				}
				
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public List<Emprunt> getListCurrentByLivre(int idLivre) throws DaoException{
		List<Emprunt> result = new ArrayList<Emprunt>();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(GET_BYLIVRE)){
			st.setInt(1, idLivre);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				if(rs.getDate("dateRetour") == null){
					Emprunt emprunt = new Emprunt();

					Membre membre = new Membre();
					membre.setId(rs.getInt("id"));
					membre.setNom(rs.getString("nom"));
					membre.setPrenom(rs.getString("prenom"));
					membre.setAdresse(rs.getString("adresse"));
					membre.setEmail(rs.getString("email"));
					membre.setTelephone(rs.getString("telephone"));
					String ab = rs.getString("abonnement");
					membre.setAbonnement(Abonnement.valueOf(ab));

					Livre livre = new Livre();
					livre.setId(rs.getInt("id"));
					livre.setTitre(rs.getString("titre"));
					livre.setAuteur(rs.getString("auteur"));
					livre.setIsbn(rs.getString("isbn"));

					emprunt.setId(rs.getInt("id"));
					emprunt.setIdMembre(membre);
					emprunt.setIdLivre(livre);
					emprunt.setDateEmprunt(rs.getDate("dateEmprunt").toString());
					emprunt.setDateRetour(null);
					result.add(emprunt);
				}
				
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public Emprunt getById(int id) throws DaoException{
		Emprunt result = new Emprunt();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(GET_BYID)){
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Membre membre = new Membre();
				membre.setId(rs.getInt("id"));
				membre.setNom(rs.getString("nom"));
				membre.setPrenom(rs.getString("prenom"));
				membre.setAdresse(rs.getString("adresse"));
				membre.setEmail(rs.getString("email"));
				membre.setTelephone(rs.getString("telephone"));
				String ab = rs.getString("abonnement");
				membre.setAbonnement(Abonnement.valueOf(ab));

				Livre livre = new Livre();
				livre.setId(rs.getInt("id"));
				livre.setTitre(rs.getString("titre"));
				livre.setAuteur(rs.getString("auteur"));
				livre.setIsbn(rs.getString("isbn"));

				result.setId(rs.getInt("id"));
				result.setIdMembre(membre);
				result.setIdLivre(livre);
				result.setDateEmprunt(rs.getDate("dateEmprunt").toString());
				result.setDateRetour(rs.getDate("dateRetour").toString());
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws DaoException{
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(CREATE)){
			st.setInt(1, idMembre);
			st.setInt(2, idLivre);
			st.setObject(3, dateEmprunt);
			st.executeUpdate();
		}catch(SQLException e){
			System.out.println(e);
		}
	}

	public void update(Emprunt emprunt) throws DaoException{
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(UPDATE)){
			st.setInt(1, emprunt.getIdMembre().getId());
			st.setInt(2, emprunt.getIdLivre().getId());
			st.setObject(3, emprunt.getDateEmprunt());
			st.setObject(4, emprunt.getDateRetour());
			st.setInt(5, emprunt.getId());
			st.executeUpdate();

			System.out.println("UPDATE: " + emprunt.toString());
		}catch(SQLException e){
			System.out.println(e);
		}
	}

	public int count() throws DaoException{
		int cnt = 0;
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(COUNT)){
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				cnt++;
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return cnt;
	}
}