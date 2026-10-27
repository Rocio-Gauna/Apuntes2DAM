# Resumen de la Unidad 1

El codigo del funcionamiento de pruebas dentro de MainActivity

``` kotlin
//val es una variable cuyo valor no se puede reasigar (=constante)
// var es lo contrario (= variable, cambia) 
// " : " son para el tipo de dato Int o String 
//  " = " le asigna el valor a ese val o var. 
// para llamar a esa variable usamos el "$" luego el nombre asignado de la variable como en el siguiente ejemplo. 

val nombre: String = "Ana"
var intentos : int = 1

// se puede llamar aesa variable como 
intentos = intentos + 1
val saludos = "Hola, $nombre. Intento numero $intentos" 
```

----

## Funciones

"{}" delimitan un bloque de instrucciones.

"()" contienen los datos de entrada de una funcion o una condicion.

"." permite acceder a una propiedad o a una funcion de un valor Ej: `nombre.length` la longitud de un texto.

"fun" declaracion de `funcion`

```Kotlin
//Ejemplo de funciones declarado lo explicado arriba

fun double(numero:Int):Int{
    return numero * 2
}
val resultado = double(5)
```

Tipos de funciones:

``` Kotlin
// Recibe un número, devuelve un texto
fun describirMonedas(monedas: Int): String {
    return "Tienes $monedas monedas"
}

// Recibe un número, devuelve verdadero/falso
fun puedeComprar(monedas: Int): Boolean {
    return monedas >= 5
}

// Recibe dos cosas, devuelve un número
fun sumar(a: Int, b: Int): Int {
    return a + b
}
```

Las funciones siempre van al final de la linea de onCreate(bloque que ejecuta Android), despues de `setContentView(...)`
Ejemplo:

```Kotlin
privare fun probarKotlin (){
    val nombre: String = "Rocio"
    var intentos: Int = 1
    intentos++ 
    log.d("PMDM","Hola, $nombre. Intentos: $intentos")

}
```

## Cuatro tipo de datos mas usados (investigar más)

```kotlin
val titulo: String = "Mi calculadora" // texto

val unidades: Int = 4                // entero

val precio: Double = 12.5            // decimal: se escribe con punto

val operacionValida: Boolean = true  // true o false
```

## Operadores y conversiones

"+" --> suma

"-" --> resta

"*" --> multiplicacion

"/" --> division

"%" --> resto

Para comparar: usamos lo siguiente

"==" --> igualdad

"!=" --> desigual

"<" --> menor que

">" --> mayor que

"<=" --> menor igual que

">=" --> mayor igual que

condiciones:
"&&" exige dos condiciones

"||" basta con una

"!" invierte el valor de un Boolean

En una app tenemos que convertir los textos a numero, por que siempre recibe un cuadro de String.
Para convertir siempre vamos a usar lo siguiente `toDoubleOrNull()` cuando puede estar vacio o contener letras. nos devolveria un numero si es valido y un null si esta vacio.

Ejemplo:

```kotlin
val dato = "12.5"
val numero: Double? = dato.toDoubleOrNull() // el ? indica que el numero puede contener un numero o un null 
val mensaje = if(numero == null) "Dato incorrecto" else "Número: $numero"
```

## If, else, y when

`if` ejecuta una decision segun la condicion del Boolean.

`else` cubre el caso contrario al if

`when` permite resolver varios casos y señada el resultado con "->" o bloque de cada opcion.

podemos usarlo de la siguiente manera:

```kotlin

val edad = 17

val resultado = if (edad >= 18) "Mayor de edad" else "Menor de edad"

val nota = 8

val calificacion = when {

    nota < 5 -> "Suspenso"

    nota < 7 -> "Aprobado"

    nota < 9 -> "Notable"

    else -> "Sobresaliente"

}

```
