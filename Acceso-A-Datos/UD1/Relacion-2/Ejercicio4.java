import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Ejercicio4 {
    public static void main(String[] args) {
        Path ruta = Path.of("reservas.csv");
        if (Files.notExists(ruta)) {
            System.out.println("El archivo no existe");
            return;
        }
       Scanner teclado = new Scanner(System.in);
       
    }
}
