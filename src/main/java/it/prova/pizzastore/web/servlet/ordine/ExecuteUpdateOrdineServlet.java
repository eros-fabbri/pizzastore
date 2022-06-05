package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.service.ordine.OrdineService;
import it.prova.pizzastore.utility.FormUtility;

/**
 * Servlet implementation class ExecuteInsertOrdineServlet
 */
@WebServlet("/ExecuteUpdateOrdineServlet")
public class ExecuteUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] idPizzeScelteParams = request.getParameterValues("pizzascelta");
		String codiceParam = request.getParameter("codice");
		String idClienteParam = request.getParameter("idcliente");
		String dataParam = request.getParameter("data");
		String idUtenteParam = request.getParameter("idutente");
		String idOrdine = request.getParameter("idOrdine");
		Ordine ordine = FormUtility.createOrdineFromParams(codiceParam, idUtenteParam, idClienteParam,
				idPizzeScelteParams, dataParam);
		
		if(!NumberUtils.isCreatable(idOrdine)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			// questo mi serve per la select di registi in pagina
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}
		
		ordine.setId(Long.parseLong(idOrdine));
		
		
		try {
			if (!FormUtility.validateOrdineBean(ordine)) {
				request.setAttribute("ordine", ordine);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				// questo mi serve per la select di registi in pagina
				request.getRequestDispatcher("pizzaiolo/insertordine.jsp").forward(request, response);
				return;
			}

			OrdineService ordineService = MyServiceFactory.getOrdineServiceInstance();
			ordineService.aggiorna(ordine);
			request.setAttribute("listaOrdiniAttribute", MyServiceFactory.getOrdineServiceInstance().listAll());

			request.getRequestDispatcher("pizzaiolo/resultsordini.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}

	}

}
