package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListClientiServlet
 */
@WebServlet("/ExecuteListClientiServlet")
public class ExecuteListClientiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listaClientiAttribute", MyServiceFactory.getClienteServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/admin/resultsclienti.jsp").forward(request, response);
	}

}
