package com.example.startapp;

import com.example.startapp.model.Nota;
import com.example.startapp.repo.IncidenciaRepository;
import com.example.startapp.service.NotaService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataSeed {

    private final NotaService repo;


    @PostConstruct
    public void run() {


        repo.findAll()
                .forEach(System.out::println);


    }

}