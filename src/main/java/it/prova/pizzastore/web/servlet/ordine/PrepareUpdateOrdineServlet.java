package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
 * Servlet implementation class PrepareUpdateOrdineServlet
 */
@WebServlet("/PrepareUpdateOrdineServlet")
public class PrepareUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");
		System.out.println(idOrdineParam);
		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}

		try {
			Ordine ordineToEdit = MyServiceFactory.getOrdineServiceInstance()
					.caricaSingoloElementoEager(Long.parseLong(idOrdineParam));
			request.setAttribute("ordineEdit", ordineToEdit);
			request.setAttribute("clienti_attribute", MyServiceFactory.getClienteServiceInstance().listAll());
			request.setAttribute("utenti_attribute", MyServiceFactory.getUtenteServiceInstance().listAll());

			List<String> descrizioniPizzeOrdine = ordineToEdit.getPizze().stream()
					.map(Pizza::getDescrizione).collect(Collectors.toList());
			
			request.setAttribute("pizze_descrizioni_attribute", descrizioniPizzeOrdine);
			request.setAttribute("pizze_attribute", MyServiceFactory.getPizzaServiceInstance().listAll());
			request.getRequestDispatcher("pizzaiolo/editordine.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
