#Crea Empleado con nombre y salario. 
#Crea Comercial(Empleado) con un atributo adicional ventas
#Añade Calcular_bonus() que devuelva el 5% de ventas y un metodo resumen() que incluya salario y bonus.
#Usa super(). __init__() para finalizar la parte heredada

class Empleado:
    def __init__(self, nombre, salario):
        self.nombre = nombre
        self.salario = salario

class Comercial(Empleado):
    def __init__(self, nombre,salario, ventas):
        #Hereda los atributos de la clase principal en este caso tenemos como herencia el nombre y salario por eso usamos el super().__init__()
        super().__init__(nombre,salario)
        self.ventas =ventas

    def calcular_bonus(self):
        return self.ventas *0.05

    def resumen(self):
        return f"{self.nombre}| {self.salario} |Bonus{ self.calcular_bonus()}"        