import java.io.IOException;
import java.util.List;

public interface RepositorioProductos {
    void guardarTodos(List<Producto> productos) throws IOException;
    List<Producto> cargarTodos() throws IOException;
}
