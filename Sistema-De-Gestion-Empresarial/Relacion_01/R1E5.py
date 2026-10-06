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
print(f"Gastos : {gasto_total:.2f}EUR. ,Resultado  : {resto:.2f}EUR ")