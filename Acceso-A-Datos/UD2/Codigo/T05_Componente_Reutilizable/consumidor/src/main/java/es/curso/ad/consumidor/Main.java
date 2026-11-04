package es.curso.ad.consumidor;

import es.curso.ad.componente.Club;
import es.curso.ad.componente.RepositorioClubes;
import es.curso.ad.componente.RepositorioMysql;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3307/ad_ud2?connectionTimeZone=UTC";
        RepositorioClubes repositorio =
                new RepositorioMysql(url, "ad_user", "ad_pass");
        try {
            for (Club club : repositorio.listar()) {
                System.out.println(club);
            }
            System.out.println("ID 2: " + repositorio.buscar(2).orElse(null));
        } catch (SQLException e) {
            System.err.println("Error del componente: " + e.getMessage());
        }
    }
}
