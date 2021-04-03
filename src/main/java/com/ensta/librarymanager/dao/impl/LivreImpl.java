package com.ensta.librarymanager.dao.impl;

import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.utils.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.exception.DaoException;
import com.ensta.librarymanager.persistence.ConnectionManager;

import java.sql.*;
import java.util.*; 

public class LivreImpl implements LivreDao{
	private static LivreImpl instance;

	private LivreImpl(){}

	public static LivreImpl getInstance(){
		if(instance == null){
			instance = new LivreImpl();
		}
		return instance;
	}

	String SELECT_ALL = "SELECT id, titre, auteur, isbn FROM livre;";
	String GET = "SELECT id, titre, auteur, isbn FROM livre WHERE id = ?;";
	String CREATE = "INSERT INTO livre(titre, auteur, isbn) VALUES (?, ?, ?);";
	String UPDATE = "UPDATE livre SET titre = ?, auteur = ?, isbn = ? WHERE id = ?;";
	String DELETE = "DELETE FROM livre WHERE id = ?;";
	String COUNT = "SELECT COUNT(id) AS count FROM livre;";


	public List<Livre> getList() throws DaoException{
		List<Livre> result = new ArrayList<Livre>();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(SELECT_ALL)){
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				Livre livre = new Livre();
				livre.setId(rs.getInt("id"));
				livre.setTitre(rs.getString("titre"));
				livre.setAuteur(rs.getString("auteur"));
				livre.setIsbn(rs.getString("isbn"));
				result.add(livre);
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public Livre getById(int id) throws DaoException{
		Livre result = new Livre();
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(GET)){
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				result.setId(rs.getInt("id"));
				result.setTitre(rs.getString("titre"));
				result.setAuteur(rs.getString("auteur"));
				result.setIsbn(rs.getString("isbn"));
			}
		}catch(SQLException e){
			System.out.println(e);
		}
		return result;
	}

	public int create(String titre, String auteur, String isbn) throws DaoException{
		int id = -1;
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)){
			st.setString(1, titre);
			st.setString(2, auteur);
			st.setString(3, isbn);
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

	public void update(Livre livre) throws DaoException{
		try(Connection con = ConnectionManager.getConnection();
			PreparedStatement st = con.prepareStatement(UPDATE)){
			st.setString(1, livre.getTitre());
			st.setString(2, livre.getAuteur());
			st.setString(3, livre.getIsbn());
			st.setInt(4, livre.getId());
			st.executeUpdate();

			System.out.println("UPDATE: " + livre.toString());
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