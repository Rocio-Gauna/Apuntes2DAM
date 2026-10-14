import java.nio.file.Path;
import java.util.List;

public class RepositorioTexto implements RepositorioProductos {

    private final Path archivo;

    public RepositorioTexto(Path archivo) {
        this.archivo = archivo;
    }

    @Override
    public void guardarTodos(List<Producto> productos) {
        throw new UnsupportedOperationException("Pendiente de implementar");
    }

    @Override
    public List<Producto> cargarTodos() {
        throw new UnsupportedOperationException("Pendiente de implementar");
    }
}
