package com.example.startapp.service;

import com.example.startapp.dto.EditPersonalDto;
import com.example.startapp.dto.GetPersonalDto;
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
                .tipo(editPersonalDto.tipo())
                .username(editPersonalDto.username())
                .build());
    }

    public GetPersonalDto editPersonal(Long id, EditPersonalDto editPersonalDto) {
        return personalRepository.findById(id).map(old -> {
            old.setNombre(editPersonalDto.nombre());
            old.setEmail(editPersonalDto.email());
            old.setRole(editPersonalDto.role());
            old.setPassword(editPersonalDto.password());
            old.setTipo(editPersonalDto.tipo());
            old.setUsername(editPersonalDto.username());
            return GetPersonalDto.of(personalRepository.save(old));
        }).orElseThrow(() -> new EntityNotFoundException("Personal no encontrado"));
    }

}
