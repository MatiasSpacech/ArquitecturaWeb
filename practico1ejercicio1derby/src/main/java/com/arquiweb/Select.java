package com.arquiweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
    public static void main(String[] args) {
        String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            System.out.println("El driver se ha cargado correctamente.");
        } catch (Exception e) {
            System.err.println("fallo al cargar el driver: ");
            System.exit(1);
        }
        String uri = "jdbc:derby:myDerbyDB;create=true";
        try  {
            Connection conn = DriverManager.getConnection(uri);
            System.out.println("Conexion a la base de datos establecida.");
            String select = "SELECT * FROM persona";
            PreparedStatement ps = conn.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1)+ ", "+ rs.getString(2)+", "+ rs.getInt(3));            
            }
            conn.close();
            System.out.println("Conexion cerrada.");
        } catch (SQLException e) {
            System.err.println("fallo al conectar a la base de datos: " + e.getMessage());
            System.exit(1);
        }

    }

   
}