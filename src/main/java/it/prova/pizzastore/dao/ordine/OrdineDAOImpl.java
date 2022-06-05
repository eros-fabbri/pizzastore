package it.prova.pizzastore.dao.ordine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

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
	
	@Override
	public List<Ordine> findByExample(Ordine example) throws Exception{
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");

		if (StringUtils.isNotEmpty(example.getCodice()) && StringUtils.isNotBlank(example.getCodice())) {
			whereClauses.add(" o.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (example.getData() != null) {
			whereClauses.add("o.data = :data ");
			paramaterMap.put("data", example.getData());
		}
		if (example.getUtente() != null) {
			whereClauses.add(" o.utente.id = :utenteId ");
			paramaterMap.put("utenteId", example.getUtente().getId());
		}
		if (example.getCliente() != null) {
			whereClauses.add(" o.cliente.id = :clienteId ");
			paramaterMap.put("clienteId", example.getCliente().getId());
		}
		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
