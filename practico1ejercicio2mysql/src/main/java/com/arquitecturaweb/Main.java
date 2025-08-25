package com.arquitecturaweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {        
        String driver = "com.mysql.cj.jdbc.Driver";//"org.apache.derby.jdbc.EmbeddedDriver";
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
            System.out.println("El driver se ha cargado correctamente.");
        } catch (Exception e) {
            System.err.println("fallo al cargar el driver: ");
            System.exit(1);
        }
        String uri = "jdbc:mysql://localhost:3306/exampleDB";//"jdbc:derby:myDerbyDB;create=true";
        try  {
            Connection conn = DriverManager.getConnection(uri, "root", "1234");
            conn.setAutoCommit(false);
            System.out.println("Conexion a la base de datos establecida.");
            createTables(conn);
            addPersona(conn, 1, "Matias", 25);
            addPersona(conn, 2, "Juan", 30);
            addPersona(conn, 3, "Ana", 22);
            conn.close();
            System.out.println("Conexion cerrada.");
        } catch (SQLException e) {
            System.err.println("fallo al conectar a la base de datos: " + e.getMessage());
            System.exit(1);
        }

    }

    private static void addPersona(Connection conn, int id, String name, int years) {
        String insert = "INSERT INTO persona (id, nombre, edad) VALUES (?, ?, ?)"; // ? placehosders para evitar sql injection

        try {
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, years);
            ps.executeUpdate();
            ps.close();
            conn.commit();           
        } catch (SQLException e) {
           
        }
    }

    private static void createTables(Connection conn) {
        String table = "CREATE TABLE persona ("+
        "id INT, "+
        "nombre VARCHAR(500), "+
        "edad INT, "+
        "PRIMARY KEY (id))";
        try {
            conn.prepareStatement(table).execute();
            System.out.println("Tabla creada correctamente.");
            conn.commit();
        } catch (SQLException e) {
            System.err.println("fallo al crear la tabla: " + e.getMessage());
            System.exit(1); 
        }
    
    }
}
