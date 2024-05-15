package org.example.TpJdbc.DAO;

import org.example.TpJdbc.entity.Animal;
import org.example.TpJdbc.util.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends BaseDAO<Animal>{
    protected AnimalDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Animal save(Animal element) throws SQLException {
        try{
            _connection = DatabaseManager.getConnection();
            request = "INSERT INTO animal (name,race,description,habitat,age) VALUE (?,?,?,?,?)";
            preparedStatement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,element.getName());
            preparedStatement.setString(2,element.getRace());
            preparedStatement.setString(3,element.getDescription());
            preparedStatement.setString(4,element.getHabitat());
            preparedStatement.setInt(5,element.getAge());
            int row = preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            if(row != 1){
                throw new SQLException();
            }
            if(resultSet.next()){
                element.setId(resultSet.getInt(1));
            }
            _connection.commit();
            return element;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            _connection.rollback();
            return null;
        }finally {
            close();
        }
    }

    @Override
    public boolean delete(Animal element) throws SQLException {
        return false;
    }

    @Override
    public Animal update(Animal element) throws SQLException {
        return null;
    }

    @Override
    public Animal get(int id) throws SQLException {
        try{
            _connection = DatabaseManager.getConnection();
            request = "SELECT * FROM animal WHERE id = ?";
            preparedStatement = _connection.prepareStatement(request);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Animal.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .race(resultSet.getString("race"))
                        .description(resultSet.getString("description"))
                        .habitat(resultSet.getString("habitat"))
                        .age(resultSet.getInt("age"))
                        .build();
            }
            return null;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }finally {
            close();
        }
    }

    @Override
    public List<Animal> get() throws SQLException {
        try{
            List<Animal> animals = new ArrayList<>();
            _connection = DatabaseManager.getConnection();
            request = "SELECT * FROM animal";
            preparedStatement = _connection.prepareStatement(request);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                animals.add(Animal.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .race(resultSet.getString("race"))
                        .description(resultSet.getString("description"))
                        .habitat(resultSet.getString("habitat"))
                        .age(resultSet.getInt("age"))
                        .build());
            }
            return animals;
        }catch (SQLException e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }finally {
            close();
        }
    }

    public List<Animal> search (String searcheValue, String value) throws SQLException{
        try {
            List<Animal> animals = new ArrayList<>();
            _connection = DatabaseManager.getConnection();
            switch (searcheValue){
                case  "AGE":
                    request="SELECT * FROM animal WHERE age = ?";
                    break;
                default:
                    request="SELECT * FROM animal WHERE " +searcheValue+ " LIKE ?";
                    value = "%"+value+"%";
                    break;
            }
            preparedStatement = _connection.prepareStatement(request);
            preparedStatement.setString(1,value);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                animals.add(Animal.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .race(resultSet.getString("race"))
                        .description(resultSet.getString("description"))
                        .habitat(resultSet.getString("habitat"))
                        .age(resultSet.getInt("age"))
                        .build());
            }
            return animals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close();
        }

    }

}
