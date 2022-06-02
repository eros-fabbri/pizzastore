package it.prova.pizzastore.service.cliente;

import java.util.List;

import it.prova.pizzastore.dao.cliente.ClienteDAO;
import it.prova.pizzastore.model.Cliente;

public interface ClienteService {

	// questo mi serve per injection
	public void setClienteDao(ClienteDAO clienteDao);

	public List<Cliente> listAll() throws Exception;

	public Cliente caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Cliente input) throws Exception;

	public void inserisciNuovo(Cliente input) throws Exception;

	public void rimuovi(Cliente input) throws Exception;

	//public List<Cliente> findByExample(Cliente input) throws Exception;

}
