package com.example.todo.controllers;

import com.example.todo.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.todo.service.PersonService;
import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor

public class PersonController {
    private final PersonService personService;

    @PostMapping("/register")
    public Person create(@RequestBody Person person) {
        return ResponseEntity.ok(personService.register(person)).getBody();
    }



}
