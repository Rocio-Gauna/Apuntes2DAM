import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        RepositorioProductos repositorio =
                new RepositorioTexto(Path.of("datos", "productos.txt"));

        List<Producto> productos = new ArrayList<>();

        // TODO: cargar datos al iniciar.
        // TODO: menú con:
        // 1 Alta
        // 2 Listado
        // 3 Buscar por ID
        // 4 Modificar stock
        // 5 Borrar
        // 6 Guardar
        // 7 Exportar XML
        // 8 Importar XML
        // 9 Exportar JSON
        // 10 Importar JSON
        // 11 Crear copia
        // 12 Restaurar copia
        // 0 Salir
        //
        // IMPORTANTE:
        // - Sin RandomAccessFile.
        // - Main no debe acceder directamente a Files para guardar/cargar productos.
    }
}
