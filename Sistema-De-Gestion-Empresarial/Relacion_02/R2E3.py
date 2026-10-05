nombre_producto = input("Nombre del producto: ")
precio = float(input("precio: "))
cantidad = int(input("cantidad: "))
porcentaje = int(input("porcentaje:"))
porcentaje_ingresado = porcentaje / 100 

subtotal = precio * cantidad
descuento = subtotal * porcentaje_ingresado
total =  subtotal - descuento

print(f"Subtotal : {subtotal:.2f}EUR")
print(f"cantidad: {cantidad}")
print(f"Descuento: {descuento} %")
print(f"Total es de : {total} EUR")
