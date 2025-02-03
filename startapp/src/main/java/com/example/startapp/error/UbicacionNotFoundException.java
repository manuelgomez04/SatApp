package com.example.startapp.error;

public class UbicacionNotFoundException extends RuntimeException {

    public UbicacionNotFoundException(String message) {
        super(message);
    }

    public UbicacionNotFoundException(Long id) {
        super("No hay usuarios con ese ID: " + id);
    }

    public UbicacionNotFoundException() {
        super("No hay usuarios con esos requisitos de búsqueda");
    }
}
