package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.FormUtility;

/**
 * Servlet implementation class ExecuteUpdateClienteServlet
 */
@WebServlet("/ExecuteUpdateClienteServlet")
public class ExecuteUpdateClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descrizioneParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String indirizzoParam = request.getParameter("indirizzo");
		String idClienteParam = request.getParameter("id");
 		
		if(!NumberUtils.isCreatable(idClienteParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore");
			// questo mi serve per la select di registi in pagina
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
			return;
		}
		
		Cliente clienteForUpdate = FormUtility.createClienteFromParams(descrizioneParam, cognomeParam, indirizzoParam);
		
		clienteForUpdate.setId(Long.parseLong(idClienteParam));
		
		try {
			if (!FormUtility.validateClienteBean(clienteForUpdate)) {
				request.setAttribute("clienteEdit", clienteForUpdate);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				// questo mi serve per la select di registi in pagina
				request.getRequestDispatcher("admin/editcliente.jsp").forward(request, response);
				return;
			}
			
			MyServiceFactory.getClienteServiceInstance().aggiorna(clienteForUpdate);
			request.setAttribute("listaClientiAttribute",
					MyServiceFactory.getClienteServiceInstance().listAll());
			
			request.getRequestDispatcher("admin/resultsclienti.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("admin/index.jsp").forward(request, response);
			return;
		}
	}

}
