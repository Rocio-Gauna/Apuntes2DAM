#Este main creado es para importar las funciones de claculos.py. 
#destinado a pedir datos y mostrar. 

#importamos 
from calculos import calcular_subtotal, aplicar_descuento

def main():
    precio= float(input("precio unitario: "))
    cantidad = int(input("Cantidad: "))
    descuento = int(input("Descuento del %: "))

    subtotal =calcular_subtotal(precio,cantidad)
    total = aplicar_descuento(subtotal,descuento)

    print(f"Subtotal: {subtotal:.2f} EUR")
    print(f"Descuento: {descuento:.2f}%")
    print(f"Total:{total:.2f} EUR")

    if __name__ == "__main__":
        main()