package it.uniroma3.siw.exception;

public class AnnuncioNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7564507423437298171L;

	public AnnuncioNotFoundException(Long id) {
        super("Annuncio con ID " + id + " non trovato.");
    }
}
