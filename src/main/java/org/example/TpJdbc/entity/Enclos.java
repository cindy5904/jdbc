package org.example.TpJdbc.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Enclos {
    private int id;
    private List<Animal> animals;
}
