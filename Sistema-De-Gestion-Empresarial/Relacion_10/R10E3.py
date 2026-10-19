# creamos una clase cliente con nombre, mail, ciudad, y añadimos cambiar_ciudad(nueva_ciudad) y ficha(),donde ficha() devuelva una cadena con los datos del cliente. no imprimas directamente dentro de ficha()


class Cliente:
    #atributos
    def __init__(self, nombre, email, ciudad):
        self.nombre = nombre
        self.email = email
        self.ciudad = ciudad
    #acciones , funcionalidad
    def cambiar_ciudad(self, nueva_ciudad):
        self.ciudad = nueva_ciudad
    #la ficha si muestra con un return
    def ficha(self):
            return f"{self.nombre} | {self.email} | {self.ciudad}"

#Pruebas
cliente = Cliente ("Lucia","LUCIA@CORREO.COM","Granada")
# como el enunciado me dice que no imprimamos el texto dentro de ficha el siguiente paso es hacer esto. los print fuera de ficha ya que lo que hace es mostrar. 
print(cliente.ficha())
cliente.cambiar_ciudad("sevilla")
print(cliente.ficha())