
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositorioEquipos {

    // TODO 2.1 - Recuperar la lista serializada con ObjectInputStream.
    // Si no existe el .dat, devolver lista vacía. Comprobar tipos, IDs y duplicados.
    public static List<Equipo> cargar(Path archivo) throws IOException {
        // PASO 1: si el fichero falta, devuelve una lista vacía.
        // Si existe, lee un Object y valida su contenido.

        //CREAMOS LA LISTA VACIA, DONDE IRAN LOS EQUIPOS
        List<Equipo> equipos = new ArrayList<>();
        // TEMPORAL 1: muestra dónde busca Java el archivo y si existe.
        // Esta línea va FUERA del try, así se imprime siempre.
        System.out.println("TEMPORAL - busco: " + archivo.toAbsolutePath() + " | existe: " + Files.exists(archivo));

        //Si el dat no existe, devulvo la lista vacia y termina aqui. 
        if (Files.notExists(archivo)) {
            return equipos;
        }
        // NUEVO: conjunto donde apunto cada ID que voy viendo (sin repetidos).
        Set<Integer> idsVistos = new HashSet<>();

        //ABRIR EL ARCHIVO BINARIO: los bytes va en un lector de objetos
        // UTILIZAMOS EL TRY-WITH-RESOURCES lo cierra al terminar
        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(archivo))) {
            //LEO EL OBJETO GUARDADO 
            Object contenido = entrada.readObject();

            //Compruebo que la caja es una lista; si no lo es el archivo no sirve. 
            if (!(contenido instanceof List<?> listaLeida)) {
                throw new IOException("el archivo no tiene una lista");
            }

            //reviisar las cajas una a una recorriendo con n for
            for(Object elemento : listaLeida){
                // si la caja no es un equipo, el archivo no es de fiar :error
                if(!(elemento instanceof Equipo equipo)){
                    throw new IOException("la lista contiene un dato que no es un quipo");
                }

                //compruebo que el equipo tiene datos validos: 
                // ID>0, nombre no nulo, ni vacio y unidaddes >=0
                if(equipo.getId()<=0 || equipo.getNombre() == null || equipo.getNombre().isBlank() || equipo.getUnidades()<0){
                    throw  new IOException ("Equipo con datos no validos (ID"+ equipo.getId() + ")");
                }
                //Intento Apuntar el ID: si el add devulve false, ya estaba y es repetido

                if(!idsVistos.add(equipo.getId())){
                    throw new IOException("ID repetido: "+ equipo.getId());
                }
                //si el equipo paso las validaciones lo agrego a la lista de equipo
                equipos.add(equipo);

            }
            // TEMPORALMENTE : Solo para comprobar la lectura.
           // System.out.println("TEMPORALMENTE: ELEMENTOS LEIDOS" + listaLeida.size());
            // TEMPORAL: muestra dónde busca Java el archivo y si existe. La borraremos después.
        } catch (ClassNotFoundException e) {
            //Convierto el error en una exception que es lo que declara el metodo
            throw new IOException("No se encontro la clase de los datos" + e.getMessage());
        }
        //devolvemos la lista vacia: sin equipos en ella
        return equipos;
    }

    // TODO 2.2 - Crear carpeta y guardar la LISTA COMPLETA con ObjectOutputStream.
    // No usar APPEND en ficheros serializados.
    public static void guardar(Path archivo, List<Equipo> lista) throws IOException {
        // PASO 2: crea la carpeta y usa ObjectOutputStream.
        // El método es void: no necesita devolver nada.

        //Averiguamos la carpeta donde ira el archivo (para datos/equipos.dat, es "datos"). con un path
        Path carpeta = archivo.getParent();

        //si la ruta tiene carpeta, me aseguro de que exista; si falta la creo

        if(carpeta != null){
            Files.createDirectories(carpeta);
        }

        //abro el archivo para escribir. si ya existe, lo sustituyo entero sin APPEND
        //try-resources lo cierra solo al terminar
        try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(archivo))) {

        // Congelo la lista COMPLETA como un único objeto y la escribo.
        salida.writeObject(lista);
    }
    }

    // MÉTODO DE APOYO COMPLETO: busca por ID sin leer archivos.
    public static Equipo buscar(List<Equipo> lista, int id) {
        for (Equipo equipo : lista) {
            if (equipo.getId() == id) {
                return equipo;
            }
        }
        return null;
    }

    // TODO 2.3 - Modificar SOLO en memoria. Devolver false si ID desconocido
    // o unidades < 0. NO guardar aquí: lo hará Main en opción 4.
    public static boolean cambiarUnidades(List<Equipo> lista, int id, int unidades) {
        // PASO 3: localiza el equipo y cambia sus unidades.

        //si las unidades son negativas no cambio nada y aviso con false
        if(unidades<0){
            return false;
        }

        //Busco el equipo con ese ID(usa el buscar que ya viene resuelto)
        Equipo equipo = buscar(lista,id);
        //si no existe (null), no hay nada que cambiar: devuelvo false.
        if(equipo== null){
            return false;
        }

        //cambio las unidades solo en memoria. no se guarda en el disco
        equipo.setUnidades(unidades);
        
        //el cambio se realizo devuelvo true
        return true;
    }

    // TODO 2.4 - Exportar DOM XML. Crear raíz <equipos> y cada <equipo id="...">.
    // Dentro, <nombre> y <unidades>. No concatenar cadenas XML manualmente.
    // Los errores de XML se convierten en IOException para simplificar Main.
    public static void exportarXml(Path archivo, List<Equipo> lista) throws IOException {
        // PASO 4: crea un Document XML, elementos y Transformer.
    }
}
