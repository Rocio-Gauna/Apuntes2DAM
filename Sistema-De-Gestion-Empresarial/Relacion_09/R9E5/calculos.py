#En este archivo python tiene que haber dos funcions calcular_subtotal(precio,unidad) y aplicar_descuento(importe, porcentaje)

def calcular_subtotal(precio, cantidad):
    subtotal = precio * cantidad
    return subtotal

def aplicar_descuento(importe, porcentaje):
    total = importe - importe * porcentaje /100
    return total