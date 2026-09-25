import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class RegistroClubes {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
    
        Path carpeta = Path.of("datos");
        Path archivoClubes = carpeta.resolve("clubes.txt");

        if (Files.notExists(carpeta)){
            Files.createDirectories(carpeta);
        }
        if (Files.notExists(archivoClubes)) {
            Files.createFile(archivoClubes);
        }

        System.out.println("Ingrese el número de clubes a registrar:");
        String id =scanner.next();

        System.out.println("Ingrese nombre");
        String nombre = scanner.next();

        System.out.println("ingrese nombre de la ciudad");
        String ciudad = scanner.next();
        try (BufferedWriter salida = Files.newBufferedWriter(archivoClubes, StandardCharsets.UTF_8,){
            
        }


    }

}
