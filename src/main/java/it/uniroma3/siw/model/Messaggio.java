package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "messaggi")
public class Messaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(length = 1000, nullable = false)
    private String testo;

    @Column(nullable = false)
    private LocalDateTime dataInvio;

    @ManyToOne
    @JoinColumn(name = "annuncio_id", nullable = false)
    private Annuncio annuncio;

    @ManyToOne
    @JoinColumn(name = "mittente_id", nullable = false)
    private User mittente;
    
    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private User destinatario;

    public Messaggio() {
    }

    public Messaggio(String testo, Annuncio annuncio, User mittente) {
        this.testo = testo;
        this.annuncio = annuncio;
        this.mittente = mittente;
        this.dataInvio = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public LocalDateTime getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(LocalDateTime dataInvio) {
        this.dataInvio = dataInvio;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    public User getMittente() {
        return mittente;
    }

    public void setMittente(User mittente) {
        this.mittente = mittente;
    }

    public User getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(User destinatario) {
		this.destinatario = destinatario;
	}

	@Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Messaggio that = (Messaggio) obj;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
