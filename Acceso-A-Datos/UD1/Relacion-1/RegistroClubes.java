import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class RegistroClubes {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Rutas: carpeta contenedora y archivo concreto dentro de ella
        Path carpeta = Path.of("datos");
        Path archivoClubes = carpeta.resolve("clubes.txt");

        // Pedimos los datos ANTES de tocar el archivo, ya que no dependen de él
        System.out.println("Ingrese el ID del club:");
        String id = scanner.nextLine();

        System.out.println("Ingrese el nombre:");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese la ciudad:");
        String ciudad = scanner.nextLine();

        // Construimos la línea con el formato pedido: id;nombre;ciudad
        String linea = id + ";" + nombre + ";" + ciudad;

        try {
            // createDirectories ya crea "datos" si no existe, sin fallar si ya existía
            Files.createDirectories(carpeta);

            // Escribimos en archivoClubes (el ARCHIVO), no en carpeta (la CARPETA)
            // CREATE: crea el archivo si no existe. APPEND: añade al final si ya existe.
            Files.writeString(archivoClubes, linea + System.lineSeparator(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

            System.out.println("Club guardado correctamente.");

        } catch (IOException e) {
            System.err.println("Error al guardar el club: " + e.getMessage());
        }

        // Cerramos el Scanner al final, ya que no se vuelve a leer teclado después
        scanner.close();
    }
}