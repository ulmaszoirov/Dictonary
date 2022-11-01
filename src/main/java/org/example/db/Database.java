package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/dic_db", "dic_db", "root");
            return con;
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(-1);
        }

        return null;
    }

    public static void createTable()
    {
        Connection con = null;
        try {
            con = getConnection();
          PreparedStatement statement = con.prepareStatement("create table if not exists word(" +
                                         " id serial unique,   " +
                                          "word varchar,  " +
                                          "translate varchar," +
                                           "description text    );");
          statement.executeUpdate();
        }catch (SQLException e)
        {
            throw  new RuntimeException(e);
        }finally {
             try {
                 con.close();
             }catch (SQLException e)
             {
              e.printStackTrace();
             }
        }


    }
}
