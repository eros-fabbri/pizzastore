package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.service.ordine.OrdineService;
import it.prova.pizzastore.utility.FormUtility;

/**
 * Servlet implementation class ExecuteSearchOrdineServlet
 */
@WebServlet("/ExecuteSearchOrdineServlet")
public class ExecuteSearchOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] idPizzeScelteParams = request.getParameterValues("pizzascelta");
		String codiceParam = request.getParameter("codice");
		String idClienteParam = request.getParameter("idcliente");
		String dataParam = request.getParameter("data");
		String idUtenteParam = request.getParameter("idutente");

		Ordine ordine = FormUtility.createOrdineFromParams(codiceParam, idUtenteParam, idClienteParam,
				idPizzeScelteParams , dataParam);
		
		try {
			MyServiceFactory.getOrdineServiceInstance().calcolaPrezzoOrdine(ordine);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
		
			request.setAttribute("listaOrdiniAttribute", MyServiceFactory.getOrdineServiceInstance().findByExample(ordine));

			request.getRequestDispatcher("pizzaiolo/resultsordiniintegrale.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}

	}

}
