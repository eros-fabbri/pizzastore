package it.prova.pizzastore.dao.pizza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.pizzastore.model.Pizza;

public class PizzaDAOImpl implements PizzaDAO {

	private EntityManager entityManager;

	@Override
	public List<Pizza> list() throws Exception {

		return entityManager.createQuery("from Pizza", Pizza.class).getResultList();
	}

	@Override
	public Pizza findOne(Long id) throws Exception {

		return entityManager.find(Pizza.class, id);
	}

	@Override
	public void update(Pizza input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Pizza input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Pizza input) throws Exception {
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
