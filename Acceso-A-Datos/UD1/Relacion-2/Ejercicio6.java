
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio6 {

    public static void main(String[] args) {
        Path ruta = Path.of("datos", "productos_errores.csv");
        Path rutaValida = Path.of("datos", "productos_validos.csv");

        try {
            // verficamos si la ruta existe y damos un aviso si no.
            if (Files.notExists(ruta)) {
                System.out.println("El archivo no existe");
                return;
            }
            //leemos las lineas del archivo de dicha ruta. 
            List<String> lineas = Files.readAllLines(ruta, StandardCharsets.UTF_8);
            // creamos la lista donde almacenamos al lineas validas.
            List<String> lineasValida = new ArrayList<>();
            // guardamos las lineas  ya aceptadas de id. para comprobar si ya existen.
            List<Integer> idExistentes = new ArrayList<>();
            // recorremos con un for salteando la cabecera del archivo.
            for (int i = 1; i < lineas.size(); i++) {
                String linea = lineas.get(i);
                String campos[] = linea.split(";", -1);
                // comprobamos si los campos son validos.
                if (campos.length != 3) {
                    System.out.println("linea: " + (i + 1) + "Numero de campo incorrecto");
                } else {
                    // validamos el id 
                    int idLinea = 0;
                    boolean idValido = true;

                    try {
                        idLinea = Integer.parseInt(campos[0].trim());

                    } catch (NumberFormatException e) {
                        System.out.println("Linea" + (i+1)+":el ID debe ser mayor que 0");
                        idValido = false;
                    }
                    if(idValido){
                        String nombreLinea = campos[1].trim();
                        if(nombreLinea.isEmpty()){
                            System.out.println("Linea "+ (i+1)+ ":el nombre no puede estar vacio");

                        }else{
                            int stockLinea = 0;
                            boolean stockValido = true;
                            try {
                                stockLinea =Integer.parseInt(campos[2].trim());

                            } catch (NumberFormatException e) {
                                System.out.println("Linea " + (i+1) + ": Stock no numerico.");
                                stockValido=false;
                            }
                            if(stockValido && stockLinea <0 ){
                                System.out.println("Linea "+ (i+1)+ ": el stock no puede ser negativo");
                                stockValido = false;

                            }
                            if(stockValido){
                                boolean idYaAceptado = false;
                                for (int idExistente : idExistentes){
                                    if(idExistente == idLinea){
                                        idYaAceptado =true;
                                    }
                                }
                                if(idYaAceptado){
                                    System.out.println("Linea "+ (i+1)+ ": ID duplicado(" + idLinea + ").");
                                }else{
                                    // ¡La fila pasó TODAS las validaciones!
                                    String lineaValidada = idLinea + ";" + nombreLinea + ";" + stockLinea;
                                    lineasValida.add(lineaValidada);
                                    idExistentes.add(idLinea);
                                }
                            }
                        }
                    }
                }
            }
            // contenido final : Cabecera + failas validas
            List<String> salida = new ArrayList<>();
            salida.add("id;nombre;stock");
            salida.addAll(lineasValida);
            //Escribimos el fichero nuevamente
            Files.write(rutaValida, salida, StandardCharsets.UTF_8);
            System.out.println("Importacion terminada. Filas validas guardadas: "+ lineasValida.size());


        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
