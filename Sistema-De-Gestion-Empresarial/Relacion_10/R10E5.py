#Crea una clase LineaPedido con producto y cantidad
#Un metodo subtotal(). 
#crea una clase pedido que guarde cliente y una lista de lineas Añade agregar_linea(linea) y total() para sumar los subtotales. 

class LineaPedido:
    def __init__(self,producto, precio, cantidad):
        self.producto = producto
        self.precio = precio
        self.cantidad = cantidad
    def subtotal(self):
        return self.precio * self.cantidad

class Pedido:
    def __init__(self, cliente):
        self.cliente = cliente
        self.lineas = []  # lista vacia, creada dentro de __init__

    def agregar_linea(self, linea):
        self.lineas.append(linea)#mete un objeto LineaPedido en la lista

    def total(self):
        suma = 0 # para sumar tiene que existir suma con un valor inicial en este caso es 0
        for linea in self.lineas:
            suma = suma + linea.subtotal()

        return suma

#comprobacion

pedido = Pedido("Lucía")
pedido.agregar_linea(LineaPedido("Teclado", 25.0, 2))   # 50.0
pedido.agregar_linea(LineaPedido("Ratón", 12.5, 1))     # 12.5
pedido.agregar_linea(LineaPedido("Monitor", 150.0, 1))  # 150.0

print(pedido.total())   # 212.5

#lineas = ["A", "B"]
#
#lineas.append("C")            # añade UN elemento al final      -> ["A", "B", "C"]
#lineas.insert(0, "Z")         # añade en una posición concreta  -> ["Z", "A", "B", "C"]
#lineas.extend(["D", "E"])     # añade VARIOS elementos al final -> [..., "D", "E"]
#lineas = lineas + ["F"]       # concatenar crea una lista nueva
#lineas += ["G"]               # equivale a extend