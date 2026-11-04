# Relación 7 - Ejercicio 5: Categorías únicas y búsqueda de texto

# Paso 1: lista de categorías con valores repetidos (datos ficticios)
categorias = ["Hardware", "Software", "Hardware", "Servicios", "Software", "Hardware"]

# Paso 2: set() elimina los repetidos y deja una sola copia de cada categoría
categorias_unicas = set(categorias)

# Paso 3: mostramos cada categoría única (un set no tiene orden fijo)
print("=== CATEGORÍAS ÚNICAS ===")                  # encabezado
for categoria in categorias_unicas:                 # recorremos el set como una lista
    print(categoria)                                # una categoría por línea

# Paso 4: lista de nombres de producto en la que vamos a buscar (datos ficticios)
nombres_productos = ["Teclado", "Teclado inalámbrico", "Ratón", "Monitor", "Webcam"]

# Paso 5: pedimos el texto de búsqueda al usuario (llega como texto, no hace falta convertir)
texto_busqueda = input("\nTexto a buscar: ")

# Paso 6: recorremos los nombres y mostramos los que contengan el texto
print("=== RESULTADOS ===")                         # encabezado
for nombre in nombres_productos:                    # "nombre" toma cada producto de la lista

    # Pasamos AMBOS lados a minúsculas para ignorar mayúsculas y minúsculas
    if texto_busqueda.lower() in nombre.lower():
        print(nombre)                               # mostramos el nombre original