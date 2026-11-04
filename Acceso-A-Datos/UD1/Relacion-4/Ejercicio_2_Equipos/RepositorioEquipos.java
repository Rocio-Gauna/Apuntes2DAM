import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RepositorioEquipos {
    // TODO 2.1 - Recuperar la lista serializada con ObjectInputStream.
    // Si no existe el .dat, devolver lista vacía. Comprobar tipos, IDs y duplicados.
    public static List<Equipo> cargar(Path archivo) throws IOException {
        // PASO 1: si el fichero falta, devuelve una lista vacía.
        // Si existe, lee un Object y valida su contenido.

        //CREAMOS LA LISTA VACIA, DONDE IRAN LOS EQUIPOS
        List<Equipo> equipos = new ArrayList<>();
        
        //Si el dat no existe, devulvo la lista vacia y termina aqui. 
        if(Files.notExists(archivo)){
            return equipos;
        }

        //ABRIR EL ARCHIVO BINARIO: los bytes va en un lector de objetos
        // UTILIZAMOS EL TRY-WITH-RESOURCES lo cierra al terminar
        try(ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(archivo))){
            //LEO EL OBJETO GUARDADO 
            Object contenido = entrada.readObject();

            //Compruebo que la caja es una lista; si no lo es el archivo no sirve. 
            if(!(contenido instanceof List<?>listaLeida)){
                throw new IOException("el archivo no tiene una lista");
            }

            // TEMPORALMENTE : Solo para comprobar la lectura.
            System.out.println("TEMPORALMENTE: ELEMENTOS LEIDOS"+ listaLeida.size());
            // TEMPORAL: muestra dónde busca Java el archivo y si existe. La borraremos después.
System.out.println("TEMPORAL - busco: " + archivo.toAbsolutePath() + " | existe: " + Files.exists(archivo));
        }catch(ClassNotFoundException e){
            //Convierto el error en una exception que es lo que declara el metodo
            throw new IOException("No se encontro la clase de los datos"+ e.getMessage());
        }
        //devolvemos la lista vacia: sin equipos en ella
        return equipos;
    }

    // TODO 2.2 - Crear carpeta y guardar la LISTA COMPLETA con ObjectOutputStream.
    // No usar APPEND en ficheros serializados.
    public static void guardar(Path archivo, List<Equipo> lista) throws IOException {
        // PASO 2: crea la carpeta y usa ObjectOutputStream.
        // El método es void: no necesita devolver nada.
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
        return false;
    }

    // TODO 2.4 - Exportar DOM XML. Crear raíz <equipos> y cada <equipo id="...">.
    // Dentro, <nombre> y <unidades>. No concatenar cadenas XML manualmente.
    // Los errores de XML se convierten en IOException para simplificar Main.
    public static void exportarXml(Path archivo, List<Equipo> lista) throws IOException {
        // PASO 4: crea un Document XML, elementos y Transformer.
    }
}
