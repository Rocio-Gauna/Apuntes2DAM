#Pide un numero hasta que el usuario introduzca un numero mayor o igual a 0, Usa try/Except valueerror y una condicion explicaicta para controlar el bucle. no uses while true ni break

# bandera: "por que no hay un precio"
dato_valido = False

# se repite mientras no tengamos un dato valido 
while not dato_valido:
    try:
        # Pedimos el precio falla con value Error si el texto no es un numero valido
        precio = float(input("Introduzca un numero: "))
        #L acomparacion da True o False y se guarda en la bandera 
        dato_valido = precio >=0
        # si el numero se convirtio bien pero es negativo se vuelve a preguntar. 
        if not dato_valido:
            print("El numero no puede ser negativo")
    except ValueError:
        print("Introduce un numero valido")

print(f"Precio registrado : {precio:.2f} EUR")

