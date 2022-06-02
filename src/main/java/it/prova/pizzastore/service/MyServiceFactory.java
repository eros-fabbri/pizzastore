package it.prova.pizzastore.service;

import it.prova.pizzastore.dao.cliente.ClienteDAO;
import it.prova.pizzastore.dao.cliente.ClienteDAOImpl;
import it.prova.pizzastore.dao.ordine.OrdineDAO;
import it.prova.pizzastore.dao.ordine.OrdineDAOImpl;
import it.prova.pizzastore.dao.pizza.PizzaDAO;
import it.prova.pizzastore.dao.pizza.PizzaDAOImpl;
import it.prova.pizzastore.dao.ruolo.RuoloDAO;
import it.prova.pizzastore.dao.ruolo.RuoloDAOImpl;
import it.prova.pizzastore.dao.utente.UtenteDAO;
import it.prova.pizzastore.dao.utente.UtenteDAOImpl;
import it.prova.pizzastore.service.cliente.ClienteService;
import it.prova.pizzastore.service.cliente.ClienteServiceImpl;
import it.prova.pizzastore.service.ordine.OrdineService;
import it.prova.pizzastore.service.ordine.OrdineServiceImpl;
import it.prova.pizzastore.service.pizza.PizzaService;
import it.prova.pizzastore.service.pizza.PizzaServiceImpl;
import it.prova.pizzastore.service.ruolo.RuoloService;
import it.prova.pizzastore.service.ruolo.RuoloServiceImpl;
import it.prova.pizzastore.service.utente.UtenteService;
import it.prova.pizzastore.service.utente.UtenteServiceImpl;

public class MyServiceFactory {
	private static OrdineService ORDINE_SERVICE_INSTANCE = null;
	private static OrdineDAO ORDINEDAO_INSTANCE = null;
	private static PizzaService PIZZA_SERVICE_INSTANCE = null;
	private static PizzaDAO PIZZADAO_INSTANCE = null;
	private static UtenteService UTENTE_SERVICE_INSTANCE = null;
	private static UtenteDAO UTENTEDAO_INSTANCE = null;
	private static RuoloService RUOLO_SERVICE_INSTANCE = null;
	private static RuoloDAO RUOLODAO_INSTANCE = null;
	private static ClienteService CLIENTE_SERVICE_INSTANCE = null;
	private static ClienteDAO CLIENTEDAO_INSTANCE = null;

	public static OrdineService getOrdineServiceInstance() {
		if (ORDINE_SERVICE_INSTANCE == null)
			ORDINE_SERVICE_INSTANCE = new OrdineServiceImpl();

		if (ORDINEDAO_INSTANCE == null)
			ORDINEDAO_INSTANCE = new OrdineDAOImpl();

		ORDINE_SERVICE_INSTANCE.setOrdineDao(ORDINEDAO_INSTANCE);

		return ORDINE_SERVICE_INSTANCE;
	}

	public static PizzaService getPizzaServiceInstance() {
		if (PIZZA_SERVICE_INSTANCE == null)
			PIZZA_SERVICE_INSTANCE = new PizzaServiceImpl();

		if (PIZZADAO_INSTANCE == null)
			PIZZADAO_INSTANCE = new PizzaDAOImpl();

		PIZZA_SERVICE_INSTANCE.setPizzaDao(PIZZADAO_INSTANCE);

		return PIZZA_SERVICE_INSTANCE;
	}

	public static UtenteService getUtenteServiceInstance() {
		if (UTENTE_SERVICE_INSTANCE == null)
			UTENTE_SERVICE_INSTANCE = new UtenteServiceImpl();

		if (UTENTEDAO_INSTANCE == null)
			UTENTEDAO_INSTANCE = new UtenteDAOImpl();

		UTENTE_SERVICE_INSTANCE.setUtenteDao(UTENTEDAO_INSTANCE);

		return UTENTE_SERVICE_INSTANCE;
	}

	public static RuoloService getRuoloServiceInstance() {
		if (RUOLO_SERVICE_INSTANCE == null)
			RUOLO_SERVICE_INSTANCE = new RuoloServiceImpl();

		if (RUOLODAO_INSTANCE == null)
			RUOLODAO_INSTANCE = new RuoloDAOImpl();

		RUOLO_SERVICE_INSTANCE.setRuoloDao(RUOLODAO_INSTANCE);

		return RUOLO_SERVICE_INSTANCE;
	}

	public static ClienteService getClienteServiceInstance() {
		if (CLIENTE_SERVICE_INSTANCE == null)
			CLIENTE_SERVICE_INSTANCE = new ClienteServiceImpl();

		if (CLIENTEDAO_INSTANCE == null)
			CLIENTEDAO_INSTANCE = new ClienteDAOImpl();

		CLIENTE_SERVICE_INSTANCE.setClienteDao(CLIENTEDAO_INSTANCE);

		return CLIENTE_SERVICE_INSTANCE;
	}
}