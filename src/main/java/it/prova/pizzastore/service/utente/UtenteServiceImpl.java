package it.prova.pizzastore.service.utente;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.utente.UtenteDAO;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class UtenteServiceImpl implements UtenteService {

	private UtenteDAO utenteDao;

	public void setUtenteDao(UtenteDAO utenteDao) {
		this.utenteDao = utenteDao;
	}

	@Override
	public List<Utente> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Utente caricaSingoloElemento(Long idInput) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			utenteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return utenteDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Utente input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			utenteDao.update(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisciNuovo(Utente input) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			utenteDao.insert(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Utente input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			utenteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			utenteDao.delete(input);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}

	}

//@Override
//public List<Utente> findByExample(Utente input) throws Exception {
//	// questo è come una connection
//			EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
//
//			try {
//				// uso l'injection per il dao
//				utenteDao.setEntityManager(entityManager);
//
//				// eseguo quello che realmente devo fare
//				return utenteDao.findByExample(input);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw e;
//			} finally {
//				LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
//			}
//	}

}