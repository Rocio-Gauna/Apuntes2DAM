import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InspectorFichero {
    public static void main(String[] args) {
        Path archivo = Path.of("datos", "clubes.txt");

        System.out.println("Ruta absoluta:");
        System.out.println(archivo.toAbsolutePath());

        if (Files.exists(archivo)) {
            System.out.println("El archivo existe.");

            try {
                long tamanio = Files.size(archivo);
                System.out.println("Tamaño: " + tamanio + " bytes");
            } catch (IOException e) {
                System.out.println("Error al acceder al archivo:");
                System.out.println(e.getMessage());
            }

        } else if (Files.notExists(archivo)) {
            System.out.println("El archivo no existe.");
        } else {
            System.out.println("No se puede determinar si el archivo existe.");
        }
    }
}