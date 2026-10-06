
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Alumnos {

    public static void main(String[] args) {
        // declaramos la ruta del fichero que vamos a consultar
        Path ruta = Path.of("datos", "alumnos.csv");

        try {
            //comprobamos, Files.notExists no lanza la excepcion por eso puedo ir fuera de cualquier bloque de try-with-resources

            if (Files.notExists(ruta)) {
                System.out.println("El archivo no existe");
                return;
            }
            // Lee todas las lineas del archivo del fichero en UTF-8( cada posicion del .csv)
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            // creamos el Scanner para pedir datos
            Scanner teclado = new Scanner(System.in);
            System.out.print("Introduce el ID del alumno: ");
            String idIntroducido = teclado.nextLine();
            //cerramos el scanner por que no necesitamos datos por el teclado
            teclado.close();

            int idBuscado;
            try {
                // convierte lo que escribio el usuario a numero
                idBuscado = Integer.parseInt(idIntroducido.trim());
            } catch (NumberFormatException e) {
                System.out.println("El ID introducido no es un numero valido.");
                return;
            }
            // recorre solo los registros de datos, compara el ID SOLICITADO CON EL PRIMER CAMPO.

            // declaramos una variable booleana para saber, al terminar el bucle si encontramos o no al alumno con el ID
            boolean encontrado = false;

            for (int i = 1; lineas.size() > i; i++) {
                String[] campos = lineas.get(i).split(";", -1);

                // si la linea no tiene 3 campo avisamos
                if (campos.length != 3) {
                    System.out.println("Línea " + (i + 1) + ": número de campos incorrecto.");
                } else {
                    try {
                        int idLinea = Integer.parseInt(campos[0].trim());

                        // comparamos numero con numero 
                        if (idLinea == idBuscado) {
                            String nombre = campos[1].trim();
                            String grupo = campos[2].trim();

                            System.out.println("Nombre: " + nombre + "|Grupo: " + grupo);
                            encontrado = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Linea " + (i + 1) + " :ID no numerico, se ignora.");
                    }
                }
            }
            //si recorrimos todo el fichero y no activamos " encontrado", es que el ID era valido como nuemro, pero no existe.
            if (!encontrado) {
                System.out.println("No existe ningun alumno con ID " + idBuscado);
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }

}
