#Pide un precio unitario y cantidad
precio_unitario = float(input("Precio unitario: "))
cantidad = int(input("cantidad: "))
descuento_porcentaje_cinco = 0.05
descuento_porcentaje_diez = 0.10
subtotal = precio_unitario * cantidad
#aplicamos un 0% de descuento si compra menos de 5 unidades, un 5% entre 5 y 9, y u 10% apartir de un 10.
#Muestra subtotal, porcentaje aplicado y total. 
if cantidad < 5: 
     descuento = 0
     print(f"Con {precio_unitario} y {cantidad} unidades, el total tras el descuento debe ser de {descuento * subtotal:.2f}EUR")
elif cantidad <5 and cantidad < 10:
    descuento = subtotal * descuento_porcentaje_cinco
    total = subtotal - descuento
    print(f"Con {precio_unitario} y {cantidad} unidades, el total tras el descuento debe ser de {total:.2f}EUR")
else:
    descuento = subtotal * descuento_porcentaje_diez
    total = subtotal - descuento
    print(f"Con {precio_unitario} y {cantidad} unidades, el total tras el descuento debe ser de {total:.2f}EUR")