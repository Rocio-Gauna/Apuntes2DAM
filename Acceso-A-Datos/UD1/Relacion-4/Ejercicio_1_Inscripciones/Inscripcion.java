public class Inscripcion {
    private final int id;
    private final String persona;
    private final String taller;
    private int plazas;

    public Inscripcion(int id, String persona, String taller, int plazas) {
        this.id = id;
        this.persona = persona;
        this.taller = taller;
        this.plazas = plazas;
    }

    public int getId() { return id; }
    public String getPersona() { return persona; }
    public String getTaller() { return taller; }
    public int getPlazas() { return plazas; }
    public void setPlazas(int plazas) { this.plazas = plazas; }

    @Override
    public String toString() {
        return "[" + id + "] " + persona + " | " + taller + " | plazas: " + plazas;
    }
}
