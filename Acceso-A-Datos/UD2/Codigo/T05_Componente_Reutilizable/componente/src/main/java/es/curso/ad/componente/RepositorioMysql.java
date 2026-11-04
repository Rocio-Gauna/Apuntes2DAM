package es.curso.ad.componente;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepositorioMysql implements RepositorioClubes {
    private final String url;
    private final String usuario;
    private final String clave;

    public RepositorioMysql(String url, String usuario, String clave) {
        this.url = url;
        this.usuario = usuario;
        this.clave = clave;
    }

    @Override
    public List<Club> listar() throws SQLException {
        List<Club> resultado = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, usuario, clave);
             PreparedStatement st = con.prepareStatement(
                     "SELECT id, nombre, ciudad FROM club ORDER BY id");
             ResultSet filas = st.executeQuery()) {
            while (filas.next()) {
                resultado.add(construir(filas));
            }
        }
        return resultado;
    }

    @Override
    public Optional<Club> buscar(int id) throws SQLException {
        try (Connection con = DriverManager.getConnection(url, usuario, clave);
             PreparedStatement st = con.prepareStatement(
                     "SELECT id, nombre, ciudad FROM club WHERE id = ?")) {
            st.setInt(1, id);
            try (ResultSet filas = st.executeQuery()) {
                if (filas.next()) return Optional.of(construir(filas));
            }
        }
        return Optional.empty();
    }

    private Club construir(ResultSet filas) throws SQLException {
        return new Club(filas.getInt("id"),
                filas.getString("nombre"), filas.getString("ciudad"));
    }
}
