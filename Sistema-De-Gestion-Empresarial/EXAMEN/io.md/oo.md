persistencia
```
from modelos import Bicicleta, Denuncia

CAMPOS_BICICLETAS = ["codigo", "propietario", "modelo", "valor", "estado"]
CAMPOS_DENUNCIAS = ["codigo", "fecha", "comisaria"]


def cargar_denuncias(ruta):
    """Lee denuncias.csv y devuelve una lista de objetos Denuncia."""
    # Lista donde guardaré los objetos
    denuncias = []
    # Si el archivo no existe, devuelvo la lista vacía
    if not ruta.exists():
        return denuncias
    # Abro en lectura con UTF-8
    with open(ruta, "r", newline="", encoding="utf-8") as archivo:
        lector = csv.DictReader(archivo)
        for fila in lector:
            # Creo un objeto Denuncia con los datos de la fila
            denuncia = Denuncia(
                fila["codigo"].strip().upper(),
                fila["fecha"].strip(),
                fila["comisaria"].strip(),
            )
            denuncias.append(denuncia)
    return denuncias


def guardar_denuncias(ruta, denuncias):
    """Escribe denuncias.csv; devuelve True al guardar."""
    try:
        with open(ruta, "w", newline="", encoding="utf-8") as archivo:
            escritor = csv.DictWriter(archivo, fieldnames=CAMPOS_DENUNCIAS)
            escritor.writeheader()
            for denuncia in denuncias:
                escritor.writerow({
                    "codigo": denuncia.codigo,
                    "fecha": denuncia.fecha,
                    "comisaria": denuncia.comisaria,
                })
    except OSError:
        return False
    return True
```

Dentro de RegistroRobos, un segundo diccionario en __init__ y un método

```
class Denuncia:
    def __init__(self, codigo, fecha, comisaria):
        # El código es el enlace con la bicicleta
        self.codigo = codigo
        self.fecha = fecha
        self.comisaria = comisaria
    def __init__(self):
        # Diccionario de bicicletas: clave = código
        self.bicicletas = {}
        # Diccionario de denuncias: clave = código de la bicicleta denunciada
        self.denuncias = {}

    def registrar_denuncia(self, denuncia):
        """Devuelve True si se añade; False si la bici no existe o ya tiene denuncia."""
        # Filtro 1: la bicicleta tiene que existir (si no, el enlace no vale)
        if denuncia.codigo not in self.bicicletas:
            return False
        # Filtro 2: no puede haber dos denuncias para la misma bici
        if denuncia.codigo in self.denuncias:
            return False
        # Pasó los filtros: guardo la denuncia con el código como clave
        self.denuncias[denuncia.codigo] = denuncia
        return True

    def listar_denuncias(self):
        """Devuelve la lista de objetos Denuncia."""
        return list(self.denuncias.values())
```


main.py

```
from persistencia import (
    cargar_bicicletas, guardar_bicicletas,
    cargar_denuncias, guardar_denuncias,
)

def main():
    # Una ruta por cada archivo
    ruta_bicicletas = Path(__file__).with_name("bicicletas.csv")
    ruta_denuncias = Path(__file__).with_name("denuncias.csv")
    registro = RegistroRobos()

    try:
        # PRIMERO las bicicletas (las denuncias dependen de ellas)
        for bicicleta in cargar_bicicletas(ruta_bicicletas):
            registro.registrar(bicicleta)
        # DESPUÉS las denuncias (ya existen las bicis a las que apuntan)
        for denuncia in cargar_denuncias(ruta_denuncias):
            registro.registrar_denuncia(denuncia)
    except (OSError, ValueError, KeyError) as error:
        print(f"No se ha podido cargar el CSV: {error}")
```

opcion de guardar


```
        elif opcion == "6":
            guardado_bicicletas = guardar_bicicletas(ruta_bicicletas, registro.listar())
            guardado_denuncias = guardar_denuncias(ruta_denuncias, registro.listar_denuncias())
            # "and": las dos condiciones tienen que ser True
            if guardado_bicicletas and guardado_denuncias:
                print("Cambios guardados en los dos CSV.")
            else:
                print("No se han podido guardar los cambios.")
```