package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListPizzeServlet
 */
@WebServlet("/ExecuteListPizzeServlet")
public class ExecuteListPizzeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("listaPizzeAttribute",
					MyServiceFactory.getPizzaServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/pizzaiolo/results.jsp").forward(request, response);
	}

}
