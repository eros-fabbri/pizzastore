package it.prova.pizzastore.service.pizza;

import java.util.List;

import it.prova.pizzastore.dao.pizza.PizzaDAO;
import it.prova.pizzastore.model.Pizza;

public interface PizzaService {

	// questo mi serve per injection
	public void setPizzaDao(PizzaDAO pizzaDao);

	public List<Pizza> listAll() throws Exception;

	public Pizza caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Pizza input) throws Exception;

	public void inserisciNuovo(Pizza input) throws Exception;

	public void rimuovi(Pizza input) throws Exception;

	//public List<Pizza> findByExample(Pizza input) throws Exception;

}
