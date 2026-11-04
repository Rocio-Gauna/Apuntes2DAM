package es.curso.ad.componente;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface RepositorioClubes {
    List<Club> listar() throws SQLException;
    Optional<Club> buscar(int id) throws SQLException;
}
