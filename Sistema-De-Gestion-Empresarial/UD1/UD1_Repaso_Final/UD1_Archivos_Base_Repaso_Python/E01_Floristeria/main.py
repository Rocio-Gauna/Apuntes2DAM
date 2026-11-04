"""Punto de entrada de la floristería Lila. Toda la interacción con la consola vive aquí."""

from modelos import Floristeria, Producto
from persistencia import (
    cargar_productos,
    cargar_ventas,
    crear_productos_iniciales,
    guardar_productos,
    guardar_ventas,
)

# Nombres de los archivos de datos
RUTA_PRODUCTOS = "productos.csv"
RUTA_VENTAS = "ventas.csv"

# Traduce cada código de error que devuelve la gestora a un mensaje para el usuario
MENSAJES_ERROR = {
    "codigo_vacio": "El código no puede estar vacío.",
    "codigo_repetido": "Ya existe un producto con ese código.",
    "precio_negativo": "El precio no puede ser negativo.",
    "stock_negativo": "El stock no puede ser negativo.",
    "producto_inexistente": "No existe un producto con ese código.",
    "cantidad_invalida": "La cantidad debe ser mayor que cero.",
    "stock_insuficiente": "No hay stock suficiente para esa cantidad.",
    "sin_venta_abierta": "No hay ninguna venta abierta.",
    "venta_ya_abierta": "Ya hay una venta abierta.",
    "cliente_vacio": "El nombre del cliente no puede estar vacío.",
    "venta_vacia": "La venta no tiene líneas; añade alguna o cancélala.",
}


def main():
    # PARTE 1: arranque. Creamos la gestora vacía
    floristeria = Floristeria()
    # Intentamos cargar el catálogo desde el CSV
    productos = cargar_productos(RUTA_PRODUCTOS)
    # Si el archivo no existe, usamos los datos ficticios iniciales
    if productos is None:
        productos = crear_productos_iniciales()
    # Cargamos el catálogo en la gestora
    floristeria.establecer_productos(productos)
    # Cargamos el historial de ventas (necesita el catálogo para reconstruir las líneas)
    ventas = cargar_ventas(RUTA_VENTAS, floristeria.productos_por_codigo)
    floristeria.establecer_ventas(ventas)

    # PARTE 2: bucle del menú. Variable de control que se evalúa en la condición
    opcion = ""
    while opcion != "0":
        # El menú también se imprime aquí, no en una función aparte
        print()
        print("=== Floristería Lila ===")
        print("1) Ver productos")
        print("2) Alta de producto")
        print("3) Reponer stock")
        print("4) Nueva venta / añadir líneas")
        print("5) Cerrar venta")
        print("6) Consultar ventas")
        print("7) Guardar")
        print("8) Cancelar venta actual")
        print("0) Salir")
        opcion = input("Elige una opción: ").strip()

        # PARTE 3: una rama por opción
        if opcion == "1":
            # Obtenemos la lista de productos de la gestora
            lista = floristeria.listar_productos()
            if len(lista) == 0:
                print("No hay productos en el catálogo.")
            else:
                for producto in lista:
                    print(
                        f"{producto.codigo} | {producto.nombre} | {producto.categoria} | "
                        f"{producto.precio:.2f} € | stock: {producto.stock}"
                    )

        elif opcion == "2":
            # Pedimos los datos de texto
            codigo = input("Código: ").strip()
            nombre = input("Nombre: ").strip()
            categoria = input("Categoría: ").strip()
            try:
                # Aceptamos coma decimal (3,50) cambiándola por punto antes de convertir
                precio = float(input("Precio en €: ").replace(",", "."))
                stock = int(input("Stock inicial: "))
            except ValueError:
                print("El precio y el stock deben ser números.")
            else:
                # Solo llegamos aquí si las conversiones salieron bien
                estado = floristeria.anadir_producto(Producto(codigo, nombre, categoria, precio, stock))
                if estado == "ok":
                    print("Producto añadido.")
                else:
                    print(MENSAJES_ERROR[estado])

        elif opcion == "3":
            codigo = input("Código del producto a reponer: ").strip()
            try:
                cantidad = int(input("Unidades a reponer: "))
            except ValueError:
                print("La cantidad debe ser un número entero.")
            else:
                estado = floristeria.reponer_stock(codigo, cantidad)
                if estado == "ok":
                    print("Stock repuesto.")
                else:
                    print(MENSAJES_ERROR[estado])

        elif opcion == "4":
            # Si no hay venta abierta, primero la abrimos pidiendo el cliente
            if floristeria.venta_actual is None:
                cliente = input("Nombre del cliente: ").strip()
                estado = floristeria.abrir_venta(cliente)
                if estado == "ok":
                    print(f"Venta {floristeria.venta_actual.numero} abierta para {cliente}.")
                else:
                    print(MENSAJES_ERROR[estado])
            # Si ya hay venta abierta (o se acaba de abrir), añadimos una línea
            if floristeria.venta_actual is not None:
                codigo = input("Código del producto: ").strip()
                try:
                    cantidad = int(input("Cantidad: "))
                except ValueError:
                    print("La cantidad debe ser un número entero.")
                else:
                    estado = floristeria.anadir_linea(codigo, cantidad)
                    if estado == "ok":
                        print("Línea añadida. Estado de la venta:")
                        print(floristeria.venta_actual.generar_detalle())
                    else:
                        print(MENSAJES_ERROR[estado])

        elif opcion == "5":
            # Guardamos la referencia a la venta antes de cerrarla
            venta = floristeria.venta_actual
            estado = floristeria.cerrar_venta()
            if estado == "ok":
                print("Venta cerrada:")
                print(venta.generar_detalle())
            else:
                print(MENSAJES_ERROR[estado])

        elif opcion == "6":
            historial = floristeria.ventas_cerradas
            if len(historial) == 0:
                print("Todavía no hay ventas cerradas.")
            else:
                for venta in historial:
                    print(venta.generar_detalle())
                    print()
                print(f"Importe acumulado: {floristeria.calcular_importe_acumulado():.2f} €")

        elif opcion == "7":
            # No guardamos con una venta abierta: su stock ya está descontado pero no está en el historial
            if floristeria.venta_actual is not None:
                print("Cierra o cancela la venta actual antes de guardar.")
            else:
                guardado_productos = guardar_productos(RUTA_PRODUCTOS, floristeria.listar_productos())
                guardado_ventas = guardar_ventas(RUTA_VENTAS, floristeria.ventas_cerradas)
                if guardado_productos and guardado_ventas:
                    print("Datos guardados.")
                else:
                    print("No se pudieron guardar los datos (¿archivo abierto en otro programa?).")

        elif opcion == "8":
            estado = floristeria.cancelar_venta()
            if estado == "ok":
                print("Venta cancelada y stock devuelto.")
            else:
                print(MENSAJES_ERROR[estado])

        elif opcion != "0":
            # Cualquier otra cosa que no sea la salida es una opción inválida
            print("Opción no válida.")

    print("Hasta pronto. Recuerda que lo no guardado se pierde.")


if __name__ == "__main__":
    main()