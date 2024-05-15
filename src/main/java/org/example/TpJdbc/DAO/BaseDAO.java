package org.example.TpJdbc.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {

    protected String request;
    protected ResultSet resultSet;
    protected Connection _connection;
    protected PreparedStatement preparedStatement;

    protected BaseDAO (Connection connection){
        this._connection = connection;
    }

    public abstract T save (T element) throws SQLException;
    public abstract boolean delete (T element) throws SQLException;
    public abstract T update (T element) throws SQLException;
    public abstract T get (int id) throws SQLException;
    public abstract List<T> get () throws SQLException;


}
