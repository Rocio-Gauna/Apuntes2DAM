#Pide tres clientes ficticios con nombre y ciudad. Guarda una linea por cliente en cliente.txt con el formato, nombre;ciudad usando whit open(..., "w", encoding="utf-8") Despues vuelve a abrir el fichero y muestra su contenido .Compueba que el archivo existe y contiene exactamente tres lineas

#Pedimos (input) nombre y ciudad
#Guardamos la linea de cliente por cliente.txt con fomrato nombre;ciudad usando el whitopen()
#mostramos el contenido 
#comprobar si el archivo existe y contiene las 3 lineas. 

#crea cliente.txt ( o lo vacia si ya existia); se cierra solo al terminar el with
with open("clientes.txt", "w", encoding="utf-8") as archivo:
    #Repetimos 3 veces una por cliente 
    for vuelta in range(3):
        nombre = input("nombre del cliente: ")
        ciudad = input("ciudad: ")
        #Escribimos una linea con el formato nombre;ciudad 
        archivo.write(f"{nombre}; {ciudad}\n")

#abrimos el archivo y mostramos el contenido

contador_de_lineas = 0

try:
    # leemos, si no existe lanza FileNotFoundError
    with open("clientes.txt", "r",encoding="utf-8")as archivo:
        #recorre linea a linea
        for linea in archivo:
            print(linea.strip())
            contador_de_lineas = contador_de_lineas +1

    #Si llegamos sin error, el fichero no existe. 
    print("\nEl fichero cliente.txt existe")
#se ejecuta solo si el fichero no se encontro
except FileNotFoundError:
    print("\nEl fichero cliente.txt No existe")






