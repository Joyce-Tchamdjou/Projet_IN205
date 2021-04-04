package com.ensta.librarymanager.test;

import com.ensta.librarymanager.dao.*;
import com.ensta.librarymanager.modele.*;
import com.ensta.librarymanager.dao.impl.*;

import java.util.*; 
import java.time.*;
import java.lang.Exception;

public class DaoTest{
	public static void main(String[] args){

		List<Livre> livres = new ArrayList<>();
		List<Membre> membres = new ArrayList<>();
		List<Emprunt> emprunts = new ArrayList<>();

		List<Emprunt> empruntsI = new ArrayList<>();
		List<Emprunt> empruntsM2 = new ArrayList<>();

		LivreDao livreDao = LivreImpl.getInstance();
		MembreDao membreDao = MembreImpl.getInstance();
		EmpruntDao empruntDao = EmpruntImpl.getInstance();

		Livre livre1 = new Livre();
		Membre membre1 = new Membre();
		Emprunt emprunt1 = new Emprunt();

		int cntL=-1, cntM=-1, cntE=-1;
		int cntL1=-1, cntM1=-1, cntE1=-1;
		int newL = -1, newM = -1;

		/*try{
			livres = livreDao.getList();
			membres = membreDao.getList();
			emprunts = empruntDao.getList();
		}catch(Exception e){
			System.out.println(e);
		}*/

		try{
			cntL = livreDao.count();
			cntM = membreDao.count();
			cntE = empruntDao.count();
		}catch(Exception e){
			System.out.println(e);
		}

		/*try{
			livre1 = livreDao.getById(1);
			membre1 = membreDao.getById(1);
			emprunt1 = empruntDao.getById(1);
		}catch(Exception e){
			System.out.println(e);
		}*/

		/*try{
			newL = livreDao.create("Joya", "Noicy", "978-5633645657");
			newM = membreDao.create("Will", "Liam", "sannois", "isdhbcijb@dnjii.com", "0808778679");
		}catch(Exception e){
			System.out.println(e);
		}*/

		/*try{
			empruntDao.create(14, 13, LocalDate.parse("2019-04-22"));
		}catch(Exception e){
			System.out.println(e);
		}*/
		/*try{
			livreDao.delete(11);
			membreDao.delete(14);
		}catch(Exception e){
			System.out.println(e);
		}*/

		try{
			cntL1 = livreDao.count();
			cntM1 = membreDao.count();
			cntE1 = empruntDao.count();
		}catch(Exception e){
			System.out.println(e);
		}

		try{
			livres = livreDao.getList();
			membres = membreDao.getList();
			emprunts = empruntDao.getList();
		}catch(Exception e){
			System.out.println(e);
		}

		/*try{
			empruntsI = empruntDao.getListCurrent();
		}catch(Exception e){
			System.out.println(e);
		}*/

		try{
			empruntsM2 = empruntDao.getListCurrentByMembre(2);
		}catch(Exception e){
			System.out.println(e);
		}

		/*try{
			emprunt1 = empruntDao.getById(3);
		}catch(Exception e){
			System.out.println(e);
		}*/

		System.out.println(cntL + " Livres");
		System.out.println(cntL1 + " Livres");
		System.out.println(livres.toString());
		//System.out.println("livre 1 :" + livre1.toString());

		System.out.println("");
		System.out.println(cntM + " Membres");
		System.out.println(cntM1 + " Membres");
		System.out.println(membres.toString());
		//System.out.println("membre 1 :" + membre1.toString());

		System.out.println("");
		System.out.println(cntE + " Emprunts");
		System.out.println(cntE1 + " Emprunts");
		for(Emprunt e : emprunts){
			System.out.println(e.toString());
		}

		System.out.println("");
		System.out.println(cntE + " Emprunts M2");
		System.out.println(cntE1 + " Emprunts");
		for(Emprunt e : empruntsM2){
			System.out.println(e.toString());
		}


		//System.out.println(emprunts.toString());
		//System.out.println("emprunt 3 : " + emprunt1.toString());
	}
}