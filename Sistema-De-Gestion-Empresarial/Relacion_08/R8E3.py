# crear aplicar_descuento(importe, porcentaje = 0) que devuelva el importe final. llama una vez a la funcion sin indicar porcentaje y otra con porcentaje = 10. 
importe = float(input("Ingrese un importe: " ))


def aplicar_descuento(importe, porcentaje=0):
    descuento = importe * porcentaje / 100
    return descuento

total_descuento = importe - aplicar_descuento(importe, porcentaje=10)

print(f"Para: {importe:.2f} EUR: sin descuento {importe:.2f}; con 10%, {total_descuento:.2f}  ")