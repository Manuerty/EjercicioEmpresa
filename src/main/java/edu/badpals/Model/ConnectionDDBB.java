package edu.badpals.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDDBB {
    String urlDB ="jdbc:mysql://localhost:3306/empresa_db";

    public Connection createConection() throws SQLException {
        Properties conectionProperties = new Properties();
        conectionProperties.setProperty("user", "root");
        conectionProperties.setProperty("password", "root");
        return DriverManager.getConnection(urlDB, conectionProperties);
    }
}
