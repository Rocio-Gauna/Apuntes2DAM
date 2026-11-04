package es.curso.ad.t03;

import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        try (SessionFactory factoria = HibernateUtil.crear()) {
            ClubOrmDao dao = new ClubOrmDao(factoria);
            System.out.println("CLUBES CON HIBERNATE");
            dao.listar().forEach(System.out::println);
            System.out.println("Jugadores de Granada: " +
                    dao.jugadoresDeCiudad("Granada"));
            System.out.println("Actualizar ID 1: " +
                    dao.cambiarCiudad(1, "Granada"));

            // Una sola vez, si se desea probar INSERT:
            // System.out.println("Jugador ID: " +
            //         dao.anadirJugador(1, "Iván", 11));
        } catch (RuntimeException e) {
            System.err.println("Error ORM: " + e.getMessage());
        }
    }
}
