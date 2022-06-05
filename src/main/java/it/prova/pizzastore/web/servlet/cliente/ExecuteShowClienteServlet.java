package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.service.MyServiceFactory;


@WebServlet("/ExecuteShowClienteServlet")
public class ExecuteShowClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idClienteParam = request.getParameter("idCliente");
		if (!NumberUtils.isCreatable(idClienteParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
			return;
		}
		
		
		try {
			request.setAttribute("clienteDetail",
					MyServiceFactory.getClienteServiceInstance().caricaSingoloElemento(Long.parseLong(idClienteParam)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("admin/detailcliente.jsp").forward(request, response);
	}

	
}
