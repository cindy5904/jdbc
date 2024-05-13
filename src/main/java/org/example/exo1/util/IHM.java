package org.example.exo1.util;

import org.example.exo1.DAO.FilmDao;
import org.example.exo1.entity.Film;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class IHM {
    private Connection connection;
    private FilmDao filmDAO;

    private Scanner scanner;

    public IHM (){
        scanner = new Scanner(System.in);
        try{
            connection = Database.getConnection();
            filmDAO = new FilmDao(connection);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void start () {
        int entry;
        while (true) {
            System.out.println("--- Gestion de film ---");
            System.out.println("1/ Creation de film");
            System.out.println("2/ afficher toutes les films");
            entry = scanner.nextInt();
            scanner.nextLine();

            switch (entry) {
                case 1:
                    createFilm();
                    break;
                case 2:
                    getAllFilm();
                    break;
                case 3 :
                    updateFilm();

                default:
                    return;
            }
        }
    }
        private void createFilm(){
            System.out.println("--- creation du film ---");
            System.out.println("nom du film :");
            String titre = scanner.nextLine();
            System.out.println("Realisateur du film:");
            String realisateur = scanner.nextLine();
            System.out.println("genre du film :");
            String genre =  scanner.nextLine();

            try{
                Film film =filmDAO.createFilm(titre, realisateur, genre);
                System.out.println("le film a ete cree" + film);
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }

        private void getAllFilm (){
            try {
                List<Film> films = filmDAO.getAllFilm();
                for(Film film : films){
                    System.out.println(film);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        private void updateFilm() {
            try{
                Film film =filmDAO.updateFilm(titre, realisateur, genre);
                System.out.println("le film a ete modifier " + film);
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }
    }


