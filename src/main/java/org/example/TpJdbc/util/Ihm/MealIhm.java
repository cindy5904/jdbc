package org.example.TpJdbc.util.Ihm;

import org.example.TpJdbc.DAO.AnimalDAO;
import org.example.TpJdbc.DAO.MealDAO;
import org.example.TpJdbc.entity.Animal;
import org.example.TpJdbc.entity.Meal;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MealIhm {
    private Scanner scanner;
    private MealDAO mealDAO;
    private AnimalDAO animalDAO;

    public MealIhm (Scanner scanner){
        this.scanner = scanner;
        mealDAO = new MealDAO();
        animalDAO = new AnimalDAO();
    }

    public void start (){
        int entry;
        while(true){
            System.out.println("--- Menu meal ---");
            System.out.println("1/ cree repas");
            System.out.println("2/ recuperer repas");
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry){
                case 1:
                    createMeal();
                    break;
                case 2:
                    getAllMeal();
                    break;
                default:
                    return;
            }
        }
    }


    private void createMeal (){
        System.out.println("-- creation du repas --");
        System.out.println("Id de l'animal :");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Animal animalFound = animalDAO.get(id);
            System.out.println("animal a nourrir " + animalFound );

            System.out.println("Description du repas :");
            String description = scanner.nextLine();
            LocalDateTime dateMeal = LocalDateTime.now();

            Meal mealCreated = mealDAO.save(Meal.builder().description(description).animal(animalFound).mealDate(dateMeal).build());

            if(mealCreated != null){
                System.out.println("Repas crée : " +mealCreated);
            }
            else{
                System.out.println("Erreure lors de la creation");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void getAllMeal(){
        System.out.println("-- recuperation des repas --");
        try {
            List<Meal> meals = mealDAO.get();
            int i =0;
            for(Meal meal : meals){
                i++;
                System.out.println(i+"/ "+meal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
