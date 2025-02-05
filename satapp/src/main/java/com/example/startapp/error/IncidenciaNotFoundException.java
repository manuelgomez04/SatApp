package com.example.startapp.error;

public class IncidenciaNotFoundException extends RuntimeException {
    public IncidenciaNotFoundException(String message) {
        super(message);
    }

    public IncidenciaNotFoundException(Long id) {
        super("No hay incidencia con ese ID: " + id);
    }
    public IncidenciaNotFoundException() {
        super("No hay incidencia con esos requisitos de búsqueda");
    }
}
