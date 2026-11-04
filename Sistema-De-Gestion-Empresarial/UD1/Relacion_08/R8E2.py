#Crear clasificar_stock(stock) que devuelva "agotado, "stock bajo" o"stor suficiente siguiendo los limites de la relacion 5 la funcion no debe imprimir, debe devolver el texto. 
#comprobacion valores 0,3 y 10

stock = int(input("Ingrese el Stock: "))

def clasificar_stock(stock):
    if stock == 0:
        mensaje = "Agotado"
    elif stock < 0:
        mensaje = "ERROR: no puede ser negativo"
    elif stock < 5:
        mensaje = "Stock bajo"
    else:
        mensaje = "Stock suficiente"
    return mensaje 

resultado = clasificar_stock(stock)

print(f" {resultado}")


