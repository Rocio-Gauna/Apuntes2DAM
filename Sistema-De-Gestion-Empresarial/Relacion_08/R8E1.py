precio = float(input("Ingrese un precio: "))
cantidad= int(input("ingrese una cantidad: "))

def precio_subtotal(precio, cantidad):
    return precio * cantidad

subtotal = precio_subtotal(precio, cantidad)

print(f" el precio es {subtotal:.2f}")
