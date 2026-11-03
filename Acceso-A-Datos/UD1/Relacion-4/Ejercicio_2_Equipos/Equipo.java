import java.io.Serializable;

public class Equipo implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int id;
    private final String nombre;
    private int unidades;

    public Equipo(int id, String nombre, int unidades) {
        this.id = id;
        this.nombre = nombre;
        this.unidades = unidades;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getUnidades() { return unidades; }
    public void setUnidades(int unidades) { this.unidades = unidades; }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre + " | unidades: " + unidades;
    }
}
