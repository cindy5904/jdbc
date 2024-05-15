package org.example.TpJdbc.DAO;

import org.example.TpJdbc.entity.Animal;
import org.example.TpJdbc.util.DatabaseManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends BaseDAO<Animal>{
    @Override
    public Animal save(Animal element) throws SQLException {
        try{
            connection = DatabaseManager.getConnection();
            request = "INSERT INTO animal (name,race,description,habitat,age) VALUE (?,?,?,?,?)";
            statement = connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,element.getName());
            statement.setString(2,element.getRace());
            statement.setString(3,element.getDescription());
            statement.setString(4,element.getHabitat());
            statement.setInt(5,element.getAge());
            int row = statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(row != 1){
                throw new SQLException();
            }
            if(resultSet.next()){
                element.setId(resultSet.getInt(1));
            }
            connection.commit();
            return element;

        }catch (SQLException e){
            System.out.println(e.getMessage());
            connection.rollback();
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
            connection = DatabaseManager.getConnection();
            request = "SELECT * FROM animal WHERE id = ?";
            statement = connection.prepareStatement(request);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
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
            connection = DatabaseManager.getConnection();
            request = "SELECT * FROM animal";
            statement = connection.prepareStatement(request);
            resultSet = statement.executeQuery();
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
            connection = DatabaseManager.getConnection();
            switch (searcheValue){
                case  "AGE":
                    request="SELECT * FROM animal WHERE age = ?";
                    break;
                default:
                    request="SELECT * FROM animal WHERE " +searcheValue+ " LIKE ?";
                    value = "%"+value+"%";
                    break;
            }
            statement = connection.prepareStatement(request);
            statement.setString(1,value);
            resultSet = statement.executeQuery();
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
