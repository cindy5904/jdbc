package org.example.TpJdbc.DAO;

import org.example.TpJdbc.entity.Animal;
import org.example.TpJdbc.entity.AnimalEnclos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalEnclosDAO extends BaseDAO<AnimalEnclos> {
    private AnimalDAO animalDao;
    public AnimalEnclosDAO(Connection connection) {
        super(connection);

    }

    @Override
    public AnimalEnclos save(AnimalEnclos element) throws SQLException {
        try{
            request = "INSERT INTO animal_enclos (id_animal,id_enclos) VALUE (?,?)";
            preparedStatement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,element.getId_animal());
            preparedStatement.setInt(2,element.getId_enclos());
            int nbrow = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if(nbrow != 1){
                throw new SQLException();
            }
            if(resultSet.next()){
                element.setId(resultSet.getInt(1));
            }
            _connection.commit();
            return element;
        }catch(SQLException e){
            _connection.rollback();
            return null;
        }
    }
    @Override
    public AnimalEnclos update(AnimalEnclos element) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(AnimalEnclos element) throws SQLException {
        return false;
    }

    @Override
    public AnimalEnclos get(int id) throws SQLException {
        return null;
    }

    @Override
    public List<AnimalEnclos> get() throws SQLException {
        return null;
    }

    public List<Animal> getAllAnimalByEnclosId (int id)throws SQLException{
        List<Animal> animals = new ArrayList<>();
        request ="SELECT * FROM Animal INNER JOIN animal_enclos ON animal_enclos.id_animal = animal.id WHERE animal_enclos.id_enclos = ?";
        preparedStatement = _connection.prepareStatement(request);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            animals.add(Animal.builder().id(resultSet.getInt("animalId"))

                    .build());
        }
        return animals;
    }
}
