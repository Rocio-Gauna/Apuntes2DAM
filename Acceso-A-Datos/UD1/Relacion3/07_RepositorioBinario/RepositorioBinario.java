import java.nio.file.Path;

public class RepositorioBinario implements RepositorioProductos {

    private final Path archivo;

    public RepositorioBinario(Path archivo) {
        this.archivo = archivo;
    }

    // TODO: implementar guardarTodos(...) con ObjectOutputStream.
    // TODO: implementar cargarTodos(...) con ObjectInputStream.
    // TODO: validar tipos al recuperar.
}
