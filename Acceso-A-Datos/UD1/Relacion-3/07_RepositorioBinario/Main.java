import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        RepositorioProductos repositorio =
                new RepositorioBinario(Path.of("datos", "productos.dat"));

        // TODO: cargar y mostrar utilizando SOLO la interfaz.
    }
}
