package it.prova.pizzastore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Utente;

/**
 * Servlet implementation class HomeRedirectServlet
 */
@WebServlet("/HomeRedirectServlet")
public class HomeRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente utenteInSession = (Utente)request.getSession().getAttribute("userInfo");
		
		if(utenteInSession.isFattorino()) {
			request.getRequestDispatcher("fattorino/index.jsp").forward(request, response);
			return;
		}
		if(utenteInSession.isAdmin()) {
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
			return;
		}
		if(utenteInSession.isPizzaiolo()) {
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}
	}

	
}
