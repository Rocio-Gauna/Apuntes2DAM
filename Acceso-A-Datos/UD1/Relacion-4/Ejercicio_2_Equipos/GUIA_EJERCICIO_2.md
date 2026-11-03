# Ejercicio 2. Archivo de equipos del centro

**Archivos que completas:** `RepositorioEquipos.java` y `Main.java`.

**Archivo que NO modificas:** `Equipo.java` (`Serializable` ya está implementado).

**Duración orientativa:** 75–90 minutos.

## Situación

El centro guarda su material en `datos/equipos.dat`, un fichero **binario que contiene una lista serializada de `Equipo`**. Ya hay cuatro equipos de ejemplo:

| ID | Equipo | Unidades |
|---|---|---:|
| 201 | Micrófono inalámbrico | 5 |
| 202 | Cables & adaptadores | 8 |
| 203 | Cámara réflex | 2 |
| 204 | Altavoz portátil | 4 |

El archivo `datos/ejemplo_xml_esperado.xml` muestra la **estructura deseada** para la exportación; no es una fuente de entrada al programa.

## Orden de trabajo obligatorio

1. **TODO 2.1 (`cargar`)**: devolver lista vacía si no existe el `.dat`. Si existe, abrir `ObjectInputStream` con `Files.newInputStream`, leer `Object`, comprobar que sea `List<?>`, comprobar que cada elemento sea `Equipo` válido y detectar IDs duplicados. Convertir `ClassNotFoundException` en `IOException`.
2. **Prueba de control:** en `Main`, opción **1**, deben verse los cuatro equipos; opción **2**, buscar 202, debe verse «Cables & adaptadores».
3. **TODO 2.2 (`guardar`)**: crear carpeta `datos` si falta, abrir `ObjectOutputStream` y guardar **la lista completa**, no una sucesión de objetos individuales. Nunca usar `APPEND`.
4. **TODO 2.3 (`cambiarUnidades`)**: modificar el equipo en la **lista recibida en memoria** y devolver `true`. Si el ID no existe o las unidades son negativas, devolver `false`. Este método NO guarda en disco.
5. **TODO 2.4 (`exportarXml`)**: crear DOM con la raíz `<equipos>`. Para cada objeto, generar `<equipo id="...">`, `<nombre>` y `<unidades>`. Guardar con `Transformer`, UTF-8 y sangría. No concatenar cadenas XML manualmente.
6. **TODO 2.5 (`Main`, casos 3, 5 y 6)**: conectar los métodos ya terminados con el menú y mostrar los mensajes desde `main`.

## Pruebas finales

- Cambia el ID **201** de 5 a **12** unidades y usa la opción **1**: se verá 12 en memoria.
- Usa **5 (recargar)** antes de guardar: el valor debe volver a 5.
- Cambia de nuevo a 12; usa **4 (guardar)** y reinicia `java Main`: el valor 12 debe mantenerse.
- Usa **6 (exportar XML)** y abre `datos/equipos_exportados.xml`. Debe haber 4 elementos `<equipo>` y el nombre «Cables & adaptadores» debe aparecer escapado en el XML como `&amp;`.
- Prueba un ID inexistente y unas unidades negativas: ambos se rechazan sin modificar la lista.

### Pistas técnicas

- `ObjectInputStream`, `ObjectOutputStream`, `readObject()`, `writeObject(lista)` y `instanceof`.
- `DocumentBuilderFactory`, `createElement`, `setAttribute`, `setTextContent`, `TransformerFactory`.
- **No uses `System.out` en `RepositorioEquipos`.** Todo mensaje va en `Main.main`.
- No se exige JSON en este simulacro: se reserva para una ampliación posterior de la Relación 3 con Jackson/Maven.
