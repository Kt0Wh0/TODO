package com.example.todo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class PersonIdDTO {
    private Long id;
    private String name;
}
