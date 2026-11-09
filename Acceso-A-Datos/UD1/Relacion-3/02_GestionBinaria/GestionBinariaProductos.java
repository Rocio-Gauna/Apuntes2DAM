import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionBinariaProductos {

    public static void main(String[] args) {

        Path archivo = Path.of("datos", "productos.dat");

        // TODO 1: comprobar que el archivo existe.
        if(Files.notExists(archivo)){
            System.err.print("ERROR: El archivo no existe");
        }
        // TODO 2: recuperar el objeto y comprobar que es una lista de Producto.

        try (ObjectInputStream entrada = new ObjectInputStream(Files.newInputStream(archivo))) {
            Object dato = entrada.readObject();
            if (!(dato instanceof List<?>)) {
                System.err.print("ERROR: El objeto no es una lista");
                return;
            }

            List<?> lista = (List<?>) dato;
            List<Producto> productos = new ArrayList<>();
            for (Object elemento : lista) {
                if (!(elemento instanceof Producto)) {
                    throw new IOException("ERROR: El objeto no es un Producto");
                }
                productos.add((Producto) elemento);
            }
            productos.forEach(System.out::println);
            Scanner teclado = new Scanner(System.in);
            System.out.print("ID del producto : ");
            int id = Integer.parseInt(teclado.nextLine().trim());
            System.out.print("Nuevo stock : (>=0): ");
            int stock = Integer.parseInt(teclado.nextLine().trim());
            if (stock < 0) {
                System.err.print("ERROR: El stock no puede ser negativo");
                return;
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.print("ERROR: " + e.getMessage());
        }
        // TODO 3: mostrar los productos.
        // TODO 4: pedir un ID y un nuevo stock.
        // TODO 5: si existe, modificarlo y guardar de nuevo la lista completa.
        // TODO 6: volver a cargar para comprobar la persistencia.
    }
}
