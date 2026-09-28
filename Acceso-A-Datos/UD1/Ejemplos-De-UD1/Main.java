import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
public class Main {
public static void main(String[] args) {
Path carpeta = Path.of("datos");
Path archivo = carpeta.resolve("clubes.txt");
try {
Files.createDirectories(carpeta);
if (Files.notExists(archivo)) {
Files.createFile(archivo);
}
System.out.println("Ruta: " + archivo.toAbsolutePath());
System.out.println("¿Existe?: " + Files.exists(archivo));
System.out.println("Tamaño: " + Files.size(archivo) + " bytes");
} catch (IOException e) {
System.err.println("No se pudo acceder al archivo: "
+ e.getMessage());
}
}
}