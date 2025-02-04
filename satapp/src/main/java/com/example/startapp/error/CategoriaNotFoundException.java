package com.example.startapp.error;

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String message) {
        super(message);
    }
    public CategoriaNotFoundException(Long id) {
        super("No hay categoría con ese ID: " + id);
    }
    public CategoriaNotFoundException() {
        super("No hay categorías con esos requisitos de búsqueda");
    }
}
