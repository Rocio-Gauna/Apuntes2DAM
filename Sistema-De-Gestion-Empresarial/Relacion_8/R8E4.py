# Crea resumir_importes(importes) que reciba una lista de números y devuelva un
#diccionario con total, media, minimo y maximo. Si la lista está vacía, devuelve
#None. El programa principal debe comprobar ese resultado.
#Comprobación
#No pidas input dentro de resumir_importes(). La función trabaja únicamente
#con sus parámetros

# ==== Funcion ====

def resumir_importe (importes):
    #Si la lista esta vacia: no imprime datos, no resume nada
    if len(importes) == 0:
        return None

#Por fuera de la funcion hacemos la suma, la media, el minimo y el maximo

    total = sum(importes)
    media = total/len(importes) #la media se calcula con el total partido a la cantidad. len cuenta la cantidad 
    minimo = min(importes)
    maximo = max(importes)

#El diccionario se construye de la siguiente manera

    resumen = {
    "total" : total,
    "media" : media,
    "minimo" : minimo,
    "maximo" : maximo
}
# devolvemos mediante un return el diccionario completo
    return resumen 


# ==== Programa principal ====
    #Declaramos los datos de la lista y declaramos una lista vacia
lista_con_datos = [10.0, 20.0, 30.0, 40.0]
lista_vacia = []

#Siempre vamos a recorrer una lista con un FOR. 
for lista_de_importes in [lista_con_datos,lista_vacia]:
    resultado = resumir_importe(lista_de_importes)

    if resultado is None:
        print("No hay importes para resumir")
    else:
        print(f"Total: {resultado['Total']:.2f} EUR")
        print(f"Media: {resultado['media']:.2f} EUR")
        print(f"Minimo: {resultado['minimo']:.2f} EUR")
        print(f"Maximo: {resultado['maximo']:.2f} EUR")

