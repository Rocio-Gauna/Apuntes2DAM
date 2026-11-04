#Crear leer_entero(mensaje, minimo, maximo) que pida un enetero hasta que este dentro del rango, debe capturar ValueError y devolver el primer entero valido, Prueba pididneo una cantidad entre 1 y 100
#Comprobacion la funcion no termina hasta obtener un entero dentro de los limites. 


def leer_entero(mensaje, minimo, maximo):
    dato_valido = False
    while not dato_valido: 
        try:
            numero = int(input(mensaje))
            dato_valido = numero >= minimo and numero <= maximo
            if not dato_valido:
                print(f"El numero entero valido")
        except ValueError:
            print("Introduce un numero entero valido")
    return numero

cantidad = leer_entero("cantidad (entre 1 y 100): ", 1, 100)

print(f"Cantidad aceptada: {cantidad}")