"""UD1 · PT1 · Persistencia CSV. Sin print() ni input()."""
import csv
from modelos import Bicicleta
CAMPOS = ["codigo", "propietario", "modelo", "valor", "estado"]


def cargar_bicicletas(ruta):
    """Lee el CSV y devuelve una lista de objetos Bicicleta.

    Convierte el valor a número y respeta el estado del archivo.
    La primera línea contiene las cabeceras indicadas en CAMPOS.
    """
    # TODO: csv.DictReader, with open(..., encoding="utf-8", newline="")
    bicicletas = [] 
    if ruta.exists():
        with open(ruta,"r", newline="",encoding="utf-8") as archivo:
            lector= csv.DictReader(archivo)
            for fila in lector:
                bicicletas.append({
                     fila["codigo"],
                     fila["propietario"],
                     fila["modelo"],
                     float(fila["valor"]),
                     fila["estado"].strip().lower()== "ROBADA",
                })


    return bicicletas


def guardar_bicicletas(ruta, bicicletas):
    """Escribe el CSV con cabecera y todas las bicicletas; devuelve True al guardar."""
    # TODO: csv.DictWriter, with open(..., encoding="utf-8", newline="")
    try:
        with open(ruta, "w",newline="", encoding="utf-8") as archivo:
             escritor= csv.DictWriter(archivo,fieldnames=CAMPOS)
             escritor.writeheader()
             for bicicleta in bicicletas:
               escritor.writerows({
                   "codigo":bicicleta.codigo,
                   "propietario":bicicleta.propietario,
                   "modelo":bicicleta.modelo,
                   "valor":f"{bicicleta.valor:.2f}",
                   "estado": bicicleta.estado,
            })
    except OSError:
        return False
    return True
