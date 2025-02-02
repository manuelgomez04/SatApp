package com.example.startapp.service;

import com.example.startapp.dto.EditPersonalDto;
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

    public List<Personal> getAllPersonal() {
        List<Personal> result = personalRepository.findAll();

        if (result.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron personal");
        } else {
            return result;
        }
    }

    public Personal getPersonalById(Long id) {
        Personal result = personalRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personal no encontrado"));
        return result;
    }

    public Personal savePersonal(EditPersonalDto editPersonalDto) {
        return personalRepository.save(Personal.builder()
                .nombre(editPersonalDto.nombre())
                .email(editPersonalDto.email())
                .role(editPersonalDto.role())
                .password(editPersonalDto.password())
                .username(editPersonalDto.username())
                .build());
    }

}
