# Relación 7 - Ejercicio 4: Catálogo con lista de diccionarios

# Paso 1: lista con cuatro productos; cada producto es un diccionario (datos ficticios)
catalogo = [
    {"nombre": "Teclado", "categoria": "Hardware", "precio": 25.00, "stock": 10},
    {"nombre": "Webcam", "categoria": "Hardware", "precio": 40.00, "stock": 0},
    {"nombre": "Licencia Office", "categoria": "Software", "precio": 99.00, "stock": 5},
    {"nombre": "Monitor", "categoria": "Hardware", "precio": 150.00, "stock": 0}
]

# Paso 2: el contador empieza en cero ANTES del bucle
productos_disponibles = 0

# Paso 3: recorremos la lista; en cada vuelta "producto" es un diccionario completo
print("=== PRODUCTOS DISPONIBLES ===")                     # encabezado
for producto in catalogo:

    # Solo entramos si el stock de ESTE producto es mayor que 0
    if producto["stock"] > 0:
        # Mostramos los datos del producto consultando sus claves
        print(f"{producto['nombre']} ({producto['categoria']}) - "
              f"{producto['precio']:.2f} EUR - stock: {producto['stock']}")
        productos_disponibles += 1                         # contamos uno más

# Paso 4: fuera del bucle, mostramos cuántos están disponibles
print(f"\nProductos disponibles: {productos_disponibles}")
