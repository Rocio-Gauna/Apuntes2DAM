#Venta con un subtotal de 250€ 
#Descuento del 12% (0.12)
#Calcular cuanto se descuenta y el total del descuento y mostrar cada importe con decimales

subtotal = 250 
porcentaje = 12 / 100

descuento = subtotal * porcentaje

total = subtotal - descuento

print (f"Descuento: {descuento:.2f}EUR")
print (f"total: {total:.2f}EUR")