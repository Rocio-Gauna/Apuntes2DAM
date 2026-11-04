numero = int(input("Escribe un numero: "))

if numero <1 : 
    print(f"ingrese un numero mayor a 1")
else:
    for numero in range(1, numero +1):
        print(f"PED-{numero:03d}")
