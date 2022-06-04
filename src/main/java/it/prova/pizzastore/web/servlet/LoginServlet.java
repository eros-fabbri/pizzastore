package it.prova.pizzastore.web.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.Utente;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");
		
		if(loginInput.equals("admin") && passwordInput.equals("admin")) {
			Utente utenteUserInfo = new Utente(loginInput, passwordInput, "Classic", "User");
			utenteUserInfo.getRuoli().add(new Ruolo(Ruolo.ADMIN_ROLE));
			request.getSession().setAttribute("userInfo", utenteUserInfo);
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}
		
		if(loginInput.equals("fattorino") && passwordInput.equals("fattorino")) {
			Utente utenteUserInfo = new Utente(loginInput, passwordInput, "Classic", "User");
			utenteUserInfo.getRuoli().add(new Ruolo(Ruolo.FATTORINO_ROLE));
			
			request.getSession().setAttribute("userInfo", utenteUserInfo);
			request.getRequestDispatcher("/fattorino/index.jsp").forward(request, response);
			return;
		}
		if(loginInput.equals("pizzaiolo") && passwordInput.equals("pizzaiolo")) {
			Utente utenteUserInfo = new Utente(loginInput, passwordInput, "Mario", "Pizza");
			utenteUserInfo.getRuoli().add(new Ruolo(Ruolo.PIZZAIOLO_ROLE));
			
			request.getSession().setAttribute("userInfo", utenteUserInfo);
			request.getRequestDispatcher("/pizzaiolo/index.jsp").forward(request, response);
			return;
		}

		request.setAttribute("messaggio", "Credenziali errate");
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

}
