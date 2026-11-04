package es.curso.ad.t01;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Importación completa: primero valida el TXT y después hace INSERT transaccional. */
public class ImportarTxt {
    public record ClubTxt(int id, String nombre, String ciudad) { }

    public List<ClubTxt> validar(Path archivo) throws IOException {
        List<ClubTxt> clubes = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();
        for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
            if (linea.isBlank()) continue;
            String[] campos = linea.split(";", -1);
            if (campos.length != 3 || campos[1].isBlank() || campos[2].isBlank()) {
                throw new IllegalArgumentException("Línea incorrecta: " + linea);
            }
            int id;
            try {
                id = Integer.parseInt(campos[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("ID incorrecto: " + linea, e);
            }
            if (id <= 0 || !ids.add(id)) {
                throw new IllegalArgumentException("ID duplicado/no válido: " + id);
            }
            clubes.add(new ClubTxt(id, campos[1].trim(), campos[2].trim()));
        }
        return clubes;
    }

    public int importar(Path archivo) throws IOException, SQLException {
        List<ClubTxt> validados = validar(archivo);
        try (Connection conexion = Conexion.abrir()) {
            conexion.setAutoCommit(false);
            try (PreparedStatement sentencia = conexion.prepareStatement(
                    "INSERT INTO club(id, nombre, ciudad, presupuesto) " +
                    "VALUES (?, ?, ?, ?)")) {
                for (ClubTxt club : validados) {
                    sentencia.setInt(1, club.id());
                    sentencia.setString(2, club.nombre());
                    sentencia.setString(3, club.ciudad());
                    sentencia.setBigDecimal(4, BigDecimal.ZERO);
                    sentencia.executeUpdate();
                }
                conexion.commit();
                return validados.size();
            } catch (SQLException | RuntimeException e) {
                try {
                    conexion.rollback();
                } catch (SQLException rollbackError) {
                    e.addSuppressed(rollbackError);
                }
                throw e;
            }
        }
    }
}
