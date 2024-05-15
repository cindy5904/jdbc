package org.example.TpJdbc.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Meal {
    private int id;
    private String description;
    private LocalDateTime mealDate;
    private Animal animal;
}
