package com.example.todo.controllers;

import com.example.todo.dto.PersonDTO;
import com.example.todo.dto.PersonIdDTO;
import com.example.todo.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.todo.service.PersonService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping("/register")
    public  ResponseEntity<PersonIdDTO> create(@RequestBody PersonDTO personDTO) {
        //ResponseEntity<Map<String, Object>>
        PersonIdDTO newPerson = personService.register(personDTO);
        //Map<String, Object> response = new HashMap<>();
        //response.put("id", newPerson.getId());
        //response.put("name", newPerson.getName());
        return ResponseEntity.ok(newPerson);
    }

    @PostMapping("/login")
    public ResponseEntity<PersonIdDTO> loginPerson(@RequestBody PersonDTO personDTO) {
        //Map<String, Object>
        PersonIdDTO loginPerson = personService.login(personDTO);
        //Map<String, Object> response = new HashMap<>();
        //response.put("id", loginPerson.getId());
        //response.put("name", loginPerson.getName());
        return ResponseEntity.ok(loginPerson);
    }
}
