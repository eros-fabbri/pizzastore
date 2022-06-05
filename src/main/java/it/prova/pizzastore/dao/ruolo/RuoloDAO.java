package it.prova.pizzastore.dao.ruolo;

import java.util.List;

import it.prova.pizzastore.dao.IBaseDAO;
import it.prova.pizzastore.model.Ruolo;

public interface RuoloDAO extends IBaseDAO<Ruolo> {

	public Ruolo findByDescrizioneAndCodice(String descrizione, String codice) throws Exception;

}
