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
		String listaIngredientiParam = request.getParameter("listaIngredienti");
		String prezzoBaseParam = request.getParameter("prezzoBase");
		
		Pizza pizzaForInsert = FormUtility.createPizzaFromParams(descrizioneParam, listaIngredientiParam, prezzoBaseParam);	
		
		try {
			if (FormUtility.validatePizzaBean(pizzaForInsert)) {
				request.setAttribute("pizza", pizzaForInsert);
				// questo mi serve per la select di registi in pagina
				request.setAttribute("registi_list_attribute",
						MyServiceFactory.getPizzaServiceInstance().listAll());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/pizzaiolo/insertpizza.jsp").forward(request, response);
				return;
			}
			
			MyServiceFactory.getPizzaServiceInstance().inserisciNuovo(pizzaForInsert);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/index.jsp").forward(request, response);
			return;
		}
		
	}

}
