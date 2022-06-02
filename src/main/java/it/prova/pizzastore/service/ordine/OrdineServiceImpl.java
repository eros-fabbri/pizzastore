package it.prova.pizzastore.service.ordine;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.ordine.OrdineDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDao;

	public void setOrdineDao(OrdineDAO ordineDao) {
		this.ordineDao = ordineDao;
	}

	@Override
	public List<Ordine> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ordineDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ordine caricaSingoloElemento(Long idInput) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ordineDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ordine input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ordineDao.update(input);

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
	public void inserisciNuovo(Ordine input) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ordineDao.insert(input);

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
	public void rimuovi(Ordine input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ordineDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ordineDao.delete(input);

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
//public List<Ordine> findByExample(Ordine input) throws Exception {
//	// questo è come una connection
//			EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
//
//			try {
//				// uso l'injection per il dao
//				ordineDao.setEntityManager(entityManager);
//
//				// eseguo quello che realmente devo fare
//				return ordineDao.findByExample(input);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw e;
//			} finally {
//				LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
//			}
//	}

}