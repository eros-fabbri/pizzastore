package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.FormUtility;


@WebServlet("/ExecuteSearchPizzaServlet")
public class ExecuteSearchPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String descrizioneParam = request.getParameter("descrizione");
		String listaIngredientiParam = request.getParameter("ingredienti");
		String prezzoBaseParam = request.getParameter("prezzobase");
		

		Pizza pizzaExample = FormUtility.createPizzaFromParams(descrizioneParam, listaIngredientiParam, prezzoBaseParam);	
		
		try {
			List<Pizza> pizzeTrovate = MyServiceFactory.getPizzaServiceInstance().findByExample(pizzaExample);
			request.setAttribute("listaPizzeAttribute", pizzeTrovate);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("pizzaiolo/resultspizze.jsp").forward(request, response);
		
		
	}

}
