"""UD1 · PT1 · Persistencia CSV. Sin print() ni input()."""
import csv
from modelos import Bicicleta # trae solo bicicleta
CAMPOS = ["codigo", "propietario", "modelo", "valor", "estado"]

#este .py  crea Objetos Bicicleta
def cargar_bicicletas(ruta):
    """Lee el CSV y devuelve una lista de objetos Bicicleta.

    Convierte el valor a número y respeta el estado del archivo.
    La primera línea contiene las cabeceras indicadas en CAMPOS.
    """
    # TODO: csv.DictReader, with open(..., encoding="utf-8", newline="") 
    #Lista donde voy guardando los objetos Bicicleta
    bicicletas = [] 
    
    #Si el archivo no existe, devuelvo la lista vacia sin error
    if ruta.exists():
        return bicicletas

    # Abro el archivo en lectura, con UTF-8; with lo cierra solo al terminar
    with open(ruta, "r", newline="", encoding="utf-8") as archivo:
        # DictReader lee la cabecera y convierte cada línea en un diccionario
        lector = csv.DictReader(archivo)

        # Recorro cada línea del CSV (cada fila es un diccionario de textos)
        for fila in lector:
            # Creo un objeto Bicicleta con los datos de esta fila
            bicicleta = Bicicleta( #.append directo es una lista a lista
                fila["codigo"].strip().upper(),    # .strip() código limpio, quita los espacios, tabuladores y.upper() pasa las letras en mayúsculas
                fila["propietario"].strip(),       # propietario limpio
                fila["modelo"].strip(),            # modelo limpio
                float(fila["valor"]),              # texto "250.0" → número 250.0
                fila["estado"].strip().upper(),    # estado del archivo, en mayúsculas
            )
            # Añado el objeto a la lista
            bicicletas.append(bicicleta)

    # Devuelvo la lista de objetos
    return bicicletas


def guardar_bicicletas(ruta, bicicletas):
    """Escribe el CSV con cabecera y todas las bicicletas; devuelve True al guardar."""
    # TODO: csv.DictWriter, with open(..., encoding="utf-8", newline="")
    try:
        # Abro el archivo en modo escritura "w": lo vacía y lo escribe de nuevo
        with open(ruta, "w",newline="", encoding="utf-8") as archivo:
            
            # DictWriter escribe diccionarios; fieldnames fija las columnas y su orden
            escritor= csv.DictWriter(archivo,fieldnames=CAMPOS)
            
            # Escribe la primera línea: codigo,propietario,modelo,valor,estado
            escritor.writeheader()
            for bicicleta in bicicletas:
                # writerow (singular) escribe UNA fila a partir de UN diccionario
                escritor.writerow({ #con "s" plural recibe muchas filas de una lista de diccionarios
                    "codigo":bicicleta.codigo,  # clave= nombre de la columna
                    "propietario":bicicleta.propietario,
                    "modelo":bicicleta.modelo,
                    "valor":f"{bicicleta.valor:.2f}", # con 2 decimales
                    "estado": bicicleta.estado,
            })
    except OSError:
        #Si fallo algo del sistema con el archivo, aviso con false
        return False
    #Si no hubo error aviso con True
    return True
