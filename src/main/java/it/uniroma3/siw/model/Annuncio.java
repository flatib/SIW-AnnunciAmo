package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "annunci")
public class Annuncio {

	public enum Stato {
		ATTIVO, VENDUTO, RIMOSSO
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Column(nullable = false)
	private String titolo;

	@Column(length = 2000)
	private String descrizione;

	@NotNull
	@Min(0)
	@Column(nullable = false)
	private Double prezzo;

	@Column(nullable = false)
	private LocalDateTime dataPubblicazione;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Stato stato;

	@ManyToOne
	@JoinColumn(name = "autore_id", nullable = false)
	private User autore;

	@ManyToOne
	@JoinColumn(name = "categoria_id")
	private Categoria categoria;

	@OneToMany(mappedBy = "annuncio", cascade = CascadeType.REMOVE)
	private List<Messaggio> messaggi;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Immagine immagine;

	public Annuncio() {
	}

	public Annuncio(String titolo, String descrizione, Double prezzo, User autore, Categoria categoria) {
		this.titolo = titolo;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.autore = autore;
		this.categoria = categoria;
		this.dataPubblicazione = LocalDateTime.now();
		this.stato = Stato.ATTIVO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Double getPrezzo() {
		return this.prezzo;
	}
	//caro merialdo se leggi sei gay

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public LocalDateTime getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(LocalDateTime dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public User getAutore() {
		return autore;
	}

	public void setAutore(User autore) {
		this.autore = autore;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Messaggio> getMessaggi() {
		return messaggi;
	}

	public void setMessaggi(List<Messaggio> messaggi) {
		this.messaggi = messaggi;
	}

	public Immagine getImmagine() {
		return immagine;
	}

	public void setImmagine(Immagine immagine) {
		this.immagine = immagine;
	}
	
	public boolean isVenduto() {
		return this.stato == Stato.VENDUTO;
	}
	
	public boolean isAttivo() {
		return this.stato == Stato.ATTIVO;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Annuncio that = (Annuncio) obj;
		return id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
