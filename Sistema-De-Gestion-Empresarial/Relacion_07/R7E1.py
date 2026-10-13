#Parte de la lista ["Teclado", "Ratón", "Monitor"]. Añade "Webcam", elimina "Ratón",ordena alfabéticamente y muestra cada producto numerado usando enumerate(...,start=1).

producto = ["Teclado", "Raton", "Monitor"]

print (producto[0]) #Muestra la 1era posicion 
print (producto[1]) #Muestra la 2da posicion
print(len(producto)) #Muestra Cuantos productos hay 

#Añadimos "Webcam" AL FINAL de la lista
producto.append("Webcam")
#Elimina el elemento que vale "Raton"
producto.remove("Raton")
#Ordena alfabeticamente en lista propia ".sort()"

#Recorremos para mostrar el producto numerado empezando en 1
for posicion, productos in enumerate(producto, start =1):
    print(f"{posicion}. {productos}.")
