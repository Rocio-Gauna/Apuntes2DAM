
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class ejercicio5 {

    public static void main(String[] args) {

        Path ruta = Path.of("datos", "biblioteca.csv");

        try {
            // comprobamos que el fichero existe antes de pedir
            if(Files.notExists(ruta)){
                System.out.println("El archivo no existe");
            }

            //Leemos todas las lineas existentes para comprobar si el ID o el titulo ya estan usados

            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            // Pedimos por teclado los 3 datos del nuevo libro
            Scanner teclado = new Scanner(System.in);

            System.out.println("Introduce el ID del libro: ");
            String idTexto = teclado.nextLine();

            System.out.println("Introduce el titulo del libro: ");
            String tituloIntroducido = teclado.nextLine();

            System.out.println("Introduce el autor del libro: ");
            String autorIntroducido = teclado.nextLine();

            teclado.close();

            int idNuevo;

            try {
                idNuevo = Integer.parseInt(idTexto.trim());
            } catch (NumberFormatException e) {
                System.out.println("El ID introducido no es un numero valido.");
                return;
            }
            
            if(idNuevo <=0){
                System.out.println("El ID debe ser mayor que 0.");
                return;
            }

            // --- Validación de título y autor ---

            // Limpiamos espacios al principio y al final
            String tituloNuevo = tituloIntroducido.trim();
            String autorNuevo = autorIntroducido.trim();

            if (tituloNuevo.isEmpty()) {
                System.out.println("El título no puede estar vacío.");
                return;
            }

            if (autorNuevo.isEmpty()) {
                System.out.println("El autor no puede estar vacío.");
                return;
            }

            // Comprobación de duplicados 

            // Dos variables separadas, porque queremos un mensaje
            // distinto según cuál de los dos datos esté repetido
            boolean idDuplicado = false;
            boolean tituloDuplicado = false;

            // Empezamos en 1 para saltar la cabecera (id;titulo;autor)
            for (int i = 1; i < lineas.size(); i++) {

                String[] campos = lineas.get(i).split(";", -1);

                // Solo comprobamos líneas bien formadas (3 campos)
                if (campos.length == 3) {

                    // Comparamos el ID como número
                    try {
                        int idLinea = Integer.parseInt(campos[0].trim());
                        if (idLinea == idNuevo) {
                            idDuplicado = true;
                        }
                    } catch (NumberFormatException e) {
                        // Si el ID de una línea antigua está corrupto,
                        // lo ignoramos para esta comprobación concreta
                    }

                    // Comparamos el título ignorando mayúsculas y espacios
                    String tituloLinea = campos[1].trim();
                    if (tituloLinea.equalsIgnoreCase(tituloNuevo)) {
                        tituloDuplicado = true;
                    }
                }
            }

            if (idDuplicado) {
                System.out.println("Ya existe un libro con ese ID.");
                return;
            }

            if (tituloDuplicado) {
                System.out.println("Ya existe un libro con ese título.");
                return;
            }

            // Construimos la línea nueva con el formato id;titulo;autor
            String lineaNueva = idNuevo + ";" + tituloNuevo + ";" + autorNuevo;

            // Escribimos esa línea al final del fichero, sin borrar lo anterior
            Files.writeString(ruta, lineaNueva + System.lineSeparator(),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND);

            System.out.println("Libro añadido correctamente.");


        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
