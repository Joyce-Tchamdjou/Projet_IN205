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

public class DashBoardServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getServletPath();
		switch(action){
			case "/countL":
				countLivre(request, response);
				break;
			case "/countM":
				countMembre(request, response);
			case "/countE":
				countEmprunt(request, response);
			case "/emprunts":
				showEmprunts(request, response);
			default:
				System.out.println("Default redirecting case from " + action + " !");
		}
	}

	private void countLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		LivreService livreSer = LivreServiceImpl.getInstance();
		int cntL = -1;
		try{
			cntL = livreSer.count();
		}catch(ServiceException e){
			System.out.println(e.getMessage());
		}
		request.setAttribute("cntL", cntL);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);	
	}

	private void countMembre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		MembreService membreSer = MembreServiceImpl.getInstance();
		int cntM = -1;
		try{
			cntM = membreSer.count();
		}catch(ServiceException e){
			System.out.println(e.getMessage());
		}
		request.setAttribute("cntM", cntM);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);	
	}

	private void countEmprunt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		EmpruntService empruntSer = EmpruntServiceImpl.getInstance();
		int cntE = -1;
		try{
			cntE = empruntSer.count();
		}catch(ServiceException e){
			System.out.println(e.getMessage());
		}
		request.setAttribute("cntE", cntM);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);	
	}

	private void showEmprunts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		EmpruntService empruntSer = EmpruntServiceImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		try{
			emprunts = empruntSer.getListCurrent();
		}catch(ServiceException e){
			System.out.println(e);
		}
		request.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);	
	}
}


