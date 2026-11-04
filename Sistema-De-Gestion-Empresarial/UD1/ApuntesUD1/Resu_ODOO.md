Ejercicio 1 :

teoria previa empresa y persona-
 en la app de *contactos*
 guarda dos tipos :
 EMPRESA: Organizacion(Biblioteca Ronda)
 INDIVIDUAL(persona): una persona fisica(Laura Ronda)

 crear 4 empresas :
 pulsas Nuevo 

 ```
┌──────────────────────────────────────────────────────────┐
│ Contactos / Nuevo                          [Guardar]     │
├──────────────────────────────────────────────────────────┤
│  ( ) Individual   (•) Empresa      ← tipo de ficha       │
│                                                          │
│  Nombre:  [______________________________]               │
│                                                          │
│  Dirección: [____________]   Teléfono: [__________]      │
│  Ciudad:    [____________]   Correo:   [__________]      │
│                                                          │
│  Pestañas: Contactos y direcciones | Ventas y compras |  │
│            Notas internas | ...                          │
└──────────────────────────────────────────────────────────┘
 ```

 CAMPO-valor ejemplo a la hora de construir  
tipo Empresa
nombre [REP26] Biblioteca Ronda
Correo info@bibliotecaronda.example.test
nota interna actua como cliente
guardamos

cada uno necesita un correo distinto y nota interna como cliente cliente, provedor
las notas internas dependen del enunciado

registrar contactos y vincular a la empresa. 

pulsamos nuevo:
```
┌──────────────────────────────────────────────────────────┐
│ Contactos / Nuevo                          [Guardar]     │
├──────────────────────────────────────────────────────────┤
│  (•) Individual   ( ) Empresa                            │
│                                                          │
│  Nombre:  [______________________________]               │
│  Empresa: [______________________________] ▼            │
│  Puesto de trabajo: [____________]                       │
│                                                          │
│  Teléfono: [__________]   Correo: [__________]           │
└──────────────────────────────────────────────────────────┘
```

al escribir la empresa bibli despliega las sugerencias:

```
  Empresa: [Bibli____________] ▼
           ┌─────────────────────────────────────┐
           │ [REP26] Biblioteca Ronda            │
           │ Crear "Bibli"...                    │
           │ Buscar más...                       │
           └─────────────────────────────────────┘
```

campos /valor
tipo individual
nombre [REP26]Laura Ronda
empresa [REP26]biblioteca Ronda ( elegida)
puesto de trabajo directora de biblioteca
correo laura@bibliotecaronda.example.test

al guardar el nombre de la empresa junto al de la persona las otras se rellenaran igual 

-cambiar el teléfono y comprobar que se guardó
significa que vamos a tener que editar la ficha 
si buscamos salen dos resultados la empresa y la persona 
abrimos la ficha correspondiente y le damos guardar una ves editado el telefono 

para comprobar abrimos la ficha de contacto , volvemos a  la ficha de contacto y abrimos otra vez. 
- Actividad 4: búsqueda, filtro y agrupación

Teoría: la barra de búsqueda de Odoo tiene tres herramientas que se combinan:

Búsqueda: escribes texto y Odoo filtra por coincidencia parcial del nombre.
Filtros: condiciones predefinidas (por ejemplo, solo empresas).
Agrupar por: reorganiza la lista en bloques según un campo.

Ninguna borra ni modifica registros, solo cambian lo que ves. Por eso el enunciado te pide retirarlas después y observar cómo vuelve la vista completa.

Paso 1: buscar por parte del nombre. Escribes Ron en la barra. Odoo ofrece opciones de búsqueda debajo:

```
  [Ron_____________]
   ┌──────────────────────────────────────┐
   │ Buscar Nombre: Ron                   │
   │ Buscar Correo: Ron                   │
   │ ...                                  │
   └──────────────────────────────────────┘
```

Pulsas Intro y salen todas las fichas cuyo nombre contiene “Ron”. Verías Biblioteca Ronda y Laura Ronda, y posiblemente alguna ficha de ejemplo que también contenga esas letras. El texto aparece como una etiqueta (chip) dentro de la barra.

Paso 2: aplicar un filtro de empresas. Con la búsqueda aún activa, abres el desplegable Filtros (la flecha ▼ de la barra):
````
  Filtros                     Agrupar por
  ──────────────────          ──────────────────
  ☐ Individuos                ☐ Comercial
  ☑ Empresas                  ☐ País
  ☐ Archivados                ☐ Empresa
  ...                         ...

```
Marcas Empresas. Ahora solo queda Biblioteca Ronda y desaparece Laura Ronda, porque es Individual. Aquí ves otra vez por qué importa el tipo de ficha.

Paso 3: agrupar. En el desplegable Agrupar por eliges una opción disponible, por ejemplo País o Empresa. La lista pasa a mostrarse así:
```
 ▸ Sin país (1)
 ▸ España (3)
```
Cada grupo se despliega al hacer clic. El nombre exacto de las opciones puede variar según tu instalación; el enunciado dice “una opción disponible”.

Paso 4: retirar filtro y agrupación. Haces clic en la x de cada etiqueta de la barra (o la desmarcas en el desplegable). La vista vuelve a mostrar todos los contactos, sin agrupar. Observa que ninguna ficha ha desaparecido: solo cambió la forma de verlas.


ejercicio 2:

