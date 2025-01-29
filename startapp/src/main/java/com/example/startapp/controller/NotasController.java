package com.example.startapp.controller;

import com.example.startapp.model.Nota;
import com.example.startapp.service.NotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/nota")
public class NotasController {

    private final NotaService notaService;

    @GetMapping("/")
    public List<Nota> getAll(){
        return notaService.findAll();
    }

}
