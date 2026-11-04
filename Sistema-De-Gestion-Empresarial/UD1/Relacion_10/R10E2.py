#Amplía una nueva clase Producto para incluir vender(cantidad) y reponer(cantidad). vender() solo debe reducir el stock si hay unidades suficientes y devolver True; si no, devuelve False. reponer() incrementa el stock.
# Comprobación
# Prueba una venta posible y otra superior al stock disponible.
class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock


    def vender(self, cantidad):
        if cantidad <= 0 or cantidad > self.stock:
            self.stock -= cantidad
            return True
        else:
            return False

    def reponer(self, cantidad):
        if cantidad > 0:
            self.stock += cantidad

producto = Producto("Monitor", 150, 10)
print(f"Stock inicial: {producto.stock}")
print(f"Ventas de 2 unidades: {producto.vender(2)}")
print(f"Venta de 10 unidades: {producto.Vender(10)}")
producto.reponer(4)
print(f"Stock despues de reponer 4 unidades: {producto.stock}")