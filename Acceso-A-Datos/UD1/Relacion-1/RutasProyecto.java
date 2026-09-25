
import java.nio.file.Path;

public class RutasProyecto {

    public static void main(String[] args) {
    // construye el onjeto Path que represeta la carpeta datos "Path.of("datos")".
    Path carpeta = Path.of( "datos");
    //resolve() contruye la ruta nueva añadiendo "clubes.txt"
    Path archivo = carpeta.resolve("clubes.txt");
    Path copia = carpeta.resolve("copias");

    // muestra las 3 rutas relativas con el "System.out.println"
        System.out.println("Ruta relativa carpeta datos: " + carpeta);
        System.out.println("Ruta relativa archivo: " + archivo);
        System.out.println("Ruta relativa copia: " + copia);

    //muestra las 3 rutas absolutas con toAbsolutePath() + system.out.println

    System.out.println("Ruta absoluta carpeta: "+ carpeta.toAbsolutePath());
    System.out.println("Ruta absoluta archivo: "+ archivo.toAbsolutePath());
    System.out.println("Ruta absoluta copias: "+ copia.toAbsolutePath());
    
    // no es necesario poner el trycatch por que no esta creando nada solo contrusyendo en memoria y mostrandolo en consola 
    }
}
