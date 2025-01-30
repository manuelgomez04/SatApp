package com.example.startapp.service;

import com.example.startapp.model.Personal;
import com.example.startapp.repo.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalService {

    private final PersonalRepository personalRepository;

    public List<Personal> findAllPersonal(){
        List <Personal> result = personalRepository.findAll();

        if (result.isEmpty()){
            throw new EntityNotFoundException("No se encontraron personal");
        } else {
            return result;
        }
    }

}
