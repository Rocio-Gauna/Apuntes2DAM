# Repaso final UD1: Odoo 18 Community, con pantallas simuladas

Los 8 ejercicios completos. Cada uno lleva **teoría**, **pasos con la pantalla que verías** y **comprobación final**.

> Los menús, botones y etapas pueden variar un poco según tu instalación. Usa la opción equivalente que tengas.

## Reglas generales

- Prefijo **REP26** en todo lo nuevo (para localizarlo y distinguirlo de los datos de ejemplo).
- Datos ficticios, correos `@example.test`.
- No enviar invitaciones, no confirmar presupuestos, no facturar, no mover inventario.
- Corregir un dato = **editar la ficha existente**, nunca crear otra.
- Buscar, filtrar y agrupar solo cambian lo que ves; no modifican datos.

| Concepto | Qué es |
|---|---|
| Enlace real | Un registro guarda la referencia a otro (persona → empresa, presupuesto → oportunidad). |
| Coincidencia de nombre | Dos registros se llaman parecido pero no están enlazados. Lo organizas tú. |
| Etapa | Columna del tablero (Pendiente, En curso, Finalizado). |
| Estado | Marca propia de la tarea (Listo). No es la etapa. |

---

# Ejercicio 1: Contactos

## Teoría
La app **Contactos** tiene dos tipos de ficha: **Empresa** e **Individual**. La persona se vincula a su empresa con el campo **Empresa** (enlace real). Por defecto Odoo crea **Individual**, así que al crear una empresa hay que marcar **Empresa**.

## Actividad 1: crear las 4 empresas

```
┌──────────────────────────────────────────────────────────┐
│ Contactos / Nuevo                          [Guardar ☁]   │
├──────────────────────────────────────────────────────────┤
│  ( ) Individual   (•) Empresa                            │
│                                                          │
│  Nombre:  [REP26] Biblioteca Ronda                       │
│  Teléfono: [__________]   Correo: info@bibliotecaronda.. │
│                                                          │
│  Pestañas: Contactos y direcciones | Ventas y compras |  │
│            [Notas internas]                              │
│            ┌──────────────────────────────────────┐      │
│            │ Actúa como cliente                   │      │
│            └──────────────────────────────────────┘      │
└──────────────────────────────────────────────────────────┘
```

| Nombre | Correo | Nota interna |
|---|---|---|
| `[REP26] Biblioteca Ronda` | `info@bibliotecaronda.example.test` | Cliente |
| `[REP26] Centro Horizonte` | `contacto@centrohorizonte.example.test` | Cliente |
| `[REP26] Academia Mirador` | `contacto@academiamirador.example.test` | Cliente |
| `[REP26] Distribuciones Nube` | `pedidos@distribucionesnube.example.test` | Proveedor |

## Actividad 2: crear las 3 personas y vincularlas

Selector en **Individual**. En **Empresa** elige la sugerencia existente; **no** pulses `Crear "..."`.

```
┌──────────────────────────────────────────────────────────┐
│ Contactos / Nuevo                          [Guardar ☁]   │
├──────────────────────────────────────────────────────────┤
│  (•) Individual   ( ) Empresa                            │
│  Nombre:  [REP26] Laura Ronda                            │
│  Empresa: [Bibli____________] ▼                          │
│           ┌─────────────────────────────────────┐        │
│           │ [REP26] Biblioteca Ronda     ◄ elegir│       │
│           │ Crear "Bibli"...             ✗ no    │       │
│           └─────────────────────────────────────┘        │
│  Puesto de trabajo: Directora de biblioteca              │
│  Correo: laura.ronda@bibliotecaronda.example.test        │
└──────────────────────────────────────────────────────────┘
```

| Persona | Empresa | Puesto | Correo |
|---|---|---|---|
| `[REP26] Laura Ronda` | `[REP26] Biblioteca Ronda` | Directora de biblioteca | `laura.ronda@bibliotecaronda.example.test` |
| `[REP26] Sara Vega` | `[REP26] Centro Horizonte` | Coordinadora de compras | `sara.vega@centrohorizonte.example.test` |
| `[REP26] Mario Luna` | `[REP26] Distribuciones Nube` | Responsable comercial | `mario.luna@distribucionesnube.example.test` |

## Actividad 3: cambiar el teléfono de Centro Horizonte

```
┌──────────────────────────────────────────────────────────┐
│ Contactos / [REP26] Centro Horizonte    ☁↑ (sin guardar) │
├──────────────────────────────────────────────────────────┤
│  (•) Empresa                                             │
│  Nombre:   [REP26] Centro Horizonte                      │
│  Teléfono: [+34 600 000 111____]   ◄ editar aquí         │
│  Correo:   contacto@centrohorizonte.example.test         │
└──────────────────────────────────────────────────────────┘
```

Guarda (nube), vuelve a **Contactos** y reabre la ficha: el teléfono nuevo debe seguir ahí.

## Actividad 4: buscar, filtrar y agrupar

```
┌──────────────────────────────────────────────────────────┐
│ [x Ron______]   ▼                                        │
│   Filtros                  Agrupar por                   │
│   ☐ Individuos             ☐ Comercial                   │
│   ☑ Empresas               ☑ País                        │
├──────────────────────────────────────────────────────────┤
│ ▸ España (1)                                             │
│     [REP26] Biblioteca Ronda                             │
└──────────────────────────────────────────────────────────┘
```

Con el filtro **Empresas** desaparecen las personas (son Individuales). Quita las etiquetas (**x**) y vuelve la vista completa.

## Actividad 5: comprobación

```
┌──────────────────────────────────────────────────────────┐
│ [x REP26]                                                │
├──────────────────────────────────────────────────────────┤
│ ▢ [REP26] Academia Mirador                               │
│ ▢ [REP26] Biblioteca Ronda                               │
│ ▢ [REP26] Centro Horizonte                               │
│ ▢ [REP26] Distribuciones Nube                            │
│ ▢ [REP26] Laura Ronda, Biblioteca Ronda                  │
│ ▢ [REP26] Mario Luna, Distribuciones Nube                │
│ ▢ [REP26] Sara Vega, Centro Horizonte                    │
└──────────────────────────────────────────────────────────┘
```

Filtro **Empresas** = 4, filtro **Individuos** = 3. En la pestaña **Contactos y direcciones** de Centro Horizonte debe salir Sara Vega.

**Resultado esperado:** 4 empresas + 3 personas vinculadas (no 7 empresas) y el teléfono persiste.

---

# Ejercicio 2: CSV (importar, verificar y exportar)

## Teoría
Un **CSV** es un texto con cabecera y una fila por registro, separado por `,` o `;`. Se guarda en **UTF-8** para que se vean las tildes. Al importar hay que **mapear** columnas a campos de Odoo. **Probar** valida sin guardar nada.

## Actividad 1: preparar el CSV

Primero exporta un contacto de prueba (nombre y correo) para ver los nombres de campo. Después crea `E02_importacion.csv` en UTF-8:

```csv
Nombre,Correo electrónico,Es una empresa
[REP26] TecnoLuz,info@tecnoluz.example.test,True
[REP26] Editorial Alba,info@editorialalba.example.test,True
[REP26] Oficina Río,info@oficinario.example.test,True
```

## Actividad 2: importar con Probar

```
┌──────────────────────────────────────────────────────────┐
│ Importar un archivo        [Probar]  [Importar]  [Cancelar]│
├──────────────────────────────────────────────────────────┤
│ Archivo: E02_importacion.csv     Codificación: utf-8     │
│ Separador: ,                                             │
│                                                          │
│ Columna del archivo       →  Campo de Odoo               │
│ Nombre                    →  Nombre                      │
│ Correo electrónico        →  Correo electrónico          │
│ Es una empresa            →  Es una empresa              │
│                                                          │
│ ✔ Todo parece correcto                                   │
└──────────────────────────────────────────────────────────┘
```

Pulsa **Probar**: debe salir un aviso verde de que todo parece correcto.

## Actividad 3: importar una sola vez

Pulsa **Importar** **una vez**. Busca `REP26`: cada empresa aparece una sola vez y debe ser de tipo **Empresa**. Si alguna salió como Individual, corrígela desde su ficha.

## Actividad 4: CSV defectuoso (no se importa)

```csv
Nombre,Correo electrónico,Es una empresa
[REP26] TecnoLuz,info@tecnoluz.example.test,True
,info@editorialalba.example.test,True
[REP26] Oficina Río,info@oficinario.example.test,True
```

```
┌──────────────────────────────────────────────────────────┐
│ ✖ Se han encontrado errores                              │
│   Fila 2: el campo "Nombre" es obligatorio y está vacío  │
└──────────────────────────────────────────────────────────┘
```

Lee los avisos con **Probar** y **no importes** este archivo.

## Actividad 5: exportar las 3 empresas

```
┌──────────────────────────────────────────────────────────┐
│ Exportar datos                                           │
├──────────────────────────────────────────────────────────┤
│ Formato: (•) CSV  ( ) XLSX                               │
│ Campos a exportar:                                       │
│   ☑ Nombre                                               │
│   ☑ Correo electrónico                                   │
│                                          [Exportar]      │
└──────────────────────────────────────────────────────────┘
```

Resultado del CSV exportado:

```csv
Nombre,Correo electrónico
[REP26] TecnoLuz,info@tecnoluz.example.test
[REP26] Editorial Alba,info@editorialalba.example.test
[REP26] Oficina Río,info@oficinario.example.test
```

Comprueba: 1 cabecera + 3 filas, tilde de `Río` bien visible y 3 correos distintos.

**Resultado esperado:** CSV exportado con tres filas de datos y una cabecera.

---

# Ejercicio 3: CRM (oportunidades, actividades y embudo)

## Teoría
Una **oportunidad** es una posible venta ligada a un cliente, con **ingreso esperado** y **etapa**. Las **actividades** son tareas programadas (llamada, reunión). Avanzar de etapa no es lo mismo que marcarla **Ganada** o **Perdida**.

## Actividad 1 y 2: crear las 3 oportunidades

```
┌──────────────────────────────────────────────────────────┐
│ CRM / Nuevo                                              │
├──────────────────────────────────────────────────────────┤
│ Oportunidad: [REP26] Centro Horizonte - 6 portátiles     │
│ Cliente:     [REP26] Centro Horizonte                    │
│ Ingreso esperado: 3.600,00 €                             │
│                                                          │
│ Etapa: [Nuevo] > Calificado > Propuesta > Ganado         │
└──────────────────────────────────────────────────────────┘
```

| Oportunidad | Cliente | Ingreso esperado |
|---|---|---|
| `[REP26] Centro Horizonte - 6 portátiles` | `[REP26] Centro Horizonte` | 3.600 € |
| `[REP26] Academia Mirador - 2 proyectores` | `[REP26] Academia Mirador` | 900 € |
| `[REP26] Centro Horizonte - mantenimiento` | `[REP26] Centro Horizonte` | 240 € |

## Actividad 3: actividades

```
┌──────────────────────────────────────────────────────────┐
│ Chatter                                                  │
│ [Enviar mensaje] [Nota] [Programar actividad]            │
├──────────────────────────────────────────────────────────┤
│ Tipo: Llamada     Resumen: Llamar para confirmar necesidades │
│ Fecha límite: 28/10/2026          [Programar]            │
└──────────────────────────────────────────────────────────┘
```

Pasos:
1. Primera oportunidad: actividad **Llamada** con fecha futura.
2. Añade una **nota** que simule el seguimiento ("Llamada realizada, el cliente pide presupuesto").
3. Marca la llamada como **Hecha** (después de la nota).
4. Segunda oportunidad: actividad de seguimiento con fecha futura, **pendiente**.

## Actividad 4: avanzar solo la primera

```
 Nuevo        Calificado      Propuesta        Ganado
 [ ]          [ ]             [Centro Horizonte
                               - 6 portátiles]  ← arrastrar aquí
                               ✗ no pulsar Ganado / Perdido
```

Añade una nota: "Avance ficticio de práctica".

## Actividad 5: comprobación

```
┌──────────────────────────────────────────────────────────┐
│ [x REP26] [x Abiertas]   Agrupar por: Etapa              │
├──────────────────────────────────────────────────────────┤
│ ▸ Nuevo (2)                                              │
│     Academia Mirador - 2 proyectores          900 €      │
│     Centro Horizonte - mantenimiento          240 €      │
│ ▸ Propuesta (1)                                          │
│     Centro Horizonte - 6 portátiles         3.600 €      │
└──────────────────────────────────────────────────────────┘
```

**Resultado esperado:** 3 oportunidades abiertas (3.600 €, 900 € y 240 €); solo la primera ha avanzado.

---

# Ejercicio 4: Ventas (presupuestos conectados con el CRM)

## Teoría
Un **presupuesto** es el borrador de una venta con líneas y **subtotal sin impuestos**. Confirmarlo lo convertiría en pedido de venta, y aquí **no** se hace. Un **servicio** es un producto sin stock. El ingreso esperado de la oportunidad es un dato aparte: no se actualiza solo.

## Actividad 1: crear los servicios

```
┌──────────────────────────────────────────────────────────┐
│ Ventas / Productos / Nuevo                               │
├──────────────────────────────────────────────────────────┤
│ Nombre: Puesta a punto de portátil                       │
│ ☑ Puede venderse                                         │
│ Tipo de producto: [Servicio ▼]                           │
│ Precio de venta: 45,00 €                                 │
└──────────────────────────────────────────────────────────┘
```

| Servicio | Precio |
|---|---|
| Puesta a punto de portátil | 45 €/ud. |
| Instalación de software | 30 €/ud. |
| Formación individual | 35 €/hora |

## Actividad 2: presupuesto de mantenimiento

Abre `[REP26] Centro Horizonte - mantenimiento` y pulsa **Nuevo presupuesto**.

```
┌──────────────────────────────────────────────────────────┐
│ Presupuesto S00001                       [Presupuesto]   │
│ Cliente: [REP26] Centro Horizonte                        │
├──────────────────────────────────────────────────────────┤
│ Producto                      Cant.   Precio   Subtotal  │
│ Puesta a punto de portátil      4     45,00    180,00 €  │
│ Instalación de software         2     30,00     60,00 €  │
│                                  Subtotal sin impuestos: │
│                                                 240,00 € │
└──────────────────────────────────────────────────────────┘
```

## Actividad 3: editar el mismo presupuesto

Cambia las puestas a punto de 4 a 6: 6 × 45 + 2 × 30 = 270 + 60 = **330 €**. Guarda y no confirmes.

```
│ Puesta a punto de portátil      6     45,00    270,00 €  │
│ Instalación de software         2     30,00     60,00 €  │
│                                  Subtotal sin impuestos: │
│                                                 330,00 € │
```

## Actividad 4: segundo presupuesto

Crea una oportunidad nueva para Academia Mirador (por ejemplo `[REP26] Academia Mirador - servicios`) y desde ella un presupuesto:

```
│ Producto                      Cant.   Precio   Subtotal  │
│ Instalación de software         3     30,00     90,00 €  │
│ Formación individual            2     35,00     70,00 €  │
│                                  Subtotal sin impuestos: │
│                                                 160,00 € │
```

## Actividad 5: comprobación

```
┌──────────────────────────────────────────────────────────┐
│ Ventas / Presupuestos            [x REP26]               │
├──────────────────────────────────────────────────────────┤
│ S00001  Centro Horizonte    330,00 €   Presupuesto       │
│ S00002  Academia Mirador    160,00 €   Presupuesto       │
└──────────────────────────────────────────────────────────┘
```

Reabre la oportunidad de mantenimiento: arriba aparece el botón con el número de presupuestos enlazados, y el ingreso esperado sigue en **240 €**.

**Resultado esperado:** subtotales 330 € y 160 € antes de impuestos; los impuestos de tu base pueden cambiar el total.

---

# Ejercicio 5: Proyecto

## Teoría
Un **proyecto** agrupa **tareas** en un tablero con **etapas**. La **etapa** es la columna; el **estado** (Listo) es una marca de la tarea. Son cosas distintas.

## Actividad 1 y 2: proyecto y 6 tareas

```
┌──────────────────────────────────────────────────────────────┐
│ [REP26] AulaTec - revisión de equipos                        │
├───────────────┬───────────────┬──────────────────────────────┤
│  Pendiente    │  En curso     │  Finalizado                  │
├───────────────┼───────────────┼──────────────────────────────┤
│ Inventariar   │               │                              │
│ equipos       │               │                              │
│ Revisar       │               │                              │
│ antivirus     │               │                              │
│ Actualizar    │               │                              │
│ navegadores   │               │                              │
│ Comprobar red │               │                              │
│ Redactar      │               │                              │
│ informe       │               │                              │
│ Documentar    │               │                              │
│ incidencias   │               │                              │
└───────────────┴───────────────┴──────────────────────────────┘
```

| Tarea | Descripción de ejemplo |
|---|---|
| Inventariar equipos | Listar los equipos del aula con modelo y número de serie |
| Revisar antivirus | Comprobar que el antivirus está activo y actualizado |
| Actualizar navegadores | Instalar la última versión del navegador en cada equipo |
| Comprobar red | Verificar la conexión y la velocidad de cada puesto |
| Redactar informe | Resumir el estado de los equipos y las mejoras |
| Documentar incidencias | Anotar los fallos encontrados y su solución |

## Actividad 3: fechas y asignación

Fecha límite futura en tres tareas (Inventariar equipos, Revisar antivirus, Actualizar navegadores) y asígnate una en **Asignados**.

```
┌──────────────────────────────────────────────────────────┐
│ Tarea: Actualizar navegadores                            │
│ Asignados: [Tú]        Fecha límite: 30/10/2026          │
└──────────────────────────────────────────────────────────┘
```

## Actividad 4: mover tareas

```
 Pendiente        En curso            Finalizado
 ...              Revisar antivirus   Inventariar equipos  (✔ Listo)
```

1. Arrastra `Inventariar equipos` y `Revisar antivirus` a **En curso**.
2. Arrastra solo `Inventariar equipos` a **Finalizado** y marca su estado como **Listo**.
3. Observa: la etapa es la columna; el estado es la marca de la tarea.

## Actividad 5: modificar una fecha

Busca `Actualizar navegadores`, cambia su fecha límite, añade la nota "Se retrasa por falta de acceso a los equipos", guarda y reabre para comprobar.

## Actividad 6: comprobación

Mira el tablero, aplica un filtro y retíralo. Deben aparecer 6 tareas sin duplicados.

| Etapa | Tareas |
|---|---|
| Pendiente | 4 (Actualizar navegadores, Comprobar red, Redactar informe, Documentar incidencias) |
| En curso | 1 (Revisar antivirus) |
| Finalizado | 1 (Inventariar equipos) |

---

# Ejercicio 6: Calendario

## Teoría
El **Calendario** muestra eventos con fecha, hora y duración. Dos eventos a la misma hora **se solapan**. Reprogramar mueve el evento sin cambiar su duración. Una actividad de CRM de tipo reunión **puede** crear un evento en Calendario, pero **no se da por hecho**: se comprueba.

## Actividad 1: reunión interna

```
┌──────────────────────────────────────────────────────────┐
│ Calendario / Nuevo evento                                │
├──────────────────────────────────────────────────────────┤
│ Título: [REP26] Reunión interna AulaTec                  │
│ Inicio: 22/10/2026 09:00   Duración: 00:45               │
│ Invitados: (ninguno externo)                             │
│ Descripción: Coordinación interna                        │
└──────────────────────────────────────────────────────────┘
```

## Actividad 2: buscarla en semana y mes

```
 Vista Semana                    Vista Mes
 Jue 22                          ... 22 ...
 09:00 ┌────────────────┐             [REP26] Reunión interna
       │ Reunión interna│
 09:45 └────────────────┘
```

Debe aparecer **una sola vez** en cada vista.

## Actividad 3: solapamiento

```
 Antes (solapados)                  Después
 10:00 ┌────────────────────┐       10:00 ┌────────────────────┐
       │ Revisión de material│            │ Revisión de material│
       │ Preparar aula       │      10:30 └────────────────────┘
 10:30 └────────────────────┘       10:30 ┌────────────────────┐
                                          │ Preparar aula       │
                                    11:00 └────────────────────┘
```

Crea ambos de 30 minutos a las 10:00 con el prefijo REP26 y arrastra `Preparar aula` a las 10:30.

## Actividad 4: reprogramar

Mueve la reunión de 45 minutos a otro día y comprueba que sigue durando 45 minutos (09:00 → 09:45).

## Actividad 5: actividad desde CRM

Abre `[REP26] Centro Horizonte - 6 portátiles` y **Programar actividad** de tipo **Reunión**. Ve a Calendario y mira si aparece un evento vinculado. Anota lo que veas en tu instalación.

**Resultado esperado:** una sola reunión interna de 45 minutos, sin afirmar que todo evento se enlaza automáticamente al CRM.

---

# Ejercicio 7: caso integrador (Centro Cauce)

## Teoría
Se encadenan cinco apps: Contactos → CRM → Ventas → Proyecto → Calendario. Unos pasos crean **enlaces reales** y otros solo comparten el nombre del cliente.

## Actividad 1: contactos

```
┌──────────────────────────────────────────────────────────┐
│ [REP26] Centro Cauce                    (•) Empresa      │
│ Contactos y direcciones:                                 │
│   [REP26] Elena Ruiz — elena.ruiz@centrocauce.example.test│
└──────────────────────────────────────────────────────────┘
```

- Empresa `[REP26] Centro Cauce`, correo `info@centrocauce.example.test`.
- Persona `[REP26] Elena Ruiz`, vinculada a Centro Cauce.

## Actividad 2: CRM

```
┌──────────────────────────────────────────────────────────┐
│ [REP26] Centro Cauce - 6 puestos y formación             │
│ Cliente: [REP26] Centro Cauce     Ingreso esperado: 490 € │
│ Etapa: Nuevo > [Calificado] > Propuesta > Ganado         │
│ Actividad: Definir alcance   (pendiente)                 │
│ Nota: "Alcance pendiente de reunión con Elena Ruiz"      │
└──────────────────────────────────────────────────────────┘
```

Avanza a una etapa posterior **sin marcarla como ganada**.

## Actividad 3: Ventas

Crea el servicio **Preparación de puesto informático** (70 €/ud.) y reutiliza **Formación individual** (35 €/hora). Desde la oportunidad genera el presupuesto:

```
┌──────────────────────────────────────────────────────────┐
│ Presupuesto S00003                       [Presupuesto]   │
│ Cliente: [REP26] Centro Cauce                            │
├──────────────────────────────────────────────────────────┤
│ Preparación de puesto informático   6   70,00   420,00 € │
│ Formación individual                2   35,00    70,00 € │
│                          Subtotal sin impuestos: 490,00 €│
└──────────────────────────────────────────────────────────┘
```

Déjalo en **borrador**; no lo confirmes.

## Actividad 4: Proyecto

```
┌──────────────────────────────────────────────────────────────────────┐
│ [REP26] Centro Cauce - aula de formación                             │
├──────────────────┬──────────────────┬────────────────────────────────┤
│ Pendiente        │ En curso         │ Finalizado                     │
├──────────────────┼──────────────────┼────────────────────────────────┤
│ Comprobar        │ Preparar equipos │ Revisar puestos                │
│ conectividad     │                  │                                │
│ Formar usuarios  │                  │                                │
│ Entregar informe │                  │                                │
└──────────────────┴──────────────────┴────────────────────────────────┘
```

Cinco tareas repartidas en tres etapas y fecha límite en tres de ellas.

## Actividad 5: Calendario

```
 [REP26] Coordinación Centro Cauce   Duración: 00:30   Invitados: ninguno externo
```

## Actividad 6: enlaces reales y coincidencias de nombre

| Relación | ¿Qué tipo es? |
|---|---|
| Elena Ruiz → Centro Cauce | Enlace real (campo Empresa) |
| Oportunidad → cliente Centro Cauce | Enlace real |
| Presupuesto → oportunidad | Enlace real (se creó desde ella) |
| Actividad → oportunidad | Enlace real |
| Proyecto ↔ presupuesto | Coincidencia de nombre, organizada a mano |
| Evento ↔ presupuesto/proyecto | Coincidencia de nombre, organizada a mano |

**Resultado esperado:** 1 empresa + 1 persona + 1 oportunidad + 1 presupuesto + 1 proyecto con 5 tareas + 1 evento; el presupuesto no se convierte en pedido.

---

# Ejercicio 8: auditoría de datos y revisión final

## Teoría
Una **auditoría** revisa lo creado, detecta incoherencias y las corrige **sin crear duplicados**. Si todo está bien, no se inventan incidencias.

## Actividad 1: teléfono de Oficina Río

```
┌──────────────────────────────────────────────────────────┐
│ [REP26] Oficina Río                    (•) Empresa       │
│ Teléfono: +34 600 000 222    ◄ añadido                   │
│ Correo:   info@oficinario.example.test                   │
└──────────────────────────────────────────────────────────┘
```

Guarda, reabre y comprueba que persiste. Debe haber una sola ficha.

## Actividad 2: exportar tres empresas

```csv
Nombre,Correo electrónico
[REP26] Biblioteca Ronda,info@bibliotecaronda.example.test
[REP26] Centro Horizonte,contacto@centrohorizonte.example.test
[REP26] Academia Mirador,contacto@academiamirador.example.test
```

Comprueba 3 filas de datos, tildes correctas y ningún duplicado.

## Actividad 3: coherencia CRM y Ventas

```
 Oportunidad                           Cliente              Presupuesto
 Centro Horizonte - mantenimiento      Centro Horizonte     S00001  330,00 €
 Centro Cauce - 6 puestos y formación  Centro Cauce         S00003  490,00 €
```

## Actividad 4: proyecto y evento de Centro Cauce

Abre el proyecto y el evento y busca un enlace directo al presupuesto. Lo normal es que solo compartan el nombre de referencia.

## Actividad 5: seis comprobaciones

| # | Comprobación | Esperado | ✔ |
|---|---|---|---|
| 1 | Empresas y personas del E01 | 4 empresas y 3 personas | ☐ |
| 2 | Importación del E02 | 3 empresas, sin duplicados | ☐ |
| 3 | Oportunidades del E03 | 3 abiertas: 3.600 €, 900 €, 240 € | ☐ |
| 4 | Presupuestos del E04 | 330 € y 160 €, sin confirmar | ☐ |
| 5 | Proyecto AulaTec | 6 tareas: 4 Pendiente, 1 En curso, 1 Finalizado | ☐ |
| 6 | Caso Centro Cauce | 1 presupuesto de 490 €, proyecto con 5 tareas, 1 evento | ☐ |

Si algo no cuadra, **corrige editando** el registro existente.

**Resultado esperado:** sin duplicados creados por las importaciones y sin presupuestos confirmados.

---

# Anexo: cómo cambia de estado un pedido (presupuesto → pedido de venta)

## Teoría
En Ventas, el mismo documento pasa por varios **estados** según lo que hagas con él. El estado se ve arriba a la derecha de la ficha, en la barra de estados.

```
 Presupuesto  >  Presupuesto enviado  >  Pedido de venta
 (borrador)       (enviado al cliente)    (confirmado)

 Aparte:  Cancelado     |     Bloqueado (si tu base tiene activada esa opción)
```

## Botones que cambian el estado

```
┌──────────────────────────────────────────────────────────┐
│ S00001                        Presupuesto > Enviado > Pedido│
│ [Enviar por correo] [Confirmar] [Vista previa] [Cancelar]│
└──────────────────────────────────────────────────────────┘
```

| Estado | Cómo se llega a él |
|---|---|
| Presupuesto | Al crear el documento |
| Presupuesto enviado | Botón **Enviar por correo** (o **Marcar como enviado**) |
| Pedido de venta | Botón **Confirmar** |
| Cancelado | Botón **Cancelar** |
| Vuelta a Presupuesto | Botón **Volver a presupuesto** (desde Cancelado) |

Además del estado del documento hay un **estado de facturación** independiente: Nada que facturar, Por facturar y Totalmente facturado. Cambia al crear y validar facturas, no con un botón propio del pedido.

## Precaución en este repaso
Los ejercicios piden **no confirmar** presupuestos. Si quieres probar el cambio de estado, hazlo con un presupuesto de prueba aparte y no con los de los ejercicios. Confirmar un pedido de servicios puede, según la configuración, crear tareas o proyectos de forma automática.

---

# Autocomprobación final

- [ ] ¿El prefijo REP26 aparece en todos los registros nuevos?
- [ ] ¿Los contactos importados se exportaron de nuevo desde Odoo, sin tocar el CSV a mano?
- [ ] ¿Las oportunidades muestran cliente, etapa, ingreso esperado y actividades correctos?
- [ ] ¿Los presupuestos mantienen cantidades, subtotales y el estado sin confirmar?
- [ ] ¿Se han guardado tableros, eventos y cambios?
- [ ] ¿Los CSV se importan y exportan con los acentos legibles?
