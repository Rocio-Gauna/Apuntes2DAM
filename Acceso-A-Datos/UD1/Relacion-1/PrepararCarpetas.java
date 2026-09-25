import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrepararCarpetas {
    public static void main(String[] args) {
        // Definir las rutas a preparar

        Path carpeta = Path.of("datos");
        Path copia = carpeta.resolve("copias");
        Path archivoClubes = carpeta.resolve("clubes.txt");
        Path archivosCopias = copia.resolve("respaldo.txt");

        try {
            //crea datos y datos/copia 
            Files.createDirectories(copia);

            // creamos el archivo o fichero de clubes con la condicion de si no existe creamelo y si existiera ccreamos de nuevo 

            if (Files.notExists(archivoClubes)){
                Files.createFile(archivoClubes);
            }

            if(Files.notExists(archivosCopias)){
                Files.createFile(archivosCopias);
            }

            // mostramos el estado del fichero

            System.out.println("clubes.txt existe: " + Files.exists(archivoClubes)
                    + " | tamaño: " + Files.size(archivoClubes) + " bytes");

            System.out.println("respaldo.txt existe: " + Files.exists(archivosCopias)
                    + " | tamaño: " + Files.size(archivosCopias) + " bytes");
        } catch (IOException e) {

            // si algo falla mostramos 
            System.err.println("Error al preparar las carpetas: " + e.getMessage());
        }

        }

    }
