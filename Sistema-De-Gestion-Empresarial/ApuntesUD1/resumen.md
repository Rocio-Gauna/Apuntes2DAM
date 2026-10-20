# RESUMEN

*usar para guardar y cargar*

con varios ficheros: 
``` Python
# Clientes
RUTA_CSV = Path(__file__).with_name("clientes_crm.csv")
CAMPOS = ["nombre", "email", "ciudad", "activo"]

# Productos
RUTA_CSV = Path(__file__).with_name("productos.csv")
CAMPOS = ["nombre", "precio", "stock"]
```
Si hay varios CSV: Una constante por fichero cada una con sus nombres y campos ejemplo : 

``` Python
RUTA_PARTICIPANTES = Path(__file__).with_name("participantes.csv")
RUTA_TALLERES = Path(__file__).with_name("talleres.csv")
RUTA_INSCRIPCIONES = Path(__file__).with_name("inscripciones.csv")
```

Guardar: open(RUTA, "w", newline="", encoding="utf-8") → DictWriter → writeheader() → for con writerow().


Cargar: comprobar si existe → open(RUTA, "r", ...) → DictReader → for convirtiendo tipos → append.

 **menu**

```Python
def main():
    clientes = []
    opcion = -1
    while opcion != 0:
        print("0) Salir")
        print("1) Listar")
        print("2) Alta")
        opcion = int(input("Opción: "))

        if opcion == 1:
            print("aquí irá listar")
        elif opcion == 2:
            print("aquí irá alta")
        elif opcion != 0:
            print("Opción no válida")

main()
```