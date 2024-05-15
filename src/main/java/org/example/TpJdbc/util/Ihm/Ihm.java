package org.example.TpJdbc.util.Ihm;

import java.util.Scanner;

public class Ihm {
    private Scanner scanner;

    private AnimalIhm animalIhm;

    private MealIhm mealIhm;

    public Ihm(Scanner scanner, AnimalIhm animalIhm, MealIhm mealIhm) {
        this.scanner = scanner;
        this.animalIhm = animalIhm;
        this.mealIhm = mealIhm;
    }

    public void start (){
        int entry;
        while(true){
            System.out.println("--- Application gestion de zoo ---");
            System.out.println("1/ menu animal");
            System.out.println("2/ menu meal");
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry){
                case 1:
                    animalIhm.start();
                    break;
                case 2:
                    mealIhm.start();
                    break;
                default:
                    return;
            }
        }
    }
}
