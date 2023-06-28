package com.DataBase;

import java.sql.*;

public class Conexion {

    private final String user = "sql9627390";
    private final String pass = "zlz2f7cWFN";
    private final String url = "jdbc:mysql://sql9.freesqldatabase.com:3306/sql9627390";

    public Connection getConexion() {
        Connection connect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("No te pudiste conectar");
        }
        return connect;
    }
}
