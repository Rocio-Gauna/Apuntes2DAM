package es.curso.ad.t03;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ClubOrmDao {
    private final SessionFactory factoria;

    public ClubOrmDao(SessionFactory factoria) {
        this.factoria = factoria;
    }

    public List<Club> listar() {
        try (Session sesion = factoria.openSession()) {
            return sesion.createQuery("from Club c order by c.id", Club.class)
                    .getResultList();
        }
    }

    public List<String> jugadoresDeCiudad(String ciudad) {
        try (Session sesion = factoria.openSession()) {
            return sesion.createQuery(
                    "select j.nombre from Jugador j join j.club c " +
                    "where c.ciudad = :ciudad order by j.nombre", String.class)
                    .setParameter("ciudad", ciudad)
                    .getResultList();
        }
    }

    public boolean cambiarCiudad(int id, String nuevaCiudad) {
        try (Session sesion = factoria.openSession()) {
            Transaction transaccion = sesion.beginTransaction();
            try {
                Club club = sesion.find(Club.class, id);
                if (club == null) {
                    transaccion.rollback();
                    return false;
                }
                club.setCiudad(nuevaCiudad); // Dirty checking: no UPDATE manual
                transaccion.commit();
                return true;
            } catch (RuntimeException e) {
                if (transaccion.isActive()) transaccion.rollback();
                throw e;
            }
        }
    }

    public int anadirJugador(int clubId, String nombre, int dorsal) {
        try (Session sesion = factoria.openSession()) {
            Transaction transaccion = sesion.beginTransaction();
            try {
                Club club = sesion.find(Club.class, clubId);
                if (club == null) {
                    throw new IllegalArgumentException("Club no encontrado");
                }
                Jugador jugador = new Jugador(nombre, dorsal, club);
                sesion.persist(jugador);
                transaccion.commit();
                return jugador.getId();
            } catch (RuntimeException e) {
                if (transaccion.isActive()) transaccion.rollback();
                throw e;
            }
        }
    }
}
