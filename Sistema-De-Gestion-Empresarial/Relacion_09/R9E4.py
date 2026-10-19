#Usa el modulo csv para escribir productos.csv con las columnas nombre, precios y stock 
#a partir de una lista de diccionarios. Despues lee el mismo CSV y muestra cada fila. Este ejercicio es local no importes el archivo en Odoo. 
#Comprobacion el csv debe abrirse correctamente es local: no importes el archivo en Odoo. 

#Cuando usamos el modulo csv lo que hacemos es importar 
import csv

#Hacemos la lista de diccionario que pidio  las claves coinciden con las csv 

productos = [ 
    {"nombre": "Teclado", "precio": 25.0, "stock": 10},
    {"nombre": "Raton", "precio": 12.5, "stock": 0},
    {"nombre": "Monitor", "precio": 150.0, "stock": 4},
    {"nombre": "Webcam", "precio": 40.0, "stock": 7}
]

#Nombre de las columnas, en el orden en que apareceran en el fichero
columnas = ["nombre","precio","stock"]

#Para escribir en el csv 

# "w" = escribir;newline="" EVITAR LINEAS EN BLANCO ; utf-8 conserva las tildes correspondiente 

with open("productos.csv", "w", newline="", encoding="utf-8") as archivo:

    #creamos el escritor para indicarle el archivo y las columnas. 
    escritor = csv.DictWriter(archivo, fieldnames=columnas)
    #Escribimos la primera linea del fichero: cabecera nombre, precio, stock
    escritor.writeheader()
    for producto in productos :
        #escribimos una fila con los valores "producto" es un diccionario
        escritor.writerow(producto)

#LEEMOS "r"; abrimos el mismo fichero que acabamos de crear

with open("productos.csv","r",newline="",encoding="utf-8")as archivo:
    #creamos el lector 
    lector = csv.DictReader(archivo)
    #imprime las comunas 
    print("; ".join(lector.fieldnames))
    for fila in lector:
        print (f"{fila['nombre']}; {fila['precio']}; {fila['stock']}")
