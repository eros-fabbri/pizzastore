package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListOrdiniDisponibiliServlet
 */
@WebServlet("/ExecuteListOrdiniDisponibiliServlet")
public class ExecuteListOrdiniDisponibiliServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			List<Ordine> listaOrdini = MyServiceFactory.getOrdineServiceInstance().listAll();
			
			
			for (Ordine ordineItem : listaOrdini) {
				System.out.println(ordineItem.getUtente().getId());
			}
			
			request.setAttribute("listaOrdiniAttribute", listaOrdini);
			System.out.println(((Utente)request.getSession().getAttribute("userInfo")).getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/fattorino/results.jsp").forward(request, response);
		
	}

}
