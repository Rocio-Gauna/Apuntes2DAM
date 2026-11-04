#agregamos un contador de totales de ventas
total = 0

for dia in range(1,8):
    ventas = float(input(f"ventas del dia {dia}EUR: "))
    total += ventas

media = ventas/7

print(f" con {ventas:.2f}EUR cada dia, total {total:.2f}y media {media:.2f}. ")