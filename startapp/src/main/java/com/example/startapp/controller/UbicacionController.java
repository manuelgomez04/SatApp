package com.example.startapp.controller;

import com.example.startapp.dto.GetUbicacionDto;
import com.example.startapp.service.UbicacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ubicacion")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @PostMapping
    public ResponseEntity<GetUbicacionDto> saveUbicacion(@RequestBody GetUbicacionDto nuevo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GetUbicacionDto.of(ubicacionService.saveUbicacion(nuevo)));
    }
}
