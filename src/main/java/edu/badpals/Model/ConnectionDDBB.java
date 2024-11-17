package edu.badpals.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDDBB {

    public Connection createConnection() throws SQLException {
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("user", "root");
        connectionProperties.setProperty("password", "root");
        String urlDB = "jdbc:mysql://localhost:3306/empresa_db";
        return DriverManager.getConnection(urlDB, connectionProperties); // Retorna la conexión
    }
}
