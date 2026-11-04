
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Path archivo = Path.of("datos", "inscripciones.csv");
        try (Scanner teclado = new Scanner(System.in)) {
            int opcion = -1;
            while (opcion != 0) {
                System.out.println("\nGESTOR DE INSCRIPCIONES");
                System.out.println("1. Listar | 2. Buscar | 3. Alta | 4. Cambiar plazas");
                System.out.println("5. Eliminar | 6. Restaurar última copia | 0. Salir");
                System.out.print("Opción: ");
                try {
                    opcion = Integer.parseInt(teclado.nextLine().trim());
                    switch (opcion) {
                        case 1 -> {
                            // EJEMPLO COMPLETO: lectura y salida solo desde main.
                            List<Inscripcion> lista = GestorInscripciones.cargar(archivo);
                            if (lista.isEmpty()) {
                                System.out.println("No hay inscripciones.");
                            }
                            for (Inscripcion item : lista) {
                                System.out.println(item);
                            }
                        }
                        case 2 -> {
                            // EJEMPLO COMPLETO: consulta sin modificar el archivo.
                            System.out.print("ID que buscas: ");
                            int id = Integer.parseInt(teclado.nextLine().trim());
                            Inscripcion encontrada = GestorInscripciones.buscar(
                                    GestorInscripciones.cargar(archivo), id);
                            System.out.println(encontrada == null ? "ID no encontrado." : encontrada);
                        }
                        case 3 -> {
                            // EJEMPLO COMPLETO: alta y validación por la capa de datos.
                            System.out.print("ID: ");
                            int id = Integer.parseInt(teclado.nextLine().trim());
                            System.out.print("Persona: ");
                            String persona = teclado.nextLine().trim();
                            System.out.print("Taller: ");
                            String taller = teclado.nextLine().trim();
                            System.out.print("Plazas: ");
                            int plazas = Integer.parseInt(teclado.nextLine().trim());
                            boolean alta = GestorInscripciones.registrar(
                                    archivo, new Inscripcion(id, persona, taller, plazas));
                            System.out.println(alta ? "Inscripción añadida." : "Datos incorrectos o ID repetido.");
                        }
                        case 4 -> {
                            // TODO 1.6-A - Pedir ID y nuevas plazas, llamar a actualizarPlazas
                            // y mostrar un mensaje distinto según el boolean recibido.
                            // Solicita ID, plazas y muestra el resultado.
                            //System.out.println("TODO 1.6-A pendiente.");
                            // Pido el ID de la inscripción que quiero cambiar.
                            System.out.print("ID de la inscripción: ");
                            int id = Integer.parseInt(teclado.nextLine().trim());

                            // Pido las plazas nuevas.
                            System.out.print("Nuevas plazas: ");
                            int plazas = Integer.parseInt(teclado.nextLine().trim());

                            // Llamo al método de datos; me devuelve true (cambió) o false (no cambió).
                            boolean cambiado = GestorInscripciones.actualizarPlazas(archivo, id, plazas);

                            // Muestro un mensaje distinto según el resultado.
                            System.out.println(cambiado ? "Plazas actualizadas." : "ID inexistente o plazas negativas.");
                        }
                        case 5 -> {
                            // TODO 1.6-B - Pedir ID, eliminar y mostrar el resultado.
                            // Solicita ID para eliminar y muestra el resultado.
                            //System.out.println("TODO 1.6-B pendiente.");
                            // Pido el ID de la inscripción que quiero eliminar.
                            System.out.print("ID a eliminar: ");
                            int id = Integer.parseInt(teclado.nextLine().trim());

                            // El método de datos me dice si la eliminó (true) o no existía (false).
                            boolean eliminada = GestorInscripciones.eliminar(archivo, id);

                            // Muestro un mensaje distinto según el resultado.
                            System.out.println(eliminada ? "Inscripción eliminada." : "ID no encontrado.");

                        }
                        case 6 -> {
                            // TODO 1.6-C - Pedir confirmación S/N antes de restaurar.
                            // Pide confirmación antes de llamar a restaurar.
                            // System.out.println("TODO 1.6-C pendiente.");
                            // Pido confirmación porque restaurar SUSTITUYE los datos actuales.
                            System.out.print("Se sustituirán los datos actuales por la última copia. ¿Continuar? (S/N): ");
                            String respuesta = teclado.nextLine().trim();

                            // equalsIgnoreCase compara sin distinguir mayúsculas: vale "S" y "s".
                            if (respuesta.equalsIgnoreCase("S")) {

                                // Solo aquí, tras la confirmación, llamo al método de datos.
                                boolean restaurada = GestorInscripciones.restaurar(archivo);

                                // Mensaje distinto según haya o no copia.
                                System.out.println(restaurada ? "Copia restaurada." : "No existe ninguna copia.");
                            } else {
                                // Cualquier otra respuesta cancela, y no se toca nada.
                                System.out.println("Restauración cancelada.");
                            }
                        }
                        case 0 ->
                            System.out.println("Hasta luego.");
                        default ->
                            System.out.println("Opción desconocida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número entero válido.");
                } catch (IOException e) {
                    System.out.println("Error de archivo: " + e.getMessage());
                }

            }
        }
    }
}
