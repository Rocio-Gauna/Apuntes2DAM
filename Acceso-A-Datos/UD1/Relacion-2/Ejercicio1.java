import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio1 {
    public static void main(String[] args) {

        Path ruta = Path.of("datos", "videojuegos.csv");
    
        try{
            if(Files.notExists(ruta)){
                System.out.println("El archivo no existe");
                return;
            }
            List<String> registro = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            List<String> nuevoRegistro = new ArrayList<>();
            boolean encontrado = false;
            for(String linea : registro){ 
                String[] campos = linea.split(";", -1);
                

            }
        } catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
    }
}