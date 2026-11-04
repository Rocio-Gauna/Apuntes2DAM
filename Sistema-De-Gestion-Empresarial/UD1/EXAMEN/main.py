"""UD1 · PT1 · BiciAlerta. Menú y validaciones de entrada preparados."""
import csv
from pathlib import Path

from modelos import Bicicleta, RegistroRobos
from persistencia import cargar_bicicletas, guardar_bicicletas


def main():
    ruta = Path(__file__).with_name("bicicletas.csv")
    registro = RegistroRobos()

    # Carga inicial. Debes implementar la lectura y el registro de las bicicletas.
    try:
        for bicicleta in cargar_bicicletas(ruta):
            registro.registrar(bicicleta)
    except (OSError, ValueError, KeyError) as error:
        print(f"No se ha podido cargar el CSV: {error}")

    opcion = ""
    while opcion != "0":
        print("\n========== BICIALERTA · REGISTRO DE ROBOS ==========")
        print("1. Listar bicicletas")
        print("2. Registrar un robo")
        print("3. Marcar bicicleta como recuperada")
        print("4. Buscar por propietario o modelo")
        print("5. Mostrar resumen")
        print("6. Guardar en CSV")
        print("0. Salir")
        opcion = input("Elige una opción: ").strip()

        if opcion == "1":
            bicicletas = registro.listar()
            if not bicicletas:
                print("No hay bicicletas registradas.")
            else:
                for bici in bicicletas:
                    print(
                        f"{bici.codigo} | {bici.propietario} | {bici.modelo} | "
                        f"{bici.valor:.2f} € | {bici.estado}"
                    )

        elif opcion == "2":
            codigo = input("Código: ").strip().upper()
            propietario = input("Propietario: ").strip()
            modelo = input("Modelo: ").strip()
            importe = input("Valor (€): ").strip().replace(",", ".")

            if not codigo or not propietario or not modelo:
                print("Error: código, propietario y modelo no pueden estar vacíos.")
            else:
                try:
                    valor = float(importe)
                except ValueError:
                    print("Error: introduce un valor numérico.")
                else:
                    if not 0 < valor < float("inf"):
                        print("Error: el valor debe ser positivo y finito.")
                    else:
                        bicicleta = Bicicleta(codigo, propietario, modelo, valor)
                        if registro.registrar(bicicleta):
                            print("Robo registrado correctamente.")
                        else:
                            print("Error: ya existe una bicicleta con ese código.")

        elif opcion == "3":
            codigo = input("Código a recuperar: ").strip().upper()
            if not codigo:
                print("Error: el código no puede estar vacío.")
            elif registro.recuperar(codigo):
                print("Bicicleta marcada como RECUPERADA.")
            else:
                print("No se puede recuperar: código inexistente o ya recuperado.")

        elif opcion == "4":
            texto = input("Texto a buscar: ").strip()
            if not texto:
                print("Error: el texto de búsqueda no puede estar vacío.")
            else:
                resultados = registro.buscar(texto)
                if not resultados:
                    print("No se encontraron coincidencias.")
                else:
                    for bici in resultados:
                        print(f"{bici.codigo} | {bici.propietario} | {bici.modelo} | {bici.estado}")

        elif opcion == "5":
            datos = registro.resumen()
            print(f"Total de bicicletas: {datos['total']}")
            print(f"Robadas: {datos['robadas']}")
            print(f"Recuperadas: {datos['recuperadas']}")
            print(f"Valor pendiente: {datos['valor_pendiente']:.2f} €")

        elif opcion == "6":
            try:
                guardado = guardar_bicicletas(ruta, registro.listar())
                if guardado:
                    print("Cambios guardados en bicicletas.csv.")
                else:
                    print("No se han podido guardar los cambios.")
            except (OSError, ValueError) as error:
                print(f"Error al guardar el CSV: {error}")

        elif opcion == "0":
            print("Fin del programa.")
        else:
            print("Opción no válida. Elige un número entre 0 y 6.")


if __name__ == "__main__":
    main()
