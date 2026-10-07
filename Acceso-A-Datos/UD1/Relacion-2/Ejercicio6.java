import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio6 {
    public static void main(String[] args){
        Path ruta = Path.of("datos", "productos_errores.csv");
        Path rutaValida = Path.of("datos", "productos_validos.csv");

        try {
            // verficamos si la ruta existe y damos un aviso si no.
            if(Files.notExists(ruta)){
                System.out.println("El archivo no existe");
                return;
            }
            //leemos las lineas del archivo de dicha ruta. 
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            // creamos la lista donde almacenamos al lineas validas.
            List<String> lineasValidas = new ArrayList<>();
            // guardamos las lineas  ya aceptadas de id. para comprobar si ya existen.
            List<Integer> idExistentes = new ArrayList<>();
             // recorremos con un for salteando la cabecera del archivo.
             for(int i=1; i<lineas.size(); i++){
                String linea = lineas.get(i);
                String campos[] = linea.split(";", -1);
                // comprobamos si los campos son validos.
                if(campos.length != 3){
                    System.out.println("linea: " + ( i + 1) + "Numero de campo incorrecto");
                }else{
                    // validamos el id 
                    
                    

                }
             }




        }catch (IOException e){
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
         


    }
}
