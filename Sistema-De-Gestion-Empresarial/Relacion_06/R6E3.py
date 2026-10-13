#Pide importes de operaciones mientras el valor introducido sea distinto de 0.
#Acumula el total y cuenta cuántos importes distintos de cero se han registrado.
#Construye el while con una condición e
contador =  0
importe = -1.0
total = 0.00

while importe  !=0:
    importe= float(input(" Ingresa importe distinto a 0:"))
    if importe !=0:
        total = total + importe
        contador +=1

print(f"El total es de: {total:.2f} EUR") 
print(f"Importes registrados: {contador}")  
