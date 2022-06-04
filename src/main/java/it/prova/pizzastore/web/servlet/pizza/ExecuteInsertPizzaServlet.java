package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.FormUtility;


@WebServlet("/ExecuteInsertPizzaServlet")
public class ExecuteInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String descrizioneParam = request.getParameter("descrizione");
		String listaIngredientiParam = request.getParameter("ingredienti");
		String prezzoBaseParam = request.getParameter("prezzobase");
		
		System.out.println(prezzoBaseParam);
		Pizza pizzaForInsert = FormUtility.createPizzaFromParams(descrizioneParam, listaIngredientiParam, prezzoBaseParam);	
		System.out.println(pizzaForInsert);
		try {
			if (!FormUtility.validatePizzaBean(pizzaForInsert)) {
				request.setAttribute("pizza", pizzaForInsert);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				// questo mi serve per la select di registi in pagina
				
				return;
			}
			
			MyServiceFactory.getPizzaServiceInstance().inserisciNuovo(pizzaForInsert);
			request.setAttribute("listaPizzeAttribute",
					MyServiceFactory.getPizzaServiceInstance().listAll());
			
			request.getRequestDispatcher("/pizzaiolo/results.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/index.jsp").forward(request, response);
			return;
		}
		
		
		
	}

}
