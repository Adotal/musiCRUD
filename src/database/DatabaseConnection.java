package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private Connection con;

    public DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/musicrud", "root", "12345");

        } catch (ClassNotFoundException | SQLException ex) {
            System.getLogger(DatabaseConnection.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } 
    }

    public Connection getConnection() {
        return con;
    }

}
