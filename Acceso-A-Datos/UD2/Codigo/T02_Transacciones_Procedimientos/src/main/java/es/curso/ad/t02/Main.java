package es.curso.ad.t02;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        OperacionesClubes operaciones = new OperacionesClubes();
        try {
            BigDecimal origenAntes = operaciones.obtenerPresupuesto(1);
            BigDecimal destinoAntes = operaciones.obtenerPresupuesto(2);
            System.out.println("Antes: " + origenAntes + " / " + destinoAntes);
            operaciones.transferir(1, 2, new BigDecimal("25.00"));
            System.out.println("Después: " + operaciones.obtenerPresupuesto(1) +
                    " / " + operaciones.obtenerPresupuesto(2));
            System.out.println("Jugadores del club 1: " + operaciones.contarJugadores(1));

            try {
                operaciones.transferir(1, 99999, new BigDecimal("10.00"));
            } catch (SQLException e) {
                System.out.println("Rollback esperado: " + e.getMessage());
            }
            System.out.println("Tras rollback: " + operaciones.obtenerPresupuesto(1));
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
    }
}
