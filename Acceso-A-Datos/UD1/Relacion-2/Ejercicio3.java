
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//modificamo un archivo en este ejercicio 
// Leemos, cambiar informacion, volver a escribir el fichero completo. 
public class Ejercicio3 {

    public static void main(String[] args) {
        //Declaramos la ruta del fichero a modificar

        Path ruta = Path.of("datos", "inventario.csv");

        try {
            // Comprobamos que el fichero existe antes de pedir nada por teclado

            if (Files.notExists(ruta)) {
                System.out.println("NO existe el archivo");
                return;
            }
            // cargamos todas la lista y la recorremos
            List<String> originales = Files.readAllLines(ruta, StandardCharsets.UTF_8);

            // Pedimos el ID del producto y el nuevo stock por teclado
            Scanner teclado = new Scanner(System.in);
            System.out.println("Introduce el ID del producto a modificar: ");
            String texto = teclado.nextLine();
            System.out.println("Introduce el nuevo stock: ");
            String stockText = teclado.nextLine();
            teclado.close();
            // validamos el ID que sea un numero

            int idBuscado;
            try {
                idBuscado = Integer.parseInt(texto.trim());
            } catch (NumberFormatException e) {
                System.out.println("El ID introducido no es un número válido.");
                return;
            }

            // Validamos que el nuevo stock introducido también sea un número entero
            int nuevoStock;
            try {
                nuevoStock = Integer.parseInt(stockText.trim());
            } catch (NumberFormatException e) {
                System.out.println("El stock introducido no es un número válido.");
                return;
            }

            // preparamos la lista NUEVA, que al final tendrá
            // exactamente el mismo número de líneas que "originales",
            // pero con el registro buscado ya actualizado.
            List<String> nuevas = new ArrayList<>();

            // Variable para saber, al terminar el recorrido, si encontramos
            // el producto solicitado.
            boolean encontrado = false;

            // Empezamos en 1 para saltar la cabecera (igual que en el
            // ejercicio 02). Si tu inventario.csv NO tiene cabecera,
            // aquí abajo te explico el único cambio a hacer.
            for (int i = 1; i < originales.size(); i++) {

                String linea = originales.get(i);
                String[] campos = linea.split(";", -1);

                if (campos.length != 3) {
                    // Línea mal formada: la descartamos, avisamos,
                    // y NO la añadimos a la lista nueva.
                    System.out.println("Línea " + (i + 1) + ": número de campos incorrecto, se descarta.");

                } else {
                    // Intentamos leer el ID de ESTA línea del fichero
                    try {
                        int idLinea = Integer.parseInt(campos[0].trim());

                        if (idLinea == idBuscado) {
                            // ¡Es el producto que buscábamos!
                            // Construimos una línea NUEVA con el mismo id
                            // y nombre, pero con el stock actualizado.
                            String nombre = campos[1].trim();
                            String lineaActualizada = idLinea + ";" + nombre + ";" + nuevoStock;

                            nuevas.add(lineaActualizada);
                            encontrado = true;

                        } else {
                            // No es el producto buscado: añadimos la línea
                            // ORIGINAL, sin modificar nada en ella.
                            nuevas.add(linea);
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Línea " + (i + 1) + ": ID no numérico, se descarta.");
                    }
                }
            }

            // escribimos el fichero si de verdad encontramos
            // el producto Y el nuevo stock era válido (ya comprobado antes).
            if (encontrado) {

                // Si tu fichero tiene cabecera, hay que volver a añadirla
                // al principio de la lista nueva antes de escribir,
                // porque el bucle de arriba empezó en 1 y nunca la copió.
                nuevas.add(0, originales.get(0));

                Files.write(ruta, nuevas, StandardCharsets.UTF_8);
                System.out.println("Stock actualizado correctamente.");

            } else {
                System.out.println("No existe ningún producto con ID " + idBuscado);
            }

        } catch (IOException e) {
        }
    }
}
