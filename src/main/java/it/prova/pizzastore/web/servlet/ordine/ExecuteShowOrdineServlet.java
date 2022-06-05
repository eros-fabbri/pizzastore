package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareShowOrdineServlet
 */
@WebServlet("/ExecuteShowOrdineServlet")
public class ExecuteShowOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");
		if (!NumberUtils.isCreatable(idOrdineParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("pizzaiolo/index.jsp").forward(request, response);
			return;
		}
		
		
		try {
			request.setAttribute("ordineDetail",
					MyServiceFactory.getOrdineServiceInstance().caricaSingoloElementoEager(Long.parseLong(idOrdineParam)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("pizzaiolo/detailordine.jsp").forward(request, response);

	}

}
