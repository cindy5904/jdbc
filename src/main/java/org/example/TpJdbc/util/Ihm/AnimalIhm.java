package org.example.TpJdbc.util.Ihm;

import org.example.TpJdbc.DAO.AnimalDAO;
import org.example.TpJdbc.DAO.MealDAO;
import org.example.TpJdbc.entity.Animal;
import org.example.TpJdbc.entity.Meal;
import org.example.TpJdbc.util.SearcheCase;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AnimalIhm {
    private Scanner scanner;
    private AnimalDAO animalDAO;
    private MealDAO mealDAO;

    public AnimalIhm(Scanner scanner, AnimalDAO animalDAO, MealDAO mealDAO) {
        this.scanner = scanner;
        this.animalDAO = animalDAO;
        this.mealDAO = mealDAO;
    }

    public void start (){
        int entry;
        while(true){
            System.out.println("--- Menu animal ---");
            System.out.println("1/ cree un animal");
            System.out.println("2/ recuperer les animaux");
            System.out.println("3/ rechercher animal");
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry){
                case 1:
                    createAnimal();
                    break;
                case 2:
                    getAllAnimal();
                    break;
                case 3 :
                    searchAnimal();
                    break;
                default:
                    return;
            }
        }
    }

    private void createAnimal (){
        System.out.println("-- creation d'animal --");
        System.out.println("nom de l'animal :");
        String name = scanner.nextLine();
        System.out.println("Race de l'animalm :");
        String race = scanner.nextLine();
        System.out.println("Description de l'animal :");
        String description = scanner.nextLine();
        System.out.println("habitat de l'animal");
        String habitat = scanner.nextLine();
        System.out.println("age de l'animal :");
        int age = scanner.nextInt();
        scanner.nextLine();

        Animal animal = Animal.builder().name(name).race(race).description(description).habitat(habitat).age(age).build();

        try {
            Animal animalCreated = animalDAO.save(animal);
            if(animalCreated != null){
                System.out.println("animale cree "+ animalCreated );
            }else{
                System.out.println("erreure lors de la creation");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void getAllAnimal(){
        System.out.println("-- recuperation des animaux --");
        try {
            List<Animal> animals = animalDAO.get();
            int i =0;
            for(Animal animal : animals){
                List<Meal> meals = mealDAO.getMealByAnimalId(animal.getId());
                i++;
                System.out.println(i+"/ "+animal);
                for (Meal meal : meals){
                    System.out.println(meal);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void searchAnimal(){
        System.out.println("-- recherche d'animaux --");
        System.out.println("critere de recherche :");
        System.out.println("1/Nom 2/Age 3/Habitat 4/Race ");
        int value = scanner.nextInt();
        scanner.nextLine();
        String researchCase = "";
        switch (value){
            case  1:
                researchCase = SearcheCase.NAME.toString();
                break;
            case 2:
                researchCase = SearcheCase.AGE.toString();
                break;
            case  3 :
                researchCase = SearcheCase.HABITAT.toString();
                break;
            case 4:
                researchCase = SearcheCase.RACE.toString();
                break;
        }

        System.out.println("Valeur de la recherche :");
        String researcheValue = scanner.nextLine();

        try {
            List<Animal> animals = animalDAO.search(researchCase,researcheValue);
            int i =0;
            for(Animal animal : animals){
                List<Meal> meals = mealDAO.getMealByAnimalId(animal.getId());
                i++;
                System.out.println(i+"/ "+animal);
                for (Meal meal : meals){
                    System.out.println(meal);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
