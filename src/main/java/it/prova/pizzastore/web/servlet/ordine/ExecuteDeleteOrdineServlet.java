package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteOrdineServlet
 */
@WebServlet("/ExecuteDeleteOrdineServlet")
public class ExecuteDeleteOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}

		try {
			Ordine ordineToDelete = MyServiceFactory.getOrdineServiceInstance()
					.caricaSingoloElemento(Long.parseLong(idOrdineParam));
			ordineToDelete.setClosed(true);
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineToDelete);
			request.setAttribute("listaOrdiniAttribute", MyServiceFactory.getOrdineServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("pizzaiolo/resultsordini.jsp").forward(request, response);
	}

	
}
