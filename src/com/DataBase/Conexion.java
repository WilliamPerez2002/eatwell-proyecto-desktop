package com.DataBase;

import java.sql.*;

public class Conexion {

    private final String user = "sql10632847";
    private final String pass = "DJzHQRP6Qd";
    private final String url = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10632847";

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
