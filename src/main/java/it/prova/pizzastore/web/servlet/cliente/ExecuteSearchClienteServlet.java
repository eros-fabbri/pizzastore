package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.FormUtility;

/**
 * Servlet implementation class ExecuteSearchClienteServlet
 */
@WebServlet("/ExecuteSearchClienteServlet")
public class ExecuteSearchClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeClienteParam = request.getParameter("nome");
		String cognomeClienteParam = request.getParameter("cognome");
		String indirizzoClienteParam = request.getParameter("indirizzo");
		
		
		Cliente clienteDaTrovare = FormUtility.createClienteFromParams(nomeClienteParam, cognomeClienteParam, indirizzoClienteParam);	

		try {
			
			MyServiceFactory.getClienteServiceInstance().inserisciNuovo(clienteDaTrovare);
			request.setAttribute("listaClientiAttribute",
					MyServiceFactory.getClienteServiceInstance().findByExample(clienteDaTrovare));
			
			request.getRequestDispatcher("admin/resultsclienti.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
			return;
		}
	
	}

}
