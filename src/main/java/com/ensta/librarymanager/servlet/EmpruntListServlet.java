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

public class EmpruntListServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getServletPath();
		switch(action){
			case "/list":
				showEmprunt(request, response);
				break;
			case "/listAll":
				showAll(request, response);
				break;
			default:
				System.out.println("Default redirecting case from " + action + " !");
		}
	}

	private void showEmprunt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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

	private void showAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		EmpruntService empruntSer = EmpruntServiceImpl.getInstance();
		List<Emprunt> emprunts = new ArrayList<>();
		try{
			emprunts = empruntSer.getList();
		}catch(ServiceException e){
			System.out.println(e);
		}
		request.setAttribute("emprunts", emprunts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/View/emprunt_add.jsp");
		dispatcher.forward(request, response);		
	}




}