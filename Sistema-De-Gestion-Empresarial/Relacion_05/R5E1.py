#Pide un nombre de un producto

nombre_producto = input("nombre del producto: ")
#Pide un stock del produto 
stock = int(input("stock del producto: "))

if stock  == 0:
    print(f"Agotado")
elif stock <0:
    print(f"Error, no puede ser negativo")    
elif stock < 5:
    print(f"Stock bajo")
else:
    print(f"Stock suficiente ")
