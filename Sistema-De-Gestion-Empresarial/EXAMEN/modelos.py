"""UD1 · PT1 · Clases del sistema BiciAlerta.

No escribas print() ni input() en las clases ni en sus métodos.
"""


class Bicicleta:
    def __init__(self, codigo, propietario, modelo, valor, estado="ROBADA"):
        # Guardo cada dato recibido dentro del objeto (self = "este objeto")
        self.codigo = codigo
        self.propietario = propietario
        self.modelo = modelo
        self.valor = valor
        # Si no me indican estado, las altas empiezan en ROBADA
        self.estado = estado


class RegistroRobos:
    def __init__(self):
        # El código de la bicicleta será la clave del diccionario.
        # Forma: {"RB01": objeto Bicicleta, "RB02": objeto Bicicleta, ...}
        self.bicicletas = {}

    def registrar(self, bicicleta):
        """Devuelve True si se añade; False si el código está repetido."""
        # Pregunto si el código ya es una clave del diccionario
        if bicicleta.codigo in self.bicicletas:
            # Está repetido: salgo con False sin tocar el diccionario
            return False
        # El casillero está libre: guardo la bici con su código como clave
        self.bicicletas[bicicleta.codigo] = bicicleta
        # Aviso de que se añadió correctamente
        return True

    def listar(self):
        """Devuelve la lista completa de objetos Bicicleta."""
        # values() da los objetos del diccionario; list() los pasa a lista normal
        return list(self.bicicletas.values())

    def recuperar(self, codigo):
        """Cambia ROBADA a RECUPERADA. Devuelve True si cambia algo."""
        # get devuelve la bici si existe, o None si el código no existe
        bicicleta = self.bicicletas.get(codigo)
        # Primer filtro: si no existe, no hay nada que recuperar
        # (se comprueba antes de mirar .estado, porque None.estado daría error)
        if bicicleta is None:
            return False
        # Segundo filtro: si ya no está ROBADA, no se puede recuperar otra vez
        if bicicleta.estado != "ROBADA":
            return False
        # Pasó los dos filtros: cambio el estado en la propia bici del diccionario
        bicicleta.estado = "RECUPERADA"
        # Aviso de que se cambió algo
        return True

    def buscar(self, texto):
        """Busca por fragmento de propietario o modelo, sin importar mayúsculas."""
        # casefold pasa el texto a minúsculas para no distinguir mayúsculas
        busqueda = texto.casefold()
        # Comprensión de lista: construye una lista con las bicis que cumplen la condición
        return [
            bicicleta for bicicleta in self.bicicletas.values()
            # "in" pregunta si el fragmento está dentro del texto
            if busqueda in bicicleta.propietario.casefold()
            or busqueda in bicicleta.modelo.casefold()
        ]

    def resumen(self):
        """Devuelve un diccionario con total, robadas, recuperadas y valor_pendiente."""
        # Total: cuántos casilleros hay en el diccionario
        total = len(self.bicicletas)
        # Acumuladores: empiezan en cero FUERA del bucle para conservar lo sumado
        robadas = 0
        valor_pendiente = 0.0
        # Recorro cada objeto Bicicleta del registro
        for bicicleta in self.bicicletas.values():
            # Solo cuento las que siguen robadas
            if bicicleta.estado == "ROBADA":
                # Una robada más
                robadas += 1
                # Sumo su valor al dinero pendiente
                valor_pendiente += bicicleta.valor
        # Las recuperadas son las que no están robadas
        recuperadas = total - robadas
        # Devuelvo un diccionario con las claves exactas que lee main.py
        return {
            "total": total,
            "robadas": robadas,
            "recuperadas": recuperadas,
            "valor_pendiente": valor_pendiente,
        }