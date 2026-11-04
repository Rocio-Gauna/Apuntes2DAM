#Iniciamos con un valor distinto a 0 para que entre en el while
opcion =-1
#El bucle sigue mientras la opcion sea distinta de 0

while opcion !=0:
    print("\n === menu ===")
    print("1- Alta ")
    print("2- Consulta")
    print("3- Informe")
    print("0- Salir")

    #Pedimos la opcion 
    opcion = int(input("Elige una opcion: "))

    #condicional para mostrar segun la opcion elegida

    if opcion == 1:
        print("Alta ")
    elif opcion ==2: 
        print("Consulta")
    elif opcion == 3:
        print("Informe")
    elif opcion == 0:
        print("saliendo del programa")
    else:
        print ( "ERROR: Opcion invalida")
