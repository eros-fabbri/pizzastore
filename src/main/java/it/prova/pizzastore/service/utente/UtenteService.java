package it.prova.pizzastore.service.utente;

import java.util.List;

import it.prova.pizzastore.dao.utente.UtenteDAO;
import it.prova.pizzastore.model.Utente;

public interface UtenteService {

	// questo mi serve per injection
	public void setUtenteDao(UtenteDAO utenteDao);

	public List<Utente> listAll() throws Exception;

	public Utente caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Utente input) throws Exception;

	public void inserisciNuovo(Utente input) throws Exception;

	public void rimuovi(Utente input) throws Exception;

	//public List<Utente> findByExample(Utente input) throws Exception;

}
