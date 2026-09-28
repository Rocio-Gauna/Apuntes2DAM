import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrepararCarpetas {
    public static void main(String[] args) {
//Primero declaramos la ruta en memoria.

Path carpeta  = Path.of("datos");
Path archivoClub = carpeta.resolve("clubes.txt");
Path copia = carpeta.resolve("copia");
Path archivoCopia = copia.resolve("respaldo.txt");

try {
    
    Files.createDirectories(copia);

    if(Files.notExists(archivoClub)){
        Files.createFile(archivoClub);
    }

    if (Files.notExists(archivoCopia)) {
        Files.createFile(archivoCopia);
    }

    System.out.println("¿Existe?"+ Files.exists(archivoClub));
    System.out.println("Tamaño"+ Files.size(archivoClub) + " bytes");

    System.out.println("¿Existe?"+ Files.exists(archivoCopia));
    System.out.println( "Tamaño"+ Files.size(archivoCopia) + " bytes");
    

} catch (IOException e) {
    System.err.println("Error al ejercutarlo" + e.getMessage());
}

    }
}
