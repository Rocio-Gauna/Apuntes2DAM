#Pedimos datos(productos,meses) y los convertimos a numero enteros. 

productos = int(input("Ingrese cantidad de productos: "))
meses = int(input("Ingrese numero de meses: "))

#Usamos un bucle para recorrer los productos del 1 al ultimo. 
#utilizamos el range(1,producto +1) "+1" para incluir el ultimo numero
for producto in range(1, productos +1):
    #dentro del mismo bucle recorremos los meses con range (1,meses +1) con la misma logica
    for mes in range(1,meses +1):
        #Mostramos dentro de los dos bucles (8 espacios), mostramos la combinacion
        print(f"Producto {producto} - Mes {mes}")
