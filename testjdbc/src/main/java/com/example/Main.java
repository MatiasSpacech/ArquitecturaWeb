package com.example;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String url = "jdbc:derby:memory:myDB;create=true";
            try (java.sql.Connection conn = java.sql.DriverManager.getConnection(url)) {
                String createTableSQL = "CREATE TABLE usuarios (" +
                        "dni VARCHAR(100) PRIMARY KEY, " +
                        "nombre VARCHAR(100), " +
                        "apellido VARCHAR(100), " +
                        "contrasenia VARCHAR(100))";
                try (java.sql.Statement stmt = conn.createStatement()) {
                    stmt.executeUpdate(createTableSQL);
                    System.out.println("Tabla 'usuarios' creada correctamente.");

                    // Insertar 10 usuarios de ejemplo
                    for (int i = 1; i <= 10; i++) {
                        String dni = String.format("%08d", i);
                        String nombre = "Nombre" + i;
                        String apellido = "Apellido" + i;
                        String contrasenia = "pass" + i;
                        String insertSQL = String.format(
                            "INSERT INTO usuarios (dni, nombre, apellido, contrasenia) VALUES ('%s', '%s', '%s', '%s')",
                            dni, nombre, apellido, contrasenia
                        );
                        stmt.executeUpdate(insertSQL);
                    }
                    System.out.println("10 usuarios insertados correctamente.");
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}