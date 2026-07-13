package it.uniroma3.siw.dto;

import it.uniroma3.siw.model.Annuncio;

public class AnnuncioDTO {

    private Long id;
    private String titolo;
    private String descrizione;
    private Double prezzo;
    private String categoria;
    private String immagineUrl;

    public AnnuncioDTO(Annuncio annuncio) {
        this.id = annuncio.getId();
        this.titolo = annuncio.getTitolo();
        this.descrizione = annuncio.getDescrizione();
        this.prezzo = annuncio.getPrezzo();

        if (annuncio.getCategoria() != null) {
            this.categoria = annuncio.getCategoria().getNome();
        }
        
        if (annuncio.getImmagine() != null) {
            this.immagineUrl = "http://localhost:8080/annunci/" + annuncio.getId() + "/immagine";
        }
    }

    public Long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public String getCategoria() {
        return categoria;
    }
    
    public String getImmagine() {
		return immagineUrl;
	}
}