Cargar un archivo

```java
public static List<Videojuego> cargar(Path archivo) throws IOException {

    // Cesta donde irán los videojuegos ya validados.
    List<Videojuego> juegos = new ArrayList<>();

    // Si el fichero no existe, devuelvo la lista vacía.
    if (Files.notExists(archivo)) {
        return juegos;
    }

    // Leo todas las líneas en UTF-8. Un fichero de cero bytes da una lista sin líneas.
    List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);

    // Fichero de cero bytes: es incorrecto.
    if (lineas.isEmpty()) {
        throw new IOException("El fichero está vacío");
    }

    // La primera línea debe ser exactamente la cabecera. Esto ya no es "saltarla":
    // ahora hay que comprobarla.
    if (!lineas.get(0).equals(CABECERA)) {
        throw new IOException("Línea 1: la cabecera no es la esperada");
    }

    // Conjunto de IDs ya vistos, para detectar repetidos.
    Set<Integer> idsVistos = new HashSet<>();

    // Empiezo en 1: la posición 0 (la cabecera) ya está comprobada.
    // Si solo hay cabecera, el bucle no da ninguna vuelta y se devuelve una lista vacía válida.
    for (int posicion = 1; posicion < lineas.size(); posicion++) {

        String linea = lineas.get(posicion);

        // Número de línea tal como se ve en el archivo.
        int numeroLinea = posicion + 1;

        // Las líneas vacías o solo con espacios se rechazan.
        if (linea.isBlank()) {
            throw new IOException("Línea " + numeroLinea + ": línea vacía");
        }

        // Separo por ';' conservando los campos vacíos del final.
        String[] campos = linea.split(";", -1);

        // Ahora son 5 campos: id, titulo, plataforma, stock, activo.
        if (campos.length != 5) {
            throw new IOException("Línea " + numeroLinea
                    + ": se esperaban 5 campos y hay " + campos.length);
        }

        // activo solo admite los textos exactos "true" y "false".
        // Se comprueba ANTES de convertir, porque Boolean.parseBoolean("hola")
        // devuelve false sin dar error y se colaría un valor inventado.
        if (!campos[4].equals("true") && !campos[4].equals("false")) {
            throw new IOException("Línea " + numeroLinea
                    + ": activo debe ser exactamente true o false");
        }

        // Variables numéricas fuera del try para usarlas después.
        int id;
        int stock;

        try {
            // id es el campo 0 y stock el campo 3.
            id = Integer.parseInt(campos[0].trim());
            stock = Integer.parseInt(campos[3].trim());
        } catch (NumberFormatException e) {
            throw new IOException("Línea " + numeroLinea
                    + ": el ID o el stock no son números enteros");
        }

        // Ya es seguro convertir el texto a boolean.
        boolean activo = Boolean.parseBoolean(campos[4]);

        // Fabrico el objeto: id, titulo, plataforma, stock, activo.
        Videojuego juego = new Videojuego(id, campos[1], campos[2], stock, activo);

        // esValido ya viene hecho: ID > 0, stock >= 0, título y plataforma no vacíos.
        if (!esValido(juego)) {
            throw new IOException("Línea " + numeroLinea + ": datos no válidos");
        }

        // add devuelve false si el ID ya estaba: está repetido.
        if (!idsVistos.add(id)) {
            throw new IOException("Línea " + numeroLinea + ": ID repetido (" + id + ")");
        }

        juegos.add(juego);
    }

    // Solo llego aquí si todo el fichero era correcto: no hay cargas parciales.
    return juegos;
}
```
Fíjate en que el stock 0 es válido (esValido solo rechaza el negativo), y que esValido ya rechaza títulos con ;

ActualizarStock 
Es actualizarPlaza. con 2 condiciones de mas. el orden importa, primero se descartan los casos en los que se devuelve false. despues el caso "mismo valor! y solo al final se escribe

```java
public static boolean actualizarStock(Path archivo, int id, int stock) throws IOException {

    // Leo todo el catálogo.
    List<Videojuego> lista = cargar(archivo);

    // Busco el videojuego (usa el buscar resuelto).
    Videojuego juego = buscar(lista, id);

    // Rechazo si no existe, si está inactivo o si el stock pedido es negativo.
    // No se escribe nada: ni el CSV ni la copia.
    if (juego == null || !juego.isActivo() || stock < 0) {
        return false;
    }

    // Si el stock ya vale lo pedido, se acepta pero no se escribe ni se crea copia.
    if (juego.getStock() == stock) {
        return true;
    }

    // Copia del CSV anterior, antes de reescribir.
    crearCopiaSiExiste(archivo);

    // Cambio el stock solo de este juego, en memoria.
    juego.setStock(stock);

    // Reescribo el catálogo entero.
    guardarTodo(archivo, lista);

    return true;
}
```
El orden de las dos primeras comprobaciones es la clave: “mismo valor” solo se acepta si está activo, y como lo inactivo ya salió con false arriba, el if del mismo valor solo se evalúa para juegos activos.


eliminar igual al ejercicio 1

```java
public static boolean eliminar(Path archivo, int id) throws IOException {

    List<Videojuego> lista = cargar(archivo);

    // Cesta nueva con todos los juegos MENOS el que se elimina.
    List<Videojuego> restantes = new ArrayList<>();

    // Banderita: se pone en true si aparece el ID.
    boolean encontrado = false;

    for (Videojuego juego : lista) {
        if (juego.getId() == id) {
            encontrado = true;          // no lo copio
        } else {
            restantes.add(juego);       // lo conservo
        }
    }

    // Si no existía, devuelvo false sin escribir nada.
    if (!encontrado) {
        return false;
    }

    // Copia antes de guardar.
    crearCopiaSiExiste(archivo);

    // Escribo los restantes. Si era el último, la lista queda vacía,
    // y guardarTodo escribe igualmente la cabecera.
    guardarTodo(archivo, restantes);

    return true;
}
```

Se pueden eliminar juegos activos e inactivos: por eso aquí no se mira isActivo().


ejercicio 2 

cargar 
Es tu cargar de equipos sin las líneas TEMPORAL. Las reglas nuevas del enunciado (elementos null, fichero de cero bytes) las resuelve el propio código: un null no pasa el instanceof, y un .dat de cero bytes hace que new ObjectInputStream(...) lance una IOException por sí solo.
```java
public static List<MaquinaArcade> cargar(Path archivo) throws IOException {

    // Cesta donde irán las máquinas validadas.
    List<MaquinaArcade> maquinas = new ArrayList<>();

    // Si el fichero no existe, lista vacía.
    if (Files.notExists(archivo)) {
        return maquinas;
    }

    // IDs ya vistos, para detectar duplicados.
    Set<Integer> idsVistos = new HashSet<>();

    // Abro el binario; try-with-resources lo cierra solo.
    try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(archivo))) {

        // Leo el objeto guardado (llega como Object).
        Object contenido = entrada.readObject();

        // Debe ser una lista; si es null o de otro tipo, el instanceof falla.
        if (!(contenido instanceof List<?> listaLeida)) {
            throw new IOException("El archivo no contiene una lista");
        }

        // Reviso cada elemento.
        for (Object elemento : listaLeida) {

            // Un elemento null o de otro tipo no es una MaquinaArcade.
            if (!(elemento instanceof MaquinaArcade maquina)) {
                throw new IOException("La lista contiene un dato que no es una máquina");
            }

            // esValida ya viene hecha: ID > 0, unidades >= 0, nombre no vacío.
            if (!esValida(maquina)) {
                throw new IOException("Máquina con datos no válidos (ID " + maquina.getId() + ")");
            }

            // ID repetido.
            if (!idsVistos.add(maquina.getId())) {
                throw new IOException("ID repetido: " + maquina.getId());
            }

            maquinas.add(maquina);
        }

    } catch (ClassNotFoundException e) {
        // Lo convierto en IOException, que es lo que declara el método.
        throw new IOException("No se encontró la clase de los datos: " + e.getMessage());
    }

    // Se devuelve solo si todo fue correcto: no hay cargas parciales.
    // Las máquinas con enVenta=false también están en la lista.
    return maquinas;
}
```

cambiar unidades

```java 
public static boolean cambiarUnidades(List<MaquinaArcade> lista, int id, int unidades) {

    // Busco la máquina (usa el buscar resuelto).
    MaquinaArcade maquina = buscar(lista, id);

    // Rechazo si no existe, si no está en venta o si las unidades son negativas.
    // La lista no se altera.
    if (maquina == null || !maquina.isEnVenta() || unidades < 0) {
        return false;
    }

    // Cambio las unidades solo en memoria. Si el valor coincide con el anterior,
    // también se acepta.
    maquina.setUnidades(unidades);

    return true;
}
```

exportar xml 
Es tu exportarXml de equipos con la raíz maquinas, el elemento maquina y un hijo más, enVenta. Exporta todas las máquinas de la lista, estén o no a la venta, así que no hay ningún if dentro del bucle

```java

public static void exportarXml(Path archivo, List<MaquinaArcade> lista) throws IOException {

    try {
        // Documento vacío en memoria.
        Document documento = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().newDocument();

        // Raíz <maquinas>.
        Element raiz = documento.createElement("maquinas");
        documento.appendChild(raiz);

        // Una vuelta por máquina. Con lista vacía no hay vueltas y queda solo la raíz.
        for (MaquinaArcade maquina : lista) {

            // <maquina id="...">
            Element elementoMaquina = documento.createElement("maquina");
            elementoMaquina.setAttribute("id", String.valueOf(maquina.getId()));

            // <nombre>: setTextContent escapa el & como &amp; automáticamente.
            Element elementoNombre = documento.createElement("nombre");
            elementoNombre.setTextContent(maquina.getNombre());

            // <unidades>
            Element elementoUnidades = documento.createElement("unidades");
            elementoUnidades.setTextContent(String.valueOf(maquina.getUnidades()));

            // <enVenta>: un boolean también se convierte a texto con String.valueOf.
            Element elementoEnVenta = documento.createElement("enVenta");
            elementoEnVenta.setTextContent(String.valueOf(maquina.isEnVenta()));

            // Cuelgo los tres hijos de <maquina>.
            elementoMaquina.appendChild(elementoNombre);
            elementoMaquina.appendChild(elementoUnidades);
            elementoMaquina.appendChild(elementoEnVenta);

            // Cuelgo <maquina> de la raíz.
            raiz.appendChild(elementoMaquina);
        }

        // Creo la carpeta de destino si falta.
        Path carpeta = archivo.getParent();
        if (carpeta != null) {
            Files.createDirectories(carpeta);
        }

        // Transformador seguro, en UTF-8 y con sangría.
        TransformerFactory fabrica = TransformerFactory.newInstance();
        fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Transformer transformador = fabrica.newTransformer();
        transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformador.setOutputProperty(OutputKeys.INDENT, "yes");

        // Escribo el árbol en el archivo. No se toca el .dat.
        transformador.transform(new DOMSource(documento), new StreamResult(archivo.toFile()));

    } catch (ParserConfigurationException | TransformerException e) {
        // Los errores de XML se convierten en IOException.
        throw new IOException("No se pudo exportar el XML: " + e.getMessage());
    }
}
```

con un json...
No, el código no es el mismo, pero la mitad del trabajo sí. Cambia cómo lees y conviertes el archivo, y se mantiene qué compruebas y cuándo lanzas el error.

Qué se queda igual

La estructura del método es idéntica: si el archivo no existe, lista vacía; se lee todo; se valida todo (ID positivo, stock no negativo, título y plataforma no vacíos con esValido, IDs repetidos con un Set); cualquier problema lanza IOException; y solo se devuelve la lista si todo fue correcto, sin cargas parciales.

Qué cambia

Con un CSV, el archivo es texto: lo cortas con split, cuentas campos y conviertes cada uno con parseInt. Con JSON no haces nada de eso: la librería Jackson lee el archivo y te entrega directamente los objetos con sus números y booleanos ya convertidos. Desaparecen split, campos.length, parseInt y NumberFormatException, y desaparece también la cabecera (el JSON no tiene).

Los errores de formato (llaves mal cerradas, un texto donde va un número, archivo vacío) los detecta Jackson y lanza una excepción que ya es un IOException, así que se propaga sola sin tener que convertirla.

Hay una trampa propia del JSON. Si en el registro falta un campo numérico, Jackson no se queja: pone 0 en los números y false en los booleanos, y se te colaría un videojuego sin id. Para detectarlo, el registro auxiliar usa Integer y Boolean (con mayúscula) en lugar de int y boolean, y así un campo ausente llega como null y puedes rechazarlo.

```java
// Registro auxiliar con la forma del JSON. Integer y Boolean (clases, con mayúscula)
// permiten saber si un campo faltaba, porque entonces valen null.
public record VideojuegoJson(Integer id, String titulo, String plataforma,
                             Integer stock, Boolean activo) {}

public static List<Videojuego> cargarJson(Path archivo) throws IOException {

    // Cesta donde irán los videojuegos validados.
    List<Videojuego> juegos = new ArrayList<>();

    // Si el archivo no existe, lista vacía.
    if (Files.notExists(archivo)) {
        return juegos;
    }

    // ObjectMapper es el traductor entre JSON y objetos Java.
    ObjectMapper mapper = new ObjectMapper();

    // Leo todo el JSON como una lista de registros.
    // TypeReference le dice a Jackson que esperamos una LISTA de VideojuegoJson.
    // Si el JSON está mal formado, vacío o con un tipo incorrecto, aquí salta
    // una excepción que ya es IOException: no hay que atraparla.
    List<VideojuegoJson> leidos = mapper.readValue(
            archivo.toFile(), new TypeReference<List<VideojuegoJson>>() {});

    // Si el archivo contiene literalmente "null", readValue devuelve null.
    if (leidos == null) {
        throw new IOException("El JSON no contiene una lista");
    }

    // IDs ya vistos, para detectar repetidos.
    Set<Integer> idsVistos = new HashSet<>();

    // No hay líneas, así que el mensaje usa el número de elemento (1, 2, 3...).
    int numero = 0;

    for (VideojuegoJson dato : leidos) {
        numero++;

        // Un elemento null dentro de la lista, o con campos ausentes, es incorrecto.
        if (dato == null || dato.id() == null || dato.stock() == null || dato.activo() == null) {
            throw new IOException("Elemento " + numero + ": faltan campos");
        }

        // Fabrico el objeto de siempre. Los Integer y Boolean ya no son null aquí.
        Videojuego juego = new Videojuego(dato.id(), dato.titulo(), dato.plataforma(),
                dato.stock(), dato.activo());

        // Misma validación que en el CSV. Un título null también falla aquí.
        if (!esValido(juego)) {
            throw new IOException("Elemento " + numero + ": datos no válidos");
        }

        // Mismo control de IDs repetidos que en el CSV.
        if (!idsVistos.add(juego.getId())) {
            throw new IOException("Elemento " + numero + ": ID repetido (" + juego.getId() + ")");
        }

        juegos.add(juego);
    }

    return juegos;
}
```

necesitas estos import 
```java
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
```


actualizar y eliminar

```java
public static boolean actualizarStock(Path archivo, int id, int stock) throws IOException {

    // cargar lee el archivo, sea del formato que sea.
    List<Videojuego> lista = cargar(archivo);
    Videojuego juego = buscar(lista, id);

    // Rechazo si no existe, está inactivo o el stock es negativo: sin escribir nada.
    if (juego == null || !juego.isActivo() || stock < 0) {
        return false;
    }

    // Mismo valor: se acepta sin escribir ni crear copia.
    if (juego.getStock() == stock) {
        return true;
    }

    crearCopiaSiExiste(archivo);   // copia del archivo, da igual su formato
    juego.setStock(stock);         // cambio en memoria
    guardarTodo(archivo, lista);   // guardarTodo escribe en el formato que toque
    return true;
}

public static boolean eliminar(Path archivo, int id) throws IOException {

    List<Videojuego> lista = cargar(archivo);
    List<Videojuego> restantes = new ArrayList<>();
    boolean encontrado = false;

    for (Videojuego juego : lista) {
        if (juego.getId() == id) {
            encontrado = true;
        } else {
            restantes.add(juego);
        }
    }

    if (!encontrado) {
        return false;
    }

    crearCopiaSiExiste(archivo);
    guardarTodo(archivo, restantes);
    return true;
}
```

un json ejemplo
```json
[
  {"id": 101, "titulo": "Pixel Fighters", "plataforma": "PC", "stock": 8, "activo": true},
  {"id": 103, "titulo": "Turbo & Drift", "plataforma": "PS5", "stock": 3, "activo": false}
]
```
import 
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
El registro auxiliar tiene la forma exacta del JSON. Se usan Integer y Boolean (con mayúscula) para poder detectar campos ausentes, porque entonces valen null; con int y boolean, Jackson pondría 0 y false en silencio.

cargar
```java
public static List<Videojuego> cargar(Path archivo) throws IOException {

    List<Videojuego> juegos = new ArrayList<>();

    // Si el archivo no existe, lista vacía.
    if (Files.notExists(archivo)) {
        return juegos;
    }

    // Lee todo el JSON y lo convierte en una lista de registros.
    // Si el archivo está vacío (0 bytes) o mal formado, Jackson lanza una excepción
    // que ya es un IOException: se propaga sola, sin atraparla.
    List<VideojuegoJson> leidos = new ObjectMapper().readValue(
            archivo.toFile(), new TypeReference<List<VideojuegoJson>>() {});

    // Si el archivo contiene literalmente "null", readValue devuelve null.
    if (leidos == null) {
        throw new IOException("El JSON no contiene una lista");
    }

    Set<Integer> idsVistos = new HashSet<>();
    int numero = 0;   // sin líneas: los mensajes usan el número de elemento

    for (VideojuegoJson dato : leidos) {
        numero++;

        // Elemento null o con algún campo numérico/booleano ausente.
        if (dato == null || dato.id() == null || dato.stock() == null || dato.activo() == null) {
            throw new IOException("Elemento " + numero + ": faltan campos");
        }

        // Los Integer y Boolean ya no son null: se convierten solos a int y boolean.
        Videojuego juego = new Videojuego(dato.id(), dato.titulo(), dato.plataforma(),
                dato.stock(), dato.activo());

        // Misma validación que con el CSV (un título null también falla aquí).
        if (!esValido(juego)) {
            throw new IOException("Elemento " + numero + ": datos no válidos");
        }

        if (!idsVistos.add(juego.getId())) {
            throw new IOException("Elemento " + numero + ": ID repetido (" + juego.getId() + ")");
        }

        juegos.add(juego);
    }

    return juegos;
}
```
guardar

```java
private static void guardarTodo(Path archivo, List<Videojuego> lista) throws IOException {

    // Creo la carpeta si falta.
    if (archivo.getParent() != null) {
        Files.createDirectories(archivo.getParent());
    }

    // Convierto cada objeto en su registro para JSON.
    List<VideojuegoJson> datos = new ArrayList<>();
    for (Videojuego juego : lista) {
        datos.add(new VideojuegoJson(juego.getId(), juego.getTitulo(),
                juego.getPlataforma(), juego.getStock(), juego.isActivo()));
    }

    // Escribe la lista con sangría, en UTF-8 (conserva las tildes).
    // Si la lista está vacía, escribe "[ ]", que es un JSON válido.
    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(archivo.toFile(), datos);
}
````



maquinas


```java

public record MaquinaJson(Integer id, String nombre, Integer unidades, Boolean enVenta) {}

public static List<MaquinaArcade> cargar(Path archivo) throws IOException {

    List<MaquinaArcade> maquinas = new ArrayList<>();
    if (Files.notExists(archivo)) {
        return maquinas;
    }

    // Jackson lee la lista; un archivo vacío o mal formado lanza IOException solo.
    List<MaquinaJson> leidas = new ObjectMapper().readValue(
            archivo.toFile(), new TypeReference<List<MaquinaJson>>() {});
    if (leidas == null) {
        throw new IOException("El JSON no contiene una lista");
    }

    Set<Integer> idsVistos = new HashSet<>();
    for (MaquinaJson dato : leidas) {
        // Elemento null o con campos ausentes.
        if (dato == null || dato.id() == null || dato.unidades() == null || dato.enVenta() == null) {
            throw new IOException("Elemento con campos ausentes");
        }
        MaquinaArcade maquina = new MaquinaArcade(dato.id(), dato.nombre(),
                dato.unidades(), dato.enVenta());
        if (!esValida(maquina)) {
            throw new IOException("Máquina con datos no válidos (ID " + dato.id() + ")");
        }
        if (!idsVistos.add(maquina.getId())) {
            throw new IOException("ID repetido: " + maquina.getId());
        }
        maquinas.add(maquina);
    }
    return maquinas;
}

public static void guardar(Path archivo, List<MaquinaArcade> lista) throws IOException {
    if (archivo.getParent() != null) {
        Files.createDirectories(archivo.getParent());
    }
    List<MaquinaJson> datos = new ArrayList<>();
    for (MaquinaArcade m : lista) {
        datos.add(new MaquinaJson(m.getId(), m.getNombre(), m.getUnidades(), m.isEnVenta()));
    }
    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(archivo.toFile(), datos);
}
```

Cómo se resume

Con cualquier formato, tu trabajo es el mismo en tres pasos: leer el archivo a una lista de objetos, validar todo (lanzando IOException ante cualquier problema), y escribir la lista completa. Lo único que se adapta es cómo se hace el primer y el tercer paso. Lo que se aprende para el CSV vale para el resto de formatos, y por eso si en un enunciado cambia el tipo de archivo, empieza por preguntarte: “¿cómo se leen y cómo se escriben los objetos en este formato?”, y deja todo lo demás tal cual.
...





csv... juegos

```java


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class GestorVideojuegos {

    private static final String CABECERA = "id;titulo;plataforma;stock;activo";

    // TODO 1.1 (2 puntos): cargar y validar el CSV según el enunciado.
    public static List<Videojuego> cargar(Path archivo) throws IOException {
        // "juegos" es una VARIABLE que guarda un OBJETO de tipo lista, fabricado con "new".
        // Empieza vacía: es la caja donde meteré los videojuegos buenos.
        List<Videojuego> juegos = new ArrayList<>();

        // Si el archivo NO existe, entrego la caja vacía y termino. No es un error.
        if (Files.notExists(archivo)) {
            return juegos;
        }

        // Copio todos los renglones del archivo a una lista de textos (UTF_8 para las tildes).
        List<String> lineas = Files.readAllLines(archivo, StandardCharsets.UTF_8);

        // Si no hay ningún renglón, el archivo tiene cero bytes: error.
        if (lineas.isEmpty()) {
            throw new IOException("El fichero está vacío");
        }

        // Si el primer renglón NO es igual a la cabecera exacta: error.
        if (!lineas.get(0).equals(CABECERA)) {
            throw new IOException("Línea 1: la cabecera no es la esperada");
        }

        // NUEVO: "idsVistos" es una VARIABLE que guarda un OBJETO de tipo Set (un conjunto).
        // Un conjunto es como una lista de invitados: no deja apuntar a nadie dos veces.
        // Lo uso para detectar IDs repetidos. Integer es la versión "objeto" de int.
        Set<Integer> idsVistos = new HashSet<>();

        // Bucle: repito el bloque una vez por cada renglón de datos (empiezo en 1, el 0 es la cabecera).
        for (int posicion = 1; posicion < lineas.size(); posicion++) {

            // Saco el renglón de esta vuelta y lo guardo en la variable "linea".
            String linea = lineas.get(posicion);

            // Sumo 1 para que el mensaje de error diga el número de línea que ves en el editor.
            int numeroLinea = posicion + 1;

            // Si el renglón está vacío o solo tiene espacios: error.
            if (linea.isBlank()) {
                throw new IOException("Línea " + numeroLinea + ": línea vacía");
            }

            // Corto el renglón por cada ";" y guardo los trozos en un ARRAY llamado "campos".
            // campos[0] es el id, campos[1] el título, campos[2] la plataforma,
            // campos[3] el stock y campos[4] el activo.
            String[] campos = linea.split(";", -1);

            // Si no hay exactamente 5 trozos: error. Se mira ANTES de usar campos[4].
            if (campos.length != 5) {
                throw new IOException("Línea " + numeroLinea
                        + ": se esperaban 5 campos y hay " + campos.length);
            }

            // Si "activo" no es exactamente "true" ni exactamente "false": error.
            if (!campos[4].equals("true") && !campos[4].equals("false")) {
                throw new IOException("Línea " + numeroLinea
                        + ": activo debe ser exactamente true o false");
            }

            // NUEVO: declaro dos VARIABLES numéricas FUERA del try.
            // Si las creara dentro, solo existirían dentro del try y no podría usarlas después.
            int id;
            int stock;

            // "try" significa "intenta esto, que podría fallar".
            try {
                // Integer.parseInt es un MÉTODO que convierte un texto ("101") en un número (101).
                // Su ARGUMENTO es el texto. trim() es un método del texto que quita espacios sobrantes.
                id = Integer.parseInt(campos[0].trim());
                stock = Integer.parseInt(campos[3].trim());

                // "catch" significa "si falló con este tipo de error, haz esto otro".
                // NumberFormatException es el error que lanza parseInt cuando el texto no es un número.
            } catch (NumberFormatException e) {
                // Lo convierto en IOException porque el enunciado lo pide.
                throw new IOException("Línea " + numeroLinea
                        + ": el ID o el stock no son números enteros");
            }

            // Convierto el texto "true"/"false" en un boolean de verdad.
            // Ya es seguro hacerlo, porque arriba comprobé que solo puede ser uno de los dos.
            boolean activo = Boolean.parseBoolean(campos[4]);

            // Fabrico un OBJETO Videojuego con "new". Sus ARGUMENTOS van en el orden del constructor:
            // id, titulo, plataforma, stock, activo. El título y la plataforma ya son texto,
            // no hay que convertirlos.
            Videojuego juego = new Videojuego(id, campos[1], campos[2], stock, activo);

            // esValido es un MÉTODO de esta misma clase (ya viene hecho). Recibe el objeto
            // y responde true si: id > 0, stock >= 0, y título y plataforma no están en blanco.
            // Con "!" pregunto: ¿NO es válido?
            if (!esValido(juego)) {
                throw new IOException("Línea " + numeroLinea + ": datos no válidos");
            }

            // add es un MÉTODO del conjunto: intenta apuntar el ID y responde true si lo apuntó,
            // o false si ya estaba. Con "!" pregunto: ¿ya estaba? Entonces está repetido.
            if (!idsVistos.add(id)) {
                throw new IOException("Línea " + numeroLinea + ": ID repetido (" + id + ")");
            }

            // El videojuego pasó todas las pruebas: lo meto en la caja.
            // add es un MÉTODO de la lista: la añade al final.
            juegos.add(juego);
        }

        // Solo llego aquí si TODAS las filas eran buenas. Si una falla, el "throw" corta todo antes,
        // así que nunca devuelvo una carga a medias.
        return juegos;
    }

    // TODO 1.2 (1,5 puntos): solo modificar el stock de un videojuego activo.
    public static boolean actualizarStock(Path archivo, int id, int stock) throws IOException {
        //throw new UnsupportedOperationException("Pendiente: actualizarStock");
        // Llamo al método cargar (el que acabamos de hacer): me devuelve la lista con todos los juegos.
        // "lista" es la VARIABLE donde la guardo.
        List<Videojuego> lista = cargar(archivo);

        // Llamo al método buscar (ya viene hecho). Sus ARGUMENTOS son la lista y el id.
        // Me devuelve el OBJETO Videojuego con ese id, o null ("nada") si no existe.
        Videojuego juego = buscar(lista, id);

        // "||" significa "o". Salgo con false si: no existe (null), O no está activo, O el stock es negativo.
        // isActivo() es un MÉTODO "get" del objeto: me dice si el juego está activo.
        // No escribo nada: ni el CSV ni la copia.
        if (juego == null || !juego.isActivo() || stock < 0) {
            return false;
        }

        // getStock() es un MÉTODO "get" del objeto: me da su stock actual.
        // Si ya es igual al que piden, no hay nada que cambiar.
        // Devuelvo true (la petición es válida) sin escribir ni crear copia.
        if (juego.getStock() == stock) {
            return true;
        }

        // Método ya hecho: guarda una copia del CSV (.bak) ANTES de cambiar nada.
        crearCopiaSiExiste(archivo);

        // setStock es un MÉTODO "set" del objeto: le cambia el stock.
        // Solo cambia este juego, y solo en la memoria. El CSV todavía no cambió.
        juego.setStock(stock);

        // Método ya hecho: reescribe el CSV entero con la cabecera y todos los juegos de la lista.
        guardarTodo(archivo, lista);

        // Todo salió bien.
        return true;
    }

    // TODO 1.3 (1,5 puntos): eliminar por ID con copia antes de guardar.
    public static boolean eliminar(Path archivo, int id) throws IOException {
        //throw new UnsupportedOperationException("Pendiente: eliminar");

        // Leo todos los juegos del archivo.
        List<Videojuego> lista = cargar(archivo);

        // Fabrico una caja nueva y vacía. Aquí pasaré todos los juegos MENOS el que se elimina.
        List<Videojuego> restantes = new ArrayList<>();

        // "encontrado" es una VARIABLE boolean: una banderita que empieza bajada (false).
        // Si veo el ID que busco, la subo (true).
        boolean encontrado = false;

        // Bucle "for-each": en cada vuelta, "juego" es un Videojuego distinto de la lista.
        for (Videojuego juego : lista) {

            // getId() es un MÉTODO "get" del objeto. Pregunto si este juego es el que hay que eliminar.
            if (juego.getId() == id) {
                // Es el que sobra: NO lo paso a la caja nueva. Solo subo la banderita.
                encontrado = true;
            } else {
                // Es otro: lo paso a la caja nueva para conservarlo.
                restantes.add(juego);
            }
        }

        // Si la banderita sigue bajada, ese ID no existía: devuelvo false sin escribir NADA.
        if (!encontrado) {
            return false;
        }

        // Copia de seguridad antes de reescribir (método ya hecho).
        crearCopiaSiExiste(archivo);

        // Escribo el archivo con la caja nueva (método ya hecho).
        // Si "restantes" quedó vacía, guardarTodo escribe igualmente la cabecera.
        guardarTodo(archivo, restantes);

        return true;
    }

    // RESUELTOS: no modificar.
    public static Videojuego buscar(List<Videojuego> lista, int id) {
        Videojuego encontrado = null;
        for (Videojuego juego : lista) {
            if (juego.getId() == id) {
                encontrado = juego;
            }
        }
        return encontrado;
    }

    private static boolean textoValido(String texto) {
        return texto != null && !texto.isBlank() && !texto.contains(";")
                && !texto.contains("\n") && !texto.contains("\r");
    }

    public static boolean esValido(Videojuego juego) {
        return juego != null && juego.getId() > 0 && juego.getStock() >= 0
                && textoValido(juego.getTitulo()) && textoValido(juego.getPlataforma());
    }

    public static boolean registrar(Path archivo, Videojuego nuevo) throws IOException {
        List<Videojuego> lista = cargar(archivo);
        boolean aceptado = esValido(nuevo) && buscar(lista, nuevo.getId()) == null;
        if (aceptado) {
            lista.add(nuevo);
            crearCopiaSiExiste(archivo);
            guardarTodo(archivo, lista);
        }
        return aceptado;
    }

    private static void guardarTodo(Path archivo, List<Videojuego> lista) throws IOException {
        if (archivo.getParent() != null) {
            Files.createDirectories(archivo.getParent());
        }
        List<String> lineas = new ArrayList<>();
        lineas.add(CABECERA);
        for (Videojuego juego : lista) {
            lineas.add(juego.getId() + ";" + juego.getTitulo() + ";" + juego.getPlataforma()
                    + ";" + juego.getStock() + ";" + juego.isActivo());
        }
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }

    private static void crearCopiaSiExiste(Path archivo) throws IOException {
        if (Files.exists(archivo)) {
            Files.copy(archivo, Path.of(archivo.toString() + ".bak"), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public static boolean restaurar(Path archivo) throws IOException {
        Path copia = Path.of(archivo.toString() + ".bak");
        boolean existe = Files.exists(copia);
        if (existe) {
            Files.copy(copia, archivo, StandardCopyOption.REPLACE_EXISTING);
        }
        return existe;
    }
}

```


.dat binario

```java



import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RepositorioMaquinas {

    // TODO 2.1 (2 puntos): recuperar y validar la lista serializada.
    public static List<MaquinaArcade> cargar(Path archivo) throws IOException {
        // throw new UnsupportedOperationException("Pendiente: cargar");

        List<MaquinaArcade> maquinas = new ArrayList<>();
        if (Files.notExists(archivo)) {
            return maquinas;
        }
        //Conjunto de IDs vistos. No admite repetidos 
        Set<Integer> idsVistos = new HashSet<>();
        // ObjectInputStream "descongela" los objetos guardados en el .dat.
        // Al estar entre paréntesis del try, se cierra solo al terminar.
        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(archivo))) {

            // Lee lo que se guardó. Llega como Object: todavía no sabemos qué es.
            Object contenido = entrada.readObject();

            // Comprueba que es una lista (un null tampoco pasa).
            // Si lo es, "listaLeida" es esa misma lista, ya con tipo de lista.
            if (!(contenido instanceof List<?> listaLeida)) {
                throw new IOException("El archivo no contiene una lista");
            }

            // Revisa los elementos uno por uno.
            for (Object elemento : listaLeida) {

                // Cada elemento debe ser una MaquinaArcade. Un null u otro tipo no pasa.
                if (!(elemento instanceof MaquinaArcade maquina)) {
                    throw new IOException("La lista contiene un dato que no es una máquina");
                }

                // esValida (ya hecha): ID positivo, unidades >= 0 y nombre no vacío.
                if (!esValida(maquina)) {
                    throw new IOException("Máquina con datos no válidos (ID " + maquina.getId() + ")");
                }

                // add devuelve false si el ID ya estaba apuntado: está repetido.
                if (!idsVistos.add(maquina.getId())) {
                    throw new IOException("ID repetido: " + maquina.getId());
                }

                // Pasó todas las comprobaciones: se guarda en la lista.
                maquinas.add(maquina);
            }

        } catch (EOFException e) {
            // Un .dat de cero bytes (o cortado) lanza este error sin mensaje.
            // Se convierte en uno claro para que Main muestre algo entendible.
            throw new IOException("El archivo está vacío o incompleto");
        } catch (ClassNotFoundException e) {
            // readObject puede lanzar este error; el enunciado pide convertirlo en IOException.
            throw new IOException("No se encontró la clase de los datos: " + e.getMessage());
        }

        // Solo se llega aquí si todo era correcto: nunca se devuelve una carga parcial.
        // Las máquinas con enVenta=false también se cargan.
        return maquinas;
    }

    // TODO 2.2 (1 punto): solo modificar máquinas en venta, únicamente en memoria.
    public static boolean cambiarUnidades(List<MaquinaArcade> lista, int id, int unidades) {
        //throw new UnsupportedOperationException("Pendiente: cambiarUnidades");
        // buscar (ya hecho) devuelve la máquina con ese ID, o null si no existe.
        MaquinaArcade maquina = buscar(lista, id);

        // Se rechaza si no existe, si no está en venta o si las unidades son negativas.
        // "maquina == null" va primero: así no se le pregunta nada a algo que no existe.
        if (maquina == null || !maquina.isEnVenta() || unidades < 0) {
            return false;
        }

        // Cambia las unidades solo de esa máquina, y solo en memoria.
        // Si el valor coincide con el anterior, también se acepta.
        maquina.setUnidades(unidades);

        return true;
    }

    // TODO 2.3 (2 puntos): exportar TODAS las máquinas de la lista mediante DOM.
    public static void exportarXml(Path archivo, List<MaquinaArcade> lista) throws IOException {
        //throw new UnsupportedOperationException("Pendiente: exportarXml");

    try {
        // Documento XML vacío en memoria: el árbol donde se irá colgando todo.
        Document documento = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder().newDocument();

        // Etiqueta raíz <maquinas>.
        Element raiz = documento.createElement("maquinas");
        documento.appendChild(raiz);

        // Una vuelta por máquina. Con la lista vacía no hay vueltas y queda solo la raíz.
        for (MaquinaArcade maquina : lista) {

            // <maquina id="..."> : el ID va como atributo, y los atributos son texto,
            // por eso String.valueOf convierte el número.
            Element elementoMaquina = documento.createElement("maquina");
            elementoMaquina.setAttribute("id", String.valueOf(maquina.getId()));

            // <nombre>: setTextContent escapa el & como &amp; automáticamente.
            Element elementoNombre = documento.createElement("nombre");
            elementoNombre.setTextContent(maquina.getNombre());

            // <unidades>: número convertido a texto.
            Element elementoUnidades = documento.createElement("unidades");
            elementoUnidades.setTextContent(String.valueOf(maquina.getUnidades()));

            // <enVenta>: el boolean también se convierte a texto.
            Element elementoEnVenta = documento.createElement("enVenta");
            elementoEnVenta.setTextContent(String.valueOf(maquina.isEnVenta()));

            // Se cuelgan los tres hijos de <maquina>, y <maquina> de la raíz.
            elementoMaquina.appendChild(elementoNombre);
            elementoMaquina.appendChild(elementoUnidades);
            elementoMaquina.appendChild(elementoEnVenta);
            raiz.appendChild(elementoMaquina);
        }

        // Crea la carpeta de destino si falta (el enunciado lo pide).
        if (archivo.getParent() != null) {
            Files.createDirectories(archivo.getParent());
        }
 
        // Transformer escribe el árbol en el archivo. Modo seguro, UTF-8 (tildes) y sangría (legible).
        TransformerFactory fabrica = TransformerFactory.newInstance();
        fabrica.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        Transformer transformador = fabrica.newTransformer();
        transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformador.setOutputProperty(OutputKeys.INDENT, "yes");

        // DOMSource es el árbol de origen; StreamResult es el archivo de destino.
        // No se toca el .dat: solo se escribe el XML.
        transformador.transform(new DOMSource(documento), new StreamResult(archivo.toFile()));

    } catch (ParserConfigurationException | TransformerException e) {
        // Los errores de configuración y de escritura del XML pasan a ser IOException.
        throw new IOException("No se pudo exportar el XML: " + e.getMessage());
    }
    }

    // RESUELTOS: no modificar.
    public static boolean esValida(MaquinaArcade maquina) {
        return maquina != null && maquina.getId() > 0 && maquina.getUnidades() >= 0
                && maquina.getNombre() != null && !maquina.getNombre().isBlank();
    }

    public static MaquinaArcade buscar(List<MaquinaArcade> lista, int id) {
        MaquinaArcade encontrada = null;
        for (MaquinaArcade maquina : lista) {
            if (maquina.getId() == id) {
                encontrada = maquina;
            }
        }
        return encontrada;
    }

    public static void guardar(Path archivo, List<MaquinaArcade> lista) throws IOException {
        if (archivo.getParent() != null) {
            Files.createDirectories(archivo.getParent());
        }
        try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(archivo))) {
            salida.writeObject(lista);
        }
    }
```