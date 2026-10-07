# Pide precio unitario y cantidad
precio_unitario = float(input("Precio unitario: "))
cantidad = int(input("Cantidad: "))

# El if/elif/else solo decide el porcentaje
if cantidad >= 10:
    porcentaje_descuento = 10
elif cantidad >= 5:
    porcentaje_descuento = 5
else:
    porcentaje_descuento = 0

#calculamos
subtotal = precio_unitario * cantidad
descuento = subtotal * porcentaje_descuento / 100
total = subtotal - descuento

# Un único print
print(f"Con {precio_unitario:.0f} EUR y {cantidad} unidades, el total tras el descuento debe ser {total:.2f} EUR")