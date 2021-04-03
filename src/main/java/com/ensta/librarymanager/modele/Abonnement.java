package com.ensta.librarymanager.modele;
import java.util.NoSuchElementException;

public enum Abonnement{
	BASIC("basic"),
	PREMIUM("premium"), 
	VIP("vip");

	private String label;

	Abonnement(String label){
		this.label = label;
	}
}