package it.prova.pizzastore.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Utente;

@WebServlet("/PrepareSearchServlet")
public class PrepareSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utente utenteInSession = (Utente) request.getSession().getAttribute("userInfo");

		if (utenteInSession.isFattorino()) {
			request.getRequestDispatcher("fattorino/search.jsp").forward(request, response);
			return;
		}
		if (utenteInSession.isAdmin()) {
			request.getRequestDispatcher("admin/search.jsp").forward(request, response);
			return;
		}
		if (utenteInSession.isPizzaiolo()) {
			request.getRequestDispatcher("pizzaiolo/search.jsp").forward(request, response);
			return;
		}
	}

}
