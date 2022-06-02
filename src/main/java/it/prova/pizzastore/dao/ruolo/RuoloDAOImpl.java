package it.prova.pizzastore.dao.ruolo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.pizzastore.model.Ruolo;

public class RuoloDAOImpl implements RuoloDAO {

	private EntityManager entityManager;

	@Override
	public List<Ruolo> list() throws Exception {

		return entityManager.createQuery("from Ruolo", Ruolo.class).getResultList();
	}

	@Override
	public Ruolo findOne(Long id) throws Exception {

		return entityManager.find(Ruolo.class, id);
	}

	@Override
	public void update(Ruolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ruolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Ruolo input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	

}
