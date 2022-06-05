package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.FormUtility;

/**
 * Servlet implementation class ExecuteUpdatePizzaServlet
 */
@WebServlet("/ExecuteUpdatePizzaServlet")
public class ExecuteUpdatePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String descrizioneParam = request.getParameter("descrizione");
		String listaIngredientiParam = request.getParameter("ingredienti");
		String prezzoBaseParam = request.getParameter("prezzobase");
		String idPizzaParam = request.getParameter("id");

		if (!NumberUtils.isCreatable(idPizzaParam)) {

		}

		Pizza pizzaForUpdate = FormUtility.createPizzaFromParams(descrizioneParam, listaIngredientiParam,
				prezzoBaseParam);

		pizzaForUpdate.setId(Long.parseLong(idPizzaParam));

		try {
			if (!FormUtility.validatePizzaBean(pizzaForUpdate)) {
				request.setAttribute("pizza", pizzaForUpdate);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				// questo mi serve per la select di registi in pagina
				request.getRequestDispatcher("pizzaiolo/insertpizza.jsp").forward(request, response);
				return;
			}

			MyServiceFactory.getPizzaServiceInstance().aggiorna(pizzaForUpdate);
			request.setAttribute("listaPizzeAttribute", MyServiceFactory.getPizzaServiceInstance().listAll());

			request.getRequestDispatcher("pizzaiolo/resultspizze.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}
	}

}
