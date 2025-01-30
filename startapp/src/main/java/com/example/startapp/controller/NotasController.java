package com.example.startapp.controller;

import com.example.startapp.dto.EditNotaDto;
import com.example.startapp.dto.GetNotaDto;
import com.example.startapp.model.Nota;
import com.example.startapp.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nota")
public class NotasController {

    private final NotaService notaService;

    @GetMapping("/")
    public List<GetNotaDto> getAll() {

        return notaService.findAll().stream().map(GetNotaDto::of).toList();
    }

    @GetMapping("/{id}")
    public GetNotaDto getNotaById(@PathVariable Long id) {
        return GetNotaDto.of(notaService.findNotaById(id));
    }

    @PostMapping("/{incidenciaId}")
    public ResponseEntity<Nota> saveNota(@PathVariable Long incidenciaId, @RequestBody EditNotaDto nota) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notaService.saveNota(incidenciaId, nota));
    }

    @PutMapping("/{notaId}")
    public Nota editNota(@PathVariable Long notaId, @RequestBody EditNotaDto nota) {
        return notaService.editNota(notaId, nota);
    }

    @DeleteMapping("/{notaId}/{incidenciaId}")
    public ResponseEntity<?> deleteNota(@PathVariable Long notaId, @PathVariable Long incidenciaId) {
        notaService.removeNota(notaId, incidenciaId);
        return ResponseEntity.noContent().build();
    }
}
