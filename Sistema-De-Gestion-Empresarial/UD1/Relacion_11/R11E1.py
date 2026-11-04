#Mini inventario
#Crea una apliacion de consola con un
#  menu: 1) listar productos. 2) añadir producto,  3) modificar stock, 49 buscar por nombre), 5) guardar en CSV,0) salir
# representa los productos con diccionario dentro de una lista. 
# Divide el programa en funciones. 
# Valida entradas numericas con try/except. 

# funcion de opciones del menu.
import csv
def main():
    productos = [
                    {"nombre": "Teclado", "precio": 25.0, "stock": 10},
                    {"nombre": "raton", "precio": 12.5, "stock": 0}
            ]
    opcion = -1
    while opcion!=0:
        print("0)Salir")
        print("1)Listar Productos")
        print("2)Añadir producto")
        print("3)modificar producto")
        print("4)Buscar por nombre")
        print("5)Guardar en CVS")
        opcion = int(input("Opcion: "))


        if opcion == 1:
            listar_productos(productos)
        elif opcion == 2:
            aniadir_producto(productos)
        elif opcion == 3:
            modificar_stock(productos)
        elif opcion == 4:
            buscar_por_nombre(productos)
        elif opcion ==5:
            guardar_csv(productos)
            print("Productos guardados en productos.csv")
        elif opcion!=0: 
            print("Opcion no valida")
# MAIN DE PRUEBA  main()

def listar_productos(productos):
    for p in productos:
        print(f"{p['nombre']} | {p['precio']} EUR | stock: {p['stock']}")


def aniadir_producto(productos):
    nombre = input("ingrese el nombre de un producto: ")
    precio = float(input("precio del producto: "))
    stock = int(input("Cantidad del producto: "))

    nuevo = {"nombre": nombre, "precio": precio, "stock" : stock} #El diccionario
    productos.append(nuevo)


def modificar_stock(productos):
    nombre_buscado = input("nombre del producto a modificar: ")
    encontrado = False

    for p in productos:
        if p["nombre"].lower() == nombre_buscado.lower():
            nuevo_stock = int(input("Nuevo Stock: "))
            p["stock"] = nuevo_stock
            encontrado = True
    if not encontrado:
        print("Ese producto no existe")

def buscar_por_nombre(producto):
    nombre_buscado = input("Nombre del producto a buscar: ")
    encontrado = False

    for p in producto:
        if nombre_buscado.lower() in p["nombre"].lower():
            print(f"{p['nombre']} | {p['precio']} | {p['stock']}")
            encontrado = True
    if not encontrado:
            print("Ese nombre no se encuentra en la lista de producto")

def guardar_csv(productos):
    columnas = ["nombre", "productos", "stock"]

    with open("producto.csv","w", newline="",encoding="utf-8") as archivo:
        escribir = csv.DictWriter(archivo, fieldnames=columnas)
        escribir.writeheader()
        for p in productos:
            escribir.writerow(p)

main()