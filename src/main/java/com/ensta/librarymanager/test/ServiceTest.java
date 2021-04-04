package com.ensta.librarymanager.test;

import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.service.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.dao.impl.*;
import com.ensta.librarymanager.service.impl.*;

import java.util.*; 
import java.time.*;
import java.lang.Exception;

public class ServiceTest {
	public static void main(String[] args){
		List<Livre> livres = new ArrayList<>();
		List<Membre> membres = new ArrayList<>();
		List<Emprunt> emprunts = new ArrayList<>();

		LivreService livreSer = LivreServiceImpl.getInstance();
		MembreService membreSer = MembreServiceImpl.getInstance();
		EmpruntService empruntSer = EmpruntServiceImpl.getInstance();

		try{
			livres = livreSer.getList();
			membres = membreSer.getList();
		}catch(Exception e){
			System.out.println(e);
		}

		System.out.println(livres.toString());
		System.out.println("");
		System.out.println(membres.toString());
	}
}