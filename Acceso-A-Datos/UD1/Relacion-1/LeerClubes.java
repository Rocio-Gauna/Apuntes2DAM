
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeerClubes {

    public static void main(String[] args) {
        Path ruta = Path.of("datos", "clubes.txt");
        // comprobamos si existe la ruta con una condicion y con el Files.notExists(),
        if (Files.notExists(ruta)) {
            // si no existe mostramos la un mensaje.
            System.out.println("Todavia no hay ningun archivo con nombre clubes.");
            return;
        }

        // creamos el objeto y guardamos en una variable entrada. lo que hace es leer el texto de un archivo, lina por linea. "lo llamamos entrada por que entra en el flujo de lectura puede llamarse tambien lector"
        try ( BufferedReader entrada = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            //leemos lo que hay dentro de la carpeta datos 

            String linea; 

            while ((linea = entrada.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.err.println("No se pudo leer: " + e.getMessage());
        }

    }

}
