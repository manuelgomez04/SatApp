package com.example.startapp.error;

public class AlumnoNotFoundException extends RuntimeException {

    public AlumnoNotFoundException(String message) {
        super(message);
    }
    public AlumnoNotFoundException(Long id) {
        super("No hay usuarios con ese ID: " + id);
    }
    public AlumnoNotFoundException() {
        super("No hay usuarios con esos requisitos de búsqueda");
    }


}
