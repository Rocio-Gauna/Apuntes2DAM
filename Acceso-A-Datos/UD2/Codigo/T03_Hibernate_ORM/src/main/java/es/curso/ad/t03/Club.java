package es.curso.ad.t03;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 90)
    private String nombre;

    @Column(nullable = false, length = 90)
    private String ciudad;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal presupuesto;

    protected Club() { }

    public Club(String nombre, String ciudad, BigDecimal presupuesto) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.presupuesto = presupuesto;
    }

    public Integer getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }
    public BigDecimal getPresupuesto() { return presupuesto; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    @Override
    public String toString() {
        return id + " | " + nombre + " | " + ciudad + " | " + presupuesto;
    }
}
