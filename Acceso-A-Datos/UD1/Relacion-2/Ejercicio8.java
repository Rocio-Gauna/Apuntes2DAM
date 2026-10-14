
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio8 {

    // Fichero de datos. Puede no existir al arrancar el programa.
    private static final Path RUTA = Path.of("datos", "prestamos.csv");

    // Copia de seguridad que se crea antes de reescribir el fichero
    private static final Path RUTA_COPIA = Path.of("datos", "copias", "prestamos.bak");

    // Aquí se guarda el estado actual justo antes de restaurar.
    // Si restaurar copiara el fichero actual sobre prestamos.bak,
    // destruiría la misma copia que queremos restaurar.
    private static final Path RUTA_PREVIA = Path.of("datos", "copias", "prestamos_previo.bak");

    public static void main(String[] args) {

        // Un único Scanner para todo el programa
        Scanner teclado = new Scanner(System.in);

        // Bandera que controla cuándo termina el menú
        boolean salir = false;

        while (!salir) {
            System.out.println();
            System.out.println("GESTOR DE PRÉSTAMOS");
            System.out.println("1. Alta");
            System.out.println("2. Listado");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Cambiar material");
            System.out.println("5. Baja");
            System.out.println("6. Crear copia");
            System.out.println("7. Restaurar copia");
            System.out.println("0. Salir");

            // Si escriben letras, pedirEntero vuelve a preguntar: el menú no se rompe
            int opcion = pedirEntero(teclado, "Elige una opción: ");

            if (opcion == 1) {
                alta(teclado);
            } else if (opcion == 2) {
                listado();
            } else if (opcion == 3) {
                buscar(teclado);
            } else if (opcion == 4) {
                cambiarMaterial(teclado);
            } else if (opcion == 5) {
                baja(teclado);
            } else if (opcion == 6) {
                crearCopia();
            } else if (opcion == 7) {
                restaurarCopia(teclado);
            } else if (opcion == 0) {
                salir = true;
            } else {
                System.out.println("Opción no válida.");
            }
        }

        teclado.close(); // el programa termina: ya no pedimos más datos
    }

    // ---------- PEDIR DATOS VÁLIDOS (no tocan el disco) ----------

    // Repite la pregunta hasta que el usuario escribe un número entero
    private static int pedirEntero(Scanner teclado, String mensaje) {
        int numero = 0;          // valor que devolveremos
        boolean valido = false;  // pasa a true cuando la conversión funciona

        while (!valido) {
            System.out.print(mensaje);
            String texto = teclado.nextLine().trim();
            try {
                numero = Integer.parseInt(texto);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Debes escribir un número entero.");
            }
        }
        return numero;
    }

    // Repite hasta conseguir un entero MAYOR que 0
    private static int pedirIdPositivo(Scanner teclado, String mensaje) {
        int id = pedirEntero(teclado, mensaje);

        while (id <= 0) {
            System.out.println("El ID debe ser mayor que 0.");
            id = pedirEntero(teclado, mensaje);
        }
        return id;
    }

    // Repite hasta conseguir un texto no vacío y sin ';'
    // (el ';' es el separador del CSV: uno extra rompería el registro)
    private static String pedirTexto(Scanner teclado, String mensaje) {
        String texto = "";
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            texto = teclado.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println("No puede estar vacío.");
            } else if (texto.contains(";")) {
                System.out.println("No se permite el carácter ';'.");
            } else {
                valido = true;
            }
        }
        return texto;
    }

    // ---------- TRABAJAR CON LÍNEAS (en memoria) ----------

    // Devuelve el ID de una línea como número, o -1 si la línea está mal formada
    private static int leerId(String linea) {
        String[] campos = linea.split(";", -1);

        // Una línea válida tiene exactamente 3 campos
        if (campos.length != 3) {
            return -1;
        }
        try {
            return Integer.parseInt(campos[0].trim());
        } catch (NumberFormatException e) {
            return -1; // el ID no era un número
        }
    }

    // Comprueba si ya hay una línea con ese ID
    private static boolean existeId(List<String> lineas, int id) {
        for (String linea : lineas) {
            if (leerId(linea) == id) {
                return true; // encontrado: no hace falta seguir
            }
        }
        return false; // recorrimos todo y no estaba
    }

    // ---------- ACCESO AL DISCO ----------

    // Devuelve las líneas del fichero.
    // Lista vacía si el fichero no existe; null si falló la lectura.
    private static List<String> cargarLineas() {
        if (Files.notExists(RUTA)) {
            return new ArrayList<>(); // todavía no hay préstamos
        }
        try {
            // new ArrayList<>(...) garantiza una lista que se puede modificar
            return new ArrayList<>(Files.readAllLines(RUTA, StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("No se pudo leer el fichero: " + e.getMessage());
            return null; // null = error de lectura (distinto de lista vacía)
        }
    }

    // Crea la copia de seguridad y después escribe la lista COMPLETA.
    // Devuelve true si todo salió bien.
    private static boolean guardarConCopia(List<String> lineas) {
        try {
            Files.createDirectories(RUTA.getParent());        // carpeta datos
            Files.createDirectories(RUTA_COPIA.getParent());  // carpeta datos/copias

            // Solo hay algo que copiar si el fichero ya existía
            if (Files.exists(RUTA)) {
                Files.copy(RUTA, RUTA_COPIA, StandardCopyOption.REPLACE_EXISTING);
            }

            Files.write(RUTA, lineas, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            System.out.println("No se pudo guardar: " + e.getMessage());
            return false;
        }
    }

    // ---------- OPCIONES DEL MENÚ ----------

    // ALTA (Create): validar -> comprobar duplicado -> añadir -> guardar
    private static void alta(Scanner teclado) {
        List<String> lineas = cargarLineas();
        if (lineas == null) {
            return; // error de lectura: no tocamos nada
        }

        int id = pedirIdPositivo(teclado, "ID del préstamo: ");

        // Comprobamos el duplicado ANTES de pedir el resto de datos
        if (existeId(lineas, id)) {
            System.out.println("Ya existe un préstamo con ese ID.");
            return;
        }

        String persona = pedirTexto(teclado, "Persona: ");
        String material = pedirTexto(teclado, "Material: ");

        // De momento solo en memoria
        lineas.add(id + ";" + persona + ";" + material);

        if (guardarConCopia(lineas)) {
            System.out.println("Préstamo añadido.");
        }
    }

    // LISTADO (Read): muestra todas las líneas bien formadas
    private static void listado() {
        List<String> lineas = cargarLineas();
        if (lineas == null) {
            return;
        }
        if (lineas.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }

        for (int i = 0; i < lineas.size(); i++) {
            String[] campos = lineas.get(i).split(";", -1);

            if (campos.length == 3) {
                System.out.println("[" + campos[0].trim() + "] " + campos[1].trim() + " - " + campos[2].trim());
            } else {
                System.out.println("Línea " + (i + 1) + " mal formada, se omite.");
            }
        }
    }

    // BÚSQUEDA POR ID (Read)
    private static void buscar(Scanner teclado) {
        List<String> lineas = cargarLineas();
        if (lineas == null) {
            return;
        }

        int id = pedirIdPositivo(teclado, "ID a buscar: ");

        // Bandera: ¿lo hemos encontrado?
        boolean encontrado = false;

        for (String linea : lineas) {
            if (leerId(linea) == id) {
                String[] campos = linea.split(";", -1);
                System.out.println("Persona: " + campos[1].trim() + " | Material: " + campos[2].trim());
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No existe ningún préstamo con ID " + id);
        }
    }

    // CAMBIO DE MATERIAL (Update): lista nueva; solo cambia la línea buscada
    private static void cambiarMaterial(Scanner teclado) {
        List<String> lineas = cargarLineas();
        if (lineas == null) {
            return;
        }

        int id = pedirIdPositivo(teclado, "ID del préstamo a modificar: ");

        // Si no existe, no pedimos más datos ni reescribimos nada
        if (!existeId(lineas, id)) {
            System.out.println("No existe ningún préstamo con ID " + id + ". No se modifica nada.");
            return;
        }

        String nuevoMaterial = pedirTexto(teclado, "Nuevo material: ");

        // Lista nueva: el resto de líneas pasan sin cambios
        List<String> nuevas = new ArrayList<>();

        for (String linea : lineas) {
            if (leerId(linea) == id) {
                String[] campos = linea.split(";", -1);
                nuevas.add(campos[0].trim() + ";" + campos[1].trim() + ";" + nuevoMaterial);
            } else {
                nuevas.add(linea);
            }
        }

        if (guardarConCopia(nuevas)) {
            System.out.println("Material actualizado.");
        }
    }

    // BAJA (Delete): lista nueva con todas las líneas MENOS la solicitada
    private static void baja(Scanner teclado) {
        List<String> lineas = cargarLineas();
        if (lineas == null) {
            return;
        }

        int id = pedirIdPositivo(teclado, "ID del préstamo a borrar: ");

        if (!existeId(lineas, id)) {
            System.out.println("No existe ningún préstamo con ID " + id + ". No se borra nada.");
            return;
        }

        List<String> nuevas = new ArrayList<>();

        for (String linea : lineas) {
            // Solo añadimos las que NO son la solicitada
            if (leerId(linea) != id) {
                nuevas.add(linea);
            }
        }

        if (guardarConCopia(nuevas)) {
            System.out.println("Préstamo borrado.");
        }
    }

    // CREAR COPIA manualmente
    private static void crearCopia() {
        if (Files.notExists(RUTA)) {
            System.out.println("No hay datos que copiar.");
            return;
        }
        try {
            Files.createDirectories(RUTA_COPIA.getParent());
            Files.copy(RUTA, RUTA_COPIA, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copia creada: " + RUTA_COPIA);
        } catch (IOException e) {
            System.out.println("No se pudo crear la copia: " + e.getMessage());
        }
    }

    // RESTAURAR COPIA: avisa si no hay copia y pide confirmación antes de sustituir
    private static void restaurarCopia(Scanner teclado) {
        if (Files.notExists(RUTA_COPIA)) {
            System.out.println("No existe ninguna copia. El archivo actual no se toca.");
            return;
        }

        System.out.print("Esto sustituirá los datos actuales por la copia. ¿Seguro? (s/n): ");
        String respuesta = teclado.nextLine().trim();

        if (respuesta.equalsIgnoreCase("s")) {
            try {
                // Guardamos el estado actual aparte, SIN pisar prestamos.bak
                if (Files.exists(RUTA)) {
                    Files.copy(RUTA, RUTA_PREVIA, StandardCopyOption.REPLACE_EXISTING);
                }
                Files.copy(RUTA_COPIA, RUTA, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Copia restaurada.");
            } catch (IOException e) {
                System.out.println("No se pudo restaurar: " + e.getMessage());
            }
        } else {
            System.out.println("Restauración cancelada.");
        }
    }
}