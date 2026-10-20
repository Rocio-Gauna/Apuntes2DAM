"""Lectura/escritura de CSV. Sin print() ni input()."""

# TODO: define funciones de carga y guardado que devuelvan resultados.
"""Lectura/escritura de CSV. Sin print() ni input()."""

# Módulo estándar para leer y escribir CSV
import csv
# Pathlib permite comprobar si un archivo existe
from pathlib import Path

from modelos import Producto, LineaVenta, Venta

# Columnas del CSV de productos, en orden
CAMPOS_PRODUCTOS = ["codigo", "nombre", "categoria", "precio", "stock"]
# Columnas del CSV de ventas: una fila por cada línea de venta
CAMPOS_VENTAS = ["numero", "cliente", "codigo_producto", "cantidad", "precio_unitario"]


def crear_productos_iniciales():
    # Catálogo ficticio de arranque, para cuando no existe productos.csv
    return [
        Producto("FLO01", "Rosas", "Flor", 3.50, 15),
        Producto("FLO02", "Tulipanes", "Flor", 2.25, 24),
        Producto("FLO03", "Orquídeas", "Flor", 18.00, 4),
    ]


def cargar_productos(ruta):
    # Convertimos la ruta en un objeto Path
    ruta_archivo = Path(ruta)
    # Si el archivo no existe devolvemos None; main decidirá qué hacer
    if not ruta_archivo.exists():
        return None
    # Lista donde guardaremos los productos leídos
    productos = []
    # Abrimos con utf-8 (para tildes) y newline="" como pide el enunciado
    with open(ruta_archivo, "r", encoding="utf-8", newline="") as archivo:
        # DictReader da cada fila como diccionario usando la cabecera
        lector = csv.DictReader(archivo)
        for fila in lector:
            try:
                # El CSV solo guarda texto: convertimos precio y stock a número
                producto = Producto(
                    fila["codigo"],
                    fila["nombre"],
                    fila["categoria"],
                    float(fila["precio"]),
                    int(fila["stock"]),
                )
            except (ValueError, TypeError):
                # Fila con datos inválidos: la ignoramos y seguimos con la siguiente
                continue
            productos.append(producto)
    return productos


def guardar_productos(ruta, lista_productos):
    try:
        # "w" sobrescribe el archivo con el catálogo actual
        with open(ruta, "w", encoding="utf-8", newline="") as archivo:
            escritor = csv.DictWriter(archivo, fieldnames=CAMPOS_PRODUCTOS)
            # Escribimos la cabecera
            escritor.writeheader()
            # Una fila por producto
            for producto in lista_productos:
                escritor.writerow({
                    "codigo": producto.codigo,
                    "nombre": producto.nombre,
                    "categoria": producto.categoria,
                    "precio": f"{producto.precio:.2f}",
                    "stock": producto.stock,
                })
    except OSError:
        # Si no se pudo escribir (archivo bloqueado, sin permisos...) devolvemos False
        return False
    return True


def cargar_ventas(ruta, productos_por_codigo):
    # Si el archivo no existe, no hay historial
    ruta_archivo = Path(ruta)
    if not ruta_archivo.exists():
        return []
    # Lista de ventas en el orden en que aparecen en el archivo
    ventas = []
    # Diccionario auxiliar: número de venta -> objeto Venta (para agrupar sus filas)
    ventas_por_numero = {}
    with open(ruta_archivo, "r", encoding="utf-8", newline="") as archivo:
        lector = csv.DictReader(archivo)
        for fila in lector:
            try:
                # Convertimos los textos a los tipos necesarios
                numero = int(fila["numero"])
                cantidad = int(fila["cantidad"])
                precio_unitario = float(fila["precio_unitario"])
            except (ValueError, TypeError):
                # Fila inválida: se ignora
                continue
            # Código del producto de esta línea
            codigo = fila["codigo_producto"]
            # Si el producto ya no está en el catálogo, no podemos reconstruir la línea
            if codigo not in productos_por_codigo:
                continue
            # Si es la primera fila de esta venta, creamos la Venta
            if numero not in ventas_por_numero:
                venta = Venta(numero, fila["cliente"])
                # Todas las ventas guardadas están cerradas
                venta.cerrar()
                ventas_por_numero[numero] = venta
                ventas.append(venta)
            # Añadimos la línea a su venta, con el precio que tenía entonces
            linea = LineaVenta(productos_por_codigo[codigo], cantidad, precio_unitario)
            ventas_por_numero[numero].agregar_linea(linea)
    return ventas


def guardar_ventas(ruta, ventas_cerradas):
    try:
        with open(ruta, "w", encoding="utf-8", newline="") as archivo:
            escritor = csv.DictWriter(archivo, fieldnames=CAMPOS_VENTAS)
            escritor.writeheader()
            # Recorremos cada venta cerrada
            for venta in ventas_cerradas:
                # Y escribimos una fila por cada línea de esa venta
                for linea in venta.lineas:
                    escritor.writerow({
                        "numero": venta.numero,
                        "cliente": venta.cliente,
                        "codigo_producto": linea.producto.codigo,
                        "cantidad": linea.cantidad,
                        "precio_unitario": f"{linea.precio_unitario:.2f}",
                    })
    except OSError:
        return False
    return True