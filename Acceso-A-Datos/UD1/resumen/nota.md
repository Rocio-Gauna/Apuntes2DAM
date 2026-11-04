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