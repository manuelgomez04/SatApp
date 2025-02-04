package com.example.startapp.dto;

import com.example.startapp.model.HistoricoCursos;

public record GetHistoricoDto(
        String curso,
        String cursoEscolar
) {
    public static GetHistoricoDto of(HistoricoCursos historicoCursos) {
        return new GetHistoricoDto(
                historicoCursos.getCurso(),
                historicoCursos.getCursoEscolar()
        );
    }

    

}
