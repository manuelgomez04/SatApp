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

        Optional<Personal> result = personalRepository.findById(id);

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
        Optional<Personal> personal = personalRepository.findById(id);

        if (personal.isEmpty()) {
            throw new PersonalNotFoundException("Personal no encontrado");
        } else {
            personal.map(p -> {
                p.setNombre(editPersonalDto.nombre());
                p.setEmail(editPersonalDto.email());
                p.setRole(editPersonalDto.role());
                p.setPassword(editPersonalDto.password());
                p.setTipo(editPersonalDto.tipo());
                p.setUsername(editPersonalDto.username());
                return personalRepository.save(p);
            });
        }

        return personal.get();
    }


}
