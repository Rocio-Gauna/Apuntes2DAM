"""Clases de dominio: impleméntalas siguiendo el enunciado."""

DESCUENTO = 50.0

PORCENTAJE_DESCUENTO = 10
# TODO: define las clases necesarias aquí.
class Producto:
    def __init__(self, codigo, nombre, categoria, precio, stock):
        self.codigo = codigo
        self.nombre = nombre
        self.categoria = categoria
        self.precio = precio
        self.stock = stock

class lineaVenta:
    def __init__(self, codigo_producto, cantidad, precio_unitario):
        self.codigo_producto = codigo_producto
        self.cantidad = cantidad
        self.precio_unitario = precio_unitario

    def calcular_importe(self):
        return self.cantidad * self.precio_unitario
    

class Venta:
    def __init__(self,numero, cliente):
        self.numero = numero
        self.cliente =cliente
        #listamos de objetivod LineaVenta: empieza vacia
        self.lineas =[]
        #indicador di la venta esta cerrada o no
        self.cerrada = False

    def agregar_linea(self, linea):
        self.lineas.append(linea)

    def cerrar(self):
        self.cerrada = True

    def calcular_subtotal(self):
        subtotal = 0.0
        for linea in self.lineas: 
            subtotal = subtotal + linea.calcular_importe()
        return round(subtotal, 2)  
    
    def calcular_decuento (delf):
        subtotal = self.calcular_subtotal()

        if subtotal >= DESCUENTO:

            return round(subtotal * PORCENTAJE_DESCUENTO / 100, 2)
        return 0.0

    def calcular_total(self):
        return round(self.calcular_subtotal() - self.calcular_decuento(), 2)

    def generar_detalle(self):

        textos=[]

        textos.append(f"Venta {self.numero} - Cliente: {self.cliente}")

        for linea in self.lineas:
            textos.append(
            f"  {linea.producto.nombre} x{linea.cantidad} a "
            f"{linea.precio_unitario:.2f} € = {linea.calcular_importe():.2f} €"
            )

        textos.append(f"Subtotal: {self.calcular_subtotal():.2f} €")
        descuento = self.calcular_decuento()
        if descuento > 0:
            textos.append(f"Descuento ({PORCENTAJE_DESCUENTO}%):-{descuento:.2f} €")
        #TOTAL FINAL 
        textos.append(f"  Total: {self.calcular_total():.2f} €")
        # Unimos todas las líneas en un solo texto separado por saltos de línea
        return "\n".join(textos)

class Floristeria:
    """Gestora: catálogo, venta actual e historial. Aquí viven las reglas de negocio."""

    def __init__(self):
        # Diccionario del catálogo: clave = código, valor = Producto
        self.productos_por_codigo = {}
        # Historial de ventas cerradas (lista de Venta)
        self.ventas_cerradas = []
        # Venta que se está preparando ahora (None si no hay ninguna)
        self.venta_actual = None
        # Número que tendrá la próxima venta que se abra
        self.siguiente_numero = 1

    def establecer_productos(self, lista_productos):
        # Partimos de un catálogo vacío
        self.productos_por_codigo = {}
        # Metemos cada producto en el diccionario usando su código como clave
        for producto in lista_productos:
            self.productos_por_codigo[producto.codigo] = producto

    def establecer_ventas(self, lista_ventas):
        # Guardamos el historial recibido
        self.ventas_cerradas = lista_ventas
        # Buscamos el mayor número de venta usado hasta ahora
        mayor_numero = 0
        for venta in lista_ventas:
            if venta.numero > mayor_numero:
                mayor_numero = venta.numero
        # La próxima venta será la siguiente a la mayor, para no repetir números
        self.siguiente_numero = mayor_numero + 1

    def listar_productos(self):
        # Devuelve una lista con todos los productos del catálogo
        return list(self.productos_por_codigo.values())

    def anadir_producto(self, producto):
        # Validamos primero; solo modificamos si todo es correcto
        if producto.codigo == "":
            return "codigo_vacio"
        if producto.codigo in self.productos_por_codigo:
            return "codigo_repetido"
        if producto.precio < 0:
            return "precio_negativo"
        if producto.stock < 0:
            return "stock_negativo"
        # Todo correcto: lo añadimos al catálogo
        self.productos_por_codigo[producto.codigo] = producto
        return "ok"

    def reponer_stock(self, codigo, cantidad):
        # El producto debe existir
        if codigo not in self.productos_por_codigo:
            return "producto_inexistente"
        # Reponer cero o una cantidad negativa no tiene sentido
        if cantidad <= 0:
            return "cantidad_invalida"
        # Sumamos las unidades repuestas al stock
        self.productos_por_codigo[codigo].stock += cantidad
        return "ok"

    def abrir_venta(self, cliente):
        # No se puede abrir una venta si ya hay una en marcha
        if self.venta_actual is not None:
            return "venta_ya_abierta"
        # El cliente no puede estar vacío
        if cliente == "":
            return "cliente_vacio"
        # Creamos la venta con el siguiente número disponible
        self.venta_actual = Venta(self.siguiente_numero, cliente)
        # Avanzamos el contador para la próxima venta
        self.siguiente_numero += 1
        return "ok"

    def anadir_linea(self, codigo, cantidad):
        # Validamos TODO antes de tocar el stock
        if self.venta_actual is None:
            return "sin_venta_abierta"
        if codigo not in self.productos_por_codigo:
            return "producto_inexistente"
        if cantidad <= 0:
            return "cantidad_invalida"
        # Recuperamos el producto del catálogo
        producto = self.productos_por_codigo[codigo]
        # No se puede vender más de lo que hay
        if cantidad > producto.stock:
            return "stock_insuficiente"
        # La línea guarda el precio del momento de la venta
        linea = LineaVenta(producto, cantidad, producto.precio)
        # Añadimos la línea a la venta actual
        self.venta_actual.agregar_linea(linea)
        # Descontamos el stock una sola vez, solo ahora que todo es válido
        producto.stock -= cantidad
        return "ok"

    def cerrar_venta(self):
        # Tiene que haber una venta abierta
        if self.venta_actual is None:
            return "sin_venta_abierta"
        # No se cierra una venta sin ninguna línea
        if len(self.venta_actual.lineas) == 0:
            return "venta_vacia"
        # Marcamos la venta como cerrada
        self.venta_actual.cerrar()
        # La añadimos al historial
        self.ventas_cerradas.append(self.venta_actual)
        # Ya no hay venta en marcha
        self.venta_actual = None
        return "ok"

    def cancelar_venta(self):
        # Ampliación voluntaria: cancela la venta actual devolviendo el stock
        if self.venta_actual is None:
            return "sin_venta_abierta"
        # Devolvemos al stock las unidades de cada línea
        for linea in self.venta_actual.lineas:
            linea.producto.stock += linea.cantidad
        # Descartamos la venta; las ventas ya cerradas no se tocan
        self.venta_actual = None
        return "ok"

    def calcular_importe_acumulado(self):
        # Acumulador del importe de todas las ventas cerradas
        acumulado = 0.0
        # Sumamos el total de cada venta del historial
        for venta in self.ventas_cerradas:
            acumulado = acumulado + venta.calcular_total()
        return round(acumulado, 2)