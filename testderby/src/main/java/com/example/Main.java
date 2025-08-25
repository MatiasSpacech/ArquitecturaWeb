
package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:derby:myDB;create=true";
        String createTableSQL = "CREATE TABLE usuarios ("
                + "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                + "nombre VARCHAR(100),"
                + "email VARCHAR(100)"
                + ")";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Tabla 'usuarios' creada correctamente.");
        } catch (SQLException e) {
            if (e.getSQLState().equals("X0Y32")) { // Table already exists
                System.out.println("La tabla 'usuarios' ya existe.");
            } else {
                e.printStackTrace();
            }
        }

        // Insertar 10 usuarios
        String[] nombres = {"Ana", "Luis", "Carlos", "María", "Pedro", "Lucía", "Jorge", "Sofía", "Miguel", "Elena"};
        String[] emails = {"ana@mail.com", "luis@mail.com", "carlos@mail.com", "maria@mail.com", "pedro@mail.com", "lucia@mail.com", "jorge@mail.com", "sofia@mail.com", "miguel@mail.com", "elena@mail.com"};
        String insertSQL = "INSERT INTO usuarios (nombre, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            for (int i = 0; i < 10; i++) {
                pstmt.setString(1, nombres[i]);
                pstmt.setString(2, emails[i]);
                pstmt.executeUpdate();
            }
            System.out.println("10 usuarios insertados correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Recuperar e imprimir todos los usuarios
        String selectSQL = "SELECT id, nombre, email FROM usuarios";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            System.out.println("\nUsuarios en la base de datos:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                System.out.println(id + ": " + nombre + " - " + email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}