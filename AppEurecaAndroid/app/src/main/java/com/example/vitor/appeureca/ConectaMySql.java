package com.example.vitor.appeureca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConectaMySql {

    public static final String URL = "jdbc:mysql://localhost/appeureca";
    public static final String USER = "root";
    public static final String SENHA = "";

    public static Connection obtemConexao() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, SENHA);

    }
}
