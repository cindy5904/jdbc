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
            System.out.println("3/ Modifier un film");
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
                    break;
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
            System.out.println("--- Modification du film ---");
            System.out.println("Entrez l'identifiant du film à modifier :");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("Nouveau titre du film :");
            String nouveauTitre = scanner.nextLine();
            System.out.println("Nouveau réalisateur du film :");
            String nouveauRealisateur = scanner.nextLine();
            System.out.println("Nouveau genre du film :");
            String nouveauGenre = scanner.nextLine();

            try {
                filmDAO.updateFilm(id, nouveauTitre, nouveauRealisateur, nouveauGenre);
                System.out.println("Le film a été modifié avec succès.");
            } catch (SQLException e) {
                throw new RuntimeException("Erreur lors de la modification du film.", e);
            }


        }
    }


