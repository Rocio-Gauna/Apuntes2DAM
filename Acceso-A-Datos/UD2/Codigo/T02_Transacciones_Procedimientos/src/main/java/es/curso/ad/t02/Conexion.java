package es.curso.ad.t02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Conexion {
    private Conexion() { }
    public static Connection abrir() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3307/ad_ud2?connectionTimeZone=UTC",
                "ad_user", "ad_pass");
    }
}
