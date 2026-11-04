package es.curso.ad.t01;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClubDao {
    public int insertar(String nombre, String ciudad,
                        BigDecimal presupuesto) throws SQLException {
        String sql = "INSERT INTO club(nombre, ciudad, presupuesto) VALUES (?, ?, ?)";
        try (Connection conexion = Conexion.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(
                     sql, Statement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, nombre);
            sentencia.setString(2, ciudad);
            sentencia.setBigDecimal(3, presupuesto);
            sentencia.executeUpdate();
            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                }
                throw new SQLException("No se ha generado el ID del club");
            }
        }
    }

    public List<Club> listar() throws SQLException {
        String sql = "SELECT id, nombre, ciudad, presupuesto FROM club ORDER BY id";
        List<Club> clubes = new ArrayList<>();
        try (Connection conexion = Conexion.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet filas = sentencia.executeQuery()) {
            while (filas.next()) {
                clubes.add(leerClub(filas));
            }
        }
        return clubes;
    }

    public Optional<Club> buscar(int id) throws SQLException {
        String sql = "SELECT id, nombre, ciudad, presupuesto FROM club WHERE id = ?";
        try (Connection conexion = Conexion.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            try (ResultSet filas = sentencia.executeQuery()) {
                if (filas.next()) {
                    return Optional.of(leerClub(filas));
                }
            }
        }
        return Optional.empty();
    }

    public boolean cambiarCiudad(int id, String ciudad) throws SQLException {
        String sql = "UPDATE club SET ciudad = ? WHERE id = ?";
        try (Connection conexion = Conexion.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, ciudad);
            sentencia.setInt(2, id);
            return sentencia.executeUpdate() == 1;
        }
    }

    public boolean borrar(int id) throws SQLException {
        String sql = "DELETE FROM club WHERE id = ?";
        try (Connection conexion = Conexion.abrir();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() == 1;
        }
    }

    private Club leerClub(ResultSet filas) throws SQLException {
        return new Club(
                filas.getInt("id"),
                filas.getString("nombre"),
                filas.getString("ciudad"),
                filas.getBigDecimal("presupuesto"));
    }
}
