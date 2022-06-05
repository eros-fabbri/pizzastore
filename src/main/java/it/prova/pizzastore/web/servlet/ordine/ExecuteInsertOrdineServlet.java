package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.FormUtility;

/**
 * Servlet implementation class ExecuteInsertOrdineServlet
 */
@WebServlet("/ExecuteInsertOrdineServlet")
public class ExecuteInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] idPizzeScelteParams = request.getParameterValues("pizzascelta");
		String codiceParam = request.getParameter("codice");
		String idClienteParam = request.getParameter("idcliente");
		String dataParam = request.getParameter("data");

		
		Ordine ordine = new Ordine();

		try {
//			if (!FormUtility.validatePizzaBean(ordine)) {
//				request.setAttribute("pizza", ordine);
//				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
//				// questo mi serve per la select di registi in pagina
//
//				return;
//			}

			MyServiceFactory.getOrdineServiceInstance().inserisciNuovo(ordine);
			request.setAttribute("listaPizzeAttribute", MyServiceFactory.getPizzaServiceInstance().listAll());

			request.getRequestDispatcher("/pizzaiolo/results.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/pizzaiolo/index.jsp").forward(request, response);
			return;
		}

	}

}
