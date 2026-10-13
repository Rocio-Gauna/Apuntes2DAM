# Relación 7 - Ejercicio 3: Ficha con diccionario

# Paso 1: creamos el diccionario con las cuatro claves pedidas (datos ficticios)
cliente = {
    "nombre": "Lucía Vega",              # clave "nombre" -> texto
    "ciudad": "Granada",                 # clave "ciudad" -> texto
    "email": "lucia@ejemplo.com",        # clave "email" -> texto
    "activo": True                       # clave "activo" -> bool, sin comillas
}

# Paso 2: mostramos la ficha ANTES de modificarla
print("=== FICHA ANTES ===")                 # encabezado
for clave, valor in cliente.items():         # en cada vuelta: una clave y su valor
    print(f"{clave}: {valor}")               # muestra "clave: valor"

# Paso 3: cambiamos la ciudad (la clave ya existe, así que se sobrescribe el valor)
cliente["ciudad"] = "Madrid"

# Paso 4: añadimos la clave telefono (no existe, así que se crea); texto para conservar ceros
cliente["telefono"] = "600123456"

# Paso 5: mostramos la ficha DESPUÉS de modificarla
print("\n=== FICHA DESPUÉS ===")             # encabezado con línea en blanco antes
for clave, valor in cliente.items():         # mismo recorrido que antes
    print(f"{clave}: {valor}")               # ahora aparecen ciudad nueva y telefono