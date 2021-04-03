package com.ensta.librarymanager.service.impl;

import com.ensta.librarymanager.service.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.dao.impl.*;
import com.ensta.librarymanager.utils.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.exception.*;

import java.time.*;
import java.lang.*;
import java.util.*;

public class EmpruntServiceImpl implements EmpruntService{
	private static EmpruntServiceImpl instance;

	private EmpruntServiceImpl(){}

	public static EmpruntServiceImpl getInstance(){
		if(instance == null){
			instance = new EmpruntServiceImpl();
		}
		return instance;
	}

	public List<Emprunt> getList() throws ServiceException{
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		try {
			emprunts = empruntDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	public List<Emprunt> getListCurrent() throws ServiceException{
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		try {
			emprunts = empruntDao.getListCurrent();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	public List<Emprunt> getListCurrentByMembre(int idMembre) throws ServiceException{
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		try {
			emprunts = empruntDao.getListCurrentByMembre(idMembre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	public List<Emprunt> getListCurrentByLivre(int idLivre) throws ServiceException{
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		try {
			emprunts = empruntDao.getListCurrentByLivre(idLivre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunts;
	}

	public Emprunt getById(int id) throws ServiceException{
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		Emprunt emprunt = new Emprunt();
		try {
			emprunt = empruntDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return emprunt;
	}

	public void create(int idMembre, int idLivre, LocalDate dateEmprunt) throws ServiceException{
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		try {
			empruntDao.create(idMembre, idLivre, dateEmprunt);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	public void returnBook(int id) throws ServiceException{
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		try {
			Emprunt e = getById(id);
			e.setDateRetour(LocalDate.now().toString());
			empruntDao.update(e);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	public int count() throws ServiceException{
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		int cnt =-1;
		try {
			cnt = empruntDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return cnt;
	}

	public boolean isLivreDispo(int idLivre) throws ServiceException{
		List<Emprunt> curr = getListCurrentByLivre(idLivre);
		if(curr == null){
			return true;
		}else{
			return false;
		}
	}

	public boolean isEmpruntPossible(Membre membre) throws ServiceException{
		List<Emprunt> curr = getListCurrentByMembre(membre.getId());
		int cnt = curr.size();
		int norm = 0;
		Abonnement ab = membre.getAbonnement();
		switch(ab){
			case BASIC :
				norm = 2;
				break;
			case PREMIUM :
				norm = 5;
				break;
			case VIP :
				norm = 20;
				break;
		}
		if(cnt < norm){
			return true;
		}else{
			return false;
		}
	}
}