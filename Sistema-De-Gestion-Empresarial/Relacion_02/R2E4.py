#pide producto Inicial, unidades recibidas, y unidades vendidas
#calcula el stock
#mostrar el resumen de movimientos 

producto_inicial = int(input("Producto inicial: "))
unidades_recibitas = int(input("Unidades recibidas: "))
unidades_vendidas = int(input("Unidades vendidas: "))
stock = producto_inicial + unidades_recibitas - unidades_vendidas

print(f" con {producto_inicial} iniciales, {unidades_recibitas} recibidas, y {unidades_vendidas} vendidas, quedan {stock}")