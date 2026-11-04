class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    #El constructor
#recibirá los tres valores. Añade un método mostrar() que presente una ficha del producto

    def mostrar(self):
        print(f"Nombre: {self.nombre}")
        print(f"Precio: {self.precio}")
        print(f"Stock: {self.stock}")   

# Comprobación
# Crea al menos dos objetos Producto y llama a mostrar() en ambos.
producto1 = Producto("MONITOR", 10.0, 100)
producto2 = Producto("RATON", 20.0, 50)
producto1.mostrar()
