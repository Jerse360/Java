package Conexion;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    public Connection getConnection() {
        Connection connection = null;

        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio","root","");
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
        return connection;

    }
}
