
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio7 {

    // declaramos las rutas como una constante
    // ruta_inventario se reutilza en modificar y restaurar.
    private static final Path RUTA_INVENTARIO = Path.of("datos", "inventario.csv");
    // ruta donde se guardaran la copia de seguridad por eso el .bak
    private static final Path RUTA_COPIA = Path.of("datos", "inventario.bak");

    public static void main(String[] args) {
        // llamamos al Scanner para introducir los datos que nos piden

        Scanner teclado = new Scanner(System.in);

        //Bandera que controla cuando termina el menu

        boolean salir = false;

        while (!salir){
            // Mostramos las opciones
            System.out.println();
            System.out.println("1.Modificar stock");
            System.out.println("2.Resturar copia de seguridad");
            System.out.println("0.Salir");

            // PedirEntero repetimos la pregunta hasta que el usuario escriba un numero

            int opcion = pedirEntero(teclado, "Elige una opcion: ");

            if(opcion ==1){
                modificarStock(teclado);
            }else if(opcion == 2){
                restaurarCopia(teclado);
            }else if(opcion == 0){
                salir = true;
            }else{
                System.out.println("Opcion no valida.");
            }
        }
        teclado.close(); 
    }

    private static int pedirEntero(Scanner teclado, String mensaje){
        int numero = 0;
        boolean valido = false;
        while(!valido){
            System.out.println(mensaje);
            String texto = teclado.nextLine().trim();
            try {
                numero = Integer.parseInt(texto);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un nuemero entero");
            }
        }
        return numero;
    }
    private static void modificarStock(Scanner teclado){
        // si no hay fichero no hay que modificar nada
        if(Files.notExists(RUTA_INVENTARIO)){
            System.out.println("Error el archivo no existe");
            return;
        }
        // 1ero Pedimos y validamos los datos 
        int idBuscado = pedirEntero(teclado, "ID del producto: ");
        int nuevoStock = pedirEntero(teclado, "Nuevo stock: ");
        // el stock no puede ser negativo

        if(nuevoStock<0){
            System.out.println("El stock no puede ser negativo.");
            return;
        }
        try {
            // cargamos las lineas 
            List<String> originales =Files.readAllLines(RUTA_INVENTARIO, StandardCharsets.UTF_8);
            // Verificamos si el archivo esta vacio 
            if(originales.isEmpty()){
                System.out.println("El archivo esta vacio.");
                return;
            }
            //lista nueva: empieza con la cabecera 
            List<String> nuevas = new ArrayList<>();
            nuevas.add(originales.get(0));
            boolean encontrado = false;
            for(int i=1; i<originales.size(); i++){
                String linea = originales.get(i);
                String[] campos = linea.split(";", -1);
                boolean esElBuscado = false;
                if(campos.length == 3){
                    try {
                        int idLine = Integer.parseInt(campos[0].trim());
                        if(idLine == idBuscado){
                            esElBuscado = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Linea " + (i + 1)+ ":ID no numerico, se conseva sin cambios. ");
                    }
                }else{
                    System.out.println("Línea " + (i + 1) + ": número de campos incorrecto, se conserva sin cambios.");
                }
                if(esElBuscado){
                    // nuevo stock
                    nuevas.add(campos[0].trim() + ";" + campos[1].trim() + ";" + nuevoStock);
                    encontrado =true;
                }else{
                    nuevas.add(linea);
                }
            }
            //
            if(encontrado){
                Files.createDirectories(RUTA_COPIA.getParent());

                Files.copy(RUTA_INVENTARIO, RUTA_COPIA,StandardCopyOption.REPLACE_EXISTING);

                Files.write(RUTA_INVENTARIO, nuevas, StandardCharsets.UTF_8);

                System.out.println("Stock actualizado. Copia guardada en " + RUTA_COPIA);
            }else{
                System.out.println("No existe ningun producto con ID" + idBuscado + ". No se ha modificado nada.");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void restaurarCopia(Scanner teclado) {

        // Si no hay copia, informamos y dejamos el archivo actual intacto
        if (Files.notExists(RUTA_COPIA)) {
            System.out.println("No existe ninguna copia. El archivo actual no se toca.");
            return;
        }

        // Restaurar SUSTITUYE los datos actuales, así que pedimos confirmación
        System.out.print("Esto sustituirá inventario.csv por la copia. ¿Seguro? (s/n): ");
        String respuesta = teclado.nextLine().trim();

        if (respuesta.equalsIgnoreCase("s")) {
            try {
                // Copiamos la copia HACIA el archivo de datos
                Files.copy(RUTA_COPIA, RUTA_INVENTARIO, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copia restaurada correctamente.");
            } catch (IOException e) {
                System.out.println("No se pudo restaurar: " + e.getMessage());
            }
        } else {
            System.out.println("Restauración cancelada.");
        }
    }
}
