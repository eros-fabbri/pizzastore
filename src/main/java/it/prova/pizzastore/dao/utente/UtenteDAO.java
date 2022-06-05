package it.prova.pizzastore.dao.utente;

import java.util.List;
import java.util.Optional;

import it.prova.pizzastore.dao.IBaseDAO;
import it.prova.pizzastore.model.Utente;

public interface UtenteDAO extends IBaseDAO<Utente> {
	public Optional<Utente> findByUsernameAndPassword(String username, String password) throws Exception;
	
	public Optional<Utente> accedi(String username, String password) throws Exception;

}
