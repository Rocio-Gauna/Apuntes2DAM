numero_producto = int(input("numero de producto: "))
mes = int(input("numero de meses: "))

for producto in range(1,numero_producto+1):
    for mes in range(1,mes+1):
        print(f"Producto {producto} - Mes {mes}")