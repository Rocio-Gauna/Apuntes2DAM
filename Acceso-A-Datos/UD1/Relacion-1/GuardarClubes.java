import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuardarClubes {
    public static void main(String[] args) {
        Path carpeta = Path.of("datos");
        Path archivo = carpeta.resolve("clubes.txt");

        try {
            Files.createDirectories(carpeta);

            try (BufferedWriter escritor =
                         Files.newBufferedWriter(archivo, StandardCharsets.UTF_8)) {

                escritor.write("1;Granada CF;Granada");
                escritor.newLine();

                escritor.write("2;Málaga CF;Málaga");
                escritor.newLine();

                escritor.write("3;Cádiz CF;Cádiz");
                escritor.newLine();
            }

            System.out.println("Clubes guardados correctamente.");

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo:");
            System.out.println(e.getMessage());
        }
    }
}