# Pide destino y total del pedido
destino = input("Destino (PENINSULA, BALEARES o CANARIAS): ").strip().upper()
total_pedido = float(input("Total del pedido (EUR): "))

destino_valido = True

match destino:
    case "PENINSULA":
        if total_pedido >= 100:
            gastos_envio = 0
        else:
            gastos_envio = 6
    case "BALEARES":
        gastos_envio = 12
    case "CANARIAS":
        gastos_envio = 18
    case _:
        destino_valido = False
        gastos_envio = 0

# Solo se calcula el total final si el destino es válido
if destino_valido:
    total_final = total_pedido + gastos_envio
    print(f"Destino: {destino}, Envio: {gastos_envio:.2f} EUR, Total final: {total_final:.2f} EUR")
else:
    print("Destino no valido. No se calcula el total final.")