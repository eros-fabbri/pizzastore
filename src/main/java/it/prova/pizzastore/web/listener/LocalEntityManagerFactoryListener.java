package it.prova.pizzastore.web.listener;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.model.StatoUtente;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.service.ruolo.RuoloService;
import it.prova.pizzastore.service.utente.UtenteService;

@WebListener
public class LocalEntityManagerFactoryListener implements ServletContextListener {

	private static EntityManagerFactory entityManagerFactory;

	public void contextInitialized(ServletContextEvent sce) {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("pizzastore_unit");
			initAdminUserAndRuoli();
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		if (entityManagerFactory != null) {
			entityManagerFactory.close();
		}
	}

	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			throw new IllegalStateException("Context is not initialized yet.");
		}
		return entityManagerFactory.createEntityManager();

	}

	public static void closeEntityManager(EntityManager em) {
		if (em != null) {
			try {
				if (em.isOpen()) {
					em.close();
				}
			} catch (PersistenceException ex) {
				System.err.println("Could not close JPA EntityManager" + ex);
			} catch (Throwable ex) {
				System.err.println("Unexpected exception on closing JPA EntityManager" + ex);
			}
		}
	}
	private void initAdminUserAndRuoli() throws Exception {
		RuoloService ruoloServiceInstance = MyServiceFactory.getRuoloServiceInstance();
		UtenteService utenteServiceInstance = MyServiceFactory.getUtenteServiceInstance();

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Administrator", "ADMIN_ROLE"));
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo User", "PIZZAIOLO_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Pizzaiolo User", "PIZZAIOLO_ROLE"));
		}
		
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE") == null) {
			ruoloServiceInstance.inserisciNuovo(new Ruolo("Fattorino User", "FATTORINO_ROLE"));
		}

		if (utenteServiceInstance.findByUsernameAndPassword("admin", "admin") == null) {
			Utente admin = new Utente("admin", "admin", "Paolo", "Verdi");
			admin.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.aggiungiRuolo(admin,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", "ADMIN_ROLE"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("pizzaiolo", "pizzaiolo") == null) {
			Utente pizzaiolouser = new Utente("pizzaiolo", "pizzaiolo", "Mario", "Pizza");
			pizzaiolouser.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(pizzaiolouser);
			utenteServiceInstance.aggiungiRuolo(pizzaiolouser,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Pizzaiolo User", "PIZZAIOLO_ROLE"));
		}
		
		if (utenteServiceInstance.findByUsernameAndPassword("fattorino", "fattorino") == null) {
			Utente fattorinouser = new Utente("fattorino", "fattorino", "Rino", "Fatto");
			fattorinouser.setStato(StatoUtente.ATTIVO);
			utenteServiceInstance.inserisciNuovo(fattorinouser);
			utenteServiceInstance.aggiungiRuolo(fattorinouser,
					ruoloServiceInstance.cercaPerDescrizioneECodice("Fattorino User", "FATTORINO_ROLE"));
		}
	}

}
