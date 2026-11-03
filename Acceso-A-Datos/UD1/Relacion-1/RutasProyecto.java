
import java.nio.file.Path;



public class RutasProyecto {
    public static void main(String[] args) {
        //Ejercicio que representa las RUTAS de la carpeta datos. El fichero clubes.txt
        // y una subcarpeta "copias". NO CREA NADA 
        
        //Con el path nombramos la ruta de datos
        Path carpetaDatos = Path.of("datos"); // /datos
        
        // nombramos la ruta de datos con cluebes.txt dentro
        Path carpetaClubes = carpetaDatos.resolve("clubes.txt"); //  /datos/clubes.txt
        
        // nombramos la ruta de datos dentro de datos la copia
        Path carpetaCopias = carpetaDatos.resolve("copias"); // /datos/copias

        //Mostramos las 3 rutas relativas y sus  correpondiente rutas

        System.out.println("Ruta relativa: " + carpetaDatos);
        System.out.println("Ruta relativa: " + carpetaClubes);
        System.out.println("Ruta relativa: " + carpetaCopias);
        
        //Mostramos las rutas absolutas de cada
        System.out.println("Ruta absoluta: "+ carpetaDatos.toAbsolutePath());
        System.out.println("Ruta relativa: " + carpetaClubes.toAbsolutePath());
        System.out.println("Ruta relativa: " + carpetaCopias.toAbsolutePath());
    }
}
