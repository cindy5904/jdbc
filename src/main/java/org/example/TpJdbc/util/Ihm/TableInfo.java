package org.example.TpJdbc.util.Ihm;

import org.example.TpJdbc.util.DatabaseManager;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TableInfo {
    public void tableInfo () throws SQLException {
        Connection connection = DatabaseManager.getConnection();
        String tablename = "animal";

        DatabaseMetaData metaData = connection.getMetaData();

        System.out.println("Nom de la base de données "+ metaData.getDatabaseProductName() );
        System.out.println("Version de la base de donnée" + metaData.getDatabaseProductVersion());

        ResultSet tables = metaData.getTables(null,null,tablename,null);
        while (tables.next()){
            String tableCatalog = tables.getString("TABLE_CAT");
            String tableSchema = tables.getString("TABLE_SCHEM");
            String name = tables.getString("TABLE_NAME");
            String type = tables.getString("TABLE_TYPE");

            System.out.println("\nInformation sur la table :");
            System.out.println("Catalogue :" + tableCatalog);
            System.out.println("Schéma :" + tableSchema);
            System.out.println("Nom de la table :" +name);
            System.out.println("Type de table" + type);

            ResultSet columns = metaData.getColumns(null,null,name,null);
            System.out.println("\nColone de la table");
            while (columns.next()){
                String columnName = columns.getString("COLUMN_NAME");
                String columType = columns.getString("TYPE_NAME");
                int columSize = columns.getInt("COLUMN_SIZE");

                System.out.println("Nom de la colone : "+columnName);
                System.out.println("Type de la colone : "+columType);
                System.out.println("Taille colone : "+columSize);
            }
        }
    }
}
