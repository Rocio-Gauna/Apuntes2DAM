import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ListarDatos {
        public static void main(String[] args){
        // Construimos la ruta de la carpeta que queremos explorar (no falla, va fuera del try)
        Path carpeta = Path.of("datos");

        // Abrimos el recorrido de la carpeta con try-with-resources,
        // así Java lo cierra automáticamente al terminar, incluso si hay un error
        try (DirectoryStream<Path> elementos = Files.newDirectoryStream(carpeta)) {

            // Recorremos cada elemento que hay dentro de "datos"
            for (Path elemento : elementos) {

                // Comprobamos si es un archivo ordinario (no una subcarpeta)
                if (Files.isRegularFile(elemento)) {

                    // Si es un archivo, mostramos solo su nombre, sin la ruta completa
                    System.out.println(elemento.getFileName());
                }
                // Si no es un archivo ordinario (es una subcarpeta), simplemente lo ignoramos
            }

        } catch (IOException e) {
            // Capturamos el error si "datos" no existe o hay problemas de acceso
            System.err.println("Error al listar la carpeta: " + e.getMessage());
        }
    }
}
