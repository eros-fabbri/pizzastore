package it.prova.pizzastore.dao.ordine;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public List<Ordine> list() throws Exception {

		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Ordine findOne(Long id) throws Exception {

		return entityManager.find(Ordine.class, id);
	}

	@Override
	public void update(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Integer calcolaPrezzoOrdine(Ordine ordine) throws Exception {

		Query query = entityManager.createNativeQuery(
				"select sum(p.prezzobase) from Ordine o inner join ordine_pizza op on o.id=op.ordine_id inner join pizza p on op.pizza_id = p.id where o.codice = :codice ;");
		query.setParameter("codice", ordine.getCodice());

		System.out.println(query.getFirstResult());
		return (Integer) query.getFirstResult();
	}

	@Override
	public List<Ordine> getOrdiniAttivi(Utente utente) {

		Query query = entityManager.createQuery("from Ordine o join o.utente u where u.id=:id ");
		System.out.println(utente.getId());
		query.setParameter("id", utente.getId());
		return (List<Ordine>) query.getResultList();
	}

	@Override
	public Optional<Ordine> findOneEager(Long id) throws Exception {

		return entityManager.createQuery(
				"from Ordine o left join fetch o.cliente left join fetch o.utente left join fetch o.pizze where o.id=:idOrdine",
				Ordine.class).setParameter("idOrdine", id).getResultList().stream().findFirst();
	}

}
