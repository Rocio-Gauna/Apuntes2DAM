#Datos : 4 teclados vendidos a 25.50€ cada uno. 
#Almacenamos el producto "teclado", "precio", "cantidad" en distintas variables y calculamos el subtotal para mostrarlo con dos decimales

producto = "teclado"
precio = 25.50
cantidad = 4
subtotal = precio * cantidad

print(f"Producto: {producto}")
print (f"El subtotal debe ser  {subtotal:.2f} EUR")
