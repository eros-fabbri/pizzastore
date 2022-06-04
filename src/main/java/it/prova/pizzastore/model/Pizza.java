package it.prova.pizzastore.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "Pizza")
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "descrizione")
	private String descrizione;
	@Column(name = "listaIngredienti")
	private String listaIngredienti;
	@Column(name = "prezzoBase")
	private Integer prezzoBase;
	@Column(name = "active")
	private Boolean active;

	@CreationTimestamp
	private LocalDateTime createDateTime;
	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public Pizza() {

	}

	public Pizza(String descrizione, String listaIngredienti, Integer prezzoBase, Boolean active) {
		super();
		this.descrizione = descrizione;
		this.listaIngredienti = listaIngredienti;
		this.prezzoBase = prezzoBase;
		this.active = active;
	}

	public Pizza(Long id, String descrizione, String listaIngredienti, Integer prezzoBase, Boolean active) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.listaIngredienti = listaIngredienti;
		this.prezzoBase = prezzoBase;
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	} 

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getListaIngredienti() {
		return listaIngredienti;
	}

	public void setListaIngredienti(String listaIngredienti) {
		this.listaIngredienti = listaIngredienti;
	}

	public Integer getPrezzoBase() {
		return prezzoBase;
	}

	public void setPrezzoBase(Integer prezzoBase) {
		this.prezzoBase = prezzoBase;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
