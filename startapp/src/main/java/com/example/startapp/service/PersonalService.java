package com.example.startapp.service;

import com.example.startapp.dto.EditPersonalDto;
import com.example.startapp.dto.GetPersonalDto;
import com.example.startapp.error.PersonalNotFoundException;
import com.example.startapp.model.Personal;
import com.example.startapp.repo.PersonalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonalService {

    private final PersonalRepository personalRepository;

    public List<Personal> getAllPersonal() {
        List<Personal> result = personalRepository.findAll();

        if (result.isEmpty()) {
            throw new PersonalNotFoundException("No se encontraron personal");
        } else {
            return result;
        }
    }

    public Personal getPersonalById(Long id) {

        Optional <Personal> result = personalRepository.findById(id);

        if (result.isEmpty()) {
            throw new PersonalNotFoundException("Personal no encontrado");
        } else {
            return result.get();
        }
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

    public Personal editPersonal(Long id, EditPersonalDto editPersonalDto) {
        return personalRepository.findById(id).map(old -> {
            old.setNombre(editPersonalDto.nombre());
            old.setEmail(editPersonalDto.email());
            old.setRole(editPersonalDto.role());
            old.setPassword(editPersonalDto.password());
            old.setTipo(editPersonalDto.tipo());
            old.setUsername(editPersonalDto.username());
            return personalRepository.save(old);
        }).orElseThrow(() -> new PersonalNotFoundException("Personal no encontrado"));
    }

}
