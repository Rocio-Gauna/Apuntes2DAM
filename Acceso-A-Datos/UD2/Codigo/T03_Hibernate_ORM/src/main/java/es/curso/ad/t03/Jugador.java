package es.curso.ad.t03;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "jugador")
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private int dorsal;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    protected Jugador() { }

    public Jugador(String nombre, int dorsal, Club club) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.club = club;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public int getDorsal() { return dorsal; }
    public Club getClub() { return club; }
}
