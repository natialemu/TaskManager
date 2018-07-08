package com.natialemu.taskmanager.DataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {



    public static Connection getDatabaseConnection(){
        return openConnection();

    }

    private static Connection openConnection() {
        Connection connection = null;
        DBConfig config = new DBConfig();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(config.getDbURL(), config.getUsername(), config.getPassword());


        } catch (SQLException exception) {
            exception.printStackTrace();
            //exception.printStackTrace();

        } catch (ClassNotFoundException es) {

            //es.printStackTrace();
        }

        return connection;

    }
}
