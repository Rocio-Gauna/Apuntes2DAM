Ejercicio 
 - ¿que es un programa concurrente? o ¿ es lo mismo que la paralela?
 es al capacidad de un sistema para gestionar multiples tarea que se solapan en el tiempo. 
 Las tareas avanzan de forma independiente. 
 - ¿ Que diferencia la programacion paralela de la multiprogramcion?
La programcion paralela consiste en ejecutar multiples tareas de foma simultnea en el mismo instante, lo cual requiere hardware fisico


llamadas al sistema: 
Ejecucion -> llamada al sistema (proceso usuario-modo usuario)-> Ejecucion llamada (Kernel) -> Ejecucion   (proceso usuario-modo usuario)

5/11
Procesos:
 cada procesos tiene un estado y cada estado tiene un modo de espera.

 quien decide el estado de cada proceso?el Sistema Operativos

 un procesos contiene varios estados los 5 estados. los cambia el sistemas operativo. no es lineal el cambio de estado.

 ejemplo
 -nuevo
 -listo
 -en ejecucion
 -bloqueado: espera una tecla o algo
 -terminado: liera todos los recursos

 distintos estado de un proceso. 

un proceso nuevo cuando se admite para a listo, luego pasa de planificacion a en ejecucion, cuando esta ahi puede pasar a terminado, bloqueado o listo. 
se ejecuta todo a la vez. cuando esta en ejecucion


ejemplo de taller: 

nuevo: cliente
admitido: cita
listo: esperando - puede coger otro 
planificacion :cita aceptada
en ejecucion: elevador
E/S: necesita pieza
bloquedao : espera pieza
evento llega pieza
terminado cuando termina

colas de procesos.

no se puede pasar de listo a bloquedo. 

no se puede bloquedar la ejecucion 

cuando un procesos se termina, termina totalmente.

multiprogramcacion es admitir varios procesos en memoria. 

hay distintas colas de procesos

cola de procesos general todos 

cola de precesos preparados. son todos los procesos listos

cola de dispositivos
entrada y salida, son los que estan esperando


PLanificacion de procesos

existen dos pasos
a corto plazos eligen que se ejecuta en ese momento en milisegundos, ejecuta varios procesos distintos, no importa los errores minimos se ejecuta muchas veces que el de largo plazo. 
procesos de largo plazos:
puede ir mas lento, 

en todos los procesos se usan ambos 

cambio de contexto

operaciones basicas con procesos
create: Creacion de nuevos procesos
wait: El oadre espera hasta el hijo
destroy: 
el padre e hijo no comparte la memoria
si un padre muere el hijo en un arbol(mueren todos)

create:

para lanzar un proceso hacemos un:
 ProcessBuilder pb = new processbuilder(..parametros..)

con un objeto
process p= pb.start() 
()entre parentesis ejemplo un ls o un ls -l ( "ls", "-l")

comunicacion de procesos...

redirectErrorStream(bool) junta todo a la salida. stdout y stderr


