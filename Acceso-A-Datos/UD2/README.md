# Unidad Didáctica 02 · Acceso a bases de datos desde Java

**Ciclo:** 2.º DAM · **Módulo:** Acceso a Datos (0486)  
**Continuidad:** UD1 «Acceso a ficheros en Java».

Esta carpeta agrupa **1 manual de UD2 (23 páginas)**, **5 tutoriales completos en PDF (37 páginas entre todos)**, **5 relaciones de 5 ejercicios cada una** y **7 proyectos Maven** (contando el complemento H2 embebido y los dos módulos del tutorial 05) con código Java, infraestructura SQL y configuraciones Docker listas para abrir.

## Orden didáctico

| Sesión | Tutorial resuelto que se hace primero | Relación que se hace después |
|---|---|---|
| 1 | `Tutoriales/T01_JDBC_y_CRUD_Resuelto.pdf` | `Relaciones/R01_JDBC_CRUD_y_Consultas.pdf` |
| 2 | `Tutoriales/T02_Transacciones_y_Procedimientos_Resuelto.pdf` | `Relaciones/R02_Transacciones_y_Procedimientos.pdf` |
| 3 | `Tutoriales/T03_Hibernate_ORM_Resuelto.pdf` | `Relaciones/R03_Hibernate_JPQL_y_Relaciones.pdf` |
| 4 | `Tutoriales/T04_MongoDB_Documental_Resuelto.pdf` | `Relaciones/R04_MongoDB_Documentos_y_Filtros.pdf` |
| 5 | `Tutoriales/T05_Componente_Reutilizable_Resuelto.pdf` | `Relaciones/R05_Componente_y_Proyecto_Final.pdf` |

La teoría general está en `UD2_Acceso_a_Bases_de_Datos_Java.pdf`. El tutorial T01 incluye una segunda práctica con **H2 embebida** y el archivo **ImportarTxt.java** para migrar el formato TXT de la UD1 a SQL.

## Directorio Codigo

- `Infraestructura/mysql`: Docker Compose, SQL de creación y datos iniciales.
- `Infraestructura/mongo`: Docker Compose para MongoDB.
- `T01_JDBC_CRUD`: CRUD JDBC, `ImportarTxt.java` y entrada TXT.
- `T01B_H2_Embebida`: JDBC sin servidor, con persistencia en fichero H2.
- `T02_Transacciones_Procedimientos`: transacciones y procedimiento almacenado.
- `T03_Hibernate_ORM`: entidades, JPQL y sesiones Hibernate.
- `T04_MongoDB_Documental`: MongoDB con documentos anidados.
- `T05_Componente_Reutilizable/componente`: biblioteca publicable en Maven local.
- `T05_Componente_Reutilizable/consumidor`: aplicación que utiliza la biblioteca.

Las instrucciones de ejecución están detalladas en cada tutorial y resumidas en `Codigo/README.md`.

## Requisitos y límites

- JDK 17 o posterior, Maven 3.9+ y Docker Compose (salvo el proyecto H2, que no requiere Docker).
- El primer arranque necesita Internet para obtener imágenes y dependencias.
- Todos los puertos del ejemplo Docker se publican **solo en `127.0.0.1`**.
- Las contraseñas son de **demostración local**, no para producción.
- El SQL inicial de MySQL se ejecuta al crear por primera vez el volumen; no se reejecuta si el volumen ya existe.
- Se han compilado mediante `javac` los fuentes que utilizan únicamente las APIs JDBC estándar. Los ejemplos de Hibernate y MongoDB requieren las dependencias Maven externas. **No se han podido ejecutar en esta generación los contenedores ni las pruebas integradas contra servidores en funcionamiento**.
- El panorama de bases de datos orientadas a objetos del **RA4** es introductorio. No equivale a un laboratorio completo con un SGBD nativo de objetos.

## Currículo de referencia

Real Decreto 405/2023, de 29 de mayo (actualización del módulo 0486 de DAM):
<https://www.boe.es/eli/es/rd/2023/05/29/405>

El temario aportado por el usuario en `DAM.rar` se utilizó como referencia de contenidos, especialmente acceso a BBDD relacionales, Hibernate y NoSQL. La estética se ha basado en la UD1 aportada.
