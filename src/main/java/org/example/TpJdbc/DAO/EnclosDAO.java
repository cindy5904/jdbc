package org.example.TpJdbc.DAO;

import org.example.TpJdbc.entity.Animal;
import org.example.TpJdbc.entity.Enclos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EnclosDAO extends BaseDAO<Enclos>{
    private AnimalEnclosDAO animalEnclosDAO;
    public EnclosDAO(Connection connection) {
        super(connection);
        animalEnclosDAO = new AnimalEnclosDAO(connection);
    }
    @Override
    public Enclos save(Enclos element) throws SQLException {
        try{
            request = "INSERT INTO enclos (name) VALUE (?)";
            preparedStatement = _connection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(element.getId()));
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
    public Enclos update(Enclos element) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(Enclos element) throws SQLException {
        return false;
    }

    @Override
    public Enclos get(int id) throws SQLException {
        request = "SELECT * FROM enclos WHERE id = ?";
        preparedStatement = _connection.prepareStatement(request);
        preparedStatement.setInt(1,id);
        resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            return Enclos.builder().id(resultSet.getInt("id")).animals(animalEnclosDAO.getAllAnimalByEnclosId(id)).build();
        }
        return null;
    }

    @Override
    public List<Enclos> get() throws SQLException {
        return null;
    }


}
