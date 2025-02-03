package com.example.startapp.error;

public class TecnicoNotFoundException extends RuntimeException {
    public TecnicoNotFoundException(String message) {
        super(message);
    }

    public TecnicoNotFoundException(Long id) {
        super("No hay usuarios con ese ID: " + id);
    }

    public TecnicoNotFoundException() {
        super("No hay usuarios con esos requisitos de búsqueda");
    }
}
