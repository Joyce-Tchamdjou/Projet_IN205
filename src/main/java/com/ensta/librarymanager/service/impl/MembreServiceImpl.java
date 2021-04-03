package com.ensta.librarymanager.service.impl;

import com.ensta.librarymanager.service.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.dao.impl.*;
import com.ensta.librarymanager.utils.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.exception.*;

import java.lang.*;
import java.util.*;

public class MembreServiceImpl implements MembreService{
	private static MembreServiceImpl instance;

	private MembreServiceImpl(){}

	public static MembreServiceImpl getInstance(){
		if(instance == null){
			instance = new MembreServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Membre> getList() throws ServiceException{
		MembreDao membreDao = MembreImpl.getInstance();
		List<Membre> membres = new ArrayList<>();
		try {
			membres = membreDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membres;
	}

	@Override
	public List<Membre> getListMembreEmpruntPossible() throws ServiceException{
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		MembreDao membreDao = MembreImpl.getInstance();
		List<Membre> membres = new ArrayList<>();
		List<Membre> membresP = new ArrayList<>();
		try {
			membres = membreDao.getList();
			boolean bool;
			for(Membre m : membres){
				bool = empruntService.isEmpruntPossible(m);
				if(bool){
					membresP.add(m);
				}
			}
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membresP;
	}

	@Override
	public Membre getById(int id) throws ServiceException{
		MembreDao membreDao = MembreImpl.getInstance();
		Membre membre = new Membre();
		try {
			membre = membreDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return membre;
	}

	@Override
	public int create(String nom, String prenom, String adresse, String email, String telephone) throws ServiceException{
		MembreDao membreDao = MembreImpl.getInstance();
		int id = -1;
		if(nom == null || prenom == null){
			throw new ServiceException();
		}
		try {
			id = membreDao.create(nom.toUpperCase(), prenom, adresse, email, telephone);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return id;
	}

	@Override
	public void update(Membre membre) throws ServiceException{
		MembreDao membreDao = MembreImpl.getInstance();
		if(membre.getNom() == null || membre.getPrenom() == null){
			throw new ServiceException();
		}
		try {
			membreDao.update(membre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void delete(int id) throws ServiceException{
		MembreDao membreDao = MembreImpl.getInstance();
		try {
			membreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	public int count() throws ServiceException{
		MembreDao membreDao = MembreImpl.getInstance();
		int cnt = -1;
		try {
			cnt = membreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return cnt;
	}
}
