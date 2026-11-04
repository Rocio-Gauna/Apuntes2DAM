# T01B · JDBC con H2 embebida

Requisitos: Java 17 y Maven. **No necesita Docker.**

Desde esta carpeta ejecuta `mvn clean compile exec:java`.

Se crea una base de datos en `datos/clubes_h2.mv.db` dentro del proyecto.
Repetir el programa mantiene un único club porque MERGE actualiza/crea por ID.
Para empezar de cero, cierra el programa y elimina la carpeta `datos/` de este ejemplo.

Comparación: T01 se conecta a un servidor MySQL por TCP en 3307; T01B usa una biblioteca H2 embebida que abre directamente los ficheros locales de la base. Ambos ejemplos emplean JDBC y PreparedStatement.
