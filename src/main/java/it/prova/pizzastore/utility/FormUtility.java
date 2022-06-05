package it.prova.pizzastore.utility;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;

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
		System.out.println(clienteToBeVaidated);
		if (StringUtils.isBlank(clienteToBeVaidated.getNome())
				|| StringUtils.isBlank(clienteToBeVaidated.getIndirizzo())
				|| StringUtils.isBlank(clienteToBeVaidated.getCognome())) {
			return false;
		}
		return true;
	}

	public static boolean validateOrdineBean(Ordine ordineToBeVaidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(ordineToBeVaidated.getCodice()) || ordineToBeVaidated.getPizze().isEmpty()
				/*|| ordineToBeVaidated.getCliente() == null*/ || ordineToBeVaidated.getUtente() == null) {
			return false;
		}
		return true;
	}

	public static Pizza createPizzaFromParams(String descrizione, String listaIngredienti, String prezzoBase) {
		Pizza pizzaFromParams = new Pizza();
		pizzaFromParams.setDescrizione(descrizione);
		pizzaFromParams.setListaIngredienti(listaIngredienti);
		if (NumberUtils.isCreatable(prezzoBase)) {
			pizzaFromParams.setPrezzoBase(Integer.parseInt(prezzoBase));
		}
		pizzaFromParams.setActive(true);
		return pizzaFromParams;

	}

	public static Ordine createOrdineFromParams(String codice, String utenteId, String clienteId, String[] idPizze) {
		Ordine ordineFromParams = new Ordine();
		List<Pizza> listaPizze = new ArrayList<Pizza>();
		ordineFromParams.setCodice(codice);
		if (NumberUtils.isCreatable(clienteId) && NumberUtils.isCreatable(utenteId)) {

			try {
				ordineFromParams.setUtente(
						MyServiceFactory.getUtenteServiceInstance().caricaSingoloElemento(Long.parseLong(utenteId)));
				ordineFromParams.setCliente(null);
				for (String idItem : idPizze) {
					if (NumberUtils.isCreatable(idItem)) {
						Pizza pizzaTemp = MyServiceFactory.getPizzaServiceInstance()
								.caricaSingoloElemento(Long.parseLong(idItem));
						listaPizze.add(pizzaTemp);

					}

				}
				ordineFromParams.setPizze(listaPizze);
				ordineFromParams.setClosed(false);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return ordineFromParams;
	}

	public static Cliente createClienteFromParams(String nome, String cognome, String indirizzo) {
		Cliente clienteFromParams = new Cliente();

		clienteFromParams.setActive(true);
		clienteFromParams.setNome(nome);
		clienteFromParams.setCognome(cognome);
		clienteFromParams.setIndirizzo(indirizzo);

		return clienteFromParams;
	}
}
