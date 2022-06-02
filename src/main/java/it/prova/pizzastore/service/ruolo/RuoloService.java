package it.prova.pizzastore.service.ruolo;

import java.util.List;

import it.prova.pizzastore.dao.ruolo.RuoloDAO;
import it.prova.pizzastore.model.Ruolo;

public interface RuoloService {

	// questo mi serve per injection
	public void setRuoloDao(RuoloDAO ruoloDao);

	public List<Ruolo> listAll() throws Exception;

	public Ruolo caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Ruolo input) throws Exception;

	public void inserisciNuovo(Ruolo input) throws Exception;

	public void rimuovi(Ruolo input) throws Exception;

	//public List<Ruolo> findByExample(Ruolo input) throws Exception;

}
