package org.example.TpJdbc.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimalEnclos {
    private int id;
    private int id_animal;
    private int id_enclos;
}
