package it.prova.pizzastore.dao.ordine;

import java.util.List;

import it.prova.pizzastore.dao.IBaseDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;

public interface OrdineDAO extends IBaseDAO<Ordine> {
	
	public Integer calcolaPrezzoOrdine(Ordine ordine) throws Exception;
	
	public List<Ordine> getOrdiniAttivi(Utente utente) throws Exception;


	
}
