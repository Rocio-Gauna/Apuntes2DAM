import java.nio.file.Path;
import java.util.List;

public class ExportarProductosXml {

    public static void main(String[] args) {

        Path archivo = Path.of("datos", "productos.xml");


        // TODO 1: crear una lista de productos.
        List<Producto> productos = List.of(
            crearProducto(1, "Producto 1", 10.0),
            crearProducto(2, "Producto 2", 20.0),
            crearProducto(3, "Producto 3", 30.0)
        );
        // TODO 2: crear un Document y la raíz <productos>.
        // TODO 3: añadir un <producto> por cada objeto.
        // TODO 4: guardar con Transformer en UTF-8 e indentado.
    }


