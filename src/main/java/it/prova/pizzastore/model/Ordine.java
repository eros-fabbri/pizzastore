package it.prova.pizzastore.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "ordine")
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "closed")
	private boolean closed;
	@Column(name = "codice")
	private String codice;
	@Column(name = "prezzototale")
	private int prezzoTotale;

	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	@ManyToOne
	@JoinColumn(name = "ordine_id", nullable = false)
	private Utente utente;

	@OneToOne(mappedBy = "ordine")
	private Cliente cliente;

	@ManyToMany
	@JoinTable(name = "ordine_pizza", joinColumns = @JoinColumn(name = "ordine_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "ID"))
	private List<Pizza> pizze = new ArrayList<Pizza>();

	public Ordine() {

	}

	public Ordine(Long id, boolean closed, String codice, int prezzoTotale, Utente utente, Cliente cliente,
			List<Pizza> pizze) {
		super();
		this.id = id;
		this.closed = closed;
		this.codice = codice;
		this.prezzoTotale = prezzoTotale;
		this.utente = utente;
		this.cliente = cliente;
		this.pizze = pizze;
	}

	public Ordine(boolean closed, String codice, int prezzoTotale, Utente utente, Cliente cliente, List<Pizza> pizze) {
		super();
		this.closed = closed;
		this.codice = codice;
		this.prezzoTotale = prezzoTotale;
		this.utente = utente;
		this.cliente = cliente;
		this.pizze = pizze;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public int getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(int prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Pizza> getPizze() {
		return pizze;
	}

	public void setPizze(List<Pizza> pizze) {
		this.pizze = pizze;
	}

}
