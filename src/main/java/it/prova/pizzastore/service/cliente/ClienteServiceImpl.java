package it.prova.pizzastore.service.cliente;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.cliente.ClienteDAO;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class ClienteServiceImpl implements ClienteService {

	private ClienteDAO clienteDao;

	public void setClienteDao(ClienteDAO clienteDao) {
		this.clienteDao = clienteDao;
	}

	@Override
	public List<Cliente> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Cliente caricaSingoloElemento(Long idInput) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// uso l'injection per il dao
			clienteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return clienteDao.findOne(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Cliente input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			clienteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			clienteDao.update(input);

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
	public void inserisciNuovo(Cliente input) throws Exception {
		// questo è come una connection
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			clienteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			clienteDao.insert(input);

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
	public void rimuovi(Cliente input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			clienteDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			clienteDao.delete(input);

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
//public List<Cliente> findByExample(Cliente input) throws Exception {
//	// questo è come una connection
//			EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
//
//			try {
//				// uso l'injection per il dao
//				clienteDao.setEntityManager(entityManager);
//
//				// eseguo quello che realmente devo fare
//				return clienteDao.findByExample(input);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw e;
//			} finally {
//				LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
//			}
//	}

}