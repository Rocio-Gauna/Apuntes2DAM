#Una empresa ingresa 1400EUR por servicio
#paga 425EUR en materiales 
#360eur en otros gastos. 
#Alcemamos los 3 valores 
#calculamos 
#Gasto total, resultado de la operacion 
#Imprimimos el informe

ingreso_Empresa = 1400
materiales = 425 
otros= 360

gasto_total = materiales + otros
resto = ingreso_Empresa - gasto_total

print(f"==== Informe de la Empresa ====")
print(f"Ingresos de la empresa: {ingreso_Empresa:.2f}EUR")
print(f"Gastos en materiales : {materiales:.2f}EUR")
print(f"Otros Gastos:{otros:.2f}EUR")
print(f"Gastos total de la Empresa: {gasto_total:.2f}EUR")
print(f"Resultado restante : {resto:.2f}EUR")