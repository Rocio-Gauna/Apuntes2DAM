#Amplía una nueva clase Producto para incluir vender(cantidad) y reponer(cantidad). vender() solo debe reducir el stock si hay unidades suficientes y devolver True; si no, devuelve False. reponer() incrementa el stock.
# Comprobación
# Prueba una venta posible y otra superior al stock disponible.
class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock

    def mostrar(self):
        print(f"Nombre: {self.nombre}")
        print(f"Precio: {self.precio}")
        print(f"Stock: {self.stock}")

    def vender(self, cantidad):
        if cantidad <= self.stock:
            self.stock -= cantidad
            return True
        else:
            return False

    def reponer(self, cantidad):
        self.stock += cantidad
producto1 = Producto("Monitor", 150, 10)
producto1.mostrar()
producto1.vender(3)
producto1.mostrar()