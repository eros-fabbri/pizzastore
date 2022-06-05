package it.prova.pizzastore.service.ordine;

import java.util.List;

import it.prova.pizzastore.dao.ordine.OrdineDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Utente;

public interface OrdineService {

	// questo mi serve per injection
	public void setOrdineDao(OrdineDAO ordineDao);

	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Ordine input) throws Exception;

	public void inserisciNuovo(Ordine input) throws Exception;

	public void rimuovi(Ordine input) throws Exception;
	
	public void calcolaPrezzoOrdine(Ordine ordine) throws Exception;

	//public List<Ordine> findByExample(Ordine input) throws Exception;

}
