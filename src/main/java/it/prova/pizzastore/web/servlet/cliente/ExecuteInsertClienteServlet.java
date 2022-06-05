package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.FormUtility;


@WebServlet("/ExecuteInsertClienteServlet")
public class ExecuteInsertClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeClienteParam = request.getParameter("nome");
		String cognomeClienteParam = request.getParameter("cognome");
		String indirizzoClienteParam = request.getParameter("indirizzo");
		
		
		Cliente clientePerInser = FormUtility.createClienteFromParams(nomeClienteParam, cognomeClienteParam, indirizzoClienteParam);	

		try {
			if (!FormUtility.validateClienteBean(clientePerInser)) {
				request.setAttribute("cliente", clientePerInser);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				// questo mi serve per la select di registi in pagina
				request.getRequestDispatcher("admin/insertcliente.jsp").forward(request, response);
				return;
			}
			
			MyServiceFactory.getClienteServiceInstance().inserisciNuovo(clientePerInser);
			request.setAttribute("listaClientiAttribute",
					MyServiceFactory.getClienteServiceInstance().listAll());
			
			request.getRequestDispatcher("/admin/resultsclienti.jsp").forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
			return;
		}
	}

}
