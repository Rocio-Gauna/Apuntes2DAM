# UD2 · Proyectos completos de los tutoriales

Se necesita **JDK 17 o superior**, **Maven 3.9+** y **Docker con Docker Compose**.
Cada tutorial es un proyecto Maven independiente; abrir la carpeta que contiene `pom.xml`.
Los proyectos no incorporan librerías descargadas (Maven las recupera en su primera ejecución).

## 1. Arrancar MySQL

```bash
cd Infraestructura/mysql
docker compose up -d
docker compose ps
```

- MySQL escucha en `127.0.0.1:3307` y se crea el esquema `ad_ud2`.
- Usuario didáctico: `ad_user`; contraseña local: `ad_pass`.
- El fichero `init/01_esquema.sql` se ejecuta **solo al crear el volumen vacío**.
- No ejecutar `docker compose down -v` salvo que se quiera **borrar toda la base de datos**.

## 2. Ejecutar los proyectos JDBC y ORM

Desde cada carpeta (`T01_JDBC_CRUD`, `T02_Transacciones_Procedimientos`, `T03_Hibernate_ORM`):

```bash
mvn clean compile exec:java
```

- T01: CRUD con `PreparedStatement` y claves generadas; deja los clubes de prueba borrados.
- T02: transferencia real de 25.00 entre los clubes 1 y 2 cada vez que se ejecuta. **No es idempotente**.
- T03: consulta y actualización ORM; inserción opcional comentada en `Main`.

## 3. Arrancar MongoDB

```bash
cd Infraestructura/mongo
docker compose up -d
docker compose ps
```

Después, desde `T04_MongoDB_Documental`:

```bash
mvn clean compile exec:java
```

Se utiliza el puerto `127.0.0.1:27018`; la colección se crea automáticamente.
La BD documental no tiene autenticación: úsese solo en el equipo de prácticas.

## 4. Compilar el componente reutilizable

```bash
cd T05_Componente_Reutilizable/componente
mvn clean install
cd ../consumidor
mvn clean compile exec:java
```

El primer comando instala la biblioteca Java en el repositorio Maven **local**.

## Advertencias

- Las versiones de dependencias se fijan para reproducibilidad, no por ser las más recientes.
- Las contraseñas y puertos están pensados exclusivamente para desarrollo local.
- Si Docker ya utiliza el puerto, habrá que cambiar tanto el `compose.yaml` como la URL en Java.
- Los archivos SQL de inicialización no vuelven a aplicarse automáticamente tras editarse si el volumen ya existe.
- En los métodos de acceso a datos no hay `println`: los métodos **devuelven resultados** y `Main` los muestra.
