import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Alumnos {
    public static void main(String[] args){
        Path ruta = Path.of("datos", "alumnos.csv" );
        Scanner teclado = new Scanner(System.in);

        try{
            if (Files.notExists(ruta)){
                System.out.println("El archivo no existe");
                return;
            }
            // Lee todas las lineas del archivo del fichero en UTF-8
            List<String> lineas =Files.readAllLines(ruta,StandardCharsets.UTF_8);
            // recorre solo los registros de datos, compara el ID SOLICITADO CON EL PRIMER CAMPO.
            System.out.print("Introduce el ID del alumno: ");
            String idIntroducido = teclado.nextLine();
            for (int i= 1; lineas.size() > i; i++) {
                String[] campos = lineas.get(i).split(";",-1);
                if(campos.length !=3){
                    System.out.println("Línea " + (i + 1) + ": número de campos incorrecto.");
                }else{
                    int id = Integer.parseInt(campos[0].trim());
                    String nombre = campos[1].trim();
                    String grupo = campos[2].trim();
                }
                if (campos[0].equals(idIntroducido)){
                    System.out.println(" Nombre: " + campos[1] + " Grupo: " + campos[2]);
                }
            }

        } catch (IOException e){
            System.out.println("Error " + e.getMessage());
        }
    }


}

