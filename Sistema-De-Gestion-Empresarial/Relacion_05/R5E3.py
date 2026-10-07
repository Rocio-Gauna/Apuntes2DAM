#Pide el todal del pedido, si el cliente es premium
total_pedido = float(input("Total del pedido :")) 
respuesta = input("¿Cliente premium? (S/N): ").strip().upper()

es_premium = respuesta == "S"

if total_pedido > 500 or (es_premium and total_pedido > 250):
    tipo_pedido = "prioritario"
else:
    tipo_pedido = "normal"

# muestra
print(f"El pedido de {total_pedido:.2f} EUR es {tipo_pedido}")