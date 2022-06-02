package it.prova.pizzastore.service.ruolo;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.ruolo.RuoloDAO;
import it.prova.pizzastore.model.Ruolo;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class RuoloServiceImpl implements RuoloService {

	private RuoloDAO ruoloDao;

	public void setRuoloDao(RuoloDAO ruoloDao) {
		this.ruoloDao = ruoloDao;
	}

	@Override
	public List<Ruolo> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Ruolo caricaSingoloElemento(Long idInput) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			ruoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return ruoloDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Ruolo input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ruoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ruoloDao.update(input);

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
	public void inserisciNuovo(Ruolo input) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ruoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ruoloDao.insert(input);

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
	public void rimuovi(Ruolo input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			ruoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			ruoloDao.delete(input);

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
//public List<Ruolo> findByExample(Ruolo input) throws Exception {
//	// questo è come una connection
//			EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
//
//			try {
//				// uso l'injection per il dao
//				ruoloDao.setEntityManager(entityManager);
//
//				// eseguo quello che realmente devo fare
//				return ruoloDao.findByExample(input);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw e;
//			} finally {
//				LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
//			}
//	}

}