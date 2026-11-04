#Construye un programa con, como minimo. las funciones pedir_datos(), calcular_total(). mostrar_resumen() y main().El usuario introduce cliente, precio, cantidad y descuento. cada funcion debe tener una repsosabilidad clara. 


#Datos que aportar a un presupuesto. 
#nombre del cliente, cantidad, precio, descuento si lo hay,subtotal( cantidad x precio), total pagado( completo con el descuento).

#con la funcion pedir datos solo pedimos los datos de un presupuesto lo guardamos en un diccionario y mostramos el diccionario en un return que es lo que nos devuelve 
def pedir_datos():
    nombre= input("Nombre del cliente: ")
    producto = input("Producto: ")
    precio = float(input("Precio: "))
    cantidad = int(input("Cantidad:"))
    descuento = float(input("Descuento %: "))

    datos ={
    "cliente": nombre,
    "producto" : producto,
    "precio": precio,
    "cantidad": cantidad,
    "descuento": descuento
    }

    return datos


#En la funcion de calcular_total() vamos a hacer todas las cuentas necesarias que necesitas en esta funcion
# por ende le llegan los parametros de precio, cantidad, descuento. y lo calculamos pero en el return llamamos al totoal ya que es nombre de la funcion. 

def calcular_total(precio, cantidad, descuento):
    subtotal = precio * cantidad
    descuento_aplicado = subtotal * descuento / 100
    total = subtotal - descuento_aplicado
    return total

#Solo nos va a mostrar el resultado por pantalla aqui deberian ir los print. 
def mostrar_resumen(datos, total):
    print(f"\n==== RESUMEN ====")
    print(f"Cliente: {datos['cliente']}")
    print(f"Producto: {datos['producto']}")
    print(f"Pecio Unitario: {datos['precio']:.2f} EUR")
    print(f"Cantidad: {datos['cantidad']}")
    print(f"Descuento: {datos['descuento']:.2f} %")
    print(f"total: {total} EUR")


#MAIN 
def main():
    #pide datos 
    datos = pedir_datos()
    #pide total
    total = calcular_total(datos["precio"], datos["cantidad"], datos["descuento"])
    mostrar_resumen(datos, total)
 

#Si el archivo se ejecuta directamente se escribe de esta manera ( o sea que no se importa desde otro lugar ) 
if __name__ == "__main__":
    main()



