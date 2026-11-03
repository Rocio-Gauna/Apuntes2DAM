# Ejercicio 1. Gestor de inscripciones a talleres

**Archivos que completas:** `GestorInscripciones.java` y `Main.java`.

**Archivo que NO modificas:** `Inscripcion.java`.

**Duración orientativa:** 75–90 minutos.

## Situación

El centro organiza talleres. Las inscripciones están en `datos/inscripciones.csv` y tienen esta estructura:

```csv
id;persona;taller;plazas
101;Lucía;Fotografía;2
102;Mario;Cerámica;1
103;Nuria;Teatro;3
104;Andrés;Pintura;1
```

Cada programa contiene partes resueltas (como el alta de inscripciones) y otras señaladas con `TODO`.

## Orden de trabajo obligatorio

1. **TODO 1.1 (`cargar`)**: si el archivo no existe, devolver lista vacía. Si existe, leer UTF-8, verificar cabecera, saltar la primera línea, separar por `;`, convertir ID/plazas y comprobar que la fila es válida y el ID no está repetido. Los datos corruptos deben provocar `IOException`.
2. **TODO 1.2 (`buscar`)**: devolver el objeto cuyo ID coincida; si no aparece, devolver `null`.
3. **Prueba de control:** ejecutar `Main`, opción **1**: deben aparecer las cuatro inscripciones; opción **2** con 102: «Mario»; con 999: «ID no encontrado». **No pruebes el alta hasta terminar los dos pasos anteriores.**
4. **TODO 1.3 (`actualizarPlazas`)**: rechazar plazas negativas o ID inexistente sin guardar. Si existe, cambiar plazas, llamar a `crearCopiaSiExiste` y `guardarTodo` y devolver `true`.
5. **TODO 1.4 (`eliminar`)**: construir una nueva lista sin el ID indicado. Si no está, devolver `false` sin crear copia. Si está, hacer copia y guardar la nueva lista.
6. **TODO 1.5 (`restaurar`)**: si existe el archivo `inscripciones.csv.bak`, restaurarlo sobre el CSV; devolver `true`/`false`. **No preguntar por teclado en este método.**
7. **TODO 1.6 (`Main`, casos 4, 5 y 6)**: solicitar los datos al usuario y mostrar el resultado devuelto por los métodos. Para restaurar, **pedir confirmación S/N**.

## Pruebas finales

- Cambia las plazas del ID **101** a **5**. Solo debe cambiar esa fila.
- Prueba a cambiar el ID **999** y asignar plazas **-1**. En ambos casos, no se modifica el CSV.
- Elimina el ID **104**. Las otras inscripciones siguen apareciendo.
- Elige **6** y confirma **S**: el último borrado debe deshacerse. Recuerda que la copia representa el estado anterior a **la última** escritura, no un historial de todas las operaciones.
- Reinicia `java Main`: los datos válidamente guardados deben seguir en el CSV.

### Pistas técnicas

- `Files.readAllLines(ruta, StandardCharsets.UTF_8)`; `split(";", -1)`; `Integer.parseInt`.
- `buscar(lista, id)`, `crearCopiaSiExiste(archivo)` y `guardarTodo(archivo, lista)` te evitan repetir código.
- **No hagas `System.out` en `GestorInscripciones`.** Todos los mensajes van en `Main.main`.
