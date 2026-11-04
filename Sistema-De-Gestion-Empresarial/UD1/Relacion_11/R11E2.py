#Crear una lista de cliente [] , con nombre, mail, ciudad, y activo
#Permite alta, listado, busqueda por texto, desactivacion y guardado/carga desde un fichero CSV organiza cada operacion en una funcion distinta. 
#comprobacion : cierra y vuelve a ejecutar el programa: al cargar el CSV deben recuperarse los clientes guardados

#1ero importamos como dice el CSV. 
import csv
from pathlib import Path
#Constante
RUTA_CSV = Path(__file__).with_name("cliente_crm.csv")
CAMPOS = ["nombre","email","ciudad","activo"]

#Funciones Listamos clientes
def listar_clientes(clientes):
    lineas = []
    for cliente in clientes:
        lineas.append(formatear_cliente(cliente))
    return lineas

def formatear_cliente(cliente):
    estado = "activo" if cliente["activo"] else "inactivo"
    return f"{cliente['nombre']} | {cliente['email']} | {cliente['ciudad']} | {estado}"

#Guarda archivo

def guardar_cliente(clientes):
    with open(RUTA_CSV,"w",newline="",encoding= "utf-8") as archivo:
        escritor = csv.DictWriter(archivo,fieldnames=CAMPOS)
        escritor.writeheader()
        escritor.writerows(clientes)
    return len(clientes)

#cargamos le cliente

def cargar_clientes():
    clientes = []
    if RUTA_CSV.exists():
        with open(RUTA_CSV,"r",newline="", encoding="utf-8") as archivo:
            lector = csv.DictReader(archivo)
            for fila in lector:
                clientes.append({
                    "nombre": fila["nombre"],
                    "email": fila["email"],
                    "ciudad": fila["ciudad"],
                    "activo": fila["activo"].strip().lower() == "true",
                    })
    return clientes

def alta_cliente(clientes, nombre, email, ciudad):
    if not nombre or not email or not ciudad:
        return "faltan_datos"
    if any(clientes["email"].casefold()==email.casefold() for cliente in clientes):
        return "duplicado"
    clientes.append({"nombre": nombre, "email": email, "ciudad": ciudad, "activo":True})
    return "anadido"

def buscar_clientes(clientes, texto):
    busqueda = texto.casefold()
    return[
        cliente for cliente in clientes
        if (busqueda in cliente["nombre"].casefold()
            or busqueda in cliente["email"].casefold()
            or busqueda in cliente["ciudad"].casefold())
    ]

def desactivar_cliente(clientes, email):
    for cliente in clientes:
        if cliente["email"].casefold() == email.casefold():
            cliente["activo"] = False
            return True
    return False

def main():
    clientes = cargar_clientes()
    opcion = ""
    while opcion != "0":
        print("\n1) Alta de cliente ")
        print("2) Listar Cliente ")
        print("3) Buscar por texto")
        print("4) Desactivar cliente")
        print("5) Guardar en CSV")
        print("6) Cargar desde CSV")
        print("0) Salir")
        opcion = input("Opción: ").strip()

        if opcion == "1":
            nombre = input("Nombre: ").strip()
            email = input("email: ").strip()
            ciudad = input("Ciudad: ").strip()
            resultado = alta_cliente(clientes, nombre, email, ciudad)
            if resultado == "faltan_datos":
                print("Todos los datos son obligatorios")
            elif resultado == "duplicado":
                print("Ya existe un cliente con ese mail")
            else:
                print("Cliente añadido")
        elif opcion == "2":
            if not clientes:
                print("No hay clientes")
            for linea in listar_clientes(clientes):
                print(linea)
        elif opcion == "3":
            texto = input("texto de busqueda: ").strip()
            encontrados = buscar_clientes(clientes,texto)
            if not encontrados:
                print("No hay clientes")
            for linea in listar_clientes(encontrados):
                print(linea)
        elif opcion == "4":
            email = input("Email del cliente que quieres desactivar: ").strip()
            if desactivar_cliente(clientes, email):
                print("cliente desactivado")
            else:
                print("cliente no encontrado")
        elif opcion == "5":
            cantidad = guardar_cliente(clientes)
            print (f"Guardados {cantidad} clientes.")

        elif opcion == "6":
            clientes = cargar_clientes()
            print(f"Cargados {len(clientes)} clientes.")
        elif opcion != 0:
            print("Opción no válida")

if __name__ == "__main__":
    main()