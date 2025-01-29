package com.example.startapp.controller;

import com.example.startapp.dto.GetNotaDto;
import com.example.startapp.model.Nota;
import com.example.startapp.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nota")
public class NotasController {

    private final NotaService notaService;

    @GetMapping("/")
    public List<GetNotaDto> getAll(){

        return notaService.findAll().stream().map(GetNotaDto::of).toList();
    }

    @GetMapping("/{id}")
    public GetNotaDto getNotaById(@PathVariable Long id){
        return GetNotaDto.of(notaService.findNotaById(id));
    }

    @DeleteMapping("/{notaId}/{incidenciaId}")
    public ResponseEntity<?> deleteNota(@PathVariable Long notaId,@PathVariable Long incidenciaId) {
        notaService.remove(notaId,incidenciaId);
        return ResponseEntity.noContent().build();
    }
}
