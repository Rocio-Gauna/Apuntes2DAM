import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class GuardarProductosBinario {

    public static void main(String[] args) {

        Path archivo = Path.of("datos", "productos.dat");

        // TODO 1: crear una lista con al menos cuatro Producto.
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "raton", 012, 6));
        productos.add(new Producto(2, "teclado", 013, 8));
        productos.add(new Producto(3, "pantalla", 014, 10));
        productos.add(new Producto(4, "mouse", 015, 5));

        // TODO 4: controlar IOException.
        try {
            // TODO 2: crear la carpeta datos si hace falta.
            if(Files.notExists(archivo)){
                Files.createDirectories(archivo.getParent());
                Files.createFile(archivo);
            }
            // TODO 3: guardar la lista completa con ObjectOutputStream.

            try(ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(archivo))){
                
                salida.writeObject(productos);
            }
            System.out.print("productos guardados " + Files.size(archivo) + " bytes \n");

            if(Files.exists(archivo) &&Files.size(archivo) > 0){
                System.out.print("El producto es mayor que 0");
            }
        

        }catch (IOException e){
            System.err.println("error: " + e.getMessage());
        }
    }
}
