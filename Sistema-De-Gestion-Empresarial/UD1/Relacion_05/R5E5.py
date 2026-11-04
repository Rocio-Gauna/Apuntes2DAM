# VENTA MENSUALES DE TIENDA, WEB, TELEFNONO
ventas_tienda = float(input("ingrese el total de las ventas de la tienda: "))
ventas_web = float(input("Ingrese el total de las ventas de la web: "))
ventas_telefono = int(input("Ingrese el total de las ventas por telefono: "))

maximo = max(ventas_telefono, ventas_tienda, ventas_web)

cantidad_maximo = 0 
if ventas_tienda == maximo:
    cantidad_maximo +=1
if ventas_web == maximo:
    cantidad_maximo +=1
if ventas_telefono == maximo:
    cantidad_maximo +=1

if cantidad_maximo > 1: 
    resultado = f"Hay empate ({maximo:.2f}EUR)"
elif ventas_telefono ==  maximo:
    resultado = f"El mejor es telefono con {maximo:.2f}EUR"
elif ventas_tienda == maximo:
    resultado = f"El mejor es tieneda con {maximo:.2f}EUR"
elif ventas_web == maximo:
    resultado =f"El mejor es web con {maximo:.2f}EUR"

print(resultado)