package com.example.todo.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectDTO {
    Long id;
    String name;
    String description;
    Long resolvedTaskCount;
    Long allTaskCount;
}
