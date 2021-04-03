package com.ensta.librarymanager.service.impl;

import com.ensta.librarymanager.service.*;
import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.dao.impl.*;
import com.ensta.librarymanager.utils.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.exception.*;

import java.lang.*;
import java.util.*;

public class LivreServiceImpl implements LivreService{
	private static LivreServiceImpl instance;

	private LivreServiceImpl(){}

	public static LivreServiceImpl getInstance(){
		if(instance == null){
			instance = new LivreServiceImpl();
		}
		return instance;
	}

	@Override
	public List<Livre> getList() throws ServiceException{
		LivreDao livreDao = LivreImpl.getInstance();
		List<Livre> livres = new ArrayList<>();
		try {
			livres = livreDao.getList();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livres;
	}

	@Override
	public List<Livre> getListDispo() throws ServiceException{
		EmpruntService empruntService = EmpruntServiceImpl.getInstance();
		LivreDao livreDao = LivreImpl.getInstance();
		List<Livre> livres = new ArrayList<>();
		List<Livre> livresD = new ArrayList<>();
		try {
			livres = livreDao.getList();
			boolean bool;
			for(Livre l : livres){
				bool = empruntService.isLivreDispo(l.getId());
				if(bool){
					livresD.add(l);
				}
			}
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livresD;
	}

	@Override
	public Livre getById(int id) throws ServiceException{
		LivreDao livreDao = LivreImpl.getInstance();
		Livre livre = new Livre();
		try {
			livre = livreDao.getById(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return livre;
	}

	@Override
	public int create(String titre, String auteur, String isbn) throws ServiceException{
		LivreDao livreDao = LivreImpl.getInstance();
		int id = -1;
		if(titre == null){
			throw new ServiceException();
		}
		try {
			id = livreDao.create(titre, auteur, isbn);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return id;
	}

	@Override
	public void update(Livre livre) throws ServiceException{
		LivreDao livreDao = LivreImpl.getInstance();
		if(livre.getTitre() == null){
			throw new ServiceException();
		}
		try {
			livreDao.update(livre);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public void delete(int id) throws ServiceException{
		LivreDao livreDao = LivreImpl.getInstance();
		try {
			livreDao.delete(id);
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
	}

	@Override
	public int count() throws ServiceException{
		LivreDao livreDao = LivreImpl.getInstance();
		int cnt = -1;
		try {
			cnt = livreDao.count();
		} catch (DaoException e1) {
			System.out.println(e1.getMessage());			
		}
		return cnt;
	}
}