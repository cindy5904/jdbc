package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo_jdbc";
        String user = "root";
        String password = "root";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            if (connection != null){
                System.out.println("Connection à la BDD ok");
                String request = "INSERT INTO person (firstname, lastname) VALUE ('toto', 'tata')";
                Statement statement = connection.createStatement();
                boolean res= statement.execute(request);
                System.out.println("Requete executée");
                if(res) {
                    System.out.println("des données renvoyées par la requête");
                } else {
                    System.out.println("Aucune données renvoyées par la requête");
                }

                String request2 = "SELECT * FROM person";
                Statement statement2 = connection.createStatement();
                ResultSet result = statement2.executeQuery(request2);
                while(result.next()) {
                    System.out.println(result.getString("firstname") + " " + result.getString("lastname"));
                }
            } else {
                System.out.println("Connexion echoué");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }


    }
}