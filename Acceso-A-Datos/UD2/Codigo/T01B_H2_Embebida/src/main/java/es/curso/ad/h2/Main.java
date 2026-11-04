package es.curso.ad.h2;

import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Path carpeta = Path.of("datos");
        try {
            Files.createDirectories(carpeta);
            String url = "jdbc:h2:file:./datos/clubes_h2";
            try (Connection conexion = DriverManager.getConnection(url, "sa", "")) {
                try (Statement ddl = conexion.createStatement()) {
                    ddl.executeUpdate("CREATE TABLE IF NOT EXISTS club (" +
                            "id INT PRIMARY KEY, " +
                            "nombre VARCHAR(90) NOT NULL, " +
                            "ciudad VARCHAR(90) NOT NULL)");
                }
                try (PreparedStatement alta = conexion.prepareStatement(
                        "MERGE INTO club KEY(id) VALUES (?, ?, ?)")) {
                    alta.setInt(1, 1);
                    alta.setString(2, "Granada CF");
                    alta.setString(3, "Granada");
                    alta.executeUpdate();
                }
                try (PreparedStatement consulta = conexion.prepareStatement(
                        "SELECT id, nombre, ciudad FROM club ORDER BY id");
                     ResultSet filas = consulta.executeQuery()) {
                    while (filas.next()) {
                        System.out.println(filas.getInt("id") + " | " +
                                filas.getString("nombre") + " | " +
                                filas.getString("ciudad"));
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("No se pudo operar con H2: " + e.getMessage());
        }
    }
}
