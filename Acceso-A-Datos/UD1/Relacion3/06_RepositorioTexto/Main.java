import java.nio.file.Path;

public class Main {

    public static void main(String[] args) {

        RepositorioProductos repositorio =
                new RepositorioTexto(Path.of("datos", "productos.txt"));

        // TODO: cargar los productos a través del repositorio.
        // TODO: mostrarlos.
        // IMPORTANTE: Main no debe utilizar directamente Files.
    }
}
