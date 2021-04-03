package com.ensta.librarymanager.test;
import com.ensta.librarymanager.modele.*;

public class ModeleTest{
	public static void main(String[] args){
		int i = 1;
		Membre m = new Membre(i, "Joyce", "Pascale", "91120 Palaiseau", "bref@ndem.com", "0646352562", Abonnement.BASIC);
		Livre l = new Livre(i, "Le petit prince", "Saint Exupery", "kotechnique");
		Emprunt e = new Emprunt(i, m, l, "2020-01-03", "2020-03-09");
		System.out.println("Membre : " + m.toString());
		System.out.println("Livre : " + l.toString());
		System.out.println("Emprunt : " + e.toString());
	}
}