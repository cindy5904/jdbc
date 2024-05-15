package org.example.TpJdbc.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Animal {
    private int id;
    private String name;
    private String race;
    private String description;
    private String habitat;
    private int age;
}
