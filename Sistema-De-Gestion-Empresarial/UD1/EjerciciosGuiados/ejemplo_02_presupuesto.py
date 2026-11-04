print ("AULATEC | PRESUPUESTO DE PRACTICA")

cliente = input ("Cliente: ")
producto = input ("Producto: ")
precio_texto  =input("Precio: ")
cantidad_texto = input("Cantidad: ")

precio = float(precio_texto)
cantidad = int(cantidad_texto)

subtotal = precio * cantidad 
recargo_simulado = subtotal * 0.21
total = subtotal + recargo_simulado

print ("\n --- PRESUPUESTO ---")
print(f"Cliente: {cliente}")
print(f"Producto: {producto}")
print(f"Precio unitario: {precio:.2f} EUR")
print(f"Cantidad: {cantidad}")
print(f"Subtotales: {subtotal:.2f}EUR")
print(f"Recarga simulado (21 %):{recargo_simulado:.2f}EURO")
print(f"Total simulado: {total:.2f}EUR")


print("AULATEC | PRESUPUESTO DE PRÁCTICA")
cliente = input("Cliente: ")
producto = input("Producto: ")
precio_texto = input("Precio unitario en EUR: ")
cantidad_texto = input("Cantidad: ")
precio = float(precio_texto)
cantidad = int(cantidad_texto)
subtotal = precio * cantidad
recargo_simulado = subtotal * 0.21
total = subtotal + recargo_simulado
print("\n--- PRESUPUESTO ---")
print(f"Cliente: {cliente}")
print(f"Producto: {producto}")
print(f"Precio unitario: {precio:.2f} EUR")
print(f"Cantidad: {cantidad}")
print(f"Subtotal: {subtotal:.2f} EUR")
print(f"Recargo simulado (21 %): {recargo_simulado:.2f} EUR")
print(f"Total simulado: {total:.2f} EUR")