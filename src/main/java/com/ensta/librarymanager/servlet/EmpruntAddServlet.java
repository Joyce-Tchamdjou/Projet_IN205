package com.ensta.librarymanager.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ensta.librarymanager.exception.*;
import com.ensta.librarymanager.service.*;
import com.ensta.librarymanager.service..impl.*;

public class EmpruntAddServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getServletPath();
		switch(action){
			case "/new":
				showAddForm(request, response);
				break;
			case "/livreDispo":
				showLivreDispo(request, response);
				break;
			case "/membreDispo":
				showMembreDispo(request, response);
				break;
			default:
				System.out.println("Default redirecting case from " + action + " !");
		}
	}

	private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);		
	}

	private void showLivreDispo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LivreService livreSer = LivreServiceImpl.getInstance();
		List<Livre> livresD = new ArrayList<>();

		try{
			livresD = livreSer.getListDispo();
		}catch(ServiceException e){
			System.out.println(e.getMessage());
		}
		request.setAttribute("livresD", livresD);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);	
	}

	private void showMembreDispo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		MembreService membreSer = MembreServiceImpl.getInstance();
		List<Membre> membresD = new ArrayList<>();

		try{
			membresD = membreSer.getListMembreEmpruntPossible();
		}catch(ServiceException e){
			System.out.println(e.getMessage());
		}
		request.setAttribute("membresD", membresD);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}