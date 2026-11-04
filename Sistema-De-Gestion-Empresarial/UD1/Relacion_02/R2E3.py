nombre_producto = input("Nombre del producto: ")
precio = float(input("precio: "))
cantidad = int(input("cantidad: "))
porcentaje = int(input("porcentaje:"))
porcentaje_ingresado = porcentaje / 100 

subtotal = precio * cantidad
descuento = subtotal * porcentaje_ingresado
total =  subtotal - descuento

print(f"Con precio: {subtotal:.2f},cantidad: {cantidad} y descuento {descuento}, el total es {total:.2f} EUR")
