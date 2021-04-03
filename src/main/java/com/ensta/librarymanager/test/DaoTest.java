package com.ensta.librarymanager.test;

import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.dao.impl.*;

import java.util.*; 
import java.lang.Exception;

public class DaoTest{
	public static void main(String[] args){
		System.out.println("yoooooooooo");
		LivreDao livreDao = LivreImpl.getInstance();
		MembreDao membreDao = MembreImpl.getInstance();
		EmpruntDao empruntDao = EmpruntImpl.getInstance();
		try{

			List<Livre> livres = livreDao.getList();
			List<Membre> membres = membreDao.getList();
			List<Emprunt> emprunts = empruntDao.getList();

			System.out.println("Livres");
			for(Livre l : livres){
				System.out.println(l.toString());
			}

			System.out.println("");
			System.out.println("Membres");
			for(Membre m : membres){
				System.out.println(m.toString());
			}
			
			System.out.println("");
			System.out.println("Emprunts");
			for(Emprunt e : emprunts){
				System.out.println(e.toString());
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}