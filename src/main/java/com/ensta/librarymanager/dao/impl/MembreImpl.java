package com.ensta.librarymanager.dao.impl;

import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.utils.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.*; 

public class MembreImpl implements MembreDao{
	private static MembreImpl instance;

	private MembreImpl(){}

	public static MembreImpl getInstance(){
		if(instance == null){
			instance = new MembreImpl();
		}
		return instance;
	}

	String SELECT_ALL ="SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre ORDER BY nom, prenom;";
	String GET = "SELECT id, nom, prenom, adresse, email, telephone, abonnement FROM membre WHERE id = ?;";
	String CREATE = "INSERT INTO membre(nom, prenom, adresse, email, telephone, abonnement) VALUES (?, ?, ?, ?, ?, ?);";
	String UPDATE = "UPDATE membre SET nom = ?, prenom = ?, adresse = ?, email = ?, telephone = ?, abonnement = ? WHERE id = ?;";
	String DELETE = "DELETE FROM membre WHERE id = ?;";
	String COUNT = "SELECT COUNT(id) AS count FROM membre;";

	public List<Membre> getList() throws DaoException{
		List<Membre> result = new ArrayList<Membre>();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(SELECT_ALL)){
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
				result.add(membre);
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public Membre getById(int id) throws DaoException{
		Membre result = new Membre();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(GET)){
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				result.setId(rs.getInt("id"));
				result.setNom(rs.getString("nom"));
				result.setPrenom(rs.getString("prenom"));
				result.setAdresse(rs.getString("adresse"));
				result.setEmail(rs.getString("email"));
				result.setTelephone(rs.getString("telephone"));
				String ab = rs.getString("abonnement");
				result.setAbonnement(Abonnement.valueOf(ab));
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public int create(String nom, String prenom, String adresse, String email, String telephone) throws DaoException{
		int id = -1;
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)){
			st.setString(1, nom);
			st.setString(2, prenom);
			st.setString(3, adresse);
			st.setString(4, email);
			st.setString(5, telephone);
			st.setString(6, "basic");
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return id;
	}

	public void update(Membre membre) throws DaoException{
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(UPDATE)){
			st.setString(1, membre.getNom());
			st.setString(2, membre.getPrenom());
			st.setString(3, membre.getAdresse());
			st.setString(4, membre.getEmail());
			st.setString(5, membre.getTelephone());
			st.setString(6, membre.getAbonnement().name());
			st.setInt(7, membre.getId());
			st.executeUpdate();

			System.out.println("UPDATE: " + membre.toString());
		}catch(SQLException e){
			System.out.println(e);
		}
	}

	public void delete(int id) throws DaoException{
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(DELETE)){
			st.setInt(1, id);
			st.executeUpdate();

			System.out.println("DELETE: " + id);
		}catch(SQLException e){
			System.out.println(e);
		}
	}

	public int count() throws DaoException{
		List<Membre> membres = new ArrayList<>();
		try{
			 membres = getList();
		}catch(DaoException e){
			System.out.println(e);
		}
		return membres.size();
	}
}
