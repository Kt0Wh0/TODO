package com.example.todo.service;

import com.example.todo.dto.PersonDTO;
import com.example.todo.dto.PersonIdDTO;
import com.example.todo.model.Person;
import com.example.todo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class PersonService {
    private final PersonRepository personRepository;

    public PersonIdDTO register(PersonDTO personDTO) {
        Person person = new Person();
        PersonIdDTO personIdDTO = new PersonIdDTO();

        person.setName(personDTO.getName());
        person.setPass(personDTO.getPass());
        personRepository.save(person);
        personIdDTO.setId(person.getId());
        personIdDTO.setName(person.getName());

        return personIdDTO;
    }

    public PersonIdDTO login(PersonDTO personDTOLogin) {
        PersonIdDTO personIdDTO = new PersonIdDTO();

        Person person = personRepository.findByNameAndPass(personDTOLogin.getName(), personDTOLogin.getPass());
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        personIdDTO.setId(person.getId());
        personIdDTO.setName(person.getName());

        return personIdDTO;
    }


}
