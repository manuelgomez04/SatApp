package com.example.startapp.error;

public class NotaNotFoundException extends RuntimeException {
    public NotaNotFoundException(String message) {
        super(message);
    }

    public NotaNotFoundException(Long id) {
        super("No hay nota con ese ID: " + id);
    }

    public NotaNotFoundException() {
        super("No hay nota con esos requisitos de búsqueda");
    }
}
