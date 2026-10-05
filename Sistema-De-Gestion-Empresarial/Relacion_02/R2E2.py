#Factura simulada de un servicio
#Pedimos un nombre del cliente
#nombre del servicio
#precio unitario
#cantidad
#Convertimos el precio en float y cantidad en INT .
#Calculamos el subtotal y presntamos un resumen sin aplicar impuestos. 

nombre = input ("Nombre: ")
nombre_servicio = input("Nombre del servicio: ")
precio_unitario = float(input("Precio unitario: "))
cantidad = int(input("Cantidad: " ))

subtotal = cantidad * precio_unitario

print(f"Precio Unitario: {precio_unitario:.2f}EUR X {cantidad}, Subtotal : { subtotal:.2f}EUR")
