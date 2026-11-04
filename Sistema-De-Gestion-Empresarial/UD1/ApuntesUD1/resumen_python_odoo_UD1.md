# Resumen UD1: Python + Odoo (2.º DAM, SGE)

Guía de estudio para el examen. Orden: Python básico → funciones → excepciones → ficheros/CSV → clases → patrones → Odoo → repasos finales.

---

## ÍNDICE

1. Reglas de sintaxis
2. Variables, tipos y entrada/salida
3. Condicionales
4. Bucles
5. Colecciones (list, tuple, set, dict) y cadenas
6. Funciones
7. Excepciones (try/except)
8. Ficheros y CSV
9. Módulos
10. Clases y objetos (POO)
11. Herencia y composición
12. Patrones que se repiten en los ejercicios
13. Errores típicos de examen
14. Conexión con Odoo (para qué sirve todo esto)
15. REPASO FINAL ODOO (8 ejercicios)
16. REPASO FINAL PYTHON (4 proyectos)
17. Checklist final

---

## 1. Reglas de sintaxis

- La **sangría** (4 espacios) forma parte de la sintaxis: define los bloques.
- Después de `if`, `elif`, `else`, `for`, `while`, `def`, `class`, `try`, `except` van **dos puntos `:`**.
- Sin punto y coma al final de línea.
- `#` es un comentario.
- `True`, `False`, `None` con mayúscula inicial.
- Nombres de variables y funciones en `snake_case` (minúsculas con `_`). Clases en `Mayúscula` (`Producto`).
- Los decimales llevan **punto**: `12.5`, no `12,5`.

---

## 2. Variables, tipos y entrada/salida

```python
empresa = "AulaTec"   # str
unidades = 3          # int
precio = 45.50        # float
disponible = True     # bool
```

| Función | Para qué |
|---|---|
| `print(x)` | Mostrar |
| `input("msg")` | Leer del teclado. **Siempre devuelve `str`** |
| `int("3")` | Texto → entero |
| `float("4.5")` | Texto → decimal |
| `str(5)` | Número → texto |
| `len(x)` | Longitud |
| `sum(l)`, `min(l)`, `max(l)` | Suma, mínimo, máximo de una lista |

**f-strings**: un `{ }` por cada dato.

```python
print(f"Importe: {importe:.2f} EUR")   # 2 decimales
```

Operadores: `+ - * / **`, `//` (división entera), `%` (resto). Atajos: `x += 1`.
Comparación: `== != < > <= >=`. Lógicos: `and or not`. Pertenencia: `in`.

---

## 3. Condicionales

```python
if stock == 0:
    print("Agotado")
elif stock < 5:
    print("Bajo")
else:
    print("Suficiente")
```

- `elif` (no `else if`).
- `=` asigna, `==` compara.
- Comparación encadenada válida: `0 < cantidad <= stock`.

---

## 4. Bucles

### for
```python
for i in range(5):          # 0,1,2,3,4
for i in range(1, 6):       # 1..5
for p in productos:         # recorre una colección
for pos, p in enumerate(productos, start=1):
```

### while (con condición explícita, NO `while True` + `break`)
```python
opcion = -1
while opcion != 0:
    ...
```
El valor de arranque (`-1`) va **antes** del bucle, porque la condición se evalúa antes de cada vuelta.

### Acumulador (sumar)
```python
suma = 0              # FUERA del for
for linea in lineas:
    suma += linea.subtotal()
```

### Bandera (saber si algo pasó)
```python
encontrado = False
for p in productos:
    if condicion:
        encontrado = True
if not encontrado:     # FUERA del for
    print("No existe")
```

---

## 5. Colecciones y cadenas

| Tipo | Se escribe | Característica | Añadir |
|---|---|---|---|
| `list` | `[1, 2]` | mutable, ordenada | `append`, `insert`, `extend` |
| `tuple` | `(1, 2)` | no se modifica | — |
| `set` | `{1, 2}` | sin duplicados | `add` |
| `dict` | `{"a": 1}` | pares clave → valor | `d["clave"] = valor` |

```python
lista.append(x)         # UN elemento al final
lista.insert(0, x)      # en una posición
lista.extend([a, b])    # varios elementos sueltos
lista.remove(x)         # quita por valor
lista.pop(i)            # quita por posición
```

Trampa: `a.append([3, 4])` mete la lista entera como un elemento; `extend` mete sus elementos.

### Diccionarios
```python
cliente = {"nombre": "Lucía", "ciudad": "Granada"}
cliente["nombre"]            # acceder
cliente["email"] = "a@b.c"   # añadir o modificar
"nombre" in cliente          # ¿existe la clave?
cliente.get("tel", "")       # acceder sin KeyError
```
**Las claves distinguen mayúsculas**: `"precio"` ≠ `"Precio"` (da `KeyError`).

### Lista de diccionarios (muy usado)
```python
productos = [{"nombre": "Teclado", "precio": 25.0, "stock": 10}]
productos.append({"nombre": "Ratón", "precio": 12.5, "stock": 0})
for p in productos:
    print(p["nombre"])
```

### Cadenas
```python
t = "  Granada,ES "
t.strip()        # quita espacios de los extremos
t.lower()        # minúsculas   (.upper() mayúsculas)
t.split(",")     # separa -> lista
", ".join(lista) # une lista de str en un texto
"gra" in t.lower()   # contiene
t[0:7]           # slice [inicio:fin]
```

### Comprensión de listas
```python
dobles = [p * 2 for p in precios]
```

---

## 6. Funciones

```python
def calcular_subtotal(precio, cantidad):
    return precio * cantidad

resultado = calcular_subtotal(25.0, 3)
```

- Se declaran con `def`. Sin `return` devuelven `None`.
- **Parámetros por defecto**: `def f(importe, porcentaje=0)`.
- Argumentos por nombre: `f(100, porcentaje=10)`.
- Anotaciones de tipo (opcionales): `def f(x: float) -> float:`.
- **Una operación = una función.** Recibe los datos como parámetros y devuelve el resultado.

### `return` vs `print`
| Enunciado dice | Usa |
|---|---|
| "devuelve" | `return` |
| "muestra" / "imprime" | `print` |

Una función que devuelve es reutilizable: puedes imprimir, guardar o comparar el resultado.

### Listas y diccionarios se pasan por referencia
Si haces `productos.append(...)` dentro de una función, la lista original queda modificada. No hace falta devolverla.

### Punto de entrada
```python
def main():
    ...

if __name__ == "__main__":
    main()
```

### Orden de definición
Las funciones pueden estar definidas **después** de `main()` siempre que la llamada a `main()` esté al final del archivo.

---

## 7. Excepciones (try/except)

```python
try:
    numero = int(input("Nº: "))      # puede fallar
except ValueError:
    print("Número inválido")         # se ejecuta si falló
```

- Captura excepciones **concretas** (`ValueError`), no `except:` a secas.
- Errores frecuentes: `ValueError` (conversión), `KeyError` (clave inexistente), `NameError` (variable no existe), `AttributeError` (objeto sin ese atributo), `FileNotFoundError`, `ZeroDivisionError`, `SyntaxError`.

### Patrón: pedir número válido (versión R11)
```python
def pedir_entero(mensaje):
    valido = False
    numero = 0
    while not valido:
        try:
            numero = int(input(mensaje))
            valido = True
        except ValueError:
            print("Introduce un número entero válido")
    return numero
```
`valido = True` solo se ejecuta si la línea anterior no falló.

**Dos tipos de validación distintos:**
1. **try/except**: el dato ni siquiera es un número.
2. **Validación de lógica**: es un número, pero no tiene sentido (stock negativo, cantidad > stock).

> En el **Repaso final** no se permite `input()` ni `print()` dentro de funciones. Ver sección 16 para cómo adaptarlo.

---

## 8. Ficheros y CSV

### Leer / escribir texto
```python
with open("a.txt", "w", encoding="utf-8") as f:
    f.write("Lucía;Granada\n")

with open("a.txt", "r", encoding="utf-8") as f:
    for linea in f:
        print(linea.strip())
```
Modos: `"w"` escribe (sobrescribe), `"r"` lee, `"a"` añade al final. `with` cierra el fichero solo.

### CSV con diccionarios
```python
import csv

columnas = ["nombre", "precio", "stock"]

# ESCRIBIR
with open("productos.csv", "w", newline="", encoding="utf-8") as archivo:
    escritor = csv.DictWriter(archivo, fieldnames=columnas)
    escritor.writeheader()            # cabecera, UNA vez, fuera del for
    for p in productos:
        escritor.writerow(p)

# LEER
with open("productos.csv", "r", newline="", encoding="utf-8") as archivo:
    for fila in csv.DictReader(archivo):    # la cabecera no llega al bucle
        print(fila["nombre"])
```

- `newline=""` evita líneas en blanco; `encoding="utf-8"` conserva tildes.
- Las claves de los diccionarios deben **coincidir exactamente** con `fieldnames`.
- `print(lector.fieldnames)` muestra una lista; para verla limpia: `", ".join(lector.fieldnames)`.

### Trampa: el CSV devuelve TEXTO
Al leer hay que convertir:
```python
p["precio"] = float(fila["precio"])
p["stock"] = int(fila["stock"])
p["activo"] = (fila["activo"] == "True")   # "False" como texto es verdadero
```

### Cargar solo si el fichero existe
```python
from pathlib import Path
if Path("productos.csv").exists():
    ...
else:
    ...   # datos iniciales
```

---

## 9. Módulos

Un `.py` es un módulo.
```python
# modelos.py
class Producto: ...

# main.py
from modelos import Producto, Pedido
```
Estructura típica: `main.py` (menú e interacción), `modelos.py` (clases), `persistencia.py` (CSV).

---

## 10. Clases y objetos (POO)

### Concepto
- **Clase** = molde. **Objeto** = copia concreta (`Producto("Teclado", 25, 10)`).
- **Atributo** = dato del objeto (sustantivo). **Método** = acción (verbo).

### Qué tiene SIEMPRE una clase
| Pieza | Para qué |
|---|---|
| `class Nombre:` | Declarar la clase (nombre con Mayúscula) |
| `def __init__(self, ...)` | Constructor: se ejecuta al crear el objeto |
| `self.atributo = valor` | Guardar datos dentro del objeto |
| `def metodo(self, ...)` | Acción del objeto |

### Plantilla
```python
class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre          # atributo = parámetro
        self.precio = precio
        self.stock = stock

    def mostrar(self):                # método que MUESTRA
        print(f"{self.nombre}: {self.precio:.2f} EUR | stock {self.stock}")

    def vender(self, cantidad):       # método que MODIFICA y valida
        if 0 < cantidad <= self.stock:
            self.stock -= cantidad
            return True
        return False

    def reponer(self, cantidad):
        self.stock += cantidad

    def ficha(self):                  # método que DEVUELVE
        return f"{self.nombre} | {self.precio} | {self.stock}"
```

### Reglas clave
1. `self` es el **primer parámetro** de todo método, pero **no se pasa** al llamarlo.
2. `self.x = x`: izquierda = atributo; derecha = parámetro. Sin `self.` el dato se pierde.
3. Los métodos van a **4 espacios**, al nivel de `__init__`. Su cuerpo a 8.
4. Para modificar un atributo existente usa **su mismo nombre** (`self.ciudad = nueva_ciudad`, no `self.nueva_ciudad`).
5. Dentro de la clase, un dato se escribe `self.dato`; un método de la misma clase, `self.metodo()`.
6. **Validar antes de modificar**: solo se toca el dato si la condición es válida.
7. Cada objeto tiene sus propios datos.

### Uso
```python
p = Producto("Teclado", 25.0, 10)   # crear (instanciar), sin "new"
p.mostrar()                          # método
print(p.ficha())                     # método que devuelve
p.stock                              # atributo
```

### Cómo leer un enunciado
| Enunciado | Es | Dónde va |
|---|---|---|
| "Crea Cliente" | clase | `class Cliente:` |
| "con nombre, email, ciudad" | atributos | `__init__` |
| "Añade vender(cantidad)" | método | `def vender(self, cantidad):` |
| "devuelve ..." | `return` | dentro del método |

**Mayúscula/minúscula no decide si algo es clase o método. Lo decide `class` (clase) vs `def` dentro de una clase (método).**

---

## 11. Herencia y composición

### Herencia = "es un"
```python
class Empleado:
    def __init__(self, nombre, salario):
        self.nombre = nombre
        self.salario = salario

class Comercial(Empleado):                    # Comercial ES un Empleado
    def __init__(self, nombre, salario, ventas):
        super().__init__(nombre, salario)     # el padre guarda lo suyo
        self.ventas = ventas                  # la hija guarda lo propio

    def calcular_bonus(self):
        return self.ventas * 0.05

    def resumen(self):
        return f"{self.nombre} | {self.salario} | {self.calcular_bonus()}"
```

Jerarquía:
```
Empleado          <- padre: nombre, salario
└── Comercial     <- hija: + ventas, calcular_bonus(), resumen()
```

Reglas:
- `class Hija(Padre):` el padre va entre paréntesis.
- Si la hija define `__init__`, **debe llamar a `super().__init__(...)`** con lo que pida el del padre. Sin `self` dentro del paréntesis.
- Orden de parámetros en la hija: `self`, los del padre, los propios.
- Si no hay `super().__init__`, los atributos del padre no existen → `AttributeError` **al usarlos**, no al crear el objeto.
- La hija hereda los métodos del padre.
- **Sobrescritura**: si la hija define un método con el mismo nombre, usa el suyo; `super().metodo()` llama al del padre.
- Si la hija no define `__init__`, usa el del padre.
- **Polimorfismo**: dos hijas con el mismo método (`calcular_importe()`) calculado distinto.

### Composición = "tiene un"
```python
class LineaPedido:
    def __init__(self, producto, precio, cantidad):
        self.producto = producto
        self.precio = precio
        self.cantidad = cantidad

    def subtotal(self):
        return self.precio * self.cantidad

class Pedido:
    def __init__(self, cliente):
        self.cliente = cliente
        self.lineas = []                 # lista creada DENTRO de __init__

    def agregar_linea(self, linea):
        if linea.cantidad > 0:
            self.lineas.append(linea)
            return True
        return False

    def total(self):
        suma = 0
        for linea in self.lineas:
            suma += linea.subtotal()
        return suma
```

Jerarquía:
```
Pedido
├── cliente
└── lineas: [LineaPedido, LineaPedido, ...]
              ├── producto
              ├── precio
              └── cantidad  -> subtotal()
```

- **Nunca** `def __init__(self, lineas=[])`: la lista se comparte entre todos los objetos.
- Cada objeto hace lo suyo (`subtotal`); el contenedor coordina (`total`).
- Cuidado con dividir entre `len(lista)` si está vacía.

| | Herencia | Composición |
|---|---|---|
| Relación | "es un" | "tiene un" |
| Ejemplo | Comercial es Empleado | Pedido tiene líneas |
| Sintaxis | `class B(A)` | atributo que guarda objetos |

---

## 12. Patrones que se repiten

### Menú con condición explícita
```python
def main():
    productos = [...]
    opcion = -1
    while opcion != 0:
        print("0) Salir")
        print("1) Listar")
        print("2) Añadir")
        opcion = pedir_entero("Opción: ")     # menú ANTES de pedir

        if opcion == 1:
            listar_productos(productos)
        elif opcion == 2:
            aniadir_producto(productos)
        elif opcion != 0:
            print("Opción no válida")
```

### Añadir a lista de diccionarios
```python
def aniadir_producto(productos):
    nombre = input("Nombre: ")
    precio = pedir_decimal("Precio: ")
    stock = pedir_entero("Stock: ")
    if precio < 0 or stock < 0:
        print("No pueden ser negativos")
    else:
        productos.append({"nombre": nombre, "precio": precio, "stock": stock})
```

### Modificar un elemento (bandera)
```python
def modificar_stock(productos):
    buscado = input("Nombre: ")
    encontrado = False
    for p in productos:
        if p["nombre"].lower() == buscado.lower():
            p["stock"] = pedir_entero("Nuevo stock: ")
            encontrado = True
    if not encontrado:
        print("No existe")
```

### Buscar por texto (contiene)
```python
if buscado.lower() in p["nombre"].lower():   # texto buscado DENTRO del nombre
```

### Búsqueda que devuelve (versión "sin print")
```python
def buscar(productos, texto):
    resultado = []
    for p in productos:
        if texto.lower() in p["nombre"].lower():
            resultado.append(p)
    return resultado
```

### Código único (rechazar duplicados)
```python
def existe_codigo(productos, codigo):
    for p in productos:
        if p["codigo"] == codigo:
            return True
    return False
```

### Contar por categoría (diccionario)
```python
conteo = {}
for c in cartas:
    conteo[c.rareza] = conteo.get(c.rareza, 0) + 1
```

### Ordenar
```python
ordenadas = sorted(cartas, key=lambda c: c.valor, reverse=True)
top3 = ordenadas[:3]
```

### Guardar / cargar CSV (ver sección 8)

---

## 13. Errores típicos de examen

1. Olvidar `self` en la definición del método o `self.` al usar un atributo.
2. Olvidar `super().__init__()` en la hija.
3. Métodos fuera de la clase (mala sangría).
4. `print` donde el enunciado pide `return`.
5. `input()` sin convertir: `"3" * 2` da `"33"`.
6. `def f(lista=[])` como parámetro por defecto.
7. Faltan los `:` tras `if`, `def`, `class`, `for`, `while`.
8. `=` en vez de `==`.
9. Clave del diccionario con mayúscula distinta (`KeyError`).
10. `import csv` olvidado (`NameError`).
11. Typos en nombres de variables (`comlumnas` vs `columnas`).
12. `if not encontrado` dentro del `for` en vez de fuera.
13. `suma = 0` dentro del `for` en vez de fuera.
14. `writeheader()` dentro del `for`.
15. `in` al revés (`nombre in buscado` en vez de `buscado in nombre`).
16. Olvidar convertir tipos al leer un CSV.
17. Datos creados **después** de usarse (la lista `productos` va antes del `while`).
18. Llamar a `main()` y olvidar la última línea.

**Leer un error:** mira la **última línea** (tipo + nombre) y el número de línea que aparece justo encima.

**Truco de lógica:** simula con papel un caso. "Stock 10, vendo 15: ¿qué debería pasar?". Si no lo explicas en una frase, aún no puedes escribirlo.

---

## 14. Conexión con Odoo

### Por qué esta base importa
Los modelos de Odoo (UD3) son **clases de Python** que heredan de `models.Model`.

```python
from odoo import models, fields

class Producto(models.Model):          # herencia
    _name = "aulatec.producto"
    nombre = fields.Char()             # atributos = campos
    precio = fields.Float()
    stock = fields.Integer()

    def vender(self, cantidad):        # métodos con self
        ...
```

| Python | Odoo |
|---|---|
| clase | modelo (tabla) |
| atributo | campo (columna) |
| objeto | registro (fila) |
| método | acción / botón / cálculo |
| herencia | extender un modelo |
| composición (lista de objetos) | relaciones entre modelos (líneas de pedido, partner) |

### API externa con XML-RPC (UD1, ejemplo 03)
```python
import xmlrpc.client

url = "http://localhost:8069"        # o 8070 si cambiaste el puerto
db = "mi_base"
usuario = "tu_usuario"
clave = "tu_contraseña"              # NO la contraseña maestra

common = xmlrpc.client.ServerProxy(f"{url}/xmlrpc/2/common")
uid = common.authenticate(db, usuario, clave, {})

models = xmlrpc.client.ServerProxy(f"{url}/xmlrpc/2/object")

total = models.execute_kw(db, uid, clave, "res.partner", "search_count", [[]])

contactos = models.execute_kw(
    db, uid, clave, "res.partner", "search_read",
    [[]], {"fields": ["name", "email"], "limit": 5}
)
for c in contactos:               # lista de diccionarios
    print(c["name"], c["email"])
```

- Flujo: **conectar → autenticar → consultar `res.partner` → mostrar**.
- `search_count` cuenta; `search_read` devuelve una **lista de diccionarios**.
- Estas consultas solo leen: no crean ni modifican.
- Es el mismo patrón "lista de diccionarios + for" que ya usas.

### Dos formas de pasar datos a Odoo
1. **API** (consulta directa, XML-RPC).
2. **CSV**: Python genera el fichero y tú confirmas la importación en Odoo (Contactos → Importar registros, con **Probar** antes de ejecutar).

### Importar/exportar CSV en Odoo (resumen)
- Archivo en UTF-8, con cabecera.
- Primero exporta un contacto de prueba para ver los nombres de campo.
- Mapea columnas → **Probar** → ejecutar **una sola vez**.
- Exporta seleccionando solo los registros necesarios y comprueba cabecera, nº de filas, tildes y correos distintos.
- El separador (`,` o `;`) depende de la configuración.

---

## 15. REPASO FINAL ODOO (8 ejercicios)

### Reglas comunes
- Base local de pruebas, **Odoo 18 Community**.
- Prefijo **REP26** en todo registro nuevo.
- Datos ficticios, correos `@example.test`.
- **No** enviar invitaciones, **no** confirmar presupuestos, **no** facturar, **no** generar movimientos de inventario.
- Trabajar con importes **sin impuestos**.
- Apps: Contactos, CRM, Ventas, Proyecto, Calendario.

### Cadena de dependencias (qué se reutiliza)
```
E01 Contactos
 ├── Biblioteca Ronda ── Laura Ronda
 ├── Centro Horizonte ── Sara Vega
 ├── Academia Mirador
 └── Distribuciones Nube ── Mario Luna
        │
E02 CSV: TecnoLuz, Editorial Alba, Oficina Río  (importar / probar / exportar)
        │
E03 CRM (usa Centro Horizonte y Academia Mirador de E01)
 ├── Opp 1: Centro Horizonte - 6 portátiles ........ 3.600 €  (avanza de etapa)
 ├── Opp 2: Academia Mirador - 2 proyectores ....... 900 €
 └── Opp 3: Centro Horizonte - mantenimiento ....... 240 €  (se usa en E04)
        │
E04 Ventas
 ├── Servicios: Puesta a punto (45 €/ud), Instalación software (30 €/ud), Formación (35 €/h)
 ├── Presupuesto A (desde Opp 3): 4x45 + 2x30 = 240 → editar a 6x45 + 2x30 = 330 €
 └── Presupuesto B (Academia Mirador): 3x30 + 2x35 = 160 €
        │
E05 Proyecto: AulaTec - revisión de equipos (6 tareas, 3 etapas)
E06 Calendario: reunión 45 min + 2 eventos solapados + actividad desde Opp 1
E07 Caso integrador: Centro Cauce (5 apps)
E08 Auditoría: revisar todo lo anterior
```

### E01 · Contactos
**Jerarquía:** empresa → persona vinculada (campo "Empresa" de la persona).

| Paso | Acción |
|---|---|
| 1 | Crear 4 empresas con correos distintos y nota interna (cliente/proveedor) |
| 2 | Crear 3 personas con cargo y correo, **vinculadas** a su empresa |
| 3 | Cambiar teléfono de Centro Horizonte **editando** (no crear otra ficha) y reabrir |
| 4 | Buscar por parte del nombre, **filtro** de empresas, **agrupar**, quitar ambos |
| 5 | Buscar REP26 y comprobar vínculos y duplicados |

**Resultado:** 4 empresas + 3 personas (no 7 empresas). El teléfono persiste.

### E02 · CSV
| Paso | Acción |
|---|---|
| 1 | Exportar un contacto para ver los campos. Preparar `E02_importacion.csv` (3 empresas, UTF-8) |
| 2 | Importar → mapear columnas → **Probar** (mapear "es empresa" si existe) |
| 3 | **Una sola** importación. Comprobar 3 empresas, sin duplicados |
| 4 | Copia con el nombre de una fila vacío → **Probar**, observar avisos, **NO importar** |
| 5 | Seleccionar solo las 3 importadas → exportar nombre y correo → comparar |

**Resultado:** CSV exportado = 1 cabecera + 3 filas. No modificar a mano el CSV exportado.

### E03 · CRM
**Jerarquía:** cliente (contacto) → oportunidad → actividades.

| Paso | Acción |
|---|---|
| 1-2 | Crear 3 oportunidades con cliente e ingreso esperado correctos |
| 3 | Llamada en Opp 1 + seguimiento en Opp 2 (resumen + fecha futura). **Simular seguimiento con nota → marcar la llamada como hecha.** La otra queda pendiente |
| 4 | Avanzar **solo Opp 1** de etapa (sin ganada/perdida) + nota justificativa |
| 5 | Buscar por REP26, filtrar abiertas, agrupar por etapa, reabrir y comprobar |

**Resultado:** 3 abiertas (3.600 / 900 / 240 €); solo una ha avanzado.

### E04 · Ventas
**Jerarquía:** producto (servicio) → línea de presupuesto → presupuesto → oportunidad.

| Paso | Acción |
|---|---|
| 1 | Crear 3 **servicios** (tipo Servicio, vendibles) |
| 2 | Desde la Opp de mantenimiento: presupuesto 4 puestas + 2 instalaciones = 240 € |
| 3 | **Editar el mismo presupuesto**: 4 → 6 puestas = 330 €. Sin confirmar |
| 4 | Segunda oportunidad nueva (Academia Mirador) → presupuesto 160 € |
| 5 | Buscar ambos; reabrir oportunidad; el **ingreso esperado sigue en 240 €** salvo que lo edites |

Ingreso esperado (CRM) ≠ subtotal del presupuesto (Ventas).

### E05 · Proyecto
**Jerarquía:** proyecto → etapas (columnas) → tareas.

| Paso | Acción |
|---|---|
| 1 | Proyecto + etapas Pendiente / En curso / Finalizado |
| 2 | 6 tareas con descripción |
| 3 | Fecha límite futura a 3 tareas; asignarte una |
| 4 | 2 tareas a En curso; solo Inventariar equipos a Finalizado + estado **Listo** |
| 5 | Cambiar fecha de Actualizar navegadores + nota + reabrir |
| 6 | Filtro y quitarlo; verificar 6 tareas exactas |

**Distribución final:** 1 Finalizado, 1 En curso, 4 Pendiente.
**Etapa** (columna del tablero) ≠ **estado** de la tarea (Listo).

### E06 · Calendario
| Paso | Acción |
|---|---|
| 1 | Reunión interna de 45 min + descripción |
| 2 | Ver en semana y mes: sin duplicados |
| 3 | 2 eventos de 30 min solapados → ver solapamiento → mover el segundo |
| 4 | Reprogramar la reunión a otro día; la duración sigue en 45 min |
| 5 | Actividad desde Opp 1 → **comprobar** si aparece evento en Calendario (no presuponer) |

Resultado: una sola reunión de 45 min. No todo evento se enlaza automáticamente al CRM.

### E07 · Caso integrador: Centro Cauce
```
Contactos : [REP26] Centro Cauce + Elena Ruiz (vinculada)
CRM       : Opp "6 puestos y formación" 490 € + actividad "Definir alcance" + nota + etapa posterior
Ventas    : servicio Preparación de puesto (70 €/ud) + Formación (35 €/h)
            presupuesto 6x70 + 2x35 = 490 €  -> borrador
Proyecto  : "aula de formación" + 5 tareas, ≥2 etapas, fecha en 3
Calendario: evento "Coordinación Centro Cauce" 30 min, sin invitados externos
```
Resultado: 1 empresa + 1 persona + 1 oportunidad + 1 presupuesto + 1 proyecto (5 tareas) + 1 evento. **El presupuesto no se convierte en pedido.**

**Enlaces reales** (por campo de relación): persona → empresa; oportunidad → cliente; presupuesto creado desde la oportunidad → oportunidad.
**Coincidencias de nombre** (organizadas a mano): proyecto y evento, salvo que los vincules por cliente/asistente. Compruébalo en tu instalación.

### E08 · Auditoría
1. Oficina Río: añadir/corregir teléfono, reabrir, sin duplicados.
2. Exportar 3 contactos (Biblioteca Ronda, Centro Horizonte, Academia Mirador) → 3 filas, tildes.
3. Opp mantenimiento → Centro Horizonte, presupuesto 330 €; Centro Cauce → 490 €.
4. Proyecto y evento de Centro Cauce: ¿enlace directo o solo nombre?
5. Mínimo 6 comprobaciones; corregir lo incoherente (no inventar incidencias).

**Resultado:** sin duplicados de importación y sin presupuestos confirmados.

### Autocomprobación Odoo
- Mismo código REP26 en todo lo nuevo.
- Exportaciones hechas desde Odoo, sin editar el CSV a mano.
- Oportunidades con cliente, etapa, ingreso y actividades correctos.
- Presupuestos con cantidades, subtotales y estado sin confirmar.
- Cambios guardados (reabrir y comprobar).
- Acentos legibles en CSV.

---

## 16. REPASO FINAL PYTHON (4 proyectos)

### Reglas comunes (distintas de R11)
1. `if __name__ == "__main__": main()` como entrada.
2. **Ni `print()` ni `input()` dentro de clases, métodos o funciones auxiliares.** Toda la interacción va en `main()`. Las funciones **devuelven** datos, booleanos, listas, cadenas o códigos de estado.
3. Menú con condición explícita, sin `while True` + `break`.
4. Estructura mínima: `main.py` + `modelos.py` (+ `persistencia.py`).
5. `try/except ValueError` para convertir entradas; no cantidades negativas, ni claves repetidas, ni datos incoherentes.
6. `csv`, `with open`, `encoding="utf-8"`, `newline=""`. Se permite `pathlib`. Sin pandas, BD, APIs ni Odoo.
7. **Operaciones atómicas**: si falla, no cambia nada.

### Estructura de archivos
```
proyecto/
├── main.py          <- menú, input(), print(), try/except
├── modelos.py       <- clases (solo lógica, devuelven resultados)
├── persistencia.py  <- guardar_xxx(), cargar_xxx() con csv
└── *.csv            <- datos
```

### Cómo adaptar `pedir_entero` (sin `input` en funciones auxiliares)
En `main.py`, el `input()` y el `print()` se quedan en `main()`. La conversión puede hacerse con una función pura que recibe el texto y devuelve `None` si no es válido:
```python
def convertir_entero(texto):          # sin input ni print
    try:
        return int(texto)
    except ValueError:
        return None

# en main():
numero = convertir_entero(input("Cantidad: "))
if numero is None:
    print("Número inválido")
```

Menú con condición explícita usando esa función:
```python
def main():
    opcion = -1
    while opcion != 0:
        print(TEXTO_MENU)       # constante de texto definida arriba; el print está en main
        valor = convertir_entero(input("Opción: "))
        if valor is None:
            print("Introduce un número válido")
        else:
            opcion = valor
            if opcion == 1:
                ...
            elif opcion != 0:
                print("Opción no válida")
```
Así la función devuelve datos y `main()` es quien habla con el usuario.

### Patrón: método que devuelve un código de estado
```python
def agregar_linea(self, producto, cantidad):
    if cantidad <= 0:
        return "cantidad_invalida"
    if cantidad > producto.stock:
        return "stock_insuficiente"
    producto.stock -= cantidad          # solo si todo es válido
    self.lineas.append(LineaVenta(producto, cantidad, producto.precio))
    return "ok"

# en main():
resultado = venta.agregar_linea(prod, cantidad)
if resultado == "ok":
    print("Línea añadida")
elif resultado == "stock_insuficiente":
    print("No hay stock suficiente")
```

### Patrón: operación atómica (comprobar TODO primero)
```python
def intercambiar(self, codigo_viejo, carta_nueva):
    if codigo_viejo not in self.cartas:
        return "no_existe"
    if carta_nueva.codigo in self.cartas:
        return "codigo_ocupado"
    del self.cartas[codigo_viejo]        # solo tras todas las comprobaciones
    self.cartas[carta_nueva.codigo] = carta_nueva
    return "ok"
```

### Proyecto 01 · Floristería Lila (inventario y ventas)
**Clases y jerarquía (composición):**
```
Gestor / catálogo ── productos: [Producto(codigo, nombre, categoria, precio, stock)]
                └── ventas cerradas: [Venta]
Venta(numero, cliente)
└── lineas: [LineaVenta(producto, cantidad, precio_unitario)]
                         └── subtotal()
```
**Reglas de negocio:**
- Alta: rechaza código repetido, precio < 0, stock < 0. Reponer suma stock.
- Añadir línea: cantidad inválida o > stock → **no toca el stock**; línea válida → descuenta **una sola vez**.
- La línea conserva el **precio del momento** (`precio_unitario`).
- Descuento: si `subtotal >= 50` → `total = subtotal * 0.90` (sobre el **total**, no por línea).
- Cerrar venta → pasa al historial; consultar ventas + importe acumulado.
- CSV: `productos.csv` + `ventas.csv` (una fila por línea: `numero,cliente,codigo,cantidad,precio_unitario`). Al cargar, agrupa filas por `numero` para reconstruir cada venta.

**Datos iniciales:** FLO01 Rosas 3,50 € ×15 · FLO02 Tulipanes 2,25 € ×24 · FLO03 Orquídeas 18 € ×4.

**Pruebas:**
| Caso | Resultado |
|---|---|
| 15 rosas, vender 3 | stock 12 |
| pedir 20 rosas | stock no cambia |
| 2 orquídeas + 4 tulipanes | subtotal 45 €, sin descuento |
| 3 orquídeas | subtotal 54 €, total **48,60 €** |
| guardar y reiniciar | catálogo y ventas coherentes |

**Ampliación:** cancelar venta actual devolviendo unidades al stock.

**Menú:** 1 Ver · 2 Alta · 3 Reponer · 4 Nueva venta/añadir líneas · 5 Cerrar venta · 6 Consultar ventas · 7 Guardar · 0 Salir.

### Proyecto 02 · Colección de cartas Pokémon
**Jerarquía:**
```
Coleccion ── cartas: {codigo: Carta(codigo, nombre, tipo, rareza, valor)}
```
Un diccionario por código evita duplicados y hace la búsqueda directa.

**Reglas:**
- Añadir: código no vacío, no repetido, valor ≥ 0.
- Eliminar por código.
- Buscar por nombre o tipo (`.lower()`), filtrar por rareza.
- Intercambio atómico (ver patrón arriba).
- Estadísticas: valor total, nº de cartas, conteo por rareza (diccionario).
- CSV: cabecera `codigo,nombre,tipo,rareza,valor`; convertir `valor` a `float` al cargar.

**Datos iniciales:** PK01 Pikachu (Eléctrico, Común, 5 €) · PK02 Charizard (Fuego, Rara, 30 €) · PK03 Bulbasaur (Planta, Común, 4 €).

**Pruebas:**
| Caso | Resultado |
|---|---|
| valor inicial | **39 €** |
| alta con PK01 repetido | no cambia nada |
| intercambiar PK02 por carta con código PK03 | falla, **no se pierde PK02** |
| intercambio válido | reemplaza exactamente una y recalcula el total |
| guardar, reiniciar, cargar | misma lista sin duplicados |

**Ampliación:** ordenar por valor descendente y top 3.

**Menú:** 1 Listar · 2 Añadir · 3 Eliminar · 4 Buscar/filtrar · 5 Intercambiar · 6 Estadísticas · 7 Guardar · 0 Salir.

### Proyecto 03 · Centro de talleres
**Jerarquía (relación por códigos y emails, no copiando objetos):**
```
CentroTalleres
├── participantes: {email: Participante(email, nombre)}
├── talleres:      {codigo: Taller(codigo, titulo, plazas)}
└── inscripciones: [Inscripcion(email_participante, codigo_taller)]
```
**Reglas:**
- Alta de participante: email único.
- Inscribir: participante existe, taller existe, no duplicada, taller no completo.
- Cancelar: si no existe no afecta a otras.
- Consulta: inscritos, plazas libres = `plazas - inscritos`, ocupación = `inscritos / plazas * 100`.
- Buscar participante por fragmento de nombre y sus talleres.
- 3 CSV: `participantes.csv`, `talleres.csv`, `inscripciones.csv`. **Al cargar, descartar inscripciones con email o taller inexistente.**

**Datos:** Cerámica (2 plazas), Fotografía (3), Cocina (4).

**Pruebas:**
| Caso | Resultado |
|---|---|
| 3.ª inscripción en taller de 2 plazas | rechazada |
| mismo email dos veces en un taller | una sola inscripción |
| cancelar inscripción válida | libera una plaza |
| email/taller inexistente | no genera inscripción |
| 2 plazas, 1 inscrito | 1 libre y 50 % |

**Ampliación:** ranking por % de ocupación (`sorted(..., key=..., reverse=True)`).

**Menú:** 1 Participantes · 2 Alta · 3 Inscribir · 4 Cancelar · 5 Ver taller · 6 Buscar participante · 7 Guardar · 0 Salir.

### Proyecto 04 · Servicio técnico (herencia)
**Jerarquía:**
```
Trabajo(codigo, cliente, horas, estado)
├── Reparacion(+ precio_pieza)        calcular_importe() = horas*25 + precio_pieza
└── AsistenciaRemota                  calcular_importe() = horas*20
```
```python
class Trabajo:
    def __init__(self, codigo, cliente, horas, estado="pendiente"):
        self.codigo = codigo
        self.cliente = cliente
        self.horas = horas
        self.estado = estado

class Reparacion(Trabajo):
    def __init__(self, codigo, cliente, horas, precio_pieza, estado="pendiente"):
        super().__init__(codigo, cliente, horas, estado)
        self.precio_pieza = precio_pieza

    def calcular_importe(self):
        return self.horas * 25 + self.precio_pieza

class AsistenciaRemota(Trabajo):
    def calcular_importe(self):          # sin __init__ propio: usa el del padre
        return self.horas * 20
```
**Reglas:**
- Alta: código único, horas > 0, cliente no vacío, precio_pieza ≥ 0, tipo conocido.
- Estados en orden: `pendiente → en curso → finalizado`. Solo se avanza al **siguiente**; no se salta ni se retrocede.
```python
ORDEN = ["pendiente", "en curso", "finalizado"]

def cambiar_estado(self, nuevo):
    if nuevo not in ORDEN:
        return "estado_invalido"
    if ORDEN.index(nuevo) != ORDEN.index(self.estado) + 1:
        return "transicion_invalida"
    self.estado = nuevo
    return "ok"
```
- Buscar por cliente, filtrar opcionalmente por tipo y estado, mostrando el importe.
- Resumen: nº de trabajos por estado + importe total de los **finalizados**.
- CSV con columna `tipo` (`reparacion` / `remota`) para reconstruir el subtipo:
```python
if fila["tipo"] == "reparacion":
    trabajo = Reparacion(fila["codigo"], fila["cliente"], float(fila["horas"]),
                         float(fila["precio_pieza"] or 0), fila["estado"])
else:
    trabajo = AsistenciaRemota(fila["codigo"], fila["cliente"],
                               float(fila["horas"]), fila["estado"])
```

**Pruebas:**
| Caso | Resultado |
|---|---|
| Reparación 2 h + pieza 40 € | **90 €** |
| Remota 1,5 h | **30 €** |
| código repetido | no crea otro |
| pendiente → finalizado | rechazado, estado intacto |
| reiniciar | se conservan subtipos e importes |

**Ampliación:** cambiar horas de un trabajo no finalizado (recalcula importe).

**Menú:** 1 Listar · 2 Alta reparación · 3 Alta remota · 4 Cambiar estado · 5 Buscar/filtrar · 6 Resumen · 7 Guardar · 0 Salir.

---

## 17. Checklist final (antes de entregar o de examinar)

- [ ] El menú termina con la opción 0 y usa una condición explícita.
- [ ] Los datos se crean **antes** del bucle.
- [ ] Cada método tiene `self`; cada atributo se usa con `self.`.
- [ ] La hija llama a `super().__init__(...)`.
- [ ] Los métodos que "devuelven" usan `return`, no `print`.
- [ ] Las listas dentro de objetos se crean en `__init__` con `[]`.
- [ ] Las claves de diccionarios y `fieldnames` coinciden exactamente.
- [ ] Al leer CSV, convertí `float`, `int` y booleanos.
- [ ] Las operaciones fallidas **no dejan cambios parciales**.
- [ ] Probé el caso válido, el inválido y tras guardar y recargar.
- [ ] Sangría coherente (4 espacios) y `snake_case`.
- [ ] Un archivo por ejercicio (no todos pegados juntos).
