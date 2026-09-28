
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InspectorFichero {

    public static void main(String[] args) {
        Path archivo = Path.of("datos", "clubes.txt");
        // muestra la ruta absoluta.
        System.out.println("Ruta absoluta:");
        System.out.println(archivo.toAbsolutePath());

        try {
            if (Files.exists(archivo)) {
                System.out.println("El archivo existe " + Files.size(archivo));
            } else {
                System.out.println("El archivo no existe. No se puede mostrar el tamaño");
            }

        } catch (IOException e) {
            System.err.println("Error " + e.getMessage());
        }
    }
}
