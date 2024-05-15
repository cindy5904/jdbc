package org.example.exo1.DAO;

import org.example.exo1.entity.Film;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FilmDao {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private String request;
    private ResultSet resultSet;

    public FilmDao(Connection connection){
        this.connection = connection;
    }

    public Film createFilm(String titre, String realisateur, String genre) throws SQLException{
        request = "INSERT INTO film (titre,realisateur, genre) VALUE (?,?,?)";
        preparedStatement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,titre);
        preparedStatement.setString(2,realisateur);
        preparedStatement.setString(3, genre);
        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        Film film = null;
        if(resultSet.next()){
            film = film.builder().titre(titre).realisateur(realisateur).genre(genre).id(resultSet.getInt(1)).build();
        }

        return film;
    }

    public List<Film> getAllFilm () throws SQLException {
        List<Film> films = new ArrayList<>();
        request = "SELECT * FROM film";
        preparedStatement = connection.prepareStatement(request);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            films.add(Film.builder().id(resultSet.getInt("id"))
                    .titre(resultSet.getString("titre"))
                    .realisateur(resultSet.getString("realisateur"))
                    .genre(resultSet.getString("genre"))
                    .build());
        }
        return films;
    }

    public void updateFilm(int id, String titre, String realisateur, String genre) throws SQLException {
        request = "UPDATE film SET titre=?, realisateur=?, genre=? WHERE id=?";
        preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, titre);
        preparedStatement.setString(3, realisateur);
        preparedStatement.setString(4, genre);
        preparedStatement.executeUpdate();
    }
}
