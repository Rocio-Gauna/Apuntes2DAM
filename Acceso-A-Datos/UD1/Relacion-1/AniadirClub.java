
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AniadirClub {

    public static void main(String[] args) {
        Path ruta = Path.of("datos", "clubes.txt");
        try {
            Files.createDirectories(ruta.getParent());
            try (BufferedWriter salida = Files.newBufferedWriter(
                    ruta, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {
                salida.write("4;Almería;Almería");
                salida.newLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
