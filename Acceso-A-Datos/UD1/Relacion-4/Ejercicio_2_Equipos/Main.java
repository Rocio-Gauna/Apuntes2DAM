
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Path binario = Path.of("datos", "equipos.dat");
        Path xml = Path.of("datos", "equipos_exportados.xml");
        try (Scanner teclado = new Scanner(System.in)) {
            try {
                List<Equipo> equipos = RepositorioEquipos.cargar(binario);
                int opcion = -1;
                while (opcion != 0) {
                    System.out.println("\nARCHIVO DE EQUIPOS");
                    System.out.println("1. Listar | 2. Buscar | 3. Cambiar unidades");
                    System.out.println("4. Guardar binario | 5. Recargar binario | 6. Exportar XML | 0. Salir");
                    System.out.print("Opción: ");
                    try {
                        opcion = Integer.parseInt(teclado.nextLine().trim());
                        switch (opcion) {
                            case 1 -> {
                                // EJEMPLO COMPLETO: Main muestra resultados.
                                if (equipos.isEmpty()) {
                                    System.out.println("No hay equipos.");
                                }
                                for (Equipo equipo : equipos) {
                                    System.out.println(equipo);
                                }
                            }
                            case 2 -> {
                                // EJEMPLO COMPLETO: consulta en memoria.
                                System.out.print("ID que buscas: ");
                                int id = Integer.parseInt(teclado.nextLine().trim());
                                Equipo encontrado = RepositorioEquipos.buscar(equipos, id);
                                System.out.println(encontrado == null ? "ID no encontrado." : encontrado);
                            }
                            case 3 -> {
                                // TODO 2.5-A: pedir ID y unidades, llamar a cambiarUnidades,
                                // e informar al usuario desde aquí (nunca desde el repositorio).
                                // Pide ID, unidades y llama al repositorio.
                                //System.out.println("TODO 2.5-A pendiente.");

                                //PIDO EL ID DEL EQUIPO QUE QUIERO CAMBIAR
                                System.out.println("ID del equipo: ");
                                int id = Integer.parseInt(teclado.nextLine().trim());

                                //PIDO las nuevas unidades
                                System.out.println("Nuevas Unidades: ");
                                int unidades = Integer.parseInt(teclado.nextLine().trim());

                                //LLAMO AL REPO CON LA LISTA QUE TENGO EN MEMORIA: ME DA UN BOOLEAN TRUE O FALSE
                                boolean cambiado = RepositorioEquipos.cambiarUnidades(equipos, id, unidades);

                                //MAIN es quien informa al usuario, con un mensaje distinto segun el resultado
                                System.out.println(cambiado ? "Unidades cambiadas en memoria. Usala opcion 4 para guardar" : "ID inexistente o unidades negativas");
                            }
                            case 4 -> {
                                // EJEMPLO COMPLETO: guardado explícito.
                                RepositorioEquipos.guardar(binario, equipos);
                                System.out.println("Lista guardada en binario.");
                            }
                            case 5 -> {
                                // TODO 2.5-B: reemplazar equipos por cargar(binario).
                                // Recarga la lista desde el fichero.
                                //System.out.println("TODO 2.5-B pendiente.");

                                //VUELVO A LEER EL ARCHIVO SUSTITUYO LA LISTA DE MEMORIA POR LA DEL DISCO
                                //ASI SE DESCARTAN LOS CAMBIOS QUE NO SE HAYAN GUARDADO
                                equipos = RepositorioEquipos.cargar(binario);

                                //aviso al usuario desde main
                                System.out.println("Lista recargada desde el archivo");
                            }
                            case 6 -> {
                                // TODO 2.5-C: llamar a exportarXml(xml, equipos).
                                // Exporta la lista a XML.
                                //System.out.println("TODO 2.5-C pendiente.");
                                // Exporto la lista que tengo en memoria al archivo XML (la variable xml ya existe arriba).
                                RepositorioEquipos.exportarXml(xml, equipos);

                                // Main informa al usuario de dónde quedó el archivo.
                                System.out.println("XML exportado en " + xml);
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
            } catch (IOException e) {
                System.out.println("No se pudo iniciar el programa: " + e.getMessage());
            }
        }
    }
}
