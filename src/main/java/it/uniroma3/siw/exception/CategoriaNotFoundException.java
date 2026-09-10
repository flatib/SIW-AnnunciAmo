package it.uniroma3.siw.exception;

public class CategoriaNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5869298804993030271L;

	public CategoriaNotFoundException(Long id) {
        super("Categoria con ID " + id + " non trovata.");
    }
}
