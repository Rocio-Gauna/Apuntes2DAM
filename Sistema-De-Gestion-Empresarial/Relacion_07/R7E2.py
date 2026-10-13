precios= [12.50, 30.00, 7.25, 45.90, 18.00]

#Recorremos con un for la lisata y mostramos los precios

for precio in precios: 
    print(f"{precio:.2f}EUR")

#Calculamos los datos con la funciones que reciben la lista entera
cantidad_precios = len(precios) #len() cuenta los elementos que hay 
suma_precios= sum(precios) #Sum() los valores de la lista
precio_minimo = min(precios) #min() devuelve el menor valor
precio_maximo = max(precios) #max() devuelve el mayor valor de la lista
precio_medio = suma_precios / cantidad_precios

#Mostramos los importes
print(f"Cantidad de precios: {cantidad_precios}EUR")
print(f"Suma: {suma_precios:.2f}EUR")
print(f"Minimo: {precio_minimo:.2f}EUR")
print(f"Maximo: {precio_maximo:.2f}EUR")
print(f"Media:{precio_medio:.2f}EUR")