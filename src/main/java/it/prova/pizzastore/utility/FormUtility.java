package it.prova.pizzastore.utility;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public class FormUtility {
	
	
	public static boolean validatePizzaBean(Pizza pizzaToBeVaidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(pizzaToBeVaidated.getDescrizione())
				|| StringUtils.isBlank(pizzaToBeVaidated.getListaIngredienti())
				|| pizzaToBeVaidated.getPrezzoBase() < 1) {
			return false;
		}
		return true;
	}

	public static boolean validateClienteBean(Cliente clienteToBeVaidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(clienteToBeVaidated.getNome()) || StringUtils.isBlank(clienteToBeVaidated.getCognome())
				|| StringUtils.isBlank(clienteToBeVaidated.getIndirizzo())
				|| StringUtils.isBlank(clienteToBeVaidated.getCognome())) {
			return false;
		}
		return true;
	}
	
	public static boolean validateOrdineBean(Ordine ordineToBeVaidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(ordineToBeVaidated.getCodice()) 
				|| ordineToBeVaidated.getPizze().isEmpty()
				|| ordineToBeVaidated.getCliente() == null
				|| ordineToBeVaidated.getUtente() == null) {
			return false;
		}
		return true;
	}
	
	public static Pizza createPizzaFromParams(String descrizione, String listaIngredienti, String prezzoBase ) {
		Pizza pizzaFromParams = new Pizza();
		pizzaFromParams.setDescrizione(descrizione);
		pizzaFromParams.setListaIngredienti(listaIngredienti);
		if(NumberUtils.isCreatable(prezzoBase)) {
			pizzaFromParams.setPrezzoBase(Integer.parseInt(prezzoBase));
		}
		pizzaFromParams.setActive(true);
		return pizzaFromParams;
		
	}
	
	public static Ordine createOrdineFromParams(String codice, String utenteId, String clienteId, String[] idPizze) {
		return null;
		
		
	}
}

