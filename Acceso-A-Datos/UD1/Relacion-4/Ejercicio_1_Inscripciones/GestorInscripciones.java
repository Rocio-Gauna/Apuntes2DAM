
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestorInscripciones {

    // es una constante ya que la linea cabecera es siempre la misma
    private static final String CABECERA = "id;persona;taller;plazas";

    // TODO 1.1 - Leer el CSV en UTF-8, ignorar cabecera y validar TODAS las filas.
    // Si el archivo no existe, devolver una lista vacía.
    // Si una fila es incorrecta o hay IDs duplicados, lanzar IOException.
    public static List<Inscripcion> cargar(Path archivo) throws IOException {
        // PASO 1: prepara la lista, recorre las filas del CSV y valida campos.

        // CREAMOS UNA LISTA. donde iran las inscripciones. por ahora esa lista esta vacia.
        List<Inscripcion> inscripciones = new ArrayList<>();

        // validamos la lista, si no existe devolvemos una vacia y termino aqui
        //Esto cumple: " si el archivo no existe, devolcer una lista vacia"
        if (Files.notExists(archivo)) {
            return inscripciones;
        }

        //Copio todas las linas del archivo a una lista de texto en UTF-8
        //LEEMOS EL CSV en UTF_8
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);

        //Lista de invitados donde apunto cada ID que voy viendo (sin repetidos)
        Set<Integer>idsVistos = new HashSet<>();

        //con un "for"comienzo a contar, Empiezo en 1 y no en 0, para no contar la cabecera. 
        for (int posicion = 1; posicion < lineas.size(); posicion++) {
            //Saco de la lista la lina que toca en esta vuelta

            String linea = lineas.get(posicion);

            //numero de linea tal como se ve en el archivo
            int numeroLinea = posicion + 1;

            //Corto el reglon en trozo por cada "; " los guardo en un array
            String[] campos = linea.split(";", -1);

            //En este caso la linea tiene 4 campos: id; persona; taller ;plazas
            // sino lanzo la bandera con un mensaje que la lina falla
            // cumple : "Si la fila es incorrecta, lazamos el IOException"
            if (campos.length != 4) {
                throw new IOException("Linea" + numeroLinea + ":se esperan 4 campos y hay: " + campos.length);
            }
            //TEMPORAL PARA VER SI CADA FILA SE SEPARA BIEN LUEGO LO BORRAMOS 
            //System.out.println("TEMPORAL - id=" + campos[0] + " | persona=" + campos[1] + " | taller=" + campos[2] + " | plazas=" + campos[3]);

            int id;
            int plazas;
            try {
                // convierto el texto de los campos que son id y plzas a numero quitando espacio con los trim()
                id = Integer.parseInt(campos[0].trim());
                plazas = Integer.parseInt(campos[3].trim());
            } catch (NumberFormatException e) {
                //si alguno no era un numero, lo convierto en una exception 
                throw new IOException("Lineas " + numeroLinea + ": EL id o las plazas no son numeros enteros");
            }

            //creo el objeto con el orden del constructor id, perdona, taller, plazas
            Inscripcion inscripcion = new Inscripcion(id, campos[1], campos[2], plazas);

            //SI la inscripcion NO es valida (ID <=0, plazas negativas, persona o taller vacias) lanzo error indicando la linea
            // Cumple : "Validar TODAS las filas"
            if (!esValida(inscripcion)) {
                throw new IOException("Linea " + numeroLinea + ":Datos  no validos");
            }

            //INTENTO Escribir el ID; si add devulve false:ya estaba, esta repetido
            //Cumple : "HAY DOS IDS DUPLICADO. LANZA IOException"
            if (!idsVistos.add(id)) {
                throw new IOException("Línea " + numeroLinea + ": ID repetido (" + id + ")");
            }

            //meto la inscripcion en la lisata
            inscripciones.add(inscripcion);
        }

        //TEMPORAL PARA VERIFICAR QUE LA LECTURA FUNCIONA
        //System.out.println("TEMPORAL- LINEAS LEIDAS: "+ lineas.size());
        //convertimos en numeros el id y plazas 
        // preparamos 2 variables con id y plazas Fueras del TRY para poder usarlas despues
        //Devolvemos la lista con las incripciones dentro
        return inscripciones;

    }

    // TODO 1.2 - Buscar por ID. Devolver la inscripción o null.
    public static Inscripcion buscar(List<Inscripcion> lista, int id) {
        // PASO 2: recorre la lista y compara los ID.
        //Recorro la lista con un for, en cada vuelta "item" es un inscripcion distinta
        //Cumple "Recorre la lista" 
        for(Inscripcion item : lista){
            // comparamos si el ID esta inscripto es el que pido
            //cumple : "Compara los ID"
            if(item.getId() == id){
                //si lo encontro devuelvo el metodo y todo termina aqui
                // cumple: "devuelve la inscripcion"
                return item;
            }
        }
        // si llego aqui, mire todas las cestas y ninguna coincidio: devuelvo nada, 
        
        return null;
    }

    // MÉTODO RESUELTO COMO EJEMPLO: no hay que modificarlo.
    public static boolean registrar(Path archivo, Inscripcion nueva) throws IOException {
        List<Inscripcion> lista = cargar(archivo);
        if (!esValida(nueva) || buscar(lista, nueva.getId()) != null) {
            return false;
        }
        lista.add(nueva);
        crearCopiaSiExiste(archivo);
        guardarTodo(archivo, lista);
        return true;
    }

    // TODO 1.3 - Si el ID existe y plazas >= 0, cambiar SOLO ese registro.
    // Crear copia antes de reescribir. Si no existe o plazas < 0, devolver false.
    public static boolean actualizarPlazas(Path archivo, int id, int plazas) throws IOException {
        // PASO 3: carga, busca, valida, crea copia y guarda.

        // si las plazas son negativas , no hago nada y aviso con false
        if(plazas< 0 ){
            return false;
        }

        //Cargo la lista completa desde el archivo 
        List<Inscripcion>lista = cargar(archivo);
        
        // Busco la inscripcion con ese ID
        Inscripcion inscripcion = buscar(lista,id);

        //sino existe (null), no hya nada que cambiar: devuelvo false sin tocar el archivo
        if(inscripcion == null){
            return false;
        }

        //hago la copia de seguridad antes de reescribir el archivo

        crearCopiaSiExiste(archivo);
        //cambio Solo de esta inscripcion, en memoria,
        inscripcion.setPlazas(plazas);

        //Guardo la cesta entera en el archivo: las demas filas se conservan igual
        guardarTodo(archivo, lista);
        //SI SALIO TODO BIEN DEVUELVE TRUE
        return true;

    }

    // TODO 1.4 - Eliminar el ID solicitado y conservar todos los demás.
    // Si no existe, NO reescribir el archivo ni su copia.
    public static boolean eliminar(Path archivo, int id) throws IOException {
        // PASO 4: reconstruye la lista omitiendo el ID solicitado.
        // Cargo la lista completa desde el archivo 
        List<Inscripcion> lista = cargar(archivo);
        //Lista nueva vacia: aqui iria toas las inscripciones menos la que se elimina
        //Construye una lista sin el ID solicitado

        List<Inscripcion> restantes = new ArrayList<>();

        //BANDERA BOOLEAN
        boolean encontrado = false;
    // Reviso las inscripciones una por una.
    for (Inscripcion item : lista) {

        // Si es la que quiero eliminar, NO la copio y subo la banderita.
        if (item.getId() == id) {
            encontrado = true;
        } else {
            // Si es otra, la paso a la cesta nueva para conservarla.
            // Cumple: "conservar todos los demás".
            restantes.add(item);
        }
    }

    // Si la banderita sigue bajada, ese ID no existía: no toco ni el archivo ni la copia.
    // Cumple: "Si no existe, NO reescribir el archivo ni su copia".
    if (!encontrado) {
        return false;
    }

    // Antes de reescribir, guardo la copia de seguridad del estado actual.
    crearCopiaSiExiste(archivo);

    // Escribo en el archivo la cesta nueva (sin la inscripción eliminada).
    guardarTodo(archivo, restantes);

    // Todo salió bien: devuelvo true.

        return true;
    }

    // TODO 1.5 - Restaurar archivo.csv.bak sobre archivo.csv.
    // La confirmación se pide en Main, NO aquí.
    public static boolean restaurar(Path archivo) throws IOException {
        // PASO 5: localiza .bak y cópialo sobre el CSV.
            // Ruta de la copia: el mismo nombre del archivo con ".bak" al final.
    // Cumple: "localiza .bak".
    Path copia = Path.of(archivo.toString() + ".bak");

    // Si no existe ninguna copia, no hay nada que restaurar: devuelvo false.
    if (Files.notExists(copia)) {
        return false;
    }

    // Copio el .bak encima del CSV; REPLACE_EXISTING permite sustituirlo.
    // Cumple: "cópialo sobre el CSV".
    Files.copy(copia, archivo, StandardCopyOption.REPLACE_EXISTING);

    // Restaurado: devuelvo true.
    return true;
    }

    // MÉTODOS PROPORCIONADOS. No se deben completar.
    private static boolean esValida(Inscripcion item) {
        return item != null && item.getId() > 0 && item.getPlazas() >= 0
                && !item.getPersona().isBlank() && !item.getTaller().isBlank()
                && !item.getPersona().contains(";") && !item.getTaller().contains(";")
                && !item.getPersona().contains("\n") && !item.getTaller().contains("\n");
    }

    private static void guardarTodo(Path archivo, List<Inscripcion> lista) throws IOException {
        Path padre = archivo.getParent();
        if (padre != null) {
            Files.createDirectories(padre);
        }
        List<String> lineas = new ArrayList<>();
        lineas.add(CABECERA);
        for (Inscripcion item : lista) {
            lineas.add(item.getId() + ";" + item.getPersona() + ";"
                    + item.getTaller() + ";" + item.getPlazas());
        }
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }

    private static void crearCopiaSiExiste(Path archivo) throws IOException {
        if (Files.exists(archivo)) {
            Path copia = Path.of(archivo.toString() + ".bak");
            Files.copy(archivo, copia, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}